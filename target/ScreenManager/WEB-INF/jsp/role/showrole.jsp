<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/2/17
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path=request.getContextPath();
%>
<html>
<head>
    <title>展示角色列表</title>
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
                        <h1 class="col-md-5">角色列表</h1>
                    </div>
                </div>
                <div class="panel panel-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>角色ID</th>
                            <th>角色</th>
                            <th>描述</th>
                            <th>拥有资源</th>
                            <th>是否可用</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${roles}" var="r">
                            <tr>
                                <td>${r.id}</td>
                                <td>${r.role}</td>
                                <td>${r.description}</td>
                                <td>${r.resource_ids}</td>
                                <td>${r.available}</td>
                                <td>
                                    <button class="btn btn-default btn-xs btn-info" onclick="location.href='<%=path%>/role/${r.id}/edit'">修改</button>
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
