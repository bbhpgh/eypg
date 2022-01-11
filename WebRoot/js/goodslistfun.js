$(document).ready(function () {
    var c = $www;
    var a = $www;
    var id = $("#topid").val();
    var typeId = $("#typeId").val();
    var typeName = $("#typeName").val();
    if (id == "hot20") {
        $("#hot20").attr("class", "SortCur");
    } else if (id == "date20") {
        $("#date20").attr("class", "SortCur");
    } else if (id == "price20") {
        $("#price20").attr("class", "Price_Sort SortCur").html("价格 <s></s>");
        if (typeId != null && typeId != "") {
            $("#price20").attr("href", $www + "/list/priceAsc20/" + typeId + ".html");
        } else {
            $("#price20").attr("href", $www + "/list/priceAsc20.html");
        }
    } else if (id == "priceAsc20") {
        $("#price20").attr("class", "Price_Sort SortCur").html("价格 <b></b>");
        if (typeId != null && typeId != "") {
            $("#price20").attr("href", $www + "/list/price20/" + typeId + ".html");
        } else {
            $("#price20").attr("href", $www + "/list/price20.html");
        }
    } else if (id == "about20") {
        $("#about20").attr("class", "SortCur");
    } else if (id == "surplus20") {
        $("#surplus20").attr("class", "SortCur");
    }
    var p = 0;
    var f = 12;
    var h = 60;
    var i = h / f;
    var k = 1;
    var m = 0;
    var v = $("#ulGoodsList");
    var t = $("#divPageNav");
    var o = $("#divLoadingLine");
    var r = false;
    var s = false;
    var n = false;
    var _Ptn = /^\d+$/;
    var _CartObj = $("#btnMyCart");
    var GoodsItemFun = function () {
        $(".goods-iten").each(function () {
            var d = $(this);
            var a = d.attr("codeID");
            d.hover(function () {
                $(this).addClass("goods_Cur");
            }, function () {
                $(this).removeClass("goods_Cur");
            });
            var e = d.find("div.gl_number");
            var b = e.find("input.num_dig");
            e.GoodsCountFun(b);
            var c = d.find("div.pic").find("img");
            d.find("div.go_buy").GoodsBuyFun(b, c, a);
        });
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
                    b("最少需" + $shortName + "1人次", 1);
                } else {
                    if (h <= f) {
                        g = h;
                    } else {
                        b("最多能" + $shortName + "" + f + "人次", f);
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
        $(this).find("a").eq(0).click(function () {
            var d = b.val();
            if (_Ptn.test(d)) {
                if (parseInt(d) > 0) {
                    $.ajax({
                        url: "/list/isStatus.action?id=" + a,
                        type: "GET",
                        success: function (data) {
                            if (data == "false") {
                                b.parent().parent().parent().find("p[name='buyCount']").html("该商品已满员请选择其它商品!").show();
                            } else {
                                var count = parseInt(data);
                                var codeid = a;
                                var json = getCookie("products");
                                if (json == null || json == "") {
                                    var val = "[{'pId':" + codeid + ",'num':" + d + "}]";
                                    SetCookieByExpires("products", val, 1);
                                } else {
                                    var flag = false;
                                    json = eval('(' + json + ')');
                                    $.each(json, function (n, value) {
                                        if (codeid == value.pId) {
                                            value.num = (parseInt(value.num) + parseInt(d));
                                            if ((parseInt(value.num) + parseInt(d)) > count) {
                                                value.num = count;
                                            }
                                            flag = true;
                                        }
                                    });
                                    if (!flag) {
                                        json.push({"pId": +codeid, "num": d});
                                    }
                                    json = JSON.stringify(json);
                                    SetCookieByExpires("products", json, 1);
                                }
                                location.href = $www + "/mycart/index.html";
                            }
                        }
                    });
                } else {
                    b.focus().select();
                }
            }
            return true;
        });
        $(this).find("a").eq(1).click(function () {
            var d = b.val();
            if (_Ptn.test(d)) {
                if (parseInt(d) > 0) {
                    $.ajax({
                        url: "/list/isStatus.action?id=" + a,
                        type: "GET",
                        success: function (data) {
                            if (data == "false") {
                                b.parent().parent().parent().find("p[name='buyCount']").html("该商品已满员请选择其它商品!").show();
                            } else {
                                var count = parseInt(data);
                                var codeid = a;
                                var json = getCookie("products");
                                if (json == null || json == "") {
                                    var val = "[{'pId':" + codeid + ",'num':" + d + "}]";
                                    SetCookieByExpires("products", val, 1);
                                } else {
                                    var flag = false;
                                    json = eval('(' + json + ')');
                                    $.each(json, function (n, value) {
                                        if (codeid == value.pId) {
                                            value.num = (parseInt(value.num) + parseInt(d));
                                            if ((parseInt(value.num) + parseInt(d)) > count) {
                                                value.num = count;
                                            }
                                            flag = true;
                                        }
                                    });
                                    if (!flag) {
                                        json.push({"pId": +codeid, "num": d});
                                    }
                                    json = JSON.stringify(json);
                                    SetCookieByExpires("products", json, 1);
                                }
                                $.ajax({
                                    url: "/mycart/getProductCartCount.html",
                                    type: "GET",
                                    success: function (data) {
                                        var D = data;
                                        if (D > 0) {
                                            $("#sCartTotal").html(D);
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
                                                    _CartObj.html("<b>购物车</b><s></s><em>" + (D > 99 ? "N+" : D) + "</em>");
                                                }
                                            });
                                        } else {
                                            $("#sCartTotal").html(0);
                                        }
                                    }
                                });
                            }
                        }
                    });
                } else {
                    b.focus().select();
                }
            }
            return false;
        });
    };


    GoodsItemFun();

    var loaded = false;

    function show() {
        var top = $(".Roll_Con").offset().top;
        if (!loaded && $(window).scrollTop() + $(window).height() > top) {
            $.ajax({
                url: "/lottery/upcomingAnnouncedByTop.action?pageNo=" + 1 + "&pageSize=" + 10,
                type: "get",
                data: "json",
                success: function (data) {
                    data = eval('(' + data + ')');
                    for (var i = 0; i < data.length; i++) {
                        $("<li id=\"" + i + "\">" +
                            "<div class=\"pic\">" +
                            "<a title=\"" + data[i].productTitle + "\" target=\"_blank\" href=\"" + $www + "/products/" + data[i].productId + ".html\" rel=\"nofollow\"><img alt=\"" + data[i].productName + "\" src=\"" + $img + data[i].headImage + "\"></a>" +
                            "</div><p class=\"name\"><a title=\"" + data[i].productTitle + "\" target=\"_blank\" href=\"" + $www + "/products/" + data[i].productId + ".html\">" + data[i].productName + "</a></p>" +
                            "<p class=\"money\">价值：<span class=\"rmbgray\">" + data[i].productPrice + ".00</span></p><div class=\"go_buy\">" +
                            "<a rel=\"nofollow\" class=\"go_Shopping12\" title=\"立即1元" + $shortName + "\" target=\"_blank\" href=\"" + $www + "/products/" + data[i].productId + ".html\">立即1元" + $shortName + "</a></div>" +
                            "</li>").appendTo("#ulRecommend");
                    }
                    $.getScript(a + "/js/recommendpicfun.js");
                }
            });
            loaded = true;
        }
    };
    $(window).scroll(show);

    $(".scrollLoading").scrollLoading();


    function SetCookie(name, value) //存cookie
    {
        document.cookie = name + "=" + escape(value) + ";id=1ypg;path=/;domain=" + $domain;
    };

    function SetCookieByExpires(name, value, date) //存cookie 有过期时限
    {
        var Days = date;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";id=1ypg;path=/;expires=" + exp.toGMTString() + ";domain=" + $domain;
    };

    function getCookie(name)//取cookies函数        
    {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null) return unescape(arr[2]);
        return null;
    };

    function delCookie(name)//删除cookie
    {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null) document.cookie = name + "=" + cval + ";id=1ypg;path=/;expires=" + exp.toGMTString() + ";domain=" + $domain;
    };
    $.getScript(a + "/js/json2.js");
});
