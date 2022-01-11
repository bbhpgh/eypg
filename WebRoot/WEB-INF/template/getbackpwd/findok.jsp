<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>设置密码成功_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/register.css"/>
    <script type="text/javascript">$(function () {
        var c = $("#timeNum");
        var b = 10;
        var a = function () {
            if (b < 2) {
                location.replace("/login/index.html");
                return
            } else {
                b--;
                c.html(b)
            }
            setTimeout(a, 1000)
        };
        a()
    });</script>
</head>

<body>
<div class="login_layout">
    <div class="login_title">
        <h2>设置密码成功</h2>
    </div>
    <div class="login_Content">
        <div class="login_CEmail">
            <dl>
                <dt><img border="0" alt=""
                         src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/login_exp02.png"/></dt>
                <dd>
                    <h2 class="orange Fb">恭喜您密码修改成功！</h2>
                    <p class="login_fs14">请您重新 <a class="blue Fb"
                                                  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/login/index.html">登录</a>
                    </p>
                </dd>
            </dl>
        </div>
    </div>
</div>
</body>
</html>
