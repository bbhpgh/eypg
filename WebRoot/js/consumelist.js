$(document).ready(function () {
    var resultCount = $("#resultCount").val();
    var pageNo = 0;
    var userId = $("#userId").val();
    var R = $("#selectTime");
    var J = $("#userSelTime");
    var S = $("#txtMisTime");
    var B = $("#txtMaxTime");
    var startDate = S.val();
    var endDate = B.val();
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
        items_per_page: 10,
        callback: pageselectCallback
    });

    function pageselectCallback(pageNo) {
        var url = "/user/ConsumeListAjaxPage.action?pageNo=" + pageNo + "&selectTime=" + R.val() + "&userId=" + userId + "&startDate=" + startDate + "&endDate=" + endDate;
        $(".pageUL").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
        $.ajax({
            url: url,
            type: "post",
            data: "json",
            beforeSend: loading,
            success: function (data) {
                data = eval("(" + data + ")");
                $("#consumer_records_list").empty();
                $("#RecordCount").text(0);
                if (resultCount > 0) {
                    $(".pageUL").show();
                    $(".pageUL").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 10) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                    $("#RecordCount").text(resultCount);
                    for (var i = 0; i < data.length; i++) {
                        var X = "<ul class=\"content\" >";
                        X += "<li class=\"time\">" + data[i].date + "</li>";
                        X += "<li class=\"bank\">\uffe5" + data[i].money + "</li>";
                        X += "<li class=\"do\"><a href='" + $www + "/ConsumeDetail/" + data[i].outTradeNo + ".html'>\u67e5\u770b\u8be6\u60c5</a></li>";
                        X += "</ul>";
                        $(X).appendTo("#consumer_records_list");
                    }
                    $("#pageLoading").hide();
                } else {
                    $("<ul><li class=\"notFound\">\u67e5\u65e0\u6d88\u8d39\u8bb0\u5f55\uff0c\u8bf7\u66f4\u6539\u67e5\u8be2\u533a\u95f4\uff01</li></ul>").appendTo("#consumer_records_list");
                    $(".pageUL").hide();
                }
            }
        });
    }

    function loading() {
        $("#pageLoading").show();
    }

    function changeLoading() {
        $("#changeLoading").show();
    }

    $("#selectTime").change(function () {
        var selectVal = R.val();
        var a = new Date();
        var b = parseInt(R.val());
        if (selectVal == "0" || selectVal == "1" || selectVal == "2" || selectVal == "3" || selectVal == "4") {
            $.ajax({
                url: "/user/ConsumeListAjaxPageResultCount.action?pageNo=" + pageNo + "&selectTime=" + R.val() + "&userId=" + userId + "&startDate=" + startDate + "&endDate=" + endDate,
                type: "get",
                data: "String",
                beforeSend: changeLoading,
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
                        items_per_page: 10,
                        callback: pageselectCallback
                    });
                    $("#changeLoading").hide();
                }
            });
            J.hide();
        } else if (selectVal == "5") {
            S.val(a.DateAdd("m", -1).Format("YYYY-MM-DD")).date_input();
            B.val(a.Format("YYYY-MM-DD")).date_input();
            J.show();
            var c = function () {
                var d = S.val();
                var e = B.val();
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
                    url: "/user/ConsumeListAjaxPageResultCount.action?pageNo=" + pageNo + "&selectTime=" + R.val() + "&userId=" + userId + "&startDate=" + d + "&endDate=" + e,
                    type: "get",
                    data: "String",
                    beforeSend: changeLoading,
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
                            items_per_page: 10,
                            callback: pageselectCallback
                        });
                        $("#changeLoading").hide();
                    }
                });
            };
            $("#butTimeSelect").unbind("click").bind("click", c);
            return;
            J.show();
        }
    });
    $.getScript($skin + "/js/date.js");
    $.getScript($skin + "/js/dateinput.js");
});

