<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2017/11/28
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>UploadDemo</title>
    <!--对移动设备更好的支持，确保适当的绘制和触屏缩放-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap/bootstrap-responsive.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap/fileinput.min.css">
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/bootstrap-wysiwyg.js"></script>
    <script src="/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="/js/fileinput.min.js"></script>
    <script src="/js/zh.js"></script>
</head>
<body>
    <form action="/uploadImage" method="post" enctype="multipart/form-data">
        <input id="imageUpload" type="file" name="images" accept="image/*">
        <input type="submit" value="上传">
    </form>
</body>
<script type="text/javascript">
    $('#imageUpload').fileinput();
</script>
</html>
