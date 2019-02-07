<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2017/11/26
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String path=request.getContextPath();
%>
<html>
<head>
    <title>成功页面</title>
</head>
<body>
    <h2>操作成功</h2>
    <br/>
    <h2>用户名=<shiro:principal/></h2>
    <br/>
    <h2><a href="<%=path%>/logout">退出</a></h2>
</body>
</html>