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
    <script type="text/javascript">

    </script>
</head>

<body>
<div class="cl-mcont">
    <form action="/admin_back/addShareImage.action" method="post" enctype="multipart/form-data"
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
<div id="thumbnails" style="width: 310px;height: 310px;"><img id="mid" src="/Images/pixel.gif"/></div>
<br/>
<s:iterator value="shareimageList" var="shareimages">
    <img name="_small" id="${shareimages.images  }" alt="<%=url.getString("img")%>/UserPost/Big/${shareimages.images }"
         src="<%=url.getString("img")%>/UserPost/Small/${shareimages.images }"/>
</s:iterator>
<button class="btn btn-primary btn-flat" onclick="history.go(-1)" type="button">返回</button>
<script type="text/javascript">
    $("#addButton").click(function () {
        $(".col-sm-3:last").after("<div class=\"col-sm-3\" style=\"float: none;margin: 0;padding: 5px;\"><input type=\"file\" name=\"Filedata\"  class=\"form-control\" accept=\"image/*\" /></div>");
    });
    $("[name='_small']").hover(function () {
        $("#mid").attr("src", $(this).attr("alt"));
    }, function () {
        $("#mid").attr("src", "/Images/pixel.gif");
    })
</script>
</body>
</html>
