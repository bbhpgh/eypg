<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>1元拍购_管理中心</title>
</head>

<body>
<div align="center">
    <form action="/admin_back/doCrawl.action" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>频道页面</td>
                <td><input type="text" name="channelUrl" style="width: 500px;"/></td>
            </tr>
            <tr>
                <td>抓取页数</td>
                <td><input type="text" name="pages"/></td>
            </tr>
            <tr>
                <td>商品类型</td>
                <td>
                    <select name="productType">
                        <option value="">请选择</option>
                        <s:iterator value="productTypeList" var="productTypes">
                            <c:choose>
                                <c:when test="${product.productType==productTypes.typeId }">
                                    <option selected="selected"
                                            value="${productTypes.typeId }">${productTypes.typeName }</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${productTypes.typeId }">${productTypes.typeName }</option>
                                </c:otherwise>
                            </c:choose>
                        </s:iterator>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="开始"/>
    </form>
</div>
</body>
</html>
