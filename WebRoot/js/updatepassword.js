$(function () {
    var a = $skin;
    var b = function () {
        var e = ["\u539f\u59cb\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a", "\u5bc6\u7801\u4e0d\u8db36\u4f4d", "\u539f\u59cb\u5bc6\u7801\u9519\u8bef,\u8bf7\u91cd\u65b0\u8f93\u5165"];
        var i = ["\u65b0\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a", "\u5bc6\u7801\u4e0d\u8db36\u4f4d", "\u65b0\u5bc6\u7801\u9519\u8bef,\u8bf7\u91cd\u65b0\u8f93\u5165"];
        var d = ["\u786e\u8ba4\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a", "\u4e24\u6b21\u5bc6\u7801\u8f93\u5165\u4e0d\u4e00\u81f4,\u8bf7\u91cd\u65b0\u8f93\u5165"];
        var p = "";
        var c = "";
        var l = "";
        var o = "";
        var v = 0;
        var t = 0;
        var w = 0;
        var j = false;
        var k = true;
        var s = false;
        var userId = $("#userId").val();
        var n = function (x, y) {
            if (!s) {
                x.focus();
            }
            x.parent().next().addClass("wrong").html("<s></s>" + y);
        };
        var u = function (x) {
            if (!x) {
                return;
            }
            s = true;
            p = x;
        };
        var q = function (y) {
            var x = /^[^ \f\n\r\t\v]{6,20}$/;
            if (!x.exec(y)) {
                return false;
            }
            return true;
        };
        var g = function (A) {
            if (!A) {
                return;
            }
            var z = getTrimVal(A);
            var B = $("#l" + A);
            var x = "";
            var y = "";
            s = false;
            switch (A) {
                case "OldPass":
                    v = 2;
                    if (z == "") {
                        c = "";
                        x = "wrong";
                        y = e[0];
                        v = 0;
                        j = false;
                    } else {
                        if (!q(z)) {
                            x = "wrong";
                            y = e[1];
                            c = z;
                            v = 1;
                            _IsNameInit = 0;
                            j = false;
                        } else {
                            x = "ok";
                            y = "&nbsp;";
                            c = z;
                            v = 2;
                            _IsNameInit = 0;
                            j = true;
                        }
                    }
                    break;
                case "NewPass":
                    t = 2;
                    if (z == "" && o != "") {
                        x = "wrong";
                        y = i[0];
                        l = z;
                        t = 0;
                        _IsNameInit = 0;
                        j = false;
                    } else {
                        if (z == "") {
                            x = "wrong";
                            y = i[0];
                            l = z;
                            t = 0;
                            _IsNameInit = 0;
                            j = false;
                        } else {
                            if (!q(z)) {
                                x = "wrong";
                                y = i[1];
                                l = z;
                                t = 1;
                                _IsNameInit = 0;
                                j = false;
                            } else {
                                if (o != "" && z != o) {
                                    x = "wrong";
                                    y = d[1];
                                    l = z;
                                    w = 1;
                                    _IsNameInit = 0;
                                    p = "NewPassAgain";
                                    j = false;
                                    B.attr("class", "ok").html("<s></s>");
                                    B = $("#lNewPassAgain");
                                } else {
                                    if (o != "" && z == o) {
                                        x = "ok";
                                        y = "&nbsp;";
                                        l = z;
                                        w = 2;
                                        _IsNameInit = 0;
                                        j = true;
                                        B.attr("class", "ok").html("<s></s>");
                                        B = $("#lNewPassAgain");
                                    } else {
                                        x = "ok";
                                        y = "&nbsp;";
                                        l = z;
                                        t = 2;
                                        _IsNameInit = 0;
                                        j = true;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "NewPassAgain":
                    w = 2;
                    if (z == "") {
                        x = "wrong";
                        y = d[0];
                        w = 0;
                        o = "";
                        _IsNameInit = 0;
                        j = false;
                    } else {
                        if (l == "") {
                            x = "wrong";
                            y = i[0];
                            o = z;
                            w = 0;
                            _IsNameInit = 0;
                            p = "NewPass";
                            j = false;
                            B = $("#lNewPass");
                        } else {
                            if (z != l) {
                                x = "wrong";
                                y = d[1];
                                w = 1;
                                o = z;
                                _IsNameInit = 0;
                                j = false;
                            } else {
                                x = "ok";
                                y = "&nbsp;";
                                o = z;
                                w = 2;
                                _IsNameInit = 0;
                                j = true;
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
            if (y != "") {
                if (x == "ok") {
                    B.attr("class", "ok").html("<s></s>");
                } else {
                    B.attr("class", x).html("<s></s>" + y);
                }
            }
            _IsNameInit = 1;
        };
        var f = function () {
            $.PageDialog("<dl class=\"sAltOK\"><dd>修改成功!</dd></dl>", {W: 160, H: 50, autoClose: true});
            location.replace("/user/UpdatePassWord.html");
        };
        var r = function () {
            if (!isLoaded) {
                return;
            }
            $.ajax({
                url: "/user/updatePwd.html",
                data: "userId=" + userId + "&id=" + c + "&userJSON=" + l,
                success: function (data) {
                    if (data == "success") {
                        f();
                    } else {
                        $("#btnSubmitSave").show();
                        $("#SaveSubmitStatus").hide();
                        $("#OldPass").focus();
                        $("#lOldPass").attr("class", "wrong").html("<s></s>" + e[2]);
                    }
                },
                error: function () {
                    alert("修改失败，请与管理员联系！");
                }
            });
        };
        var m = function () {
            if (!isLoaded) {
                return;
            }
            k = true;
            var x = $("#OldPass");
            if (v == 0) {
                n(x, e[0]);
                k = false;
            }
            if (v == 1) {
                n(x, e[1]);
                k = false;
            }
            var z = $("#NewPass");
            if (t == 0) {
                n(z, i[0]);
                k = false;
            }
            if (t == 1) {
                n(z, i[1]);
                k = false;
            }
            var y = $("#NewPassAgain");
            if (w == 0) {
                n(y, d[0]);
                k = false;
            }
            if (w == 1 || y.val() != z.val()) {
                n(y, d[1]);
                k = false;
            }
            if (k == true) {
                r();
            }
        };
        $("input[type='password']").bind("focus", function () {
            u(this.id);
        }).bind("blur", function () {
            g(this.id);
        }).removeAttr("disabled");
        var h = $("#NewPass");
        h.bind("keyup", function (A) {
            var y = $("#pwdStrength");
            var C = $("#tdStrength");
            var E = ["gray45", "red", "yellow", "green"];
            var D = ["", "\u5f31", "\u4e2d", "\u5f3a"];
            var x = function (G) {
                var H = "";
                for (var F = 1; F <= 3; F++) {
                    if (F == G) {
                        H += "<li class=\"" + E[G] + "\"></li>";
                    } else {
                        H += "<li class=\"" + E[0] + "\"></li>";
                    }
                }
                y.html(H);
                C.html("<span>" + D[G] + "</span>");
            };
            var B = $("#lNewPass");
            var z = function (G) {
                if (G.length >= 6) {
                    B.attr("class", "ok").html("<s></s>");
                    if (/[a-zA-Z]+/.test(G) && /[0-9]+/.test(G) && /\W+\D+/.test(G)) {
                        x(3);
                    } else {
                        if (/[a-zA-Z]+/.test(G) && /[0-9]+/.test(G)) {
                            x(2);
                        } else {
                            if (/\[a-zA-Z]+/.test(G) && /\W+\D+/.test(G)) {
                                x(2);
                            } else {
                                if (/[0-9]+/.test(G) && /\W+\D+/.test(G)) {
                                    x(2);
                                } else {
                                    x(1);
                                }
                            }
                        }
                    }
                } else {
                    var F = G.length == 0 ? i[0] : i[1];
                    B.attr("class", "wrong").html("<s></s>" + F);
                    x(0);
                }
            };
            z(h.val());
        });
        $("#btnSubmitSave").bind("click", function () {
            m();
        });
        $("input[type='password']").bind("keydown", function (y) {
            var x = (window.event) ? event.keyCode : y.keyCode;
            if (x == 32) {
                return false;
            } else {
                if (x == 13) {
                    g(p);
                    if (j) {
                        if (p == "NewPassAgain") {
                            $("#btnSubmitSave").click();
                        } else {
                            $(p).parent().parent().next().next().find("input[type='password']").focus();
                        }
                    } else {
                        $(p).focus();
                    }
                    return false;
                }
            }
            return true;
        });
        isLoaded = true;
    };

    function getTrimVal(a) {
        return $("#" + a).val();
    }

    b();
});

