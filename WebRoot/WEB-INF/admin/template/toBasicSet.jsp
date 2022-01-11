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
<div class="header-title lr10"><b>基本设置</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
    <!--start-->
    <form method="post" action="/admin_back/doSeoSet.action" name="myform">
        <input type="hidden" name="sysConfigure.id" value="${sysConfigure.id }"/>
        <input type="hidden" name="sysConfigure.wwwUrl" value="${sysConfigure.wwwUrl }"/>
        <input type="hidden" name="sysConfigure.saitName" value="${sysConfigure.saitName }"/>
        <input type="hidden" name="sysConfigure.shortName" value="${sysConfigure.shortName }"/>
        <input type="hidden" name="sysConfigure.saitTitle" value="${sysConfigure.saitTitle }"/>
        <input type="hidden" name="sysConfigure.saitLogo" value="${sysConfigure.saitLogo }"/>
        <input type="hidden" name="sysConfigure.keyword" value="${sysConfigure.keyword }"/>
        <input type="hidden" name="sysConfigure.description" value="${sysConfigure.description }"/>
        <input type="hidden" name="sysConfigure.mailName" value="${sysConfigure.mailName }"/>
        <input type="hidden" name="sysConfigure.mailPwd" value="${sysConfigure.mailPwd }"/>
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
        <input type="hidden" name="sysConfigure.qqAppId" value="${sysConfigure.qqAppId }"/>
        <input type="hidden" name="sysConfigure.qqAppKey" value="${sysConfigure.qqAppKey }"/>
        <input type="hidden" name="sysConfigure.qqAppStatus" value="${sysConfigure.qqAppStatus }"/>
        <table width="100%" cellspacing="0">
            <tbody>
            <tr>
                <td width="220" align="right">网站字符集：</td>
                <td><input type="text" class="input-text" value="utf-8" name="charset" disabled="disabled"/></td>
            </tr>
            <tr>
                <td width="220" align="right">Gzip压缩：</td>
                <td>
                    <input type="radio" checked="checked" value="1" name="gzip"/>是
                    <input type="radio" value="0" name="gzip" disabled="disabled"/>否
                    <span class="lr10">启用压缩可提高用户访问速度。</span>
                </td>
            </tr>
            <tr>
                <td width="220" align="right">域名域：</td>
                <td><input type="text" class="input-text" value="${sysConfigure.domain }" name="sysConfigure.domain"/>如：.1ypg.com
                    (请注意前面有点)
                </td>
            </tr>
            <tr>
                <td width="220" align="right">JS、CSS域名：</td>
                <td><input type="text" class="input-text" value="${sysConfigure.skinUrl }" name="sysConfigure.skinUrl"/>如：http://skin.1ypg.com
                    (请先添加解析，如没有解析请填写http://www.你的域名.com 域名)
                </td>
            </tr>
            <tr>
                <td width="220" align="right">图片域名：</td>
                <td><input type="text" class="input-text" value="${sysConfigure.imgUrl }" name="sysConfigure.imgUrl"/>如：http://img.1ypg.com
                    (请先添加解析，如没有解析请填写http://www.你的域名.com 域名)
                </td>
            </tr>
            <tr>
                <td width="220" align="right">客服QQ：</td>
                <td><input type="text" class="input-text" value="${sysConfigure.serviceQq }"
                           name="sysConfigure.serviceQq"/></td>
            </tr>
            <tr>
                <td width="220" align="right">客服电话:</td>
                <td><input type="text" class="input-text" value="${sysConfigure.serviceTel }"
                           name="sysConfigure.serviceTel"/>如：400-000-8888
                </td>
            </tr>
            <tr>
                <td width="220" align="right"></td>
                <td>
                    <input type="submit" value=" 提交 " class="button"/>
                    <input type="button" value="返回" class="button" onclick="history.go(-1)"/></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
