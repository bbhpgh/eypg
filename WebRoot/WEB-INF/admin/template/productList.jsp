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
    <a href="/admin_back/productList.action">商品列表</a><span class="span_fenge lr5">|</span><a
        href="/admin_back/toAddProduct.action">添加商品</a><span class="span_fenge lr5">|</span><a
        href="/admin_back/productTypeList.action">商品分类</a><span class="span_fenge lr5">|</span><a
        href="/admin_back/index.action?id=hot20">在售商品管理</a><span class="span_fenge lr5">|</span></div>
<div class="bk10"></div>
<input type="hidden" value="${resultCount }" id="resultCount"/>
<input type="hidden" id="backUrl" name="backUrl" value=""/>

<div class="header-data lr10">
    <select name="products.productType" id="typeId" class="form-control" style="width:180px;">
        <option value="">请选择</option>
        <s:iterator value="productTypeList" var="productTypes">
            <c:choose>
                <c:when test="${products.productType==productTypes.typeId }">
                    <option selected="selected" value="${productTypes.typeId }">${productTypes.typeName }</option>
                </c:when>
                <c:otherwise>
                    <option value="${productTypes.typeId }">${productTypes.typeName }</option>
                </c:otherwise>
            </c:choose>
        </s:iterator>
    </select>
    商品名称：<input type="text" value="" name="product.productName" id="keyword" class="input-text wid200"/>
    <input type="submit" value="搜索" id="search" class="button"/>
</div>
<div class="bk10"></div>

<div class="table-list lr10">
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="5%">商品ID</th>
            <th width="25%">商品名称</th>
            <th width="5%">商品价格</th>
            <th width="5%">商品图片</th>
            <th width="5%">商品类型</th>
            <th width="20%">商品操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="productList" var="products">
            <tr>
                <td align="center">${products.productId } </td>
                <td align="left">${products.productName } </td>
                <td align="center">${products.productPrice } </td>
                <td align="center"><img width="50px;" height="50px;"
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/${products.headImage } "/>
                </td>
                <td align="center">
                    <c:if test="${products.productType ==1000}">
                        全部分类
                    </c:if>
                    <c:if test="${products.productType ==1001}">
                        手机数码
                    </c:if>
                    <c:if test="${products.productType ==1002}">
                        电脑办公
                    </c:if>
                    <c:if test="${products.productType ==1003}">
                        家用电器
                    </c:if>
                    <c:if test="${products.productType ==1004}">
                        化妆个护
                    </c:if>
                    <c:if test="${products.productType ==1005}">
                        钟表首饰
                    </c:if>
                    <c:if test="${products.productType ==1006}">
                        礼品箱包
                    </c:if>
                    <c:if test="${products.productType ==1007}">
                        其它商品
                    </c:if>
                    <c:if test="${products.productType ==1008}">
                        上网本
                    </c:if>
                    <c:if test="${products.productType ==1009}">
                        平板电脑
                    </c:if>
                    <c:if test="${products.productType ==1010}">
                        眼霜
                    </c:if>
                    <c:if test="${products.productType ==1011}">
                        洁面
                    </c:if>
                    <c:if test="${products.productType ==1012}">
                        乳液面霜
                    </c:if>
                    <c:if test="${products.productType ==1013}">
                        防晒隔离霜
                    </c:if>
                    <c:if test="${products.productType ==1014}">
                        晚霜
                    </c:if>
                    <c:if test="${products.productType ==1015}">
                        精华
                    </c:if>
                    <c:if test="${products.productType ==1016}">
                        BB霜
                    </c:if>
                    <c:if test="${products.productType ==1017}">
                        面膜
                    </c:if>
                    <c:if test="${products.productType ==1018}">
                        口红
                    </c:if>
                    <c:if test="${products.productType ==1019}">
                        女士香水
                    </c:if>
                    <c:if test="${products.productType ==1020}">
                        手机
                    </c:if>
                    <c:if test="${products.productType ==1021}">
                        便携相机
                    </c:if>
                    <c:if test="${products.productType ==1022}">
                        单反相机
                    </c:if>
                    <c:if test="${products.productType ==1023}">
                        数码相框
                    </c:if>
                    <c:if test="${products.productType ==1024}">
                        MP3/MP4
                    </c:if>
                    <c:if test="${products.productType ==1025}">
                        音箱
                    </c:if>
                    <c:if test="${products.productType ==1026}">
                        笔记本
                    </c:if>
                </td>
                <td align="center"><a name="toUpdate" ids="${products.productId }" href="javascript:;">[编辑]</a> <a
                        href="/admin_back/toAddProductImg.action?id=${products.productId }">[添加展示图片]</a>
                    <c:if test="${products.status==0}">
                        <a name="upSpellbuyproduct" id="${products.productId }" href="javascript:;">[上架]</a>
                    </c:if>
                    <c:if test="${products.status==1}">
                        <a name="downSpellbuyproduct" id="${products.productId }" href="javascript:;">[下架]</a>
                    </c:if>
                    <c:if test="${products.status==2}">
                        <a name="upSpellbuyproduct" id="${products.productId }" href="javascript:;">[已下架]</a>
                    </c:if>
                    <c:if test="${products.attribute71=='hot'}">
                        <a name="downHotProduct" pid="${products.productId }" href="javascript:;" style="color: red;">[下架热门商品]</a>
                    </c:if>
                    <c:if test="${products.attribute71!='hot'}">
                        <a name="hotProduct" pid="${products.productId }" href="javascript:;">[设置热门商品]</a>
                    </c:if>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
${pageString }
<script language="javascript" type="text/javascript">
    $("#search").click(function () {
        location.replace("/admin_back/productList.action?typeId=" + $("#typeId").val() + "&keyword=" + $("#keyword").val());
    });
    $("[name='toUpdate']").click(function () {
        var id = $(this).attr("ids");
        var burl = document.URL;
        window.location.href = "/admin_back/toUpdate.action?id=" + id + "&backUrl=" + burl;
    });
    $("[name='upSpellbuyproduct']").click(function () {
        var id = $(this).attr("id");
        $.ajax({
            url: "/admin_back/upSpellbuyproduct.action",
            type: "post",
            data: "id=" + id,
            beforeSend: function () {
                $("#" + id).html('操作中');
            },
            success: function (data) {
                if (data == "success") {
                    $("#" + id).html("[已上架]").attr("name", "");
                }
            }
        });
    });
    $("[name='downSpellbuyproduct']").click(function () {
        var id = $(this).attr("id");
        $.ajax({
            url: "/admin_back/downSpellbuyproduct.action",
            type: "post",
            data: "id=" + id,
            beforeSend: function () {
                $("#" + id).html('操作中');
            },
            success: function (data) {
                if (data == "success") {
                    $("#" + id).html("[已下架]").attr("name", "");
                }
            }
        });
    });
    $("[name='hotProduct']").live("click", function () {
        var id = $(this).attr("pid");
        $.ajax({
            url: "/admin_back/hotProduct.action",
            type: "post",
            data: "id=" + id,
            beforeSend: function () {
                $("[pid='" + id + "']").html('操作中');
            },
            success: function (data) {
                if (data == "success") {
                    $("[pid='" + id + "']").html("[下架热门商品]").attr("name", "downHotProduct").css({"color": "#FF0000"});
                }
            }
        });
    });
    $("[name='downHotProduct']").live("click", function () {
        var id = $(this).attr("pid");
        $.ajax({
            url: "/admin_back/downHotProduct.action",
            type: "post",
            data: "id=" + id,
            beforeSend: function () {
                $("[pid='" + id + "']").html('操作中');
            },
            success: function (data) {
                if (data == "success") {
                    $("[pid='" + id + "']").html("[设置热门商品]").attr("name", "hotProduct").css({"color": "#3380FF"});
                }
            }
        });
    });
</script>
</body>
</html>
