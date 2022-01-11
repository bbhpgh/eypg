<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
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
<div class="header-title lr10"><b>系统邮箱设置</b></div>
<div class="bk10"></div>
<div class="table_form lr10">
    <form id="myform" method="post" action="/admin_back/doSeoSet.action">
        <input type="hidden" name="sysConfigure.id" value="${sysConfigure.id }"/>
        <input type="hidden" name="sysConfigure.imgUrl" value="${sysConfigure.imgUrl }"/>
        <input type="hidden" name="sysConfigure.skinUrl" value="${sysConfigure.skinUrl }"/>
        <input type="hidden" name="sysConfigure.wwwUrl" value="${sysConfigure.wwwUrl }"/>
        <input type="hidden" name="sysConfigure.domain" value="${sysConfigure.domain }"/>
        <input type="hidden" name="sysConfigure.saitName" value="${sysConfigure.saitName }"/>
        <input type="hidden" name="sysConfigure.shortName" value="${sysConfigure.shortName }"/>
        <input type="hidden" name="sysConfigure.saitTitle" value="${sysConfigure.saitTitle }"/>
        <input type="hidden" name="sysConfigure.saitLogo" value="${sysConfigure.saitLogo }"/>
        <input type="hidden" name="sysConfigure.keyword" value="${sysConfigure.keyword }"/>
        <input type="hidden" name="sysConfigure.description" value="${sysConfigure.description }"/>
        <input type="hidden" name="sysConfigure.tenpayPartner" value="${sysConfigure.tenpayPartner }"/>
        <input type="hidden" name="sysConfigure.tenpayKey" value="${sysConfigure.tenpayKey }"/>
        <input type="hidden" name="sysConfigure.tenpayStatus" value="${sysConfigure.tenpayStatus }"/>
        <input type="hidden" name="sysConfigure.alipayPartner" value="${sysConfigure.alipayPartner }"/>
        <input type="hidden" name="sysConfigure.alipayKey" value="${sysConfigure.alipayKey }"/>
        <input type="hidden" name="sysConfigure.alipayMail" value="${sysConfigure.alipayMail }"/>
        <input type="hidden" name="sysConfigure.alipayStatus" value="${sysConfigure.alipayStatus }"/>
        <input type="hidden" name="sysConfigure.yeepayKey" value="${sysConfigure.yeepayKey }"/>
        <input type="hidden" name="sysConfigure.yeepayPartner" value="${sysConfigure.yeepayPartner }"/>
        <input type="hidden" name="sysConfigure.yeepayStatus" value="${sysConfigure.yeepayStatus }"/>
        <input type="hidden" name="sysConfigure.icp" value="${sysConfigure.icp }"/>
        <input type="hidden" name="sysConfigure.serviceQq" value="${sysConfigure.serviceQq }"/>
        <input type="hidden" name="sysConfigure.serviceTel" value="${sysConfigure.serviceTel }"/>
        <input type="hidden" name="sysConfigure.qqAppId" value="${sysConfigure.qqAppId }"/>
        <input type="hidden" name="sysConfigure.qqAppKey" value="${sysConfigure.qqAppKey }"/>
        <input type="hidden" name="sysConfigure.qqAppStatus" value="${sysConfigure.qqAppStatus }"/>
        <table width="100%" class="lr10">
            <tbody>
            <tr>
                <td width="100">邮件发送模式</td>
                <td>
                    <input type="radio" value="1" disabled="disabled" checked="checked"/> SMTP 函数发送
                </td>
            </tr>
            <tr>
                <td>邮件服务器：</td>
                <td><input type="text" value="smtp.ym.163.com,smtp.sina.com.cn" size="30" name="server"
                           class="input-text" disabled="disabled"/></td>
            </tr>
            <tr>
                <td>系统邮箱：</td>
                <td><input type="text" value="${sysConfigure.mailName }" size="30" name="sysConfigure.mailName"
                           class="input-text"/></td>
            </tr>
            <tr>
                <td>系统邮箱密码：</td>
                <td><input type="text" value="${sysConfigure.mailPwd }" size="30" name="sysConfigure.mailPwd"
                           class="input-text"/></td>
            </tr>
            <tr>
                <td>发送人名称：</td>
                <td><input type="text" value="${sysConfigure.saitName }" size="30" disabled="disabled"
                           class="input-text wid80"/>默认为网站名称
                </td>
            </tr>
            <tr>
                <td>发送编码：</td>
                <td><input type="text" value="utf-8" name="big" class="input-text wid80" disabled="disabled"/></td>
            </tr>
            <tr>
                <td>测试邮件：</td>
                <td>
                    <input type="text" size="30" class="input-text" id="testMailName"/>
                    <input type="button" class="button" id="sendTestMail" value=" 测试邮件 "/>
                </td>
            </tr>
            <tr>
                <td width="100"></td>
                <td><input type="submit" value=" 提交 " class="button"/>
                    <input type="button" value="返回" class="button" onclick="history.go(-1)"/></td>
            </tr>
            </tbody>
        </table>

    </form>
</div>
<script type="text/javascript">
    var b = function (w) {
        var x = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        return x.test(w);
    };
    $("#sendTestMail").click(function () {
        var G = $("#testMailName").val();
        if (!(b(G))) {
            alert("请输入正常的邮箱地址！");
            return false;
        }
        $.ajax({
            url: "/admin_back/doTestMail.action",
            type: "POST",
            data: "userName=" + G,
            success: function (data) {
                if (data == "mailError") {
                    alert("请先配置系统邮箱及系统邮箱密码！");
                } else if (data == "success") {
                    alert("发送成功！请进入邮箱查收！");
                } else {
                    alert("发送失败，请核对邮箱！");
                }
            },
            error: function () {
                alert("请求异常，请联系管理员！");
            }
        });
    });

</script>
</body>
</html>
