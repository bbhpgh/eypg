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
        r.find("div.countdown_div").html("<span class=\"lateron\">\u59dd\uff45\u6e6a\u7481\uff04\u757b,\u7487\u98ce\u25e2\u935a\u5e98\u20ac\ufffd</span>");
        var u = null;
        var t = function () {
            GetJPData("http://dataserver.1yyg.com", "GetBarcodernoInfo", "codeID=" + s, function (v) {
                if (v.code == 0) {
                    r.attr("class", "new_li").find("div.countdown_div").replaceWith("<span class=\"winner\">\u947e\u5cf0\u7df1\u9470\u5483\u7d30<strong><a href=\"http://u.1yyg.com/" + v.userWeb + "\" class=\"blue\" target=\"_blank\">" + v.userName + "</a></strong></span>").find("div.newawary").remove();
                    if (u != null) {
                        clearInterval(u);
                        u = null;
                    }
                }
            });
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

