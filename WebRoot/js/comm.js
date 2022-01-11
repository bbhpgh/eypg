if ($.browser.msie) {
    document.execCommand("BackgroundImageCache", false, true);
}

function getobj(A) {
    return $("#" + A);
}

function gotoClick() {
}

String.prototype.trim = function () {
    return $.trim(this);
};

function getTrimVal(A) {
    return $("#" + A).val().trim();
}

function GetRandomNum(A, C) {
    var D = C - A;
    var B = Math.random();
    return (A + Math.round(B * D));
}

function setDisabled(A) {
    getobj(A).attr("disabled", true);
}

function setEnabled(A) {
    getobj(A).removeAttr("disabled");
}

function setWait(A) {
    getobj(A).attr("disabled", true).css("cursor", "wait");
}

function setUnWait(A) {
    getobj(A).removeAttr("disabled").css("cursor", "pointer");
}

function GetStrLen(A) {
    return A.replace(/[^\x00-\xff]/g, "**").length;
}

function formatFloat(A) {
    A = Math.round(A * 1000) / 1000;
    A = Math.round(A * 100) / 100;
    if (/^\d+$/.test(A)) {
        return A + ".00";
    }
    if (/^\d+\.\d$/.test(A)) {
        return A + "0";
    }
    return A;
}

function ProhibitedCopy() {
    $(document.body).bind("copy", function () {
        try {
            clipboardData.setData("text", "\u672c\u9875\u5185\u5bb9\u672a\u7ecf\u5141\u8bb8\u8bf7\u4e0d\u8981\u968f\u610f\u590d\u5236");
        }
        catch (A) {
        }
        return false;
    });
}

function closeWindow() {
    if (document.all) {
        window.opener = null;
    }
    window.open("", "_top", "");
    window.close();
    return false;
}

function getPageDataArray() {
    var B = /([^\?&]+)=([^&]*)/g;
    var A = new Object();
    while (arr = B.exec(location.href)) {
        A[arr[1]] = arr[2] ? arr[2] : "";
    }
    return A;
}

function FailDialog(C, A) {
    var B = "<div class=\"mAltFail\"><s></s>" + C + "</div>";
    $.PageDialog(B, {W: (A === undefined ? 200 : A), H: 60, close: false, autoClose: true});
}

function OKDialog(C, A) {
    var B = "<div class=\"mAltOK\"><s></s>" + C + "</div>";
    $.PageDialog(B, {W: (A === undefined ? 200 : A), H: 60, close: false, autoClose: true});
}

function ConfirmDialog(B, A) {
    $.PageDialog.showConfirm(B, A);
}

$.cookie = function (B, I, L) {
    if (typeof I != "undefined") {
        L = L || {};
        if (I === null) {
            I = "";
            L.expires = -1;
        }
        var E = "";
        if (L.expires && (typeof L.expires == "number" || L.expires.toUTCString)) {
            var F;
            if (typeof L.expires == "number") {
                F = new Date();
                F.setTime(F.getTime() + (L.expires * 24 * 60 * 60 * 1000));
            } else {
                F = L.expires;
            }
            E = "; expires=" + F.toUTCString();
        }
        var K = L.path ? "; path=" + (L.path) : "";
        var G = L.domain ? "; domain=" + (L.domain) : "";
        var A = L.secure ? "; secure" : "";
        document.cookie = [B, "=", encodeURIComponent(I), E, K, G, A].join("");
    } else {
        var D = null;
        if (document.cookie && document.cookie != "") {
            var J = document.cookie.split(";");
            for (var H = 0; H < J.length; H++) {
                var C = jQuery.trim(J[H]);
                if (C.substring(0, B.length + 1) == (B + "=")) {
                    D = decodeURIComponent(C.substring(B.length + 1));
                    break;
                }
            }
        }
        return D;
    }
};
$.extend({
    loadcss: function (B, A) {
        $("head").append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + B + "\">").ready(function () {
            A();
        });
    }
});

