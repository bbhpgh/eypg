<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>设置新密码_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/getpassword.css"/>
</head>

<body>
<input id="hidUserName" type="hidden" value="epgwyc@126.com" name="hidUserName"/>
<input id="hidKey" type="hidden"
       value="197ADFCCE66470FBD47F1C6ACD2219A169E48DA5010347222508701A0A87EE0BAD5A21F535E515F3" name="hidKey"/>
<input id="hidSN" type="hidden" value="0" name="hidSN"/>
<div class="install_password">
    <h2>
        <span>修改密码</span></h2>
    <div id="MemberForm" class="install_password_box">
        <ul>
            <li class=""><span>设置新密码：</span><input type="password" maxlength="20" class="password_text" value=""
                                                   id="password"/>
                <div id="lpassword">密码由6-16位字符，可由英文、数字及“_”组成</div>
                <p>
                    <span id="low">低</span><span id="middle">中</span><span id="height">高</span></p>
            </li>
            <li class=""><span>确认新密码：</span><input type="password" maxlength="20" class="password_text"
                                                   id="passwordAgain"/>
                <div id="lpasswordAgain">重复您刚刚输入的密码</div>
            </li>
            <li id="registerSubmit" class="next">
                <input type="button" value="确认修改" id="btnSubmitRegister"/></li>
            <li style=" display:none" id="registerSubmitStatus">
                正在设置新密码，请稍后...
            </li>
        </ul>
    </div>
</div>

<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/setnewpwd.js"></script>
</body>
</html>
