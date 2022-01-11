<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>1元拍购_管理中心</title>
    <link href="/admin_css/global.css" rel="stylesheet" type="text/css"/>
    <link href="/admin_css/admin_style.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div class="bk10"></div>
<div class="table-list lr10">
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="5%">商品期数</th>
            <th width="20%">商品名称</th>
            <th width="10%">揭晓时间</th>
            <th width="5%">获得者</th>
            <th width="5%">商品图片</th>
            <th width="5%">物流状态</th>
            <th width="5%">晒单状态</th>
            <th width="10%">操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="latestlotteryList" var="latestlotterys">
            <tr>
                <td align="center">${latestlotterys.productPeriod }</td>
                <td align="center">${latestlotterys.productName }</td>
                <td align="center">${latestlotterys.announcedTime }</td>
                <td align="center">${latestlotterys.userName }</td>
                <td align="center"><img width="50px;" height="50px;"
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${latestlotterys.productImg }"/>
                </td>
                <td align="center">
                    <c:if test="${latestlotterys.status == 1}">
                        未提交收获地址
                    </c:if>
                    <c:if test="${latestlotterys.status == 2}">
                        等待发货
                    </c:if>
                    <c:if test="${latestlotterys.status == 3}">
                        等待收货
                    </c:if>
                    <c:if test="${latestlotterys.status == 4}">
                        已确认收货
                    </c:if>
                    <c:if test="${latestlotterys.status == 10}">
                        交易完成
                    </c:if>
                    <c:if test="${latestlotterys.status == 11}">
                        交易取消
                    </c:if>
                </td>
                <td align="center">
                    <c:if test="${latestlotterys.shareStatus == -1}">
                        暂未晒单
                    </c:if>
                    <c:if test="${latestlotterys.shareStatus == 0}">
                        等待审核
                    </c:if>
                    <c:if test="${latestlotterys.shareStatus == 1}">
                        未审核通过，请重新修改晒单信息
                    </c:if>
                    <c:if test="${latestlotterys.shareStatus == 2}">
                        审核通过，奖励10元
                    </c:if>
                </td>
                <td align="center">
                    <a href="/admin_back/toAddShare.action?id=${latestlotterys.spellbuyProductId }&userId=${latestlotterys.userId }&announcedTime=${latestlotterys.announcedTime }">[晒单]</a>
                    <a href="javascript:alert('用户未提交收获地址');">[发货]</a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
${pageString }
</body>
</html>
