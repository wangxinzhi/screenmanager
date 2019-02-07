<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/2/17
  Time: 9:11
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
    <title>用户列表</title>
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
                        <h1 class="col-md-5">个人信息</h1>
                        <form class="bs-example bs-example-form col-md-5" role="form" style="margin:20px 0 10px 0;" action="" id="form1" method="post">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="请输入用户ID" name="findByID">
                                <span class="input-group-addon btn" id="sub">搜索</span>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="panel panel-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>用户ID</th>
                            <th>所属部门</th>
                            <th>用户名</th>
                            <th>密码</th>
                            <th>角色集</th>
                            <th>是否锁定</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="u">
                            <tr>
                                <td>${u.userLogin.userId}</td>
                                <td>${u.organization.name}</td>
                                <td>${u.userLogin.username}</td>
                                <td>${u.userLogin.password}</td>
                                <td>${u.userLogin.role_ids}</td>
                                <td>${u.userLogin.locked}</td>
                                <td>
                                    <button class="btn btn-default btn-xs btn-info" onclick="location.href='<%=path%>/user/${u.userLogin.username}/edit'">修改</button>
                                    <button class="btn btn-default btn-xs btn-info" onclick="location.href='<%=path%>/user/${u.userLogin.username}/delete.do'">删除</button>
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
