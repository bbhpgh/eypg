<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>_商品列表页
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网,1元购物,1元购,一元购物</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/goodslist.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/header.css"/>
</head>

<body class="buy">
<input type="hidden" id="topid" value="${id }"/>
<input type="hidden" id="typeId" value="${typeId }"/>
<input type="hidden" id="typeName" value="${typeName }"/>
<input type="hidden" id="resultCount" value="${resultCount }"/>

<!-- start -->
<div id="loadingPicBlock" class="wrap">
    <div class="Current_nav">
        <a href="/">首页</a> &gt; ${typeName }
    </div>
    <div class="list_Curtit" id="current">
        <h1 class="fl">${typeName }</h1>
        <span id="spTotalCount">(共<em class="orange">${resultCount }</em>件相关商品)</span>
    </div>
    <!--			<div class="list_Sort">-->
    <!--				<dl>-->
    <!--					<dt>排序</dt>-->
    <!--					<dd>-->
    <!--						<a class="" id="about20" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/about20.html">即将揭晓</a>-->
    <!--						<a class="" id="hot20" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20.html">人气</a>-->
    <!--						<a class="" id="surplus20" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/surplus20.html">剩余人次</a>-->
    <!--						<a class="" id="date20" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/date20.html">最新</a>-->
    <!--						<a class="Price_Sort" id="price20" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/price20.html">价格 <i></i>-->
    <!--						</a>-->
    <!--					</dd>-->
    <!--				</dl>-->
    <!--			</div>-->
    <div class="listContent">
        <ul id="ulGoodsList" class="item">
            <s:iterator value="productList" var="Products">
                <li codeid="${Products.productId }" class="goods-iten">
                    <div class="pic">
                        <a title="${Products.productTitle }" target="_blank"
                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/productInfo/${Products.productId }.html"
                           rel="nofollow">
                            <c:if test="${Products.style=='goods_xs'}">
                                <i class="goods_xs"></i>
                            </c:if>
                            <c:if test="${Products.style=='goods_xp'}">
                                <i class="goods_xp"></i>
                            </c:if>
                            <c:if test="${Products.style=='goods_rq'}">
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
                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/productInfo/${Products.productId }.html">${Products.productName }</a>
                    </p>
                    <p style="color: #999;font-family: 宋体;font-size: 16px;padding: 0 10px 0;text-align: left;">价值：<span
                            style="background-position: 0 -3px;background-repeat: no-repeat;color: #f60;padding-left: 10px;">￥${Products.productPrice}.00</span>
                    </p>
                    <div class="Progress-bar">

                    </div>
                    <div class="gl_buybtn">

                        <div class="go_buy">
                            <a class="go_Shopping fl" style="padding: 15px 55px;font-size: 24px;" title="立即购买"
                               prId="${Products.productId }" price="${Products.productPrice }" name="goPay"
                               href="javascript:;">立即购买</a>
                        </div>
                    </div>
                    <div class="goods_Curbor"></div>
                </li>
            </s:iterator>
        </ul>
    </div>
    <div style="display: none;" class="goods_loding" id="divLoadingLine">
        <img border="0" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/goods_loading.gif" alt=""
             style="vertical-align: middle;"/>
        正在努力加载中......
    </div>
    <div class="list_Pageline"></div>
    ${pageString }
    <!--			<div class="list_goodsTj" id="divGoodsRec">-->
    <!--				<div class="list_goodsTjTit">-->
    <!--					人气推荐-->
    <!--				</div>-->
    <!--				<div class="list_goodsTjCon" id="divRecommend">-->
    <!--					<div class="Roll_Left">-->
    <!--						<a href="javascript:;" name="left" class="Roll_enabled">&lt;</a>-->
    <!--					</div>-->
    <!--					<div class="Roll_Con">-->
    <!--						<ul id="ulRecommend" style="width: 1820px; left: 0px;">-->
    <!--						</ul>-->
    <!--					</div>-->
    <!--					<div class="Roll_Right">-->
    <!--						<a href="javascript:;" name="right" class="Roll_enabled">&gt;</a>-->
    <!--					</div>-->
    <!--				</div>-->
    <!--			</div>-->
</div>
<!-- end -->
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/productlist.js"></script>
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.scrollloading-min.js"></script>
</body>
</html>
