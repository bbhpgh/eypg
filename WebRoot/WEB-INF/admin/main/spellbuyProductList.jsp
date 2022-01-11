<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    ResourceBundle url = ResourceBundle.getBundle("config");
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="admin_main"/>
    <title>1元拍购_管理中心</title>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div align="center" style="padding: 10px;">
    <div class="form-group">
        <label class="col-sm-3 control-label">列表排序</label>
        <div class="col-sm-6" style="width: 180px;">
            <select id="select" class="form-control">
                <option value="hot20">请选择</option>
                <option value="date20">最新上架</option>
                <option value="hot20">人气</option>
                <option value="price20">高价格</option>
                <option value="priceAsc20">低价格</option>
            </select>
        </div>
    </div>

    <table border="1px;" width="1024px;">
        <tr>
            <th>期数ID</th>
            <th>期数</th>
            <th width="470px;">商品名称</th>
            <th>价格</th>
            <th>当前购买数</th>
            <th>商品图片</th>
            <th>操作</th>
        </tr>
        <s:iterator value="productJSONList" var="productJSONs">
            <tr>
                <td>${productJSONs.productId }</td>
                <td>${productJSONs.productPeriod }</td>
                <td>${productJSONs.productName }</td>
                <td>${productJSONs.productPrice }</td>
                <td>${productJSONs.currentBuyCount }</td>
                <td><img width="50px;" height="50px;" src="<%=url.getString("img")%>/${productJSONs.headImage }"/></td>
                <td><a href="/products/${productJSONs.productId }.html" target="_blank">[查看]</a> <a href="javascript:;"
                                                                                                    pid="${productJSONs.productId  }"
                                                                                                    name="indexTop">[首页推荐]</a>
                </td>
            </tr>
        </s:iterator>

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
