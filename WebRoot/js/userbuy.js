$(document).ready(function () {
    var resultCount = $("#resultCount").val();
    var pageNo = 0;
    var id = $("#hidPageUserID").val();
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
        var url = "/u/userBuyAjaxPage.action?pageNo=" + pageNo + "&id=" + id;
        $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
        $.ajax({
            url: url,
            type: "post",
            data: "json",
            beforeSend: loading,
            success: function (data) {
                data = eval("(" + data + ")");
                $("#pageListItems").empty();
                if (resultCount > 0) {
                    $(".pageULEx").show();
                    $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 12) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].buyStatus == "0") {
                            $("<li name=\"userBuyItem\"><p class=\"get-pic\"><a title=\"" + data[i].productTitle + "\" rel=\"nofollow\" href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\">" +
                                "<img border=\"0\" alt=\"" + data[i].productName + "\" src=\"" + $img + data[i].productImg + "\"></a></p><p class=\"get-name\">" +
                                "<a title=\"" + data[i].productTitle + "\" class=\"gray01\" href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\">(第" + data[i].productPeriod + "期)" + data[i].productName + "</a></p>" +
                                "<p class=\"buy-money gray02\">价值：<span class=\"money\"><i class=\"rmb\"></i>" + data[i].productPrice + ".00</span></p><div class=\"Progress-bar\"><p><span style=\"width:" + (data[i].spellbuyCount / data[i].productPrice) * 100 + "%\"></span></p>" +
                                "<ul class=\"Pro-bar-li\"><li class=\"P-bar01\"><em>" + data[i].spellbuyCount + "</em>已参与人次</li><li class=\"P-bar02\"><em>" + data[i].productPrice + "</em>总需人次</li><li class=\"P-bar03\"><em>" + (data[i].productPrice - data[i].spellbuyCount) + "</em>剩余人次</li></ul></div></li>").appendTo("#pageListItems");
                        } else {
                            $("<li name=\"userBuyItem\"><p class=\"get-pic\"><a title=\"" + data[i].productTitle + "\" rel=\"nofollow\" href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\">" +
                                "<img border=\"0\" alt=\"" + data[i].productName + "\" src=\"" + $img + data[i].productImg + "\"></a><b>已揭晓</b></p>" +
                                "<p class=\"get-name\"><a title=\"" + data[i].productTitle + "\" class=\"gray01\" href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\">(第" + data[i].productPeriod + "期)" + data[i].productName + "</a></p>" +
                                "<p class=\"buy-money gray02\">价值：<span class=\"money\"><i class=\"rmb\"></i>" + data[i].productPrice + ".00</span></p>" +
                                "<p class=\"buy-award gray02\">获得者：<a class=\"blue\" href=\"" + $www + "/u/" + data[i].winUserId + ".html\">" + data[i].winUser + "</a></p><p class=\"buy-code gray02\">幸运" + $shortName + "码：<span class=\"orange\">" + data[i].winId + "</span></p></li>").appendTo("#pageListItems");
                        }
                    }
                    $("#pageLoading").hide();
                } else {
                    $("<div class=\"tips-con\"><i></i>TA还没有" + $shortName + "记录哦</div>").appendTo(".New-content");
                    $(".pageULEx").hide();
                }
            }
        });
    }

    function loading() {
        $("#pageLoading").show();
    }

    $.ajax({
        url: "/u/visitorsList.action?pageNo=" + pageNo + "&userId=" + id,
        type: "get",
        date: "json",
        success: function (data) {
            data = eval("(" + data + ")");
            $("#visitors").empty();
            for (var i = 0; i < data.length; i++) {
                $("<dl class=\"clearfix sid-guest\"><dt class=\"guest-pic\"><a href=\"" + $www + "/u/" + data[i].userId + ".html\" type=\"showCard\" uweb=\"" + data[i].userId + "\"><img width=\"50\" height=\"50\" border=\"0\" alt=\"" + data[i].userName + "\" src=\"" + data[i].faceImg + "\"></a></dt>" +
                    "<dd class=\"guest-con\"><p class=\"sid-mane\"><a rel=\"nofollow\" class=\"blue\" href=\"" + $www + "/u/" + data[i].userId + ".html\" type=\"showCard\" uweb=\"" + data[i].userId + "\">" + data[i].userName + "</a></p><p class=\"sid-address gray02\">" + data[i].ipLocation + "</p><p class=\"sid-time gray02\">" + data[i].newDate + "</p></dd></dl>").appendTo("#visitors");
            }
        }
    });
});