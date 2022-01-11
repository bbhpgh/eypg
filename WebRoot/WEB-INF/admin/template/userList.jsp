<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    ResourceBundle url = ResourceBundle.getBundle("config");
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>1元拍购_管理中心</title>
    <link href="/admin_css/global.css" rel="stylesheet" type="text/css"/>
    <link href="/admin_css/admin_style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="/ueditor/editor_all.js"></script>
    <script language="javascript" type="text/javascript"
            src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div class="header lr10"><a href="/admin_back/userListAll.action">会员列表</a><span class="span_fenge lr5">|</span><a
        href="/admin_back/toAddUser.action">添加会员</a><span class="span_fenge lr5">|</span><a href="#">会员福利配置</a><span
        class="span_fenge lr5">|</span></div>
<div class="bk10"></div>


<div class="table-list lr10">
    <!--start-->
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th align="center">UID</th>
            <th align="center">用户名</th>
            <th align="center">邮箱</th>
            <th align="center">手机</th>
            <th align="center">余额</th>
            <th align="center">福分</th>
            <th align="center">经验值</th>
            <th align="center">登陆地址、IP</th>
            <th align="center">注册时间</th>
            <th align="center">最新登录时间</th>
            <th align="center">管理</th>
        </tr>
        </thead>
        <tbody>

        <s:iterator value="userList" var="users" status="st">
            <tr class="gradeA even">
                <td align="center">${users.userId }</td>
                <td align="center">${users.userName }</td>
                <td align="center">${users.mail }</td>
                <td align="center">${users.phone }</td>
                <td align="center">${users.balance }</td>
                <td align="center">${users.commissionPoints }</td>
                <td align="center">${users.experience }</td>
                <td align="center">${users.ipLocation }(${users.ipAddress })</td>
                <td align="center">${users.oldDate }</td>
                <td align="center">${users.newDate }</td>
                <td align="center">
                    <a href="javascript:alert('无权修改');"><input type="submit" value="修改" class="button"/></a>
                    <input type="submit" value="删除" class="button"/></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
${pageString }
</body>
</html>
