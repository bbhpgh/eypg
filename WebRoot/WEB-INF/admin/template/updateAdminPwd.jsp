<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.pojo.User" %>
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
<div class="header-title lr10"><b>修改密码</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
    <table width="100%">
        <tbody>
        <tr>
            <td width="80">用户名</td>
            <td><input type="text" disabled="disabled" class="input-text"
                       value="<%=((User)request.getSession().getAttribute("admin")).getUserName() %>"/></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" class="input-text" id="password" value=""/></td>
        </tr>
        <tr>
            <td>确认密码</td>
            <td><input type="password" class="input-text" id="pwdconfirm"/></td>
        </tr>
        <tr>
            <td>所属角色</td>
            <td>
                <select name="mid">
                    <option value="1">超级管理员</option>
                </select>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="bk10"></div>
    <input type="button" value=" 提交 " id="dosubmit" class="button"/>
    <input type="button" value="返回" class="button" onclick="history.go(-1)"/>
</div>
<script type="text/javascript">
    $("#dosubmit").click(function () {
        if ($("#password").val() == "") {
            alert("密码不能为空！");
            return false;
        } else if ($("#pwdconfirm").val() == "") {
            alert("请输入确认密码！");
            return false;
        } else if ($("#password").val() != $("#pwdconfirm").val()) {
            alert("密码输入不一致，请重新输入！");
            $("#password").val("");
            $("#pwdconfirm").val("");
            return false;
        } else {
            $.ajax({
                url: "/admin_back/doUpdateAdminPwd.action",
                type: "POST",
                data: "pwd=" + $("#pwdconfirm").val(),
                success: function (data) {
                    if (data == "success") {
                        alert("操作成功！");
                        window.location.href = "/admin_back/toAdminList.action";
                    }
                },
                error: function () {
                    alert("操作失败，请联系管理员!");
                }
            });
        }
    });
</script>
</body>
</html>
