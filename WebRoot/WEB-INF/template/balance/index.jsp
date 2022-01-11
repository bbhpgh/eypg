<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="template_footer"/>
    <title>支付结果_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/mycart.css"/>
</head>

<body>
<div class="logo">
    <div class="float">
        <span class="logo_pic"><a title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>" class="a"
                                  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>"></a></span><span
            class="tel"></span>
    </div>
</div>
<div class="shop_payment">
    <ul class="payment">
        <li class="first_step">第一步：提交订单</li>
        <li class="arrow_2"></li>
        <li class="secend_step">第二步：网银支付</li>
        <li class="arrow_1"></li>
        <li class="third_step orange_Tech">第三步：支付成功 等待揭晓</li>
        <li class="arrow_3"></li>
        <li class="fourth_step">第四步：揭晓获得者</li>
        <li class="arrow_2"></li>
        <li class="fifth_step">第五步：晒单奖励</li>
    </ul>
    <div class="wait_list hidden" id="divResult" style="display: block;">
        <c:choose>
            <c:when test="${paymentStatus == 'success' }">
                <div class="wait_list_tips ">
                    <dl>
                        <dt><img border="0" alt=""
                                 src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/login_exp04.png"/>
                        </dt>
                        <dd>
                            <h2 class="orange Fb">恭喜您，充值成功！</h2>
                            <p>您现在可以 <a class="blue"
                                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/UserBalance.html">查看充值记录</a>
                                或 <a class="blue"
                                     href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">继续购物</a></p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="wait_list_tips ">
                    <dl>
                        <dt><img border="0" alt=""
                                 src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/login_exp03.png"/>
                        </dt>
                        <dd>
                            <h2 class="orange Fb">哎呀！充值失败了。请与管理员联系！</h2>
                        </dd>
                    </dl>
                </div>
                <div class="wait_listCon">

                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div class="fast">
    <h3><span>以下商品即将揭晓，快<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>吧！</span></h3>
</div>
<div class="clear_process"></div>
<link type="text/css" rel="stylesheet"
      href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css"/>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/successcartlist.js"></script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
<div class="pageDialogBG" id="pageDialogBG"></div>
<div class="pageDialogBorder" id="pageDialogBorder"></div>
<div class="pageDialog" id="pageDialog">
    <div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    <div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
</div>
</body>
</html>
