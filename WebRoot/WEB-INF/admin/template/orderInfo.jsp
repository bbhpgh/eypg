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
</head>

<body>
<div class="bk10"></div>
<div class="table-list lr10">
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="5%">购买总数</th>
            <th width="5%">金额</th>
            <th width="25%">商品名称</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="orderBeanList" var="orderBean" status="st">
            <tr align="center">
                <td align="center">${orderBean.buyCount }</td>
                <td align="center">${orderBean.money }</td>
                <td align="center">
                    <a href="/products/${orderBean.productId }.html" target="_blank">${orderBean.productName }
                        (第${orderBean.productPeriod }期)</a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <div class="bk10"></div>
    <input type="button" value="返回" class="button" onclick="history.go(-1)"/>
    <div class="bk10"></div>
</div>
${pageString }
</body>
</html>
