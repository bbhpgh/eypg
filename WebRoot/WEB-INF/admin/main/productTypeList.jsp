<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="admin_main"/>
    <title>1元拍购_管理中心</title>
</head>

<body>
<div class="block-flat">
    <div class="center">
        <a href="/admin_back/toAddProductType.action">
            <button type="button" class="btn btn-default btn-xs">添加分类</button>
        </a>
        <table id="datatable" class="table table-bordered dataTable" aria-describedby="datatable_info">
            <thead>
            <tr role="row">
                <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                    colspan="1" style="width: 270px;" aria-sort="ascending"
                    aria-label="Rendering engine: activate to sort column descending">分类ID
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1"
                    style="width: 398px;" aria-label="Browser: activate to sort column ascending">分类名称
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1"
                    style="width: 361px;" aria-label="Platform(s): activate to sort column ascending">父分类ID
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1"
                    style="width: 233px;" aria-label="CSS grade: activate to sort column ascending">操作
                </th>
            </tr>
            </thead>

            <tbody role="alert" aria-live="polite" aria-relevant="all">
            <s:iterator value="productTypeList" var="productType" status="st">
                <s:if test="#st.odd == true">
                    <tr class="gradeA odd">
                        <td class="  sorting_1">${productType.typeId }</td>
                        <td class=" ">${productType.typeName }</td>
                        <td class=" ">${productType.ftypeId }</td>
                        <td class="center ">
                            <a href="/admin_back/toAddProductType.action?id=${productType.typeId  }">
                                <button type="button" class="btn btn-default btn-xs">修改</button>
                            </a>
                            <!--											<a href="/admin_back/toAddProductType.action?id=${productType.typeId  }"><button type="button" class="btn btn-default btn-xs">添加子分类</button></a>-->
                        </td>
                    </tr>
                </s:if>
                <s:else>
                    <tr class="gradeA even">
                        <td class="  sorting_1">${productType.typeId }</td>
                        <td class=" ">${productType.typeName }</td>
                        <td class=" ">${productType.ftypeId }</td>
                        <td class="center ">
                            <a href="/admin_back/toAddProductType.action?id=${productType.typeId  }">
                                <button type="button" class="btn btn-default btn-xs">修改</button>
                            </a>
                            <!--											<a href="/admin_back/toAddProductType.action?id=${productType.typeId  }"><button type="button" class="btn btn-default btn-xs">添加子分类</button></a>-->
                        </td>
                    </tr>
                </s:else>
            </s:iterator>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
