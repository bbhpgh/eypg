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
        <table id="datatable" class="table table-bordered dataTable" aria-describedby="datatable_info">
            <thead>
            <tr role="row">
                <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                    colspan="1" style="width: 100px;" aria-sort="ascending"
                    aria-label="Rendering engine: activate to sort column descending">购买总数
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1"
                    style="width: 50px;" aria-label="Platform(s): activate to sort column ascending">金额
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1"
                    style="width: 500px;" aria-label="CSS grade: activate to sort column ascending">商品名称
                </th>
            </tr>
            </thead>

            <tbody role="alert" aria-live="polite" aria-relevant="all">
            <s:iterator value="orderBeanList" var="orderBean" status="st">
                <s:if test="#st.odd == true">
                    <tr class="gradeA odd">
                        <td class=" ">${orderBean.buyCount }</td>
                        <td class=" ">${orderBean.money }</td>
                        <td class="center ">
                            <a href="/products/${orderBean.productId }.html" target="_blank">${orderBean.productName }
                                (第${orderBean.productPeriod }期)</a>
                        </td>
                    </tr>
                </s:if>
                <s:else>
                    <tr class="gradeA even">
                        <td class=" ">${orderBean.buyCount }</td>
                        <td class=" ">${orderBean.money }</td>
                        <td class="center ">
                            <a href="/products/${orderBean.productId }.html" target="_blank">${orderBean.productName }
                                (第${orderBean.productPeriod }期)</a>
                        </td>
                    </tr>
                </s:else>
            </s:iterator>
            </tbody>
        </table>
        <button class="btn btn-default" onclick="history.go(-1)" type="button">返回</button>
    </div>
    ${pageString }
</div>
</body>
</html>
