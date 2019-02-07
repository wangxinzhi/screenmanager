<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/5/4
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path=request.getContextPath();
%>
<html>
<head>
    <title>编辑部门信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap-select.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="<%=path%>/js/jquery-3.2.1.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>
    <script src="<%=path%>/js/bootstrap-select.min.js"></script>
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="../index/top.jsp"></jsp:include>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="../index/menu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">编辑部门信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="<%=path%>/organization/${organization.id}/edit.do" id="editfrom" method="post">
                        <div class="form-group">
                            <label for="id" class="col-sm-2 control-label">ID</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="number" class="form-control" id="id" name="id" value="${organization.id}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">部门名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="name" name="name" value="${organization.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="parent_id" class="col-sm-2 control-label">上级部门</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="parent_id" name="parent_id" value="${organization.parent_id}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="parent_id" class="col-sm-2 control-label">路径</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="parent_ids" name="parent_ids" value="${organization.parent_ids}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="available" class="col-sm-2 control-label">是否锁定</label>
                            <div class="col-sm-10">
                                <select class="selectpicker show-tick form-control" data-live-search="false" id="available" name="available">
                                    <option value="0">锁定</option>
                                    <option value="1" selected="selected">可用</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <button class="btn btn-default" type="reset">重置</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
</html>
