<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>1元拍购_管理中心</title>
    <link href="/admin_css/global.css" rel="stylesheet" type="text/css"/>
    <link href="/admin_css/admin_style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/dateinput.css"/>
    <script type="text/javascript" src="/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="/ueditor/editor_all.js"></script>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div class="header-title lr10">
    <b>QQ 登陆配置</b>
</div>
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
        <input type="hidden" name="sysConfigure.mailName" value="${sysConfigure.mailName }"/>
        <input type="hidden" name="sysConfigure.mailPwd" value="${sysConfigure.mailPwd }"/>
        <table width="100%" class="lr10">
            <tbody>
            <tr>
                <td width="100">开启QQ登陆</td>
                <td>
                    <c:if test="${sysConfigure.qqAppStatus==0}">
                        <input type="radio" checked="checked" value="0" name="sysConfigure.qqAppStatus"/>开启
                        <input type="radio" value="1" name="sysConfigure.qqAppStatus"/>关闭
                    </c:if>
                    <c:if test="${sysConfigure.qqAppStatus==1}">
                        <input type="radio" value="0" name="sysConfigure.qqAppStatus"/>开启
                        <input type="radio" checked="checked" value="1" name="sysConfigure.qqAppStatus"/>关闭
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>QQ App id</td>
                <td><input type="text" value="${sysConfigure.qqAppId }" name="sysConfigure.qqAppId"
                           class="input-text wid150"/></td>
            </tr>
            <tr>
                <td>QQ App key</td>
                <td><input type="text" value="${sysConfigure.qqAppKey }" name="sysConfigure.qqAppKey"
                           class="input-text wid250"/>
                    <a target="_blank" href="http://connect.qq.com/">点击注册</a>
                </td>
            </tr>
            <tr>
                <td width="100"></td>
                <td><input type="submit" class="button" name="dosubmit" value=" 提交 "/></td>
            </tr>
            </tbody>
        </table>
    </form>

</div><!--table-form end-->

<script>
</script>

</body>
</html>
