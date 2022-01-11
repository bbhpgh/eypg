$(document).ready(function () {
    var resultCount = $("#resultCount").val();
    var pageNo = 0;
    var userId = $("#userId").val();
    var M = $("#divCommissionList");
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
        var url = "/user/CommissionPointsAjaxPage.action?pageNo=" + pageNo + "&userId=" + userId;
        $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
        $.ajax({
            url: url,
            type: "post",
            data: "json",
            beforeSend: loading,
            success: function (data) {
                data = eval("(" + data + ")");
                $("#divCommissionList").empty();
                if (resultCount > 0) {
                    $("<ul class=\"listTitle\"><li class=\"w150\">时间</li><li class=\"w230\">获得/支出</li><li>详情</li></ul>").appendTo("#divCommissionList");
                    $(".pageULEx").show();
                    $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 12) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                    for (var i = 0; i < data.length; i++) {
                        $("<ul><li class=\"w150\">" + data[i].date + "</li><li class=\"w230 orange\">" + data[i].pay + "</li><li>" + data[i].detailed + "</li></ul>").appendTo("#divCommissionList");
                    }
                    $("#pageLoading").hide();

                } else {
                    $("<ul class=\"listTitle\"><li class=\"w150\">时间</li><li class=\"w230\">获得/支出</li><li>详情</li></ul>").appendTo("#divCommissionList");
                    $("<div class=\"tips-con\"><i></i>未有相应的福分记录</div>").appendTo("#divCommissionList");
                    $(".pageULEx").hide();
                }
            }
        });
    }


    function loading() {
        $("#pageLoading").show();
    }

});

