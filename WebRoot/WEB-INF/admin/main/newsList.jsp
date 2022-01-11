<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div align="center">
    <a href="/admin_back/toAddNews.action">
        <button class="btn btn-default" type="button">添加新闻</button>
    </a>
    <a href="/admin_back/replaceNews.action">
        <button class="btn btn-default" type="button">更新新闻</button>
    </a>

    <table id="datatable" class="table table-bordered dataTable"
           aria-describedby="datatable_info">
        <thead>
        <tr role="row">
            <th class="sorting_asc" rowspan="1" colspan="1" style="width: 80px;">
                新闻ID
            </th>
            <th class="sorting" rowspan="1" colspan="1" style="width: 100px;">
                新闻标题
            </th>
            <th class="sorting" rowspan="1" colspan="1" style="width: 100px;">
                日期
            </th>
            <th class="sorting" rowspan="1" colspan="1" style="width: 120px;">
                操作
            </th>
        </tr>
        </thead>
        <tbody role="alert" aria-live="polite" aria-relevant="all">
        <s:iterator value="newsList" var="news" status="st">
            <s:if test="#st.odd == true">
                <tr class="gradeA odd">
                    <td>${news.newsId } </td>
                    <td>${news.title } </td>
                    <td>${news.postDate } </td>
                    <td><a href="/admin_back/toUpdateNews.action?id=${news.newsId }">
                        <button class="btn btn-warning btn-flat" type="button">修改</button>
                    </a>
                </tr>
            </s:if>
            <s:else>
                <tr class="gradeA even">
                    <td>${news.newsId } </td>
                    <td>${news.title } </td>
                    <td>${news.postDate } </td>
                    <td><a href="/admin_back/toUpdateNews.action?id=${news.newsId }">
                        <button class="btn btn-warning btn-flat" type="button">修改</button>
                    </a></td>
                </tr>
            </s:else>
        </s:iterator>
        </tbody>
    </table>
    ${pageString }
    <br/>
</div>
</body>
</html>
