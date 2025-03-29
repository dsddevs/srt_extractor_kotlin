import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.UnsupportedAudioFileException

class AudioReader {

    @Throws(IOException::class, UnsupportedAudioFileException::class)
    fun readAudioFile(path: String): Pair<ByteArray, AudioFormat> {
        if (path.isEmpty()) {
            throw IllegalArgumentException("Error: Audio file path is empty")
        }
        val audioFile = File(path)

        if (!audioFile.isFile || !audioFile.exists()) {
            throw FileNotFoundException("Error: Audio file does not exist or is not a file: $path")
        }
        AudioSystem.getAudioInputStream(audioFile).use { stream ->
            return stream.readAllBytes() to stream.format
        }
    }
}