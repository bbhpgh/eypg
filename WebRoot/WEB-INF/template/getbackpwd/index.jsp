<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>忘记密码_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/register.css"/>
</head>

<body>
<div class="login_layout">
    <div class="login_title">
        <h2>密码忘了？不要着急，通过以下方式即可帮您顺利的找回来！</h2>
    </div>
    <div class="login_Content">
        <div class="login_CMobile_Complete login_CEmailPal">
            <p>您可以通过注册时所填写的邮箱地址找回密码，请在下方输入您的帐号信息。</p>
            <dl>
                <dt>邮箱地址：</dt>
                <dd><input type="text" value="" class="login_CMobile_CodeW" name="txtUserAccount" id="userAccount"/>
                </dd>
                <dd></dd>
            </dl>
            <dl>
                <dt>验证码：</dt>
                <dd><input type="text" style="width:60px;" maxlength="6" value="" class="login_input_text"
                           name="txtRegSN" id="regSN"/>
                    <span><img alt="看不清？换一张" id="showVerifySN"
                               src="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/getbackpwd/getRandomCode.html?rnd=<%=new Date().getTime() %>"/><em>点击换一张</em></span>
                </dd>
                <dd></dd>
            </dl>
            <a class="login_Email_but" href="javascript:;" id="btnSubmitAccount">提交</a>
        </div>
        <div class="login_Explain">
            <p>1.您若忘记注册时所用的邮箱建议您重新注册账号， <a class="blue Fb"
                                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/register/index.html">立即注册</a>
            </p>
            <p>2.若有任何疑问或需要帮助请您进入帮助中心，也可以点击在线客服进行咨询。</p>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/getbackpwd.js"></script>
</body>
</html>
