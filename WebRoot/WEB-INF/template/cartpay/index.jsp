<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="template_footer"/>
    <title>购物车_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/cartlist.css"/>
</head>

<body>
<div class="logo">
    <div class="float">
        <span class="logo_pic"><a title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>" class="a"
                                  href="/index.html"></a></span><span class="tel"><a style="color:#999;"
                                                                                     href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">返回首页</a></span>
    </div>
</div>
<div class="shop_process">
    <ul class="process">
        <li class="first_step">第一步：提交订单</li>
        <li class="arrow_1"></li>
        <li class="secend_step">第二步：网银支付</li>
        <li class="arrow_2"></li>
        <li class="third_step">第三步：支付成功 等待配送</li>
        <li class="arrow_2"></li>
        <li class="fourth_step">第四步：确认收货</li>
        <li class="arrow_2"></li>
        <li class="fifth_step">第五步：晒单奖励</li>
    </ul>
    <div class="submitted">
        <ul class="order">
            <li class="top"><span class="goods">商品</span><span class="name" style="width:458px;">名称</span><span
                    class="moneys" style="width:150px;">市场价</span><span class="xj" style="width:150px;">小计</span><span
                    class="do" style="width:150px;">操作</span></li>
            <s:iterator var="productCarts" value="productCartList">
                <li class="end">
                    <input type="hidden" value="${productCarts.productId }"/>
                    <input type="hidden" name="CartMoneyCount" value="${productCarts.moneyCount }"/>
                    <input type="text" value="${productCarts.productPrice }" style="display: none;"/>
                    <span class="goods">
                             <input type="checkbox" checked="checked" class="check"/>
                             	<a title="${productCarts.productName } ${productCarts.productTitle }"
                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/productInfo/${productCarts.productId }.html">
                                 <img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${productCarts.headImage }"/></a></span>
                    <span><span class="name" style="width:480px;"><a style="width:480px;"
                                                                     title="${productCarts.productName } ${productCarts.productTitle }"
                                                                     href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/productInfo/${productCarts.productId }.html">
                            ${productCarts.productName }</a>
                                 </span>
                                 	<span class="moneys" style="width:150px;">￥${productCarts.productPrice}</span> 
                                 </span>
                    <span class="xj" style="width:155px;">￥${productCarts.productPrice }.00</span>
                    <span class="do"><a class="delgood" href="javascript:;">删除</a></span>
                </li>
            </s:iterator>
            <li>
                <div class="cartno" id="cartNO"></div>
            </li>
            <li class="ts">
                <p class="left">
                    <input type="checkbox" checked="checked" id="ckAll"><a class="all" href="javascript:;"><label
                        for="ckAll">全选</label></a><a id="AllDelete" class="del" href="javascript:;">批量删除</a></p>
                <p class="right"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>金额总计:￥<span
                        id="moneyCount">0.00</span></p>
            </li>
        </ul>
    </div>
    <h5>
        <a id="but_on" href="javascript:;"></a>
        <input type="button" name="submitted" value="" id="but_ok"></h5>
</div>
<link type="text/css" rel="stylesheet"
      href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css"/>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/cartbuylist.js"></script>
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
