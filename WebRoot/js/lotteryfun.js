$(document).ready(function () {
    var a = $www;
    $("li", "#ProductList").hover(function () {
        $(this).addClass("Cursor");
    }, function () {
        $(this).removeClass("Cursor");
    });

    var h = $("#buyList");
    $.ajax({
        url: "/getNowBuyProduct.action?pageNo=" + 1 + "&pageSize=" + 12,
        type: "GET",
        data: "JSON",
        success: function (data) {
            data = eval('(' + data + ')');
            var m = "";
            for (var L = 0; L < data.length; L++) {
                m += "<li><a href=\"" + $www + "/products/" + data[L].productId + ".html\" title=\"" + data[L].productName + "\" class=\"pic\" target=\"_blank\">";
                m += "<img src=\"" + $img + data[L].headImage + "\" /></a>";
                m += "<p class=\"Rtagou\"><a class=\"blue\" target=\"_blank\" href=\"" + $www + "/u/" + data[L].userId + ".html\">" + data[L].buyer + "</a>\u521a\u521a\u62cd\u8d2d\u4e86</p>";
                m += "<p class=\"Rintro\"><a class=\"gray01\" href=\"" + $www + "/products/" + data[L].productId + ".html\" target=\"_blank\">" + data[L].productName + "</a></p></li>";
            }
            h.find("li:gt(" + (h.find("li").length - data.length - 1) + ")").remove();
            h.append(m);
        }
    });

    var f = function () {
        h.prepend(h.find("li:last")).css("marginTop", "-68px");
        h.animate({marginTop: "0px"}, 800);
    };
    var g = setInterval(f, 3000);
    h.hover(function () {
        clearInterval(g);
        g = null;
    }, function () {
        g = setInterval(f, 3000);
    });

//	var loaded = false;
//	  function show(){
//	   var top = $("#RcenterH").offset().top;
//		   if(!loaded && $(window).scrollTop() + $(window).height() > top ){
    $.ajax({
        url: "/lottery/upcomingAnnouncedByTop.action?pageNo=" + 1 + "&pageSize=" + 7,
        type: "get",
        data: "json",
        success: function (data) {
            data = eval('(' + data + ')');
            for (var i = 0; i < data.length; i++) {
                if (i >= 0 && i <= 2) {
                    if (i == 0) {
                        $("<li class=\"list-block\"><div name=\"detailsinfo\" style=\"\"><b>1</b><div class=\"pic\"><a rel=\"nofollow\" href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\" title=\"" + data[i].productTitle + "\">" +
                            "<img alt=\"" + data[i].productName + "\" src=\"" + $img + data[i].headImage + "\"></a></div><p class=\"name\">" +
                            "<a class=\"gray01\" href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\" title=\"" + data[i].productTitle + "\">" + data[i].productName + "</a></p>" +
                            "<p class=\"money\">价值：<span class=\"rmb\">" + data[i].productPrice + ".00</span></p><div class=\"Progress-bar\" style=\"\"><p title=\"已完成" + ((data[i].currentBuyCount / data[i].productPrice) * 100).toFixed(2) + "%\"><span style=\"width:" + ((data[i].currentBuyCount / data[i].productPrice) * 100).toFixed(2) + "%;\"></span></p>" +
                            "<ul class=\"Pro-bar-li\"><li class=\"P-bar01 list-block\"><em>" + data[i].currentBuyCount + "</em>已参与人次</li><li class=\"P-bar02\"><em>" + data[i].productPrice + "</em>总需人次</li><li class=\"P-bar03\"><em>" + (data[i].productPrice - data[i].currentBuyCount) + "</em>剩余人次</li></ul>" +
                            "</div><div class=\"shop_buttom\"><a class=\"go_Shopping\" title=\"立即1元" + $shortName + "\" target=\"_blank\" href=\"" + $www + "/products/" + data[i].productId + ".html\">立即1元" + $shortName + "</a></div></div>" +
                            "<div name=\"simpleinfo\" style=\"display:none;\"><b>1</b><span class=\"pic\"><img alt=\"" + data[i].productName + "\" src=\"" + $img + data[i].headImage + "\"></span>" +
                            "<p class=\"Rintro gray01\">" + data[i].productName + "</p><p><i>剩余人次</i><em>" + (data[i].productPrice - data[i].currentBuyCount) + "</em></p></div></li>").appendTo("#RcenterH");
                    } else {
                        $("<li class=\"\"><div style=\"display: none;\" name=\"detailsinfo\"><b>" + (i + 1) + "</b>" +
                            "<div class=\"pic\"><a title=\"" + data[i].productTitle + "\" target=\"_blank\" href=\"" + $www + "/products/" + data[i].productId + ".html\" rel=\"nofollow\"><img alt=\"" + data[i].productName + "\" src=\"" + $img + data[i].headImage + "\"></a></div>" +
                            "<p class=\"name\"><a title=\"" + data[i].productTitle + "\" target=\"_blank\" href=\"" + $www + "/products/" + data[i].productId + ".html\" class=\"gray01\">" + data[i].productName + "</a>" +
                            "</p><p class=\"money\">价值：<span class=\"rmb\">" + data[i].productPrice + ".00</span></p><div style=\"\" class=\"Progress-bar\">" +
                            "<p title=\"已完成" + ((data[i].currentBuyCount / data[i].productPrice) * 100).toFixed(2) + "%\"><span style=\"width:" + ((data[i].currentBuyCount / data[i].productPrice) * 100).toFixed(2) + "%;\"></span></p>" +
                            "<ul class=\"Pro-bar-li\"><li class=\"P-bar01\"><em>" + data[i].currentBuyCount + "</em>已参与人次</li><li class=\"P-bar02\"><em>" + data[i].productPrice + "</em>总需人次</li><li class=\"P-bar03\"><em>" + (data[i].productPrice - data[i].currentBuyCount) + "</em>剩余人次</li>" +
                            "</ul></div><div class=\"shop_buttom\"><a href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\" title=\"立即1元" + $shortName + "\" class=\"go_Shopping\">立即1元" + $shortName + "</a></div>" +
                            "</div><div style=\"display: block;\" name=\"simpleinfo\"><b>" + (i + 1) + "</b><span class=\"pic\"><img alt=\"" + data[i].productName + "\" src=\"" + $img + data[i].headImage + "\"></span>" +
                            "<p class=\"Rintro gray01\">" + data[i].productName + "</p><p><i>剩余人次</i><em>" + (data[i].productPrice - data[i].currentBuyCount) + "</em></p></div></li>").appendTo("#RcenterH");
                    }
                } else {
                    $("<li class=\"\"><div style=\"display: none;\" name=\"detailsinfo\">" +
                        "<div class=\"pic\"><a title=\"" + data[i].productTitle + "\" target=\"_blank\" href=\"" + $www + "/products/" + data[i].productId + ".html\" rel=\"nofollow\"><img alt=\"" + data[i].productName + "\" src=\"" + $img + data[i].headImage + "\"></a></div>" +
                        "<p class=\"name\"><a title=\"" + data[i].productTitle + "\" target=\"_blank\" href=\"" + $www + "/products/" + data[i].productId + ".html\" class=\"gray01\">" + data[i].productName + "</a>" +
                        "</p><p class=\"money\">价值：<span class=\"rmb\">" + data[i].productPrice + ".00</span></p><div style=\"\" class=\"Progress-bar\">" +
                        "<p title=\"已完成" + ((data[i].currentBuyCount / data[i].productPrice) * 100).toFixed(2) + "%\"><span style=\"width:" + ((data[i].currentBuyCount / data[i].productPrice) * 100).toFixed(2) + "%;\"></span></p>" +
                        "<ul class=\"Pro-bar-li\"><li class=\"P-bar01\"><em>" + data[i].currentBuyCount + "</em>已参与人次</li><li class=\"P-bar02\"><em>" + data[i].productPrice + "</em>总需人次</li><li class=\"P-bar03\"><em>" + (data[i].productPrice - data[i].currentBuyCount) + "</em>剩余人次</li>" +
                        "</ul></div><div class=\"shop_buttom\"><a href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\" title=\"立即1元" + $shortName + "\" class=\"go_Shopping\">立即1元" + $shortName + "</a></div>" +
                        "</div><div style=\"display: block;\" name=\"simpleinfo\"><span class=\"pic\"><img alt=\"" + data[i].productName + "\" src=\"" + $img + data[i].headImage + "\"></span>" +
                        "<p class=\"Rintro gray01\">" + data[i].productName + "</p><p><i>剩余人次</i><em>" + (data[i].productPrice - data[i].currentBuyCount) + "</em></p></div></li>").appendTo("#RcenterH");
                }

            }
            $("li", "#RcenterH").hover(function () {
                $(this).addClass("list-block").siblings().removeClass("list-block");
                $("div[name='detailsinfo']", $(this)).show();
                $("div[name='simpleinfo']", $(this)).hide();
                $("div[name='detailsinfo']", $(this).siblings()).hide();
                $("div[name='simpleinfo']", $(this).siblings()).show();
            }, function () {
            });
        }
    });
//			loaded = true;
//	  	}
//	  };
//	  $(window).scroll(show);

    var d = function () {
        var g = false;
        var h = 0;
        var f = (function (j) {
            if (j.errorCode == 0) {
                h = j.maxSeconds;
                var i = function (m) {
                    var l = $("#ProductList");
                    for (var k = m.length - 1; k > -1; k--) {
                        var n = "";
                        n += "<li class=\"lottery\">";
                        n += "<s class=\"lottery-tips\"></s>";
                        n += "<a class=\"fl goodsimg\" href=\"http://www.1yyg.com/product/" + m[k].codeID + ".html\" target=\"_blank\" title=\"(\u7b2c" + m[k].period + "\u671f)" + m[k].goodsSName + "\">";
                        n += "<img src=\"http://goodsimg.1yyg.com/goodspic/pic-200-200/" + m[k].goodsPic + "\" border=\"0\" alt=\"" + m[k].goodsSName + "\" width=\"140\" />";
                        n += "</a>";
                        n += "<div class=\"publishC-tit\">";
                        n += "<h3>";
                        n += "<a href=\"http://www.1yyg.com/product/" + m[k].codeID + ".html\" target=\"_blank\" class=\"gray01\">(\u7b2c" + m[k].period + "\u671f)" + m[k].goodsSName + "</a>";
                        n += "</h3>";
                        n += "<p class=\"money\">\u5546\u54c1\u4ef7\u503c\uff1a<span class=\"rmb\">" + m[k].price + "</span></p>";
                        n += "</div>";
                        n += "<div id=\"jiexiaoTime\">";
                        n += "<div class=\"Pour-time jiexiaoTime\">";
                        n += "<b><em>\u63ed&nbsp;&nbsp;\u6653</em>\u5012\u8ba1\u65f6</b>";
                        n += "<ol>";
                        n += "<li class=\"Dig\"><span class=\"minute\">00</span></li>";
                        n += "<li>:</li>";
                        n += "<li class=\"Dig\"><span class=\"second\">00</span></li>";
                        n += "<li>:</li>";
                        n += "<li class=\"Dig\"><span class=\"millisecond\">0</span><span class=\"last\">0</span></li>";
                        n += "</ol>";
                        n += "</div>";
                        n += "</div>";
                        n += "<div class=\"details\">\u5373\u5c06\u63ed\u6653\uff0c\u656c\u8bf7\u671f\u5f85...</div>";
                        n += "</li>";
                        n = $(n);
                        l.prepend(n);
                        n.StartTimeOut(m[k].codeID, parseInt(m[k].seconds));
                    }
                };
                if (g) {
                    i(j.listItems);
                } else {
                    Base.getScript(a + "/DataServer/JS/LotteryTime.js?date=20131015", function () {
                        g = true;
                        i(j.listItems);
                    });
                }
            }
            setTimeout(e, 5000);
        });
        var e = function () {
            $.ajax({
                url: "/lottery/lotteryproductutilList.action",
                data: "",
                success: function (data) {
                    data = eval('(' + data + ')');
                    f(data);
                }
            });
//				GetJPData("http://api.1yyg.com", "GetStartRaffleAllList", "time=" + h, f);
        };
        e();
    };
    d();

    $(".scrollLoading").scrollLoading();
});

