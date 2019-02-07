<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/2/18
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path=request.getContextPath();
%>
<html>
<head>
    <title>创建新的角色信息</title>
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
                        <h1 style="text-align: center;">添加角色</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="<%=path%>/role/create.do" id="createform" method="post">
                        <div class="form-group">
                            <label for="role" class="col-sm-2 control-label">角色名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="role" name="role" placeholder="请输入角色名(英文)">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-sm-2 control-label">描述</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="description" name="description" placeholder="请输入描述(中文)">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="resource_ids" class="col-sm-2 control-label">拥有资源</label>
                            <div class="col-sm-10">
                                <select class="selectpicker show-tick form-control" multiple data-live-search="false" id="resource_ids" name="resource_ids">
                                    <c:forEach items="${resources}" var="r">
                                        <option value="${r.id}">${r.name}</option>
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
