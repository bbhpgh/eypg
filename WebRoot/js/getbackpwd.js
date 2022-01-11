$(document).ready(function () {
    var a = $skin;
    var e = function (f) {
        var g = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        return g.test(f);
    };
    var b = function (g) {
        var f = /^1\d{10}$/;
        return f.test(g);
    };
    var d = function (g) {
        var f = /^[a-zA-Z0-9]{3,6}$/;
        return f.test(g);
    };
    var c = function () {
        var o = $("#btnSubmitAccount");
        var u = $("#userAccount");
        var r = $("#regSN");
        var q = $("#showVerifySN");
        var k = "";
        var s = {
            empty: "请输入邮箱地址！",
            avible: "该邮箱地址未注册！",
            error: "邮箱地址不正确！",
            many: "该帐号请求取回密码过于频繁，请稍后再试！",
            mailFail: "发送取回密码邮件失败，请稍后再试！"
        };
        var g = {empty: "请输入图片中的验证码！", error: "验证码不正确，请重新输入！"};
        var w = function (x) {
            u.parent().next().html("<span class=\"tips_txt_Wrong\"><s></s>" + x + "</span>");
        };
        var t = function (x) {
            r.parent().next().html("<span class=\"tips_txt_Wrong\"><s></s>" + x + "</span>");
        };
        var h = function () {
            if (!isLoaded) {
                return;
            }
//			var x = new JQAjax("/DataServer/SendFindSMS");
//			x.OnSuccess(function (y) {
//				if (y != null && y.d.Code == 0) {
//					location.href = "findmobilecheck.html?str=" + escape(y.d.Str);
//				} else {
//					w(s.many);
//					o.html("\u63d0\u4ea4").attr("class", "login_Email_but");
//				}
//			});
//			x.OnSend("{'mobile':'" + u.val() + "','sn':'" + k + "'}", "json", true);
        };
        var j = function () {
            if (!isLoaded) {
                return;
            }
            location.href = "/getbackpwd/findemailcheck.html?mail=" + u.val() + "&rnd=" + r.val();
//			var x = new JQAjax("/DataServer/EmailGetBackPwd");
//			x.OnSuccess(function (y) {
//				if (y != null && y.d.Code == 0) {
//					location.href = "findemailcheck.html?str=" + escape(y.d.Str);
//				} else {
//					w(s.mailFail);
//					o.html("\u63d0\u4ea4").attr("class", "login_Email_but");
//				}
//			});
//			x.OnSend("{'email':'" + u.val() + "','sn':'" + k + "'}", "json", true);
        };
        var m = function () {
            if (!isLoaded) {
                return;
            }
            var A = u.val();
            var z = r.val();
            $.ajax({
                url: "/getbackpwd/getBackPwd.html?mail=" + A + "&rnd=" + z,
                data: "string",
                type: "get",
                beforeSend: loadingBack,
                success: function (data) {
                    if (data != null && data == 1) {
                        isLoaded = true;
                        if (b(A)) {
                            h();
                        } else {
                            j();
                        }
                    } else {
                        if (data != null && data == 3) {
                            t(g.error);
                            q.parent().click();
                            o.html("\u63d0\u4ea4").attr("class", "login_Email_but");
                        } else {
                            w(s.avible);
                            o.html("\u63d0\u4ea4").attr("class", "login_Email_but");
                        }
                        isLoaded = true;
                    }
                },
                error: function () {
                    w(s.mailFail);
                    o.html("\u63d0\u4ea4").attr("class", "login_Email_but");
                }
            });

            function loadingBack() {
                isLoaded = false;
                o.html("正在验证，请稍后").attr("class", "login_Email_butClick");
            };
        };
        var p = function () {
            var z = true;
            var y = u.val();
            var x = r.val();
            if (x == "") {
                z = false;
                t(g.empty);
            } else {
                if (!d(x)) {
                    z = false;
                    t(g.error);
                }
            }
            if (y == "") {
                z = false;
                w(s.empty);
            } else {
                if (e(y)) {
                } else {
                    z = false;
                    w(s.error);
                }
            }
            if (z) {
                m();
            }
        };
        var v = function () {
            u.parent().next().html("");
        };
        var i = function () {
            var x = u.val();
            if (x == "") {
                w(s.empty);
            } else {
                if (!e(x)) {
                    w(s.error);
                }
            }
        };
        var f = function () {
            r.parent().next().html("");
        };
        var l = function () {
            var x = r.val();
            if (x == "") {
                t(g.empty);
            } else {
                if (!d(x)) {
                    t(g.error);
                }
            }
        };
        var n = function () {
            o.bind("click", p);
            q.parent().click(function () {
                q.attr("src", $www + "/getbackpwd/getRandomCode.html?rnd=" + new Date().getTime());
            });
            r.bind("focus", f).bind("blur", l).attr("disabled", false);
            u.bind("focus", v).bind("blur", i).bind("keydown", function (y) {
                var x = (window.event) ? event.keyCode : y.keyCode;
                if (x == 32) {
                    return false;
                } else {
                    if (x == 13) {
                        p();
                        return false;
                    }
                }
                return true;
            }).attr("disabled", false).focus();
        };
        isLoaded = true;
        n();
    };
    c();
});

