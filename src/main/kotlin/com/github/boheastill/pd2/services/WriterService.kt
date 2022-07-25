package com.github.boheastill.pd2.services

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorModificationUtil
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.util.ThrowableRunnable
import org.apache.commons.lang3.StringUtils


class WriterService {/*fun write(project: Project?, psiElement: PsiElement, comment: PsiDocComment) {
        try {
            WriteCommandAction.writeCommandAction(project).run<Throwable>(
                ThrowableRunnable<Throwable?> {
                    if (psiElement.containingFile == null) {
                        return@ThrowableRunnable
                    }

                    // 写入文档注释
                    if (psiElement is PsiJavaDocumentedElement) {
                        val psiDocComment: PsiDocComment = (psiElement as PsiJavaDocumentedElement).getDocComment()
                        if (psiDocComment == null) {
                            psiElement.node.addChild(comment.getNode(), psiElement.firstChild.node)
                        } else {
                            psiDocComment.replace(comment)
                        }
                    }

                    // 格式化文档注释
                    val codeStyleManager = CodeStyleManager.getInstance(psiElement.project)
                    val javadocElement = psiElement.firstChild
                    val startOffset = javadocElement.textOffset
                    val endOffset = javadocElement.textOffset + javadocElement.text.length
                    codeStyleManager.reformatText(psiElement.containingFile, startOffset, endOffset + 1)
                })
        } catch (throwable: Throwable) {
            LOGGER.error("写入错误", throwable)
        }
    }*/

    /*替换文本*/
    fun write(project: Project, editor: Editor, text: String) {
        if (StringUtils.isBlank(text)) {
            return
        }
        try {
            WriteCommandAction.writeCommandAction(project).run<Throwable>( {
                val start = editor.getSelectionModel().selectionStart
                //这里做的编辑会改掉代码
                EditorModificationUtil.insertStringAtCaret(editor, text)
                editor.getSelectionModel().setSelection(start, start + text.length)
            })
            ApplicationManager.getApplication().runWriteAction(Runnable {
                CommandProcessor.getInstance().executeCommand(project, Runnable {
                    // 具体操作
                }, "","")
            })

        } catch (throwable: Throwable) {
            LOGGER.error("写入错误", throwable)
        }
    }

    /*包含业务逻辑：行判定*/
    fun write(anActionEvent: AnActionEvent, text: String) {
        //text 格式化
        var ftext = text.trim().replace("\n", "").replace("\r", "").replace("\t", "")
            .replace("。", "。\n").replace(".", "\t.\n") //去除制表符

        val project = anActionEvent.getData(LangDataKeys.PROJECT) ?: return //?:表示如果project为空，则返回null
        //取出选中的文本
        val editor = anActionEvent.getData(LangDataKeys.EDITOR)
        if (editor != null) {
            //用户滑选中的文本
            val selectedText = editor.selectionModel.getSelectedText(true)
            println("selectedText：$selectedText")
            //todo 判断选中的是不是注释区域
            val blank = true// StringUtils.isBlank(selectedText)
            //把text包装为注释
            var wapperText = "/* $ftext */"
            if (blank) {
                //这里就直接替换了原文
                write(project, editor, wapperText)
//                println("finished")

            }
        }
    }


    companion object {
        private val LOGGER = Logger.getInstance(
            WriterService::class.java
        )
    }
}

var textStr =
    "豫章故郡，洪都新府。星分翼轸，地接衡庐。襟三江而带五湖，控蛮荆而引瓯越。物华天宝，龙光射牛斗之墟；人杰地灵，徐孺下陈蕃之榻。雄州雾列，俊采星驰。台隍枕夷夏之交，宾主尽东南之美。都督阎公之雅望，棨戟遥临；宇文新州之懿范，襜帷暂驻。十旬休假，胜友如云；千里逢迎，高朋满座。腾蛟起凤，孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；童子何知，躬逢胜饯。\n" +
            "\n" +
            "时维九月，序属三秋。潦水尽而寒潭清，烟光凝而暮山紫。俨骖騑于上路，访风景于崇阿；临帝子之长洲，得天人之旧馆。层峦耸翠，上出重霄；飞阁流丹，下临无地。鹤汀凫渚，穷岛屿之萦回；桂殿兰宫，即冈峦之体势。\n" +
            "\n" +
            "披绣闼，俯雕甍，山原旷其盈视，川泽纡其骇瞩。闾阎扑地，钟鸣鼎食之家；舸舰弥津，青雀黄龙之舳。云销雨霁，彩彻区明。落霞与孤鹜齐飞，秋水共长天一色。渔舟唱晚，响穷彭蠡之滨；雁阵惊寒，声断衡阳之浦。\n" +
            "\n" +
            "遥襟甫畅，逸兴遄飞。爽籁发而清风生，纤歌凝而白云遏。睢园绿竹，气凌彭泽之樽；邺水朱华，光照临川之笔。四美具，二难并。穷睇眄于中天，极娱游于暇日。天高地迥，觉宇宙之无穷；兴尽悲来，识盈虚之有数。望长安于日下，目吴会于云间。地势极而南溟深，天柱高而北辰远。关山难越，谁悲失路之人？萍水相逢，尽是他乡之客。怀帝阍而不见，奉宣室以何年？\n" +
            "\n" +
            "嗟乎！时运不齐，命途多舛。冯唐易老，李广难封。屈贾谊于长沙，非无圣主；窜梁鸿于海曲，岂乏明时？所赖君子见机，达人知命。老当益壮，宁移白首之心？穷且益坚，不坠青云之志。酌贪泉而觉爽，处涸辙以犹欢。北海虽赊，扶摇可接；东隅已逝，桑榆非晚。孟尝高洁，空余报国之情；阮籍猖狂，岂效穷途之哭！\n" +
            "\n" +
            "勃，三尺微命，一介书生。无路请缨，等终军之弱冠；有怀投笔，慕宗悫之长风。舍簪笏于百龄，奉晨昏于万里。非谢家之宝树，接孟氏之芳邻。他日趋庭，叨陪鲤对；今兹捧袂，喜托龙门。杨意不逢，抚凌云而自惜；钟期既遇，奏流水以何惭？\n" +
            "\n" +
            "呜乎！胜地不常，盛筵难再；兰亭已矣，梓泽丘墟。临别赠言，幸承恩于伟饯；登高作赋，是所望于群公。敢竭鄙怀，恭疏短引；一言均赋，四韵俱成。请洒潘江，各倾陆海云尔：\n" +
            "\n" +
            "滕王高阁临江渚，佩玉鸣鸾罢歌舞。\n" +
            "\n" +
            "画栋朝飞南浦云，珠帘暮卷西山雨。\n" +
            "\n" +
            "闲云潭影日悠悠，物换星移几度秋。\n" +
            "\n" +
            "阁中帝子今何在？槛外长江空自流。\n"