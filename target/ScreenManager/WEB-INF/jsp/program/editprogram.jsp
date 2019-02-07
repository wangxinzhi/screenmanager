<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/2/20
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path=request.getContextPath();
%>
<html>
<head>
    <title>修改节目</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap-select.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap-responsive.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap-switch.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/fileinput.min.css">
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="<%=path%>/js/jquery-3.2.1.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>
    <script src="<%=path%>/js/bootstrap-select.min.js"></script>
    <script src="<%=path%>/js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=path%>/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=path%>/js/bootstrap-switch.js"></script>
    <script src="<%=path%>/js/fileinput.min.js"></script>
    <script src="<%=path%>/js/zh.js"></script>
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="../index/top.jsp"></jsp:include>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="../index/menu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">修改节目信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="<%=path%>/program/${program.ID}/edit.do" id="editfrom" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="programId" class="col-sm-2 control-label">节目ID</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="number" class="form-control" id="programId" name="ID" value="${program.ID}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PName" class="col-sm-2 control-label">节目名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="PName" name="PName" value="${program.PName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PContent" class="col-sm-2 control-label">节目内容</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="PContent" name="PContent" value="${program.PContent}" placeholder="请概述节目内容">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PBeginTime" class="col-sm-2 control-label">节目开始时间</label>
                            <div class="input-group date form_datetime">
                                <div class="input-group col-sm-10">
                                    <input type="text" class="form-control" id="PBeginTime" name="BeginTime" value="${program.formatBeginTime()}" readonly>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar pull-right"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PEndTime" class="col-sm-2 control-label">节目结束时间</label>
                            <div class="input-group date form_datetime">
                                <div class="input-group col-sm-10">
                                    <input type="text" class="form-control" id="PEndTime" name="EndTime" value="${program.formatEndTime()}" readonly>
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar pull-right"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PSendPerson" class="col-sm-2 control-label">发布人</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="PSendPerson" name="PSendPerson" value="${program.PSendPerson}" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">展示节目的屏幕</label>
                            <div class="col-sm-10">
                                <select class="selectpicker show-tick form-control" multiple data-live-search="false" id="ScreensList" name="ScreensList">
                                    <c:forEach items="${screenslist}" var="s">
                                        <option value="${s.id}" selected="selected">${s.location}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="judge" class="col-sm-2 control-label">是否重新上传文件</label>
                            <div class="switch col-sm-10" data-on-lable="Yes" data-off-lable="No">
                                <input id="judge" name="judge" type="checkbox" checked/>
                            </div>
                        </div>
                        <div class="form-group" id="fileUploadForm" style="display: none">
                            <label for="fileUpload" class="col-sm-2 control-label" name="programUrl">节目资源URL</label>
                            <div class="input-group col-sm-10">
                                <input id="fileUpload" name="files" type="file" multiple placeholder="支持多个文件同时上传(不超过5个文件)">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <button class="btn btn-default" type="reset">重置</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-horizontal" role="form" >
                        <div id="feedbackForm" class="form-group">
                            <label for="feedback" class="col-sm-2 control-label">问题反馈</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="feedback" name="feedback" value="${feedback}" readonly="readonly">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h2 style="text-align: center;">节目时间安排表</h2>
                        <h6 style="text-align: center;color: red;">注：新建节目的开始与结束时间不能和该表中的时间重合</h6>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" >
                        <div id="scheduletime" class="form-group">
                            <div class="col-sm-10">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>开始时间</th>
                                        <th>结束时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${time}" var="t">
                                        <tr>
                                            <td>${t.formatBeginTime()}</td>
                                            <td>${t.formatEndTime()}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script>
    $(".form_datetime").datetimepicker({language:'zh_CN'});
    $("#judge").bootstrapSwitch({
        size:"mini",
        state:false,
        onColor:"info",
        offColor:"danger",
        onText:"Yes",
        offText:"No"
    });
    $("#judge").on('switchChange.bootstrapSwitch',function (event,state) {
        if (state){
            $("#fileUploadForm").show();
            //$('#fileUpload').name="files";
        }else {
            $("#fileUploadForm").hide();
            //$('#fileUpload').name="files2";
        }
    });
    $('#fileUpload').fileinput({
        language:'zh',
        showUpload:false,
        allowedFileExtensions:["jpg","png","jpeg",'txt','doc','mp4','mkv'],
        maxFileCount:5
    });
</script>
</html>
