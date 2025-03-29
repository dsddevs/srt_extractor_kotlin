import org.json.JSONObject
import org.vosk.Model
import org.vosk.Recognizer
import java.nio.ByteBuffer
import javax.sound.sampled.AudioFormat

class AudioProcessor(
    private val model: Model
) {

    fun processAudioChunk(
        format: AudioFormat,
        audioData: ByteArray,
        start: Int,
        end: Int
    ): String {

        Recognizer(model, format.sampleRate).use { recognizer ->
            val bufferSize = 8192
            var position = start
            while (position < end) {
                val chunkSize = minOf(bufferSize, end - position)
                val audioDataCopy = audioData.copyOfRange(position, position + chunkSize)
                processAudioFile(recognizer, audioDataCopy)
                position += chunkSize
            }

            return JSONObject(recognizer.finalResult).getString("text")
        }

    }

    fun processAudioFile(recognizer: Recognizer, audioDataCopy: ByteArray){
        val partAudioData = ShortArray(audioDataCopy.size / 2)
        ByteBuffer.wrap(audioDataCopy).asShortBuffer().get(partAudioData)
        recognizer.acceptWaveForm(partAudioData, partAudioData.size)
    }

}