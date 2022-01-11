<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="template_footer"/>
    <title>出错了啦_1元拍购</title>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            font: 12px/1.6 Arial, Sans-serif;
        }

        a {
            color: #04c;
            text-decoration: none;
        }

        a:hover {
            color: #F60;
            text-decoration: underline;
        }

        h1 {
            position: relative;
            z-index: 2;
            width: 540px;
            height: 0;
            margin: 110px auto 15px;
            padding: 230px 0 0;
            overflow: hidden;
            background-image: url(/Images/error<%Random random = new Random();int i = random.nextInt(7);out.print(i);%>.jpg);
            background-repeat: no-repeat;
        }

        h2 {
            position: absolute;
            top: 15px;
            left: 233px;
            margin: 0;
            font-size: 0;
            text-indent: -999px;
            -moz-user-select: none;
            -webkit-user-select: none;
            user-select: none;
            cursor: default;
            height: 90px;
        }

        h2 em {
            display: block;
            font: italic bold 200px/120px "Times New Roman", Times, Serif;
            text-indent: 0;
            color: rgba(200, 200, 200, 0.9);
        }

        .link a {
            margin-right: 1em;
        }

        .link, .texts {
            width: 540px;
            margin: 0 auto 15px;
            color: #505050;
        }

        .texts {
            line-height: 2;
        }

        .texts dd {
            margin: 0;
            padding: 0 0 0 15px;
        }

        .texts ul {
            margin: 0;
            padding: 0;
        }

        .portal {
            color: #505050;
            text-align: center;
            white-space: nowrap;
            word-spacing: 0.45em;
        }

        .portal a:link, .portal a:visited {
            color: #505050;
            word-spacing: 0;
        }

        .portal a:hover, .portal a:active {
            color: #007ab7;
        }

        .portal span {
            display: inline-block;
            height: 38px;
            line-height: 35px;
            background: url(img/portal.png) repeat-x;
        }

        .portal span span {
            padding: 0 0 0 20px;
            background: url(img/portal.png) no-repeat 0 -40px;
        }

        .portal span span span {
            padding: 0 20px 0 0;
            background-position: 100% -80px;
        }

        .STYLE1 {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 65px;
            letter-spacing: -5px;
        }

        .STYLE2 {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 25px;
        }

    </style>
    <!--[if lte IE 8]>
    <style type="text/css">
    h2 em{color:#e4ebf8;}
    </style>
    <![endif]-->
</head>
<body>
<h1></h1>
<h2><em><span class="STYLE1">404 Error </span><span class="STYLE2">: 您所查找的页面不存在,可能已被删除或您输错了网址!</span></em></h2>
<p class="link">
    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">&#9666;返回首页</a>
</p>
<dl class="texts">
    <dt>我们正在联系火星总部查找您所需要的页面.请返回等待信息...</dt>
    <br/><br/><br/>
</dl>
</body>
</html>