<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>1元拍购_管理中心</title>
    <link href="/admin_css/global.css" rel="stylesheet" type="text/css"/>
    <link href="/admin_css/admin_style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="/ueditor/editor_all.js"></script>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div class="bk10"></div>
<div class="table_form lr10" style="">
    <table width="100%" cellspacing="0" cellpadding="0" style="">
        <tbody style="">
        <c:choose>
        <c:when test="${shareinfo.uid!=null}">
        <form action="/admin_back/updateShare.action" method="post" enctype="multipart/form-data">
            <input type="hidden" name="shareinfo.uid" value="${shareinfo.uid }"/>
            <input type="hidden" name="shareinfo.upCount" value="${shareinfo.upCount }"/>
            <input type="hidden" name="shareinfo.replyCount" value="${shareinfo.replyCount }"/>
            <input type="hidden" name="shareinfo.productId" value="${shareinfo.productId }"/>
            <input type="hidden" name="shareinfo.userId" value="${shareinfo.userId }"/>
            <tr>
                <td align="right" style="width:120px"><font color="red">*</font>审核：</td>
                <td>
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
                </td>
            </tr>
            <tr>
                <td align="right" style="width:120px"><font color="red">*</font>奖励：</td>
                <td>
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
                </td>
            </tr>
            </c:when>
            <c:otherwise>
            <form action="/admin_back/addShare.action" method="post" enctype="multipart/form-data">
                <input type="hidden" name="shareinfo.productId" value="${id }"/>
                <input type="hidden" name="shareinfo.userId" value="${userId }"/>
                </c:otherwise>
                </c:choose>
                <tr>
                    <td align="right" style="width:120px"><font color="red">*</font>晒单标题：</td>
                    <td>
                        <input type="text" class="input-text wid400 bg" name="shareinfo.shareTitle"
                               value="${shareinfo.shareTitle }"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="width:120px"><font color="red">*</font>晒单日期：</td>
                    <td>
                        <c:choose>
                            <c:when test="${announcedTime!=null}">
                                <input type="text" class="input-text wid400 bg" name="shareinfo.shareDate"
                                       value="${fn:substring(announcedTime,0,19) }"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="input-text wid400 bg" class="form-control"
                                       name="shareinfo.shareDate" value="${fn:substring(shareinfo.shareDate,0,19) }"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="width:120px"><font color="red">*</font>晒单内容：</td>
                    <td>
                        <textarea id="editor" style="height: 500px;width: 500px;" name="shareinfo.shareContent"
                                  cols="60" rows="10">${shareinfo.shareContent }</textarea>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="width:120px"><font color="red">*</font>晒单图片：</td>
                    <td>
                        <s:iterator value="shareimageList" var="shareimages">
                            <img name="_small" id="${shareimages.images  }"
                                 alt="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/Big/${shareimages.images }"
                                 src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/Small/${shareimages.images }"/>
                        </s:iterator>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="width:120px"></td>
                    <td>

                    </td>
                </tr>
        </tbody>
        </from>
    </table>
</div>
<input type="submit" value="提交" id="submit" class="button"/>
<input type="button" value="返回" class="button" onclick="history.go(-1)"/>
<script type="text/javascript">
    $("#submit").click(function () {
        if ($("[name='shareinfo.shareTitle']").val() == "") {
            alert("请输入标题！");
            return false;
        }
        if ($("[name='shareinfo.shareContent']").val() == "") {
            alert("请输入晒单内容！");
            return false;
        }
        $("form:first").submit();
    });
</script>

</body>
</html>
