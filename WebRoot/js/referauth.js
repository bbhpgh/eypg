var ShareUrl = "";
var ShareTitle = "";
$(function () {
    var B = $skin;
    var C = $("#hidRefTitle");
    var A = function () {
        var G = $("#referDocument");
        var D = G.find("ul.process li");
        $.each(D, function (H) {
            $(this).hover(function () {
                $(this).find("div").removeClass("hidden");
            }, function () {
                $(this).find("div").addClass("hidden");
            });
        });
        var E = $("#copyShareText");
        var F = function () {
            var M = $www;
            M += "/share.html?uid=";
            var J = $("#btnCopy");
            ShareTitle = "\u6211\u521a\u53d1\u73b0\u4e00\u4e2a\u5f88\u597d\u5f88\u597d\u73a9\u7684\u7f51\u7ad9\uff0c1\u5143\u5c31\u80fd\u4e70IPhone 5S\u54e6\uff0c\u5feb\u53bb\u770b\u770b\u5427\uff01";
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
                            W: 418,
                            H: 50,
                            autoClose: true
                        });
                        E.select();
                    }
                });
                J.show();
                $.getScript($skin + "/js/bdshare.js");
            };
            var L = $("#hidUID").val();
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
    };
    A();
});

