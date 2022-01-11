<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="admin_main"/>
    <title>1元拍购_管理中心</title>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div class="block-flat">
    <div class="center">
        <div class="form-group">
            <label class="col-sm-3 control-label" style="width: 100px;">搜索订单</label>
            <div class="col-sm-3">
                <input type="text" id="keyword" class="form-control" placeholder="可输入用户ID、昵称、邮箱、手机号查询"/>
            </div>
        </div>
        <button class="btn btn-primary btn-flat" id="search" type="button">查询</button>
        <script language="javascript" type="text/javascript">
            $("#search").click(function () {
                location.replace("/admin_back/orderList.action?userName=" + $("#keyword").val());
            });
        </script>

        <table id="datatable" class="table table-bordered dataTable" aria-describedby="datatable_info">
            <thead>
            <tr role="row">
                <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                    colspan="1" style="width: 100px;" aria-sort="ascending"
                    aria-label="Rendering engine: activate to sort column descending">交易时间
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1"
                    style="width: 50px;" aria-label="Browser: activate to sort column ascending">购买总数
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1"
                    style="width: 50px;" aria-label="Platform(s): activate to sort column ascending">金额
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1"
                    style="width: 100px;" aria-label="CSS grade: activate to sort column ascending">支付类型
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1"
                    style="width: 150px;" aria-label="CSS grade: activate to sort column ascending">用户ID
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1"
                    style="width: 300px;" aria-label="CSS grade: activate to sort column ascending">用户名称
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1" colspan="1"
                    style="width: 100px;" aria-label="CSS grade: activate to sort column ascending">详情
                </th>
            </tr>
            </thead>

            <tbody role="alert" aria-live="polite" aria-relevant="all">
            <s:iterator value="orderBeanList" var="orderBean" status="st">
                <s:if test="#st.odd == true">
                    <tr class="gradeA odd">
                        <td class="  sorting_1">${orderBean.date }</td>
                        <td class=" ">${orderBean.buyCount }</td>
                        <td class=" ">${orderBean.money }</td>
                        <td class=" ">${orderBean.payType }</td>
                        <td class=" ">${orderBean.userId }</td>
                        <td class="center ">
                            <a href="/u/${orderBean.userId }.html" target="_blank">${orderBean.userName }</a>
                        </td>
                        <td class=" "><a href="/admin_back/orderInfo.action?id=${orderBean.outTradeNo }">详情</a></td>
                    </tr>
                </s:if>
                <s:else>
                    <tr class="gradeA even">
                        <td class="  sorting_1">${orderBean.date }</td>
                        <td class=" ">${orderBean.buyCount }</td>
                        <td class=" ">${orderBean.money }</td>
                        <td class=" ">${orderBean.payType }</td>
                        <td class=" ">${orderBean.userId }</td>
                        <td class="center ">
                            <a href="/u/${orderBean.userId }.html" target="_blank">${orderBean.userName }</a>
                        </td>
                        <td class=" "><a href="/admin_back/orderInfo.action?id=${orderBean.outTradeNo }">详情</a></td>
                    </tr>
                </s:else>
            </s:iterator>
            </tbody>
        </table>
    </div>
    ${pageString }
</div>
</body>
</html>
