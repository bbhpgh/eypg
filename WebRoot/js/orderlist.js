$(document).ready(function () {
    var resultCount = $("#resultCount").val();
    var pageNo = 0;
    var userId = $("#userId").val();
    var M = $("#tbList");
    var O = $("[name=divTip]");
    var p = $("<ul></ul>");
    var X = $("#txtMisTime");
    var F = $("#txtMaxTime");
    var N = $("#btnSearch");
    var W = $("[class=record-cur]").attr("name");
    var a = new Date();
    var startDate = X.val();
    var endDate = F.val();
    $(".record-tab a").each(function (D, C) {
        $(this).bind("click", function () {
            $(this).addClass("record-cur").siblings().removeClass("record-cur");
            W = $("[class=record-cur]").attr("name");
            $("#goods_list").empty();
            selectTime(W);
        });
    });
    //分页事件
    $("#pagination").pagination(resultCount, {
        current_page: pageNo,
        prev_text: "上一页",
        next_text: "下一页",
        num_display_entries: 5,
        num_edge_entries: 1,
        link_to: "",
        prev_show_always: false,
        next_show_always: false,
        items_per_page: 5,
        callback: pageselectCallback
    });

    function pageselectCallback(pageNo) {
        var url = "/user/OrderListAjaxPage.action?pageNo=" + pageNo + "&selectTime=" + W + "&userId=" + userId + "&startDate=" + startDate + "&endDate=" + endDate;
        $(".pageUL").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
        $.ajax({
            url: url,
            type: "post",
            data: "json",
            beforeSend: loading,
            success: function (data) {
                data = eval("(" + data + ")");
                $("#tbList").empty();
                if (resultCount > 0) {
                    $("<ul class=\"listTitle\"><li class=\"single-img\">商品图片</li><li class=\"single-xx-has\">商品信息</li><li class=\"sdzt\">状态</li><li class=\"single-Control\">操作</li></ul>").appendTo("#tbList");
                    $(".pageULEx").show();
                    $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 5) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                    for (var i = 0; i < data.length; i++) {
                        var html = "";
                        html += "<ul class=\"sdzt\"><li class=\"single-img\"><a href=\"" + $www + "/products/" + data[i].productId + ".html\" class=\"pic\" target=\"_blank\" title=\"\"><img src=\"" + $img + data[i].productImg + "\"></a></li>" +
                            "<li class=\"single-xx-has\"><a class=\"blue\" href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\">(第" + data[i].productPeriod + "期)" + data[i].productName + "</a>" +
                            "<p class=\"buy-money\">价值：<span class=\"money\"><i class=\"rmb\"></i>" + data[i].productPrice + ".00</span></p>" +
                            "<p class=\"buy-code\">幸运" + $shortName + "码：" + data[i].winId + "</p>" +
                            "<p class=\"gray02\">揭晓时间：" + data[i].winDate + "</p></li><li class=\"sdzt\"><span class=\"orange\">";
                        if (data[i].buyStatus == 1) {
                            html += "收货地址未确认<br /><a class=\"blue\" href=\"/user/OrderDetail-" + data[i].productId + ".html\">立即确认地址</a>";
                        } else if (data[i].buyStatus == 2) {
                            html += "等待发货";
                        } else if (data[i].buyStatus == 3) {
                            html += "等待收货";
                        } else if (data[i].buyStatus == 4) {
                            html += "已确认收货";
                        } else if (data[i].buyStatus == 10) {
                            html += "交易完成";
                        } else if (data[i].buyStatus == 11) {
                            html += "交易取消";
                        }
                        html += "</span></li>";
                        html += "<li class=\"single-Control\"><a title=\"详情\" class=\"blue\" href=\"" + $www + "/user/OrderDetail-" + data[i].productId + ".html\">交易详情</a>";
                        if (data[i].buyStatus == 4) {
                            html += "<a class=\"blue\" href=\"" + $www + "/user/PostSingleAdd-" + data[i].productId + ".html\">立即晒单</a>"
                        }
                        html += "</li></ul>";
                        $(html).appendTo("#tbList");
                    }
                } else {
                    $("<ul class=\"listTitle\"><li class=\"single-img\">商品图片</li><li class=\"single-xx-has\">商品信息</li><li class=\"sdzt\">状态</li><li class=\"single-Control\">操作</li></ul>").appendTo("#tbList");
                    $("<div class=\"tips-con\"><i></i>无相应的获得商品记录</div>").appendTo("#tbList");
                    $(".pageULEx").hide();
                }
            }
        });
    }

    function pageselectCallback2(pageNo) {
        var url = "/user/OrderListAjaxPage.action?pageNo=" + pageNo + "&userId=" + userId + "&startDate=" + startDate + "&endDate=" + endDate;
        $(".pageUL").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
        $.ajax({
            url: url,
            type: "post",
            data: "json",
            beforeSend: loading,
            success: function (data) {
                data = eval("(" + data + ")");
                $("#tbList").empty();
                if (resultCount > 0) {
                    $("<ul class=\"listTitle\"><li class=\"single-img\">商品图片</li><li class=\"single-xx-has\">商品信息</li><li class=\"sdzt\">状态</li><li class=\"single-Control\">操作</li></ul>").appendTo("#tbList");
                    $(".pageULEx").show();
                    $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 5) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                    for (var i = 0; i < data.length; i++) {
                        var html = "";
                        html += "<ul class=\"sdzt\"><li class=\"single-img\"><a href=\"" + $www + "/products/" + data[i].productId + ".html\" class=\"pic\" target=\"_blank\" title=\"\"><img src=\"" + $img + data[i].productImg + "\"></a></li>" +
                            "<li class=\"single-xx-has\"><a class=\"blue\" href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\">(第" + data[i].productPeriod + "期)" + data[i].productName + "</a>" +
                            "<p class=\"buy-money\">价值：<span class=\"money\"><i class=\"rmb\"></i>" + data[i].productPrice + ".00</span></p>" +
                            "<p class=\"buy-code\">幸运" + $shortName + "码：" + data[i].winId + "</p>" +
                            "<p class=\"gray02\">揭晓时间：" + data[i].winDate + "</p></li><li class=\"sdzt\"><span class=\"orange\">";
                        if (data[i].buyStatus == 1) {
                            html += "收货地址未确认<br /><a class=\"blue\" href=\"/user/OrderDetail-" + data[i].productId + ".html\">立即确认地址</a>";
                        } else if (data[i].buyStatus == 2) {
                            html += "等待发货";
                        } else if (data[i].buyStatus == 3) {
                            html += "等待收货";
                        } else if (data[i].buyStatus == 4) {
                            html += "已确认收货";
                        } else if (data[i].buyStatus == 10) {
                            html += "交易完成";
                        } else if (data[i].buyStatus == 11) {
                            html += "交易取消";
                        }
                        html += "</span></li>";
                        html += "<li class=\"single-Control\"><a title=\"详情\" class=\"blue\" href=\"" + $www + "/user/OrderDetail-" + data[i].productId + ".html\">交易详情</a></li></ul>";
                        $(html).appendTo("#tbList");
                    }
                } else {
                    $("<ul class=\"listTitle\"><li class=\"single-img\">商品图片</li><li class=\"single-xx-has\">商品信息</li><li class=\"sdzt\">状态</li><li class=\"single-Control\">操作</li></ul>").appendTo("#tbList");
                    $("<div class=\"tips-con\"><i></i>无相应的获得商品记录</div>").appendTo("#tbList");
                    $(".pageULEx").hide();
                }
            }
        });
    }

    function loading() {
        $("#pageLoading").show();
    }

    var selectTime = function (W) {
        var selectVal = W;
        $.ajax({
            url: "/user/OrderListAjaxPageResultCount.action?pageNo=" + pageNo + "&selectTime=" + selectVal + "&userId=" + userId,
            type: "get",
            data: "String",
            success: function (data) {
                resultCount = parseInt(data);
                //分页事件
                $("#pagination").pagination(resultCount, {
                    current_page: pageNo,
                    prev_text: "上一页",
                    next_text: "下一页",
                    num_display_entries: 5,
                    num_edge_entries: 1,
                    link_to: "",
                    prev_show_always: false,
                    next_show_always: false,
                    items_per_page: 5,
                    callback: pageselectCallback
                });
            }
        });
    };

    N.click(function () {
        $(".record-tab a").attr("class", "");
        $("#goods_list").empty();
        c();
    });

    var c = function () {
        var d = X.val();
        var e = F.val();
        if (!IsValidDate(d)) {
            alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u5f00\u59cb\u65e5\u671f!");
            return;
        }
        if (!IsValidDate(e)) {
            alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7ed3\u675f\u65e5\u671f!");
            return;
        }
        if (ConvertDate(e) < ConvertDate(d)) {
            alert("\u7ed3\u675f\u65e5\u671f\u4e0d\u80fd\u5c0f\u4e8e\u5f00\u59cb\u65e5\u671f!");
            return;
        }
        $.ajax({
            url: "/user/OrderListAjaxPageResultCount.action?pageNo=" + pageNo + "&userId=" + userId + "&startDate=" + d + "&endDate=" + e,
            type: "get",
            data: "String",
            success: function (data) {
                resultCount = parseInt(data);
                startDate = d;
                endDate = e;
                //分页事件
                $("#pagination").pagination(resultCount, {
                    current_page: pageNo,
                    prev_text: "上一页",
                    next_text: "下一页",
                    num_display_entries: 5,
                    num_edge_entries: 1,
                    link_to: "",
                    prev_show_always: false,
                    next_show_always: false,
                    items_per_page: 5,
                    callback: pageselectCallback2
                });
            }
        });
    };

    X.val(a.DateAdd("m", -1).Format("YYYY-MM-DD")).date_input();
    F.val(a.Format("YYYY-MM-DD")).date_input();
});

