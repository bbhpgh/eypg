$(function () {
    var A = $skin;
    var B = function () {
        var H = "";
        var J = $("#submit_ok");
        var G = $("#Money");
        var I = $("#txtOtherMoney");
        var C = function (L, M) {
            $.PageDialog($("#payAltBox").html(), {W: 405, H: 180, title: "支付提醒"});
            $("#btnReSelect").live("click", function () {
                $("#pageDialog").hide();
                $("#pageDialogBG").hide();
                $("#pageDialogBorder").hide();
            });
        };
        var E = function (K) {
            if (/^\d{4}$/.test(H)) {
                $("#hidPayName").val("Tenpay");
                $("#hidPayBank").val(H);
            } else {
                $("#hidPayName").val(H);
                $("#hidPayBank").val("0");
            }
            $("#hidMoney").val(K);
            C();
        };
        $("#ulMoneyList").find("li").each(function () {
            var K = $(this);
            K.hover(function () {
                var L = K.find("input[type='radio']");
                if (!L.attr("checked")) {
                    K.addClass("rdoCheck");
                }
            }, function () {
                K.removeClass("rdoCheck");
            });
            K.click(function () {
                $(this).addClass("selected").siblings().removeClass("selected");
                var M = $(this).find("input[type='radio']");
                M.attr("checked", true);
                var N = M.val();
                if (N == 0) {
                    I.focus();
                    var L = I.val();
                    G.html(L == "" ? "0" : L);
                } else {
                    G.html(N);
                    I.val("");
                }
            });
        });
        var D = function () {
            var K = I.val();
            if (K == "") {
                G.html(0);
            } else {
                if (isNaN(parseInt(K)) || K == "0") {
                    if (G.html() == "0") {
                        I.val("");
                    } else {
                        I.val(G.html());
                    }
                } else {
                    G.html(parseInt(K));
                }
            }
        };
        I.focus(function () {
            $("#rdOther").attr("checked", true);
        }).bind("keyup", function (L) {
            D();
            var K = (window.event) ? event.keyCode : L.keyCode;
            if (K == 13) {
                J.focus();
            }
        });
        var F = false;
        J.click(function () {
            if ($("#Alipay").attr("checked") == true) {
                $("#toPayForm").attr("action", $www + "/alipayBalance/index.html");
            } else if ($("#Tenpay").attr("checked") == true) {
                $("#toPayForm").attr("action", $www + "/balance/goBalance.html");
            } else if ($("#Yeepay").attr("checked") == true) {
                $("#toPayForm").attr("action", $www + "/yeepayBalance/index.html");
            } else {
                $("#toPayForm").attr("action", $www + "/balance/goBalance.html");
            }
            var K = G.html();
            if (isNaN(parseInt(K)) || K == "0") {
                $.PageDialog("<dl class=\"sAltFail\"><dd>请选择或输入充值金额!</dd></dl>", {W: 218, H: 50, autoClose: true});
                I.focus();
                return false;
            }
            $("input[name=account]").each(function (L, M) {
                if ($(M).attr("checked") == true) {
                    F = true;
                    H = $(this).val();
                }
            });
            if (F == false) {
                $.PageDialog("<dl class=\"sAltFail\"><dd>请选择支付方式!</dd></dl>", {W: 180, H: 50, autoClose: true});
                return false;
            }
            E(G.html());
            return H != "";
        });
    };
    B();
});

