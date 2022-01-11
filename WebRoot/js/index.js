$(document).ready(function () {
    var banner = $(".banner");
    var I = $("#hidBuyID").val();
    var SI = $("#slideImg");
    var imgShow = [
        "<a target=\"_blank\" href=\"" + $www + "/search/hot20/iphone.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\"" + $img + "/Images/iphone5s.jpg\"/></a>",
        "<a target=\"_blank\" href=\"" + $www + "/search/hot20/macbook.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\"" + $img + "/Images/index_2.jpg\"/></a>",
//		"<a target=\"_blank\" href=\""+$www+"/search/hot20/samsung.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_3.jpg\"/></a>",
//		"<a target=\"_blank\" href=\""+$www+"/search/hot20/nikon.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_4.jpg\"/></a>",
//		"<a target=\"_blank\" href=\""+$www+"/search/hot20/ipad.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_6.jpg\"/></a>",
//		"<a target=\"_blank\" href=\""+$www+"/search/hot20/sony.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_7.jpg\"/></a>",
//		"<a target=\"_blank\" href=\""+$www+"/search/hot20/松下.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_8.jpg\"/></a>",
//		"<a target=\"_blank\" href=\""+$www+"/search/hot20/18K.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_9.jpg\"/></a>",
//		"<a target=\"_blank\" href=\""+$www+"/search/hot20/coach.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_11.jpg\"/></a>",
//		"<a target=\"_blank\" href=\""+$www+"/search/hot20/周生生.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_5.jpg\"/></a>"
    ];
    for (var i = 1; i < imgShow.length + 1; i++) {
        banner.find("ul").append("<li class=\"\">" + i + "</li>");
    }
    var number = 1;
    SI.html(imgShow[0]);
    banner.find("li").eq(0).attr("class", "on");
    var E = function (c) {
        SI.css({"opacity": "0"});
        SI.html(imgShow[c]);
        number++;
        SI.animate({"opacity": "1"}, 500);
        banner.find("li").attr("class", "");
        banner.find("li").eq(c).attr("class", "on");
        if (number == imgShow.length) {
            number = 0;
        }
    };
    var P = setInterval(function () {
        E(number)
    }, 5000);
    SI.hover(
        function () {
            clearInterval(P);
            P = null;
        },
        function () {
            P = setInterval(function () {
                E(number)
            }, 5000);
        }
    );
    banner.find("li").each(function () {
        var i = $(this).html();
        $(this).hover(
            function () {
                clearInterval(P);
                P = null;
                banner.find("li").attr("class", "");
                $(this).attr("class", "on");
                E(i - 1);
                number = i - 1;
                if (number == 2) {
                    number = 0;
                }
            },
            function () {
                P = setInterval(function () {
                    E(number)
                }, 5000);
            }
        );
    });

    var a = $www;
    $.fn.showSlide = function () {
        var k = $(this);
        var e = k.next().children("a");
        var j = e.size();
        if (j > 1) {
            var m = $("<ul></ul>");
            for (var g = 1; g <= j; g++) {
                m.append("<li>" + g + "</li>");
            }
            k.parent().append(m);
            var l = m.children("li");
            var h = 0;
            var d = 5000;
            var f = function () {
                k.stop(true, false).animate({opacity: 0.2}, 200, function () {
                    k.empty().append(e.eq(h)).animate({opacity: 1}, 100);
                    l.eq(h).addClass("on").siblings().removeClass("on");
                    h++;
                    if (h == j) {
                        h = 0;
                    }
                });
            };
            var c = setInterval(f, d);
            l.hover(function () {
                if (c) {
                    clearInterval(c);
                }
                h = l.index(this);
                f();
            }, function () {
                c = setInterval(f, d);
            });
            this.hover(function () {
                if (c) {
                    clearInterval(c);
                }
            }, function () {
                c = setInterval(f, d);
            });
            f();
        } else {
            if (j > 0) {
                k.html(k.next().html());
            }
        }
    };
//	var b = function () {
//		isLoaded = true;
//		$("div.Progress-bar").each(function (t) {
//			var p = $(this);
//			var s = p.find("p");
//			var q = p.find("ul li em");
//			var u = parseInt(q.eq(0).html());
//			var w = parseInt(q.eq(1).html());
//			var o = w - u;
//			var x = parseFloat(u) / w;
//			s.attr("title", "\u5bb8\u63d2\u756c\u93b4\ufffd" + formatFloat(x * 100) + "%");
//			q.eq(2).html(o);
//			p.show();
//			var v = parseInt(s.width()) - 2;
//			var r = parseInt(v * x);
//			if (u > 0) {
//				s.find("span").width(r);
//			} else {
//				s.find("span").hide();
//			}
//		});
//		var d = ",";
//		var n = false;
//		var i = 0;
//		var j = function () {
//			GetJPData("http://dataserver.1yyg.com", "GetStartRaffleAllList", "time=" + i, function (p) {
//				if (p.errorCode == 0) {
//					o(p);
//				}
//				setTimeout(j, 5000);
//			});
//			var o = function (q) {
//				i = q.maxSeconds;
//				var p = function (u) {
//					var t = $("#ulNewAwary");
//					for (var r = u.length - 1; r > -1; r--) {
//						var s = u[r];
//						if (d.indexOf("," + s.codeID + ",") < 0) {
//							d += s.codeID + ",";
//							var v = $("<li class=\"countdown_li\"><a href=\"http://dataserver.1yyg.com/LotteryDetail-" + s.codeID + ".html\" target=\"_blank\" class=\"pic\"><img src=\"http://goodsimg.1yyg.com/GoodsPic/pic-200-200/" + s.goodsPic + "\"></a><a href=\"http://dataserver.1yyg.com/lotterydetail-" + s.codeID + ".html\" target=\"_blank\" class=\"name\">" + s.goodsSName + "</a><div class=\"countdown_div\"><img src=\"" + a + "/Images/zhong.gif\"><span class=\"wenzi\">\u934a\u6395\ue178\u93c3\ufffd:</span><span class=\"shi\"><span class=\"minute\">00</span>:<span class=\"second\">00</span>:<span class=\"millisecond\">0</span><span class=\"last\">0</span></span></div><div class=\"newawary\"></div></li>");
//							t.find("li:gt(2)").remove();
//							t.prepend(v);
//							v.StartTimeOut(s.codeID, parseInt(s.seconds));
//						}
//					}
//				};
//				if (n) {
//					p(q.listItems);
//				} else {
//					$.getScript(a + "/js/indexlotteryfun.js", function () {
//						n = true;
//						p(q.listItems);
//					});
//				}
//			};
//		};
//		j();
//		var c = function () {
//			var p = "getbysortid";
//			var o = function (r, q) {
//				GetLogoInfo(r, p, false, function (s) {
//					$("#" + q).html(s);
//				});
//			};
//			o(10, "posterTopNav");
//			GetLogoInfo(2, p, false, function (q) {
//				$("#posterBanner").html(q).ready(function () {
//					getobj("slideImg").showSlide();
//				});
//			});
//			o(4, "divLottery");
//		};
//		$.getScript(a + "/js/posterfun.js", c);
//		var l = $("#hidBuyID").val();
//		var h = function () {
//			GetJPData("http://dataserver.1yyg.com", "GetUserBuyNewList", "buyID=" + l, function (o) {
//				if (o.code == 0) {
//					k(o);
//				}
//			});
//		};
//		setInterval(h, 10000);
//		var g = 0;
//		var m = $("#buyList");
//		var k = function (q) {
//			var p = "";
//			g = q.listItems.length;
//			for (var o = 0; o < g; o++) {
//				p += "<li><a href=\"/products/" + q.listItems[o].goodsID + ".html\" class=\"pic\" target=\"_blank\"><img src=\"http://goodsimg.1yyg.com/GoodsPic/pic-70-70/" + q.listItems[o].goodsPic + "\" /></a><span class=\"who\"><p style=\"display: inline;\"><a class=\"blue\" href=\"http://u.1yyg.com/" + q.listItems[o].userWeb + "\">" + q.listItems[o].userName + "</a></p>\u9352\u6c2c\u57b0\u6d5c\u6223\u5598\u6d5c\ufffd</span><span><a href=\"/products/" + q.listItems[o].goodsID + ".html\" class=\"name\" target=\"_blank\">" + q.listItems[o].goodsName + "</a></span></li>";
//			}
//			m.find("li:gt(" + (m.find("li").length - g - 1) + ")").remove();
//			m.append(p);
//			l = q.listItems[g - 1].buyID;
//		};
//		var f = function () {
//			m.prepend(m.find("li:last")).css("marginTop", "-85px");
//			m.animate({marginTop:"0px"}, 800);
//		};
//		var e = setInterval(f, 3000);
//		m.hover(function () {
//			clearInterval(e);
//			e = null;
//		}, function () {
//			e = setInterval(f, 3000);
//		});

    var J = $("#buyList");
    $.ajax({
        url: "/getNowBuyProduct.action?pageNo=" + 1 + "&pageSize=" + 12,
        type: "GET",
        data: "JSON",
        success: function (data) {
            var M = "";
            data = eval("(" + data + ")");
            for (var L = 0; L < data.length; L++) {
                M += "<li><a href=\"" + $www + "/products/" + data[L].productId + ".html\" class=\"pic\" title=\"" + data[L].productName + "\" target=\"_blank\"><img src=\"" + $img + data[L].headImage + "\" /></a><span class=\"who\"><p style=\"display: inline;\"><a class=\"blue\" href=\"" + $www + "/u/" + data[L].userId + ".html\" target=\"_blank\">" + data[L].buyer + "</a></p>\u521a\u521a\u62cd\u8d2d\u4e86</span><span><a href=\"" + $www + "/products/" + data[L].productId + ".html\" class=\"name\" title=\"" + data[L].productTitle + "\" target=\"_blank\">" + data[L].productName + "</a></span></li>";
            }
            J.find("li:gt(" + (J.find("li").length - data.length - 1) + ")").remove();
            J.append(M);
        }
    });

    var C = function () {
        J.prepend(J.find("li:last")).css("marginTop", "-85px");
        J.animate({"marginTop": "0px"}, 800);
    };
    var B = setInterval(C, 3000);
    J.hover(function () {
        clearInterval(B);
        B = null;
    }, function () {
        B = setInterval(C, 3000);
    });

//		$("#ulNewAwary").find("li").hover(function () {
//			$(this).addClass("hover");
//		}, function () {
//			$(this).removeClass("hover");
//		});
    $("#hostGoodsItems").find("li.list-block").hover(function () {
        $(this).addClass("cursor");
    }, function () {
        $(this).removeClass("cursor");
    });
    $("#readyLotteryItems").find("li.list-block").hover(function () {
        $(this).addClass("cursor");
    }, function () {
        $(this).removeClass("cursor");
    });
//	};
//	$.getScript(a + "/js/ajaxfun.js", b);

    var loaded = false;

    function show() {
        var top = $(".share_show").offset().top;
        if (!loaded && $(window).scrollTop() + $(window).height() > top) {
            $.ajax({
                url: "/share/ajaxPage.action?pageNo=0&pageSize=8&id=new20",
                type: "GET",
                data: "JSON",
                success: function (data) {
                    data = eval("(" + data + ")");
                    for (var i = 0; i < data.length; i++) {
                        if (i >= 0 && i <= 1) {
                            $(".show_list").before("<dl><dt><a target=\"_blank\" href=\"" + $www + "/shareShow/" + data[i].uid + ".html\">" +
                                "<img alt=\"\" src=\"" + $img + "/UserPost/220/" + data[i].shareImages + "\"></a></dt>" +
                                "<dd class=\"bg\"><ul><li class=\"name\"><a target=\"_blank\" href=\"" + $www + "/shareShow/" + data[i].uid + ".html\">" + data[i].shareTitle + "</a><br /> 获得者：<a target=\"_blank\" href=\"" + $www + "/u/" + data[i].userId + ".html\" class=\"blue\" rel=\"nofollow\">" + data[i].userName + "</a></li><li class=\"content\">" + data[i].shareContent + "…" + "</li></ul></dd>" +
                                "</dl>");
                        } else {
                            $("<ul><li class=\"pic\"><a href=\"" + $www + "/shareShow/" + data[i].uid + ".html\">" +
                                "<img alt=\"\" src=\"" + $img + "/UserPost/220/" + data[i].shareImages + "\"></a></li>" +
                                "<li><a target=\"_blank\" title=\"" + data[i].shareTitle + "\" href=\"" + $www + "/shareShow/" + data[i].uid + ".html\">" + data[i].shareTitle + "</a></li><li>获得者：<a target=\"_blank\" href=\"" + $www + "/u/" + data[i].userId + ".html\" class=\"blue\" rel=\"nofollow\">" + data[i].userName + "</a></li><li>揭晓时间：" + data[i].announcedTime + "</li>" +
                                "</ul>").appendTo($(".show_list"));
                        }
                    }
                }
            });
            loaded = true;
        }
    };
    $(window).scroll(show);
    $(".scrollLoading").scrollLoading();
});

