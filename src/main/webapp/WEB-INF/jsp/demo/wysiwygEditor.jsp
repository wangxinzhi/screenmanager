<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2017/12/7
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--bootstrap-wysiwyg富文本编辑器--%>
<div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
    <div class="btn-group">
        <a class="btn dropdown-toggle" data-toggle="dropdown" data-original-title="字体 Font"><i class="icon-font"></i><b class="caret"></b></a>
        <ul class="dropdown-menu"></ul>
    </div>
    <div class="btn-group">
        <a class="btn dropdown-toggle" data-toggle="dropdown" data-original-title="字体大小 Font Size"><i class="icon-text-height"></i><b class="caret"></b></a>
        <ul class="dropdown-menu">
            <li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
            <li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
            <li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
        </ul>
    </div>
    <div class="btn-group">
        <a class="btn" data-edit="bold" data-original-title="加粗 Bold(Ctrl/Cmd+B)"><i class="icon-bold"></i></a>
        <a class="btn" data-edit="italic" data-original-title="斜体 Italic(Ctrl/Cmd+I)"><i class="icon-italic"></i></a>
        <a class="btn" data-edit="strikethrough" data-original-title="删除线 Strikethrough"><i class="icon-strikethrough"></i></a>
        <a class="btn" data-edit="underline" data-original-title="下划线 Underline(Ctrl/Cmd+U)"><i class="icon-underline"></i></a>
    </div>
    <div class="btn-group">
        <a class="btn" data-edit="insertunorderedlist" data-original-title="加点 Bullet list"><i class="icon-list-ul"></i></a>
        <a class="btn" data-edit="insertorderedlist" data-original-title="数字排序 Number list"><i class="icon-list-ol"></i></a>
        <a class="btn" data-edit="outdent" data-original-title="减少缩进 Reduce indent (Shift+Tab)"><i class="icon-indent-left"></i></a>
        <a class="btn" data-edit="indent" data-original-title="增加缩进 Indent (Tab)"><i class="icon-indent-right"></i></a>
    </div>
    <div class="btn-group">
        <a class="btn" data-edit="justifyleft" data-original-title="左对齐 Align Left (Ctrl/Cmd+L)"><i class="icon-align-left"></i></a>
        <a class="btn" data-edit="justifycenter" data-original-title="居中 Center (Ctrl/Cmd+E)"><i class="icon-align-center"></i></a>
        <a class="btn" data-edit="justifyright" data-original-title="右对齐 Align Right (Ctrl/Cmd+R)"><i class="icon-align-right"></i></a>
        <a class="btn" data-edit="justifyfull" data-original-title="垂直对齐 Justify (Ctrl/Cmd+J)"><i class="icon-align-justify"></i></a>
    </div>
    <div class="btn-group">
        <a class="btn" data-edit="undo" data-original-title="撤销 Undo (Ctrl/Cmd+Z)"><i class="icon-undo"></i></a>
        <a class="btn" data-edit="redo" data-original-title="恢复 Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>
    </div>
</div>
<div id="editor" contenteditable="true">
</div>


