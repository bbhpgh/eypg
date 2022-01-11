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
<div class="header-data lr10">
    卡密充值成功后自动销毁，请及时生成卡密。
</div>
<div class="bk10"></div>
<div class="table-list lr10">
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="10%">卡密</th>
            <th width="5%">面值金额</th>
            <th width="5%">生成时间</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="cardpasswordList" var="cardpasswords" status="st">
            <tr>
                <td align="left"
                    style="padding-left: 5px;font-size: 14px;">${cardpasswords.randomNo }${cardpasswords.cardPwd }</td>
                <td align="center">${cardpasswords.money }</td>
                <td align="center">${cardpasswords.date }</td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>

${pageString }
</body>
</html>
