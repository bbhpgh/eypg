<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>会员注册_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/register.css"/>
</head>

<body>
<div class="login_layout">
    <div class="login_title">
        <h2>新用户注册</h2>
        <ul class="login_process">
            <li><b>1</b>填写注册信息</li>
            <li class="login_arrow"></li>
            <li class="login_processCur"><b>2</b>验证邮箱</li>
            <li class="login_arrow"></li>
            <li><b>3</b>完成注册</li>
        </ul>
        <span>已经是会员？<a
                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/login/index.html?forward=${forward }"
                class="blue Fb" id="hylinkLoginPage">登录</a></span>
    </div>
    <div class="login_Content">
        <div class="login_CEmail hidden" id="divVerify" style="display: none;">
            <dl>
                <dt><img border="0" alt=""
                         src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/login_exp03.png"/></dt>
                <dd>
                    <h2 class="orange Fb">您的帐号还未验证激活！</h2>
                    <p class="login_fs14"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>已向您的 <span
                            class="orange Fb">${str }</span> 邮箱发送了一封验证邮件，请前往收信，完成验证！</p>
                    <a target="_blank" href="http://mail.${fn:substring(str,fn:indexOf(str,'@')+1,fn:length(str))}/"
                       class="login_Email_but" id="hylinkMailsA">登录邮箱完成验证</a>
                </dd>
            </dl>
        </div>
        <div class="login_CMobile_Complete hidden" id="divError" style="display: block;"><span class="orange Fb">系统正在发送验证邮件，请稍后……</span>
        </div>
        <div class="login_CMobile_Complete hidden" id="divNormal" style="">
            <p><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>已向您的 <span class="orange">${str }</span>
                邮箱发送了一封验证邮件，请前往收信，完成验证！</p>
            <a target="_blank" href="http://mail.${fn:substring(str,fn:indexOf(str,'@')+1,fn:length(str))}/"
               class="login_Email_but" id="hylinkMailsB">登录邮箱完成验证</a>
            <input type="hidden" value="${isVerify }" id="hidIsVerify" name="hidIsVerify"/>
            <input type="hidden" value="${str }" id="hidRegEmail" name="hidRegEmail"/>
        </div>
        <div class="login_Explain">
            <h2>没收到验证邮件？</h2>
            <p>1.查看邮箱的垃圾箱或广告箱，邮件有可能被误认为垃圾邮件。</p>
            <p>2.如果在10分钟后仍未收到验证邮件，请点击 <a class="login_Sendoutbut" href="javascript:;" id="retrySend">重新发送</a></p>
            <p>3.如果再次发送验证邮件您仍未收到，请更换邮箱 <a
                    href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/register/index.html?forward=${forward }"
                    class="blue Fb" id="hylinkRegisterPageA">重新注册</a></p>
        </div>


    </div>
</div>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/emailcheck.js"></script>
</body>
</html>
