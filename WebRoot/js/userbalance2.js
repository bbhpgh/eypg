$(function () {
    var F = "";
    var H = $("#submit_ok");
    var E = $("#Money");
    var G = $("#otherMoney");
    var A = function () {
        var content = "<div id=\"payAltBox\"><div class=\"payment_ts\"><p><span></span>冲值完成之前请不要关闭此窗口</p><p class=\"bottom\">完成冲值后跟据您的情况进行以下操作</p><ul><li>冲值成功：</li><li><a href=\"" + $www + "/user/RechargeList.html\">查看冲值记录&gt;&gt;</a></li><li class=\"title\">冲值失败：</li><li><a id=\"btnReSelect\" href=\"javascript:;\">返回重新选择>></a></li></ul></div></div>";
        $.PageDialog(content, {W: 405, H: 180, title: "冲值提醒"});
    };
    $("#btnReSelect").live("click", function () {
        $("#pageDialog").hide();
        $("#pageDialogBG").hide();
        $("#pageDialogBorder").hide();
    });
    var C = function (I) {
        if (/^\d{4}$/.test(F)) {
            $("#hidPayName").val("Tenpay");
            $("#hidPayBank").val(F);
        } else {
            $("#hidPayName").val(F);
            $("#hidPayBank").val("0");
        }
        $("#hidMoney").val(I);
        A();
    };
    $("#ten").click(function () {
        E.html("10");
        G.val("");
    });
    $("#fifty").click(function () {
        E.html("50");
        G.val("");
    });
    $("#hundred").click(function () {
        E.html("100");
        G.val("");
    });
    $("#twohundred").click(function () {
        E.html("200");
        G.val("");
    });
    $("#other").click(function () {
        E.html("0");
    });
    var B = function () {
        var I = G.val();
        if (I == "") {
            E.html(0);
        } else {
            if (isNaN(parseInt(I)) || I == "0") {
                if (E.html() == "0") {
                    G.val("");
                } else {
                    G.val(E.html());
                }
            } else {
                E.html(parseInt(I));
            }
        }
    };
    G.focus(function () {
        $("#other").attr("checked", true);
        B();
    }).bind("keyup", function (J) {
        B();
        var I = (window.event) ? event.keyCode : J.keyCode;
        if (I == 13) {
            H.focus();
        }
    });
    var D = false;
    H.click(function () {
        var I = E.html();
        if (isNaN(parseInt(I)) || I == "0") {
            alert("\u8bf7\u9009\u62e9\u6216\u8f93\u5165\u5145\u503c\u91d1\u989d!");
            return false;
        }
        $("input[name=account]").each(function (J, K) {
            if ($(K).attr("checked") == true) {
                D = true;
                F = $(this).val();
            }
        });
        if (D == false) {
            alert("\u8bf7\u9009\u62e9\u652f\u4ed8\u65b9\u5f0f\uff01");
            return false;
        }
        C(E.html());
        return F != "";
    });
});

