var CBLFun = null;
$(document).ready(function () {
    var i = $skin;
    var e = getobj("txtFirstT1");
    var d = getobj("sltFirstT2");
    var c = getobj("sltFirstT3");
    var h = getobj("txtEndT1");
    var g = getobj("sltEndT2");
    var f = getobj("sltEndT3");
    var a = function () {
        var k = function (l) {
            Base.getScript(i + "/JS/Date.js?date=20111209", l);
        };
        var j = function () {
            var o = ConvertDate(getobj("spanServerTime").html());
            var m = o.DateAdd("h", 2);
            var s = "", l = "", q = o.Format("HH"), r = m.Format("HH"), p = o.Format("mm");
            for (var n = 0; n < 24; n++) {
                if (n < 10) {
                    s = "0" + n;
                } else {
                    s = n;
                }
                d.append("<option value=\"" + s + "\"" + (s == q ? " selected" : "") + ">" + s + "</option>");
                g.append("<option value=\"" + s + "\"" + (s == r ? " selected" : "") + ">" + s + "</option>");
            }
            for (var n = 0; n < 60; n++) {
                if (n < 10) {
                    s = "0" + n;
                } else {
                    s = n;
                }
                l = "<option value=\"" + s + "\"" + (s == p ? " selected" : "") + ">" + s + "</option>";
                c.append(l);
                f.append(l);
            }
            e.val(o.Format("YYYY-MM-DD")).date_input();
            h.val(m.Format("YYYY-MM-DD")).date_input();
            Base.getScript(i + "/JS/AjaxFun.js?date=20130123", b);
        };
        Base.getScript(i + "/JS/DateInput.js?date=20111209", function () {
            k(j);
        });
    };
    $.loadcss(i + "/CSS/DateInput.css?date=20111209", a);
    var b = function () {
        var k = function (l) {
            $.PageDialog("<dl class=\"sAltFail\"><dd>" + l + "</dd></dl>", {
                W: 280,
                H: 50,
                close: false,
                autoClose: true
            });
        };
        var j = function () {
            var p = getobj("btnQuery");
            var n = false;
            var q = 20;
            var m = 1;
            var l = 0;
            var t = {FIdx: 1, EIdx: q, BTime: "", ETime: "", isCount: 1};
            var o = new Object();
            var u = function (v) {
                getobj("recordList").html(v);
            };
            var r = function () {
                if (!isLoaded) {
                    return false;
                }
                var v = new JQAjax("/DataServer/GetHistoryBuyRecords");
                v.OnStart(function (w) {
                    u("<ul class=\"Recordloading\"><li></li></ul>");
                });
                v.OnSuccess(function (x) {
                    var B = "<ul class=\"Record_title\"><li class=\"time\">\u4e91\u8d2d\u65f6\u95f4</li><li class=\"nem\">\u4f1a\u5458\u8d26\u53f7</li><li class=\"name\">\u4e91\u8d2d\u5546\u54c1\u540d\u79f0</li><li class=\"much\">\u4e91\u8d2d\u4eba\u6b21</li></ul>";
                    try {
                        var A = parseInt($(x).find("Code").text());
                        if (A == 0) {
                            if (t.isCount == 1) {
                                l = parseInt($(x).find("Count").text());
                            }
                            if (l > 0) {
                                var w = B;
                                $(x).find("historyRecords").each(function (C) {
                                    w += "<ul class=\"Record_content" + ((C + 1) % 2 == 0 ? "s" : "") + "\">";
                                    w += "<li class=\"time\">" + $(this).find("buyTime").text() + "</li>";
                                    w += "<li class=\"nem\"><a class=\"blue\" href=\"http://u.1yyg.com/" + $(this).find("userWeb").text() + "\" target=\"_blank\">" + $(this).find("buyName").text() + "</a></li>";
                                    w += "<li class=\"name\"><a href=\"http://www.1yyg.com/product/" + $(this).find("buyCode").text() + ".html\">\uff08\u7b2c" + $(this).find("goodsPeriod").text() + "\u671f\uff09" + $(this).find("goodsName").text() + "</a></li>";
                                    w += "<li class=\"much\">" + $(this).find("buyNum").text() + "\u4eba\u6b21</li>";
                                    w += "</ul>";
                                });
                                var y = "";
                                if (l > q) {
                                    w += "<div class=\"page_nav\">" + GetAjaxPageNation(l, q, 5, m, "CBLFun.gotoPageIndex") + "</div>";
                                }
                                u(w);
                                n = true;
                                o["page" + m] = w;
                            } else {
                                u(B + "<ul class=\"Recordnone\"><li>\u5728\u6b64\u671f\u95f4\u67e5\u65e0\u4efb\u4f55\u4e91\u8d2d\u8bb0\u5f55\u3002</li></ul>");
                            }
                        } else {
                            if (A == 2) {
                                k("\u5bf9\u4e0d\u8d77\uff0c\u67e5\u8be2\u533a\u95f4\u8de8\u5ea6\u4e0d\u5f97\u8d85\u8fc72\u5c0f\u65f6\u3002");
                            } else {
                                u(B);
                            }
                        }
                    }
                    catch (z) {
                    }
                });
                v.OnSend(t, "xml", true);
            };
            gotoPageFun = function (v, w) {
                m = Math.floor(w / q);
                if (o["page" + m] != null) {
                    u(o["page" + m]);
                } else {
                    t.FIdx = v;
                    t.EIdx = w;
                    if (v > 1) {
                        t.isCount = 0;
                    }
                    r();
                }
            };
            var s = function () {
                var x = e.val() + " " + d.val() + ":" + c.val() + ":00";
                var v = h.val() + " " + g.val() + ":" + f.val() + ":59";
                if (IsValidDateTime(x) && IsValidDateTime(v)) {
                    var w = ConvertDate(x);
                    timeB = ConvertDate(v);
                    if (w.DateAdd("n", 121) < timeB) {
                        k("\u5bf9\u4e0d\u8d77\uff0c\u67e5\u8be2\u533a\u95f4\u8de8\u5ea6\u4e0d\u5f97\u8d85\u8fc72\u5c0f\u65f6\u3002");
                    } else {
                        if (w > timeB) {
                            k("\u5f00\u59cb\u65f6\u95f4\u4e0d\u80fd\u5927\u4e8e\u7ed3\u675f\u65f6\u95f4\u3002");
                        } else {
                            t.BTime = x;
                            t.ETime = v + ".999";
                            t.isCount = 1;
                            o = null;
                            o = new Object();
                            gotoPageFun(1, q);
                        }
                    }
                } else {
                    k("\u60a8\u67e5\u8be2\u7684\u65f6\u95f4\u683c\u5f0f\u6709\u8bef\uff0c\u8bf7\u68c0\u67e5\u3002");
                }
            };
            this.gotoPageIndex = function (v, w) {
                gotoPageFun(v, w);
            };
            this.initData = function () {
                if (!n) {
                    p.bind("click", s);
                    s();
                }
            };
        };
        CBLFun = new j();
        isLoaded = true;
        CBLFun.initData();
    };
});

