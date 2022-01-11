var BuyFun = null;
var PostFun = null;
var PagePOPLoginOK = null;
var CBLPageFun = null;
var jiathis_config = {url: location.href + "?s=shared", title: document.title};
$(document).ready(function () {
    var n = $www;
    var o = $("#hidCodeID").val();
    var e = $("#hidGoodsID").val();
    var g = parseInt($("#hidLogined").val()) == 1;
    var c = parseInt($("#hidIsEnd").val()) == 1;
    var i = parseInt($("#hidIsStart").val()) == 1;
    var a = false;
    var f = $("#btnMyCart");
    var k = function (q, s) {
        var u, w, p = "";
        var v = String(q * 100 / s);
        if (v.indexOf(".") == -1) {
            u = v;
            w = "00";
        } else {
            u = v.substring(0, v.indexOf("."));
            w = v.substring(v.indexOf(".") + 1) + "0";
        }
        for (var r = 0; r < w.length; r++) {
            var t = w.substring(r, r + 1);
            p += t;
            if (t != "0" && r > 0) {
                break;
            }
        }
        return u + "." + p + "%";
    };

    var m = $("#divNumber");
    var h = $("#divBuy");
    var d = function (F, x) {
        var r = parseInt($("#CodeQuantity").html());
        var B = parseInt($("#CodeLift").html());
        var D = F.find("input.num_dig");
        var q = $("#chance");
        var A = 1;
        var y = /^\d+$/;
        var v = function (H, G) {
            q.html("<font color=\"ff6600\">" + H + "</font>");
            A = G;
            D.val(G);
        };
        var p = function (G) {
            A = G;
            D.val(G);
            var H = k(G, r);
            q.html("<font color='red'>\u83b7\u5f97\u673a\u7387 <b>" + H + "</b></font>");
        };
        D.bind("keyup", function () {
            var G = D.val();
            if (!y.test(G)) {
                p(A);
            } else {
                if (G == 0) {
                    v("\u6700\u5c11\u9700\u62cd\u8d2d1\u4eba\u6b21", 1);
                } else {
                    if (G <= B) {
                        p(G);
                    } else {
                        v("\u672c\u671f\u6700\u591a\u53ef\u62cd\u8d2d" + B + "\u4eba\u6b21", B);
                    }
                }
            }
        }).mouseover(function () {
            $(this).focus().select();
        });
        var E = F.find("a").eq(0);
        var s = F.find("a").eq(1);
        var C = function (H) {
            var G = D.val();
            if (y.test(G)) {
                if (H == "add") {
                    if (G < B) {
                        G++;
                        if (G == B) {
                            s.addClass("num_ban");
                        }
                        E.removeClass("num_ban");
                    }
                } else {
                    if (G > 1) {
                        G--;
                        if (G == 1) {
                            E.addClass("num_ban");
                        }
                        s.removeClass("num_ban");
                    }
                }
                p(G);
            }
        };
        E.click(function () {
            C("sub");
        });
        s.click(function () {
            C("add");
        });
        var w = x.find("a").eq(0);
        var u = function () {
            var G = D.val();
            if (y.test(G)) {
                //请求是否还有余购
//				var H = new JQAjax("/DataServer/ShopCartNew");
//				H.OnSuccess(function (I) {
//					var K = "<input type=\"button\" id=\"dgSubmit\" class=\"button_2\" /><input type=\"button\" id=\"dgCancle\" class=\"button_3\" />";
//					var O = function (Q) {
//						var S = $("#pageDialog"), R = $("#pageDialogMain");
//						if (Q == 1) {
//							S.css("border-color", "#5db158");
//							R.css("background-color", "#f9fff4");
//						} else {
//							S.css("border-color", "#fff");
//							R.css("background-color", "#fff");
//						}
//					};
//					var N = I.d.Code;
//					if (N == 0) {
//						location.href = "/MyCart/CartList.html";
//					} else {
//						var M = "";
//						var J = "";
//						if (N == 1) {
//							M = "<b>\u554a\u54e6\uff01\uff01\u5269\u4e0b\u7684\u88ab\u62a2\u5149\u4e86\uff01</b><span>\u6b63\u5728\u5f00\u5956\u54e6\uff0c\u53bb\u56f4\u89c2\u4e00\u4e0b\u770b\u770b\u8c01\u4e2d\u5956\u4e86\uff01</span>";
//							J = "<a class=\"detail_Promptbut\" href=\"/product/" + o + ".html\">\u53bb\u56f4\u89c2</a>";
//						} else {
//							M = "<b>\u5bf9\u4e0d\u8d77\uff0c\u62cd\u8d2d\u5931\u8d25\uff01</b><span>&nbsp;</span>";
//							J = "<a class=\"detail_Promptbut\" href=\"javascript:$.PageDialog.close()();\">\u5173 \u95ed</a>";
//						}
//						var L = "<div class=\"detail_Prompt\"><dl><dt><img border=\"0\" alt=\"\" src=\"http://skin.1yyg.com/images/Prompt_icon.jpg\"></dt><dd>" + M + J + "</dd></dl></div>";
//						var P = function () {
//							O(0);
//							$("#pageDialog").unbind("click").bind("click", function () {
//								location.href = "/product/" + o + ".html";
//							});
//						};
//						$.PageDialog(L, {W:452, H:200, obj:w, oT:-226, ready:P});
//					}
//				});
//				H.OnSend("{'codeID':" + o + ",'num':" + G + "}", "json", true);
            } else {
                v("\u60a8\u8f93\u5165\u7684\u4eba\u6b21\u6709\u8bef\uff01", 1);
            }
        };
        w.unbind("click").bind("click", function () {
            u();
        });
        var t = $("#imgGoodsPic");
        var z = function () {
            var H = D.val();
            var G = /^\d+$/;
            if (G.test(H)) {
                var codeid = o;
                var json = getCookie("products");
                if (json == null || json == "") {
                    var val = "[{'pId':" + codeid + ",'num':1}]";
                    SetCookieByExpires("products", val, H);
                } else {
                    var flag = false;
                    json = eval('(' + json + ')');
                    $.each(json, function (n, value) {
                        if (codeid == value.pId) {
                            value.num = parseInt(value.num + 1);
                            flag = true;
                        }
                    });
                    if (!flag) {
                        json.push({"pId": +codeid, "num": H});
                    }
                    json = JSON.stringify(json);
                    SetCookieByExpires("products", json, 1);
                }
                $.ajax({
                    url: "/mycart/getProductCartCount.html",
                    type: "GET",
                    success: function (data) {
                        IsCartChanged = true;
                        $("#sCartTotal").html(data);
                        J(data);
                    }
                });
            }
            var J = function (K) {
                var L = $("<div id=\"cart_shadow\" style=\"display: none;border:1px solid #aaa;z-index: 9999;\"><img src=\"" + t.attr("src") + "\" width=\"100%\" /></div>").prependTo("body");
                L.css({
                    width: t.css("width"),
                    height: t.css("height"),
                    position: "absolute",
                    top: $("#middlePicBox").offset().top,
                    left: $("#middlePicBox").offset().left
                }).show();
                L.animate({
                    width: f.width(),
                    height: f.height(),
                    top: f.offset().top,
                    left: f.offset().left,
                    opacity: 0.5
                }, {
                    queue: false, duration: 600, complete: function () {
                        L.remove();
                        f.html("<b>\u8d2d\u7269\u8f66</b><s></s><em>" + (K > 99 ? "N+" : K) + "</em>");
                    }
                });
            };
        };
        x.find("a").eq(1).click(function () {
            z();
            return false;
        });
        $("#btnAdd2Cart").click(function () {
            z();
            return false;
        });
    };

    d(m, h);
    /////////////////////////////////////////////
    var b = function () {
        var p = $("#btnOpenPeriod");
        var E = false;
        if (p.length > 0) {
            p.click(function () {
                if (E) {
                    E = false;
                    $(this).parent().parent().css("height", "99px");
                    $(this).html("\u5c55\u5f00<i></i>");
                    if ($(window).scrollTop() > $("div.Current_nav").offset().top + 25) {
                        $("body,html").animate({scrollTop: 0}, 0);
                    }
                } else {
                    E = true;
                    $(this).parent().parent().css("height", "");
                    $(this).html("\u6536\u8d77<s></s>");
                }
                return false;
            });
        }
        this.checkUserLoginFunEx = function (S) {
            if (g) {
                S();
            } else {
                var T = new popLogin();
                PagePOPLoginOK = function () {
                    g = true;
                    T.hide();
                    S();
                };
                T.show();
            }
        };
        var G = false;
        var R = function () {
            if (g && !G) {
                //我的拍购记录
//				GetJPData("http://www.1yyg.com", "getUserBuyList", "codeID=" + o + "&quantity=8", function (T) {
//					if (T.code == 0) {
//						var W = T.data;
//						var S = W.length;
//						if (S > 0) {
//							var V = "<ul>";
//							for (var U = S - 1; U > -1; U--) {
//								V += "<li>" + W[U].buyTime + " \u62cd\u8d2d\u4e86 " + W[U].buyNum + " \u4e2a\u62cd\u8d2d\u7801</li>";
//							}
//							V += "</ul>";
//							if (S >= 8) {
//								V += "<p><a href=\"http://member.1yyg.com/UserBuyDetail-" + o + ".html\" target=\"_blank\" class=\"gray01\">\u67e5\u770b\u6240\u6709\u62cd\u8d2d\u7801>></a></p>";
//							}
//							t.html(V);
//						} else {
//							t.html("<div class=\"tips-con\"><i></i>\u60a8\u8fd8\u672a\u6709\u8d2d\u4e70\u6b64\u5546\u54c1\u54e6\uff01</div>");
//						}
//						G = true;
//					} else {
//						if (T.Code == 1) {
//							t.html("<div class=\"tips-con\"><i></i>\u60a8\u8fd8\u6ca1\u6709\u8d2d\u4e70\u672c\u671f\u5546\u54c1\u54e6\uff01</div>");
//						} else {
//							if (T.Code == -2) {
//								t.html("<div class=\"tips-con\"><i></i>\u52a0\u8f7d\u5931\u8d25\uff0c\u8bf7\u5237\u65b0\uff01</div>");
//							}
//						}
//					}
//				});
            }
        };
        var C = $("#divProductNav");
        var y = $("#divContent");
        var K = $("#divBuyRecord");
        var F = $("#divPost");
        C.find("li").each(function (S, T) {
            var U = $(this);
            if (S == 1 && c) {
                U.remove();
            } else {
                U.click(function () {
                    if (A) {
                        $("body,html").animate({scrollTop: D - 1}, 0);
                    }
                    Q(U, S);
                });
                if (a && S == 1) {
                    U.addClass("DetailsTCur").siblings().removeClass("DetailsTCur");
                    y.hide();
                    K.show().next().hide();
                    F.hide().prev().hide();
                }
            }
        });
        var Q = function (T, S) {
            T.addClass("DetailsTCur").siblings().removeClass("DetailsTCur");
            switch (S) {
                case 0:
                    y.show();
                    K.hide();
                    F.hide();
//				K.show().prev().show();
//				F.show().prev().hide();
                    break;
                case 1:
                    K.show();
                    y.hide();
                    F.hide();
//				K.show().prev().hide();
//				F.hide().prev().hide();
                    if (!a) {
                        getParticipatePage();
                    }
                    break;
                case 2:
                    y.hide();
                    K.hide();
                    F.show();
//				K.hide().prev().hide();
//				F.show().prev().hide();
                    break;
            }
        };
        var D = C.offset().top;
        var P = 0;
        var r = 0;
        var A = false;
        $(window).scroll(function () {
            x();
        });
        var x = function () {
            P = $(window).scrollTop();
            r = $(document.body).height();
            if (P >= D && P <= r - 795) {
                A = true;
                C.addClass("nav-fixed");
            } else {
                A = false;
                C.removeClass("nav-fixed");
            }
        };
        if (c || !i) {
//			K.prev("div").eq(0).remove();
//			K.remove();
//			$("#liUserBuyAll").remove();
//			$("#btnAdd2Cart").remove();
        } else {
            var q = function () {
                var U = false;
                var W = 10;
                var T = 1;
                var aa = {codeID: o, FIdx: 1, EIdx: W, isCount: 1};
                var V = new Object();
                var Z = 0;
                var S = K.children().eq(0);
                var ab = function () {
                    var ac = "";
                    ac += "codeID=" + aa.codeID;
                    ac += "&FIdx=" + aa.FIdx;
                    ac += "&EIdx=" + aa.EIdx;
                    ac += "&isCount=" + aa.isCount;
                    return ac;
                };
//				var Y = function () {
//					S.show();
//					$("#btnUserBuyMore").unbind("click").bind("click", function () {
//						$("body,html").animate({scrollTop:D - 1}, 0);
//						$("#liUserBuyAll").addClass("DetailsTCur").siblings().removeClass("DetailsTCur");
//						y.hide();
//						K.show().prev().hide();
//						F.hide().prev().hide();
//						BuyFun.initData();
//						return false;
//					}).parent().show();
//					GetJPData("http://dataserver.1yyg.com", "GetUserBuyListByCode", ab(), function (ae) {
//						if (ae.Code == 0) {
//							if (aa.isCount == 1) {
//								Z = parseInt(ae.Count);
//								if (Z > 6) {
//									$("#btnUserBuyMore").unbind("click").bind("click", function () {
//										$("body,html").animate({scrollTop:D - 1}, 0);
//										$("#liUserBuyAll").addClass("DetailsTCur").siblings().removeClass("DetailsTCur");
//										y.hide();
//										K.show().prev().hide();
//										F.hide().prev().hide();
//										BuyFun.initData();
//										return false;
//									}).parent().show();
//								}
//								$("#spTotalCount").html("(\u5171<em class=\"orange\">" + Z + "</em>\u6761\u8bb0\u5f55)");
//							}
//							var ai = ae.Data.Tables.BuyList.Rows;
//							var aj = function (ak) {
//								var al = "<div class=\"AllRecW AllReclist\">";
//								al += "<div class=\"AllRecL fl\">" + ak.buyTime + "<i></i></div>";
//								al += "<div class=\"AllRecR fl\"><p class=\"AllRecR_T\"><a class=\"Headpic\" href=\"http://u.1yyg.com/" + ak.userWeb + "\" target=\"_blank\" title=\"" + ak.userName + "\"><img border=0 width=\"20\" height=\"20\" alt=\"\" src=\"http://faceimg.1yyg.com/UserFace/30/" + ak.userPhoto + "\" ></a><a href=\"http://u.1yyg.com/" + ak.userWeb + "\" target=\"_blank\" class=\"blue\" title=\"" + ak.userName + "\">" + ak.userName + "</a>(" + ak.buyIPAddr + " IP:" + ak.buyIP + ")\u62cd\u8d2d\u4e86<em class=\"Fb orange\">" + ak.buyNum + "</em>\u4eba\u6b21";
//								if (ak.buyDevice == "1") {
//									al += "<i></i><span>\u624b\u673a\u62cd\u8d2d</span>";
//								}
//								al += "</p></div></div>";
//								return al;
//							};
//							var ah = "";
//							var ad = "";
//							var af = null;
//							var ac = "<div name=\"bitem\" class=\"AllRecordCon\">";
//							for (var ag = 0; ag < ai.length; ag++) {
//								ah = ai[ag].buyTime;
//								af = ah.split(" ");
//								if (ag == 0) {
//									ad = af[0];
//									ac += "<div class=\"AllRecW AllRecTime\"><p>" + af[0] + "</p> <b></b></div>";
//								} else {
//									if (ad != af[0]) {
//										ad = af[0];
//										ac += "<div class=\"AllRecW All_lineH\"></div>";
//										ac += "<div class=\"AllRecW AllRecTime\"><p>" + af[0] + "</p> <b></b></div>";
//									}
//								}
//								ac += aj(ai[ag]);
//							}
//							ac += "</div>";
//							if (Z > W) {
//								ac += "<div name=\"bitem\" class=\"pages\">" + GetAjaxPageNationEx2(Z, W, 5, T, "BuyFun.gotoPageIndex", false, true) + "</div>";
//							}
//							X(ac);
//							U = true;
//							V["page" + T] = ac;
//						} else {
//							K.html("<div class=\"NoConMsg\"><span>\u6682\u65e0\u62cd\u8d2d\u8bb0\u5f55\u54e6~\uff01</span></div>");
//						}
//					});
//				};
                var X = function (ac) {
                    S.hide();
                    K.append(ac);
                };
                this.gotoPageIndex = function (ac, ad) {
                    if (U) {
                        Q(C.find("li").eq(1), 1);
                        if (A) {
                            $("body,html").animate({scrollTop: D - 1}, 0);
                        }
                    }
                    T = Math.floor(ad / W);
                    K.children("div[name=\"bitem\"]").remove();
                    if (V["page" + T] != null) {
                        X(V["page" + T]);
                    } else {
                        if (T > 1) {
                            aa.isCount = 0;
                        }
                        aa.codeID = o;
                        aa.FIdx = ac;
                        aa.EIdx = ad;
                        Y();
                    }
                };
                this.initData = function () {
                    if (!U) {
                        BuyFun.gotoPageIndex(1, W);
                    }
                };
            };
//			BuyFun = new q();
//			BuyFun.initData();
        }
        var v = $(".Announced_FrameBut > a").eq(0);
        if (v.length > 0) {
            v.click(function () {
                $("#liUserBuyAll").prev().addClass("DetailsTCur").siblings().removeClass("DetailsTCur");
                y.show();
                K.hide();
                F.hide();
                $("body,html").animate({scrollTop: D - 1}, 0);
                return false;
            });
        }
        var u = $(".Announced_FrameBut > a").eq(2);
        if (u.length > 0) {
            u.click(function () {
                $("#liUserBuyAll").next().addClass("DetailsTCur").siblings().removeClass("DetailsTCur");
                F.show();
                K.hide();
                y.hide();
                $("body,html").animate({scrollTop: D - 1}, 0);
                return false;
            });
        }
        $(".Announced_FrameBut > a:eq(1)").unbind("click").bind("click", function () {
            $("body,html").animate({scrollTop: D - 1}, 0);
            $("#liUserBuyAll").addClass("DetailsTCur").siblings().removeClass("DetailsTCur");
            K.show();
            F.hide();
            y.hide();
            if (!a) {
                getParticipatePage();
            }
            return false;
        }).parent().show();

        isLoaded = true;
        x();
    };
    b();

    var j = function () {
        var u = $("#userRnoId");
        var m = u.children("dl");
        var q = 0;
        m.each(function () {
            q += $(this).height();
        });
        if (q > 90) {
            var s = function () {
                var w = $("#userRnoId", $(this).prev());
                if ($(this).attr("class") == "MaOff") {
                    w.attr("class", "MaCenterC");
                    $(this).attr("class", "MaOpen").html("<span>\u6536\u8d77<s></s></span>");
                } else {
                    $(this).attr("class", "MaOff").html("<span>\u5c55\u5f00\u67e5\u770b\u5168\u90e8 <s></s></span>");
                    w.attr("class", "MaCenterH");
                }
            };
            $("#divOpen").bind("click", s).show();
        } else {
            $("#divOpen").hide();
        }
    };
    j();

    function getParticipatePage() {
        var resultCount = $("#resultCount").val();
        var pageNo = 0;
        var id = $("#productId").val();
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
            items_per_page: 20,
            callback: pageselectCallback
        });

        function pageselectCallback(pageNo) {
            var url = "/products/ajaxPage.action?id=" + id + "&pageNo=" + pageNo;
            $.ajax({
                url: url,
                type: "post",
                data: "json",
                beforeSend: loading,
                success: function (data) {
                    $("#userByListDIV").empty();
                    $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 20) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                    var ai = eval("(" + data + ")");
                    var aj = function (ak) {
                        var al = "<div class=\"AllRecW AllReclist\">";
                        al += "<div class=\"AllRecL fl\">" + ak.buyDate + "<i></i></div>";
                        al += "<div class=\"AllRecR fl\"><p class=\"AllRecR_T\"><a class=\"Headpic\" target=\"_blank\" href=\"" + $www + "/u/" + ak.userId + ".html\" title=\"" + ak.userName + "\"><img border=0 width=\"20\" height=\"20\" alt=\"\" src=\"" + $img + ak.userFace + "\" ></a><a target=\"_blank\" href=\"" + $www + "/u/" + ak.userId + ".html\" class=\"blue\" title=\"" + ak.userName + "\">" + ak.userName + "</a>(" + ak.ip_location + " IP:" + ak.ip_address + ")\u62cd\u8d2d\u4e86<em class=\"Fb orange\">" + ak.buyCount + "</em>\u4eba\u6b21";
//								if (ak.buyDevice == "1") {
//									al += "<i></i><span>\u624b\u673a\u62cd\u8d2d</span>";
//								}
                        al += "</p></div></div>";
                        return al;
                    };
                    var ah = "";
                    var ad = "";
                    var af = null;
                    var ac = "<div name=\"bitem\" class=\"AllRecordCon\">";
                    for (var ag = 0; ag < ai.length; ag++) {
                        ah = ai[ag].buyDate;
                        af = ah.split(" ");
                        if (ag == 0) {
                            ad = af[0];
                            ac += "<div class=\"AllRecW AllRecTime\"><p>" + af[0] + "</p> <b></b></div>";
                        } else {
                            if (ad != af[0]) {
                                ad = af[0];
                                ac += "<div class=\"AllRecW All_lineH\"></div>";
                                ac += "<div class=\"AllRecW AllRecTime\"><p>" + af[0] + "</p> <b></b></div>";
                            }
                        }
                        ac += aj(ai[ag]);
                    }
                    ac += "</div>";
                    $(ac).appendTo("#userByListDIV");
                    $(".goods_loding").hide();
                    a = true;
                }
            });
        }
    }

    function loading() {
        $(".goods_loding").show();
    }

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
                    $.getScript($skin + "/js/recommendpicfun.js");
                }
            });
            loaded = true;
        }
    };
    $(window).scroll(show);

});
