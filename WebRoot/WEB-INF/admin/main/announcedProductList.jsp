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
            <th>期数ID</th>
            <th>商品ID</th>
            <th width="470px;">商品名称</th>
            <th>价格</th>
            <th>商品图片</th>
            <th>操作</th>
        </tr>
        <s:iterator value="productJSONList" var="productJSONs">
            <tr>
                <td>${productJSONs.productId }</td>
                <td>${productJSONs.currentBuyCount }</td>
                <td>${productJSONs.productName }</td>
                <td>${productJSONs.productPrice }</td>
                <td><img width="50px;" height="50px;" src="<%=url.getString("img")%>${productJSONs.headImage }"/></td>
                <td><a href="/products/${productJSONs.productId }.html" target="_blank">[查看]</a> <a
                        name="upSpellbuyrecord" id="${productJSONs.currentBuyCount }" href="javascript:;">[上架]</a></td>
            </tr>
        </s:iterator>

    </table>
    ${pageString }
</div>
<script type="text/javascript">
    $("[name='upSpellbuyrecord']").click(function () {
        var id = $(this).attr("id");
        $.ajax({
            url: "/admin_back/upSpellbuyrecord.action",
            type: "post",
            data: "id=" + id,
            success: function (data) {
                if (data == "上架成功！") {
                    $("#" + id).html("[已上架]");
                }
            }
        });
    });
</script>
</body>
</html>
