<%@ page import="org.apache.commons.lang.RandomStringUtils" %><%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/2/20
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //String path=request.getContextPath();
    String path1=request.getServletContext().getRealPath("zutimg")+"/"+1+"/"+ RandomStringUtils.randomAlphabetic(5);
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
    String path=request.getContextPath();
%>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap-select.min.css">
    <!--link rel="stylesheet" type="text/css" href="/css/flat/flat-ui.min.css"-->
    <!-- 引入JQuery  bootstrap.js-->
    <script src="<%=path%>/js/jquery-3.2.1.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>
    <script src="<%=path%>/js/bootstrap-select.min.js"></script>
    <!--script src="/js/flat/flat-ui.js"></script-->
    <script>videojs.options.flash.swf="<%=path%>/js/flat/video-js.swf"</script>
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
                        <h1 style="text-align: center;">审核节目信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="<%=path%>/program/check.do" id="checkform" method="post">
                        <div class="form-group">
                            <label for="programId" class="col-sm-2 control-label">节目ID</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="number" class="form-control" id="programId" name="pid" value="${programbycheck.ID}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PName" class="col-sm-2 control-label">节目名</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="text" class="form-control" id="PName" name="PName" value="${programbycheck.PName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PContent" class="col-sm-2 control-label">节目内容</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="text" class="form-control" id="PContent" name="PContent" value="${programbycheck.PContent}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PType" class="col-sm-2 control-label">节目类型</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="text" class="form-control" id="PType" name="PType" value="${programbycheck.PType}">
                            </div>
                        </div>
                        <div class="form-group date date-time">
                            <label for="PBeginTime" class="col-sm-2 control-label">节目开始时间</label>
                            <div class="input-group">
                                <div class="input-group col-sm-10 form_datetime">
                                    <input readonly="readonly" type="text" class="form-control" id="PBeginTime" name="PBeginTime" value="${programbycheck.formatBeginTime()}">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar pull-right"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group date date-time">
                            <label for="PEndTime" class="col-sm-2 control-label">节目结束时间</label>
                            <div class="input-group">
                                <div class="input-group col-sm-10 form_datetime">
                                    <input readonly="readonly" type="text" class="form-control" id="PEndTime" name="PEndTime" value="${programbycheck.formatEndTime()}">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar pull-right"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PSendPerson" class="col-sm-2 control-label">发布人</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="text" class="form-control" id="PSendPerson" name="PSendPerson" value="${programbycheck.PSendPerson}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PSendPerson" class="col-sm-2 control-label">显示节目大屏</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="text" class="form-control" id="ScreensList" name="ScreensList" value="${locations}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否通过</label>
                            <div class="col-sm-10">
                                <select class="selectpicker show-tick form-control" name="pjudge">
                                    <option value="1">审核通过</option>
                                    <option value="2">审核未通过</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">反馈</label>
                            <div class="col-sm-10">
                                <input name="feedback" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group" id="myPictures">
                            <label class="col-sm-2 control-label">图片集</label>
                            <div class="col-sm-10">
                                <jsp:include page="../index/carousel.jsp"/>
                            </div>
                        </div>
                        <div class="form-group" id="myVideo">
                            <label class="col-sm-2 control-label">视频集</label>
                            <div class="col-sm-10">
                                <jsp:include page="../index/video.jsp"/>
                            </div>
                        </div>
                        <div class="form-group" id="myWord">
                            <label class="col-sm-2 control-label">文档</label>
                            <div class="col-sm-10">
                                <jsp:include page="../index/word.jsp"/>
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h2 style="text-align: center;">节目时间安排表</h2>
                        <h6 style="text-align: center;color: red;">注：审核该节目时间是否与表中时间重合(重合则审核不通过)</h6>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" >
                        <div id="feedbackForm" class="form-group">
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
<script>
    window.onload=function () {
        var type=document.getElementById("PType");
        //var pictures=document.getElementById("myPictures");
        //var video=document.getElementById("myVideo");
        //var word=document.getElementById("myWord");
        if (type.value==0){
            //pictures.style.display="block";
            //video.style.display="none";
            //word.style.display="none";
            $("#myPictures").show();
            $("#myVideo").hide();
            $("#myWord").hide();
            console.log("value=0");
        }
        if (type.value==1){
            //video.style.display="block";
            //pictures.style.display="none";
            //word.style.display="none";
            $("#myPictures").hide();
            $("#myVideo").show();
            $("#myWord").hide();
            console.log("value=1");
        }
        if (type.value==2){
            //pictures.style.display="none";
            //video.style.display="none";
            //word.style.display="block";
            $("#myPictures").hide();
            $("#myVideo").hide();
            $("#myWord").show();
            console.log("value=2");
        }
    };
    $(".form_datetime").datetimepicker({language:'zh_CN'});
</script>
</body>
</html>
