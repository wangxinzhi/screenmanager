<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2018/2/13
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>404</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="Content-Security-Policy" content="default-src 'none'; base-uri 'self'; connect-src 'self'; form-action 'self'; img-src 'self' data:; script-src 'self'; style-src 'unsafe-inline'">
    <meta content="origin" name="referrer">
    <title>Page not found · ZUT · ScreenManagerSystem</title>
    <style type="text/css" media="screen">
        body {
            background-color: #f1f1f1;
            margin: 0;
        }
        body,
        input,
        button {
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        }
        .container { margin: 30px auto 40px auto; width: 800px; text-align: center; }

        a { color: #4183c4; text-decoration: none; font-weight: bold; }
        a:hover { text-decoration: underline; }

        h3 { color: #666; }
        ul { list-style: none; padding: 25px 0; }
        li {
            display: inline;
            margin: 10px 50px 10px 0px;
        }
        input[type=text],
        input[type=password] {
            font-size: 13px;
            min-height: 32px;
            margin: 0;
            padding: 7px 8px;
            outline: none;
            color: #333;
            background-color: #fff;
            background-repeat: no-repeat;
            background-position: right center;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-shadow: inset 0 1px 2px rgba(0,0,0,0.075);
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            -webkit-transition: all 0.15s ease-in;
            transition: all 0.15s ease-in;
            vertical-align: middle;
        }
        .btn {
            position: relative;
            display: inline-block;
            padding: 6px 12px;
            font-size: 13px;
            font-weight: bold;
            line-height: 20px;
            color: #333;
            white-space: nowrap;
            vertical-align: middle;
            cursor: pointer;
            background-color: #EEE;
            background-image: -webkit-linear-gradient(#FCFCFC, #EEE);
            background-image: linear-gradient(#FCFCFC, #EEE);
            background-repeat: repeat-x;
            border: 1px solid #d5d5d5;
            border-radius: 3px;
            user-select: none;
            -webkit-appearance: none;
        }

        .btn:focus,
        input[type=text]:focus,
        input[type=password]:focus {
            text-decoration: none;
            border-color: #51a7e8;
            outline: none;
            box-shadow: 0 0 5px rgba(81, 167, 232, 0.5);
        }

        .btn:hover,
        .btn:active {
            text-decoration: none;
            background-color: #ddd;
            background-image: -webkit-linear-gradient(#eee, #ddd);
            background-image: linear-gradient(#eee, #ddd);
            background-repeat: repeat-x;
            border-color: #ccc;
        }

        .btn:active {
            background-color: #dcdcdc;
            background-image: none;
            border-color: #b5b5b5;
            box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15);
        }

        #auth {
            position: absolute;
            top: 0;
            right: 0;
            z-index: 50;
            min-height: 32px;
            background-color: rgba(53,95,120,.4);
            padding: 7px 10px;
            border-bottom-right-radius: 10px;
            border-bottom-left-radius: 10px;
            box-shadow: 0 3px 0 rgba(0, 0, 0, 0.28);
            display: none;
        }
        #auth h1, #auth p, #auth label {
            display: none;
        }
        .auth-form-body {
            display: inline;
        }
        #auth input[type=text],
        #auth input[type=password] {
            float: left;
            width: 175px;
            margin-right: 9px;
            border: 0;
            background-color: #f5f5f5;
        }
        #auth input[type=text]:focus,
        #auth input[type=password]:focus {
            background-color: #fff;
            box-shadow: 0 0 5px rgba(255,255,255,.5);
        }
        #auth .btn {
            border: 0;
        }
        #auth .btn:focus {
            box-shadow: 0 0 5px rgba(255,255,255,.5);
        }
        label[for=search] {
            display: block;
            text-align: left;
        }
        #search label {
            font-weight: 200;
            padding: 5px 0;
        }
        #search input[type=text] {
            font-size: 18px;
            width: 705px;
        }
        #search .btn {
            padding: 10px;
            width: 90px;
        }
        .logo { display: inline-block; margin-top: 35px; }
        .logo-img-2x { display: none; }
        @media
        only screen and (-webkit-min-device-pixel-ratio: 2),
        only screen and (   min--moz-device-pixel-ratio: 2),
        only screen and (     -o-min-device-pixel-ratio: 2/1),
        only screen and (        min-device-pixel-ratio: 2),
        only screen and (                min-resolution: 192dpi),
        only screen and (                min-resolution: 2dppx) {
            .logo-img-1x { display: none; }
            .logo-img-2x { display: inline-block; }
        }
        #suggestions {
            margin-top: 35px;
            color: #ccc;
        }
        #suggestions a {
            color: #666666;
            font-weight: 200;
            font-size: 14px;
            margin: 0 10px;
        }

        #parallax_wrapper {
            position: relative;
            z-index: 0;
            -webkit-transition: all 0.25s ease-in;
            transition: all 0.25s ease-in;
        }
        #parallax_field {
            overflow: hidden;
            position: absolute;
            left: 0;
            top: 0;
            height: 370px;
            width: 100%;
        }
        #parallax_field #parallax_bg {
            position: absolute;
            top: -20px;
            left: -20px;
            width: 110%;
            height: 425px;
            z-index: 1;
        }
        #parallax_illustration {
            display: block;
            margin: 0 auto;
            width: 940px;
            height: 370px;
            position: relative;
            overflow: hidden;
            clear: both;
        }
        #parallax_illustration img {
            position: absolute;
        }
        #parallax_illustration #parallax_error_text {
            top: 72px;
            left: 72px;
            z-index: 10;
        }
        #parallax_illustration #parallax_octocat {
            top: 94px;
            left: 356px;
            z-index: 9;
        }
        #parallax_illustration #parallax_speeder {
            top: 150px;
            left: 432px;
            z-index: 8;
        }
        #parallax_illustration #parallax_octocatshadow {
            top: 297px;
            left: 371px;
            z-index: 7;
        }
        #parallax_illustration #parallax_speedershadow {
            top: 263px;
            left: 442px;
            z-index: 6;
        }
        #parallax_illustration #parallax_building_1 {
            top: 73px;
            left: 467px;
            z-index: 5;
        }
        #parallax_illustration #parallax_building_2 {
            top: 113px;
            left: 762px;
            z-index: 4;
        }
    </style>
</head>
<body>
<div id="parallax_field">
    <img alt="" class="js-plaxify" data-invert="true" data-xrange="0" data-yrange="20" id="parallax_bg" src="<%=path%>/images/desert.jpg" style="top: -20px; left: -20px; transform: translate3d(0px, -5.52189px, 0px);" width="940" height="415">
</div>
<div id="parallax_illustration">
    <img alt="404 “This is not the web page you are looking for”" class="js-plaxify" data-xrange="20" data-yrange="10" id="parallax_error_text" src="<%=path%>/images/words.png" style="top: 72px; left: 72px; transform: translate3d(-6.62638px, 2.76094px, 0px);" width="271" height="249">
    <img alt="" class="js-plaxify" data-xrange="10" data-yrange="10" id="parallax_octocat" src="<%=path%>/images/github.png" style="top: 94px; left: 356px; transform: translate3d(-3.31319px, 2.76094px, 0px);" width="188" height="230">

    <img alt="" class="js-plaxify" data-xrange="10" data-yrange="10" id="parallax_speeder" src="<%=path%>/images/ship.png" style="top: 150px; left: 432px; transform: translate3d(-3.31319px, 2.76094px, 0px);" width="440" height="156">

    <img alt="" class="js-plaxify" data-xrange="10" data-yrange="10" id="parallax_octocatshadow" src="<%=path%>/images/p-shadow.png" style="top: 297px; left: 371px; transform: translate3d(-3.31319px, 2.76094px, 0px);" width="166" height="49">

    <img alt="" class="js-plaxify" data-xrange="10" data-yrange="10" id="parallax_speedershadow" src="<%=path%>/images/ship-shadow.png" style="top: 263px; left: 442px; transform: translate3d(-3.31319px, 2.76094px, 0px);" width="430" height="75">

    <img alt="" class="js-plaxify" data-invert="true" data-xrange="50" data-yrange="20" id="parallax_building_1" src="<%=path%>/images/house.png" style="top: 73px; left: 467px; transform: translate3d(16.5659px, -5.52189px, 0px);" width="304" height="123">

    <img alt="" class="js-plaxify" data-invert="true" data-xrange="75" data-yrange="30" id="parallax_building_2" src="<%=path%>/images/house2.png" style="top: 113px; left: 762px; transform: translate3d(24.3489px, -8.28283px, 0px);" width="116" height="50">
</div>
</body>
</html>
