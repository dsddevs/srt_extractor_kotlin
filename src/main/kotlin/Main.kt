fun main() {

    val modelPath = "vosk_model_path"
    val audioFilePath = "audio_file_path" //.wav format
    val outputFilePath = "srt_file_path" // .srt format

    val strExtractor = SrtExtractor(modelPath)
    strExtractor.extractSubtitles(audioFilePath, outputFilePath)

}