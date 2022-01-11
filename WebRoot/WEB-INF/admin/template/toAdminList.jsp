<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>1元拍购_管理中心</title>
    <link href="/admin_css/global.css" rel="stylesheet" type="text/css"/>
    <link href="/admin_css/admin_style.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div class="header-title lr10"><b>管理员管理</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="10%">序号</th>
            <th width="10%" align="left">用户名</th>
            <th width="10%" align="left">所属角色</th>
            <th width="15%" align="left">最后登录IP所在地</th>
            <th width="10%" align="left">最后登录时间</th>
            <th width="15%">管理操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator var="users" value="userList">
            <tr>
                <td align="center">${users.userId }</td>
                <td>${users.userName }</td>
                <td>超级管理员</td>
                <td>${users.oldIpAddress }</td>
                <td>${users.oldDate }</td>
                <td align="center">
                    <a href="/admin_back/updateAdminPwd.action">修改</a>
                    <span class="span_fenge lr5">|</span>
                    <font color="#cccccc">删除</font>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
</body>
</html>
