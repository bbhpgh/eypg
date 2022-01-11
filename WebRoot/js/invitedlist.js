var ShareUrl = "";
var ShareTitle = "";
$(function () {
    var B = "http://skin.1ypg.com";
    var C = $("#hidRefTitle");
    var E = $("#txtInfo");
    var resultCount = $("#resultCount").val();
    var pageNo = 0;
    var userId = $("#userId").val();
    var F = function () {
        var M = $www;
        M += "/share.html?uid=";
        var J = $("#btnCopy");
        ShareTitle = "1\u5143\u5c31\u80fd\u4e70IPhone 5S\u54e6\uff0c\u5feb\u53bb\u770b\u770b\u5427\uff01";
        var N = function () {
            if (C.val() == "") {
                C.val(ShareTitle);
            }
            E.val(C.val() + "\n" + ShareUrl);
            var P = function (Q) {
                try {
                    window.clipboardData.clearData();
                    window.clipboardData.setData("Text", Q);
                    OKDialog("\u590d\u5236\u6210\u529f\uff01");
                }
                catch (R) {
                }
            };
            J.click(function () {
                if ($.browser.msie) {
                    var Q = new RegExp("\n", "g");
                    P(E.val().replace(Q, ""));
                } else {
                    $.PageDialog("<dl class=\"sAltFail\"><dd>\u5bf9\u4e0d\u8d77\uff0c\u60a8\u4f7f\u7528\u7684\u662f\u975eIE\u6838\u5fc3\u6d4f\u89c8\u5668\uff0c\u8bf7\u624b\u52a8\u590d\u5236\u5185\u5bb9\u3002</dd></dl>", {
                        W: 408,
                        H: 50,
                        autoClose: true
                    });
                    E.select();
                }
            });
            J.show();
            $.getScript($skin + "/js/bdshare.js");
        };
        var L = $("#userId").val();
        if (parseInt(L) > 0) {
            var I = function () {
                return M + L;
            };
            var H = function (P) {
                if (P.code == 1 && P.data.urls[0].result) {
                    ShareUrl = P.data.urls[0].url_short;
                    N();
                }
            };
            $.getJSON("https://api.weibo.com/2/short_url/shorten.json?source=1681459862&url_long=" + I() + "&callback=?", H);
        } else {
            ShareUrl = M;
            N();
        }
    };
    F();

    function loading() {
        $("#pageLoading").show();
    }

    $("#pagination").pagination(resultCount, {
        current_page: pageNo,
        prev_text: "\u4e0a\u4e00\u9875",
        next_text: "\u4e0b\u4e00\u9875",
        num_display_entries: 5,
        num_edge_entries: 1,
        link_to: "",
        prev_show_always: false,
        next_show_always: false,
        items_per_page: 12,
        callback: pageselectCallback
    });

    function pageselectCallback(pageNo) {
        var url = "/user/InvitedListAjaxPage.action?pageNo=" + pageNo + "&userId=" + userId;
        $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
        $.ajax({
            url: url, type: "get", data: "json", beforeSend: loading, success: function (data) {
                data = eval("(" + data + ")");
                $("#divList").empty();
                if (resultCount > 0) {
                    $("<ul class=\"listTitle\"><li class=\"w200\">\u7528\u6237\u540d</li><li class=\"w200\">\u65f6\u95f4</li><li class=\"w180\">\u9080\u8bf7\u7f16\u53f7</li><li>\u6d88\u8d39\u72b6\u6001</li></ul>").appendTo("#divList");
                    $(".pageULEx").show();
                    $(".pageULEx").prepend("<li class=\"total_page\">\u9875\u6b21<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 12) + "</i>&nbsp;&nbsp;\u5171<i>" + resultCount + "</i>\u6761\u8bb0\u5f55</li>");
                    for (var i = 0; i < data.length; i++) {
                        $("<ul><li class=\"w200\"><a class=\"name-img\" target=\"_blank\" href=\"" + $www + "/u/" + data[i].userId + ".html\"><img border=\"0\" alt=\"\" src=\"" + data[i].faceImg + "\"></a><p><a class=\"blue\" target=\"_blank\" href=\"" + $www + "/u/" + data[i].userId + ".html\">" + data[i].userName + "</a></p></li><li class=\"w200\">" + data[i].oldDate + "</li><li class=\"w180\">" + data[i].userId + "</li><li>" + data[i].experience / 10 + " \u5143</li></ul>").appendTo("#divList");
                    }
                    $("#divInviteInfo").show();
                    $("#pageLoading").hide();
                } else {
                    $("#divInviteInfo").hide();
                    $("<ul class=\"listTitle\"><li class=\"w200\">\u7528\u6237\u540d</li><li class=\"w200\">\u65f6\u95f4</li><li class=\"w180\">\u9080\u8bf7\u7f16\u53f7</li><li>\u6d88\u8d39\u72b6\u6001</li></ul>").appendTo("#divList");
                    $("<div class=\"tips-con\"><i></i>\u65e0\u76f8\u5e94\u7684\u9080\u8bf7\u8bb0\u5f55</div>").appendTo("#divList");
                    $(".pageULEx").hide();
                }
            }
        });
    }
});

