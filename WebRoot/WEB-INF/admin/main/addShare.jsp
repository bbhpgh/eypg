<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script type="text/javascript" src="/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="/ueditor/editor_all.js"></script>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div align="center" style="padding: 15px;">
    <c:choose>
    <c:when test="${shareinfo.uid!=null}">
    <form action="/admin_back/updateShare.action" method="post" enctype="multipart/form-data">
        <input type="hidden" name="shareinfo.uid" value="${shareinfo.uid }"/>
        <input type="hidden" name="shareinfo.upCount" value="${shareinfo.upCount }"/>
        <input type="hidden" name="shareinfo.replyCount" value="${shareinfo.replyCount }"/>
        <input type="hidden" name="shareinfo.productId" value="${shareinfo.productId }"/>
        <input type="hidden" name="shareinfo.userId" value="${shareinfo.userId }"/>

        <div class="col-sm-6">
            <label class="col-sm-2 control-label" for="inputEmail3">审核</label>
            <div class="col-sm-3">
                <select name="shareinfo.status" class="form-control" style="width:180px;">
                    <c:if test="${shareinfo.status==0}">
                        <option value="0" selected="selected">等待审核</option>
                        <option value="1">未审核通过</option>
                        <option value="2">审核通过</option>
                    </c:if>
                    <c:if test="${shareinfo.status==1}">
                        <option value="0">等待审核</option>
                        <option value="1" selected="selected">未审核通过</option>
                        <option value="2">审核通过</option>
                    </c:if>
                    <c:if test="${shareinfo.status==2}">
                        <option value="0">等待审核</option>
                        <option value="1">未审核通过</option>
                        <option value="2" selected="selected">审核通过</option>
                    </c:if>
                </select>
                <select name="shareinfo.reward" class="form-control" style="width:180px;">
                    <c:choose>
                        <c:when test="${shareinfo.reward==0}">
                            <option value="0" selected="selected">未奖励</option>
                            <option value="1">已奖励</option>
                        </c:when>
                        <c:otherwise>
                            <option value="0">未奖励</option>
                            <option value="1" selected="selected">已奖励</option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
        </div>
        </c:when>
        <c:otherwise>
        <form action="/admin_back/addShare.action" method="post" enctype="multipart/form-data">
            <input type="hidden" name="shareinfo.productId" value="${id }"/>
            <input type="hidden" name="shareinfo.userId" value="${userId }"/>
            </c:otherwise>
            </c:choose>

            <div style="float: left; width: 100%;">
                <div class="col-sm-6">
                    <label class="col-sm-2 control-label" for="inputEmail3">标题</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="请输入标题" class="form-control" name="shareinfo.shareTitle"
                               value="${shareinfo.shareTitle }"/>
                    </div>
                    <label class="col-sm-2 control-label" for="inputEmail3">日期</label>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${announcedTime!=null}">
                                <input type="text" placeholder="请输入日期" style="width: 180px;float: left;"
                                       class="form-control" name="shareinfo.shareDate"
                                       value="${fn:substring(announcedTime,0,19) }"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" placeholder="请输入日期" style="width: 180px;float: left;"
                                       class="form-control" name="shareinfo.shareDate"
                                       value="${fn:substring(shareinfo.shareDate,0,19) }"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <p style="float: left; padding: 50px;"><textarea id="editor" name="shareinfo.shareContent" cols="60"
                                                             rows="10">${shareinfo.shareContent }</textarea></p>
        </form>
</div>
<div id="thumbnails" style="width: 310px;height: 310px;"><img id="mid" src="/Images/pixel.gif"/></div>
<div align="center" style="float: right; padding: 20px;">
    <s:iterator value="shareimageList" var="shareimages">
        <img name="_small" id="${shareimages.images  }"
             alt="<%=url.getString("img")%>/UserPost/Big/${shareimages.images }"
             src="<%=url.getString("img")%>/UserPost/Small/${shareimages.images }"/>
    </s:iterator>
</div>
<button class="btn btn-primary btn-flat" id="submit" type="submit">提交</button>
<button class="btn btn-primary btn-flat" onclick="history.go(-1)" type="button">返回</button>
<script type="text/javascript">
    $("#submit").click(function () {
        if ($("[name='shareinfo.shareTitle']").val() == "") {
            alert("请输入标题！");
            return false;
        }
        $("form:first").submit();
    });
    $("[name='_small']").hover(function () {
        $("#mid").attr("src", $(this).attr("alt"));
    }, function () {
        $("#mid").attr("src", "/Images/pixel.gif");
    })
</script>

</body>
</html>
