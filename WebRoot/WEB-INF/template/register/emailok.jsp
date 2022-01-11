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
            <li><b>2</b>验证邮箱</li>
            <li class="login_arrow"></li>
            <li class="login_processCur"><b>3</b>
                <c:choose>
                    <c:when test="${isVerify==0}">
                        完成注册
                    </c:when>
                    <c:otherwise>
                        验证失败
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
    <div class="login_Content">
        <div class="login_CEmail login_Complete">
            <c:choose>
                <c:when test="${isVerify==0}">
                    <dl>
                        <dt><img border="0" alt=""
                                 src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/login_exp02.png"/>
                        </dt>
                        <dd>
                            <h2 class="orange Fb">恭喜您，已成功通过邮箱验证！</h2>
                            <p class="login_fs14">您现在可以通过您注册的邮箱 <span class="orange Fb">${mail }</span> 进行登录和收取获得商品的信息
                            </p>
                            <p class="login_fs14">您还可以进行以下操作：</p>
                            <p><b>1、</b><a class="blue Fb login_fs14"
                                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/MemberModify.html">完善资料</a>
                                <!--					        <em>( 完善资料即可立即获得<span class="orange Fb">80</span>福分哦？<a class="blue" target="_blank" href="http://help.1ypg.com/">福分可以做什么？</a>)</em></p>-->
                            <p><b>2、</b><a class="blue Fb login_fs14"
                                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">跳过，直接去<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                                ！</a></p>
                        </dd>
                    </dl>
                </c:when>
                <c:otherwise>
                    <dl>
                        <dt><img border="0" alt=""
                                 src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/login_exp03.png"/>
                        </dt>
                        <dd>
                            <h2 class="orange Fb">对不起，验证码已过期或不正确！</h2>
                            <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/register/emailcheck.html?str=${mail }"
                               class="blue Fb" id="hylinkReTry">重新发送验证邮件</a>
                        </dd>
                    </dl>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
