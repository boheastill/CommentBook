# 注释文档阅读器  [![Downloads](src/main/resources/META-INF/pluginIcon.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

----------------

<!-- Plugin description -->
<div>
<h3>Target</h3>
<p> Establish the binding between code and document, so that it is easy to read the binding document without leaving IDEA. </p>
<ul>
<li> supports annotations for any class/method/field, bound to a text document (such as server log, requirements document, sample code, API document, web tutorial). </li>
<li> The contents of the bound document are displayed in the comment area by pagination </li>
The number of lines and the length of each line can be customized. </li>
<li> Binding documents, support search, back and forth page, tag access, these operations can be triggered by clicking the GUI, can also be triggered by typing commands </li>
</ul>
</div>

<div>
  <h3>目标</h3>
  <p>建立代码与文档的绑定，以便于在不离开IDEA的情况下，很方便的阅读绑定的文档。</p>
  <ul>
    <li>支持对任意类/方法/字段的注释，绑定一个文本文档（比如服务器日志、需求文档、示例代码、API文档、网络教程）。</li>
    <li>被绑定的文档的内容，会按被分页的展示在注释区域</li>
    <li>注释的区域，支持自定义行数、每行长度</li>
    <li>被绑定的文档，支持搜索、前后翻页、标签存取，这些操作可以通过点击GUI触发，也可以通过输入命令的触发</li>
  </ul>
</div>
----

## 功能列表
- [x] <kbd>Ctrl+Shift+\\</kbd>,弹出一个支持中英文切换的可选择功能的窗口，可以在里面进行各种操作
- [ ] 让类/方法的注释变为动态文本,默认使用<kbd>Ctrl+\\</kbd>向下翻页
- [ ] txt文本导入到指定方法的注释中，支持用户指定每次显示行数
- [x] 支持通过关键词搜索，让注释显示文本相应上下文内容
- [x] 支持指定页码/比例，让注释显示文本相应上下文内容
- [x] 支持把当前注释区域显示的文本，作为书签保存下来。
- [x] 支持跳转到指定书签，让注释显示文本相应上下文内容
- [ ] 支持通过网络下载指定文档
- [ ] 支持通过访问服务器日志，来便捷的一边看日志报错信息，一边修复程序
- [ ] 支持通过网络搜索指定关键词，获取网络中的信息，帮助更好的开发
- [ ] 支持通过非gui的命令交互

## function list
- [x] <KBD>Ctrl+Shift+\\</KBD>, pop up a support Chinese and English switch selectable function window, you can carry out various operations in it
- [ ] makes class/method comments dynamic text and defaults to < KBD >Ctrl+\\</ KBD > to page down
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

## Note/注意事项

- 插件等待加载完成再用
- 代码误替换风险


<!-- Plugin description end -->

----

# alpha内容：

### 不使用gui的命令式交互参考

- "l", "左", "前", "上" -> 向前翻页

- "s", "安" -> 安全模式

- "r", "右", "后", "下" -> 向后翻页

- "f", "查", "搜" -> 搜索指定文本

- "到" -> 跳转到指定页码

- "标", "存" -> 加标签

- "取", "签" -> 跳转到指定标签
  Download the [latest release](https://github.com/boheastill/pd2/releases/latest)
