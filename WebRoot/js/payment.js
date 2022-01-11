var PagePOPLoginOK = null;
$(function () {
    var w = "http://skin.1ypg.com";
    var A = function (J, H) {
        var I = "<div class=\"mAltFail\"><s></s>" + J + "</div>";
        $.PageDialog(I, {
            W: 200, H: 60, close: false, autoClose: true, submit: function () {
                if (H != undefined) {
                    H();
                }
            }
        });
    };
    var l = false;
    var G = true;
    var c = false;
    var a = parseInt($("#hidBalance").val());
    var k = parseInt($("#hidCountMoney").val());
    var b = parseInt($("#hidPoints").val());
    var i = parseInt($("#hidAvailablePoints").val());
    var B = k;
    var F = 0;
    var y = $("#submit_ok");
    if (k < 1 || isNaN(k)) {
        alert("购物车中暂时没有商品!马上去" + $shortName + "吧!");
        location.replace($www);
    }
    var t = $("#liPayByPoints");
    var C = t.find("input[type='checkbox']").eq(0);
    var h = t.find("p.payment_List_Rc").eq(0);
    var d = t.find("input[name='costPoint']").eq(0);
    var r = $("#liPayByBalance");
    var o = r.find("input[type='checkbox']").eq(0);
    var q = r.find("p.payment_List_Rc").eq(0);
    var e = $("#liPayByOther");
    var E = $("#divBankList");
    var n = $("#pNewPointNum");
    var v = -1;
    if (i < 100) {
        C.hide();
    }
    if (a > 0) {
        o.attr("checked", true);
        if (a < k) {
            $("#pBalanceTip").show();
        }
    } else {
        o.hide();
    }
    var u = $("#pPointsTip");
    var m = function (H) {
        if (H == "") {
            u.hide();
        } else {
            u.html("<span>\u25c6</span><em>\u25c6</em>" + H).show();
        }
    };
    var p = function (H) {
        $(H).unbind("keyup").bind("keyup", function () {
            G = true;
            var I = $(this).val();
            if (I != "") {
                var K = k * 100;
                if (I != "0") {
                    var J = /^[1-9]+(\d*)([0]{2})$/;
                    if (!J.test(I)) {
                        m("福分必须为100的整数倍");
                        G = false;
                    } else {
                        I = parseInt(I);
                        if (I < 100) {
                            G = false;
                        }
                        if (I > i) {
                            m("本次消费最多可使用 " + i + " 福分");
                            G = false;
                        }
                    }
                }
            }
            if (!G) {
                F = 0;
                f(0, a);
                s(0, "0");
            } else {
                m("");
                F = I - I % 100;
                f(F, a);
            }
        }).unbind("focus").bind("focus", function () {
            if (G && $(this).val() == "") {
                m("福分必须为100的整数倍");
            }
            C.attr("checked", true);
        }).unbind("blur").bind("blur", function () {
            if (!G) {
                s(0, "0");
            }
            if ($(this).val() == "") {
                m("");
            }
        });
    };
    var x = function (H) {
        $(H).unbind("click").bind("click", function () {
            F = 0;
            if ($(this).attr("checked")) {
                d.focus();
                m("福分必须为100的整数倍");
                s(0, "0");
                f(0, o.attr("checked") ? a : 0);
            } else {
                $("#hidIntegral").val("0");
                d.val("");
                m("");
                f(0, a);
            }
        });
    };
    var j = function (H) {
        $(H).unbind("click").bind("click", function () {
            if ($(this).attr("checked")) {
                f(F, a);
            } else {
                f(F, 0);
            }
        });
    };
    var s = function (I, J) {
        switch (I) {
            case 0:
                if (J == "") {
                    if (C.attr("checked")) {
                        h.html("福分抵扣：<span class=\"orange Fb\">0.00</span> 元");
                    } else {
                        h.html("");
                    }
                } else {
                    h.html("福分抵扣：<span class=\"orange Fb\">" + J + ".00</span> 元");
                }
                break;
            case 1:
                if (J == "") {
                    q.html("");
                } else {
                    if (J < 0) {
                        m("本次只需抵扣" + (k * 100) + "福分");
                        v = 0;
                        G = false;
                        r.hide();
                    } else {
                        q.html("余额支付：<span class=\"orange F20\">" + J + ".00</span> 元");
                        $("#hidUseBalance").val(J);
                    }
                }
                break;
            case 2:
                if (J == "") {
                    e.hide();
                    E.hide();
                } else {
                    var H = "您的账户余额不足，请选择以下方式完成支付";
                    if (a >= k) {
                        H = "您可使用账户余额支付，也可选择以下方式完成支付";
                    }
                    $("#moneyCount").val(J);
                    e.find("div.payment_List_Lc").eq(0).html(H);
                    e.show().find("p.payment_List_Rc").eq(0).html("网银支付：<span class=\"orange F20\">" + J + ".00</span> 元");
                    E.show();
                }
                break;
            case 3:
                if (J > 0) {
                    n.html("成功支付可获得<b>" + J + "</b>福分").show();
                } else {
                    n.html("&nbsp;");
                }
                break;
        }
    };
    var f = function (J, K) {
        var I = 0;
        v = -1;
        s(0, "");
        s(1, "");
        s(2, "");
        s(3, "");
        var L = o.attr("checked");
        if (J >= 100) {
            D(0, true);
            I = k - J / 100;
            if (I == 0) {
                v = 0;
                s(0, k);
                D(1, false);
                D(2, false);
            } else {
                s(0, J / 100);
                if (K > 0 && L) {
                    D(1, true);
                    var H = K - I;
                    if (H >= 0) {
                        v = 1;
                        s(1, I);
                        D(2, false);
                    } else {
                        v = 6;
                        s(1, K);
                        s(2, -H);
                        D(2, true);
                    }
                    s(3, I);
                } else {
                    v = 2;
                    s(2, I);
                    s(3, I);
                    D(2, true);
                }
            }
        } else {
            D(0, false);
            if (K > 0) {
                if (L) {
                    D(1, true);
                    I = K - k;
                    if (I >= 0) {
                        v = 3;
                        s(1, k);
                        D(2, false);
                    } else {
                        v = 4;
                        s(1, K);
                        s(2, -I);
                        D(2, true);
                    }
                    s(3, k);
                } else {
                    D(1, false);
                    v = 5;
                    s(2, k);
                    s(3, k);
                    D(2, true);
                }
            } else {
                v = 5;
                s(2, k);
                s(3, k);
                D(2, true);
            }
        }
    };
    var D = function (I, H) {
        switch (I) {
            case 0:
                if (!l && (H || i >= 100)) {
                    l = true;
                    t.removeClass("point_gray");
                    C.attr("disabled", "").attr("checked", true).show();
                    x(C);
                    d.attr("disabled", "").attr("class", "pay_input_text").focus();
                    p(d);
                    m("福分必须为100的整数倍");
                    s(0, "0");
                }
                break;
            case 1:
                if (a > 0) {
                    if (v == 0) {
                        r.hide();
                    } else {
                        r.show().removeClass("point_gray");
                        o.attr("disabled", "").attr("checked", H);
                        j(o);
                    }
                }
                break;
            case 2:
                if (H) {
                    e.show();
                    E.show();
                    c = true;
                } else {
                    e.hide();
                    E.hide();
                }
                break;
        }
    };
    var g = function (J, H, I) {
        //$.getJSON("http://account.1yyg.com/JPData/API.ashx?action=" + J + "&" + H + "&fun=?", I);
    };
    var z = function () {
        var H = function () {
            d.focus();
        };
        y.bind("click", function () {
            if (!G) {
                A("使用福分输入不对哦！", H);
                return false;
            }
            $("#hidIntegral").val(F);
            var I = function () {
                var L = function () {
                    $("#btnBuyOk", "#pageDialog").bind("click", function () {
                        location.replace($www + "/user/UserBuyList.html");
                    });
                    $("#btnReSelect", "#pageDialog").bind("click", function () {
                        $.PageDialog.close();
                    });
                };
                $.PageDialog($("#payAltBox").html(), {W: 405, H: 180, title: "支付提醒", ready: L});
            };
            var J = function (L) {
                var M = "";
                $("input[name=account]").each(function () {
                    if ($(this).attr("checked")) {
                        M = $(this).val();
                    }
                });
                if (M == "") {
                    alert("请选择支付方式！");
                    return false;
                }
                if ($("#Alipay").attr("checked") == true) {
                    $("#toPayForm").attr("action", $www + "/alipay/goPay.action");
                } else if ($("#Tenpay").attr("checked") == true) {
                    $("#toPayForm").attr("action", $www + "/tenpay/goPay.action");
                } else if ($("#Yeepay").attr("checked") == true) {
                    $("#toPayForm").attr("action", $www + "/yeepay/goPay.action");
                } else {
                    $("#toPayForm").attr("action", $www + "/tenpay/goPay.action");
                }
//				if(M=="Alipay"){
//					$("#toPayForm").attr("action",$www+"/alipay/goPay.action");
//				}
                if (/^\d{4}$/.test(M)) {
                    $("#hidPayName").val("Tenpay");
                    $("#hidPayBank").val(M);
                } else {
                    $("#hidPayName").val(M);
                    $("#hidPayBank").val("0");
                }
                $("#hidUseBalance").val(L);
                I();
            };
            var K = function () {
                location.replace($www + "/mycart/goPay.html?moneyCount=" + k + "&integral=" + F + "&userPayType=2");
                return false;
            };
            var X = function () {
                location.replace($www + "/mycart/goPay.html?moneyCount=" + k + "&integral=" + F + "&hidUseBalance=" + $("#hidUseBalance").val() + "&userPayType=1");
                return false;
            }
            var T = function () {
                location.replace($www + "/mycart/goPay.html?moneyCount=" + k + "&integral=" + F + "&hidUseBalance=" + $("#hidUseBalance").val() + "&userPayType=3");
                return false;
            }
            if (v == -1) {
                A("请选择支付方式！");
            } else {
                if (v == 0) {
                    if (F <= 0) {
                        A("请输入需要抵扣的福分！", H);
                    } else {
                        $.PageDialog.showConfirm("您确定使用福分抵扣吗?", K);
//						if (confirm("您确定使用福分抵扣吗?")) {
//							K();
//						}
                    }
                    return false;
                } else {
                    if (v == 1) {
                        $.PageDialog.showConfirm("您确定使用福分和余额支付吗?", T);
                        return false;
                    }
                    if (v == 3) {
                        $.PageDialog.showConfirm("您确定使用账户余额支付吗?", X);
//						if (confirm("您确定使用账户余额支付吗?")) {
//							K();
//						}
                        return false;
                    } else {
                        if (v == 4 || v == 6) {
                            J(1);
                        } else {
                            J(0);
                        }
                    }
                }
            }
            return true;
        });
        f(0, a);
    };
    z();
});

