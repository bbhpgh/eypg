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
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<input type="hidden" value="${resultCount }" id="resultCount"/>
<div class="bk10"></div>
<div class="header-data lr10">
    时间搜索: <input type="text" value="" readonly="readonly" class="input-text wid100" id="txtMisTime"/> -
    <input type="text" value="" readonly="readonly" class="input-text wid100" id="txtMaxTime"/>
    用户ID：<input type="text" class="input-text wid100" id="userId"/>
    <input type="submit" value="搜索" id="btnSearch" class="button"/>
</div>
<div class="bk10"></div>
<div class="table-list lr10">
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="100px">用户ID</th>
            <th width="100px">充值金额</th>
            <th width="100px">资金渠道</th>
            <th width="100px">时间</th>
        </tr>
        </thead>
        <tbody id="divList">

        </tbody>

    </table>
</div>
<div class="page_nav" id="divPageNav">
    <div class="pages">
        <ul class="pageULEx" id="pagination"></ul>
    </div>
</div>
<script language="javascript" type="text/javascript" src="/js/date.js?data=20131121"></script>
<script language="javascript" type="text/javascript" src="/js/dateinput.js?data=20131121"></script>
<script type="text/javascript" src="/js/pagination.js?data=20131121"></script>
<script language="javascript" type="text/javascript" src="/admin_js/rechargeAllList.js"></script>
</body>
</html>
