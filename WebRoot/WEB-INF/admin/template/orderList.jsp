<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
<div class="header-data lr10">
    搜索订单 <input type="text" id="keyword" class="input-text wid200" title="可输入用户ID号查询订单"/>
    <input type="submit" id="search" value="搜索" class="button"/>
    <script language="javascript" type="text/javascript">
        $("#search").click(function () {
            location.replace("/admin_back/orderList.action?userName=" + $("#keyword").val());
        });
    </script>
</div>
<div class="bk10"></div>
<div class="table-list lr10">
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="5%">交易时间</th>
            <th width="5%">购买总数</th>
            <th width="5%">金额</th>
            <th width="8%">支付类型</th>
            <th width="5%">用户ID</th>
            <th width="5%">详情</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="orderBeanList" var="orderBean" status="st">
            <tr>
                <td align="center">${orderBean.date }</td>
                <td align="center">${orderBean.buyCount }</td>
                <td align="center">${orderBean.money }</td>
                <td align="center">${orderBean.payType }</td>
                <td align="center"
                ">${orderBean.userId }</td>
                <td align="center"><a href="/admin_back/orderInfo.action?id=${orderBean.outTradeNo }">详情</a></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
${pageString }
</body>
</html>
