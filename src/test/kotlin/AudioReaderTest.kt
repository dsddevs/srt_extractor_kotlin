import org.junit.jupiter.api.assertThrows
import java.io.File
import java.io.FileNotFoundException
import javax.sound.sampled.AudioSystem
import kotlin.test.Test

class AudioReaderTest {

    private val audioReader = AudioReader()

    @Test
    fun `readAudioFile with correct path should not throw exception`(){
        val path = "audio_file_path.wav"
        audioReader.readAudioFile(path)
    }

    @Test
    fun `readAudioFile with empty path should throw exception`(){
        val path = ""
        val exception = assertThrows<IllegalArgumentException> {
            audioReader.readAudioFile(path)
        }
        assert(exception.message == "Error: Audio file path is empty")
    }

    @Test
    fun `readAudioFile with correct is file or is present should throw exception`(){
        val path = "audio_file_path.wav"
        val file = File(path)
        if (!file.isFile || !file.exists()) {
            throw FileNotFoundException("Error: Audio file does not exist or is not a file: $path")
        }
        audioReader.readAudioFile(path)
    }

    @Test
    fun `readAudioFile with in correct is not file or is absent should throw exception`(){
        val path = "audio_file_path.wav"
        val file = File(path)
        if (file.exists() && AudioSystem.getAudioFileFormat(file) != null) {
            throw FileNotFoundException("Error: Audio file does not exist or is not a file: $path")
        }
        val exception = assertThrows<FileNotFoundException> {
            audioReader.readAudioFile(path)
        }
        assert(exception.message == "Error: Audio file does not exist or is not a file: $path")
    }
}