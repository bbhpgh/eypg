<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="template_footer"/>
    <title>会员登录_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/login.css"/>
    <%
        if (ApplicationListenerImpl.sysConfigureJson.getQqAppStatus() == 0) {
    %>
    <script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js"
            data-appid="<%=ApplicationListenerImpl.sysConfigureJson.getQqAppId() %>"
            data-redirecturi="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl() %>/qc_callback.html"
            charset="utf-8"></script>
    <%
        }
    %>
</head>

<body>
<!--login 开始-->
<div class="login">
    <div class="login_top">
        <h1><a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>" class="logo_1ypg_img"
               title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        </a></h1>
        <p><a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>" class="back_home">返回首页</a><a
                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/index.html" target="_blank"
                class="help">帮助中心</a></p>
    </div>
    <div class="login_bg">
        <div id="loadingPicBlock" class="login_banner">
            <a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">
                <img width="542" height="360" alt="Hold住全场，仅需1元"
                     src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/loginImg2.jpg"/></a>
        </div>
        <div class="login_box" id="LoginForm">
            <h3>用户登录</h3>
            <ul>
                <li class="click"><span>账号：</span><input id="username" type="text" name="username" class="text_name"
                                                         tabindex="1"/></li>
                <li class="ts" id="name_ts">请输入正确的手机号/邮箱</li>
                <li><span>密码：</span><input id="pwd" type="password" class="text_password" tabindex="2"/><span
                        class="fog"><a id="hylinkGetPassPage" tabindex="5"
                                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/getbackpwd/index.html">忘记密码？</a></span>
                </li>
                <li class="ts" id="pwd_ts">请填写长度为6-16长度的字符密码</li>
                <li class="end wait" id="loginText">
                    <input type="hidden" id="forward" value="${forward }"/>
                    <input id="btnSubmitLogin" type="button" value="登录" class="login_init" tabindex="3"/>
                    <p id="loginSubmitStatus" style="display:none;"><span>loding</span>登录中,请稍后...</p>
                    <span style="padding: 5px 0 0 5px;" id="qqLoginBtn"></span>
                </li>
            </ul>
            <p>
                <br/>
                还不是<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>用户？<br/>
                开心<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                ，惊喜无限，就在<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>，马上注册吧！</p>
            <h4>
                <a id="hylinkRegisterPage" tabindex="4" class="reg"
                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/register/index.html">立即注册</a></h4>
        </div>
    </div>
</div>
<!--login 结束-->

<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/login.js"></script>
</body>
</html>
