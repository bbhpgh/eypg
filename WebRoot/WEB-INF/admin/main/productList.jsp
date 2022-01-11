<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    ResourceBundle url = ResourceBundle.getBundle("config");
%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="admin_main"/>
    <title>1元拍购_管理中心</title>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<input type="hidden" value="${resultCount }" id="resultCount"/>
<input type="hidden" id="backUrl" name="backUrl" value=""/>
<div align="center" style="padding: 10px;">

    <div class="form-group">
        <label class="col-sm-3 control-label" style="width: 100px;">商品类型</label>
        <div class="col-sm-3">
            <select name="products.productType" id="typeId" class="form-control" style="width:180px;">
                <option value="">请选择</option>
                <s:iterator value="productTypeList" var="productTypes">
                    <c:choose>
                        <c:when test="${products.productType==productTypes.typeId }">
                            <option selected="selected"
                                    value="${productTypes.typeId }">${productTypes.typeName }</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${productTypes.typeId }">${productTypes.typeName }</option>
                        </c:otherwise>
                    </c:choose>
                </s:iterator>
            </select>
        </div>
        <label class="col-sm-3 control-label" style="width: 100px;">商品名称</label>
        <div class="col-sm-3">
            <input type="text" value="" name="product.productName" id="keyword" class="form-control"
                   placeholder="请输入商品名称"/>
        </div>
    </div>
    <button class="btn btn-primary btn-flat" id="search" type="button">查询</button>
    <div class="content">
        <table border="1px;" width="1024px;">
            <tr>
                <th width="60px;">商品ID</th>
                <th width="400px;">商品名称</th>
                <th width="60px;">商品价格</th>
                <th width="60px;">商品图片</th>
                <th width="60px;">商品类型</th>
                <th width="160px;">商品操作</th>
            </tr>
            <s:iterator value="productList" var="products">
                <tr>
                    <td>${products.productId } </td>
                    <td>${products.productName } </td>
                    <td>${products.productPrice } </td>
                    <td><img width="50px;" height="50px;" src="<%=url.getString("img")%>/${products.headImage } "/></td>
                    <td>
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
                    <td><a name="toUpdate" ids="${products.productId }" href="javascript:;">[编辑]</a> <a
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
                            <a name="downHotProduct" pid="${products.productId }" href="javascript:;"
                               style="color: red;">[下架热门商品]</a>
                        </c:if>
                        <c:if test="${products.attribute71!='hot'}">
                            <a name="hotProduct" pid="${products.productId }" href="javascript:;">[设置热门商品]</a>
                        </c:if>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </div>
    ${pageString }
    <br/>
</div>
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
