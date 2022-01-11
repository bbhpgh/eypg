<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>1元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>_管理中心</title>
    <link href="/admin_css/bootstrap.css" rel="stylesheet"/>
    <link href="/admin_css/style.css" rel="stylesheet"/>
</head>

<body class="texture">
<!-- 	<div align="center">-->
<!-- 	<form action="/admin_back/login.action" method="post">-->
<!--	 	<div style="background: url(/Images/admin_bg.png) no-repeat;width: 500px;height: 300px;">-->
<!--	 	</div>-->
<!--	 		<p>-->
<!--				<label>用户名：</label>-->
<!--				<input type="text" name="userName" size="20" class="login_input"></input>-->
<!--			</p>-->
<!--			<p>-->
<!--				<label>密码：</label>-->
<!--				<input type="password" name="pwd" size="20" class="login_input"></input>-->
<!--			</p>-->
<!--			<div class="login_bar">-->
<!--				<input class="sub" type="submit" value="登录"></input>-->
<!--			</div>-->
<!-- 	</form>-->
<!-- 	</div>-->
<div id="cl-wrapper" class="login-container">

    <div class="middle-login">
        <div class="block-flat">
            <div class="header">
                <h3 class="text-center"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>管理中心</h3>
            </div>
            <div>
                <form style="margin-bottom: 0px !important;" class="form-horizontal" action="/admin_back/login.action"
                      method="post">
                    <div class="content">
                        <h4 class="title"></h4>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user">用户名</i></span>
                                    <input type="text" placeholder="Username" id="username" name="userName"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><i
                                            class="fa fa-lock">密&nbsp;&nbsp;&nbsp;码</i></span>
                                    <input type="password" placeholder="Password" name="pwd" id="password"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="foot">
                        <button class="btn btn-primary" data-dismiss="modal" type="submit">登入</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="text-center out-links"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">&copy;
            2014 <%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        </a></div>
    </div>
</div>
<script type="text/javascript" src="/admin_js/jquery.js"></script>
<script type="text/javascript" src="/admin_js/general.js"></script>
</body>
</html>
