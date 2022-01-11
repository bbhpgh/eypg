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
<div class="table-list lr10">
    <!--start-->
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="3%" align="center">ID</th>
            <th width="3%" align="center">订单商品ID</th>
            <th width="5%" align="center">收货人</th>
            <th width="5%" align="center">联系电话</th>
            <th width="15%" align="center">收货地址</th>
            <th width="3%" align="center">配送时间</th>
            <th width="10%" align="center">订单备注</th>
            <th width="5%" align="center">快递单号</th>
            <th width="5%" align="center">快递公司</th>
            <th width="5%" align="center">发货时间</th>
            <th width="5%" align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="orderdetailaddressList" var="orderdetailaddresss" status="st">
            <tr>
                <td>${orderdetailaddresss.id }</td>
                <td><a href="/lotteryDetail/${orderdetailaddresss.orderDetailId }.html"
                       target="_blank">${orderdetailaddresss.orderDetailId }</a></td>
                <td>${orderdetailaddresss.consignee }</td>
                <td>${orderdetailaddresss.phone }</td>
                <td>${orderdetailaddresss.address }</td>
                <td>${orderdetailaddresss.postDate }</td>
                <td>${orderdetailaddresss.orderRemarks }</td>
                <td>${orderdetailaddresss.expressNo }</td>
                <td>${orderdetailaddresss.expressCompany }</td>
                <td>${orderdetailaddresss.deliverRemarks }</td>
                <td align="center"><a target="_blank" href="/lotteryDetail/${orderdetailaddresss.orderDetailId }.html">订单详情</a>
                </td>
            </tr>
        </s:iterator>
        </tbody>

    </table>
</div>
${pageString }
</body>
</html>
