var CBLFun = null;
$(document).ready(function () {
    var b = function () {
        var c = function () {
            var u = $("#txtAppMoney");
            var r = $("#txtUserName");
            var q = $("#txtBankName");
            var o = $("#txtSubBank");
            var x = $("#txtBankNo");
            var w = $("#txtPhone");
            var d = $("#txtCZMoney");
            var userId = $("#userId").val();
            $("#linkApply").click(function () {
                $(this).removeClass("current2").addClass("current");
                $("#linkRecharge").removeClass("current");
                $("#divSQTX").show();
                $("#divTip").show();
                $("#divSQCZ").hide();
                u.focus();
            });
            $("#linkRecharge").click(function () {
                $("#linkApply").removeClass("current").addClass("current2");
                $(this).addClass("current");
                $("#divSQTX").hide();
                $("#divTip").hide();
                $("#divSQCZ").show();
                d.focus();
            });

            var e = getPageDataArray();
            if (e.t != undefined) {
                $("#linkRecharge").click();
            }
            var m = function (z, y) {
                z.value = z.value.replace(y == "." ? (/[^\d.]/g) : (/[^\d-]/g), "");
                z.value = z.value.replace(y == "." ? (/^\./g) : (/^\-/g), "");
                z.value = z.value.replace(y == "." ? (/\.{2,}/g) : (/\-{2,}/g), y);
                z.value = z.value.replace(y, "$#$").replace(y == "." ? (/\./g) : (/\-/g), "").replace("$#$", y);
            };
            x.bind("keyup", function (y) {
                x.val(x.val().replace(/\s/g, "").replace(/(\w{4})(?=\w)/g, "$1 "));
            });
            var f = function (y, z) {
                y.siblings("span").attr("class", "oran").html("<em></em>" + z);
            };
            var i = function (y, z) {
                y.siblings("span").attr("class", "").html(z);
            };
            $("input[type='text']").keyup(function () {
                var z = $(this);
                var y = z.val();
                if (y != "") {
                    i(z, "");
                    if (parseInt(y) == 0) {
                        z.val(0);
                    }
                }
            }).blur(function () {
                var y = parseInt($(this).attr("tip"));
                k = null;
                n = true;
                switch (y) {
                    case 1:
                        v();
                        break;
                    case 2:
                        s();
                        break;
                    case 3:
                        j();
                        break;
                    case 4:
                        h();
                        break;
                    case 5:
                        l();
                        break;
                    case 6:
                        t();
                        break;
                }
            });
            var p = {money: "", userName: "", bankName: "", subBank: "", bankNo: "", phone: ""};
            var k = null;
            var n = true;
            var g = /^[\u4e00-\u9fa5]{2,30}$/;
            var v = function () {
                if (u.val() == "") {
                    f(u, "\u8bf7\u8f93\u5165\u63d0\u73b0\u91d1\u989d");
                    n = false;
                } else {
                    var y = parseFloat($("#spanBrokerage").html());
                    var A = parseFloat(u.val());
                    var z = /^\d{1,9}(\.\d{1,2})?$/;
                    if (!z.exec(A)) {
                        f(u, "\u63d0\u73b0\u91d1\u989d\u4e3a\u6b63\u6574\u6570\u6216\u5e26\u4e24\u4f4d\u5c0f\u6570");
                        n = false;
                    } else {
                        if (A > y) {
                            f(u, "\u8f93\u5165\u989d\u8d85\u51fa\u53ef\u63d0\u73b0\u91d1\u989d");
                            n = false;
                        } else {
                            if (A < 100) {
                                f(u, "\u63d0\u73b0\u91d1\u989d\u987b\u81f3\u5c11100\u5143");
                                n = false;
                            }
                        }
                    }
                }
                if (!n && k == null) {
                    k = u;
                }
            };
            var s = function () {
                var y = r.val();
                if (y == "") {
                    f(r, "\u8bf7\u8f93\u5165\u5f00\u6237\u4eba");
                    n = false;
                    if (k == null) {
                        k = r;
                    }
                } else {
                    if (!g.test(y)) {
                        f(r, "\u5f00\u6237\u4eba\u8f93\u5165\u6709\u8bef");
                        n = false;
                    }
                }
            };
            var j = function () {
                var y = q.val();
                if (y == "") {
                    f(q, "\u8bf7\u8f93\u5165\u94f6\u884c\u540d\u79f0");
                    n = false;
                    if (k == null) {
                        k = q;
                    }
                } else {
                    if (!g.test(y)) {
                        f(q, "\u94f6\u884c\u540d\u79f0\u8f93\u5165\u6709\u8bef");
                        n = false;
                    }
                }
            };
            var h = function () {
                var y = o.val();
                if (y == "") {
                    f(o, "\u8bf7\u8f93\u5165\u5f00\u6237\u652f\u884c");
                    n = false;
                    if (k == null) {
                        k = o;
                    }
                } else {
                    if (!g.test(y)) {
                        f(o, "\u5f00\u6237\u652f\u884c\u8f93\u5165\u6709\u8bef");
                        n = false;
                    }
                }
            };
            var l = function () {
                if (x.val() == "") {
                    f(x, "\u8bf7\u8f93\u5165\u94f6\u884c\u5e10\u53f7");
                    n = false;
                } else {
                    var y = /^\d{16,19}$/;
                    if (!y.test(x.val().replace(/[ ]/g, ""))) {
                        f(x, "\u94f6\u884c\u8d26\u53f7\u4e3a16-19\u4f4d\u6570\u5b57");
                        n = false;
                    }
                }
                if (!n && k == null) {
                    k = x;
                }
            };
            var t = function () {
                var y = /(^1+[0-9]{10}$)|(^(0[0-9]{2,3}\-)+([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)/;
                if (w.val() == "") {
                    f(w, "\u8bf7\u8f93\u5165\u8054\u7cfb\u7535\u8bdd");
                    n = false;
                } else {
                    if (!y.test(w.val())) {
                        f(w, "\u8054\u7cfb\u7535\u8bdd\u683c\u5f0f\u6709\u8bef\uff0c\u56fa\u8bdd\u683c\u5f0f\u5982\uff1a0755-22228888");
                        n = false;
                    }
                }
                if (!n && k == null) {
                    k = w;
                }
            };
            $("#btnSQTX").click(function () {
                k = null;
                n = true;
                v();
                s();
                j();
                h();
                l();
                t();
                if (!n) {
                    k.focus();
                    return false;
                }
                p.money = u.val();
                p.userName = r.val();
                p.bankName = q.val();
                p.subBank = o.val();
                p.bankNo = x.val().replace(/[ ]/g, "");
                p.phone = w.val();
                var userJSON = "{\"userId\":\"" + userId + "\",\"money\":\"" + p.money + "\",\"bankUser\":\"" + p.userName + "\",\"bankName\":\"" + p.bankName + "\",\"bankSubbranch\":\"" + p.subBank + "\",\"bankNo\":\"" + p.bankNo + "\",\"phone\":\"" + p.phone + "\"}";
                $.ajax({
                    url: "/user/ApplyMentionAdd.html",
                    data: "userJSON=" + encodeURIComponent(userJSON),
                    type: "post",
                    success: function (data) {
                        if (data == "success") {
                            u.val("");
                            r.val("");
                            q.val("");
                            o.val("");
                            x.val("");
                            w.val("");
                            var z = parseFloat($("#spanBrokerage").html());
                            var B = formatFloat(z - parseFloat(p.money));
                            $("#spanBrokerage2").html(B);
                            $("#spanBrokerage").html(B);
                            $.PageDialog("<dl class=\"sAltOK\"><dd>申请成功，请等待审核!</dd></dl>", {
                                W: 218,
                                H: 50,
                                autoClose: true
                            });
                        } else if (data == "moneyError") {
                            $.PageDialog("<dl class=\"sAltFail\"><dd>您输入的金额有误！</dd></dl>", {
                                W: 218,
                                H: 50,
                                autoClose: true
                            });
                        } else {
                            alert("网络错误，请与管理员联系！");
                        }
                    },
                    error: function () {
                        alert("网络错误，请与管理员联系！");
                    }
                });
            });
            d.keyup(function () {
                this.value = this.value.replace(/[^\d]/g, "");
            });
            $("#btnSQCZ").click(function () {
                var B = true;
                var z = parseFloat($("#spanBrokerage2").html());
                if (z < 1) {
                    f(d, "\u60a8\u73b0\u6709\u4f63\u91d1\u4e0d\u6ee11\u5143");
                    B = false;
                } else {
                    if (d.val() == "") {
                        f(d, "\u8bf7\u8f93\u5165\u5145\u503c\u91d1\u989d");
                        B = false;
                    } else {
                        var y = /^[1-9]+[0-9]*]*$/;
                        if (!y.test(d.val())) {
                            f(d, "\u8bf7\u8f93\u5165\u6b63\u6574\u6570");
                            B = false;
                        } else {
                            if (parseFloat(d.val()) > z) {
                                f(d, "\u8f93\u5165\u7684\u91d1\u989d\u5927\u4e8e\u53ef\u7528\u4f63\u91d1");
                                B = false;
                            }
                        }
                    }
                }
                if (!B) {
                    d.focus();
                    return;
                }
                var userJSON = "{\"userId\":\"" + userId + "\",\"recharge\":\"" + d.val() + "\"}";
                $.ajax({
                    url: "/user/ApplyMentionAddToUser.html",
                    data: "userJSON=" + encodeURIComponent(userJSON),
                    type: "post",
                    success: function (data) {
                        if (data == "success") {
                            $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功!</dd></dl>", {W: 160, H: 50, autoClose: true});
                            C();
                        } else if (data == "moneyError") {
                            $.PageDialog("<dl class=\"sAltFail\"><dd>您输入的金额有误！</dd></dl>", {
                                W: 218,
                                H: 50,
                                autoClose: true
                            });
                        } else {
                            alert("网络错误，请与管理员联系！");
                        }
                    },
                    error: function () {
                        alert("网络错误，请与管理员联系！");
                    }
                });
//				var D = new JQAjax("/DataServer/MemberCenterApplyToAccount");
//				D.OnSuccess(function (E) {
//					eval("E=" + E.d);
//					if (E.errorCode == 10) {
//						location.reload();
//					} else {
//						if (E.errorCode == 0) {
//							OKDialog("\u5145\u503c\u6210\u529f\uff01", 150);
//							C();
//						} else {
//							FailDialog("\u5145\u503c\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u5ba2\u670d\uff01");
//						}
//					}
//				});
//				D.OnSend($.toJSON(A), "json", true);
                var C = function () {
                    var E = formatFloat(z - parseFloat(d.val()));
                    $("#spanBrokerage2").html(E);
                    $("#spanBrokerage").html(E);
                    d.val("");
                };
            });
            u.focus();
        };
        CBLFun = new c();
    };

    function getPageDataArray() {
        var url = location.search;
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
            }
        }
        return theRequest;
    }

    function formatFloat(src) {
        return Math.round(src * Math.pow(10, 2)) / Math.pow(10, 2);
    }

    b();
});

