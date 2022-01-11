<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="java.net.URLEncoder" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>${keyword }_商品搜索_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/goodslist.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/header.css"/>
</head>

<body rf="2">
<input type="hidden" id="topid" value="${id }"/>
<input type="hidden" id="resultCount" value="${resultCount }"/>
<input type="hidden" id="keyword" value="${keyword }"/>

<!-- start -->
<div id="loadingPicBlock" class="wrap">
    <div class="list_Curtit" id="current">
        <h1 class="fl">商品搜索-"${keyword }"</h1>
        <span id="spTotalCount">(共找到<em class="orange">${resultCount }</em>件相关商品)</span>
    </div>
    <div class="list_Sort">
        <dl>
            <dt>排序</dt>
            <dd>
                <a class="" id="about20"
                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/about20/<%= URLEncoder.encode(request.getParameter("keyword") ,"UTF-8") %>.html">即将揭晓</a>
                <a class="" id="hot20"
                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/hot20/<%= URLEncoder.encode(request.getParameter("keyword") ,"UTF-8") %>.html">人气</a>
                <a class="" id="surplus20"
                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/surplus20/<%= URLEncoder.encode(request.getParameter("keyword") ,"UTF-8") %>.html">剩余人次</a>
                <a class="" id="date20"
                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/date20/<%= URLEncoder.encode(request.getParameter("keyword") ,"UTF-8") %>.html">最新</a>
                <a class="Price_Sort" id="price20"
                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/price20/<%= URLEncoder.encode(request.getParameter("keyword") ,"UTF-8") %>.html">价格
                    <i></i>
                </a>
            </dd>
        </dl>
    </div>
    <div class="listContent">
        <c:choose>
            <c:when test="${fn:length(ProductList)==0}">
                <div class="NoConMsg"><span>未找到有关“<em class="orange">${keyword }</em>”的商品</span></div>
            </c:when>
            <c:otherwise>
                <ul id="ulGoodsList" class="item">
                    <s:iterator value="ProductList" var="Products">
                        <li codeid="${Products.productId }" class="goods-iten">
                            <div class="pic">
                                <a title="${Products.productTitle }" target="_blank"
                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${Products.productId }.html"
                                   rel="nofollow">
                                    <c:if test="${Products.productStyle=='goods_xs'}">
                                        <i class="goods_xs"></i>
                                    </c:if>
                                    <c:if test="${Products.productStyle=='goods_xp'}">
                                        <i class="goods_xp"></i>
                                    </c:if>
                                    <c:if test="${Products.productStyle=='goods_rq'}">
                                        <i class="goods_rq"></i>
                                    </c:if>
                                    <img class="scrollLoading"
                                         data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${Products.headImage }"
                                         src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif"
                                         title="${Products.productTitle }" alt="${Products.productName }"/>
                                </a>
                                <p style="display: none;" name="buyCount"></p>
                            </div>
                            <p class="name">
                                <a title="${Products.productTitle }" target="_blank"
                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${Products.productId }.html">${Products.productName }</a>
                            </p>
                            <p class="money">价值：<span class="rmbgray">${Products.productPrice}.00</span>
                            </p>
                            <div class="Progress-bar">
                                <p title="已完成<fmt:formatNumber type="number" value="${(Products.currentBuyCount/Products.productPrice)*100 } " maxFractionDigits="2"/>%">
                                    <span style="width: <fmt:formatNumber type="number"
                                                                          value="${(Products.currentBuyCount/Products.productPrice)*100 } "
                                                                          maxFractionDigits="2"/>%"></span></p>
                                <ul class="Pro-bar-li">
                                    <li class="P-bar01">
                                        <em>${Products.currentBuyCount }</em>已参与人次
                                    </li>
                                    <li class="P-bar02">
                                        <em>${Products.productPrice }</em>总需人次
                                    </li>
                                    <li class="P-bar03">
                                        <em>${Products.productPrice - Products.currentBuyCount }</em>剩余人次
                                    </li>
                                </ul>
                            </div>
                            <div class="gl_buybtn">
                                <div style="display: none;" class="error">
                                    最少需<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>1人次
                                </div>
                                <div class="gl_number">我要<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                                    <a class="num_del num_ban" href="javascript:;">-</a>
                                    <input type="text" class="num_dig" value="1"
                                           surplus="${Products.productPrice - Products.currentBuyCount }"/>
                                    <a class="num_add" href="javascript:;">+</a> 人次
                                </div>
                                <div class="go_buy">
                                    <a class="go_Shopping fl"
                                       title="立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>"
                                       href="javascript:;">立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                                    </a><a class="go_cart fr"
                                           title="加入购物车" href="javascript:;">加入购物车</a>
                                </div>
                            </div>
                            <div class="goods_Curbor"></div>
                        </li>
                    </s:iterator>
                </ul>
                <div class="list_Pageline"></div>
                ${pageString }
                <div style="display: none;" class="pages" id="divPageNav"></div>
            </c:otherwise>
        </c:choose>
    </div>


    <div class="list_goodsTj" id="divGoodsRec">
        <div class="list_goodsTjTit">
            人气推荐
        </div>
        <div class="list_goodsTjCon" id="divRecommend">
            <div class="Roll_Left">
                <a href="javascript:;" name="left" class="Roll_enabled">&lt;</a>
            </div>
            <div class="Roll_Con">
                <ul id="ulRecommend" style="width: 1820px; left: 0px;">
                </ul>
            </div>
            <div class="Roll_Right">
                <a href="javascript:;" name="right" class="Roll_enabled">&gt;</a>
            </div>
        </div>
    </div>
</div>
<!-- end -->
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/searchlistfun.js"></script>
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.scrollloading-min.js"></script>
</body>
</html>
