# 文本注释插件

![Build](https://github.com/boheastill/pd2/workflows/Build/badge.svg)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

## 功能列表

- [x] 1.按快捷键<kbd>Ctrl+\\</kbd>唤醒插件功能
- [x] 2.光标所在位置会生成文本(如果光标选中，则会替换选中的全部文本)
- [x] 3.再按<kbd>Ctrl+\\</kbd>，默认显示下一页，会替换调之前展示出的文本【潜在风险点：代码里的文本和阅读器文本一样，新代码默认替换了旧文本。解决：开个不重要的类，或光标选中区域，或文本加特征符号（待完成）】
- [x] 4.在类注释输入指定命令，可以进行【向前翻页（+数量,数量自定待完成）、向后翻页（+数量）、安全模式、搜索文本页码、跳转指定页/比例、存/取书签、加载指定路径txt文档（待完成）、下载（待完成）】
- [ ] 5.快捷键映射表见文档，支持自定义（待完成）
- [x] 6.指定命令语法：
  在类注释上，以<code>^</code>或<code>……</code>为开头和结尾为语法行，中间以空格分隔，如：<code>^前7^</code>,<code>
  ……后1……</code>,
  注释：语法行开头有斜杠被认为是注释，不解析，如：<code>^/前7^</code> <code>……\后1……</code>
- [ ]  下载
- [ ]  读取指定路径文档
- [x] 截至2022-7-26,支持到最新预览版 IC-222.3345.90版本

- ---
功能|参数|

执行







## function list

- [x]  Press the shortcut key < KBD >Ctrl+\\</ KBD > to wake up the plugin function

- [x] Text is generated at the position where the cursor is located (if the cursor is selected, all selected text is
  replaced)
- [x]  Press < KBD >Ctrl+\\</ KBD >, the next page will be displayed by default, and the text displayed before the tone
  will be replaced [Potential risk point: the text in the code is the same as the text in the reader, the new code
  will replace the old text by default.
- [x] Enter the specified command in the class annotation to carry
  out [forward page turn (+ number, number customized to be completed), backward page turn (+ number), safe mode, search text page number, jump to specified page/proportion, save/fetch bookmark, load TXT document in specified path (to be completed), download (to be completed)]

- []Shortcut key mapping table see the document, support custom (to be completed)
- [x]  Specify the command syntax:
  On class comments, start with <code>^</code> or <code>... </code> is the beginning and end of the syntax line,
  separated by Spaces, for example: <code>^ First 7^</code>,<code>
  ... After 1... </code>,
  Comments: A syntax line with a slash at the beginning is considered a comment and is not parsed. For
  example: <code>^/ first 7^</code> <code>... After \ 1... </code>
- [] Download
- [] Reads the document in the specified path

---

## 命令简述

- "l", "左", "前", "上" -> 向前翻页

- "s", "安" -> 安全模式

- "r", "右", "后", "下" -> 向后翻页

- "f", "查", "搜" -> 搜索指定文本

- "到" -> 跳转到指定页码

- "标", "存" -> 加标签

- "取", "签" -> 跳转到指定标签
  Download the [latest release](https://github.com/boheastill/pd2/releases/latest)

## Note

- 插件等待加载完成再用

---
reader alpha 0.1
----------------

# REFERENCE

<!-- Plugin description -->
This Fancy IntelliJ Platform Plugin is going to be your implementation of the brilliant ideas that you have.

This specific section is a source for the [plugin.xml](/src/main/resources/META-INF/plugin.xml) file which will be
extracted by the [Gradle](/build.gradle.kts) during the build process.

To keep everything working, do not remove `<!-- ... -->` sections.
<!-- Plugin description end -->

## Installation

- Using IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "pd2"</kbd> >
  <kbd>Install Plugin</kbd>

- Manually:

  Download the [latest release](https://github.com/boheastill/pd2/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
