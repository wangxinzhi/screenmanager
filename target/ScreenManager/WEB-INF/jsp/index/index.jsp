<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/2/16
  Time: 19:42
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
    <title>主页</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <%--引入bootstrap--%>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap.min.css"/>
    <%--引入JQuery bootstrap.js文件--%>
    <script src="<%=path%>/js/jquery-3.2.1.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:include page="top.jsp"/>
        <div class="container" id="content">
            <div class="span2">
                <div class="row">
                    <jsp:include page="menu.jsp"/>
                </div>
            </div>
            <div class="span10">
            </div>
        </div>
        <div class="container" id="footer">
            <div class="row">
                <div class="col-md-12"/>
            </div>
        </div>
</body>
</html>