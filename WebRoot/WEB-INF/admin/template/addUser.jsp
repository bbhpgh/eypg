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
<div class="header lr10"><a href="/admin_back/userListAll.action">会员列表</a><span class="span_fenge lr5">|</span><a
        href="/admin_back/toAddUser.action">添加会员</a><span class="span_fenge lr5">|</span><a href="#">会员福利配置</a><span
        class="span_fenge lr5">|</span></div>
<div class="bk10"></div>

<div class="table_form lr10">
    <!--start-->
    <form enctype="multipart/form-data" method="post" action="/admin_back/doAddUser.action" name="myform"
          enctype="multipart/form-data">
        <input type="hidden" name="user.userType" value="0"/>
        <table width="100%" cellspacing="0">
            <tbody>
            <tr>
                <td width="120" align="right">昵称：</td>
                <td><input type="text" class="input-text" value="${user.userName }" name="user.userName"/></td>
            </tr>
            <tr>
                <td width="120" align="right">邮箱：</td>
                <td><input type="text" class="input-text" value="${user.mail }" name="user.mail"/></td>
            </tr>
            <tr>
                <td width="120" align="right">手机：</td>
                <td><input type="text" class="input-text" value="${user.phone }" name="user.phone"/></td>
            </tr>
            <tr>
                <td width="120" align="right">密码：</td>
                <td><input type="text" class="input-text" value="${user.userPwd }" name="user.userPwd"/></td>
            </tr>
            <tr>
                <td width="120" align="right">账户余额：</td>
                <td><input type="text" class="input-text" value="${user.balance }" name="user.balance"/>元</td>
            </tr>
            <tr>
                <td width="120" align="right">经验值：</td>
                <td><input type="text" class="input-text" value="${user.experience }" name="user.experience"/></td>
            </tr>
            <tr>
                <td width="120" align="right">邀请人：</td>
                <td><input type="text" class="input-text" value="${user.invite }" name="user.invite"/>(填写邀请人用户ID)</td>
            </tr>
            <tr>
                <td width="120" align="right">佣&nbsp;&nbsp;金：</td>
                <td><input type="text" class="input-text" value="${user.commissionBalance }"
                           name="user.commissionBalance"/></td>
            </tr>
            <tr>
                <td width="120" align="right">福&nbsp;&nbsp;分：</td>
                <td><input type="text" class="input-text" value="${user.commissionPoints }"
                           name="user.commissionPoints"/></td>
            </tr>
            <tr>
                <td width="120" align="right">签名：</td>
                <td><textarea name="user.signature" style="width:400px;height:100px;">${user.signature }</textarea></td>
            </tr>
            <tr>
                <td width="120" align="right"></td>
                <td>
                    <input type="submit" value="提交" name="submit" class="button"/>
                    <input type="button" value="返回" class="button" onclick="history.go(-1)"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
<script type="text/javascript">
    $("[name=submit]").click(function () {
        if ($("[name=user.mail]").val() == "" && $("[name=user.phone]").val() == "") {
            alert("邮箱或手机必须输入一个！");
            return false;
        } else if ($("[name=user.userPwd]").val() == "") {
            alert("请输入密码！");
            return false;
        } else {
            $("form:first").submit();
        }
    });
</script>
</body>
</html>
