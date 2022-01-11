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
<div class="header lr10">
    <a href="/admin_back/index.action?id=date20">最新上架</a><span class="span_fenge lr5">|</span>
    <a href="/admin_back/index.action?id=hot20">最高人气</a><span class="span_fenge lr5">|</span>
    <a href="/admin_back/index.action?id=price20">最高价格</a><span class="span_fenge lr5">|</span>
    <a href="/admin_back/index.action?id=priceAsc20">最低价格</a><span class="span_fenge lr5">|</span>
</div>
<div class="bk10"></div>

<div class="table-list lr10">
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="5%">期数ID</th>
            <th width="5%">期数</th>
            <th width="25%">商品名称</th>
            <th width="5%">价格</th>
            <th width="5%">当前购买数</th>
            <th width="5%">商品图片</th>
            <th width="10%">操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="productJSONList" var="productJSONs">
            <tr>
                <td align="center">${productJSONs.productId }</td>
                <td align="center">${productJSONs.productPeriod }</td>
                <td align="center">${productJSONs.productName }</td>
                <td align="center">${productJSONs.productPrice }</td>
                <td align="center">${productJSONs.currentBuyCount }</td>
                <td align="center"><img width="50px;" height="50px;"
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/${productJSONs.headImage }"/>
                </td>
                <td align="center"><a href="/products/${productJSONs.productId }.html" target="_blank">[查看]</a> <a
                        href="javascript:;" pid="${productJSONs.productId  }" name="indexTop">[首页推荐]</a></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    ${pageString }
</div>

<script language="javascript" type="text/javascript">
    $("#select").change(function () {
        location.replace("/admin_back/index.action?id=" + $("#select").val());
    });
    $("[name=indexTop]").click(function () {
        var id = $(this).attr("pid");
        $.ajax({
            url: "/admin_back/indexTop.action?id=" + id,
            success: function (data) {
                if (data == "success") {
                    alert("操作成功");
                }
            },
            error: function () {
                alert("网络错误，请稍后再试！");
            }
        });
    });
</script>
</body>
</html>
