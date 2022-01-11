$(function () {
    var id = $("#topid").val();
    if (id == "new20") {
        $("#new").attr("class", "SortCur");
    } else if (id == "hot20") {
        $("#popularity").attr("class", "SortCur");
    } else if (id == "reply20") {
        $("#comment").attr("class", "SortCur");
    }

    var a = function () {
        $("div.share_list_content", "#loadingPicBlock").hover(function () {
            $(this).addClass("hover");
        }, function () {
            $(this).removeClass("hover");
        }).each(function () {
//			var f = $(this).find("dd.text_message");
//			if (f.length > 0) {
//				var j = f.find("span");
//				j.html(e(j.html()));
//				f.show();
//			}
            var k = $(this).find("span.smile");
            var l = false;
            var g = k.find("em");
            var i = g.html();
            var h = g.attr("num");
            if (getCookie("UP_" + h) == "1") {
                l = true;
                k.addClass("smile-have").find("b").html("\u5df2\u7fa1\u6155(" + i + ")");
            } else {
                k.unbind("click").bind("click", function () {
                    if (l) {
                        return false;
                    }
                    $.ajax({
                        url: "/shareShow/upShareInfo.action?shareId=" + h,
                        type: "post",
                        data: "string"
                    });
                    SetCookieByExpires("UP_" + h, "1", 1);
                    l = true;
                    k.addClass("smile-have").find("b").html("\u5df2\u7fa1\u6155(" + (parseInt(i) + 1) + ")");
                    var n = $("<div class='ts'>\u7fa1\u6155+1</div>");
                    k.append(n);
                    n.fadeTo(3000, 0, function () {
                        n.remove();
                    });
                });
            }
            k.parent().show();
        });
    };
    a();

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

    $(".scrollLoading").scrollLoading();
});

