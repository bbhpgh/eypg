<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    ResourceBundle url = ResourceBundle.getBundle("config");
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="admin_main"/>
    <title>1元拍购_管理中心</title>
    <script type="text/javascript" src="/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="/ueditor/editor_all.js"></script>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div class="block-flat">
    <div class="content">
        <div class="form-group">
            <label class="col-sm-3 control-label">用户昵称</label>
            <div class="col-sm-6">
                <input type="text" placeholder="请输入商品名称" class="form-control" name="product.productName"
                       value="${product.productName }"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">商品价格</label>
            <div class="input-group" style="width: 180px; float: left;padding-left: 15px;">
                <span class="input-group-addon">￥</span>
                <input type="text" class="form-control" name="product.productPrice" value="${product.productPrice }"/>
                <span class="input-group-addon">.00</span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">商品标题</label>
            <div class="col-sm-6">
                <input type="text" placeholder="请输入商品标题" class="form-control" name="product.productTitle"
                       value="${product.productTitle }"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">商品类型</label>
            <div class="col-sm-6" style="width: 180px;">
                <select name="product.productType" class="form-control">
                    <option value="">请选择</option>
                    <s:iterator value="productTypeList" var="productTypes">
                        <c:choose>
                            <c:when test="${product.productType==productTypes.typeId }">
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
        </div>
        <button class="btn btn-success" type="submit" id="submit"><i class="fa fa-cloud-download"></i> 提交</button>
        <button class="btn btn-primary btn-flat" onclick="history.go(-1)" type="button">返回</button>
        </form>
    </div>
</div>
<script type="text/javascript">
    $("#submit").click(function () {
        if ($("[name='product.productName']").val() == "") {
            alert("请输入商品名称！");
            return false;
        }
        if ($("[name='product.productPrice']").val() == "") {
            alert("请输入商品价格！");
            return false;
        }
        if ($("[name='product.productTitle']").val() == "") {
            alert("请输入商品标题！");
            return false;
        }
        if ($("[name='product.productType']").val() == "") {
            alert("请选择商品类型！");
            return false;
        }
        if ($("[name='product.productId']").val() == "") {
            if ($("[name='myFile']").val() == "") {
                alert("请给该商品添一第主图片！");
                return false;
            }
        }
        $("form:first").submit();
    });
</script>

</body>
</html>
