<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>最新<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        记录 <%=ApplicationListenerImpl.sysConfigureJson.getSaitTitle() %>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/record.css"/>
</head>

<body>
<div class="ygRecord">
    <h3>
        <span>最新<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</span>
        <p>本页显示最新<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录<strong>100</strong>条
            <!--				<a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/getAllBuyRecord.html">查看历史<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录&gt;&gt;</a>-->
        </p>
    </h3>
    <ul class="Record_title">
        <li class="time"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>时间</li>
        <li class="nem">会员账号</li>
        <li class="name"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>商品名称</li>
        <li class="much"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>人次</li>
    </ul>
    <div id="recordList">
        <s:iterator value="productList" var="newRecords" status="i">
            <c:choose>
                <c:when test="${i.index%2==0}">
                    <ul class="Record_content" rel="${newRecords.productStyle }">
                        <li class="time">${newRecords.buyDate }</li>
                        <li class="nem"><a
                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${newRecords.userId }.html"
                                target="_blank" class="blue">${newRecords.buyer }</a></li>
                        <li class="name"><a
                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${newRecords.productId }.html"
                                title="${newRecords.productTitle }">（第${newRecords.productPeriod }期）${newRecords.productName }</a>
                        </li>
                        <li class="much">${newRecords.buyCount }人次</li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="Record_contents" rel="${newRecords.productStyle }">
                        <li class="time">${newRecords.buyDate }</li>
                        <li class="nem"><a
                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${newRecords.userId }.html"
                                target="_blank" class="blue">${newRecords.buyer }</a></li>
                        <li class="name"><a
                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${newRecords.productId }.html"
                                title="${newRecords.productTitle }">（第${newRecords.productPeriod }期）${newRecords.productName }</a>
                        </li>
                        <li class="much">${newRecords.buyCount }人次</li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </s:iterator>
    </div>
    <div class="endpage">
        本页显示最新<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录<strong>100</strong>条
        <!--				<a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/getAllBuyRecord.html">查看历史<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录&gt;&gt;</a>-->
    </div>
</div>
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/newrecord.js"></script>
</body>
</html>
