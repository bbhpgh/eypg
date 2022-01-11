Date.prototype.Format = function (B) {
    var C = B;
    var A = ["\u65e5", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d"];
    C = C.replace(/yyyy|YYYY/, this.getFullYear());
    C = C.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : "0" + (this.getYear() % 100));
    C = C.replace(/MM/, this.getMonth() + 1 > 9 ? (this.getMonth() + 1).toString() : "0" + (this.getMonth() + 1));
    C = C.replace(/M/g, this.getMonth());
    C = C.replace(/w|W/g, A[this.getDay()]);
    C = C.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : "0" + this.getDate());
    C = C.replace(/d|D/g, this.getDate());
    C = C.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : "0" + this.getHours());
    C = C.replace(/h|H/g, this.getHours());
    C = C.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : "0" + this.getMinutes());
    C = C.replace(/m/g, this.getMinutes());
    C = C.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : "0" + this.getSeconds());
    C = C.replace(/s|S/g, this.getSeconds());
    return C;
};
Date.prototype.DateAdd = function (B, A) {
    var C = this;
    switch (B) {
        case "s":
            return new Date(Date.parse(C) + (1000 * A));
        case "n":
            return new Date(Date.parse(C) + (60000 * A));
        case "h":
            return new Date(Date.parse(C) + (3600000 * A));
        case "d":
            return new Date(Date.parse(C) + (86400000 * A));
        case "w":
            return new Date(Date.parse(C) + ((86400000 * 7) * A));
        case "q":
            return new Date(C.getFullYear(), (C.getMonth()) + A * 3, C.getDate(), C.getHours(), C.getMinutes(), C.getSeconds());
        case "m":
            return new Date(C.getFullYear(), (C.getMonth()) + A, C.getDate(), C.getHours(), C.getMinutes(), C.getSeconds());
        case "y":
            return new Date((C.getFullYear() + A), C.getMonth(), C.getDate(), C.getHours(), C.getMinutes(), C.getSeconds());
    }
};

function IsValidDate(A) {
    var E = A.replace(/(^\s+|\s+$)/g, "");
    if (E == "") {
        return true;
    }
    var D = E.replace(/[\d]{4}[\-]{1}[\d]{1,2}[\-]{1}[\d]{1,2}/g, "");
    if (D == "") {
        var C = new Date(E.replace(/\-/g, "/"));
        var B = E.split(/[-\/:]/);
        if (B[0] != C.getFullYear() || B[1] != C.getMonth() + 1 || B[2] != C.getDate()) {
            return false;
        }
    } else {
        return false;
    }
    return true;
}

function ConvertDate(A) {
    return new Date(A.replace(/\-/g, "/"));
}

function IsValidDateTime(C) {
    var A = C.match(/^(\d{1,4})\-(\d{1,2})\-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
    if (A == null) {
        return false;
    }
    A[2] = A[2] - 1;
    var B = new Date(A[1], A[2], A[3], A[4], A[5], A[6]);
    if (B.getFullYear() != A[1]) {
        return false;
    }
    if (B.getMonth() != A[2]) {
        return false;
    }
    if (B.getDate() != A[3]) {
        return false;
    }
    if (B.getHours() != A[4]) {
        return false;
    }
    if (B.getMinutes() != A[5]) {
        return false;
    }
    if (B.getSeconds() != A[6]) {
        return false;
    }
    return true;
}

