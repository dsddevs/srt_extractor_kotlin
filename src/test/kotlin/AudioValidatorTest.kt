import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.sound.sampled.AudioFormat


class AudioValidatorTest {

    private val audioValidator = AudioValidator()

    @Test
    fun `validateAudioFile with correct format should not throw exception`() {
        val format = AudioFormat(16000.0f, 16, 1, true, false)
        audioValidator.validateAudioFile(format)
    }

    @Test
    fun `validateAudioFile with incorrect sample rate should throw exception`(){
        val format = AudioFormat(8000.0f, 16, 1, true, false)
        val exception = assertThrows<IllegalArgumentException> {
            audioValidator.validateAudioFile(format)
        }
        assert(exception.message == "Audio format should be 16 kHz, 16 bit, mono")
    }

    @Test
    fun `validateAudioFile with incorrect sample size in bits throws exception`(){
        val format = AudioFormat(16000.0f, 9, 1, true, false)
        val exception = assertThrows<IllegalArgumentException>{
            audioValidator.validateAudioFile(format)
        }
        assert(exception.message == "Audio format should be 16 kHz, 16 bit, mono")
    }

    @Test
    fun `validateAudioFile with incorrect channel throws exception`(){
        val format = AudioFormat(16000.0f, 16, 2, true, false)
        val exception = assertThrows<IllegalArgumentException>{
            audioValidator.validateAudioFile(format)
        }
        assert(exception.message == "Audio format should be 16 kHz, 16 bit, mono")
    }

}