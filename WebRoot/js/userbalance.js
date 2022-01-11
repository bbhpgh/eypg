var CBLFun = null;
$(function () {
    var A = $skin;
    var C = function () {
        var D = function () {
            var resultCount = $("#resultCount").val();
            var pageNo = 0;
            var userId = $("#userId").val();
            var S = 15;
            var I = 1;
            var F = 0;
            var outTradeNo = "";
            var outTradePageNo = 0;
            var Q = {"type": 0, "FIdx": 1, "EIdx": S, "region": 0, "beginTime": "", "endTime": "", "isCount": 1};
            var G = $("#divList0");
            var L = $("#divPageNav0");
            var O = $("#txtMisTime");
            var J = $("#txtMaxTime");
            $("div.type a").each(function (U, T) {
                $(this).bind("click", function () {
                    $(this).addClass("annal1").siblings().removeClass("annal1");
                    Q.type = U;
                    Q.beginTime = "";
                    Q.endTime = "";
                    G = $("#divList" + U);
                    L = $("#divPageNav" + U);
                    if (U == 0) {
                        $("#divTimeSel").show();
                        $("#divList0").show();
                        $("#divPageNav0").show();
                        $("#divList1").hide();
                        $("#divPageNav1").hide();
                        $("#divDetailInfo").hide();
                        $("#divbuyDetail").hide();
                    } else {
                        $("#divTimeSel").show();
                        $("#divList0").hide();
                        $("#divPageNav0").hide();
                        $("#divList1").show();
                        $("#divPageNav1").show();
                    }
                    CBLFun.initData();
                });
            });
            $("div.record-tab a").each(function (U, T) {
                $(this).bind("click", function () {
                    $(this).addClass("record-cur").siblings().removeClass("record-cur");
                    Q.region = U;
                    Q.beginTime = "";
                    Q.endTime = "";
                    CBLFun.initData();
                });
            });
            var H = function () {
                var T = new Date();
                O.val(T.DateAdd("m", -1).Format("YYYY-MM-DD")).date_input();
                J.val(T.Format("YYYY-MM-DD")).date_input();
                var U = function () {
                    var V = O.val();
                    var W = J.val();
                    if (!IsValidDate(V)) {
                        alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u5f00\u59cb\u65e5\u671f!");
                        return;
                    }
                    if (!IsValidDate(W)) {
                        alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7ed3\u675f\u65e5\u671f!");
                        return;
                    }
                    if (ConvertDate(W) < ConvertDate(V)) {
                        alert("\u7ed3\u675f\u65e5\u671f\u4e0d\u80fd\u5c0f\u4e8e\u5f00\u59cb\u65e5\u671f!");
                        return;
                    }
                    $(".record-tab a").each(function () {
                        $(this).removeClass("record-cur");
                    });
                    Q.region = 5;
                    Q.beginTime = O.val();
                    Q.endTime = J.val();
                    CBLFun.initData();
                };
                $("#btnSearch").unbind("click").bind("click", U);
            };
            H();
            var N = "<ul class=\"listTitle\"><li class=\"leftTitle\">充值时间</li><li class=\"price\">资金渠道</li><li class=\"regard\">充值金额</li></ul>";
            var K = "<ul class=\"listTitle\"><li class=\"leftTitle\">" + $shortName + "时间</li><li class=\"price\">" + $shortName + "金额（元）</li><li class=\"regard\">操作</li></ul>";
            var M = "<ul class=\"listTitle\"><li class=\"leftTitle\">" + $shortName + "商品</li><li>" + $shortName + "人次</li><li class=\"regard\">" + $shortName + "金额</li></ul>";
            $("#pagination").pagination(resultCount, {
                current_page: pageNo,
                prev_text: "上一页",
                next_text: "下一页",
                num_display_entries: 5,
                num_edge_entries: 1,
                link_to: "",
                prev_show_always: false,
                next_show_always: false,
                items_per_page: 10,
                callback: pageselectCallbackConsume
            });

            function pageselectCallbackConsume(pageNo) {
                var url = "/user/ConsumeListByDeltaAjaxPage.action?pageNo=" + pageNo + "&pageSize=10&userId=" + userId + "&selectTime=" + Q.region + "&startDate=" + Q.beginTime + "&endDate=" + Q.endTime;
                $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
                $.ajax({
                    url: url,
                    type: "post",
                    data: "json",
                    beforeSend: loading,
                    success: function (data) {
                        data = eval("(" + data + ")");
                        G.empty();
                        if (resultCount > 0) {
                            $(N).appendTo(G);
                            $(".pageULEx").show();
                            $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 10) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                            for (var i = 0; i < data.length; i++) {
                                $("<ul><li class=\"leftTitle fontAri\">" + data[i].date + "</li><li class=\"price\">网银支付</li><li class=\"regard\">￥" + data[i].money + "</li></ul>").appendTo(G);
                            }
                            $("#pageLoading").hide();
                        } else {
                            $(N).appendTo(G);
                            $("<div class=\"tips-con\"><i></i>无相应的记录</div>").appendTo(G);
                            $(".pageULEx").hide();
                        }
                    }
                });
            }

            function pageselectCallbackConsume2(pageNo) {
                var url = "/user/ConsumeListByDeltaAjaxPage.action?pageNo=" + pageNo + "&pageSize=10&userId=" + userId + "&startDate=" + Q.beginTime + "&endDate=" + Q.endTime;
                $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
                $.ajax({
                    url: url,
                    type: "post",
                    data: "json",
                    beforeSend: loading,
                    success: function (data) {
                        data = eval("(" + data + ")");
                        G.empty();
                        if (resultCount > 0) {
                            $(N).appendTo(G);
                            $(".pageULEx").show();
                            $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 10) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                            for (var i = 0; i < data.length; i++) {
                                $("<ul><li class=\"leftTitle fontAri\">" + data[i].date + "</li><li class=\"price\">网银支付</li><li class=\"regard\">￥" + data[i].money + "</li></ul>").appendTo(G);
                            }
                            $("#pageLoading").hide();
                        } else {
                            $(N).appendTo(G);
                            $("<div class=\"tips-con\"><i></i>无相应的记录</div>").appendTo(G);
                            $(".pageULEx").hide();
                        }
                    }
                });
            }

            function pageselectCallbackConsumeDetail(outTradePageNo) {
                var url = "/ConsumeDetail/ConsumeDetailAjaxPage.action?pageNo=" + outTradePageNo + "&id=" + outTradeNo;
                $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
                $.ajax({
                    url: url,
                    type: "post",
                    data: "json",
                    beforeSend: loading,
                    success: function (data) {
                        data = eval("(" + data + ")");
                        if (resultCount > 0) {
                            var h = "<dd class=\"gray01\"></dd><dd class=\"btn\"><a name=\"goBack\" href=\"javascript:;\" title=\"\u8fd4\u56de\u5217\u8868\">\u8fd4\u56de\u5217\u8868</a><span></span></dd>";
                            var j = $(h);
                            $("#divDetailInfo").show().empty().append(j);
                            j.find("a[name='goBack']").click(function () {
                                $("div.type a:eq(1)").click();
                                $("#divDetailInfo").hide();
                                $("#divbuyDetail").hide();
                                $("#divTimeSel").show();
                                $("#divList1").show();
                                $("#divPageNav1").show();
                            });
                            var f = "";
                            for (var d = 0; d < data.length; d++) {
                                f += "<ul><li class=\"leftTitle\"><a target=\"_blank\" title=\"" + data[d].productTitle + "\" href=\"" + $www + "/products/" + data[d].productId + ".html\">(\u7b2c" + data[d].productPeriod + "\u671f)" + data[d].productName.substring(0, 40) + "...</a></li><li>" + data[d].buyCount + "</li><li class=\"regard\">\uffe5" + data[d].buyMoney + "</li></ul>";
                            }
                            $("#divTimeSel").hide();
                            426532
                            $("#divList1").hide();
                            $("#divPageNav1").hide();
                            $("#divbuyDetail").html(M + f).show();
                            $("#pageLoading").hide();
                        }
                    }
                });
            }

            function pageselectCallbackConsumeDetail2(outTradePageNo) {
                var url = "/ConsumeDetail/ConsumeDetailAjaxPage.action?pageNo=" + outTradePageNo + "&id=" + outTradeNo;
                $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
                $.ajax({
                    url: url,
                    type: "post",
                    data: "json",
                    beforeSend: loading,
                    success: function (data) {
                        data = eval("(" + data + ")");
                        if (resultCount > 0) {
                            var h = "<dd class=\"gray01\"></dd><dd class=\"btn\"><a name=\"goBack\" href=\"javascript:;\" title=\"\u8fd4\u56de\u5217\u8868\">\u8fd4\u56de\u5217\u8868</a><span></span></dd>";
                            var j = $(h);
                            $("#divDetailInfo").show().empty().append(j);
                            j.find("a[name='goBack']").click(function () {
                                $("#btnSearch").click();
                                $("#divDetailInfo").hide();
                                $("#divbuyDetail").hide();
                                $("#divTimeSel").show();
                                $("#divList1").show();
                                $("#divPageNav1").show();
                            });
                            var f = "";
                            for (var d = 0; d < data.length; d++) {
                                f += "<ul><li class=\"leftTitle\"><a target=\"_blank\" title=\"" + data[d].productTitle + "\" href=\"" + $www + "/products/" + data[d].productId + ".html\">(\u7b2c" + data[d].productPeriod + "\u671f)" + data[d].productName.substring(0, 40) + "...</a></li><li>" + data[d].buyCount + "</li><li class=\"regard\">\uffe5" + data[d].buyMoney + "</li></ul>";
                            }
                            $("#divTimeSel").hide();
                            $("#divList1").hide();
                            $("#divPageNav1").hide();
                            $("#divbuyDetail").html(M + f).show();
                            $("#pageLoading").hide();
                        }
                    }
                });
            }

            function pageselectCallback(pageNo) {
                var url = "/user/ConsumeListAjaxPage.action?pageNo=" + pageNo + "&pageSize=10&userId=" + userId + "&selectTime=" + Q.region + "&startDate=" + Q.beginTime + "&endDate=" + Q.endTime;
                $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
                $.ajax({
                    url: url,
                    type: "post",
                    data: "json",
                    beforeSend: loading,
                    success: function (data) {
                        data = eval("(" + data + ")");
                        G.empty();
                        if (resultCount > 0) {
                            $(K).appendTo(G);
                            $(".pageULEx").show();
                            $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 10) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                            for (var i = 0; i < data.length; i++) {
                                $("<ul><li class=\"leftTitle\">" + data[i].date + "</li><li class=\"price\">￥" + data[i].buyCount + "</li><li class=\"regard\"><a title=\"详情\" shopid=\"" + data[i].outTradeNo + "\" href=\"javascript:;\" name=\"showDetail\">详情</a></li></ul>").appendTo(G);
                            }
                            $("#pageLoading").hide();
                            $("a[name=showDetail]").click(function () {
                                outTradeNo = $(this).attr("shopid");
                                $.ajax({
                                    url: "/ConsumeDetail/ConsumeDetailAjaxPageByCount.action?id=" + outTradeNo,
                                    success: function (data) {
                                        resultCount = parseInt(data);
                                        //分页事件
                                        $("#pagination").pagination(resultCount, {
                                            current_page: outTradePageNo,
                                            prev_text: "上一页",
                                            next_text: "下一页",
                                            num_display_entries: 5,
                                            num_edge_entries: 1,
                                            link_to: "",
                                            prev_show_always: false,
                                            next_show_always: false,
                                            items_per_page: 10,
                                            callback: pageselectCallbackConsumeDetail
                                        });
                                    }
                                });
                            });
                        } else {
                            $(K).appendTo(G);
                            $("<div class=\"tips-con\"><i></i>无相应的记录</div>").appendTo(G);
                            $(".pageULEx").hide();
                        }
                    }
                });
            }

            function pageselectCallback2(pageNo) {
                var url = "/user/ConsumeListAjaxPage.action?pageNo=" + pageNo + "&pageSize=10&userId=" + userId + "&startDate=" + Q.beginTime + "&endDate=" + Q.endTime;
                $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
                $.ajax({
                    url: url,
                    type: "post",
                    data: "json",
                    beforeSend: loading,
                    success: function (data) {
                        data = eval("(" + data + ")");
                        G.empty();
                        if (resultCount > 0) {
                            $(K).appendTo(G);
                            $(".pageULEx").show();
                            $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 10) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                            for (var i = 0; i < data.length; i++) {
                                $("<ul><li class=\"leftTitle\">" + data[i].date + "</li><li class=\"price\">￥" + data[i].buyCount + "</li><li class=\"regard\"><a title=\"详情\" shopid=\"" + data[i].outTradeNo + "\" href=\"javascript:;\" name=\"showDetail\">详情</a></li></ul>").appendTo(G);
                            }
                            $("#pageLoading").hide();
                            $("a[name=showDetail]").click(function () {
                                outTradeNo = $(this).attr("shopid");
                                $.ajax({
                                    url: "/ConsumeDetail/ConsumeDetailAjaxPageByCount.action?id=" + outTradeNo,
                                    success: function (data) {
                                        resultCount = parseInt(data);
                                        //分页事件
                                        $("#pagination").pagination(resultCount, {
                                            current_page: outTradePageNo,
                                            prev_text: "上一页",
                                            next_text: "下一页",
                                            num_display_entries: 5,
                                            num_edge_entries: 1,
                                            link_to: "",
                                            prev_show_always: false,
                                            next_show_always: false,
                                            items_per_page: 10,
                                            callback: pageselectCallbackConsumeDetail2
                                        });
                                    }
                                });
                            });
                        } else {
                            $(K).appendTo(G);
                            $("<div class=\"tips-con\"><i></i>无相应的记录</div>").appendTo(G);
                            $(".pageULEx").hide();
                        }
                    }
                });
            }

            function loading() {
                $("#pageLoading").show();
            }

            var R = function () {
                if (!isLoaded) {
                    return false;
                }
//				alert("Q.region:"+Q.region+"type:"+Q.type+"startdate:"+Q.beginTime+"enddate:"+Q.endTime);
                if (Q.type == "0") {
                    if (Q.region == "5") {
                        $.ajax({
                            url: "/user/ConsumeListByDeltaAjaxPageResultCount.action?pageNo=" + pageNo + "&userId=" + userId + "&startDate=" + Q.beginTime + "&endDate=" + Q.endTime,
                            type: "get",
                            data: "String",
                            success: function (data) {
                                resultCount = parseInt(data);
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
                                    items_per_page: 10,
                                    callback: pageselectCallbackConsume2
                                });
                            }
                        });
                    } else {
                        $.ajax({
                            url: "/user/ConsumeListByDeltaAjaxPageResultCount.action?pageNo=" + pageNo + "&userId=" + userId + "&selectTime=" + Q.region,
                            type: "get",
                            data: "String",
                            success: function (data) {
                                resultCount = parseInt(data);
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
                                    items_per_page: 10,
                                    callback: pageselectCallbackConsume
                                });
                            }
                        });
                    }
                } else {
                    if (Q.region == "5") {
                        $.ajax({
                            url: "/user/ConsumeListAjaxPageResultCount.action?pageNo=" + pageNo + "&userId=" + userId + "&startDate=" + Q.beginTime + "&endDate=" + Q.endTime,
                            type: "get",
                            data: "String",
                            success: function (data) {
                                resultCount = parseInt(data);
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
                                    items_per_page: 10,
                                    callback: pageselectCallback2
                                });
                            }
                        });
                    } else {
                        $.ajax({
                            url: "/user/ConsumeListAjaxPageResultCount.action?pageNo=" + pageNo + "&userId=" + userId + "&selectTime=" + Q.region,
                            type: "get",
                            data: "String",
                            success: function (data) {
                                resultCount = parseInt(data);
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
                                    items_per_page: 10,
                                    callback: pageselectCallback
                                });
                            }
                        });
                    }
                }
//				P.OnSuccess(function (T) {
//					var U = T.d.Code;
//					if (U == 0) {
//						eval("T=" + T.d.Str);
//						if (Q.isCount == 1) {
//							F = T.totalCount;
//						}
//						var X = T.listItems;
//						var Y = "";
//						for (var W = 0; W < X.length; W++) {
//							if (Q.type == 0) {
//								Y = "<ul><li class=\"leftTitle fontAri\">" + X[W].logTime + "</li><li class=\"price\">" + X[W].typeName + "</li><li class=\"regard\">\uffe5" + X[W].logMoeny + "</li></ul>";
//								G.append(Y);
//							} else {
//								Y = "<ul><li class=\"leftTitle\">" + X[W].buyTime + "</li><li class=\"price\">\uffe5" + X[W].buyNum + ".00</li><li class=\"regard\"><a name=\"showDetail\" href=\"javascript:void();\" shopID=" + X[W].buyShopID + "  title=\"\u8be6\u60c5\">\u8be6\u60c5</a></li></ul>";
//								var V = $(Y);
//								G.append(V);
//								V.find("a[name='showDetail']").click(function () {
//									var b = $(this).attr("shopID");
//									var Z = new JQAjax("/DataServer/GetMemberCenterUserConsumptionDetail");
//									Z.OnSuccess(function (c) {
//										var e = c.d.Code;
//										if (e == 0) {
//											eval("c=" + c.d.Str);
//											var h = "<dd class=\"gray01\">本次消费合计：<em class=\"orange\">\uffe5" + c.money + ".00</em>   \u4e91\u8d2d\u65f6\u95f4\uff1a" + c.buyTime + "</dd><dd class=\"btn\"><a name=\"goBack\" href=\"javascript:void();\" title=\"\u8fd4\u56de\u5217\u8868\">\u8fd4\u56de\u5217\u8868</a><span></span></dd>";
//											var j = $(h);
//											$("#divDetailInfo").show().empty().append(j);
//											j.find("a[name='goBack']").click(function () {
//												$("#divDetailInfo").hide();
//												$("#divbuyDetail").hide();
//												$("#divTimeSel").show();
//												$("#divList1").show();
//												$("#divPageNav1").show();
//											});
//											var g = c.listItems;
//											var f = "";
//											for (var d = 0; d < g.length; d++) {
//												f += "<ul><li class=\"leftTitle\"><a target=\"_blank\" href=\"http://www.1yyg.com/product/" + g[d].codeID + ".html\">(\u7b2c" + g[d].codePeriod + "\u671f)" + g[d].goodsSName + "</a></li><li>1</li><li class=\"regard\">\uffe5" + g[d].buyNum + ".00</li></ul>";
//											}
//											$("#divTimeSel").hide();
//											$("#divList1").hide();
//											$("#divPageNav1").hide();
//											$("#divbuyDetail").html(M + f).show();
//										} else {
//											if (e == 10) {
//												location.reload();
//											} else {
//												if (e == -2) {
//													FailDialog("\u8bf7\u6c42\u5931\u8d25\uff01");
//												}
//											}
//										}
//									});
//									var a = {"shopID":b};
//									Z.OnSend($.toJSON(a), "json", true);
//								});
//							}
//						}
//						if (F > S) {
//							L.html(GetAjaxPageNationEx(F, S, 5, I, "CBLFun.gotoPageIndex", false)).show();
//						}
//					} else {
//						if (U == 10) {
//							location.reload();
//						} else {
//							if (U == -3) {
//								G.append("<div class=\"tips-con\"><i></i>\u6682\u65e0\u76f8\u5e94\u8bb0\u5f55</div>");
//							} else {
//								FailDialog("\u8bf7\u6c42\u5931\u8d25\uff01");
//							}
//						}
//					}
//				});
//				P.OnSend($.toJSON(Q), "json", true);
            };
            var E = function (T, U) {
                I = Math.floor(U / S);
                Q.FIdx = T;
                Q.EIdx = U;
                if (I > 1) {
                    Q.isCount = 0;
                } else {
                    Q.isCount = 1;
                }
                if (Q.type == 0) {
                    G.html(N);
                } else {
                    G.html(K);
                }
                L.html("").hide();
                R();
            };
            this.gotoPageIndex = function (T, U) {
                E(T, U);
            };
            this.initData = function () {
                E(1, S);
            };
        };
        CBLFun = new D();
        isLoaded = true;
        CBLFun.initData();
    };
    C();
});

