import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.vosk.Model
import org.vosk.Recognizer
import javax.sound.sampled.AudioFormat

class AudioProcessorTest {

    private val model: Model = mock(Model::class.java)
    private val recognizer: Recognizer = mock(Recognizer::class.java)
    private val audioProcessor = AudioProcessor(model)

    @Test
    fun `processAudioFile should process audio data correctly`() {
        AudioFormat(16000.0f, 16, 1, true, false)
        val audioData = ByteArray(1024)

        audioProcessor.processAudioFile(recognizer, audioData)

        verify(recognizer).acceptWaveForm(any(ShortArray::class.java), anyInt())
    }
}