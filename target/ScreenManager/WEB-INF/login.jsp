<%@page language="java" contentType="text/html; charset=utf-8" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 登陆界面 -->
<!DOCTYPE html>
<html>
<head>
    <title>郑州中原工学院LED大屏管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%-- 引入bootstrap --%>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap.min.css">
    <%-- 引入JQuery bootstrap.js --%>
    <script type="text/javascript" src="<%=path%>/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
    <style type="text/css">
        body{
            background: url("<%=path%>/images/a.jpg")repeat;
        }
        #login-box {
            padding: 35px;
            border-radius:15px;
            background: #56666B;
            color: #fff;
        }

    </style>
</head>
<body>
    <div class="container" id="top">
        <div class="row" style="margin-top: 280px; ">
            <div class="col-md-4"></div>
            <div class="col-md-4" id="login-box">
                <form class="form-horizontal" role="form" action="<%=path%>/login" id="from1" method="post">
                    <div class="form-group">
                        <label for="userID" class="col-sm-3 control-label">用户id</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="userID" placeholder="请输入名字" name="username">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-3 control-label">密码</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="rememberMe" class="col-sm-3 control-label">自动登陆</label>
                        <div class="col-sm-9">
                            <input type="checkbox" id="rememberMe" name="rememberMe">
                        </div>
                    </div>
                    <div class="form-group pull-right" style="margin-right: 15px;">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default btn-info">登录</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</body>
</html>
