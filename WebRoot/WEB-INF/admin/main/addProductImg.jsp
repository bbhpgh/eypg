<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    ResourceBundle url = ResourceBundle.getBundle("config");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="admin_main"/>
    <title>1元拍购_管理中心</title>
    <link href="/admin_css/dropzone.css" rel="stylesheet"/>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>
<body>
<div class="cl-mcont">
    <form action="/admin_back/addProductImg.action" method="post" enctype="multipart/form-data"
          style="border-radius: 0px;" class="form-horizontal group-border-dashed">
        <input type="hidden" name="id" value="${id }"/>
        <div class="form-group">
            <label class="col-sm-1 control-label">选择图片</label>
            <div class="col-sm-3" style="float: none;margin: 0;padding: 5px;">
                <input type="file" name="Filedata" class="form-control" accept="image/*"/>
            </div>
            <button class="btn btn-default" id="addButton" type="button">增加</button>
        </div>
        <button class="btn btn-success" type="submit" id="submit"><i class="fa fa-cloud-download"></i> 上传</button>
        <button class="btn btn-primary btn-flat" onclick="history.go(-1)" type="button">返回</button>
    </form>
</div>
<div>
    <s:iterator var="productimages" value="productimageList">
        <img src="<%=url.getString("img")%>/productImg/imagezoom/${productimages.piProductId }/${productimages.image }_mid${productimages.imageType }"/>
    </s:iterator>
</div>

<script type="text/javascript">
    $("#addButton").click(function () {
        $(".col-sm-3:last").after("<div class=\"col-sm-3\" style=\"float: none;margin: 0;padding: 5px;\"><input type=\"file\" name=\"Filedata\"  class=\"form-control\" accept=\"image/*\" /></div>");
    });
</script>
</body>
</html>
