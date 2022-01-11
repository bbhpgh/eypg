$(document).ready(function () {
    var resultCount = $("#resultCount").val();
    var pageNo = 0;
    var userId = $("#userId").val();
    var M = $("#goods_list");
    var O = $("[name=divTip]");
    var p = $("<ul></ul>");
    var X = $("#txtMisTime");
    var F = $("#txtMaxTime");
    var N = $("#btnSearch");
    var W = $("[class=record-cur]").attr("name");
    var a = new Date();
    var startDate = X.val();
    var endDate = F.val();
    $(".record-tab a").each(function (D, C) {
        $(this).bind("click", function () {
            $(this).addClass("record-cur").siblings().removeClass("record-cur");
            W = $("[class=record-cur]").attr("name");
            $("#goods_list").empty();
            selectTime(W);
        });
    });

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
        items_per_page: 5,
        callback: pageselectCallback
    });

    function pageselectCallback(pageNo) {
        var url = "/user/userBuyListAjaxPage.action?pageNo=" + pageNo + "&userId=" + userId + "&selectTime=" + W + "&startDate=" + startDate + "&endDate=" + endDate;
        $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
        $.ajax({
            url: url,
            type: "post",
            data: "json",
            beforeSend: loading,
            success: function (data) {
                data = eval("(" + data + ")");
                $("#goods_list").empty();
                if (resultCount > 0) {
                    $("<ul class=\"gtitle\"><li>商品图片</li><li class=\"gname\">商品名称</li><li class=\"yg_status\">" + $shortName + "状态</li><li class=\"joinInfo\">参与人次/" + $shortName + "码</li><li class=\"do\">操作</li></ul>").appendTo("#goods_list");
                    $(".pageULEx").show();
                    $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 5) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].buyStatus == "0") {
                            var K = parseInt(data[i].productPrice) - parseInt(data[i].spellbuyCount);
                            var H = parseFloat(data[i].spellbuyCount) / parseFloat(data[i].productPrice);
                            var E = 206 * H;
                            E = (E < 1 && parseInt(data[i].spellbuyCount) > 0) ? 1 : E;
                            $("<ul class=\"goods_list\"><li><a href=\"" + $www + "/products/" + data[i].productId + ".html\" class=\"pic\" target=\"_blank\" title=\"\"><img src=\"" + $img + data[i].productImg + "\"></a></li>" +
                                "<li class=\"gname\"><a class=\"blue\" href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\">(第" + data[i].productPeriod + "期)" + data[i].productName + "</a><p class=\"gray02\">价值：<span class=\"money\"><i class=\"rmb\"></i>" + data[i].productPrice + ".00</span></p>" +
                                "<div class=\"Progress-bar\"><p><span style=\"width: " + E + "px\"></span></p><ul class=\"Pro-bar-li\"><li class=\"P-bar01\"><em>" + data[i].spellbuyCount + "</em>已参与人次</li><li class=\"P-bar02\"><em>" + data[i].productPrice + "</em>总需人次</li>" +
                                "<li class=\"P-bar03\"><em>" + K + "</em>剩余人次</li></ul></div></li><li class=\"yg_status\"><span class=\"green\">正在进行……</span><br><a class=\"gray01\" name=\"appendBuy\" href=\"JavaScript:;\">追加" + $shortName + "人次</a>" +
                                "<div style=\"display:none;\" class=\"add\"><dl><input type=\"hidden\" value=\"" + K + "\"><dd><input type=\"text\" value=\"\" class=\"amount\" onpaste=\"return false\" title=\"购买人次\"></dd>" +
                                "<dd><a href=\"JavaScript:;\" class=\"jia\"></a><a href=\"JavaScript:;\" class=\"jian\"></a></dd><dd><input type=\"hidden\" value=\"" + data[i].productId + "\">" +
                                "<input type=\"button\" name=\"btnAppendBuy\" value=\"确 定\" class=\"orangebut btn29\" title=\"追加" + $shortName + "人次\"></dd></dl></div></li><li buynum=\"" + data[i].buyCount + "\" codeid=\"" + data[i].productId + "\" class=\"joinInfo\"><p><em>" + data[i].buyCount + "</em>人次</p>" +
                                "<div class=\"joinInfo-Pop\"><div class=\"grhead-join gray01\" name=\"divTip\" style=\"display: block;\">所有" + $shortName + "码<b></b></div><div style=\"display: none;\" class=\"grhead-joinC\" name=\"divShowRNO\">" +
                                "<div class=\"grhead-joinCT gray01\">所有" + $shortName + "码<b></b></div><div class=\"grhead-joinClist\" name=\"divRNOList\"></div></div></div></li>" +
                                "<li class=\"do\"><a title=\"详情\" class=\"blue\" href=\"" + $www + "/user/UserBuyDetail-" + data[i].productId + ".html\">详情</a></li></ul>").appendTo("#goods_list");
                        } else {
                            $("<ul class=\"goods_list\"><li><a href=\"" + $www + "/products/" + data[i].productId + ".html\" class=\"pic\" target=\"_blank\" title=\"\"><img src=\"" + $img + data[i].productImg + "\"></a></li>" +
                                "<li class=\"gname\"><a class=\"blue\" href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\">(第" + data[i].productPeriod + "期)" + data[i].productName + "</a><p class=\"gray02\">获得者：<a class=\"blue\" target=\"_blank\" href=\"" + $www + "/u/" + data[i].winUserId + ".html\">" + data[i].winUser + "</a></p>" +
                                "<p class=\"gray02\">揭晓时间：" + data[i].winDate + "</p></li><li class=\"yg_status\"><span class=\"orange\">已揭晓</span></li><li buynum=\"" + data[i].buyCount + "\" codeid=\"" + data[i].productId + "\" class=\"joinInfo\"><p><em>" + data[i].buyCount + "</em>人次</p><div class=\"joinInfo-Pop\">" +
                                "<div class=\"grhead-join gray01\" name=\"divTip\">所有" + $shortName + "码<b></b></div><div style=\"display:none;\" class=\"grhead-joinC\" name=\"divShowRNO\"><div class=\"grhead-joinCT gray01\">所有" + $shortName + "码<b></b></div>" +
                                "<div class=\"grhead-joinClist\" name=\"divRNOList\"></div></div></div></li><li class=\"do\"><a title=\"详情\" class=\"blue\" href=\"" + $www + "/user/UserBuyDetail-" + data[i].productId + ".html\">详情</a></li></ul>").appendTo("#goods_list");
                        }
                    }
                    $("#pageLoading").hide();

                    $("a[name='appendBuy']", M).click(function (N) {
                        $(this).parent().parent().siblings().each(function () {
                            $(this).find(".add").hide();
                            $(this).find("a[name='appendBuy']").show();
                        });
                        $(this).parent().find(".add").show().click(function (O) {
                            f(O);
                        });
                        $(this).parent().find("input[type=text]").focus().val("1").select();
                        $(this).hide();
                        f(N);
                    });
                    $(".amount", M).mouseover(function () {
                        $(this).focus().select();
                    }).keydown(function (O) {
                        if (13 == ((window.event) ? event.keyCode : O.keyCode)) {
                            var N = $(this).parent().parent().find("input[type=button]");
                            h(N, O);
                        }
                        f(O);
                    });
                    $(".jia", M).click(function () {
                        var N = $(this).parent().parent().find("input[type=text]");
                        if (isNaN(parseInt(N.val()))) {
                            return;
                        }
                        u("add", $(this));
                    });
                    $(".jian", M).click(function () {
                        var O = $(this).parent().parent().find("input[type=text]");
                        var N = O.val();
                        if (isNaN(parseInt(N)) || N == "0") {
                            O.focus().select();
                            return;
                        }
                        u("sub", $(this));
                    });
                    $("input[name='btnAppendBuy']", M).click(function (N) {
                        h($(this), N);
                    });

                    $(".joinInfo", M).hover(function () {
                        var Q = $(this);
                        Q.find("div[name='divTip']").hide();
                        Q.find("div[name='divShowRNO']").show();
                        var N = Q.attr("codeID");
                        var O = Q.attr("buyNum");
                        var P = Q.find("div[name='divRNOList']");
                        e(P, N, O);
                    }, function () {
                        $(this).find("div[name='divTip']").show();
                        $(this).find("div[name='divShowRNO']").hide();
                        p.remove();
                    });
                } else {
                    $("<ul class=\"gtitle\"><li>商品图片</li><li class=\"gname\">商品名称</li><li class=\"yg_status\">" + $shortName + "状态</li><li class=\"joinInfo\">参与人次/" + $shortName + "码</li><li class=\"do\">操作</li></ul>").appendTo("#goods_list");
                    $("<div class=\"tips-con\"><i></i>无相应的" + $shortName + "记录</div>").appendTo("#goods_list");
                    $(".pageULEx").hide();
                }
            }
        });
    }

    var u = function (D, G) {
        var H = G.parent().parent().find("input[type=text]");
        var F = parseInt(G.parent().parent().find("input[type=hidden]").val());
        var E = o(H, F);
        if (!E) {
            return;
        }
        var C = parseInt(H.val());
        if (D == "add") {
            if (C >= F) {
                C = F;
            } else {
                C++;
            }
        } else {
            if (D == "sub") {
                if (C <= 1) {
                    C = 1;
                } else {
                    C--;
                }
            }
        }
        H.val(C);
    };
    var o = function (E) {
        var C = E.val();
        var D = parseInt(E.parent().parent().find("input[type=hidden]").val());
        if (C == "") {
            alert("请输入" + $shortName + "人次！");
            return false;
        } else {
            if (isNaN(C)) {
                alert("您输入的" + $shortName + "人次好像不对哦!");
                return false;
            } else {
                if (parseInt(C) < 1) {
                    alert("最少需" + $shortName + "1人次");
                    return false;
                } else {
                    if (parseInt(C) > D) {
                        E.val(D);
                        alert("本期最多可" + $shortName + "" + D + "人次!");
                        return false;
                    }
                }
            }
        }
        return true;
    };
    var h = function (E, F) {
        var G = E.parent().parent().find("input[type=text]");
        if (!o(G)) {
            var D = E.parent().parent();
            $(".jian", D).focus();
        } else {
            var C = E.parent().find("input[type=hidden]").val();
            if (G.val() != "" && G.val() != "0") {
                var json = getCookie("products");
                if (json == null || json == "") {
                    var val = "[{'pId':" + C + ",'num':" + G.val() + "}]";
                    SetCookieByExpires("products", val, 1);
                } else {
                    var flag = false;
                    json = eval('(' + json + ')');
                    $.each(json, function (n, value) {
                        if (C == value.pId) {
                            value.num = (parseInt(value.num) + parseInt(G.val()));
                            flag = true;
                        }
                    });
                    if (!flag) {
                        json.push({"pId": +C, "num": G.val()});
                    }
                    json = JSON.stringify(json);
                    SetCookieByExpires("products", json, 1);
                }
                location.href = $www + "/mycart/payment.html";
            }
        }
    };
    var f = function (C) {
        if (C && C.stopPropagation) {
            C.stopPropagation();
        } else {
            window.event.cancelBubble = true;
        }
    };
    var r = function (C) {
        C = Math.round(C * 1000) / 1000;
        C = Math.round(C * 100) / 100;
        if (/^\d+$/.test(C)) {
            return C + ".00";
        }
        if (/^\d+\.\d$/.test(C)) {
            return C + "0";
        }
        return C;
    };
    $().click(function (C) {
        $(this).find(".add").hide();
        $(this).find("a[name='appendBuy']").show();
        f(C);
    });
    var B = function (C, D) {
        i = Math.floor(D / l);
        q.FIdx = C;
        q.EIdx = D;
        if (i > 1) {
            q.isCount = 0;
        } else {
            q.isCount = 1;
        }
        if (q.state == 4) {
            n.html(j);
        } else {
            n.html(x);
        }
        $("#divPageNav").html("");
        v();
    };

    function pageselectCallback2(pageNo) {
        var url = "/user/userBuyListAjaxPage.action?pageNo=" + pageNo + "&userId=" + userId + "&startDate=" + startDate + "&endDate=" + endDate;
        $(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
        $.ajax({
            url: url,
            type: "post",
            data: "json",
            beforeSend: loading,
            success: function (data) {
                data = eval("(" + data + ")");
                $("#goods_list").empty();
                if (resultCount > 0) {
                    $("<ul class=\"gtitle\"><li>商品图片</li><li class=\"gname\">商品名称</li><li class=\"yg_status\">" + $shortName + "状态</li><li class=\"joinInfo\">参与人次/" + $shortName + "码</li><li class=\"do\">操作</li></ul>").appendTo("#goods_list");
                    $(".pageULEx").show();
                    $(".pageULEx").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 5) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].buyStatus == "0") {
                            var K = parseInt(data[i].productPrice) - parseInt(data[i].spellbuyCount);
                            var H = parseFloat(data[i].spellbuyCount) / parseFloat(data[i].productPrice);
                            var E = 206 * H;
                            E = (E < 1 && parseInt(data[i].spellbuyCount) > 0) ? 1 : E;
                            $("<ul class=\"goods_list\"><li><a href=\"" + $www + "/products/" + data[i].productId + ".html\" class=\"pic\" target=\"_blank\" title=\"\"><img src=\"" + $img + data[i].productImg + "\"></a></li>" +
                                "<li class=\"gname\"><a class=\"blue\" href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\">(第" + data[i].productPeriod + "期)" + data[i].productName + "</a><p class=\"gray02\">价值：<span class=\"money\"><i class=\"rmb\"></i>" + data[i].productPrice + ".00</span></p>" +
                                "<div class=\"Progress-bar\"><p><span style=\"width: " + E + "px\"></span></p><ul class=\"Pro-bar-li\"><li class=\"P-bar01\"><em>" + data[i].spellbuyCount + "</em>已参与人次</li><li class=\"P-bar02\"><em>" + data[i].productPrice + "</em>总需人次</li>" +
                                "<li class=\"P-bar03\"><em>" + K + "</em>剩余人次</li></ul></div></li><li class=\"yg_status\"><span class=\"green\">正在进行……</span><br><a class=\"gray01\" name=\"appendBuy\" href=\"JavaScript:;\">追加" + $shortName + "人次</a>" +
                                "<div style=\"display:none;\" class=\"add\"><dl><input type=\"hidden\" value=\"" + K + "\"><dd><input type=\"text\" value=\"\" class=\"amount\" onpaste=\"return false\" title=\"购买人次\"></dd>" +
                                "<dd><a href=\"JavaScript:;\" class=\"jia\"></a><a href=\"JavaScript:;\" class=\"jian\"></a></dd><dd><input type=\"hidden\" value=\"" + data[i].productId + "\">" +
                                "<input type=\"button\" name=\"btnAppendBuy\" value=\"确 定\" class=\"orangebut btn29\" title=\"追加" + $shortName + "人次\"></dd></dl></div></li><li buynum=\"" + data[i].buyCount + "\" codeid=\"" + data[i].productId + "\" class=\"joinInfo\"><p><em>" + data[i].buyCount + "</em>人次</p>" +
                                "<div class=\"joinInfo-Pop\"><div class=\"grhead-join gray01\" name=\"divTip\" style=\"display: block;\">所有" + $shortName + "码<b></b></div><div style=\"display: none;\" class=\"grhead-joinC\" name=\"divShowRNO\">" +
                                "<div class=\"grhead-joinCT gray01\">所有" + $shortName + "码<b></b></div><div class=\"grhead-joinClist\" name=\"divRNOList\"></div></div></div></li>" +
                                "<li class=\"do\"><a title=\"详情\" class=\"blue\" href=\"" + $www + "/user/UserBuyDetail-" + data[i].productId + ".html\">详情</a></li></ul>").appendTo("#goods_list");
                        } else {
                            $("<ul class=\"goods_list\"><li><a href=\"" + $www + "/products/" + data[i].productId + ".html\" class=\"pic\" target=\"_blank\" title=\"\"><img src=\"" + $img + data[i].productImg + "\"></a></li>" +
                                "<li class=\"gname\"><a class=\"blue\" href=\"" + $www + "/products/" + data[i].productId + ".html\" target=\"_blank\">(第" + data[i].productPeriod + "期)" + data[i].productName + "</a><p class=\"gray02\">获得者：<a class=\"blue\" target=\"_blank\" href=\"" + $www + "/u/" + data[i].winUserId + ".html\">" + data[i].winUser + "</a></p>" +
                                "<p class=\"gray02\">揭晓时间：" + data[i].winDate + "</p></li><li class=\"yg_status\"><span class=\"orange\">已揭晓</span></li><li buynum=\"" + data[i].buyCount + "\" codeid=\"" + data[i].productId + "\" class=\"joinInfo\"><p><em>" + data[i].buyCount + "</em>人次</p><div class=\"joinInfo-Pop\">" +
                                "<div class=\"grhead-join gray01\" name=\"divTip\">所有" + $shortName + "码<b></b></div><div style=\"display:none;\" class=\"grhead-joinC\" name=\"divShowRNO\"><div class=\"grhead-joinCT gray01\">所有" + $shortName + "码<b></b></div>" +
                                "<div class=\"grhead-joinClist\" name=\"divRNOList\"></div></div></div></li><li class=\"do\"><a title=\"详情\" class=\"blue\" href=\"" + $www + "/user/UserBuyDetail-" + data[i].productId + ".html\">详情</a></li></ul>").appendTo("#goods_list");
                        }
                    }
                    $("#pageLoading").hide();
                    $("a[name='appendBuy']", M).click(function (N) {
                        $(this).parent().parent().siblings().each(function () {
                            $(this).find(".add").hide();
                            $(this).find("a[name='appendBuy']").show();
                        });
                        $(this).parent().find(".add").show().click(function (O) {
                            f(O);
                        });
                        $(this).parent().find("input[type=text]").focus().val("1").select();
                        $(this).hide();
                        f(N);
                    });
                    $(".amount", M).mouseover(function () {
                        $(this).focus().select();
                    }).keydown(function (O) {
                        if (13 == ((window.event) ? event.keyCode : O.keyCode)) {
                            var N = $(this).parent().parent().find("input[type=button]");
                            h(N, O);
                        }
                        f(O);
                    });
                    $(".jia", M).click(function () {
                        var N = $(this).parent().parent().find("input[type=text]");
                        if (isNaN(parseInt(N.val()))) {
                            return;
                        }
                        u("add", $(this));
                    });
                    $(".jian", M).click(function () {
                        var O = $(this).parent().parent().find("input[type=text]");
                        var N = O.val();
                        if (isNaN(parseInt(N)) || N == "0") {
                            O.focus().select();
                            return;
                        }
                        u("sub", $(this));
                    });
                    $("input[name='btnAppendBuy']", M).click(function (N) {
                        h($(this), N);
                    });

                    $(".joinInfo", M).hover(function () {
                        var Q = $(this);
                        Q.find("div[name='divTip']").hide();
                        Q.find("div[name='divShowRNO']").show();
                        var N = Q.attr("codeID");
                        var O = Q.attr("buyNum");
                        var P = Q.find("div[name='divRNOList']");
                        e(P, N, O);
                    }, function () {
                        $(this).find("div[name='divTip']").show();
                        $(this).find("div[name='divShowRNO']").hide();
                        p.remove();
                    });
                } else {
                    $("<div class=\"tips-con\"><i></i>无相应的" + $shortName + "记录</div>").appendTo("#goods_list");
                    $(".pageULEx").hide();
                }
            }
        });
    }

    function loading() {
        $("#pageLoading").show();
    }

    var e = function (E, D, C) {
        if (!isLoaded) {
            return;
        }
        p.html("\u6b63\u5728\u52a0\u8f7d...");
        E.append(p);
        $.ajax({
            url: "/user/getRandomNumberList.action?id=" + D + "&userId=" + userId,
            data: "JSON",
            type: "GET",
            success: function (data) {
//					data = eval("("+data+")");
                var H = "";
                var I = data.length;
                H = data.substring(0, 85);
                if (I > 85) {
                    H += "<li><a href=\"/user/UserBuyDetail-" + D + ".html\" >\u67e5\u770b\u66f4\u591a</a></li>";
                }
                p.html(H).show();
            },
            error: function () {
                location.reload();
            }
        });
    };
    var selectTime = function (W) {
        var selectVal = W;
        $.ajax({
            url: "/user/getuserBuyListAjaxPageResultCount.action?pageNo=" + pageNo + "&selectTime=" + selectVal + "&userId=" + userId,
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
                    items_per_page: 5,
                    callback: pageselectCallback
                });
            }
        });
    };

    N.click(function () {
        $(".record-tab a").attr("class", "");
        $("#goods_list").empty();
        c();
    });

    var c = function () {
        var d = X.val();
        var e = F.val();
        if (!IsValidDate(d)) {
            alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u5f00\u59cb\u65e5\u671f!");
            return;
        }
        if (!IsValidDate(e)) {
            alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7ed3\u675f\u65e5\u671f!");
            return;
        }
        if (ConvertDate(e) < ConvertDate(d)) {
            alert("\u7ed3\u675f\u65e5\u671f\u4e0d\u80fd\u5c0f\u4e8e\u5f00\u59cb\u65e5\u671f!");
            return;
        }
        $.ajax({
            url: "/user/getuserBuyListAjaxPageResultCount.action?pageNo=" + pageNo + "&userId=" + userId + "&startDate=" + d + "&endDate=" + e,
            type: "get",
            data: "String",
            success: function (data) {
                resultCount = parseInt(data);
                startDate = d;
                endDate = e;
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
                    items_per_page: 5,
                    callback: pageselectCallback2
                });
            }
        });
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

    isLoaded = true;
    X.val(a.DateAdd("m", -1).Format("YYYY-MM-DD")).date_input();
    F.val(a.Format("YYYY-MM-DD")).date_input();
});

