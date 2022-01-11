$(function () {
    var a = $skin;
    var b = $www;
    var c = function () {
        var j = function (M) {
            var L = /^1\d{10}$/;
            return L.test(M);
        };
        var D = function (M) {
            var L = /^(0[0-9]{2,3}\-)+([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
            return L.test(M);
        };
        var t = function (L) {
            if (L.length > 20) {
                return false;
            }
            var M = /^[\w\u4e00-\u9fa5\-]{2,20}$/;
            return M.test(L);
        };
        var m = true;
        var q = $("#txtName");
        var E = $("#txtPhone");
        var phone = $("#phone");
        var u = $("#txtQQ");
        var A = $("#txtSignature");
        var userId = $("#userId").val();
        var s = true;
        var C = {
            oldUserName: $("#hidOldName").val(),
            userName: "",
            userPhone: "",
            mobilePhone: "",
            userSex: 0,
            userBirthDay: "",
            userCons: 0,
            userLiveArea: 0,
            liveAreaName: "",
            userBirthArea: 0,
            birthAreaName: "",
            userQQ: "",
            userMonthIncome: "",
            userSign: "",
            rnd: 0
        };
        var y = function (L, M) {
            s = false;
            L.removeClass("txt").addClass("orangeBor");
            L.nextAll("span").eq(0).attr("class", "orange").html("<s></s>" + M);
        };
        var I = function (L, M) {
            L.removeClass("orangeBor").addClass("txt");
            L.nextAll("span").eq(0).attr("class", "").html((M == undefined ? "" : M));
        };
        var d = function (L) {
            s = false;
            if (L == 0) {
                $("#errmsgArea").attr("class", "orange").html("<s></s>\u8bf7\u9009\u62e9\u6240\u5728\u5e02\u533a").show();
            } else {
                $("#errmsgArea2").attr("class", "orange").html("<s></s>\u8bf7\u9009\u62e9\u6240\u5728\u5e02\u533a").show();
            }
        };
        var r = function (M) {
            this.DataTextField = null;
            this.DataValueField = null;
            this.DataSource = null;
            this.OnItemDataBinding = null;
            this.OnSelectedIndexChanged = null;
            var L = M;
            this.DataBind = function () {
                var P = $("#" + L);
                P.empty();
                if (this.DataSource == null) {
                    return;
                }
                var O = this.DataSource.length;
                if (this.OnSelectedIndexChanged != null) {
                    P.bind("change", this.OnSelectedIndexChanged);
                }
                var Q = null;
                for (var N = 0; N < O; N++) {
                    Q = this.DataSource[N];
                    if (this.OnItemDataBinding != null) {
                        this.OnItemDataBinding(Q);
                    }
                    P.get(0).options.add(new Option(Q[this.DataTextField], Q[this.DataValueField]));
                }
            };
            this.appendFirstOption = function (N, O) {
                $("#" + L).get(0).options.add(new Option(N, O), 0);
            };
            this.show = function () {
                $("#" + L).show();
            };
            this.hide = function () {
                $("#" + L).hide();
            };
            this.getValue = function () {
                return $("#" + L).val();
            };
            this.setValue = function (N) {
                $("#" + L).val(N);
            };
            this.clear = function () {
                $("#" + L).empty();
            };
            this.getText = function () {
                return $("#" + L).get(0).options[$("#" + L).get(0).selectedIndex].text;
            };
            this.getSelectedIndex = function () {
                return $("#" + L).get(0).selectedIndex;
            };
        };
        var i = function (O) {
            var Q = null;
            var P = null;
            if (O == 0) {
                Q = new r("sel_areaA");
                P = new r("sel_areaB");
            } else {
                Q = new r("sel_areaA2");
                P = new r("sel_areaB2");
            }
            var N = new Object();
            Q.DataTextField = P.DataTextField = "name";
            Q.DataValueField = P.DataValueField = "id";
            var M = function (T, U) {
                var V = {name: "\u8bf7\u9009\u62e9\u3000\u3000", id: 0};
                if (U == 0) {
                    T.DataSource = [V];
                    T.DataBind();
                    return;
                }
                var S = N[U];
                if (S != null) {
                    T.DataSource = S.regions;
                    T.DataBind();
                    return;
                }
//				var R = new JQAjax("/DataServer/GetAreaChildNodes");
//				R.OnStart(function () {
//					m = false;
//				});
//				R.OnStop(function () {
//					m = true;
//				});
//				R.OnSuccess(function (W) {
//					if (W != null && W.d.Code == 0) {
//						eval("W=" + W.d.Str);
//						if (U != 0) {
//							W.regions.unshift(V);
//						}
//						N[U] = W;
//						T.DataSource = W.regions;
//						T.DataBind();
//					}
//				});
//				R.OnSend("{areaID:" + U + "}", "json", false);
            };
            var L = function () {
                var R = Q.getValue();
                M(P, R);
            };
            Q.OnSelectedIndexChanged = L;
            P.OnSelectedIndexChanged = function () {
                var R = P.getValue();
                if (R == "0") {
                    d(O);
                } else {
                    if (O == 0) {
                        $("#errmsgArea").hide();
                    } else {
                        $("#errmsgArea2").hide();
                    }
                }
            };
            this.setValue = function (T, S, R) {
                M(Q, 1);
                Q.setValue(T);
                if (T != 0) {
                    M(P, T);
                } else {
                    M(P, 0);
                }
                if (S != 0) {
                    P.setValue(S);
                }
                return;
            };
            this.getValue = function () {
                var S = Q.getValue();
                var R = P.getValue();
                if (S != "0" && R == "0") {
                    d(O);
                    return null;
                }
                return {areaAID: S, areaBID: R};
            };
            this.getText = function () {
                var S = Q.getValue();
                var R = P.getValue();
                if (S != "0" && R == "0") {
                    d(O);
                    return null;
                } else {
                    return Q.getText() + P.getText();
                }
            };
        };
        var h = new i(0);
        h.setValue($("#hidSelareaA").val(), $("#hidSelareaB").val());
        var e = new i(1);
        e.setValue($("#hidSelareaA2").val(), $("#hidSelareaB2").val());
        var l = $("#sltYear");
        var g = $("#sltMouth");
        var H = $("#sltDay");
        l.bind("change", function () {
            v(l, g, H);
        });
        g.bind("change", function () {
            v(l, g, H);
        });
        var f = null;
        var G = function () {
            f = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            var Q = new Date().getFullYear();
            var L = new Date().getMonth() + 1;
            var O = new Date().getDate();
            var N = $("#hidBirthday").val().split("-");
            for (var M = Q; M > Q - 60; M--) {
                if (M == N[0]) {
                    l.append("<option value='" + M + "' selected>" + M + "</option>");
                } else {
                    l.append("<option value='" + M + "'>" + M + "</option>");
                }
            }
            for (var M = 1; M <= 12; M++) {
                if (M == N[1]) {
                    g.append("<option value='" + M + "' selected>" + M + "</option>");
                } else {
                    g.append("<option value='" + M + "'>" + M + "</option>");
                }
            }
            var P = f[L - 1];
            if (L == 2 && p(l.val())) {
                P++;
            }
            for (var M = 1; M <= P; M++) {
                if (M == N[2]) {
                    H.append("<option value='" + M + "' selected>" + M + "</option>");
                } else {
                    H.append("<option value='" + M + "'>" + M + "</option>");
                }
            }
        };
        var v = function (M, N) {
            var P = M.val();
            var L = N.val();
            var O = f[L - 1];
            if (L == 2 && p(P)) {
                O++;
            }
            w(O);
        };
        var w = function (M) {
            J();
            for (var L = 1; L <= M; L++) {
                H.append("<option value='" + L + "'>" + L + "</option>");
            }
        };
        var J = function () {
            H.find("option").remove();
        };
        var p = function (L) {
            return (L % 4 == 0 || (L % 100 == 0 && L % 400 == 0));
        };
        if (!l.attr("disabled")) {
//			G();
        } else {
            var z = $("#hidBirthday").val().split("-");
            $("#sltYear").append("<option value='" + z[0] + "' selected>" + z[0] + "</option>");
            $("#sltMouth").append("<option value='" + z[1] + "' selected>" + z[1] + "</option>");
            $("#sltDay").append("<option value='" + z[2] + "' selected>" + z[2] + "</option>");
            var n = $("#hidUpdateTime").val().split("-");
            var k = (parseInt(n[0]) + 1);
            var o = k + "\u5e74" + n[1] + "\u6708" + n[2] + "\u65e5\u540e\u624d\u80fd\u518d\u6b21\u7f16\u8f91\u60a8\u7684\u751f\u65e5";
            $("#BirthdayMsg").html(o).show();
        }
        var K = function () {
            var O = ["\u767d\u7f8a\u5ea7", "\u91d1\u725b\u5ea7", "\u53cc\u5b50\u5ea7", "\u5de8\u87f9\u5ea7", "\u72ee\u5b50\u5ea7", "\u5904\u5973\u5ea7", "\u5929\u67b0\u5ea7", "\u5929\u874e\u5ea7", "\u5c04\u624b\u5ea7", "\u6469\u7faf\u5ea7", "\u6c34\u74f6\u5ea7", "\u53cc\u9c7c\u5ea7"];
            var L = $("#selCons");
            L.empty();
            var M = $("#hideCons").val();
            for (var N = 1; N < 13; N++) {
                if (N == M) {
                    L.append("<option value=" + N + " selected>" + O[N - 1] + "</option>");
                } else {
                    L.append("<option value=" + N + ">" + O[N - 1] + "</option>");
                }
            }
        };
        K();
        var F = "昵称长度为2-20个字符，由汉字、字母、数字、“_-”字符组成";
        q.blur(function () {
            var L = q.val();
            if (L == "") {
                y(q, "请输入昵称");
            } else {
                if (!t(L)) {
                    y(q, F, 300);
                } else {
                    if (L != C.oldUserName) {
                        $.ajax({
                            url: "/user/isUserNameExists.html",
                            type: "post",
                            data: "id=" + encodeURIComponent(L) + "&userId=" + userId,
                            success: function (data) {
                                if (data == "true") {
                                    I(q, "");
                                } else {
                                    y(q, "该昵称已被使用，换一个吧");
                                }
                            }
                        });
                    } else {
                        I(q, F);
                    }
                }
            }
        });
        E.blur(function () {
            var L = E.val();
            if (L != "" && (!D(L) && !j(L))) {
                y(E, "请输入正确的电话号码");
            } else {
                I(E);
            }
        });
        phone.blur(function () {
            var L = phone.val();
            if (L == "") {
                y(phone, "请输入手机号！");
            } else {
                if ((!D(L) && !j(L))) {
                    y(phone, "请输入正确的电话号码");
                } else {
                    I(phone);
                }
            }
        });
        u.blur(function () {
            var L = u.val();
            var M = /^[0-9]{5,12}$/;
            if (L != "" && !M.test(L)) {
                y(u, "QQ为5-12位数字");
            } else {
                I(u);
            }
        });
        A.focus(function () {
            if (A.val() == "让别人看到不一样的你！") {
                A.val("").removeClass("gray03").addClass("gray01");
            }
        }).blur(function () {
            if (A.val() == "") {
                A.val("让别人看到不一样的你！").removeClass("gray01").addClass("gray03");
            }
        }).bind("keyup", function (N) {
            var L = (window.event) ? event.keyCode : N.keyCode;
            var M = A.val();
            if (L == 13) {
                M = M.replace("\n", "");
                A.val(M);
            }
            if (M.length > 200) {
                A.val(M.substr(0, 200));
            }
        }).bind("keydown", function (M) {
            if (!window.event) {
                var L = M.keyCode;
                var L = String.fromCharCode(L).toLowerCase();
                if (M.ctrlKey && L == "v") {
                    M.preventDefault();
                    M.stopPropagation();
                }
            }
        });
        if (A.val() != "\u8ba9\u522b\u4eba\u770b\u5230\u4e0d\u4e00\u6837\u7684\u4f60\uff01") {
            A.removeClass("gray03").addClass("gray01");
        }

        function loading() {
            m = false;
            $("#butSaveSubmit").attr("disabled", true);
        }

        var B = function () {
            if (!m) {
                return;
            }
            var userJSON = "{\"userName\":\"" + C.userName + "\",\"mobilePhone\":\"" + C.mobilePhone + "\",\"qq\":\"" + C.userQQ + "\",\"mail\":\"\",\"phone\":\"" + C.userPhone + "\",\"userSign\":\"" + C.userSign + "\"}";
//			var C = {oldUserName:$("#hidOldName").val(), userName:"", userPhone:"", userSex:0, userBirthDay:"", userCons:0, userLiveArea:0, liveAreaName:"", userBirthArea:0, birthAreaName:"", userQQ:"", userMonthIncome:"", userSign:"", rnd:0};
            $.ajax({
                url: "/user/updateUser.action",
                data: "id=" + userId + "&userJSON=" + encodeURIComponent(userJSON),
                beforeSend: loading,
                success: function (data) {
                    if (data == "true") {
                        m = true;
                        $("#butSaveSubmit").attr("disabled", false);
                        $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功！</dd></dl>", {W: 160, H: 50, autoClose: true});
                        setTimeout(function () {
                            location.replace(location.href);
                        }, 1500);
                    }
                },
                error: function () {
                    alert("网络错误，请稍后再试！");
                    location.reload();
                }
            });
//			L.OnSuccess(function (M) {
//				var N = M.d.Code;
//				if (N == 0) {
//					if (M.d.Num == 0) {
//						$.PageDialog("<div class=\"mAltOK\"><s></s>\u4fee\u6539\u6210\u529f\uff01</div>", {W:150, H:60, oT:-30, obj:$("#butSaveSubmit"), close:false, autoClose:true, submit:function () {
//							location.reload();
//						}});
//					} else {
//						if (M.d.Num == -9) {
//							alert("昵称已存在！);
//						} else {
//							if (M.d.Num == -10) {
//								alert("修改失败！");
//							} else {
//								if (M.d.Num == -11) {
//									alert("同步更新用户名失败！");
//								}
//							}
//						}
//					}
//				} else {
//					if (N == 10) {
//						location.reload();
//					} else {
//						if (N == -2) {
//							y(q, "昵称请不要设置为与官方有关的名词！", 300);
//						} else {
//							if (N == -3) {
//								y(q, "昵称不符合规则，再修改一下吧！", 300);
//							} else {
//								alert("修改失败,请重试！");
//							}
//						}
//					}
//				}
//			});
//			C.rnd = GetRandomNum(1, 10000);
//			L.OnSend($.toJSON(C), "json", true);
        };
        var x = function () {
            if (!m) {
                return;
            }
            s = true;
            var P = q.val();
            if (P == "") {
                y(q, "\u8bf7\u8f93\u5165\u6635\u79f0");
            } else {
                if (!t(P)) {
                    y(q, F, 300);
                }
            }
            C.userName = P;
            var N = E.val();
            if (N != "" && (!D(N) && !j(N))) {
                y(E, "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7535\u8bdd\u53f7\u7801");
            }
            C.mobilePhone = N;
            var UP = phone.val();
            if (UP == "") {
                y(phone, "请输入手机号！");
            } else {
                if ((!D(UP) && !j(UP))) {
                    y(phone, "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7535\u8bdd\u53f7\u7801");
                }
            }
            C.userPhone = UP;
            if ($("#rdoBoy").attr("checked") == true) {
                C.userSex = 2;
            } else {
                if ($("#rdoGirl").attr("checked") == true) {
                    C.userSex = 1;
                } else {
                    if ($("#rdoSecrit").attr("checked") == true) {
                        C.userSex = 3;
                    }
                }
            }
//			C.userBirthDay = $("#sltYear").val() + "-" + $("#sltMouth").val() + "-" + $("#sltDay").val();
//			C.userCons = $("#selCons").val();
//			var O = e.getValue();
//			if (O != null) {
//				C.userLiveArea = O.areaBID;
//				var M = e.getText().trim();
//				C.liveAreaName = (M == "\u8bf7\u9009\u62e9" ? "" : M);
//			}
//			O = h.getValue();
//			if (O != null) {
//				C.userBirthArea = O.areaBID;
//				var M = h.getText().trim();
//				C.birthAreaName = (M == "\u8bf7\u9009\u62e9" ? "" : M);
//			}
            var L = u.val();
            var Q = /^[0-9]{5,12}$/;
            if (L != "" && !Q.test(L)) {
                y(u, "QQ\u4e3a5-12\u4f4d\u6570\u5b57");
            }
            C.userQQ = L;
            C.userMonthIncome = $("#sltMonthIncome").val();
            if (A.val() != "\u8ba9\u522b\u4eba\u770b\u5230\u4e0d\u4e00\u6837\u7684\u4f60\uff01") {
                C.userSign = A.val();
            }
            if (s) {
                B();
            }
        };
        $("#butSaveSubmit").bind("click", function () {
            x();
        });
        m = true;
    };
    c();
});

