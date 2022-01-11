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
        var url = "/u/userPostAjaxPage.action?pageNo=" + pageNo + "&id=" + id;
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
                        $("<li><div class=\"share_list_content\"><dl><dt><a href=\"" + $www + "/shareShow/" + data[i].uid + ".html\" target=\"_blank\"><img src=\"" + $img + "/UserPost/220/" + data[i].shareImages + "\"></a></dt><dd class=\"share-name gray02\"><a class=\"name-img\" href=\"" + $www + "/" + data[i].userId + ".html\"><img border=\"0\" alt=\"\" src=\"" + $img + data[i].userFace + "\" /> </a>" +
                            "<div class=\"share-name-r\"> <span class=\"gray03\"> <a class=\"blue\" href=\"" + $www + "/u/" + data[i].userId + ".html\" rel=\"nofollow\">" + data[i].userName + "</a>" + data[i].shareDate + "</span><a target=\"_blank\" href=\"" + $www + "/shareShow/" + data[i].uid + ".html\" class=\"Fb gray01\">" + data[i].shareTitle + "</a></div> </dd> <dd class=\"share_info gray01\">" + data[i].shareContent + "</dd>" +
                            "<dd class=\"message\"> <span class=\"smile gray03\"><i></i><b>羡慕(<em num=\"" + data[i].uid + "\">" + data[i].upCount + "</em>)</b></span> <span class=\"much\"> <a class=\"gray03\" href=\"" + $www + "/shareShow/" + data[i].uid + ".html\" rel=\"nofollow\" target=\"_blank\"><i></i>评论<em>(" + data[i].replyCount + ")</em></a></span></dd></dl></div></li>").appendTo("#pageListItems");
                    }
                    $("#pageLoading").hide();
                    a();
                } else {
                    $("<div class=\"tips-con\"><i></i>TA还没有晒单哦</div>").appendTo(".New-content");
                    $(".pageULEx").hide();
                }
            }
        });
    }

    function loading() {
        $("#pageLoading").show();
    }

    var a = function () {
        $("div.share_list_content", "#pageListItems").hover(function () {
            $(this).addClass("hover");
        }, function () {
            $(this).removeClass("hover");
        }).each(function () {
//				var j = $(this).find("dd.text_message");
//				if (j.length > 0) {
//					var n = j.find("span");
//					n.html(i(n.html()));
//					j.show();
//				}
            var o = $(this).find("span.smile");
            var p = false;
            var k = o.find("em");
            var m = k.html();
            var l = k.attr("num");
            if (getCookie("UP_" + l) == "1") {
                p = true;
                o.addClass("smile-have").find("b").html("\u5df2\u7fa1\u6155(" + m + ")");
            } else {
                o.unbind("click").bind("click", function () {
                    if (p) {
                        return false;
                    }
                    $.ajax({
                        url: "/shareShow/upShareInfo.action?shareId=" + l,
                        type: "post",
                        data: "string"
                    });
                    SetCookieByExpires("UP_" + l, "1", 1);
                    p = true;
                    o.addClass("smile-have").find("b").html("\u5df2\u7fa1\u6155(" + (parseInt(m) + 1) + ")");
                    var r = $("<div class='ts'>\u7fa1\u6155+1</div>");
                    o.append(r);
                    r.fadeTo(3000, 0, function () {
                        r.remove();
                    });
                });
            }
            o.parent().show();
        });
    };

    $.ajax({
        url: "/u/visitorsList.action?pageNo=" + pageNo + "&userId=" + id,
        type: "get",
        data: "json",
        success: function (data) {
            data = eval("(" + data + ")");
            $("#visitors").empty();
            for (var i = 0; i < data.length; i++) {
                $("<dl class=\"clearfix sid-guest\"><dt class=\"guest-pic\"><a href=\"" + $www + "/u/" + data[i].userId + ".html\" type=\"showCard\" uweb=\"" + data[i].userId + "\"><img width=\"50\" height=\"50\" border=\"0\" alt=\"" + data[i].userName + "\" src=\"" + data[i].faceImg + "\"></a></dt>" +
                    "<dd class=\"guest-con\"><p class=\"sid-mane\"><a rel=\"nofollow\" class=\"blue\" href=\"" + $www + "/u/" + data[i].userId + ".html\" type=\"showCard\" uweb=\"" + data[i].userId + "\">" + data[i].userName + "</a></p><p class=\"sid-address gray02\">" + data[i].ipLocation + "</p><p class=\"sid-time gray02\">" + data[i].newDate + "</p></dd></dl>").appendTo("#visitors");
            }
        }
    });


    function SetCookieByExpires(name, value, date) //存cookie 有过期时限
    {
        var Days = date;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";id=1ypg;path=/;expires=" + exp.toGMTString() + ";domain=" + $domain;
    }

    function getCookie(name)//取cookies函数        
    {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null) return unescape(arr[2]);
        return null;
    }
});