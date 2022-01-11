$(document).ready(function () {
    var a = $skin;
    var f = $("#retrySend");
    var h = $("#hidRegEmail").val();
    var e = $("#hidRegSN").val();
    var c = $("#divError");
    var d = $("#divNormal");
    var i = function () {
        c.html("").hide();
        d.show();
    };
    var j = function (k) {
        d.hide();
        c.html("<span class=\"orange Fb\">" + k + "</span>").show();
    };
    var g = function () {
        if (!isLoaded) {
            return;
        }
        $.ajax({
            url: "/getbackpwd/sendFindPwdMail.html?mail=" + h + "&rnd=" + e,
            data: "string",
            type: "post",
            beforeSend: OnStart,
            success: function (data) {
                if (data != null && data == 0) {
                    i();
                } else {
                    if (data != null && data == 2) {
                        j("操作超时，请重新取回密码！");
                    } else {
                        j("验证邮件已发送至邮箱，请前往收信，完成验证！");
                    }
                }
                isLoaded = true;
                f.attr("class", "login_SendoutbutClick");
            },
            error: function () {
                j("验证邮件发送失败，请重试！");
            }
        });

        function OnStart() {
            isLoaded = false;
            j("系统正在发送验证邮件，请稍后……");
            f.attr("class", "login_Sendoutbut");
        }
    };
    f.bind("click", g);
    isLoaded = true;
    g();
});

