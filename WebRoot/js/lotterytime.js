$.fn.StartTimeOut = function (s, d) {
    var r = $(this);
    var a = new Date();
    a.setSeconds(a.getSeconds() + d);
    var l = 0;
    var o = 0;
    var n = 0;
    var k = function () {
        var u = new Date();
        if (a > u) {
            var v = parseInt((a.getTime() - u.getTime()) / 1000);
            var t = v % 60;
            l = parseInt(v / 60);
            o = parseInt(t);
            if (t >= o) {
                n = parseInt((t - o) * 10);
            } else {
                n = 0;
            }
            setTimeout(k, 3000);
        }
    };
    var b = r.find("span.minute");
    var j = r.find("span.second");
    var e = r.find("span.millisecond");
    var q = r.find("span.last");
    var g = 9;
    var m = function () {
        g--;
        if (g < 0) {
            g = 9;
        }
        q.html(g);
        setTimeout(m, 10);
    };
    var c = function () {
        q.html("0");
        r.find("#jiexiaoTime").html("<div class=\"pour-are\">\u6b63\u5728\u8ba1\u7b97...</div>");
        var u = null;
        var t = function () {
//			alert("请求开奖去");
//			GetJPData("http://api.1yyg.com", "GetBarcodernoInfo", "codeID=" + s, function (w) {
//				if (w.code == 0) {
//					var v = "";
//					v += "<s class=\"lottery-tips\"></s><a class=\"fl goodsimg\" href=\"http://dataserver.1yyg.com/lotterydetail-" + s + ".html\" target=\"_blank\" title=\"(\u7b2c" + w.codePeriod + "\u671f)" + w.goodsName + "\">";
//					v += "<img src=\"http://goodsimg.1yyg.com/goodspic/pic-200-200/" + w.goodsPic + "\" alt=\"\" /></a>";
//					v += "<div class=\"publishC-Member gray02\">";
//					v += "<a class=\"fl headimg\" href=\"http://u.1yyg.com/" + w.userWeb + "\" target=\"_blank\" title=\"" + w.userName + "\">";
//					v += "<img src=\"http://faceimg.1yyg.com/UserFace/" + w.userPhoto + "\" alt=\"\" /></a><p>";
//					v += "\u83b7\u5f97\u8005\uff1a<a class=\"blue Fb\" href=\"http://u.1yyg.com/" + w.userWeb + "\" target=\"_blank\" title=\"" + w.userName + "\">" + w.userName + "</a></p>";
//					v += "<p>\u6765\u81ea\uff1a" + w.ipAddr + "</p><p>\u4e91\u8d2d\uff1a<em class=\"orange Fb\">" + w.buyCount + "</em>\u4eba\u6b21</p>";
//					v += "</div>";
//					v += "<div class=\"publishC-tit\">";
//					v += "<h3><a href=\"http://dataserver.1yyg.com/lotterydetail-" + s + ".html\" target=\"_blank\" class=\"gray01\">(\u7b2c" + w.codePeriod + "\u671f)" + w.goodsName + "</a></h3>";
//					v += "<p class=\"money\">\u5546\u54c1\u4ef7\u503c\uff1a<span class=\"rmb\">" + w.price + "</span></p>";
//					v += "<p class=\"Announced-time gray02\">\u63ed\u6653\u65f6\u95f4\uff1a" + w.codeRTime.substring(0, 10) + "</p></div>";
//					v += "<div class=\"details\"><p class=\"fl details-Code\">\u5e78\u8fd0\u4e91\u8d2d\u7801\uff1a<em class=\"orange Fb\">" + w.codeRNO + "</em></p>";
//					v += "<a class=\"fl details-A\" href=\"http://dataserver.1yyg.com/lotterydetail-" + s + ".html\" rel=\"nofollow\" target=\"_blank\">\u67e5\u770b\u8be6\u60c5</a>";
//					r.removeClass("lottery").html(v);
//					r.hover(function () {
//						r.addClass("Cursor");
//					}, function () {
//						r.removeClass("Cursor");
//					});
//					if (u != null) {
//						clearInterval(u);
//						u = null;
//					}
//				}
//			});
        };
        u = setInterval(t, 2000);
    };
    var i = function () {
        n--;
        if (n < 1) {
            if (o < 1) {
                if (l < 1) {
                    c();
                    return;
                } else {
                    l--;
                }
                o = 59;
            } else {
                o--;
            }
            n = 9;
        }
        setTimeout(i, 100);
    };
    var f = 0, p = 0;
    var h = function () {
        e.html(n);
        if (f != o) {
            if (o < 10) {
                j.html("0" + o);
            } else {
                j.html(o);
            }
            f = o;
        }
        if (p != l) {
            if (l < 10) {
                b.html("0" + l);
            } else {
                b.html("00");
            }
            p = l;
        }
        setTimeout(h, 100);
    };
    k();
    i();
    m();
    h();
};

