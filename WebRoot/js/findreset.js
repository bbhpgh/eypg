$(document).ready(function () {
    var m = "http://skin.1ypg.com";
    var f = function (o) {
        var n = /^[\S]{6,20}$/;
        return n.test(o);
    };
    var i = function (n) {
        return "<p class=\"tips_txt\">" + n + "</p>";
    };
    var g = function (n) {
        return "<p class=\"tips_txt_Wrong\"><s></s>" + n + "</p>";
    };
    var b = "<p class=\"tips_txt_yes\"><s></s></p>";
    var j = function (n) {
        return "<div class=\"Pas_tips_StrWeak\"><p class=\"password_tips0" + n + "\"><span><em></em></span><i>" + (n == 3 ? "\u5f3a" : n == 2 ? "\u4e2d" : "\u5f31") + "</i></p><b>\u76f8\u540c\u5b57\u7b26\u5bc6\u7801\u6613\u88ab\u7834\u89e3\uff0c\u8bf7\u7528\u591a\u7ec4\u5408\u7684\u5bc6\u7801</b></div>";
    };
    var a = {
        password: {
            normal: i("\u5bc6\u7801\u75316\uff5e20\u4f4d\u534a\u89d2\u5b57\u7b26\uff08\u5b57\u6bcd\u3001\u6570\u5b57\u3001\u7b26\u53f7\uff09\u7ec4\u6210\uff0c\u533a\u5206\u5927\u5c0f\u5199"),
            empty: g("\u5bc6\u7801\u957f\u5ea6\u4e0d\u6b63\u786e\uff0c\u5e94\u4e3a6\uff5e20\u4e2a\u4e0d\u542b\u7a7a\u683c\u7684\u5b57\u7b26"),
            error: g("\u5bc6\u7801\u957f\u5ea6\u4e0d\u6b63\u786e\uff0c\u5e94\u4e3a6\uff5e20\u4e2a\u4e0d\u542b\u7a7a\u683c\u7684\u5b57\u7b26"),
            ok1: j(1),
            ok2: j(2),
            ok3: j(3)
        },
        passwordAgain: {
            normal: i("\u8bf7\u518d\u6b21\u8f93\u5165\u5bc6\u7801"),
            empty: g("\u8bf7\u518d\u6b21\u8f93\u5165\u5bc6\u7801"),
            error: g("\u4e24\u6b21\u8f93\u5165\u7684\u5bc6\u7801\u4e0d\u4e00\u81f4\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165"),
            ok: b
        }
    };
    var d = "";
    var e = "";
    var l = 0;
    var k = 0;
    var h = true;
    var c = function () {
        var n = $("#btnSubmitPassword");
        var mail = $("#hidUserName").val();
        var p = function (t) {
            if (!t) {
                return;
            }
            $("#" + t).attr("class", "login_input_textCur").parent().next().html(a[t].normal);
        };
        var r = function (v) {
            var y = $("#" + v);
            if (y.length == 0) {
                return;
            }
            var u = y.val();
            var w = y.parent().next();
            var t = "";
            switch (v) {
                case "password":
                    if (u == "") {
                        t = a[v].empty;
                        d = u;
                        l = 0;
                    } else {
                        if (!f(u)) {
                            t = a[v].error;
                            d = u;
                            l = 1;
                        } else {
                            var x = function (z) {
                                var A = 0;
                                if (z.match(/[a-z]/ig)) {
                                    A++;
                                }
                                if (z.match(/[0-9]/ig)) {
                                    A++;
                                }
                                if (z.match(/(.[^a-z0-9])/ig)) {
                                    A++;
                                }
                                if (A == 3 && z.length < 8) {
                                    A--;
                                }
                                return A;
                            };
                            t = a[v]["ok" + x(u)];
                            d = u;
                            l = 2;
                            if (e != "") {
                                r("passwordAgain");
                            }
                        }
                    }
                    break;
                case "passwordAgain":
                    if (u == "") {
                        t = d == "" ? "" : a[v].empty;
                        e = "";
                        k = 0;
                    } else {
                        if (u != d) {
                            t = a[v].error;
                            e = u;
                            k = 1;
                        } else {
                            if (!f(u)) {
                                t = "";
                                e = u;
                                k = 1;
                            } else {
                                t = a[v].ok;
                                e = u;
                                k = 2;
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
            if (w) {
                w.html(t);
            }
            y.attr("class", "login_input_text");
        };
        var o = function () {
            if (!isLoaded) {
                return;
            }
            $.ajax({
                url: "/getbackpwd/updatePwd.html",
                data: "mail=" + mail + "&newPwd=" + d,
                type: "post",
                beforeSend: OnStart,
                success: function (v) {
                    if (v != null && v == 0) {
                        location.replace("/getbackpwd/findok.html");
                    } else {
                        alert("设置新密码失败！");
                        n.html("\u786e\u8ba4\u4fee\u6539").attr("class", "login_Email_but");
                    }
                },
                error: function () {
                    alert("设置新密码失败！请稍后再试！");
                }
            });
//			var t = null;
//			t = new JQAjax("/DataServer/SetUserPwd");
//			t.OnStart(function () {
//				isLoaded = false;
//				n.html("\u6b63\u5728\u63d0\u4ea4\uff0c\u8bf7\u7a0d\u540e").attr("class", "login_Email_butClick");
//			});
//			t.OnStop(function () {
//				isLoaded = true;
//			});
//			t.OnSuccess(function (v) {
//				if (v != null && v.d.Code == 0) {
//					location.replace("findok.html");
//				} else {
//					FailDialog("\u8bbe\u7f6e\u65b0\u5bc6\u7801\u5931\u8d25\uff01");
//					n.html("\u786e\u8ba4\u4fee\u6539").attr("class", "login_Email_but");
//				}
//			});
//			var u = {userName:$("#hidUserName").val(), pwd:d, key:$("#hidKey").val()};
//			t.OnSend($.toJSON(u), "json", true);
            function OnStart() {
                isLoaded = false;
                n.html("\u6b63\u5728\u63d0\u4ea4\uff0c\u8bf7\u7a0d\u540e").attr("class", "login_Email_butClick");
            }
        };
        var q = function () {
            if (!isLoaded) {
                return;
            }
            h = true;
            if (l != 2) {
                r("password");
                h = false;
            }
            if (k != 2) {
                r("passwordAgain");
                h = false;
            }
            if (h == true) {
                o();
            }
        };
        var s = function () {
            $("input[name*='txt']").each(function () {
                $(this).bind("focus", function () {
                    p(this.id);
                }).bind("blur", function () {
                    r(this.id);
                }).bind("keydown", function (u) {
                    var t = (window.event) ? event.keyCode : u.keyCode;
                    if (t == 32) {
                        return false;
                    } else {
                        if (t == 13) {
                            this.blur();
                            if (this.id == "passwordAgain") {
                                n.click();
                            } else {
                                $("#" + this.id).parent().parent().next().find("input[name*='txt']").focus();
                            }
                            return false;
                        }
                    }
                    return true;
                }).attr("disabled", false);
            });
            n.bind("click", q);
            $("#password").focus();
        };
        s();
        isLoaded = true;
    };
    c();
});

