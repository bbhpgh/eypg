<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>
        会员登录_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/comm.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/logincart.css"/>
    <script language="javascript" type="text/javascript"
            src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery-1.4.4-min.js"></script>
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
<div class="login_register">
    <div class="shop_login" id="divLogin">
        <h3>已经是<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>用户,请登录:</h3>
        <ul id="LoginForm">
            <li><span>
                    <p>
                        账号：</p>
                    <input id="username" type="text" value="" name="num" class="num"/></span>
                <div id="name_ts">
                    请输入正确的手机号/邮箱
                </div>
            </li>
            <li><span>
                    <p>
                        密码：</p>
                    <input id="pwd" type="password" class="password" name="password"/></span>
                <div id="pwd_ts">请填写长度为6-16长度的字符密码</div>
            </li>
            <li class="pad">
                <input id="btnSubmitLogin" type="button" name="login_init" value="登录" class="login_init"/>
                <p id="loginSubmitStatus" style="display:none;"><span>loding</span>登录中,请稍后...</p>
                <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/getbackpwd/index.html"
                   target="_blank">忘记密码？</a>
            </li>
        </ul>
    </div>
    <div class="registration">
        <div class="registration_txt">
            还不是<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>用户? <br/>
            <%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>，享受购物的<br/>乐趣！<br/>
            <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/register/index.html"
               target="_blank"><span class="regHrefStyle">立即注册>></span></a><br/><br/>
            <span style="padding: 5px 0 0 5px;" id="qqLoginBtn"></span>
        </div>
    </div>
</div>
<script type="text/javascript">
    var $img = "<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>";
    var $skin = "<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>";
    var $www = "<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>";
    var $domain = "<%=ApplicationListenerImpl.sysConfigureJson.getDomain()%>";
</script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/shoplogin.js"></script>
</body>
</html>
