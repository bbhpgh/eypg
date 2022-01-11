$(document).ready(function () {
    function getCookie(name)//取cookies函数        
    {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null) return unescape(arr[2]);
        return null;
    }

    function SetCookieByExpires(name, value, date) //存cookie 有过期时限
    {
        var Days = date;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";id=1ypg;path=/;expires=" + exp.toGMTString() + ";domain=" + $domain;
    }

    function saveProduct(ID, nums) {
        var json = getCookie("products");
        if (json == null || json == "") {
            var val = "[{'pId':" + ID + ",'num':" + nums + "}]";
            SetCookieByExpires("products", val, 1);
        } else {
            var flag = false;
            json = eval('(' + json + ')');
            $.each(json, function (n, value) {
                if (ID == value.pId) {
                    value.num = parseInt(nums);
                    flag = true;
                }
            });
            if (!flag) {
                json.push({"pId": +ID, "num": nums});
            }
            json = JSON.stringify(json);
            SetCookieByExpires("products", json, 1);
        }
    }

    var B = function () {
        var G = $("#cartNO");
        if ($("li.end").length == 0) {
            G.html("<p><span></span>\u8d2d\u7269\u8f66\u4e2d\u6682\u65f6\u6ca1\u6709\u5546\u54c1\uff01<a href='" + $www + "'><u>\u8fd4\u56de\u7ee7\u7eed\u62cd\u8d2d>></u></a></br>\u5982\u679c\u60a8\u8fd8\u672a\u767b\u5f55\uff0c\u53ef\u80fd\u5bfc\u81f4\u8d2d\u7269\u8f66\u4e3a\u7a7a\uff0c\u8bf7<a href=\"" + $www + "/login/index.html?forward=cartPay\">\u9a6c\u4e0a\u767b\u5f55</a></p>");
        } else {
            G.html("");
        }
    };
    B();
    var A = $("#moneyCount");
    var D = function () {
        var G = 0;
        $("input:hidden[name=CartMoneyCount]").each(function () {
            var J = parseInt($(this).val());
            G += J;
        });
        return G;
    };
    A.html(D() + ".00");
    var C = "";
    var F = true;
    $("li.end input.text").bind("keyup", function () {
        var M = $(this);
        M.val(M.val().replace(/[^\d]/g, ""));
        var L = M.parent().parent();
        var G = L.find("input:eq(1)").val();
        var K = L.find("span[class=count]");
        var J = L.find("span[class=xj]");
        var H = L.find("div[class=error]");
        var I = M.val();
        var ID = L.find("input[type=hidden]").eq(0).val();
        if (I == "") {
            I = 0;
        }
        I = parseInt(I);
        if (I > G) {
            H.show().html("\u6570\u91cf\u4e0d\u80fd\u5927\u4e8e" + G + "\uff01").fadeOut(3000, function () {
                H.hide();
            });
            E(L.find("input:eq(0)").val(), G);
            K.html(G);
            M.val(G);
            J.html("\uffe5" + G + ".00");
            saveProduct(ID, G);
        } else {
            E(L.find("input:eq(0)").val(), I);
            K.html(I);
            J.html("\uffe5" + I + ".00");
            saveProduct(ID, I)
        }
        if (I < 1) {
            H.show().html("\u6570\u91cf\u5fc5\u987b\u5927\u4e8e1\uff01").fadeOut(3000, function () {
                H.hide();
            });
            E(L.find("input:eq(0)").val(), 1);
            M.val("1");
            K.html("1");
            J.html("\uffe51.00");
            saveProduct(ID, M.val());
        }
        A.html(D() + ".00");
    }).mouseover(function () {
        $(this).select();
    });

    $("li.end a.delgood").bind("click", function () {
        if (confirm("\u786e\u5b9a\u8981\u5220\u9664\u5417?")) {
            var I = $(this);
            I.html("\u6b63\u5728\u5220\u9664");
            var H = I.parent().parent();
            var G = H.find("input:eq(0)").val();
            var json = getCookie("buyProduct");
            if (json != null || json != "") {
                json = eval('(' + json + ')');
                var i = 0;
                $.each(json, function (n, value) {
                    if (G == value.pId) {
                        i = n;
                    }
                });
                json.splice(i, 1);
                json = JSON.stringify(json);
                SetCookieByExpires("buyProduct", json, 1);
            }
            location.replace(location.href);
        }
    });
    $("#but_on").bind("click", function () {
        location.replace($www);
    });
    $("#but_ok").bind("click", function () {
        var J = $(".end").find("input[type=checkbox]");
        var I = 0;
        for (var H = 0; H < J.length; H++) {
            if (!$(J[H]).attr("checked")) {
                I++;
            }
        }
        if (J.length > I) {
            var userId = getCookie("userId");
            if (userId != null && userId != "") {
                location.href = $www + "/cartPay/payment.html";
            } else {
                var content = "<iframe frameborder=\"0\" style=\"width:700px;height:500px;padding:0px;overflow:auto;\" src=\"" + $www + "/login/buyCartLogin.html\" id=\"fastLoginFrame\" name=\"fastLoginFrame\"></iframe>";
                $.PageDialog(content, {W: 484, H: 265, title: "会员登录"});
            }
        } else {
            var G = "";
            if ($("li.end").length == 0) {
                G = "\u5bf9\u4e0d\u8d77,\u8d2d\u7269\u8f66\u4e2d\u6ca1\u6709\u5546\u54c1!";
            } else {
                G = "\u5bf9\u4e0d\u8d77,\u8d2d\u7269\u8f66\u4e2d\u6ca1\u6709\u9009\u4e2d\u7684\u5546\u54c1!";
            }
            $.PageDialog("<dl class=\"sAltFail\"><dd>" + G + "</dd></dl>", {W: 250, H: 50, autoClose: true});
        }
    });
    $("#close_").bind("click", function () {
        $(this).parent().remove();
    });
    $("#ckAll").bind("click", function () {
        F = $(this).attr("checked");
        var G = $("li.end");
        G.find("input[type=checkbox]").attr("checked", F);
        var H = 0;
        if (F) {
            C = "";
            A.html(D() + ".00");
            H = 0;
        } else {
            C = "";
            G.each(function (J) {
                C += G.eq(J).find("input[type=hidden]").eq(0).val() + ",";
            });
            A.html("0.00");
            H = 1;
        }
    });
    $("li.end input.check").bind("click", function () {
        var Q = $(this);
        var P = Q.parent().parent();
        var M = parseInt(P.find("input[type=text]").val());
        var H = parseInt(P.find("input[type=hidden]").eq(0).val());
        var G = $("#moneyCount");
        var N = 0;
        if (!Q.attr("checked")) {
            var K = C.split(",");
            C = "";
            for (var J = 0; J < K.length; J++) {
                if (H != K[J]) {
                    C += K[J] + ",";
                }
            }
            var R = parseInt(G.html());
            if (F) {
                if (R == 0) {
                    N = R + M;
                } else {
                    N = R - M;
                }
            } else {
                if (R == 0) {
                    N = R + M;
                } else {
                    N = R - M;
                }
            }
            G.html(N + ".00");
        } else {
            C += H + ",";
            G.html((parseInt(G.html()) + M) + ".00");
        }
        var L = $("li.end").find("input[type=checkbox]");
        var I = false;
        for (var J = 0; J < L.length; J++) {
            if (!$(L[J]).attr("checked")) {
                I = true;
            }
        }
        var O = $("#ckAll");
        if (I) {
            O.attr("checked", false);
        } else {
            O.attr("checked", true);
        }
    });
    $("#AllDelete").bind("click", function () {
        var I = "";
        var H = $("li.end").find("input[type=checkbox]");
        for (var G = 0; G < H.length; G++) {
            if (H.length == 1) {
                if ($(H[G]).attr("checked")) {
                    I = I + $(H[G]).parent().parent().find("input:eq(0)").val();
                }
            } else {
                if ($(H[G]).attr("checked")) {
                    I = I + "," + $(H[G]).parent().parent().find("input:eq(0)").val();
                }
            }
        }
        I = I.replace(",", "");
        if (I == "") {
            alert("\u8bf7\u9009\u62e9\u8981\u5220\u9664\u7684\u5546\u54c1!");
        } else {
            var a = function () {
                I = I.split(",");
                var json = getCookie("buyProduct");
                if (json != null || json != "") {
                    for (var i = 0; i < I.length; i++) {
                        json = eval('(' + json + ')');
                        var j = 0;
                        $.each(json, function (n, value) {
                            if (I[i] == value.pId) {
                                j = n;
                            }
                        });
                        json.splice(j, 1);
                        json = JSON.stringify(json);
                    }
                    SetCookieByExpires("buyProduct", json, 1);
                }
                location.reload();
            }
            $.PageDialog.showConfirm("\u786e\u5b9a\u8981\u5220\u9664\u5417?", a);
        }
    });
    var E = function (H, G) {
//	H是商品编号，G是当前购买数量，查询该商品总购买数是否满员
//		var I = new JQAjax("/DataServer/UpdateCartNum");
//		I.OnSuccess(function (J) {
//			if (J.d.Code > 0) {
//				if (J.d.Num == 1) {
//					$.PageDialog("<dl class=\"sAltFail\"><dd>\u8be5\u5546\u54c1\u672c\u671f\u7684\u62cd\u8d2d\u5df2\u6ee1\u5458,\u8bf7\u9009\u62e9\u4e0b\u4e00\u671f\u8fdb\u884c\u62cd\u8d2d\uff01</dd></dl>", {W:320, H:50, close:false, autoClose:true});
//					A.html(D() + ".00");
//					B();
//				}
//			} else {
//				alert("\u8be5\u5546\u54c1\u672c\u671f\u7684\u62cd\u8d2d\u4eba\u6b21\u5df2\u6ee1,\u5df2\u4ece\u8d2d\u7269\u8f66\u4e2d\u5220\u9664,\u8bf7\u9009\u62e9\u4e0b\u4e00\u671f\u8fdb\u884c\u62cd\u8d2d!");
//				location.reload();
//			}
//		});
//		I.OnSend($.toJSON({"codeID":H, "num":G}), "json", true);
    };

    $("li.buy > a").live("click", function () {
        $.PageDialog("<dl class=\"sAltOK\"><dd>\u6dfb\u52a0\u6210\u529f\uff01</dd></dl>", {W: 160, H: 50});
        setTimeout(function () {
            location.replace(location.href);
        }, 1500);
        var codeid = $(this).parent().find("input").val();
        var json = getCookie("products");
        if (json == null || json == "") {
            var val = "[{'pId':" + codeid + ",'num':1}]";
            SetCookieByExpires("products", val, 1);
        } else {
            var flag = false;
            json = eval('(' + json + ')');
            $.each(json, function (n, value) {
                if (codeid == value.pId) {
                    value.num = (parseInt(value.num) + parseInt(1));
                    flag = true;
                }
            });
            if (!flag) {
                json.push({"pId": +codeid, "num": 1});
            }
            json = JSON.stringify(json);
            SetCookieByExpires("products", json, 1);
        }
    });
    $.getScript($www + "/js/json2.js");
});

