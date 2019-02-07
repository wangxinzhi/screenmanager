<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/5/5
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="row">
            <h2 style="text-align: center;">节目时间安排表</h2>
            <h6 style="text-align: center;color: red;">注：新建节目的开始与结束时间不能和该表中的时间重合</h6>
        </div>
    </div>
    <div class="panel-body">
        <form class="form-horizontal" role="form" >
            <div id="feedbackForm" class="form-group">
                <div class="col-sm-10">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>开始时间</th>
                            <th>结束时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${time}" var="t">
                            <tr>
                                <td>${t.formatBeginTime()}</td>
                                <td>${t.formatEndTime()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
