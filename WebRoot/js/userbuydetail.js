$(document).ready(function () {
    var resultCount = $("#resultCount").val();
    var userId = $("#userId").val();
    var pageNo = 0;
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
        items_per_page: 50,
        callback: pageselectCallback
    });

    function pageselectCallback(pageNo) {
        var id = $("#productId").val();
        var url = "/user/getRandomNumberListPage.action?id=" + id + "&userId=" + userId + "&pageNo=" + pageNo;
        $.ajax({
            url: url,
            type: "get",
            data: "String",
            success: function (data) {
                data = eval("(" + data + ")");
                $("#userbuylist").empty();
                $(".pageULEx").show();
                $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 50) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                var html = "<ul class=\"listTitle\"><li class=\"leftTitle\">" + $shortName + "时间</li><li>" + $shortName + "人次</li><li>" + $shortName + "码</li></ul>";
                for (var d = 0; d < data.length; d++) {
                    html += "<ul><li class=\"leftTitle\">" + data[d].buyDate + "</li><li>" + data[d].buyCount + "人次</li><li class=\"Code\"><span>" + data[d].randomNumbers + "</span></li></ul>";
                }
                $("#userbuylist").html(html);
            }
        });
    }

});