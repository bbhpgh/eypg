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
        var url = "/u/userFriendsAjaxPage.action?pageNo=" + pageNo + "&id=" + id;
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
                        var html = "";
                        html += "<li class=\"\"><div class=\"friend-img\"><a href=\"" + $www + "/u/" + data[i].userId + ".html\"><img border=\"0\" alt=\"" + data[i].userName + "\" src=\"" + data[i].faceImg + "\"></a></div>";
                        html += "<div class=\"friend-info\"><p class=\"friend-name\"><a rel=\"nofollow\" class=\"blue\" href=\"" + $www + "/u/" + data[i].userId + ".html\">" + data[i].userName + "</a></p>";
                        html += "<p class=\"friend-class gray02\">";
                        if (data[i].experience < 10000) {
                            html += "<span class=\"class-icon01\"><s></s>" + $shortName + "小将</span>";
                        } else if (data[i].experience < 50000) {
                            html += "<span class=\"class-icon02\"><s></s>" + $shortName + "少将</span>";
                        } else if (data[i].experience < 100000) {
                            html += "<span class=\"class-icon03\"><s></s>" + $shortName + "中将</span>";
                        } else if (data[i].experience < 500000) {
                            html += "<span class=\"class-icon04\"><s></s>" + $shortName + "上将</span>";
                        } else if (data[i].experience < 1000000) {
                            html += "<span class=\"class-icon05\"><s></s>" + $shortName + "少校</span>";
                        } else if (data[i].experience < 2000000) {
                            html += "<span class=\"class-icon06\"><s></s>" + $shortName + "中校</span>";
                        } else if (data[i].experience < 5000000) {
                            html += "<span class=\"class-icon07\"><s></s>" + $shortName + "上校</span>";
                        }
                        html += "</p><p class=\"friend-intro gray02\">" + data[i].signature + "</p></div></li>";
                        $(html).appendTo("#pageListItems");
                    }
                    $("#pageLoading").hide();
                } else {
                    $("<div class=\"tips-con\"><i></i>TA还没有添加好友哦</div>").appendTo(".New-content");
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