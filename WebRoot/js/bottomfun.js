var mainHttp = $www;
//(function () {
//	if (window.self != window.top) {
//		var b = mainHttp;
//		if (typeof (window.location) == "object") {
//			b = window.location.href;
//		}
//		var a = $("<form name='toTopUrl' method='get' action='" + b + "' target='_top'></form>");
//		a.appendTo("body").ready(function () {
//			a.submit();
//		});
//	}
//})();
//function GetJPData(d, c, a, b) {
//	$.getJSON(d + "/JPData?action=" + c + "&" + a + "&fun=?", b);
//}
//var loadImgFun = function () {
//	var e = "src2";
//	var b = $("#loadingPicBlock");
//	if (b.length > 0) {
//		var c = b.find("img");
//		var d = function () {
//			return Math.max(document.documentElement.scrollTop, document.body.scrollTop);
//		};
//		var a = function () {
//			return document.documentElement.clientHeight + d() + 100;
//		};
//		var f = d();
//		var g = f;
//		var h = function () {
//			c.each(function () {
//				if ($(this).parent().offset().top <= a()) {
//					var i = $(this).attr(e);
//					if (i) {
//						$(this).attr("src", i).removeAttr(e).show();
//					}
//				}
//			});
//		};
//		$(window).bind("scroll", function () {
//			g = d();
//			if (g - f > 50) {
//				f = g;
//				h();
//			}
//		});
//		h();
//	}
//};
var IsCartChanged = true;
(function () {
    var v = mainHttp;
    var P = $www;
    var i = $www;
    var E = $skin;
    var j = $www;
//	loadImgFun();
    var s = function () {
        var W = $("#divRighTool");
        var X = $("body").attr("rf");
        var Z = W.width();
        var ab = 5;
        var Y = 980;
        var U = (Z + ab) * 2 + Y;
        var aa = false;
        var ac = function () {
            if (aa || $(window).scrollTop() > 0) {
                var ad = $(window).width();
                var ae = ad > U ? ((ad - Y) / 2 - Z - ab) : ab;
                W.css("right", ae + "px").fadeIn("slow");
            } else {
                if (!aa) {
                    W.fadeOut("slow");
                }
            }
        };
        var V = function () {
            W.find("dd").eq(0).show();
            ac();
        };
        if (X == "2") {
            aa = true;
            V();
        } else {
            if (X == "1") {
                V();
            }
        }
        $(window).scroll(ac).resize(ac);
        setTimeout(ac, 1000);
    };
    s();
    var M = $("#logininfo");
    var n = M.next();
    var J = n.find("div.h_1ypg_eject");
    n.hover(function () {
        J.show();
    }, function () {
        J.hide();
    });
    var b = n.next();
    var A = $("#logininfo");
    var userId = getCookie("userId");
    var userName = getCookie("userName");
    var phone = getCookie("phone");
    var mail = getCookie("mail");
    var face = getCookie("face");
    if (A.length > 0) {
        if (userId != null && userId != "") {
            if (userName != null && userName != "") {
                A.html("<a class=\"gray01\" href=\"" + $www + "/u/" + userId + ".html\"><img src=\"" + face + "\">" + userName + "</a> <a href=\"" + $www + "/logout/index.html\">[\u9000\u51fa]</a>");
            } else if (mail != null && mail != "") {
//				A.html("\u60a8\u597d\uff0c<span class=\"name\">" + mail + "</span>\u6b22\u8fce\u6765\u52301\u5143\u62CD\u8d2d\uff01<a href=\""+$www+"/logout/index.html\">[\u9000\u51fa]</a>");
                A.html("<a class=\"gray01\" href=\"" + $www + "/u/" + userId + ".html\"><img src=\"" + face + "\">" + mail + "</a> <a href=\"" + $www + "/logout/index.html\">[\u9000\u51fa]</a>");
            } else if (phone != null && phone != "") {
//				A.html("\u60a8\u597d\uff0c<span class=\"name\">" + phone + "</span>\u6b22\u8fce\u6765\u52301\u5143\u62CD\u8d2d\uff01<a href=\""+$www+"/logout/index.html\">[\u9000\u51fa]</a>");
                A.html("<a class=\"gray01\" href=\"" + $www + "/u/" + userId + ".html\"><img src=\"" + face + "\">" + phone + "</a> <a href=\"" + $www + "/logout/index.html\">[\u9000\u51fa]</a>");
            }
        } else {
            A.html("<i>您好，欢迎光临！</i><a class=\"gray01\" href=\"" + $www + "/login/index.html?forward=rego\" rel=\"nofollow\">登录</a><span>|</span><a class=\"gray01\" href=\"" + $www + "/register/index.html?forward=rego\" rel=\"nofollow\">注册</a>");
        }
        //$("#spanFundTotal").html("");
    }
//	if (M.length > 0) {
//		GetJPData(P, "topinfo", "", function (U) {
//			if (U.state != -1) {
//				if (U.state == 1) {
//					$("#liMsgTip").show();
//					M.attr("class", "h_wel").html("<a href=\"http://u.1yyg.com/" + U.userWeb + "\" class=\"gray01\"><img src=\"http://faceimg.1yyg.com/UserFace/30/" + U.userPhoto + "\" />" + U.username + "</a>&nbsp;&nbsp;<a href=\"" + P + "/Logout.html\" class=\"gray01\">[\u9000\u51fa]</a>");
//					b.show();
//				} else {
//					if (U.state == 0) {
//						M.attr("class", "h_login").html("<i>\u60a8\u597d\uff0c\u6b22\u8fce\u5149\u4e34\uff01</i><a rel=\"nofollow\" href=\"" + P + "/login.html?forward=rego\"  class=\"gray01\">\u767b\u5f55</a><span>|</span><a rel=\"nofollow\" href=\"" + P + "/register.html?forward=rego\" class=\"gray01\">\u6ce8\u518c</a></span>");
//					}
//				}
//			}
//		});
//	}
    var a = $("#spBuyCount");
//	var f = $("#spanFundTotal");
    var A = 0;
    //已有多少人参与拍购
    var c = function () {
        $.ajax({
            url: "/getAllBuyCount.html",
            data: "JSON",
            type: "GET",
            success: function (data) {
                if (A != data) {
                    if (A == 0) {
                        A = data;
                        a.html(A);
                    } else {
                        A = data;
                        a.css({color: "white", background: "red"}).html(A);
                        a.animate({opacity: 0.1}, {
                            queue: false, duration: 1000, complete: function () {
                                a.show().css({color: "#22AAFF", background: "#F5F5F5", opacity: 1});
                            }
                        });
                    }
                }
            }
        });
        setTimeout(c, 5000);
    };
    c();
    var h = function (aa) {
        var Y = "iPhone";
        var X = Y;
        var Z = $("#searchKey");
        if (Z.length > 0 && Z.val() != "") {
            X = Z.val();
        }
        var W = function () {
            aa.removeClass("text").unbind("blur").bind("focus", V);
            if (aa.val() == "") {
                aa.val(Y);
            }
        };
        var V = function () {
            aa.addClass("text").unbind("focus").bind("blur", W);
            if (aa.val() == Y) {
                aa.val("");
            }
        };
        var U = function () {
            var ab = $.trim(aa.val());
            ab = ab.replace(/(\/)/g, "");
            ab = ab.replace(/(\%)/g, "");
            ab = ab.replace(/(\&)/g, "");
            if (ab == "") {
                aa.removeClass("text").focus(V).val(Y);
            } else {
                location.href = mainHttp + "/search/hot20/" + encodeURIComponent(ab) + ".html";
            }
        };
        aa.focus(V).keydown(function (ab) {
            if (13 == ((window.event) ? event.keyCode : ab.keyCode)) {
                U();
            }
        }).val(X);
        $("#butSearch").click(U);
    };
    var q = $("#txtSearch");
    if (q.length > 0) {
        h(q);
    }
    var C = $("#addSiteFavorite");
    var F = $("#btnFavorite");
    if (C.length > 0) {
        var u = function () {
            var V = "1元拍购 享受购物的乐趣";
            var U = $www;
            try {
                window.external.addFavorite(U, V);
            } catch (e) {
                try {
                    window.sidebar.addPanel(V, U, "");
                } catch (e) {
                    alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加");
                }
            }
        };
        C.bind("click", function () {
            u();
        });
        F.bind("click", function () {
            u();
        }).hover(function () {
            $(this).addClass("Current");
        }, function () {
            $(this).removeClass("Current");
        });
    }
    ;
//	var y = $("#sp_ServerTime");
//	if (y.length > 0) {
//		var B = function (X) {
//			var V = X.getHours();
//			var U = X.getMinutes();
//			var W = X.getSeconds();
//			return (V > 9 ? V.toString() : "0" + V) + ":" + (U > 9 ? U.toString() : "0" + U) + ":" + (W > 9 ? W.toString() : "0" + W);
//		};
//		var k = 0;
//		var l = new Date();
//		var L = l.getFullYear() + "-" + l.getMonth() + "-" + l.getDate() + " " + B(l);
//		GetJPData(mainHttp, "servertime", "time=" + L, function (V) {
//			if (V.code == 0) {
//				k = V.num;
//			}
//			var U = function () {
//				var W = new Date();
//				W.setSeconds(W.getSeconds() + k);
//				y.html(B(W));
//			};
//			setInterval(U, 1000);
//		});
//	}
    var R = $("#sCartNavi");
    var e = R.children().eq(1);
    var p = 0;
    var m = 0;
    var I = false;
    var K = false;
    var g = false;
    var D = $("#btnMyCart");
    var T = D.parent();
    var t = D.next();
    var r = t.find("ul");
    var z = t.find("p");
    D.hover(function () {
        D.addClass("Current");
    }, function () {
        D.removeClass("Current");
    });
    r.hover(function () {
        D.addClass("Current");
    }, function () {
        D.removeClass("Current");
    });
    var Q = function (U) {
        if (U >= 0) {
            e.html(U);
            if (U > 0) {
                D.html("<b>\u8d2d\u7269\u8f66</b><s></s><em>" + (U > 99 ? "N+" : U) + "</em>");
            } else {
                D.html("<b>\u8d2d\u7269\u8f66</b><s></s>");
            }
        }
    };
    if (T.length > 0) {
        var G = $("#rCartLoading");
        T.hover(function () {
            if (I) {
                return;
            }
            I = true;
            var V = parseInt(e.html());
            if (V == 0) {
                return;
            }
            var U = function (W) {
                t.show();
                if (W) {
                    t.children().hide();
                    G.show();
                }
                $.ajax({
                    url: "/mycart/getMyProductCart.html",
                    type: "GET",
                    success: function (data) {
                        data = eval("(" + data + ")");
                        if (data.length > 0) {
                            var Y = function (af, ae) {
                                z.html("\u5171\u8ba1<span id=\"rCartTotal2\">" + af + "</span> \u4ef6\u5546\u54c1 \u91d1\u989d\u603b\u8ba1\uff1a<span class=\"rmbgray\" id=\"rCartTotalM\">" + ae + ".00</span>").show();
                            };
                            if (data.length > 0) {
                                p = data.length;
                                var ac = data;
                                var aa = "";
                                var ad = data.length;
                                var ab = ad > 8 ? 7 : (ad == 8 ? 8 : ad);
                                for (var Z = 0; Z < ab; Z++) {
                                    aa += "<li><a href=\"javascript:;\" title=\"\u5220\u9664\" class=\"Close\"></a><a href=\"" + mainHttp + "/products/" + ac[Z].productId + ".html\"><img src=\"" + $img + ac[Z].headImage + "\" title=\"" + ac[Z].productTitle + "\" ></a><span class=\"orange\">" + ac[Z].count + "</span>\u4eba\u6b21<input type=\"hidden\" value=" + ac[Z].productId + "><input type=\"hidden\" value=" + ac[Z].count + "></li>";
                                }
                                if (ad > 8) {
                                    aa += "<li class=\"Roll_CartMore\"><a target=\"_blank\" title=\"\u67e5\u770b\u66f4\u591a\" href=\"/mycart/index.html\">\u66f4\u591a<i>></i></a></li>";
                                }
                                p = data[ad - 1].productCount;
                                m = data[ad - 1].moneyCount;
                                Y(p, m);
                                Q(p);
                                r.show().html(aa);
                                G.hide();
                            }
                            $(".Close", r).bind("click", function () {
                                var af = $(this).parent();
                                var ae = af.find("input[type=hidden]").eq(0).val();
                                var ag = af.find("input[type=hidden]").eq(1).val();
                                var json = getCookie("products");
                                if (json != null || json != "") {
                                    json = eval('(' + json + ')');
                                    var i = 0;
                                    $.each(json, function (n, value) {
                                        if (ae == value.pId) {
                                            i = n;
                                        }
                                    });
                                    json.splice(i, 1);
                                    json = JSON.stringify(json);
                                    SetCookieByExpires("products", json, 1);

                                    IsCartChanged = true;
                                    if (p > 8) {
                                        Q(p - 1);
                                        U(false);
                                    } else {
                                        p -= 1;
                                        m -= ag;
                                        Q(p);
                                        if (p == 0) {
                                            t.hide();
                                        } else {
                                            Y(p, m);
                                            af.remove();
                                        }
                                    }
                                } else {
                                    alert("\u5220\u9664\u5931\u8d25!");
                                    location.reload();
                                }
                            });
                            $("#rGotoCart").unbind("click").bind("click", function () {
                                location.href = mainHttp + "/mycart/index.html";
                            }).parent().show();
                        } else {
                            Q(0);
                            t.hide();
                        }
                    }
                });
                IsCartChanged = false;
            };
            if (IsCartChanged) {
                K = true;
                U(true);
            } else {
                if (g) {
                    U(true);
                    g = false;
                } else {
                    t.show();
                }
            }
        }, function () {
            I = false;
            D.next().hide();
        });
    }
    ;
    if (R.length > 0) {
        $.ajax({
            url: "/mycart/getProductCartCount.html",
            type: "GET",
            success: function (data) {
                Q(data);
                var X = $("#sCartlist");
                var V = $("#sCartLoading");
                var Y = $("#p1");
                var U = function () {
                    var Z = function (ab, aa) {
                        Y.html("\u5171\u8ba1 <span id=\"sCartTotal2\"> " + ab + "</span> \u4ef6\u5546\u54c1 \u91d1\u989d\u603b\u8ba1\uff1a<span id=\"sCartTotalM\" class=\"rmbred\">" + aa + ".00</span>");
                    };
                    X.show().find("dl").remove();
                    V.show();
                    $.ajax({
                        url: "/mycart/getMyProductCart.html",
                        data: "JSON",
                        type: "GET",
                        success: function (data) {
                            data = eval("(" + data + ")");
                            if (data.length > 0) {
                                if (data[0].count > 0) {
                                    var ad = data;
                                    var ac = "";
                                    for (var ab = 0; ab < ad.length; ab++) {
                                        ac += "<dl><dt class=\"img\"><a href=\"" + mainHttp + "/products/" + ad[ab].productId + ".html\"><img src=\"" + $img + ad[ab].headImage + "\" /></a></dt><dd class=\"title\"><a href=\"" + mainHttp + "/products/" + ad[ab].productId + ".html\">" + ad[ab].productName + "</a><span class=\"rmbred\">1.00 \xd7 " + ad[ab].count + "</span></dd><dd class=\"del\"><a class=\"delGood\" href=\"javascript:;\" >\u5220\u9664</a></dd><input type=\"hidden\" value=" + ad[ab].productId + "><input type=\"hidden\" value=" + ad[ab].productCount + "></dl>";
                                    }
                                    p = data[(ad.length - 1)].productCount;
                                    m = data[(ad.length - 1)].moneyCount;
                                    Z(p, m);
                                    Q(p);
                                    V.hide();
                                    X.prepend(ac).find("dl").hover(function () {
                                        $(this).addClass("mycartcur");
                                    }, function () {
                                        $(this).removeClass("mycartcur");
                                    });
                                }
                                $(".delGood", X).bind("click", function () {
                                    var af = $(this).parent().parent();
                                    var ae = af.find("input[type=hidden]").eq(0).val();
                                    var ag = af.find("input[type=hidden]").eq(1).val();

                                    var json = getCookie("products");
                                    if (json != null || json != "") {
                                        json = eval('(' + json + ')');
                                        var i = 0;
                                        $.each(json, function (n, value) {
                                            if (ae == value.pId) {
                                                i = n;
                                            }
                                        });
                                        json.splice(i, 1);
                                        json = JSON.stringify(json);
                                        SetCookieByExpires("products", json, 1);
                                        IsCartChanged = true;
                                        af.remove();
                                        p -= 1;
                                        m -= ag;
                                        Z(p, m);
                                        Q(p);

                                        if (p == 0) {
                                            X.hide();
                                        } else {
                                            X.show();
                                        }

                                    } else {
                                        alert("\u5220\u9664\u5931\u8d25!");
                                        location.reload();
                                    }
                                });
                                $("#sGotoCart").bind("click", function () {
                                    location.href = mainHttp + "/mycart/index.html";
                                });
                            } else {
                                Q(0);
                                X.hide();
                            }
                        }
                    });

                    IsCartChanged = false;
                };
                $("#sCart").hover(function () {
                    if (I) {
                        return;
                    }
                    I = true;
                    var Z = parseInt(e.html());
                    if (Z == 0) {
                        return;
                    }
                    if (IsCartChanged) {
                        g = true;
                        U();
                    } else {
                        if (K) {
                            U();
                            K = false;
                        } else {
                            X.show();
                        }
                    }
                }, function () {
                    I = false;
                    X.hide();
                });
            }
        });
    }
    ;
//	var x = null;
//	var O = function () {
//		var U = function () {
//			var ag = $.cookie("_msgFApply");
//			var ai = $.cookie("_msgSys");
//			var aa = $.cookie("_msgFPriv");
//			var W = 0;
//			var af = "";
//			var ad = "";
//			var Z = function (ak, al, aj) {
//				return "<a href=\"" + i + ak + "\">" + al + (aj && parseInt(aj) > 0 ? "(<span class=\"orange Fb\">" + (parseInt(aj) > 99 ? "99+" : aj) + "</span>)" : "") + "</a>";
//			};
//			var ab = function (ak, al, aj) {
//				return "<p><a href=\"" + i + ak + "\">\u67e5\u770b</a>\u60a8\u6709<span class=\"orange\">" + aj + "</span>\u6761" + al + "</p>";
//			};
//			if (ag && parseInt(ag) > 0) {
//				W += parseInt(ag);
//				ad += ab("/FriendsApply.html", "\u597d\u53cb\u7533\u8bf7", ag);
//			}
//			if (ai && parseInt(ai) > 0) {
//				W += parseInt(ai);
//				ad += ab("/UserMessage.html", "\u65b0\u7cfb\u7edf\u6d88\u606f", ai);
//			}
//			if (aa && parseInt(aa) > 0) {
//				W += parseInt(aa);
//				ad += ab("/UserPrivMsg.html", "\u65b0\u79c1\u4fe1", aa);
//			}
//			af += Z("/FriendsApply.html", "\u597d\u53cb\u8bf7\u6c42", ag);
//			af += Z("/UserMessage.html", "\u7cfb\u7edf\u6d88\u606f", ai);
//			af += Z("/UserPrivMsg.html", "\u79c1\u4fe1", aa);
//			var X = $.cookie("_msgTip");
//			b.hover(function () {
//				if (x != null) {
//					x.remove();
//					x = null;
//				}
//				b.find("div.h_news_down").show();
//			}, function () {
//				b.find("div.h_news_down").hide();
//			});
//			b.find("div.h_news_downC").html(af);
//			if (W > 0 && X == null) {
//				if (x != null) {
//					x.remove();
//				}
//				var ae = "<div class=\"h_news_tips\"><div class=\"h_newsarrow\"><a href=\"javascript:;\" class=\"tipsCancel\"></a></div>" + ad + "</div>";
//				x = $(ae);
//				var ac = b.offset();
//				var Y = ac.left - 38;
//				var ah = ac.top + b.height() + 7;
//				x.find("a").click(function () {
//					$.cookie("_msgTip", 1, {domain:"1yyg.com"});
//					x.remove();
//					x = null;
//				});
//				$("body").append(x);
//				x.css({left:Y, top:ah, zIndex:22}).show();
//			} else {
//				if (x != null) {
//					x.remove();
//					x = null;
//				} else {
//					b.hover(function () {
//						b.find("div.h_news_down").show();
//					}, function () {
//						b.find("div.h_news_down").hide();
//					});
//				}
//			}
//		};
//		var V = function () {
//			if (H) {
//				return;
//			}
//			GetJPData(P, "ckmsg", "", function (W) {
//				if (W.state == 0) {
//					$.cookie("_msgTip", null, {domain:"1yyg.com"});
//					U();
//				}
//				setTimeout(V, 10000);
//			});
//		};
//		V();
//	};
//	var d = false;
//	var H = false;
//	var w = true;
//	var o = function () {
//		if (M.html() != "" && M.attr("class") == "h_wel" && d) {
//			O();
//		} else {
//			setTimeout(o, 1000);
//		}
//	};
//	var N = function () {
//		d = true;
//		o();
//		var U = function (X) {
//			var W = new Date();
//			X.attr("src", X.attr("data") + "?v=" + GetVerNum()).removeAttr("id").removeAttr("data");
//		};
//		var V = $("#pageJS", "head");
//		if (V.length > 0) {
//			U(V);
//		} else {
//			V = $("#pageJS", "body");
//			if (V.length > 0) {
//				U(V);
//			}
//		}
//	};
//	$.getScript(E + "/js/comm.js", N);
//	var S = $("#topJackaroo");
//	if (S.length > 0) {
//		GetJPData("http://poster.1yyg.com", "getbysortid", "ID=3", function (V) {
//			if (V.state == 0) {
//				var U = V.listItems[0];
//				if (U.type == 0) {
//					S.html("<a href=\"" + U.url + "\" target=\"_blank\" title=\"" + U.alt + "\"><img width=\"" + U.width + "\" height=\"" + U.height + "\" src=\"" + U.src + "\" /></a>");
//				} else {
//					S.html("<embed src=\"" + U.src + "\" wmode=\"Transparent\" width=\"" + U.width + "\" height=\"" + U.height + "\" quality=\"high\" pluginspage=\"http://www.adobe.com/shockwave/download/download.cgi?p1_prod_version=shockwaveflash\" type=\"application/x-shockwave-flash\"></embed>");
//				}
//			}
//		});
//	}
    $("#btnRigQQ").hover(function () {
        $(this).addClass("Current");
    }, function () {
        $(this).removeClass("Current");
    });
    $("#btnGotoTop").click(function () {
        $("body,html").animate({scrollTop: 0}, 0);
        return false;
    }).hover(function () {
        $(this).addClass("Current");
    }, function () {
        $(this).removeClass("Current");
    });
    $(".go_cart").click(function () {
        IsCartChanged = true;
    });

    var ab = $("#divWechat");
    var aj = ab.width();
    var ah = 5;
    var ac = 980;
    var ad = ab.children("a");
    var af = getCookie("_wxTip") != "off";
    var W = function () {
        if (af) {
            ad.click(function () {
                ab.fadeOut("slow");
                SetCookie("_wxTip", "off");
            });
            var al = $(window).width();
            var ak = (aj + ah) * 2 + ac;
            var am = al > ak ? ((al - ac) / 2 - aj - ah) - 2 : ah;
            ab.css("right", am + "px").fadeIn("slow");
        }
    };
    W();

//	var Z = getCookie("referralHAD");
//	if (Z != "off") {
//		var V = "<div style=\"background:#5fd1ff;\"><div style=\"background:url('"+$img+"/Images/bannerAdTest.png') center no-repeat; width:980px; height:40px; margin:0 auto; position:relative; \"><a id=\"referADLink\" style=\" display:block; width:980px; height:40px;\" title=\"\" target=\"_blank\" href=\"http://www.1ypg.com/news/1021.html\"></a><a class=\"closeBtn\" style=\" display:block; width:12px; height:11px; position:absolute; right:8px; top:1px; z-index:5;\" title=\"\u5173\u95ed\" href=\"javascript:;\"></a></div></div>";
//		var X = $(V);
//		var W = function () {
//			X.find("a.closeBtn").click(function () {
//				SetCookie("referralHAD","off");
//				X.remove();
//			});
//		};
//		$("body").prepend(X).ready(W);
//	}
//	$.getScript(mainHttp + "/js/json2.js");
})();

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
