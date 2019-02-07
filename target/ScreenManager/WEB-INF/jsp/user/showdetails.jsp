<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/2/17
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                            <h1 style="text-align: center;">用户信息</h1>
                        </div>
                    </div>
                    <div class="panel panel-body">
                        <form class="form-horizontal" role="form" action="##" id="editfrom">
                            <div class="form-group">
                                <label for="userid" class="col-sm-2 control-label">用户ID</label>
                                <div class="col-sm-10">
                                    <input readonly="readonly" type="number" class="form-control" id="userid" value="${user.userLogin.userId}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="organizationid" class="col-sm-2 control-label">所属部门</label>
                                <div class="col-sm-10">
                                    <input readonly="readonly" type="text" class="form-control" id="organizationid" value="${user.organization.name}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">用户名</label>
                                <div class="col-sm-10">
                                    <input readonly="readonly" type="text" class="form-control" id="username" value="${user.userLogin.username}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="roleids" class="col-sm-2 control-label">角色集</label>
                                <div class="col-sm-10">
                                    <input readonly="readonly" type="text" class="form-control" id="roleids" value="${user.rolesname()}" >
                                </div>
                            </div>
                        </form>
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
