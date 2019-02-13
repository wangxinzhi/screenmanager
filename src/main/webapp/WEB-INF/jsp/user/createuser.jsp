<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/2/17
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path=request.getContextPath();
%>
<!DOCTYPE>
<html>
<head>
    <title>添加新的用户</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <%--引入bootstrap--%>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap-select.min.css"/>
    <%--引入JQuery bootstrap.js文件--%>
    <script src="<%=path%>/js/jquery-3.2.1.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>
    <script src="<%=path%>/js/bootstrap-select.min.js"></script>
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
                        <h1 style="text-align: center;">添加用户</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="<%=path%>/user/create.do" id="createform" method="post">
                        <div class="form-group">
                            <label for="username" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="password" name="password" placeholder="请输入新密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="organization_id" class="col-sm-2 control-label">所属部门</label>
                            <div class="col-sm-10">
                                <select class="show-tick form-control" id="organization_id" name="organization_id">
                                    <c:forEach items="${organizations}" var="o">
                                        <option value="${o.id}">${o.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="role_ids" class="col-sm-2 control-label">用户角色(权限)</label>
                            <div class="col-sm-10">
                                <select class="selectpicker show-tick form-control" multiple data-live-search="false" id="role_ids" name="role_ids">
                                    <c:forEach items="${roles}" var="r">
                                        <option value="${r.id}">${r.description}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <button class="btn btn-default" type="reset">重置</button>
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
