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
<div align="center" class="header">
    <h3>生成卡密</h3>
</div>
<div class="bk10"></div>
<div class="table_form lr10" style="">
    <table width="100%" cellspacing="0" cellpadding="0" style="">
        <tbody style="">
        <tr>
            <td align="left" width="150px;"><font color="red">*</font>生成卡密数量：</td>
            <td align="left"><input type="text" id="id" class="input-text wid100 bg"/>
            </td>
        </tr>
        <tr>
            <td align="left" width="150px;"><font color="red">*</font>生成卡密面值：</td>
            <td align="left">
                <input type="text" class="input-text wid100" id="pwd"/>
            </td>
        </tr>
        <tr>
            <td align="left"></td>
            <td align="left">
                <input type="submit" value="确定生成" id="search" class="button"/>
                <input type="button" value="返回" class="button" onclick="history.go(-1)"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script language="javascript" type="text/javascript">
    $("#search").click(function () {
        if ($("#id").val() == "") {
            alert("输入你要生成的卡密数量");
            return false;
        } else if ($("#pwd").val() == "") {
            alert("输入你要生成的卡密面值");
            return false;
        } else {
            var url = "/admin_back/doCard.action?id=" + $("#id").val() + "&pwd=" + $("#pwd").val();
            $.ajax({
                url: url,
                type: "POST",
                beforeSend: function () {
                    $("#search").text('生成中...').attr("disabled", "disabled");
                },
                cache: false,
                success: function (msg) {
                    if (msg != "error") {
                        alert("操作成功！");
                        location.replace("/admin_back/cardList.action");
                    } else {
                        alert("操作失败！");
                        location.replace("/admin_back/cardList.action");
                    }
                },
                error: function () {
                    alert("网络错误，请稍后再试！");
                }
            });
        }
    });
</script>
</body>
</html>
