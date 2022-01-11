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
<div class="header-title lr10"><b>SEO设置</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
    <!--start-->
    <form method="post" action="/admin_back/doSeoSet.action" name="myform" enctype="multipart/form-data">
        <input type="hidden" name="sysConfigure.id" value="${sysConfigure.id }"/>
        <input type="hidden" name="sysConfigure.imgUrl" value="${sysConfigure.imgUrl }"/>
        <input type="hidden" name="sysConfigure.skinUrl" value="${sysConfigure.skinUrl }"/>
        <input type="hidden" name="sysConfigure.domain" value="${sysConfigure.domain }"/>
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
        <input type="hidden" name="sysConfigure.serviceQq" value="${sysConfigure.serviceQq }"/>
        <input type="hidden" name="sysConfigure.serviceTel" value="${sysConfigure.serviceTel }"/>
        <input type="hidden" name="sysConfigure.qqAppId" value="${sysConfigure.qqAppId }"/>
        <input type="hidden" name="sysConfigure.qqAppKey" value="${sysConfigure.qqAppKey }"/>
        <input type="hidden" name="sysConfigure.qqAppStatus" value="${sysConfigure.qqAppStatus }"/>
        <table width="100%" cellspacing="0">
            <tbody>
            <tr>
                <td width="220" align="right">网站地址：</td>
                <td><input type="text" class="input-text wid200" value="${sysConfigure.wwwUrl }"
                           name="sysConfigure.wwwUrl"/> 如：http://www.1ypg.com
                </td>
            </tr>
            <tr>
                <td width="220" align="right">网站logo：</td>
                <td>
                    <input type="text" class="input-text wid200" name="sysConfigure.saitLogo"
                           value="${sysConfigure.saitLogo }"/>
                    <input name="myFile" class="button" value="上传图片" id="myFile" accept="image/*" type="file"/>
                    图片大小：105*63
                </td>
            </tr>
            <tr>
                <td width="220" align="right">网站名称：</td>
                <td><input type="text" class="input-text wid200" name="sysConfigure.saitName"
                           value="${sysConfigure.saitName }"/> 如：1元云购
                </td>
            </tr>
            <tr>
                <td width="220" align="right">网站简称：</td>
                <td><input type="text" class="input-text wid200" name="sysConfigure.shortName"
                           value="${sysConfigure.shortName }"/> 如：云购
                </td>
            </tr>
            <tr>
                <td width="220" align="right">网站标题：</td>
                <td><input type="text" class="input-text wid200" name="sysConfigure.saitTitle"
                           value="${sysConfigure.saitTitle }"/> 如：1元云购 - 惊喜无限
                </td>
            </tr>
            <tr>
                <td width="220" align="right">网站关键字：</td>
                <td>
                    <input type="text" class="input-text wid300" name="sysConfigure.keyword"
                           value="${sysConfigure.keyword }"/>如：1元云购,一元云购 （多个关键词请用,号分割）
                </td>
            </tr>
            <tr>
                <td width="220" align="right">网站描述：</td>
                <td><textarea style="height:80px" class="wid300"
                              name="sysConfigure.description">${sysConfigure.description }</textarea>
                </td>
            </tr>
            <tr>
                <td width="220" align="right">版权信息：</td>
                <td><textarea style="height:80px" class="wid300" name="sysConfigure.icp">${sysConfigure.icp }</textarea>
                </td>
            </tr>
            <tr>
                <td width="220" align="right"></td>
                <td>
                    <input type="submit" value=" 提交 " class="button"/>
                    <input type="button" value="返回" class="button" onclick="history.go(-1)"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
