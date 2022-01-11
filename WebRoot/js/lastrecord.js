$(document).ready(function () {
    var a = $skin;
    var b = function () {
        var d = parseInt($("#hidBuyId").val().trim());
        var c = function () {
            var f = new JQAjax("/DataServer/GetLastRecords");
            f.OnSuccess(function (h) {
                try {
                    var k = parseInt($(h).find("Code").text());
                    if (k == 0) {
                        var i = parseInt($(h).find("Count").text());
                        if (i > 0) {
                            var g = "";
                            $(h).find("newRecords").each(function (m) {
                                if (m == 0) {
                                    d = parseInt($(this).find("buyID").text());
                                }
                                g += "<ul class=\"Record_content" + (m % 2 == 0 ? "s" : "") + "\">";
                                g += "<li class=\"time\">" + $(this).find("buyTime").text() + "</li>";
                                g += "<li class=\"nem\"><a class=\"blue\" href=\"http://u.1yyg.com/" + $(this).find("userWeb").text() + "\" target=\"_blank\">" + $(this).find("buyName").text() + "</a></li>";
                                g += "<li class=\"name\"><a href=\"http://www.1yyg.com/product/" + $(this).find("buyCode").text() + ".html\">\uff08\u7b2c" + $(this).find("goodsPeriod").text() + "\u671f\uff09" + $(this).find("goodsName").text() + "</a></li>";
                                g += "<li class=\"much\">" + $(this).find("buyNum").text() + "\u4eba\u6b21</li>";
                                g += "</ul>";
                            });
                            var l = getobj("recordList");
                            l.prepend(g);
                            l.children("ul:gt(99)").remove();
                        }
                    }
                }
                catch (j) {
                }
            });
            var e = function () {
                if (isLoaded) {
                    f.OnSend({buyId: d}, "xml", true);
                }
                setTimeout(e, 5000);
            };
            e();
        };
        isLoaded = true;
        c();
    };
});

