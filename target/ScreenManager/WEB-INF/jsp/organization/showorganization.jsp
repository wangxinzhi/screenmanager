<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/5/4
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path=request.getContextPath();
%>
<html>
<head>
    <title>展示部门结构</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <%--引入bootstrap--%>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap-editable.css"/>
    <%--引入JQuery bootstrap.js文件--%>
    <script src="<%=path%>/js/jquery-3.2.1.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>
    <script src="<%=path%>/js/bootstrap-editable.js"></script>
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
                            <th>部门ID</th>
                            <th>名称</th>
                            <th>上级部门</th>
                            <th>路径</th>
                            <th>是否可用</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${organizations}" var="o">
                            <tr>
                                <td>${o.id}</td>
                                <td>${o.name}</td>
                                <td>${o.parent_id}</td>
                                <td>${o.parent_ids}</td>
                                <td>${o.available}</td>
                                <td>
                                    <button class="btn btn-default btn-xs btn-info" onclick="location.href='<%=path%>/organization/${o.id}/edit'">修改</button>
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
