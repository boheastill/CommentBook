# 注释阅读扩展  [![Downloads](src/main/resources/META-INF/pluginIcon.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

## 功能列表

- [x] 让类/方法的注释变为动态文本,默认使用<kbd>Ctrl+\\</kbd>向下翻页
- [ ] txt文本导入到指定方法的注释中，支持用户指定每次显示行数
- [x] 支持通过关键词搜索，让注释显示文本相应上下文内容
- [x] 支持指定页码/比例，让注释显示文本相应上下文内容
- [x] 支持把当前注释区域显示的文本，作为书签保存下来。
- [x] 支持跳转到指定书签，让注释显示文本相应上下文内容
- [ ] 支持通过网络下载指定文档
- [ ] 支持通过访问服务器日志，来便捷的一边看日志报错信息，一边修复程序
- [ ] 支持通过网络搜索指定关键词，获取网络中的信息，帮助更好的开发
- [ ] 支持通过非gui的命令交互

---

## function list

- [x] makes class/method comments dynamic text and defaults to < KBD >Ctrl+\\</ KBD > to page down
- [ ] TXT Imports the text to the annotation of the specified method. Users can specify the number of lines to be
  displayed each time
- [x] allows comments to display text in context by keyword search
- [x] allows you to specify the page number/scale for comments to display text in context
- [x] supports saving the text displayed in the current comment area as a bookmark.
- [x] supports jumping to a specified bookmark, allowing comments to display text in context
- [ ] Supports downloading specified documents over the network
- [ ] Allows you to access the server logs to conveniently view the error information and fix the program at the same
  time
- [ ] You can search for specific keywords on the Internet to obtain information on the Internet, which facilitates
  development
- [ ] Supports interaction through non-GUI commands

---

### 不使用gui的命令式交互参考

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
- 代码误替换风险

---
reader alpha 0.1
----------------

# REFERENCE

<!-- Plugin description -->

<p>An IDEA built-in TXT File reader
        <ul>
            <li> support forward page (+ number, number of customized to be completed)</li>
            <li>backward page (+ number)</li>
            <li>,safe mode</li>
            <li>search text page number</li>
            <li> jump to specified page/proportion</li>
            <li>save/fetch bookmarks</li>
            <li>load specified path TXT document (to be completed)</li>
            <li>download (to be completed) Display at the cursor position</li>
            <li>etc</li>
        </ul>
        </p>
        <p>readme描述： 一个插件，让diea的注释区变成你的文本阅读器，Crtl+\ 为翻页键
        <ul>
            <li>支持向前翻页）、向后翻页、安全模式、搜索文本页码、跳转指定页/比例、存/取书签、加载指定路径txt文档等功能</li>
            <li>先在右侧工具栏的GUI设置好要进行的操作，然后无脑Ctrl+\ 翻页/搜索/查询就行</li>
        </ul>
        </p>
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
