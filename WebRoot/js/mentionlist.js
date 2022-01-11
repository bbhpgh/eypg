$(document).ready(function () {
    var resultCount = $("#resultCount").val();
    var pageNo = 0;
    var userId = $("#userId").val();
    var M = $("#divMentionList");
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
            $("#divCommissionList").empty();
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
        items_per_page: 12,
        callback: pageselectCallback
    });

    function pageselectCallback(pageNo) {
        var url = "/user/MentionListAjaxPage.action?pageNo=" + pageNo + "&userId=" + userId + "&selectTime=" + W + "&startDate=" + startDate + "&endDate=" + endDate;
        $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
        $.ajax({
            url: url,
            data: "json",
            beforeSend: loading,
            success: function (data) {
                data = eval("(" + data + ")");
                $("#divMentionList").empty();
                if (resultCount > 0) {
                    $("<ul class=\"listTitle\"><li class=\"w150\">申请时间</li><li class=\"w300\">银行账户信息</li><li class=\"w120\">提现金额(元)</li><li class=\"w120\">手续费(元)</li><li class=\"w120\">审核状态</li></ul>").appendTo("#divMentionList");
                    $(".pageULEx").show();
                    $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 12) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                    for (var i = 0; i < data.length; i++) {
                        $("<ul><li class=\"w150\">" + data[i].date + "</li><li class=\"w300\">" + data[i].bankName + " " + data[i].bankSubbranch + "<br />" + data[i].bankUser + " " + data[i].bankNo + "</li><li class=\"w120 orange\">" + data[i].money + "</li><li class=\"w120 orange\">" + data[i].fee + "</li><li class=\"w120\">" + data[i].status + "</li></ul>").appendTo("#divMentionList");
                    }
                    $("#pageLoading").hide();
                } else {
                    $("<ul class=\"listTitle\"><li class=\"w150\">申请时间</li><li class=\"w300\">银行账户信息</li><li class=\"w120\">提现金额(元)</li><li class=\"w120\">手续费(元)</li><li class=\"w120\">审核状态</li></ul>").appendTo("#divMentionList");
                    $("<div class=\"tips-con\"><i></i>未有相应提现记录</div>").appendTo("#divMentionList");
                    $(".pageULEx").hide();
                }
            }
        });
    }

    function pageselectCallback2(pageNo) {
        var url = "/user/MentionListAjaxPage.action?pageNo=" + pageNo + "&userId=" + userId + "&startDate=" + startDate + "&endDate=" + endDate;
        $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
        $.ajax({
            url: url,
            type: "post",
            data: "json",
            beforeSend: loading,
            success: function (data) {
                data = eval("(" + data + ")");
                $("#divMentionList").empty();
                if (resultCount > 0) {
                    $("<ul class=\"listTitle\"><li class=\"w150\">申请时间</li><li class=\"w300\">银行账户信息</li><li class=\"w120\">提现金额(元)</li><li class=\"w120\">手续费(元)</li><li class=\"w120\">审核状态</li></ul>").appendTo("#divMentionList");
                    $(".pageULEx").show();
                    $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 12) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                    for (var i = 0; i < data.length; i++) {
                        $("<ul><li class=\"w150\">" + data[i].date + "</li><li class=\"w300\">" + data[i].bankName + " " + data[i].bankSubbranch + "<br />" + data[i].bankUser + " " + data[i].bankNo + "</li><li class=\"w120 orange\">" + data[i].money + "</li><li class=\"w120 orange\">" + data[i].fee + "</li><li class=\"w120\">" + data[i].status + "</li></ul>").appendTo("#divMentionList");
                    }
                    $("#pageLoading").hide();
                } else {
                    $("<ul class=\"listTitle\"><li class=\"w150\">申请时间</li><li class=\"w300\">银行账户信息</li><li class=\"w120\">提现金额(元)</li><li class=\"w120\">手续费(元)</li><li class=\"w120\">审核状态</li></ul>").appendTo("#divMentionList");
                    $("<div class=\"tips-con\"><i></i>未有相应提现记录</div>").appendTo("#divMentionList");
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
            url: "/user/getMentionListAjaxPageResultCount.action?pageNo=" + pageNo + "&selectTime=" + selectVal + "&userId=" + userId,
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
                    items_per_page: 12,
                    callback: pageselectCallback
                });
            }
        });
    };

    N.click(function () {
        $(".record-tab a").attr("class", "");
        $("#divCommissionList").empty();
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
            url: "/user/getMentionListAjaxPageResultCount.action?pageNo=" + pageNo + "&userId=" + userId + "&startDate=" + d + "&endDate=" + e,
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
                    items_per_page: 12,
                    callback: pageselectCallback2
                });
            }
        });
    };

    isLoaded = true;
    X.val(a.DateAdd("m", -1).Format("YYYY-MM-DD")).date_input();
    F.val(a.Format("YYYY-MM-DD")).date_input();
});

