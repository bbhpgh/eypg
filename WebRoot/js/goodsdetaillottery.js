function startTimeOut() {
    if (!isLoaded) {
        setTimeout("startTimeOut()", 1000);
        return false;
    }
    var b = $("#hidCodeID").val();
    var a = $("#divLotteryTimer");
    var c = $("#divLotteryTiming");
    $.ajax({
        url: "/products/isLottery.action",
        data: "id=" + b,
        success: function (data) {
            data = eval('(' + data + ')');
            var k = data.spStatus;
            var l = parseInt(data.date);
            var e = function () {
                a.hide();
                c.show();
                var z = function () {
                    $.ajax({
                        url: "/lottery/lotteryUtil.action",
                        data: "id=" + b,
                        success: function (data) {
                            if (data == "true") {
                                location.href = $www + "/lotteryDetail/" + b + ".html";
                            }
                        }
                    });
                };
                setInterval(z, 2000);
            };
            if (k == 2) {
                var d = new Date();
                d.setSeconds(d.getSeconds() + l);
                var p = 0;
                var v = 0;
                var u = 0;
                var f = $("#imgFunny");
                var o = function () {
                    var A = new Date();
                    if (d > A) {
                        var C = (d.getTime() - A.getTime()) / 1000;
                        var z = C % 60;
                        p = parseInt(C / 60);
                        v = parseInt(z);
                        if (z >= v) {
                            u = parseInt((z - v) * 10);
                        } else {
                            u = 0;
                        }
                        var B = parseInt(C / 15) + 1;
                        if (B < 11) {
                            f.attr("src", $img + "/Images/" + B + ".gif");
                        }
                        setTimeout(o, 3000);
                    }
                };
                var g = $("#liMinute1");
                var i = $("#liMinute2");
                var r = $("#liSecond1");
                var s = $("#liSecond2");
                var q = $("#liMilliSecond1");
                var x = $("#last");
                var j = 9;
                var t = function () {
                    j--;
                    if (j < 0) {
                        j = 9;
                    }
                    x.attr("class", "Code_" + j).html(j + "<b></b>");
                    setTimeout(t, 10);
                };
                var n = function () {
                    u--;
                    if (u < 1) {
                        if (v < 1) {
                            if (p < 1) {
                                e();
                                return;
                            } else {
                                p--;
                            }
                            v = 59;
                        } else {
                            v--;
                        }
                        u = 9;
                    }
                    setTimeout(n, 100);
                };
                var h = -1, w = -1;
                var m = function () {
                    q.attr("class", "Code_" + u).html(u + "<b></b>");
                    var z = 0;
                    if (h != v) {
                        if (v < 10) {
                            r.attr("class", "Code_0").html("0<b></b>");
                            s.attr("class", "Code_" + v).html(v + "<b></b>");
                        } else {
                            z = parseInt(v / 10);
                            r.attr("class", "Code_" + z).html(z + "<b></b>");
                            z = parseInt(v % 10);
                            s.attr("class", "Code_" + z).html(z + "<b></b>");
                        }
                        h = v;
                    }
                    if (w != p) {
                        if (p < 10) {
                            g.attr("class", "Code_0").html("0<b></b>");
                            i.attr("class", "Code_" + p).html(p + "<b></b>");
                        } else {
                            z = parseInt(p / 10);
                            g.attr("class", "Code_" + z).html(z + "<b></b>");
                            z = parseInt(p % 10);
                            i.attr("class", "Code_" + z).html(z + "<b></b>");
                        }
                        w = p;
                    }
                    setTimeout(m, 100);
                };
                o();
                n();
                t();
                m();
                a.show();
            } else {
//				if (k == -3) {
                e();
//				}
            }
        }
    });
}

