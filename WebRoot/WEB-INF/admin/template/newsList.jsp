<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
    <a href="/admin_back/toAddNews.action">添加新闻</a><span class="span_fenge lr5">|</span><a
        href="/admin_back/newsList.action">新闻列表</a><span class="span_fenge lr5">|</span><a
        href="/admin_back/replaceNews.action">更新新闻</a><span class="span_fenge lr5">|</span>
</div>
<input type="hidden" value="${resultCount }" id="resultCount"/>
<input type="hidden" id="backUrl" name="backUrl" value=""/>
<div class="bk10"></div>
<div class="table-list lr10">
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th>新闻ID</th>
            <th>新闻标题</th>
            <th>日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="newsList" var="news" status="st">
            <tr>
                <td align="center">${news.newsId }</td>
                <td align="center"><a target="_blank" href="/news/${news.newsId }.html">${news.title } </a></td>
                <td align="center">${news.postDate }</td>
                <td align="center">
                    <a href="/admin_back/toUpdateNews.action?id=${news.newsId }">修改</a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
${pageString }
</body>
</html>
