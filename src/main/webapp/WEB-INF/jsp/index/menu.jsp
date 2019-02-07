<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2017/12/1
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path=request.getContextPath();
%>
<%--侧边导航栏--%>
<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="<%=path%>/user/${principal}/showdetails">个人信息<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li>
            <div class="panel-default">
                <div class="panel-heading" role="tab" id="collapseListGroup2">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" href="#collapseExample2" aria-expanded="false" aria-controls="collapseExample">用户管理</a>
                    </h4>
                </div>
                <div class="panel-collapse collapse" id="collapseExample2" role="tabpanel" aria-labelledby="collapseListGroup2">
                    <ul class="list-group">
                        <shiro:hasPermission name="user:view">
                            <li class="list-group-item"><a href="<%=path%>/user/${principal}/showuser">查看系统用户</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="user:create">
                            <li class="list-group-item"><a href="<%=path%>/user/create">添加用户<span class="glyphicon glyphicon-adjust pull-right"/></a></li>
                        </shiro:hasPermission>
                    </ul>
                </div>
            </div>
        </li>
        <li>
            <div class="panel-default">
                <div class="panel-heading" role="tab" id="collapseListGroup3">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" href="#collapseExample3" aria-expanded="false" aria-controls="collapseExample">角色管理</a>
                    </h4>
                </div>
                <div class="panel-collapse collapse" id="collapseExample3" role="tabpanel" aria-labelledby="collapseListGroup3">
                    <ul class="list-group">
                        <shiro:hasPermission name="role:view">
                            <li class="list-group-item"><a href="<%=path%>/role/show">查看角色列表</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="role:create">
                            <li class="list-group-item"><a href="<%=path%>/role/create">添加角色</a></li>
                        </shiro:hasPermission>
                    </ul>
                </div>
            </div>
        </li>
        <li>
            <div class="panel-default">
                <div class="panel-heading" role="tab" id="collapseListGroup4">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" href="#collapseExample4" aria-expanded="false" aria-controls="collapseExample">部门管理</a>
                    </h4>
                </div>
                <div class="panel-collapse collapse" id="collapseExample4" role="tabpanel" aria-labelledby="collapseListGroup4">
                    <ul class="list-group">
                        <shiro:hasPermission name="organization:view">
                            <li class="list-group-item"><a href="<%=path%>/organization/show">查看部门列表</a></li>
                        </shiro:hasPermission>
                    </ul>
                </div>
            </div>
        </li>
        <li>
            <div class="panel-default">
                <div class="panel-heading" role="tab" id="collapseListGroup5">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" href="#collapseExample5" aria-expanded="false" aria-controls="collapseExample">资源管理</a>
                    </h4>
                </div>
                <div class="panel-collapse collapse" id="collapseExample5" role="tabpanel" aria-labelledby="collapseListGroup5">
                    <ul class="list-group">
                        <shiro:hasPermission name="resource:view">
                            <li class="list-group-item">查看资源</li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="resource:create">
                            <li class="list-group-item">添加资源</li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="resource:update">
                            <li class="list-group-item">编辑/删除资源</li>
                        </shiro:hasPermission>
                    </ul>
                </div>
            </div>
        </li>
        <li>
            <div class="panel-default">
                <div class="panel-heading" role="tab" id="collapseListGroup6">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" href="#collapseExample6" aria-expanded="false" aria-controls="collapseExample2">节目管理</a>
                    </h4>
                </div>
                <div class="panel-collapse collapse" id="collapseExample6" role="tabpanel" aria-labelledby="collapseListGroup6">
                    <ul class="list-group">
                        <shiro:hasPermission name="program:view">
                            <li class="list-group-item"><a href="<%=path%>/program/${principal}/show">查看节目列表</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="program:create">
                            <li class="list-group-item"><a href="<%=path%>/program/${principal}/create">添加节目</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="program:check">
                            <li class="list-group-item"><a href="<%=path%>/program/${principal}/checklist">待审核节目</a></li>
                        </shiro:hasPermission>
                    </ul>
                </div>
            </div>
        </li>
        <li><a href="<%=path%>/logout">退出系统</a></li>
        <li class="disabled"><a href="##">Responsive</a></li>
    </ul>
</div>
