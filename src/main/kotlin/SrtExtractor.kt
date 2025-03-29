import org.vosk.LibVosk
import org.vosk.LogLevel
import org.vosk.Model
import java.io.File
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import javax.sound.sampled.AudioFormat

class SrtExtractor(
    private val modelPath: String,
    private val defaultThreads: Int = 4
) {

    private val model: Model by lazy { Model(modelPath) }
    private val audioProcessor = AudioProcessor(model)
    private val audioReader = AudioReader()
    private val audioValidator = AudioValidator()

    init {
        LibVosk.setLogLevel(LogLevel.WARNINGS)
    }

    fun extractText(audioFilePath: String, numThreads: Int = defaultThreads): String {
        val (audioData, format) = readAndValidateAudio(audioFilePath)
        return processChunks(audioData, format, numThreads).joinToString(" ")
    }

    fun extractSubtitles(
        audioFilePath: String,
        outputPath: String,
        numThreads: Int = defaultThreads
    ): Boolean {
        val (audioData, format) = readAndValidateAudio(audioFilePath)
        val chunkDuration = calculateChunkDuration(audioData.size, format, numThreads)
        val results = processChunks(audioData, format, numThreads)

        return try {
            val outputFile = File(outputPath).apply {
                parentFile?.mkdirs()
                check(parentFile?.canWrite() == true) { "Нет прав на запись в директорию" }
            }

            outputFile.writeText(generateSrtContent(results, chunkDuration))
            println("Субтитры успешно сохранены: ${outputFile.absolutePath}")
            true
        } catch (e: Exception) {
            System.err.println("Ошибка записи файла: ${e.javaClass.simpleName}")
            e.printStackTrace()
            false
        }
    }

    private fun readAndValidateAudio(path: String): Pair<ByteArray, AudioFormat> {
        val (audioData, format) = audioReader.readAudioFile(path)
        audioValidator.validateAudioFile(format)
        return audioData to format
    }

    private fun processChunks(
        audioData: ByteArray,
        format: AudioFormat,
        numThreads: Int
    ): List<String> {
        val executor = Executors.newFixedThreadPool(numThreads)
        val tasks = createChunkTasks(audioData, format, numThreads)

        return executor.invokeAll(tasks).mapNotNull { getResultSafely(it) }.also {
            executor.shutdown()
        }
    }

    private fun createChunkTasks(
        audioData: ByteArray,
        format: AudioFormat,
        numThreads: Int
    ): List<Callable<String>> {
        val chunkSize = audioData.size / numThreads
        return (0 until numThreads).map { i ->
            val start = i * chunkSize
            val end = if (i == numThreads - 1) audioData.size else (i + 1) * chunkSize
            Callable { audioProcessor.processAudioChunk(format, audioData, start, end) }
        }
    }

    private fun generateSrtContent(results: List<String>, chunkDuration: Double): String {
        return results.mapIndexed { index, text ->
            val startTime = index * chunkDuration
            val endTime = startTime + chunkDuration
            """
                |${index + 1}
                |${startTime.toSrtTime()} --> ${endTime.toSrtTime()}
                |$text
                |
            """.trimMargin()
        }.joinToString("\n")
    }

    private fun calculateChunkDuration(totalBytes: Int, format: AudioFormat, numChunks: Int): Double {
        val totalSamples = totalBytes / 2
        return (totalSamples.toDouble() / format.sampleRate) / numChunks
    }

    private fun Double.toSrtTime(): String {
        val totalMillis = (this * 1000).toLong()
        return String.format(
            "%02d:%02d:%02d,%03d",
            totalMillis / 3600000,
            (totalMillis % 3600000) / 60000,
            (totalMillis % 60000) / 1000,
            totalMillis % 1000
        )
    }

    private fun getResultSafely(future: Future<String>): String? = try {
        future.get()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}