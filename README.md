# 🎧 SRT Extractor – High-Performance Audio-to-Subtitle Generator

SRT Extractor is a powerful and scalable Kotlin-based tool that leverages [Vosk](https://alphacephei.com/vosk/) for offline speech recognition to convert audio files into `.srt` subtitle files. Designed with performance and accuracy in mind, it utilizes multithreading to handle large audio files efficiently.

---

## 🚀 Features

- 🎙️ **Offline Speech Recognition** powered by Vosk
-  ⚡  **Multithreaded Audio Processing** for high performance
- 🧠 **Intelligent Audio Chunking** for accurate transcription
- 🎯 **SRT Subtitle Generation** with precise timestamps
- ✅ **Robust Audio Validation** and error handling
- 🧪 **Unit tested** with JUnit and Mockito

---

## 🛠️ Technology Stack

- **Kotlin** (JVM 21)
- **Vosk API** for speech recognition
- **JUnit 5** and **Mockito** for testing
- **Commons Text**, **Kotlinx Serialization**, **org.json**

---

## 📦 Installation

Clone the repository:

```bash
git clone https://github.com/dsddevs/srt_extractor_kotlin.git
cd srt-extractor
./gradlew build
```

## 🔧 Configuration

Ensure you have a Vosk model downloaded and placed in an accessible directory. You can download pre-trained models from Vosk Models.

## 🧑‍💻 Usage

#### Extract Subtitles from Audio File

```kotlin
fun main() {

    val modelPath = "models/vosk-model-cn-0.22"
    val audioFilePath = "audio_file_path" //.wav format
    val outputFilePath = "srt_file_path" // .srt format

    val strExtractor = SrtExtractor(modelPath)
    strExtractor.extractSubtitles(audioFilePath, outputFilePath)

}
```


## 📝 Subtitle Format Example

```dbn-psql
1
00:00:00,000 --> 00:07:28,426
哇

2
00:07:28,426 --> 00:14:56,853
我 现在 还 瘦 呢 我 回 我 以后 都 不准 拿下 你 来 我 读 过 的 清真 轻轻 的 什么 必须 跟 人 跟车 真 兄弟 中 人 彼此 心 中文 的 京都 之 巅 哥仨 之 行 可 没有 考卷 图 这是 为什么 这样 设计 的 我们 那个 时候 在 做 这 卡 的 时候 出现 了 一个 抱 一个 抱 的 是 什么 得到 了 我 的 老K 反正 呢 就是 因为 有 这个 爸爸 导致 我们 的 数据 收集 有 问题 那 其实 这个 数据 收集 好像 你 看起来 就是 差 一点点 但 只要 拆 的 零点零一 分 来 谢谢 我 的 手分 不 知道 穿 绿色 肯定 是 你 控制 心脏 做 副总 的 样子 简直 是 像 父亲 将 芳芳 知道 如何 去 就 真的 真的 真的 好 吗 他 活着 早 在 这儿 告诉 你 个 好 消息 公司 不 裁员 是 吗 嗯 我 跟 周 总 精算 过 了 目前 所有 的 研发 的 进度 将 提高 公司 整体 的 价值 老实 跟 你 说 吧 才 说 的 是 失踪 或者 说说 一下 大家 所以 你们 是 为 增加 压力 毕竟 不 还有 测试员 更 难 的 呀 但是 有 几 位 需要 谈一谈 虽然 它 的 进度 上 代理 公司 的 愿景 在 这 非常 时期 每个 螺丝 的 事 还 少 呢 你 这 手艺 什么 关系 你们 怎么 认识 的 在 那边 认识 的 很 少 运动 跟 你 提 过 我 你 想 知道 花锦绣 问 他 想 所有人 说 他 知道 你 的 傲娇 不 懂 的 事 所以 到底 什么 关系 这些 你 在 公司 也 是 前辈 也 是 公司 非常 看重 人才 问 尊敬 但 不 代表 你 可以 感受 事实 是 知情者 记得 看 妈宝 所以 你 跟 女生 先 从 他 高中 时 的 认识 了 教书育人 没有 什么 如果 不是 你 他 不 晓得 我 什么 意思 他 跟 终于 找到 我 与 真心 跟踪 我 他 说 你 跟 周 属于 私相授受 说 有 段 时间 暗中 盯 着 你 没 想到 怎么 那么 从 北京 这么 久 没 发现 两 个人 看 我 好生 我 对不起 他 的 这个 战线 还 真的 是 有 一点 雏鸟 情结 偏执症 亚斯伯格 轻度 抑郁 还有 自残 倾向 你 想 先 了解 一下 系统 的 了解 他 他 高三 的 时候 盯 着 他 一眼 哦 所以 你 辞掉 高中 效益 的 工作 就是 因为 你 想 说 什么 你 刚刚 说 的 很 好 了 不说 她 解释 她 的 你 这么 容易 就 让 他 一 开口 说话 了 吗 难道 你 问问 他 当初 怎么 对 你 的 这几年 要不是 我 跟 评委 一直 陪 着 你 你 觉得 你 能 熬 过来 嘛 而且 你 的 复仇 计划 呢 我们 踏入 帮 你 看 外观 上 与 亮堂 了 我 深觉 若 说 窦婴 决定 我们 的 知识 他 把 与其 同 上周 写字

3
00:14:56,853 --> 00:22:25,280
哇
```

## 📁 Project Structure

```dbn-psql
src/
 └── main/
     └── kotlin/
         └── AudioProcessor.kt
         └── AudioReader.kt
         └── AudioValidator.kt
         └── Main.kt
         └── SrtExtractor.kt
 └── test/
     └── kotlin/
          └── AudioProcessorTest.kt
          └── AudioReaderTest.kt
          └── AudioValidatorTest.kt
```

## ⚠️ Requirements
- JDK 21
- WAV audio files (PCM format, 16-bit, mono)
- Pre-trained Vosk model

## 📄 License
This project is licensed under the Apache-2.0 license 