<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>1元拍购_管理中心</title>
    <link href="/admin_css/global.css" rel="stylesheet" type="text/css"/>
    <link href="/admin_css/admin_style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/dateinput.css"/>
    <script type="text/javascript" src="/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="/ueditor/editor_all.js"></script>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div class="header lr10">
    <a href="/admin_back/toAddNews.action">添加新闻</a><span class="span_fenge lr5">|</span><a
        href="/admin_back/newsList.action">新闻列表</a><span class="span_fenge lr5">|</span><a
        href="/admin_back/replaceNews.action">更新新闻</a><span class="span_fenge lr5">|</span>
</div>
<div class="bk10"></div>
<div class="table_form lr10" style="">
    <c:choose>
    <c:when test="${news.newsId!=null}">
    <form action="/admin_back/updateNews.action" method="post" enctype="multipart/form-data">
        <input type="hidden" name="news.newsId" value="${news.newsId }"/>
        </c:when>
        <c:otherwise>
        <form action="/admin_back/addNews.action" method="post" enctype="multipart/form-data">
            </c:otherwise>
            </c:choose>
            <table width="100%" cellspacing="0" cellpadding="0" style="">
                <tbody style="">
                <tr>
                    <td align="right"><font color="red">*</font>标题标题：</td>
                    <td><input type="text" class="input-text wid400 bg" name="news.title" value="${news.title }"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">发布时间：</td>
                    <td>
                        <input type="text" class="input-text wid100" id="time" name="news.postDate"
                               value="${news.postDate }"/>
                    </td>
                </tr>
                <tr style="">
                    <td height="300" align="right"><font color="red">*</font>内容详情：</td>
                    <td>
                        <textarea id="editor" name="news.content">${news.content }</textarea>
                        <script type="text/javascript">
                            UE.getEditor('editor');
                        </script>
                    </td>
                </tr>
                <tr height="60px">
                    <td align="right"></td>
                    <td>
                        <input type="submit" value="提交" id="submit" class="button"/>
                        <input type="button" value="返回" class="button" onclick="history.go(-1)"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
</div>
</div>
<script language="javascript" type="text/javascript" src="/js/date.js?data=20131121"></script>
<script language="javascript" type="text/javascript" src="/js/dateinput.js?data=20131121"></script>
<script type="text/javascript">
    $("#submit").click(function () {
        if ($("[name='news.title']").val() == "") {
            alert("请输入新闻标题！");
            return false;
        }
        $("form:first").submit();
    });
    var a = new Date();
    var X = $("#time");
    X.val(a.Format("YYYY-MM-DD")).date_input();
</script>
</body>
</html>
