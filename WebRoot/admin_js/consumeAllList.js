var CBLFun = null;
$(function () {
    var C = function () {
        var D = function () {
            var resultCount = $("#resultCount").val();
            var pageNo = 0;
            var S = 15;
            var I = 1;
            var F = 0;
            var Q = {"type": 0, "FIdx": 1, "EIdx": S, "region": 0, "beginTime": "", "endTime": "", "isCount": 1};
            var G = $("#divList");
            var O = $("#txtMisTime");
            var J = $("#txtMaxTime");

            var H = function () {
                var T = new Date();
                O.val(T.DateAdd("m", -1).Format("YYYY-MM-DD")).date_input();
                J.val(T.Format("YYYY-MM-DD")).date_input();
                var U = function () {
                    var V = O.val();
                    var W = J.val();
                    if (!IsValidDate(V)) {
                        alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u5f00\u59cb\u65e5\u671f!");
                        return;
                    }
                    if (!IsValidDate(W)) {
                        alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7ed3\u675f\u65e5\u671f!");
                        return;
                    }
                    if (ConvertDate(W) < ConvertDate(V)) {
                        alert("\u7ed3\u675f\u65e5\u671f\u4e0d\u80fd\u5c0f\u4e8e\u5f00\u59cb\u65e5\u671f!");
                        return;
                    }
                    Q.region = 5;
                    Q.beginTime = O.val();
                    Q.endTime = J.val();
                    R();
                };
                Q.beginTime = O.val();
                Q.endTime = J.val();
                $("#btnSearch").unbind("click").bind("click", U);
            };
            H();
            $("#pagination").pagination(resultCount, {
                current_page: pageNo,
                prev_text: "上一页",
                next_text: "下一页",
                num_display_entries: 5,
                num_edge_entries: 1,
                link_to: "",
                prev_show_always: false,
                next_show_always: false,
                items_per_page: 20,
                callback: pageselectCallbackConsume
            });

            function pageselectCallbackConsume(pageNo) {
                var url = "/user/ConsumeListAjaxPage.action?pageNo=" + pageNo + "&pageSize=20&startDate=" + Q.beginTime + "&endDate=" + Q.endTime;
                $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"/Images/loding.gif\" /></li>");
                $.ajax({
                    url: url,
                    type: "post",
                    data: "json",
                    beforeSend: loading,
                    success: function (data) {
                        G.empty();
                        if (resultCount > 0) {
                            $(".pageULEx").show();
                            $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 20) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                            for (var i = 0; i < data.length; i++) {
                                $("<tr><td>" + data[i].userId + "</td><td>" + data[i].date + "</td><td>￥" + data[i].money + "</td><td  align=\"center\"><a href=\"/admin_back/orderInfo.action?id=" + data[i].outTradeNo + "\">详情</a></td></tr>").appendTo(G);
                            }
                            $("#pageLoading").hide();
                        } else {
                            $("<div class=\"tips-con\"><i></i>无相应的记录</div>").appendTo(G);
                            $(".pageULEx").hide();
                        }
                    }
                });
            }

            function pageselectCallbackConsume2(pageNo) {
                var url = "/user/ConsumeListAjaxPage.action?pageNo=" + pageNo + "&pageSize=20&userId=" + $("#userId").val() + "&startDate=" + Q.beginTime + "&endDate=" + Q.endTime;
                $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"/Images/loding.gif\" /></li>");
                $.ajax({
                    url: url,
                    type: "post",
                    data: "json",
                    beforeSend: loading,
                    success: function (data) {
                        G.empty();
                        if (resultCount > 0) {
                            $(".pageULEx").show();
                            $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 20) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                            for (var i = 0; i < data.length; i++) {
                                $("<tr><td>" + data[i].userId + "</td><td>" + data[i].date + "</td><td>￥" + data[i].money + "</td><td  align=\"center\"><a href=\"/admin_back/orderInfo.action?id=" + data[i].outTradeNo + "\">详情</a></td></tr>").appendTo(G);
                            }
                            $("#pageLoading").hide();
                        } else {
                            $("<div class=\"tips-con\"><i></i>无相应的记录</div>").appendTo(G);
                            $(".pageULEx").hide();
                        }
                    }
                });
            }


            function loading() {
                $("#pageLoading").show();
            }

            var R = function () {
                if (!isLoaded) {
                    return false;
                }
                $.ajax({
                    url: "/user/ConsumeListAjaxPageResultCount.action?pageNo=" + pageNo + "&userId=" + $("#userId").val() + "&startDate=" + Q.beginTime + "&endDate=" + Q.endTime,
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
                            items_per_page: 20,
                            callback: pageselectCallbackConsume2
                        });
                    }
                });
            };
        };
        CBLFun = new D();
        isLoaded = true;
    };
    C();
});

