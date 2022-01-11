<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>设置新密码_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/register.css"/>
</head>

<body>
<input name="hidUserName" type="hidden" id="hidUserName" value="${mail }"/>
<div class="login_layout">
    <div class="login_title">
        <h2>设置密码</h2>
    </div>
    <div class="login_ConInput login_Ptxtf14">
        <p class="Ptxt_F14">请重新设置您的登录密码，您的帐号是：<span class="orange Fb">${mail}</span></p>
        <dl>
            <dt>密码：</dt>
            <dd><input type="password" value="" class="login_input_text" name="txtPassword" id="password"/></dd>
            <dd></dd>
        </dl>
        <dl>
            <dt>确认密码：</dt>
            <dd><input type="password" value="" class="login_input_text" name="txtPasswordAgain" id="passwordAgain"/>
            </dd>
            <dd></dd>
        </dl>
        <div class="login_Membut">
            <a class="login_Email_but" href="javascript:;" id="btnSubmitPassword">确认修改</a>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/findreset.js"></script>
</body>
</html>
