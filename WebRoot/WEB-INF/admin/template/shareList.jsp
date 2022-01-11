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
    <a href="/admin_back/shareList.action?typeId=new20">最新晒单</a><span class="span_fenge lr5">|</span>
    <a href="/admin_back/shareList.action?typeId=hot20">人气晒单</a><span class="span_fenge lr5">|</span>
    <a href="/admin_back/shareList.action?typeId=reply20">评论最多</a><span class="span_fenge lr5">|</span>
    <a href="/admin_back/shareList.action?typeId=hot20&id=-1">暂未晒单</a><span class="span_fenge lr5">|</span>
    <a href="/admin_back/shareList.action?typeId=hot20&id=0">等待审核</a><span class="span_fenge lr5">|</span>
    <a href="/admin_back/shareList.action?typeId=hot20&id=1">未审核通过</a><span class="span_fenge lr5">|</span>
    <a href="/admin_back/shareList.action?typeId=hot20&id=2">审核通过</a><span class="span_fenge lr5">|</span>
</div>
<div class="bk10"></div>
<div class="table-list lr10">
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="15%">标题</th>
            <th width="15%">内容</th>
            <th width="2%">是否奖励</th>
            <th width="5%">羡慕嫉妒恨</th>
            <th width="2%">回复</th>
            <th width="5%">晒单时间</th>
            <th width="5%">晒单人</th>
            <th width="5%">状态</th>
            <th width="5%">操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="ShareJSONList" var="ShareJSONLs">
            <tr style="height: 30px;">
                <td align="left">${ShareJSONLs.shareTitle }</td>
                <td style="height: 30px;overflow: hidden;float: left;"
                    title="${ShareJSONLs.shareContent }">${ShareJSONLs.shareContent }</td>
                <td>
                    <c:if test="${ShareJSONLs.reward == 1}">
                        已奖励
                    </c:if>
                    <c:if test="${ShareJSONLs.reward == 0}">
                        未奖励
                    </c:if>
                </td>
                <td>${ShareJSONLs.upCount }</td>
                <td>${ShareJSONLs.replyCount }</td>
                <td>${ShareJSONLs.shareDate }</td>
                <td>${ShareJSONLs.userName }</td>
                <td>
                    <c:if test="${ShareJSONLs.status == -1}">
                        暂未晒单
                    </c:if>
                    <c:if test="${ShareJSONLs.status == 0}">
                        等待审核
                    </c:if>
                    <c:if test="${ShareJSONLs.status == 1}">
                        未审核通过，请重新修改晒单信息
                    </c:if>
                    <c:if test="${ShareJSONLs.status == 2}">
                        审核通过，奖励10元
                    </c:if>
                </td>
                <td><a href="/admin_back/toAddShareImage.action?id=${ShareJSONLs.uid }">[添加晒单图]</a> <a
                        href="/admin_back/toUpdateShare.action?id=${ShareJSONLs.uid }">[审核]</a></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
${pageString }
</body>
</html>
