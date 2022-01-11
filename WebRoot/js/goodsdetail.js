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
    var i = parseInt($("#hidIsStart").val());
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
                $.ajax({
                    url: "/list/isStatus.action?id=" + o,
                    type: "GET",
                    data: "json",
                    success: function (data) {
                        if (data == "false") {
                            $(".Det_Shopbut").html("该商品已经满员！");
                            location.href = $www + "/products/" + o + ".html";
                        } else {
                            var count = parseInt(data);
                            var codeid = o;
                            var json = getCookie("products");
                            if (json == null || json == "") {
                                var val = "[{'pId':" + codeid + ",'num':" + G + "}]";
                                SetCookieByExpires("products", val, 1);
                            } else {
                                var flag = false;
                                json = eval('(' + json + ')');
                                $.each(json, function (n, value) {
                                    if (codeid == value.pId) {
                                        value.num = (parseInt(value.num) + parseInt(G));
                                        if ((parseInt(value.num) + parseInt(G)) > count) {
                                            value.num = count;
                                        }
                                        flag = true;
                                    }
                                });
                                if (!flag) {
                                    json.push({"pId": +codeid, "num": G});
                                }
                                json = JSON.stringify(json);
                                SetCookieByExpires("products", json, 1);
                            }
                            location.href = $www + "/mycart/index.html";
                        }
                    }
                });
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
//							M = "<b>啊哦！！剩下的被抢光了！</b><span>正在开奖哦，去围观一下看看谁中奖了！</span>";
//							J = "<a class=\"detail_Promptbut\" href=\"/product/" + o + ".html\">去围观</a>";
//						} else {
//							M = "<b>对不起，拍购失败！</b><span>&nbsp;</span>";
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
                v("您输入的人次有误！", 1);
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
                $.ajax({
                    url: "/list/isStatus.action?id=" + o,
                    type: "GET",
                    data: "json",
                    success: function (data) {
                        if (data == "false") {
                            $(".Det_Shopbut").html("该商品已经满员！");
                            location.href = $www + "/products/" + o + ".html";
                        } else {
                            var count = parseInt(data);
                            var codeid = o;
                            var json = getCookie("products");
                            if (json == null || json == "") {
                                var val = "[{'pId':" + codeid + ",'num':" + H + "}]";
                                SetCookieByExpires("products", val, 1);
                            } else {
                                var flag = false;
                                json = eval('(' + json + ')');
                                $.each(json, function (n, value) {
                                    if (codeid == value.pId) {
                                        value.num = (parseInt(value.num) + parseInt(H));
                                        if ((parseInt(value.num) + parseInt(H)) > count) {
                                            value.num = count;
                                        }
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
        var B = $("#ulRecordTab");
        var v = B.next().eq(0);
        var t = v.next().eq(0);
        var s = t.next().eq(0);
        B.find("li").each(function (T, S) {
            $(this).click(function () {
                $(this).addClass("Record_titCur").siblings().removeClass("Record_titCur");
                switch (T) {
                    case 0:
                        v.show();
                        t.hide();
                        s.hide();
                        break;
                    case 1:
                        v.hide();
                        t.show();
                        s.hide();
                        R();
                        break;
                    case 2:
                        v.hide();
                        t.hide();
                        s.show();
                        break;
                }
            });
        });
        var G = false;
        var R = function () {
            if (!G) {
                //我的拍购记录
                $.ajax({
                    url: "/products/getUserByHistory.action?id=" + o,
                    type: "GET",
                    data: "JSON",
                    success: function (data) {
                        if (data != "") {
                            var data = eval('(' + data + ')');
                            var S = data.length;
                            if (S > 0) {
                                var V = "<ul>";
                                for (var U = 0; U < S; U++) {
                                    V += "<li>" + data[U].buyDate + " \u62cd\u8d2d\u4e86 <em class=\"Fb gray01\">" + data[U].buyPrice + "</em> \u4e2a\u62cd\u8d2d\u7801</li>";
                                }
                                V += "</ul>";
                                if (S >= 8) {
                                    V += "<p><a href=\"" + $www + "/user/UserBuyDetail-" + o + ".html\" target=\"_blank\" class=\"gray01\">查看所有" + $shortName + "码>></a></p>";
                                }
                                t.html(V);
                            } else {
                                t.html("<div class=\"tips-con\"><i></i>您还未有购买此商品哦！</div>");
                            }
                            G = true;
                        }
                    },
                    error: function () {
                        t.html("<div class=\"tips-con\"><i></i>加载失败，请刷新！</div>");
                    }

                });
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
                    K.show().prev().hide();
                    F.hide().prev().hide();
                }
            }
        });
        var Q = function (T, S) {
            T.addClass("DetailsTCur").siblings().removeClass("DetailsTCur");
            switch (S) {
                case 0:
                    y.show();
                    K.show().hide();
//				K.show().prev().show();
//				F.show().prev().show();
                    break;
                case 1:
                    y.hide();
                    K.show().prev().hide();
//				F.hide().prev().hide();
                    if (!a) {
                        getParticipatePage();
                    }
                    break;
                case 2:
                    y.hide();
                    K.hide().prev().hide();
                    F.show().prev().hide();
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
//			var q = function () {
//				var U = false;
//				var W = 10;
//				var T = 1;
//				var aa = {codeID:o, FIdx:1, EIdx:W, isCount:1};
//				var V = new Object();
//				var Z = 0;
//				var S = K.children().eq(0);
//				var ab = function () {
//					var ac = "";
//					ac += "codeID=" + aa.codeID;
//					ac += "&FIdx=" + aa.FIdx;
//					ac += "&EIdx=" + aa.EIdx;
//					ac += "&isCount=" + aa.isCount;
//					return ac;
//				};
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
//				var X = function (ac) {
//					S.hide();
//					K.append(ac);
//				};
//				this.gotoPageIndex = function (ac, ad) {
//					if (U) {
//						Q(C.find("li").eq(1), 1);
//						if (A) {
//							$("body,html").animate({scrollTop:D - 1}, 0);
//						}
//					}
//					T = Math.floor(ad / W);
//					K.children("div[name=\"bitem\"]").remove();
//					if (V["page" + T] != null) {
//						X(V["page" + T]);
//					} else {
//						if (T > 1) {
//							aa.isCount = 0;
//						}
//						aa.codeID = o;
//						aa.FIdx = ac;
//						aa.EIdx = ad;
//						Y();
//					}
//				};
//				this.initData = function () {
//					if (!U) {
//						BuyFun.gotoPageIndex(1, W);
//					}
//				};
//			};
//			BuyFun = new q();
//			BuyFun.initData();
        }
        var u = $("#btnGoToPost");
        if (u.length > 0) {
            u.click(function () {
                $("#liUserBuyAll").next().addClass("DetailsTCur").siblings().removeClass("DetailsTCur");
                y.hide();
                K.hide().prev().hide();
                F.show().prev().hide();
                $("body,html").animate({scrollTop: D - 1}, 0);
                return false;
            });
        }

        $("#btnUserBuyMore").unbind("click").bind("click", function () {
            $("body,html").animate({scrollTop: D - 1}, 0);
            $("#liUserBuyAll").addClass("DetailsTCur").siblings().removeClass("DetailsTCur");
            y.hide();
            K.show().prev().hide();
            F.hide().prev().hide();
            if (!a) {
                getParticipatePage();
            }
            return false;
        }).parent().show();
//		var N = parseInt($("#hidPostCount").val());
//		if (N > 0) {
//			var z = 10;
//			var M = 1;
//			var J = {goodsID:e, FIdx:1, EIdx:z, isCount:0};
//			var I = F.children().eq(0);
//			var L = function () {
//				if (N > z) {
//					F.append("<div class=\"pages\">" + GetAjaxPageNationEx2(N, z, 5, M, "PostFun.gotoPageIndex", false, true) + "</div>");
//				} else {
//					F.children().eq(N).addClass("lineNone");
//				}
//			};
//			L();
//			var w = function () {
//				$("div.SingleR_Comment").each(function () {
//					var T = $(this);
//					var S = parseInt(T.attr("count"));
//					if (S > 0) {
//						var U = T.attr("postID");
//						GetJPData("http://post.1yyg.com", "getPostReplyByID", "FIdx=1&EIdx=5&postID=" + U, function (V) {
//							if (V.Code == 0) {
//								if (V.Count > 0) {
//									var X = V.Data.Tables.PostReplyList.Rows;
//									var Y = "<ul class=\"gray02\">";
//									for (var W = 0; W < X.length; W++) {
//										Y += "<li><a href=\"http://u.1yyg.com/" + X[W].replyUserWeb + "\" target=\"_blank\" class=\"blue\">" + X[W].replyUserName + "</a>\u8bf4\uff1a <span class=\"gray01\">" + X[W].replyContent + "</span>" + X[W].replyTime + "</li>";
//									}
//									Y += "</ul><p class=\"SingleRmore\"><a href=\"http://post.1yyg.com/detail-" + U + ".html\" target=\"_blank\" class=\"gray01\">\u67e5\u770b\u5168\u90e8>></a></p>";
//								}
//								T.append(Y);
//							}
//						});
//					}
//				});
//			};
//			w();
//			var O = function () {
//				var T = function () {
//					var U = "";
//					U += "goodsID=" + J.goodsID;
//					U += "&FIdx=" + J.FIdx;
//					U += "&EIdx=" + J.EIdx;
//					U += "&isCount=" + J.isCount;
//					return U;
//				};
//				var S = function () {
//					I.show();
//					GetJPData("http://post.1yyg.com", "getGoodsPostList", T(), function (U) {
//						if (U.Code == 0) {
//							var Z = U.Data.Tables.PostList.Rows;
//							var aa = Z.length;
//							var Y = "";
//							for (var X = 0; X < aa; X++) {
//								if (X == aa - 1 && N < z) {
//									Y += "<div class=\"Single_list lineNone\">";
//								} else {
//									Y += "<div class=\"Single_list\">";
//								}
//								Y += "<div class=\"SingleL fl Topiclist-img\"><a class=\"head-img\" href=\"http://u.1yyg.com/" + Z[X].userWeb + "\" type=\"showCard\" uweb=\"" + Z[X].userWeb + "\"  target=\"_blank\"><img border=\"0\" alt=\"\" src=\"http://faceimg.1yyg.com/UserFace/" + Z[X].userPhoto + "\"></a><a class=\"blue\" href=\"http://u.1yyg.com/" + Z[X].userWeb + "\" target=\"_blank\" rel=\"nofollow\" type=\"showCard\" uweb=\"" + Z[X].userWeb + "\">" + Z[X].userName + "</a><span class=\"class-icon" + Z[X].grade + "\"><s></s>" + Z[X].gradeName + "</span></div><div class=\"SingleR fl\"><div class=\"SingleR_TC\"><i></i> <s></s><h3><span class=\"gray02\">\u7b2c" + Z[X].codePeriod + "\u671f\u6652\u5355</span> <a href=\"http://post.1yyg.com/Detail-" + Z[X].postID + ".html\" target=\"_blank\">" + Z[X].postTitle + "</a><em class=\"gray02\">" + Z[X].postTimeEx + "</em></h3><p class=\"gray01\">" + Z[X].postContent + "</p></div><ul class=\"SingleR_pic\">";
//								var V = Z[X].postAllPic.split(",");
//								for (var W = 0; W < V.length; W++) {
//									Y += "<li><img src=\"http://postimg.1yyg.com/UserPost/Small/" + V[W] + "\"/></li>";
//								}
//								Y += "</ul><div class=\"SingleR_Comment\" postID=\"" + Z[X].postID + "\" count=\"" + Z[X].postReplyCount + "\"><div class=\"Comment_smile gray02\"><span><i></i>" + Z[X].postHits + "\u4eba\u7fa1\u6155\u5ac9\u5992\u6068</span><span><s></s>" + Z[X].postReplyCount + "\u6761\u8bc4\u8bba</span></div></div></div></div>";
//							}
//							I.hide();
//							F.html(Y);
//							w();
//							H();
//							L();
//						}
//					});
//				};
//				this.gotoPageIndex = function (U, V) {
//					Q(C.find("li").eq(2), 2);
//					if (A) {
//						$("body,html").animate({scrollTop:D - 1}, 0);
//					}
//					M = Math.floor(V / z);
//					J.goodsID = e;
//					J.FIdx = U;
//					J.EIdx = V;
//					S();
//				};
//			};
//			PostFun = new O();
//			var H = function () {
//				$("a[type='showCard']").each(function () {
//					$(this).ShowUserCard();
//				});
//			};
//			Base.getScript(n + "/Star/JS/userInfoFun.js?date=20120123", H);
//		}
        isLoaded = true;
        x();
    };
    b();

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
                    var ai = eval("(" + data + ")");
                    if (ai.length > 0) {
                        $("#userByListDIV").empty();
                        $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 20) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
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
                    } else {
                        $(".goods_nodata").show();
                        $(".goods_loding").hide();
                    }
                }
            });
        }

        a = true;
    };

    function loading() {
        $(".goods_loding").show();
    };

//	var c = function () {
//		if(i==0){
//			$.ajax({
//				url:"/products/isLottery.action?id="+o,
//				data:"JSON",
//				type:"GET",
//				success:function(data){
//					if(data=="lottery"){
//						window.location.href="/products/" + o + ".html";
//						clearInterval(B);
//						B = null;
//					}
//					if(data=="detail"){
//						window.location.href="/lotteryDetail/" + o + ".html";
//						clearInterval(B);
//						B = null;
//					}
//				}
//			});
//		}else{
//			$.ajax({
//				url:"/products/isLottery.action?id="+o,
//				data:"JSON",
//				type:"GET",
//				success:function(data){
//					if(data=="detail"){
//						window.location.href="/lotteryDetail/" + o + ".html";
//						clearInterval(B);
//						B = null;
//					}
//				}
//			});
//		}
//	};
//	var B = setInterval(c, 2000);
//		setTimeout(c, 1000);
//	c();
    var l = function () {
        if ($("#divLotteryTimer").length > 0) {
            $("#btnAdd2Cart").remove();
            $.getScript(n + "/js/goodsdetaillottery.js", function () {
                startTimeOut();
            });
        } else {
            d(m, h);
        }
//		var p = $("#divAutoRTime");
//		if (p.length > 0) {
//			var t = p.find("p");
//			var s = parseInt(p.attr("time"));
//			var q = function () {
//				window.location.replace("/product/" + o + ".html");
//			};
//			Base.getScript(n + "/JS/CountdownFun.js?date=20130301", function () {
//				t.countdowntime(s, q);
//			});
//			var r = function () {
//				GetJPData("http://dataserver.1yyg.com", "getCodeState", "codeID=" + o, function (u) {
//					if (u.Code == 0) {
//						q();
//					}
//				});
//			};
//			setInterval(r, 30000);
//		}
        CBLPageFun = new b();
    };
    l();

    $(".jqzoom").imagezoom();
    $("#mycarousel li").eq(0).addClass("curr");
    var T = $("#onload");
    $("#mycarousel li").click(function () {
        $(this).addClass("curr").siblings().removeClass("curr");
        T.show();
        $(".jqzoom").attr('src', $(this).find("img").attr("big")).load(function () {
            T.hide();
        });
        $(".jqzoom").attr('rel', $(this).find("img").attr("big"));
    });

//$.getScript("/js/test.js",function(){mycarousel()});
//	$("#BigViewImage").jqzoom();
//$.getScript(a + "/js/recommendpicfun.js");


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
                            "<a rel=\"nofollow\" class=\"go_Shopping12\" title=\"立即1元拍购\" target=\"_blank\" href=\"" + $www + "/products/" + data[i].productId + ".html\">立即1元拍购</a></div>" +
                            "</li>").appendTo("#ulRecommend");
                    }
                    $.getScript($skin + "/js/recommendpicfun.js");
                }
            });
            loaded = true;
        }
    };
    $(window).scroll(show);

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
    $.getScript(n + "/js/json2.js");
});
