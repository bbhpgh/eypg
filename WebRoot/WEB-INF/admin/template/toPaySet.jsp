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
<div class="header-title lr10"><b>支付方式设置</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
    <!--start-->
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="100px" align="center">支付名称</th>
            <th width="100px" align="center">图片</th>
            <th width="100px" align="center">是否启用</th>
            <th width="100px" align="center">支付方式</th>
            <th width="100px" align="center">管理</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td align="center">财付通</td>
            <td align="center">
                <div style="background: url('/Images/bank_logo.png') repeat scroll 8px -500px rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">
                    财付通
                </div>
            </td>
            <td align="center">
                <%
                    if (ApplicationListenerImpl.sysConfigureJson.getTenpayStatus() == 0) {
                %>
                <font color="#0c0">启用</font>
                <%
                } else {
                %>
                <font color="#f00">禁用</font>
                <%
                    }
                %>
            </td>
            <td align="center">
                及时到账
            </td>
            <td align="center"><a href="/admin_back/payInfo.action?id=tenpay">设置</a></td>
        </tr>
        <tr>
            <td align="center">支付宝</td>
            <td align="center">
                <div style="background: url('/Images/bank_logo.png') repeat scroll 8px -613px rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">
                    支付宝
                </div>
            </td>
            <td align="center">
                <%
                    if (ApplicationListenerImpl.sysConfigureJson.getAlipayStatus() == 0) {
                %>
                <font color="#0c0">启用</font>
                <%
                } else {
                %>
                <font color="#f00">禁用</font>
                <%
                    }
                %>

            </td>
            <td align="center">
                及时到账
            </td>
            <td align="center"><a href="/admin_back/payInfo.action?id=alipay">设置</a></td>
        </tr>
        <tr>
            <td align="center">易宝支付</td>
            <td align="center">
                <div style="background: url('/Images/yeepay.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">
                    易宝支付
                </div>
            </td>
            <td align="center">
                <%
                    if (ApplicationListenerImpl.sysConfigureJson.getYeepayStatus() == 0) {
                %>
                <font color="#0c0">启用</font>
                <%
                } else {
                %>
                <font color="#f00">禁用</font>
                <%
                    }
                %>
            </td>
            <td align="center">
                及时到账
            </td>
            <td align="center"><a href="/admin_back/payInfo.action?id=yeepay">设置</a></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
