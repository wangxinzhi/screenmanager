<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2017/12/3
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <div style="width: 500px;margin: 0px auto;text-align: center">
        <table align="center" border="1" cellspacing="0">
            <tr>
                <td>ID</td>
                <td>name</td>
            </tr>
            <c:forEach items="${cs}" var="c" varStatus="st">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                </tr>
            </c:forEach>
        </table>
        <div style="text-align: center">
            <a href="/demo/showPages?pageNo=0">首  页</a>
            <a href="/demo/showPages?pageNo=${page.getStart()-5}">上一页</a>
            <a href="/demo/showPages?pageNo=${page.getStart()+5}">下一页</a>
            <a href="/demo/showPages?pageNo=${page.getLast()}">末  页</a>
        </div>
    </div>
</body>
</html>
