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
<div class="bk10"></div>
<div class="table-list lr10">
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="5%">ID</th>
            <th width="5%">提现时间</th>
            <th width="5%">提现金额</th>
            <th width="8%">银行名称</th>
            <th width="8%">开户支行</th>
            <th width="5%">开 户 人</th>
            <th width="8%">银行帐号</th>
            <th width="5%">联系电话</th>
            <th width="5%">用户ID</th>
            <th width="5%">状态</th>
            <th width="5%">操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="applymentionList" var="applymentions" status="st">
            <tr align="center">
                <td align="center">${applymentions.id }</td>
                <td align="center">${applymentions.date }</td>
                <td align="center">${applymentions.money }</td>
                <td align="center">${applymentions.bankName }</td>
                <td align="center">${applymentions.bankSubbranch }</td>
                <td align="center">${applymentions.bankUser }</td>
                <td align="center">${applymentions.bankNo }</td>
                <td align="center">${applymentions.phone }</td>
                <td align="center">${applymentions.userId }</td>
                <td align="center">未处理</td>
                <td align="center"><a href="javascript:alert('无权处理');">[处理]</a></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
${pageString }
</body>
</html>
