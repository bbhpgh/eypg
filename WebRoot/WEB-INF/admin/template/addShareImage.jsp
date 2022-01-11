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
<div class="bk10"></div>
<div class="table_form lr10" style="">
    <form action="/admin_back/addShareImage.action" method="post" enctype="multipart/form-data"
          style="border-radius: 0px;" class="form-horizontal group-border-dashed">
        <input type="hidden" name="id" value="${id }"/>
        <table width="100%" cellspacing="0" cellpadding="0" style="">
            <tbody style="">
            <tr>
                <td align="left"><font color="red">*</font>选择图片：</td>
                <td>
                    <div class="col-sm-3" style="float: none;margin: 0;padding: 5px;">
                        <input type="file" name="Filedata" class="form-control" accept="image/*"/>
                    </div>
                    <input type="button" value="增加" class="button" id="addButton"/>
                </td>
            </tr>
            <tr>
                <td align="left"><font color="red">*</font>已传图片：</td>
                <td>
                    <s:iterator value="shareimageList" var="shareimages">
                        <img name="_small" id="${shareimages.images  }"
                             alt="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/Big/${shareimages.images }"
                             src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/Small/${shareimages.images }"/>
                    </s:iterator>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="bk10"></div>
        <input type="submit" value="提交" id="submit" class="button"/>
        <input type="button" value="返回" class="button" onclick="history.go(-1)"/>
    </form>
</div>
<script type="text/javascript">
    $("#addButton").click(function () {
        $(".col-sm-3:last").after("<div class=\"col-sm-3\" style=\"float: none;margin: 0;padding: 5px;\"><input type=\"file\" name=\"Filedata\"  class=\"form-control\" accept=\"image/*\" /></div>");
    });
</script>
</body>
</html>
