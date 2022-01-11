<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    ResourceBundle url = ResourceBundle.getBundle("config");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="admin_main"/>
    <title>1元拍购_管理中心</title>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div align="center">
    <table border="1px;" width="1024px;">
        <tr>
            <th>商品期数</th>
            <th>商品名称</th>
            <th>揭晓时间</th>
            <th>获得者</th>
            <th>商品图片</th>
            <th>物流状态</th>
            <th>晒单状态</th>
            <th>操作</th>
        </tr>
        <s:iterator value="latestlotteryList" var="latestlotterys">
            <tr>
                <td>${latestlotterys.productPeriod }</td>
                <td>${latestlotterys.productName }</td>
                <td>${latestlotterys.announcedTime }</td>
                <td>${latestlotterys.userName }</td>
                <td><img width="50px;" height="50px;" src="<%=url.getString("img")%>${latestlotterys.productImg }"/>
                </td>
                <td>
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
                <td>
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
                <td>
                    <a href="/admin_back/toAddShare.action?id=${latestlotterys.spellbuyProductId }&userId=${latestlotterys.userId }&announcedTime=${latestlotterys.announcedTime }">[晒单]</a>
                    <a href="javascript:alert('用户未提交收获地址');">[发货]</a>
                </td>
            </tr>
        </s:iterator>

    </table>
    ${pageString }
</div>
</body>
</html>
