<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/2/19
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path=request.getContextPath();
%>
<!DOCTYPE>
<html>
<head>
    <title>节目列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <%--引入bootstrap--%>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap.min.css"/>
    <%--引入JQuery bootstrap.js文件--%>
    <script src="<%=path%>/js/jquery-3.2.1.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../index/top.jsp"/>
<div class="container" id="content">
    <div class="row">
        <jsp:include page="../index/menu.jsp"/>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 class="col-md-5">节目列表</h1>
                    </div>
                </div>
                <div class="panel panel-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>节目ID</th>
                            <th>节目名</th>
                            <th>节目内容</th>
                            <th>节目类型</th>
                            <!--th>开始时间</th-->
                            <!--th>结束时间</th-->
                            <th>发布人</th>
                            <th>显示屏幕ID</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${programCustoms}" var="p">
                            <tr>
                                <td>${p.program.ID}</td>
                                <td>${p.program.PName}</td>
                                <td>${p.program.PContent}</td>
                                <td>${p.PTypeStr}</td>
                                <!--td></td-->
                                <!--td></td-->
                                <td>${p.program.PSendPerson}</td>
                                <td>${p.program.getScreensList()}</td>
                                <td>${p.PJudgeStr}</td>
                                <td>
                                    <button class="btn btn-default btn-xs btn-info" onclick="location.href='<%=path%>/program/${p.program.ID}/edit'">修改</button>
                                    <button class="btn btn-default btn-xs btn-info" onclick="location.href='<%=path%>/program/${p.program.ID}/delete.do'">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="panel panel-footer">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"/>
    </div>
</div>
</body>
</html>
