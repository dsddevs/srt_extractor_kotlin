import javax.sound.sampled.AudioFormat

class AudioValidator {
    fun validateAudioFile(format: AudioFormat) {
        val rate = format.sampleRate == 16000.0f
        val sizeInBits = format.sampleSizeInBits == 16
        val channel = format.channels == 1
        require(rate && sizeInBits && channel) {
            "Audio format should be 16 kHz, 16 bit, mono"
        }
    }
}