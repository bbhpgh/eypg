<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> <%=ApplicationListenerImpl.sysConfigureJson.getSaitTitle() %>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/home.css"/>
</head>

<body class="index" id="loadingPicBlock" rf="1">
<!-- 大图 -->
<div class="banner_recommend">
    <div class="banner-box">
        <div class="yg-flow" id="posterTopNav">
            <a target="_blank"
               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/ship.html"><img width="742"
                                                                                                    height="50"
                                                                                                    alt="新手指南"
                                                                                                    src="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/Images/help.gif"/>
            </a>
        </div>
        <div class="banner">
            <div id="slideImg"></div>
            <div style="display: none;" id="posterBanner"></div>
            <ul></ul>
        </div>
    </div>
    <div class="recommend">
        <ul class="Pro">
            <c:if test="${recommendJSON!=null}">
                <li>
                    <div class="pic">
                        <a title="${recommendJSON.productTitle }" target="_blank"
                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${recommendJSON.productId }.html"
                           rel="nofollow"><img
                                alt="${recommendJSON.productName }"
                                src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${recommendJSON.headImage }"/>
                        </a>
                    </div>
                    <p class="name">
                        <a title="${recommendJSON.productTitle }" target="_blank"
                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${recommendJSON.productId }.html">${recommendJSON.productName }</a>
                    </p>
                    <p class="money">
                        价值：
                        <span class="rmb">${recommendJSON.productPrice }.00</span>
                    </p>
                    <div class="Progress-bar">
                        <p title="已完成<fmt:formatNumber type="number" value="${(recommendJSON.currentBuyCount/recommendJSON.productPrice)*100 } " maxFractionDigits="2"/>%">
                            <span style="width: <fmt:formatNumber type="number"
                                                                  value="${(recommendJSON.currentBuyCount/recommendJSON.productPrice)*100 } "
                                                                  maxFractionDigits="2"/>%"></span>
                        </p>
                        <ul class="Pro-bar-li">
                            <li class="P-bar01">
                                <em>${recommendJSON.currentBuyCount}</em>已参与人次
                            </li>
                            <li class="P-bar02">
                                <em>${recommendJSON.productPrice }</em>总需人次
                            </li>
                            <li class="P-bar03">
                                <em>${recommendJSON.productPrice - recommendJSON.currentBuyCount}</em>剩余人次
                            </li>
                        </ul>
                    </div>
                    <p>
                        <a style="-moz-border-bottom-colors: none;-moz-border-left-colors: none;-moz-border-right-colors: none;-moz-border-top-colors: none;background: none repeat scroll 0 0 #f60;border-color: #f60 #f60 #e85700;border-image: none;border-radius: 3px;border-style: solid;border-width: 1px;color: #fff;font-family: 微软雅黑,宋体;font-size: 18px;line-height: 45px;padding: 5px 22px;"
                           target="_blank"
                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${recommendJSON.productId }.html"
                           rel="nofollow">立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                        </a>
                    </p>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<!-- 大图结束 -->
<!-- 最新揭晓start -->
<div class="goods_hot">
    <div class="goods_left">
        <div class="new_lottery">
            <h4>
                <span>最新揭晓</span><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lottery/index.html"
                                    rel="nofollow">更多&gt;&gt;</a>
            </h4>
            <ul id="ulNewAwary">
                <s:iterator var="latestlotterys" value="latestlotteryList">
                    <li class="new_li">
                        <a class="pic" title="${latestlotterys.productTitle }" target="_blank"
                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${latestlotterys.spellbuyProductId }.html"
                           rel="nofollow"><img alt="${latestlotterys.productName }" class="scrollLoading"
                                               data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${latestlotterys.productImg }"
                                               src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif"/>
                        </a>
                        <a class="name" title="${latestlotterys.productTitle }" target="_blank"
                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${latestlotterys.spellbuyProductId }.html">${latestlotterys.productName }</a>
                        <span class="winner">获得者：<strong><a class="blue"
                                                            href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${latestlotterys.userId }.html"
                                                            target="_blank">${latestlotterys.buyUser}</a></strong> </span>
                    </li>
                </s:iterator>
            </ul>
        </div>
        <div class="hot">
            <h3>
                <span>最热人气商品</span><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20.html"
                                      rel="nofollow">更多&gt;&gt;</a>
            </h3>
            <ul class="hot-list" id="hostGoodsItems">
                <s:iterator var="hotProduct" value="hotProductList" status="index">
                    <li class="list-block">
                        <div class="pic">
                            <a title="${hotProduct.productTitle }" target="_blank"
                               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${hotProduct.productId }.html"
                               rel="nofollow">
                                <c:if test="${hotProduct.productStyle=='goods_xs'}">
                                    <i class="goods_xs"></i>
                                </c:if>
                                <c:if test="${hotProduct.productStyle=='goods_xp'}">
                                    <i class="goods_xp"></i>
                                </c:if>
                                <c:if test="${hotProduct.productStyle=='goods_rq'}">
                                    <i class="goods_rq"></i>
                                </c:if>
                                <img class="scrollLoading" alt="${hotProduct.productTitle }"
                                     data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${hotProduct.headImage }"
                                     src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif"/>
                            </a>
                        </div>
                        <p class="name">
                            <a title="${hotProduct.productTitle}" target="_blank"
                               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${hotProduct.productId }.html">${hotProduct.productName}</a>
                        </p>
                        <p class="money">
                            价值：
                            <span class="rmb">${hotProduct.productPrice}.00</span>
                        </p>
                        <div class="Progress-bar">
                            <p title="已完成<fmt:formatNumber type="number" value="${(hotProduct.currentBuyCount/hotProduct.productPrice)*100 } " maxFractionDigits="2"/>%">
                                <span style="width: <fmt:formatNumber type="number"
                                                                      value="${(hotProduct.currentBuyCount/hotProduct.productPrice)*100 } "
                                                                      maxFractionDigits="2"/>%"></span>
                            </p>
                            <ul class="Pro-bar-li">
                                <li class="P-bar01">
                                    <em>${hotProduct.currentBuyCount}</em>已参与人次
                                </li>
                                <li class="P-bar02">
                                    <em>${hotProduct.productPrice}</em>总需人次
                                </li>
                                <li class="P-bar03">
                                    <em>${hotProduct.productPrice - hotProduct.currentBuyCount}</em>剩余人次
                                </li>
                            </ul>
                        </div>
                        <div class="shop_buttom">
                            <a title="立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>"
                               style="-moz-border-bottom-colors: none;-moz-border-left-colors: none;-moz-border-right-colors: none;-moz-border-top-colors: none;background: none repeat scroll 0 0 #f60;border-color: #f60 #f60 #e85700;border-image: none;border-radius: 3px;border-style: solid;border-width: 1px;color: #fff;font-family: 微软雅黑,宋体;font-size: 14px;line-height: 30px;padding: 5px 22px;"
                               target="_blank"
                               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${hotProduct.productId }.html"
                               rel="nofollow"
                               title="立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>">立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                            </a>
                        </div>
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>
    <div class="goods_right">
        <div class="news">
            <h3>
                <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>动态
            </h3>
            <ul>
                <s:iterator var="news" value="newsList" status="index">
                    <li><a target="_blank"
                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/news/${news.newsId }.html">${news.title }</a>
                    </li>
                </s:iterator>
            </ul>
        </div>
        <div id="divLottery" class="wait_lottery">
            <a target="_blank"
               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/hot20/macbook.html"><img
                    width="230" height="200"
                    alt="苹果（Apple）MacBook Air MD224CH/A 11.6英寸宽屏笔记本电脑"
                    src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/MacBookAir.jpg"/>
            </a>
        </div>
        <div class="share">
            <h3>
                TA们正在<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
            </h3>
            <div class="buyList">
                <input type="hidden" value="" id="hidBuyID" name="hidBuyID"/>
                <ul id="buyList" style="margin-top: 0px;">
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- 最新揭晓结束 -->
<div class="clear"></div>
<!-- 即将揭晓start -->
<div class="get_ready">
    <h3><span>即将揭晓</span><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/about20.html"
                            rel="nofollow">更多&gt;&gt;</a></h3>
    <ul class="hot-list" id="readyLotteryItems">
        <s:iterator var="products" value="ProductList" status="index">
            <li class="list-block">
                <div class="pic">
                    <a title="${products.productTitle }" target="_blank"
                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${products.productId }.html"
                       rel="nofollow">
                        <c:if test="${products.productStyle=='goods_xs'}">
                            <i class="goods_xs"></i>
                        </c:if>
                        <c:if test="${products.productStyle=='goods_xp'}">
                            <i class="goods_xp"></i>
                        </c:if>
                        <c:if test="${products.productStyle=='goods_rq'}">
                            <i class="goods_rq"></i>
                        </c:if>
                        <img class="scrollLoading" alt="${products.productTitle }"
                             data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${products.headImage }"
                             src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif"/></a>
                </div>
                <p class="name">
                    <a title="${products.productTitle }" target="_blank"
                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${products.productId }.html">${products.productName }</a>
                </p>
                <p class="money">价值：<span class="rmb">${products.productPrice }.00</span></p>
                <div class="Progress-bar">
                    <p title="已完成<fmt:formatNumber type="number" value="${(products.currentBuyCount/products.productPrice)*100 } " maxFractionDigits="2"/>%">
                        <span style="width: <fmt:formatNumber type="number"
                                                              value="${(products.currentBuyCount/products.productPrice)*100 } "
                                                              maxFractionDigits="2"/>%"></span></p>
                    <ul class="Pro-bar-li">
                        <li class="P-bar01"><em>${products.currentBuyCount }</em>已参与人次</li>
                        <li class="P-bar02"><em>${products.productPrice }</em>总需人次</li>
                        <li class="P-bar03"><em>${products.productPrice - products.currentBuyCount }</em>剩余人次</li>
                    </ul>
                </div>
                <div class="shop_buttom"><a title="立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>"
                                            style="-moz-border-bottom-colors: none;-moz-border-left-colors: none;-moz-border-right-colors: none;-moz-border-top-colors: none;background: none repeat scroll 0 0 #f60;border-color: #f60 #f60 #e85700;border-image: none;border-radius: 3px;border-style: solid;border-width: 1px;color: #fff;font-family: 微软雅黑,宋体;font-size: 14px;line-height: 30px;padding: 5px 22px;"
                                            target="_blank"
                                            href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${products.productId }.html"
                                            rel="nofollow"
                                            title="立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>">立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                </a></div>
            </li>
        </s:iterator>
    </ul>
</div>
<!-- 即将揭晓end -->
<div class="clear"></div>
<!-- 晒单分享start -->
<div class="lottery_show">
    <div class="share_show">
        <h3><span>晒单分享</span><a target="_blank"
                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/share/new20.html">更多&gt;&gt;</a>
        </h3>
        <div class="show">
            <div class="show_list">
            </div>
        </div>
    </div>
</div>
<!-- 晒单分享end -->
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/index.js"></script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.scrollloading-min.js"></script>
</body>
</html>
