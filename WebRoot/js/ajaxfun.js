var isLoaded = null;
String.prototype.trim = function () {
    return this.replace(/(\s*$)|(^\s*)/g, "");
};
window.onerror = function () {
    return true;
};

function GetData(b) {
    var c = b;
    var a = function () {
    };
    this.OK = function (d) {
        a = d;
    };
    this.Get = function (d) {
        $.ajax({
            url: c, dataType: d, data: "", beforeSend: function (e) {
                if (d == "json") {
                    e.setRequestHeader("Content-Type", "application/json;charset=utf-8");
                }
            }, success: function (f) {
                try {
                    a(f);
                }
                catch (g) {
                }
            }
        });
    };
}

function JQAjax(a, e) {
    var b = function () {
        isLoaded = false;
    };
    var d = function () {
        isLoaded = true;
    };
    var g = function () {
        isLoaded = true;
    };
    var f = function () {
    };
    var c = null;
    var i = a;
    var h = 20000;
    if (e != null) {
        h = e;
    }
    this.OnStart = function (j) {
        b = j;
    };
    this.OnStop = function (j) {
        d = j;
    };
    this.OnError = function (j) {
        g = j;
    };
    this.OnSuccess = function (j) {
        f = j;
    };
    this.OnTimeout = function (j) {
        c = window.setTimeout(j, h);
    };
    this.OnSend = function (j, k, l) {
        $.ajax({
            global: false,
            async: l,
            type: "POST",
            url: i + "?rnd=" + GetRandomNum(1, 10000),
            cache: true,
            contentType: k == "json" ? "application/json" : "application/x-www-form-urlencoded;charset=utf-8",
            data: j,
            dataType: k,
            dataFilter: function (n, m) {
                return n;
            },
            beforeSend: function (m) {
                try {
                    b();
                }
                catch (n) {
                }
            },
            complete: function (m, o) {
                if (c != null) {
                    window.clearTimeout(c);
                }
                try {
                    d();
                }
                catch (n) {
                }
            },
            error: function (m, p, o) {
                if (c != null) {
                    window.clearTimeout(c);
                }
                try {
                    g(p);
                }
                catch (n) {
                }
            },
            success: function (m) {
                if (c != null) {
                    window.clearTimeout(c);
                }
                try {
                    f(m);
                }
                catch (n) {
                }
            }
        });
    };
}

String.prototype.rExp = function (b, a) {
    var c = new RegExp(b, "g");
    return this.replace(c, a);
};
String.prototype.replaceWhile = function (b, a) {
    var d = this;
    var c = new RegExp(b, "g");
    while (d.indexOf(b) > -1) {
        d = d.replace(c, a);
    }
    return d;
};
String.prototype.returnRegExp = function () {
    var a = this;
    a = a.rExp("\\+", "%2B");
    a = a.rExp("%F7", escape("&divide;"));
    a = a.rExp("%B1", escape("&plusmn;"));
    a = a.rExp("%D7", escape("&times;"));
    a = a.rExp("%A9", escape("&copy;"));
    a = a.rExp("%AE", escape("&reg;"));
    a = a.rExp("%B7", escape("&middot;"));
    a = a.rExp("%A3", escape("&pound;"));
    a = a.rExp("%u2122", escape("&#8482;"));
    return a;
};

function escapeEx(a) {
    return escape(a).returnRegExp();
}

function escapeEx2(a) {
    return encodeURIComponent(a);
}

function descapeEx2(a) {
    return decodeURIComponent(a);
}

String.prototype.toHTML = function (b) {
    var a = String(this);
    if (b) {
    }
    a = a.rExp(">", "&gt;");
    a = a.rExp("<", "&lt;");
    a = a.rExp(" ", "&nbsp;");
    a = a.rExp("'", "'");
    a = a.rExp("\"", "\"");
    a = a.rExp("\r\n", "<br/>");
    a = a.rExp("\n", "<br/>");
    a = a.rExp("\r", "<br/>");
    return a;
};
String.prototype.toText = function (b) {
    var a = String(this);
    if (b) {
    }
    a = a.rExp("<", "&#60;");
    a = a.rExp(">", "&#62;");
    a = a.rExp("\"", "&#34;");
    a = a.rExp("'", "&#39;");
    return a;
};
String.prototype.reAjaxStr = function () {
    var a = String(this);
    a = a.rExp("\u951b\ufffd", "'");
    return a;
};

function ToHTML(b, a) {
    return b.toHTML(a);
}

function ToText(b, a) {
    return b.toText(a);
}

function GetAjaxPageNation(a, e, b, d, c) {
    return GetAjaxPageNationEx(a, e, b, d, c, true);
}

function GetAjaxPageNationEx(b, f, c, e, d, a) {
    return GetAjaxPageNationEx2(b, f, c, e, d, a, false);
}

function GetAjaxPageNationEx2(b, j, a, l, g, h, k) {
    if (b < 1) {
        return "";
    }
    var c = 1, f = 1, m = 1;
    c = ((b % j) == 0 ? Math.floor(b / j) : Math.floor(b / j) + 1);
    var e = "<ul class=\"pageULEx\">";
    if (h) {
        e += "<li class=\"total_page\">\u6924\u57ab\ue0bc<i>" + l + "/" + c + "</i>&nbsp;&nbsp;\u934f\ufffd<i>" + b + "</i>\u93c9\xa4\ue187\u8930\ufffd</li>";
    }
    while (f + a < c + 1 && f + a < l + 2) {
        f += a - 2;
    }
    m = f + a - 1;
    if (m > c) {
        m = c;
    }
    if (l == 1) {
        if (k) {
            e += "<li class=\"prev_page page_curgray\"><a><i><</i>\u6d93\u5a41\u7af4\u6924\ufffd</a></li>";
        }
    } else {
        e += "<li class=\"prev_page\"><a href=\"javascript:gotoClick();\" onclick=\"javascript:return " + g + "(" + ((l - 2) * j + 1) + "," + (l - 1) * j + ");\">\u6d93\u5a41\u7af4\u6924\ufffd</a></li>";
    }
    if (f > 1) {
        e += "<li><a href=\"javascript:gotoClick();\" onclick=\"javascript:return " + g + "(1," + j + ");\">1</a></li><li>\u9225\ufffd</li>";
    }
    for (var d = f; d <= m; d++) {
        if (d != l) {
            e += "<li><a href=\"javascript:gotoClick();\" onclick=\"javascript:return " + g + "(" + ((d - 1) * j + 1) + "," + d * j + ");\">" + d + "</a></li>";
        } else {
            e += "<li class=\"curr_page\">" + d + "</li>";
        }
    }
    if (m < c) {
        e += "<li>\u9225\ufffd</li><li><a href=\"javascript:gotoClick();\" onclick=\"javascript:return " + g + "(" + ((c - 1) * j + 1) + "," + c * j + ");\">" + c + "</a></li>";
    }
    if (l < c) {
        e += "<li class=\"next_page\"><a href=\"javascript:gotoClick();\" onclick=\"javascript:return " + g + "(" + (l * j + 1) + "," + (l + 1) * j + ");\">\u6d93\u5b29\u7af4\u6924\ufffd</a></li>";
    } else {
        if (k) {
            e += "<li class=\"prev_page page_curgray\"><a>\u6d93\u5b29\u7af4\u6924\ufffd<i>></i></a></li>";
        }
    }
    e += "</ul>";
    return e;
}

function showLoadingStatus() {
}

function closeLoadingStatus() {
}

$.extend({
    toJSON: function (a) {
        var d = typeof a;
        if ("object" == d) {
            if (Array == a.constructor) {
                d = "array";
            } else {
                if (RegExp == a.constructor) {
                    d = "regexp";
                } else {
                    d = "object";
                }
            }
        }
        switch (d) {
            case "undefined":
            case "unknown":
                return;
                break;
            case "function":
            case "boolean":
            case "regexp":
                return a.toString();
                break;
            case "number":
                return isFinite(a) ? a.toString() : "null";
                break;
            case "string":
                return "\"" + a.replace(/(\\|\")/g, "\\$1").replace(/\n|\r|\t/g, function () {
                    var g = arguments[0];
                    return (g == "\n") ? "\\n" : (g == "\r") ? "\\r" : (g == "\t") ? "\\t" : "";
                }) + "\"";
                break;
            case "object":
                if (a === null) {
                    return "null";
                }
                var c = [];
                for (var f in a) {
                    var e = jQuery.toJSON(a[f]);
                    if (e !== undefined) {
                        c.push(jQuery.toJSON(f) + ":" + e);
                    }
                }
                return "{" + c.join(",") + "}";
                break;
            case "array":
                var c = [];
                for (var b = 0; b < a.length; b++) {
                    var e = jQuery.toJSON(a[b]);
                    if (e !== undefined) {
                        c.push(e);
                    }
                }
                return "[" + c.join(",") + "]";
                break;
        }
    }
});

