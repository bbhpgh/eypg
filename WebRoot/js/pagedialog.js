(function (a) {
    a.PageDialog = function (c, q) {
        var g = {
            W: 710,
            H: 480,
            obj: null,
            oL: 0,
            oT: 0,
            close: true,
            autoClose: false,
            autoTime: 1500,
            title: "",
            BSize: 4,
            BColor: "#b4b4b4",
            BShow: true,
            ready: function () {
            },
            submit: function () {
            }
        };
        var e = {
            obj: null,
            oL: 0,
            oT: 0,
            close: true,
            autoClose: false,
            autoTime: 1500,
            title: "",
            BSize: 4,
            BColor: "#b4b4b4",
            BShow: true,
            ready: function () {
            },
            submit: function () {
            }
        };
        q = q || e;
        a.extend(g, q);
        var h = $("#pageDialog");
        var l = $("#pageDialogBG");
        var n = $("#pageDialogBorder");
        var i = $("#pageDialogMain");
        var o = $("#pageDialogClose");
        var b = a(window);
        if (g.obj != null) {
            if (g.obj.length < 1) {
                g.obj = null;
            }
        }
        var f = function () {
            b.unbind("resize");
            i.empty();
            n.hide();
            l.hide();
            h.hide();
            g.submit();
        };
        o.unbind().bind("click", f);
        if (!g.close) {
            o.hide();
        } else {
            o.show();
        }
        c = "<div class=\"content\">" + c + "</div>";
        if (g.title != "") {
            c = "<div class=\"title\">" + g.title + "</div>" + c;
        }
        i.html(c);
        h.css("border", "1px solid " + g.BColor);
        i.css({width: g.W + "px", height: g.H + "px"});
        n.css({
            opacity: 0.1,
            width: (h.width() + (g.BSize * 2 + 2)) + "px",
            height: (h.height() + (g.BSize * 2 + 2)) + "px"
        });
        if (g.obj != null) {
            var m = g.obj.offset();
            var d = m.left + g.oL;
            var p = m.top + g.obj.height() + g.oT;
            h.css({left: d, top: p});
            n.css({left: d - g.BSize, top: p - g.BSize});
        }
        var j = function () {
            var r = document.body.scrollWidth;
            if (b.width() > r) {
                r = b.width();
            } else {
            }
            l.css({
                opacity: 0.3,
                width: r + "px",
                height: a(document).height() > b.height() ? a(document).height() - 4 : b.height() + "px"
            });
        };
        var k = function () {
            j();
            if (g.obj != null) {
                return;
            }
            var u = b.scrollTop();
            var s = b.scrollLeft();
            var t = (b.height() - g.H) / 2 + u;
            var r = (b.width() - g.W) / 2 + s;
            if (t < g.BSize) {
                t = g.BSize;
            }
            if (r < g.BSize) {
                r = g.BSize;
            }
            n.css("top", t - g.BSize);
            n.css("left", r - g.BSize);
            h.css("top", t);
            h.css("left", r);
        };
        k();
        b.resize(k);
        b.scroll(j);
        l.show();
        if (g.BShow) {
            n.show();
        }
        h.show();
        if (g.autoClose) {
            window.setTimeout(function () {
                a.PageDialog.close();
            }, g.autoTime);
        }
        h.ready = g.ready();
    };
    a.PageDialog.close = function () {
        $("#pageDialogClose").click();
    };
    a.PageDialog.showConfirm = function (e, b) {
        var c = "<div class=\"PopMsgC\"><s></s>" + e + "</div><div class=\"PopMsgbtn\"><a href=\"javascript:;\" id=\"btnMsgOK\" class=\"orangebut\">\u786e\u8ba4</a>&nbsp;&nbsp;<a href=\"javascript:;\" id=\"btnMsgCancel\" class=\"cancelBtn\">\u53d6\u6d88</a></div>";
        var d = function () {
            a("#btnMsgCancel").click(function () {
                a.PageDialog.close();
            });
            a("#btnMsgOK").click(function () {
                a.PageDialog.close();
                b();
            });
        };
        a.PageDialog(c, {title: "\u63d0\u793a", W: 282, H: 146, ready: d});
    };
})(jQuery);

