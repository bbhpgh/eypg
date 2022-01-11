$(document).ready(function () {
    var resultCount = $("#resultCount").val();
    var pageNo = 0;
    var id = $("#DetailId").val();
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
        var url = "/ConsumeDetail/ConsumeDetailAjaxPage.action?pageNo=" + pageNo + "&id=" + id;
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
                    for (var i = 0; i < data.length; i++) {
                        var X = "<ul class=\"content\" >";
                        X += "<li class=\"time\"><a href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\" title=\"" + data[i].productTitle + "\"><font color=\"#ff6600\">(第" + data[i].productPeriod + "期)</font>" + data[i].productName + "</a></li>";
                        X += "<li class=\"bank\">\uffe5" + data[i].buyCount + "</li>";
                        X += "<li class=\"do\">" + parseFloat(data[i].buyMoney) + "</li>";
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
});

