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
</head>

<body>
<div class="bk10"></div>

<div class="table-list lr10">
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="100px;">分类ID</th>
            <th align="center">分类名称</th>
            <th width="100px;" align="center">父分类ID</th>
            <th align="center">管理操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="productTypeList" var="productType" status="st">
            <tr align="center">
                <td align="center">${productType.typeId }</td>
                <td align="center">${productType.typeName }</td>
                <td align="center">${productType.ftypeId }</td>
                <td class="center ">
                    <a href="/admin_back/toAddProductType.action?id=${productType.typeId  }"><input type="submit"
                                                                                                    class="button"
                                                                                                    value="修改"/></a>
                    <a href="/admin_back/toAddProductType.action?typeId=${productType.typeId  }"><input type="submit"
                                                                                                        class="button"
                                                                                                        value="添加子分类"/></a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
</body>
</html>
