var _Ptn = /^\d+$/;
var _CartObj = $("#btnMyCart");
alert(1);
$.fn.GoodsItemFun = function () {
    alert(12);
    var d = $(this);
    var a = d.attr("codeID");
    d.hover(function () {
        alert(11);
        d.addClass("goods_Cur");
        var f = d.find("p[name='buyCount']");
        if (f.html() == "") {
            GetJPData(mainHttp, "getBuyCount", "codeID=" + a, function (g) {
                if (g.code == 0) {
                    f.html("\u93c8\ue101\u6e61\u93ae\u3125\u51e1\u6d5c\u6223\u5598\u6d5c\ufffd<span>" + g.count + "</span>\u6d93\ue043\u7c2f\u7490\ue160\u721c").show();
                } else {
                    f.html("&nbsp;").hide();
                }
            });
        } else {
            if (f.html() != "&nbsp;") {
                f.show();
            }
        }
    }, function () {
        $(this).removeClass("goods_Cur").find("p[name='buyCount']").hide();
    });
    var e = d.find("div.gl_number");
    var b = e.find("input.num_dig");
    e.GoodsCountFun(b);
    var c = d.find("div.pic").find("img");
    d.find("div.go_buy").GoodsBuyFun(b, c, a);
};
$.fn.GoodsCountFun = function (a) {
    var f = parseInt(a.attr("surplus"));
    var d = a.prev();
    var c = a.next();
    var e = a.parent().prev();
    var b = function (i, h) {
        e.show().html(i).fadeOut(3000, function () {
            e.hide();
            a.val(h);
        });
    };
    var g = 1;
    a.keyup(function (i) {
        var h = a.val();
        if (!_Ptn.test(h)) {
            a.val(g);
        } else {
            if (h == 0) {
                b("\u93c8\u20ac\u704f\u6226\u6e36\u6d5c\u6223\u55981\u6d5c\u70d8\ue0bc", 1);
            } else {
                if (h <= f) {
                    g = h;
                } else {
                    b("\u93c8\u20ac\u6fb6\u6c33\u5158\u6d5c\u6223\u5598" + f + "\u6d5c\u70d8\ue0bc", f);
                    a.val(f);
                }
            }
        }
        if (g == 1) {
            d.addClass("num_ban");
            c.removeClass("num_ban");
        } else {
            if (g == f) {
                d.removeClass("num_ban");
                c.addClass("num_ban");
            }
        }
    }).mouseover(function () {
        $(this).focus().select();
    });
    d.click(function () {
        var h = a.val();
        if (_Ptn.test(h)) {
            var i = parseInt(h);
            if (i > 1) {
                i--;
                a.val(i);
                if (i == 1) {
                    d.addClass("num_ban");
                }
                c.removeClass("num_ban");
            } else {
            }
        }
        return false;
    });
    c.click(function () {
        var h = a.val();
        if (_Ptn.test(h)) {
            var i = parseInt(h);
            if (i < f) {
                i++;
                a.val(i);
                d.removeClass("num_ban");
                if (i == f) {
                    c.addClass("num_ban");
                }
            } else {
            }
        }
        return false;
    });
};
$.fn.GoodsBuyFun = function (b, c, a) {
    alert(1);
    $(this).find("a").eq(0).click(function () {
        var d = b.val();
        if (_Ptn.test(d)) {
            if (parseInt(d) > 0) {
                var e = new JQAjax("/dataserver/ShopCartNew");
                e.OnSuccess(function (f) {
                    if (f.d.Code == 0) {
                        location.href = "http://www.1yyg.com/MyCart/CartList.html";
                    }
                });
                e.OnSend($.toJSON({codeID: a, num: d}), "json", true);
            } else {
                b.focus().select();
            }
        }
        return true;
    });
    $(this).find("a").eq(1).click(function () {
        alert(2);
        var d = b.val();
        if (_Ptn.test(d)) {
            if (parseInt(d) > 0) {
                var e = new JQAjax("/dataserver/ShopCartNew");
                e.OnSuccess(function (g) {
                    if (g.d.Code == 0) {
                        _IsCartChanged = true;
                        $("#sCartTotal").html(g.d.Num);
                        f(g.d.Num);
                    }
                });
                e.OnSend($.toJSON({codeID: a, num: d}), "json", true);
            } else {
                b.focus().select();
            }
        }
        var f = function (g) {
            var h = $("<div id=\"cart_shadow\" style=\"display: none;border:1px solid #aaa;z-index: 9999;\"><img src=\"" + c.attr("src") + "\" width=\"100%\" /></div>").prependTo("body");
            h.css({
                width: c.css("width"),
                height: c.css("height"),
                position: "absolute",
                top: c.offset().top,
                left: c.offset().left
            }).show();
            h.animate({
                width: _CartObj.width(),
                height: _CartObj.height(),
                top: _CartObj.offset().top,
                left: _CartObj.offset().left,
                opacity: 0.5
            }, {
                queue: false, duration: 600, complete: function () {
                    h.remove();
                    _CartObj.html("<b>\u7490\ue160\u58bf\u675e\ufffd</b><s></s><em>" + (g > 99 ? "N+" : g) + "</em>");
                }
            });
        };
        return false;
    });
};
var getListPaging = function (g, f, j, k, c) {
    var b = 1, h = 1, e = 1;
    var a = "";
    b = parseInt(g / f);
    if (g % f > 0) {
        b++;
    }
    if (b < 1) {
        b = 1;
    }
    a += "<ul class=\"pageULEx\">";
    if (g > 0) {
        h = 1;
        while (h + k < b + 1 && h + k < j + 2) {
            h += k - 2;
        }
        e = h + k - 1;
        if (e > b) {
            e = b;
        }
        if (j == 1) {
            a += "<li class=\"prev_page page_curgray\"><a><i><</i>\u6d93\u5a41\u7af4\u6924\ufffd</a></li>";
        } else {
            a += "<li class=\"prev_page\"><a href=\"" + c(j - 1) + "\"><i><</i>\u6d93\u5a41\u7af4\u6924\ufffd</a></li>";
        }
        if (h > 1) {
            a += "<li><a href=\"" + c(1) + "\">1</a></li><li>\u9225\ufffd</li>";
        }
        for (var d = h; d <= e; d++) {
            if (d != j) {
                a += "<li><a href=\"" + c(d) + "\">" + d + "</a></li>";
            } else {
                a += "<li class=\"curr_page\">" + d + "</li>";
            }
        }
        if (e < b) {
            a += "<li>\u9225\ufffd</li><li><a href=\"" + c(b) + "\">" + b + "</a></li>";
        }
        if (j < b) {
            a += "<li class=\"next_page\"><a href=\"" + c(j + 1) + "\">\u6d93\u5b29\u7af4\u6924\ufffd<i>></i></a></li>";
        } else {
            a += "<li class=\"prev_page page_curgray\"><a>\u6d93\u5b29\u7af4\u6924\ufffd<i>></i></a></li>";
        }
    }
    a += "</ul>";
    return a;
};

