$(document).ready(function () {
    var r = "http://skin.1ypg.com";

    function SetCookie(name, value) //存cookie
    {
        document.cookie = name + "=" + escape(value) + ";id=1ypg;path=/;domain=" + $domain;
    }

    function getCookie(name)//取cookies函数        
    {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null) return unescape(arr[2]);
        return null;
    }

    SetCookie("isCookie", "yes");
    var m = getCookie("isCookie");
    if (m == null || m != "yes") {
        alert("\u6e29\u99a8\u63d0\u793a\uff1a\u60a8\u7684\u6d4f\u89c8\u5668\u5f53\u524d\u4e0d\u652f\u6301Cookies\u529f\u80fd\uff0c\u8bf7\u60a8\u542f\u7528\u6d4f\u89c8\u5668Cookie\u529f\u80fd\u6216\u66f4\u6362\u6d4f\u89c8\u5668\u3002");
        window.location.href = $www + "/help/openCookie.html";
        return;
    }
    var b = function (w) {
        var x = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        return x.test(w);
    };
    var q = function (x) {
        var w = /^[\S]{6,20}$/;
        return w.test(x);
    };
    var c = function (x) {
        var w = /^1\d{10}$/;
        return w.test(x);
    };
    var h = function (x) {
        var w = /^[a-zA-Z0-9]{3,6}$/;
        return w.test(x);
    };
    var j = function (w) {
        return "<p class=\"tips_txt\">" + w + "</p>";
    };
    var l = function (w) {
        return "<p class=\"tips_txt_Wrong\"><s></s>" + w + "</p>";
    };
    var a = "<p class=\"tips_txt_yes\"><s></s></p>";
    var v = function (w, x) {
        return "<div class=\"Pas_tips_StrWeak\"><p class=\"password_tips0" + w + "\"><span><em></em></span><i>" + (w == 3 ? "\u5f3a" : w == 2 ? "\u4e2d" : "\u5f31") + "</i></p><b>" + x + "</b></div>";
    };
    var n = {
        userAccount: {
            normal: j("邮件是收到获得商品信息的重要途径"),
            empty: l("请输入邮箱地址"),
            ishad: l("已被注册，换一个吧"),
            error: l("邮箱地址不正确"),
            ismany: l("注册帐号过于频繁，请稍后再试"),
            ok: a
        },
        password: {
            normal: j("密码由6～20位半角字符（字母、数字、符号）组成，区分大小写"),
            empty: l("密码长度不正确，应为6～20个不含空格的字符"),
            error: l("密码长度不正确，应为6～20个不含空格的字符"),
            ok1: v(1, "密码太弱啦，试试数字和字母的组合吧！"),
            ok2: v(2, "复杂度还行，再加强一下？"),
            ok3: v(3, "密码强度很好，请牢记！")
        },
        passwordAgain: {normal: j("请再次输入密码"), empty: l("请再次输入密码"), error: l("两次输入的密码不一致，请重新输入"), ok: a},
        regSN: {normal: j("请输入图片中的验证码"), empty: l("请输入图片中的验证码"), error: l("验证码不正确，请重新输入"), ok: a}
    };
    var s = "";
    var e = "";
    var p = "";
    var f = "";
    var u = "";
    var t = 0;
    var o = 0;
    var i = 0;
    var d = 2;
    var g = true;
    var k = function () {
        var w = $("#btnSubmitRegister");
        var A = $("#showVerifySN");
        var D = function (F) {
            if (!F) {
                return;
            }
            $("#" + F).attr("class", "login_input_textCur").parent().next().html(n[F].normal);
        };
        var y = function (H) {
            var K = $("#" + H);
            if (K.length == 0) {
                return;
            }
            var G = K.val();
            var I = K.parent().next();
            var F = "";
            switch (H) {
                case "userAccount":
                    if (G == "") {
                        s = "";
                        F = n[H].empty;
                        t = 0;
                    } else {
                        if (!(b(G))) {
                            F = n[H].error;
                            s = G;
                            t = 1;
                        } else {
                            if (G != s) {
                                s = G;
                                I = null;
                                x(G, H);
                            } else {
                                if (t == 2) {
                                    F = n[H].ishad;
                                } else {
                                    if (t == 3) {
                                        F = n[H].ok;
                                    } else {
                                        if (t == 4) {
                                            F = n[H].ismany;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "password":
                    if (G == "") {
                        F = n[H].empty;
                        e = G;
                        o = 0;
                    } else {
                        if (!q(G)) {
                            F = n[H].error;
                            e = G;
                            o = 1;
                        } else {
                            var J = function (L) {
                                var M = 0;
                                if (L.match(/[a-z]/ig)) {
                                    M++;
                                }
                                if (L.match(/[0-9]/ig)) {
                                    M++;
                                }
                                if (L.match(/(.[^a-z0-9])/ig)) {
                                    M++;
                                }
                                if (M == 3 && L.length < 8) {
                                    M--;
                                }
                                return M;
                            };
                            F = n[H]["ok" + J(G)];
                            e = G;
                            o = 2;
                            if (p != "") {
                                y("passwordAgain");
                            }
                        }
                    }
                    break;
                case "passwordAgain":
                    if (G == "") {
                        F = e == "" ? "" : n[H].empty;
                        p = "";
                        i = 0;
                    } else {
                        if (G != e) {
                            F = n[H].error;
                            p = G;
                            i = 1;
                        } else {
                            if (!q(G)) {
                                F = "";
                                p = G;
                                i = 1;
                            } else {
                                F = n[H].ok;
                                p = G;
                                i = 2;
                            }
                        }
                    }
                    break;
                case "regSN":
                    if (G == "") {
                        u = "";
                        F = n[H].empty;
                        d = 0;
                    } else {
                        if (!h(G)) {
                            F = n[H].error;
                            u = G;
                            d = 1;
                        } else {
                            u = G;
                            d = 2;
                        }
                    }
                    break;
                default:
                    break;
            }
            if (I) {
                I.html(F);
            }
            K.attr("class", "login_input_text");
        };

        var E = function () {
            if (!isLoaded) {
                return;
            }
            var F = null;
//			alert("s:"+s+"  e:"+e+"  f:"+f+"  u:"+u);
            $.ajax({
                url: "/register/regsiter.html",
                type: "post",
                data: "str=" + s + "&userPwd=" + e,
                beforeSend: loadingReg,
                success: function (data) {
                    data = eval('(' + data + ')');
                    if (data != "false") {
                        var J = escape($("#hidRegisterForward").val());
                        if (c(s)) {
                            location.replace("/register/mobilecheck.html?str=" + escape(s) + "&forward=" + J);
                        } else {
                            location.replace("/register/emailcheck.html?str=" + escape(s) + "&forward=" + J);
                        }
// 						if(data.userId){
//	 						SetCookieByExpires("userId",data.userId,1);
// 						}
// 						if(data.phone){
//	 						SetCookieByExpires("phone",data.phone,1);
// 						}
// 						if(data.mail){
// 							SetCookieByExpires("mail",data.mail,1);
// 						}
// 						SetCookieByExpires("loginType",0,1);
// 						if(forward=="rego"){
//	 						window.location.href=$www;
// 						}else if(forward=="auth"){
// 							window.location.href=$www+"/referAuth.html";
// 						}else{
// 							window.location.href=$www;
// 						}
                    } else {
                        alert("\u6CE8\u518C\u5931\u8D25\uFF0C\u8BF7\u7A0D\u540E\u518D\u8BD5\!");
                        window.location.href = $www;
                    }
                    isLoaded = true;
                },
                error: function () {
                    w.html("同意以下协议，提交").attr("class", "Mem_orangebut");
//					var I = s
                    var K = "测试失败，请稍后再试！";
//					if (I == -3) {
//					} else {
//						if (I == 10) {
//							$("#regSN").parent().next().html(n.regSN.error);
//							return;
//						} else {
//							if (I == -1) {
//								K = ":注册次数过于频繁,请稍后再试！";
//							} else {
//								if (I == -2) {
//									K = ":该邮箱已注册，请直接登录！";
//								} else {
//									if (I == 2 && c(s)) {
//										K = ":提交过于频繁，请稍后再提交！";
//									}
//								}
//							}
//						}
//					}
// 					window.location.href=$www;
                }
            });

            function loadingReg() {
                isLoaded = false;
                w.html("正在提交，请稍后").attr("class", "login_Email_butClick");
            };
//			F = new JQAjax("/DataServer/SaveRegsiter");
//			F.OnStart(function () {
//				isLoaded = false;
//				w.html("正在提交，请稍后").attr("class", "login_Email_butClick");
//			});
//			F.OnStop(function () {
//				isLoaded = true;
//			});
//			F.OnSuccess(function (H) {
//				if (H != null && H.d.Code == 0) {
//					var J = escape($("#hidRegisterForward").val());
//					if (c(s)) {
//						location.replace("mobilecheck.html?str=" + escape(H.d.Str) + "&forward=" + J);
//					} else {
//						location.replace("emailcheck.html?str=" + escape(H.d.Str) + "&forward=" + J);
//					}
//				} else {
//					w.html("同意以下协议，提交").attr("class", "Mem_orangebut");
//					var I = parseInt(H.d.Num);
//					var K = "";
//					if (I == -3) {
//					} else {
//						if (I == 10) {
//							$("#regSN").parent().next().html(n.regSN.error);
//							return;
//						} else {
//							if (I == -1) {
//								K = ":注册次数过于频繁,请稍后再试！";
//							} else {
//								if (I == -2) {
//									K = ":该邮箱已注册，请直接登录！";
//								} else {
//									if (I == 2 && c(s)) {
//										K = ":提交过于频繁，请稍后再提交！";
//									}
//								}
//							}
//						}
//					}
//					FailDialog("注册失败" + K, 320);
//				}
//			});
//			var G = {account:s, pwd:e, check:f, sn:u};
//			F.OnSend($.toJSON(G), "json", true);
        };

//		var C = new JQAjax("/DataServer/CheckName");
        var x = function (I, G) {
            if (!isLoaded) {
                return;
            }
            var J = $("#" + G);
            var H = J.parent().next();
            var F = getCookie("_regnum");
            if (F != null && parseInt(F) >= 3) {
                t = 4;
                H.html(n[G].ismany);
                return;
            }
            $.ajax({
                url: "/register/isExists.html?userName=" + I,
                type: "post",
                data: "String",
                beforeSend: loadingIsName,
                success: function (data) {
                    if (data == "true") {
//						f = K.d.Str;
                        t = 3;
                        H.html(n[G].ok);
                    } else {
                        H.html(n[G].ishad);
                        t = 2;
                    }
                    isLoaded = true;
                    window.defaultStatus = "";
                    w.bind("click", B);
                },
                error: function () {
                    alert("\u8FDE\u63A5\u670D\u52A1\u5668\u5931\u8D25\uFF0C\u8BF7\u7A0D\u540E\u518D\u8BD5\uFF01");
                    window.location.href = $www;
                }
            });

            function loadingIsName() {
                isLoaded = false;
                window.defaultStatus = "正在检验数据，请稍后...";
                w.unbind("click");
                H.html("<p class=\"tips_txt_loding\"><i><img src=\"http://img.1ypg.com/Images/loding.gif\" border=0 alt=\"\"></i></p>");
            };
//			C.OnStart(function () {
//				isLoaded = false;
//				window.defaultStatus = "正在检验数据，请稍后...";
//				w.unbind("click");
//				H.html("<p class=\"tips_txt_loding\"><i><img src=\"http://skin.1yyg.com/Passport/images/loding.gif\" border=0 alt=\"\"></i></p>");
//			});
//			C.OnStop(function () {
//				isLoaded = true;
//				window.defaultStatus = "";
//				w.bind("click", B);
//			});
//			C.OnSuccess(function (K) {
//				if (K != null && K.d.Code == 1) {
//					H.html(n[G].ishad);
//					t = 2;
//				} else {
//					if (K != null && K.d.Code == 0) {
//						f = K.d.Str;
//						t = 3;
//						H.html(n[G].ok);
//					} else {
//						return;
//					}
//				}
//			});
//			C.OnSend("{'name':'" + I + "'}", "json", false);
        };
        var B = function () {
            if (!isLoaded) {
                return;
            }
            g = true;
            if (t != 3) {
                y("userAccount");
                g = false;
            }
            if (o != 2) {
                y("password");
                g = false;
            }
            if (i != 2) {
                y("passwordAgain");
                g = false;
            }
            if (d != 2) {
                y("regSN");
                g = false;
            }
            if (g) {
                E();
            }
        };

        var z = function () {
            $("input[name*='txt']").each(function () {
                $(this).bind("focus", function () {
                    D(this.id);
                }).bind("blur", function () {
                    y(this.id);
                }).bind("keydown", function (G) {
                    var F = (window.event) ? event.keyCode : G.keyCode;
                    if (F == 32) {
                        return false;
                    } else {
                        if (F == 13) {
                            this.blur();
                            if (this.id == "passwordAgain") {
                                w.click();
                            } else {
                                $("#" + this.id).parent().parent().next().find("input[name*='txt']").focus();
                            }
                            return false;
                        }
                    }
                    return true;
                }).attr("disabled", false);
            });
            w.bind("click", B);
            $("#userAccount").focus();
        };
        z();
        isLoaded = true;
    };
    k();
//	Base.getScript(r + "/JS/AjaxFun.js?date=20130123", k);
});

