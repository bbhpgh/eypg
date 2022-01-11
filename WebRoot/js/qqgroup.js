$(function () {
    var c = "----\u8bf7\u9009\u62e9----";
    var e = "";
    var b = function (i) {
        this.DataTextField = null;
        this.DataValueField = null;
        this.DataSource = null;
        this.OnItemDataBinding = null;
        this.OnSelectedIndexChanged = null;
        this.DataBind = function () {
            i.empty();
            if (this.DataSource == null) {
                return;
            }
            var l = this.DataSource.length;
            var n = false;
            if (this.OnSelectedIndexChanged != null) {
                n = true;
            }
            var o = null;
            for (var j = 0; j < l; j++) {
                o = this.DataSource[j];
                if (this.OnItemDataBinding != null) {
                    this.OnItemDataBinding(o);
                }
                var k = $("<a href=\"javascript:void(0);\" no=\"" + o[this.DataValueField] + "\">" + o[this.DataTextField] + "</a>");
                if (n) {
                    k.bind("click", this.OnSelectedIndexChanged);
                }
                i.append(k);
            }
            var m = 220;
            if (l < 10) {
                m = l * 22;
            }
            i.css("height", m + "px");
        };
        this.show = function () {
            i.show();
        };
        this.hide = function () {
            i.hide();
        };
        this.clear = function () {
            i.empty();
        };
    };
    var a = function () {
        var k = $("#sltAreaAID");
        var q = $("#sltAreaBID");
        var s = $("#sltAreaCID");
        var m = $("#itemAreaAID");
        var r = $("#itemAreaBID");
        var t = $("#itemAreaCID");
        var o = new b(m);
        var n = new b(r);
        var l = new b(t);
        k.parent().click(function (w) {
            r.hide();
            t.hide();
            m.show();
            w.stopPropagation();
        });
        q.parent().click(function (w) {
            m.hide();
            t.hide();
            r.show();
            w.stopPropagation();
        });
        s.parent().click(function (w) {
            m.hide();
            r.hide();
            t.show();
            w.stopPropagation();
        });
        $("body").click(function (w) {
            m.hide();
            r.hide();
            t.hide();
        });
        k.html(c);
        q.html(c);
        s.html(c);
        var v = new Object();
        o.DataTextField = n.DataTextField = l.DataTextField = "name";
        o.DataValueField = n.DataValueField = l.DataValueField = "id";
        var j = function (y, z) {
            var A = {name: c, id: 0};
            if (z == 0) {
                y.DataSource = [A];
                y.DataBind();
                return;
            }
            var w = v[z];
            if (w != null) {
                y.DataSource = w;
                y.DataBind();
                return;
            }
            var x = function (B) {
                if (B != null && B.code == 0) {
                    if (z != 0) {
                        B.data.unshift(A);
                    }
                    v[z] = B.data;
                    y.DataSource = B.data;
                    y.DataBind();
                }
            };
            GetJPData(e, "getchildarea", "id=" + z, x);
        };
        var p = function (w) {
            var x = $(this);
            var y = x.attr("no");
            var z = k.attr("no");
            if (z != y) {
                j(n, y);
                j(l, 0);
                k.attr("no", y).html(x.html());
                q.attr("no", "0").html(c);
                s.attr("no", "0").html(c);
            }
            o.hide();
            w.stopPropagation();
            g();
        };
        var i = function (x) {
            var y = $(this);
            var w = y.attr("no");
            var z = q.attr("no");
            if (z != w) {
                j(l, w);
                q.attr("no", w).html(y.html());
                s.attr("no", "0").html(c);
            }
            n.hide();
            x.stopPropagation();
            g();
        };
        var u = function (x) {
            var y = $(this);
            var w = y.attr("no");
            s.attr("no", w).html(y.html());
            l.hide();
            x.stopPropagation();
            g();
        };
        o.OnSelectedIndexChanged = p;
        n.OnSelectedIndexChanged = i;
        l.OnSelectedIndexChanged = u;
        this.init = function () {
            j(o, 1);
            j(n, 0);
            j(l, 0);
            return;
        };
        this.getValue = function () {
            var x = k.attr("no");
            var w = q.attr("no");
            var y = s.attr("no");
            return {areaAID: x, areaBID: w, areaCID: y};
        };
    };
    var h = new a();
    var d = function (i) {
        return i.replace(/&/ig, "&amp;").replace(/</ig, "&lt;").replace(/>/ig, "&gt;").replace(/\[s:([^\]]+)\]/ig, "<em>$1</em>");
    };
    var g = function () {
        var m = $("#listContents");
        m.html("<div class=\"loadImg\">正在加载…</div>");
        var p = 60;
        var i = 1;
        var l = 0;
        var k = {areaID: 0, key: "", FIdx: 1, EIdx: p, isCount: 1};
        var o = h.getValue();
        k.areaID = o.areaCID > 0 ? o.areaCID : o.areaBID > 0 ? o.areaBID : o.areaAID;
        if (!k.areaID) {
            k.areaID = 0;
        }
        k.key = "";
        var j = function () {
            var r = "areaID=" + k.areaID + "&key=" + k.key + "&FIdx=" + k.FIdx + "&EIdx=" + k.EIdx + "&isCount=" + k.isCount;
            var q = function (u) {
                if (u.code == 0) {
                    if (k.isCount == 1) {
                        l = u.count;
                    }
                    var v = "<ul>";
                    for (var s = 0; s < u.data.Rows.length; s++) {
                        var t = u.data.Rows[s];
                        v += "<li><dt><img border=\"0\" alt=\"" + t.name + "\" src=\"/Images/qqgroup.png\"/></dt><dt>" + d(t.name) + "<dd>" + t.qq + "</dd></dt></li>";
                    }
                    v += "</ul>";
                    m.html(v);
                } else {
                    m.html("<div class=\"nothing\">该地区暂无<em>QQ</em>群加盟，1元拍购诚邀您加盟，详情请咨询<em>QQ:52013594</em></div>");
                }
            };
            GetJPData(e, "getqqpagelist", r, q);
        };
        var n = function (q, r) {
            i = Math.floor(r / p);
            if (i > 1) {
                k.isCount = 0;
            }
            k.FIdx = q;
            k.EIdx = r;
            j();
        };
        j();
    };
    var f = function () {
        var k = "<p>\u76f4\u5c5e\u7fa4</p>";
        var j = $("#listTopContents");
        j.html(k + "<div class=\"loadImg\">正在加载…</div>");
        var i = function (n) {
            if (n.code == 0) {
                var o = k + "<ul>";
                for (var l = 0; l < n.data.Rows.length; l++) {
                    var m = n.data.Rows[l];
                    o += "<li><dt><img border=\"0\" alt=\"" + m.name + "\" src=\"/Images/qqgroup.png\"/></dt><dt>" + d(m.name) + "<dd>" + m.qq + "</dd></dt></li>";
                }
                o += "</ul>";
                j.html(o);
            } else {
                j.html(k + "<div class=\"nothing\">暂无<em>QQ</em>群，详情请咨询<em>QQ:52013594</em></div>");
            }
            g();
        };
        GetJPData(e, "getqqtoplist", "", i);
    };
    h.init();
    f();
});

