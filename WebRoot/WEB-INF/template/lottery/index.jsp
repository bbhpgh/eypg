<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>最新揭晓_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/lottery.css"/>
</head>

<body class="lottery" id="loadingPicBlock">
<div class="Current_nav"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">首页</a> &gt; 最新揭晓</div>
<div class="Newpublish">
    <div class="NewpublishL">
        <div class="publish_Curtit" id="current">
            <h1 class="fl">最新揭晓</h1>
            <span id="spTotalCount">(到目前为止共揭晓商品<em class="orange">${resultCount }</em>件)</span></div>
        <div class="publishBor">
            <div id="listDivTitle" class="publishCen">
                <ul id="ProductList">
                    <s:iterator value="latestlotteryList" var="latestlotterys">
                        <li class="">
                            <c:if test="${latestlotterys.announcedType==1}">
                                <i class="lottery-time">限时揭晓</i>
                            </c:if>
                            <a title="${latestlotterys.productName }" target="_blank" rel="nofollow"
                               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${latestlotterys.spellbuyProductId }.html"
                               class="fl goodsimg">
                                <img alt="${latestlotterys.productName }" class="scrollLoading"
                                     data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${latestlotterys.productImg}"
                                     src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif"/></a>
                            <div class="publishC-Member gray02"><a title="${latestlotterys.buyUser }"
                                                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${latestlotterys.userId }.html"
                                                                   target="_blank" rel="nofollow" class="fl headimg">
                                <img border="0" class="scrollLoading"
                                     data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${latestlotterys.userFace }"
                                     src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif"/></a>
                                <p>获得者：<a title="${latestlotterys.buyUser }"
                                          href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${latestlotterys.userId }.html"
                                          target="_blank" class="blue Fb">${latestlotterys.buyUser }</a></p>
                                <p>来自：${latestlotterys.location }</p>
                                <p><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>：<em
                                        class="orange Fb">${latestlotterys.buyNumberCount }</em>人次</p>
                            </div>
                            <div class="publishC-tit">
                                <h3><a class="gray01" target="_blank"
                                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${latestlotterys.spellbuyProductId }.html">(第${latestlotterys.productPeriod }期)${latestlotterys.productName }</a>
                                </h3>
                                <p class="money">商品价值：<span class="rmb">${latestlotterys.productPrice }.00</span></p>
                                <p class="Announced-time gray02">揭晓时间：${latestlotterys.announcedTime }</p>
                            </div>
                            <div class="details">
                                <p class="fl details-Code">
                                    幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码：<em
                                        class="orange Fb">${latestlotterys.randomNumber }</em></p>
                                <a target="_blank" rel="nofollow"
                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${latestlotterys.spellbuyProductId }.html"
                                   class="fl details-A">查看详情</a>
                            </div>
                        </li>
                    </s:iterator>
                </ul>
            </div>
            ${pageString }
        </div>
    </div>
    <div class="NewpublishR">
        <div class="Newpublishbor">
            <div class="Rtitle gray01">TA们正在<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
            </div>
            <div class="Rcenter buylistH">
                <ul id="buyList" style="margin-top: 0px;">
                </ul>
            </div>
        </div>
        <div class="blank10"></div>
        <div class="Newpublishbor">
            <div class="Rtitle gray01">人气排行</div>
            <div class="Rcenter RcenterPH">
                <ul id="RcenterH" class="RcenterH">

                </ul>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>

<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/lotteryfun.js"></script>
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/lotterytime.js"></script>
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.scrollloading-min.js"></script>
</body>
</html>
