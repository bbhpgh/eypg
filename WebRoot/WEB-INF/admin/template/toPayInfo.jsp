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
<div class="header-title lr10"><b>支付方式配置</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
    <!--start-->
    <form method="post" action="/admin_back/doSeoSet.action" name="myform">
        <c:if test="${id=='tenpay'}">
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
            <input type="hidden" name="sysConfigure.mailName" value="${sysConfigure.mailName }"/>
            <input type="hidden" name="sysConfigure.mailPwd" value="${sysConfigure.mailPwd }"/>
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
            <table width="100%" cellspacing="0">
                <tbody>
                <tr>
                    <td width="220" align="right">支付名称：</td>
                    <td><input type="text" class="input-text" value="财付通" name="pay_name" disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right">支付方式：</td>
                    <td>
                        <input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>及时到帐
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right">图片：</td>
                    <td>
                        <div style="background: url('/Images/bank_logo.png') repeat scroll 8px -500px rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">
                            财付通
                        </div>
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right">是否开启：</td>
                    <td>
                        <c:if test="${sysConfigure.tenpayStatus==0}">
                            <input type="radio" checked="checked" value="0" name="sysConfigure.tenpayStatus"/>开启
                            <input type="radio" value="1" name="sysConfigure.tenpayStatus"/>关闭
                        </c:if>
                        <c:if test="${sysConfigure.tenpayStatus==1}">
                            <input type="radio" value="0" name="sysConfigure.tenpayStatus"/>开启
                            <input type="radio" checked="checked" value="1" name="sysConfigure.tenpayStatus"/>关闭
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right" style="padding-right:5px;">财付通商户号:</td>
                    <td><input name="sysConfigure.tenpayPartner" value="${sysConfigure.tenpayPartner }"
                               class="input-text wid300"/></td>
                </tr>
                <tr>
                    <td width="220" align="right" style="padding-right:5px;">财付通商户密钥:</td>
                    <td><input name="sysConfigure.tenpayKey" value="${sysConfigure.tenpayKey }"
                               class="input-text wid300"/></td>
                </tr>
                <tr>
                    <td width="220" align="right"></td>
                    <td>
                        <input type="submit" value=" 提交 " name="dosubmit" class="button"/>
                        <input type="button" value="返回" class="button" onclick="history.go(-1)"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </c:if>
        <c:if test="${id=='alipay'}">
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
            <input type="hidden" name="sysConfigure.mailName" value="${sysConfigure.mailName }"/>
            <input type="hidden" name="sysConfigure.mailPwd" value="${sysConfigure.mailPwd }"/>
            <input type="hidden" name="sysConfigure.tenpayPartner" value="${sysConfigure.tenpayPartner }"/>
            <input type="hidden" name="sysConfigure.tenpayKey" value="${sysConfigure.tenpayKey }"/>
            <input type="hidden" name="sysConfigure.tenpayStatus" value="${sysConfigure.tenpayStatus }"/>
            <input type="hidden" name="sysConfigure.yeepayKey" value="${sysConfigure.yeepayKey }"/>
            <input type="hidden" name="sysConfigure.yeepayPartner" value="${sysConfigure.yeepayPartner }"/>
            <input type="hidden" name="sysConfigure.yeepayStatus" value="${sysConfigure.yeepayStatus }"/>
            <input type="hidden" name="sysConfigure.icp" value="${sysConfigure.icp }"/>
            <input type="hidden" name="sysConfigure.serviceQq" value="${sysConfigure.serviceQq }"/>
            <input type="hidden" name="sysConfigure.serviceTel" value="${sysConfigure.serviceTel }"/>
            <input type="hidden" name="sysConfigure.qqAppId" value="${sysConfigure.qqAppId }"/>
            <input type="hidden" name="sysConfigure.qqAppKey" value="${sysConfigure.qqAppKey }"/>
            <input type="hidden" name="sysConfigure.qqAppStatus" value="${sysConfigure.qqAppStatus }"/>
            <table width="100%" cellspacing="0">
                <tbody>
                <tr>
                    <td width="220" align="right">支付名称：</td>
                    <td><input type="text" class="input-text" value="支付宝" name="pay_name" disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right">支付方式：</td>
                    <td>
                        <input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>及时到帐
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right">图片：</td>
                    <td>
                        <div style="background: url('/Images/bank_logo.png') repeat scroll 8px -613px rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">
                            支付宝
                        </div>
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right">是否开启：</td>
                    <td>
                        <c:if test="${sysConfigure.alipayStatus==0}">
                            <input type="radio" checked="checked" value="0" name="sysConfigure.alipayStatus"/>开启
                            <input type="radio" value="1" name="sysConfigure.alipayStatus"/>关闭
                        </c:if>
                        <c:if test="${sysConfigure.alipayStatus==1}">
                            <input type="radio" value="0" name="sysConfigure.alipayStatus"/>开启
                            <input type="radio" checked="checked" value="1" name="sysConfigure.alipayStatus"/>关闭
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right" style="padding-right:5px;">支付宝商户号:</td>
                    <td><input name="sysConfigure.alipayPartner" value="${sysConfigure.alipayPartner }"
                               class="input-text wid300"/></td>
                </tr>
                <tr>
                    <td width="220" align="right" style="padding-right:5px;">支付宝商户密钥:</td>
                    <td><input name="sysConfigure.alipayKey" value="${sysConfigure.alipayKey }"
                               class="input-text wid300"/></td>
                </tr>
                <tr>
                    <td width="220" align="right" style="padding-right:5px;">支付宝账号:</td>
                    <td><input name="sysConfigure.alipayMail" value="${sysConfigure.alipayMail }"
                               class="input-text wid300"/></td>
                </tr>
                <tr>
                    <td width="220" align="right"></td>
                    <td>
                        <input type="submit" value=" 提交 " name="dosubmit" class="button"/>
                        <input type="button" value="返回" class="button" onclick="history.go(-1)"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </c:if>
        <c:if test="${id=='yeepay'}">
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
            <input type="hidden" name="sysConfigure.mailName" value="${sysConfigure.mailName }"/>
            <input type="hidden" name="sysConfigure.mailPwd" value="${sysConfigure.mailPwd }"/>
            <input type="hidden" name="sysConfigure.tenpayPartner" value="${sysConfigure.tenpayPartner }"/>
            <input type="hidden" name="sysConfigure.tenpayKey" value="${sysConfigure.tenpayKey }"/>
            <input type="hidden" name="sysConfigure.tenpayStatus" value="${sysConfigure.tenpayStatus }"/>
            <input type="hidden" name="sysConfigure.alipayPartner" value="${sysConfigure.alipayPartner }"/>
            <input type="hidden" name="sysConfigure.alipayKey" value="${sysConfigure.alipayKey }"/>
            <input type="hidden" name="sysConfigure.alipayMail" value="${sysConfigure.alipayMail }"/>
            <input type="hidden" name="sysConfigure.alipayStatus" value="${sysConfigure.alipayStatus }"/>
            <input type="hidden" name="sysConfigure.icp" value="${sysConfigure.icp }"/>
            <input type="hidden" name="sysConfigure.serviceQq" value="${sysConfigure.serviceQq }"/>
            <input type="hidden" name="sysConfigure.serviceTel" value="${sysConfigure.serviceTel }"/>
            <input type="hidden" name="sysConfigure.qqAppId" value="${sysConfigure.qqAppId }"/>
            <input type="hidden" name="sysConfigure.qqAppKey" value="${sysConfigure.qqAppKey }"/>
            <input type="hidden" name="sysConfigure.qqAppStatus" value="${sysConfigure.qqAppStatus }"/>
            <table width="100%" cellspacing="0">
                <tbody>
                <tr>
                    <td width="220" align="right">支付名称：</td>
                    <td><input type="text" class="input-text" value="易宝支付" name="pay_name" disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right">支付方式：</td>
                    <td>
                        <input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>及时到帐
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right">图片：</td>
                    <td>
                        <div style="background: url('/Images/yeepay.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">
                            易宝支付
                        </div>
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right">是否开启：</td>
                    <td>
                        <c:if test="${sysConfigure.yeepayStatus==0}">
                            <input type="radio" checked="checked" value="0" name="sysConfigure.yeepayStatus"/>开启
                            <input type="radio" value="1" name="sysConfigure.yeepayStatus"/>关闭
                        </c:if>
                        <c:if test="${sysConfigure.yeepayStatus==1}">
                            <input type="radio" value="0" name="sysConfigure.yeepayStatus"/>开启
                            <input type="radio" checked="checked" value="1" name="sysConfigure.yeepayStatus"/>关闭
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td width="220" align="right" style="padding-right:5px;">易宝支付商户号:</td>
                    <td><input name="sysConfigure.yeepayPartner" value="${sysConfigure.yeepayPartner }"
                               class="input-text wid300"/></td>
                </tr>
                <tr>
                    <td width="220" align="right" style="padding-right:5px;">易宝支付商户密钥:</td>
                    <td><input name="sysConfigure.yeepayKey" value="${sysConfigure.yeepayKey }"
                               class="input-text wid300"/></td>
                </tr>
                <tr>
                    <td width="220" align="right"></td>
                    <td>
                        <input type="submit" value=" 提交 " name="dosubmit" class="button"/>
                        <input type="button" value="返回" class="button" onclick="history.go(-1)"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </c:if>
    </form>

</div>
</body>
</html>
