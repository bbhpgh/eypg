<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

        <div class="login_CMobile_Complete hidden" id="divError">
            <p><span class="orange Fb">请稍候，系统正在发送邮件...</span></p>
        </div>
        <div class="login_CMobile_Complete" id="divNormal">
            <p><%=ApplicationListenerImpl.sysConfigureJson.getSaitName() %>已向您的 <span class="orange">${mail}</span>
                邮箱发送了一封验证邮件，请前往收信，完成验证！</p>
            <a target="_blank" href="http://mail.${fn:substring(mail,fn:indexOf(mail,'@')+1,fn:length(mail))}"
               class="login_Email_but" id="hylinkMails">登录邮箱完成验证</a>
            <input type="hidden" value="${mail }" id="hidRegEmail" name="hidRegEmail"/>
            <input type="hidden" value="${rnd }" id="hidRegSN" name="hidRegSN"/>
        </div>
        <div class="login_Explain">
            <h2>没收到验证邮件？</h2>
            <p>1.查看邮箱的垃圾箱或广告箱，邮件有可能被误认为垃圾邮件。</p>
            <p>2.如果在10分钟后仍未收到验证邮件，请点击 <a class="login_SendoutbutClick" href="javascript:;" id="retrySend">重新发送</a></p>
        </div>


    </div>
</div>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/findemailcheck.js"></script>
</body>
</html>
