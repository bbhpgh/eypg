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
            <table id="datatable" class="table table-bordered dataTable" aria-describedby="datatable_info">
                <thead>
                <tr role="row">
                    <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                        colspan="1" style="width: 20px;" aria-sort="ascending"
                        aria-label="Rendering engine: activate to sort column descending">ID
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                        colspan="1" style="width: 50px;" aria-label="Browser: activate to sort column ascending">订单商品ID
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                        colspan="1" style="width: 50px;" aria-label="Browser: activate to sort column ascending">收货人
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                        colspan="1" style="width: 20px;" aria-label="CSS grade: activate to sort column ascending">联系电话
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                        colspan="1" style="width: 200px;" aria-label="CSS grade: activate to sort column ascending">收货地址
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                        colspan="1" style="width: 40px;" aria-label="CSS grade: activate to sort column ascending">配送时间
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                        colspan="1" style="width: 100px;" aria-label="CSS grade: activate to sort column ascending">订单备注
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                        colspan="1" style="width: 20px;" aria-label="CSS grade: activate to sort column ascending">快递单号
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                        colspan="1" style="width: 40px;" aria-label="CSS grade: activate to sort column ascending">快递公司
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                        colspan="1" style="width: 20px;" aria-label="CSS grade: activate to sort column ascending">发货时间
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="datatable" rowspan="1"
                        colspan="1" style="width: 100px;" aria-label="CSS grade: activate to sort column ascending">发货备注
                    </th>
                </tr>
                </thead>

                <tbody role="alert" aria-live="polite" aria-relevant="all">
                <s:iterator value="orderdetailaddressList" var="orderdetailaddresss" status="st">
                    <s:if test="#st.odd == true">
                        <tr class="gradeA odd">
                            <td class="sorting_1">${orderdetailaddresss.id }</td>
                            <td class=""><a href="/lotteryDetail/${orderdetailaddresss.orderDetailId }.html"
                                            target="_blank">${orderdetailaddresss.orderDetailId }</a></td>
                            <td class="">${orderdetailaddresss.consignee }</td>
                            <td class="">${orderdetailaddresss.phone }</td>
                            <td class="">${orderdetailaddresss.address }</td>
                            <td class="">${orderdetailaddresss.postDate }</td>
                            <td class="">${orderdetailaddresss.orderRemarks }</td>
                            <td class="">${orderdetailaddresss.expressNo }</td>
                            <td class="">${orderdetailaddresss.expressCompany }</td>
                            <td class="">${orderdetailaddresss.deliverTime }</td>
                            <td class="">${orderdetailaddresss.deliverRemarks }</td>

                        </tr>
                    </s:if>
                    <s:else>
                        <tr class="gradeA even">
                            <td class="sorting_1">${orderdetailaddresss.id }</td>
                            <td class=""><a href="/lotteryDetail/${orderdetailaddresss.orderDetailId }.html"
                                            target="_blank">${orderdetailaddresss.orderDetailId }</a></td>
                            <td class="">${orderdetailaddresss.consignee }</td>
                            <td class="">${orderdetailaddresss.phone }</td>
                            <td class="">${orderdetailaddresss.address }</td>
                            <td class="">${orderdetailaddresss.postDate }</td>
                            <td class="">${orderdetailaddresss.orderRemarks }</td>
                            <td class="">${orderdetailaddresss.expressNo }</td>
                            <td class="">${orderdetailaddresss.expressCompany }</td>
                            <td class="">${orderdetailaddresss.deliverTime }</td>
                            <td class="">${orderdetailaddresss.deliverRemarks }</td>
                        </tr>
                    </s:else>
                </s:iterator>
                </tbody>
            </table>
        </div>
        ${pageString }
    </div>
    <script language="javascript" type="text/javascript">
        $("#search").click(function () {
            if ($("#id").val() == "") {
                alert("输入你要生成的卡密数量");
                return false;
            } else if ($("#pwd").val() == "") {
                alert("输入你要生成的卡密面值");
                return false;
            } else {
                var url = "/admin_back/doCard.action?id=" + $("#id").val() + "&pwd=" + $("#pwd").val();
                $.ajax({
                    url: url,
                    type: "POST",
                    beforeSend: function () {
                        $("#search").text('生成中...').attr("disabled", "disabled");
                    },
                    cache: false,
                    success: function (msg) {
                        if (msg != "error") {
                            alert("操作成功！");
                            location.replace("/admin_back/cardList.action");
                        } else {
                            alert("操作失败！");
                            location.replace("/admin_back/cardList.action");
                        }
                    },
                    error: function () {
                        alert("网络错误，请稍后再试！");
                    }
                });
            }
        });
    </script>
</body>
</html>
