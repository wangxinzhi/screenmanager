<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/2/21
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<div>
    <video id="myvideo" class="video-js" controls preload="auto" width="740" height="400" data-setup="{}">
        <%--<c:forEach items="${video}" var="v">
            <source src="<%=basePath%>${v}">
        </c:forEach>--%>
        <c:forEach items="${video}" var="v">
            <source src="<%=path%>/${v}" type="video/mp4">
        </c:forEach>
        <p class="vjs-no-js"> To view this video please enable JavaScript, and consider upgrading to a web browser that <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a> </p>
    </video>
    <script src="<%=path%>js/videojs/video.min.js"></script>
    <script src="<%=path%>js/videojs/zh-CN.js"></script>
    <script type="text/javascript">
        var myPlayer = videojs('myvideo');
        myPlayer.ready(function(){
            this.play();
        });
    </script>
</div>



