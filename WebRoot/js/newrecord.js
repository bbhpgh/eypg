$(document).ready(function () {
    var c = function () {
        var id = $("#recordList > ul:eq(0)").attr("rel");
        var A = id;
        var css = $("#recordList > ul:eq(0)").attr("class");
        $.ajax({
            url: "/getNewRecordAjax.html",
            data: "id=" + id,
            success: function (data) {
                data = eval('(' + data + ')');
                if (data.length > 0) {
                    if (A != data[0].productStyle) {
                        var html = "";
                        if (css == "Record_content") {
                            for (var i = 0; i < data.length; i++) {
                                if (i % 2 == 0) {
                                    html += "<ul class=\"Record_contents\" rel=\"" + data[i].productStyle + "\">";
                                    html += "<li class=\"time\">" + data[i].buyDate + "</li>";
                                    html += "<li class=\"nem\"><a href=\"" + $www + "/u/" + data[i].userId + ".html\" target=\"_blank\" class=\"blue\">" + data[i].buyer + "</a></li>";
                                    html += "<li class=\"name\"><a href=\"" + $www + "/products/" + data[i].productId + ".html\" title=\"" + data[i].productTitle + "\">（第" + data[i].productPeriod + "期）" + data[i].productName + "</a></li>";
                                    html += "<li class=\"much\">" + data[i].buyCount + "人次</li>";
                                    html += "</ul>";
                                } else {
                                    html += "<ul class=\"Record_content\" rel=\"" + data[i].productStyle + "\">";
                                    html += "<li class=\"time\">" + data[i].buyDate + "</li>";
                                    html += "<li class=\"nem\"><a href=\"" + $www + "/u/" + data[i].userId + ".html\" target=\"_blank\" class=\"blue\">" + data[i].buyer + "</a></li>";
                                    html += "<li class=\"name\"><a href=\"" + $www + "/products/" + data[i].productId + ".html\" title=\"" + data[i].productTitle + "\">（第" + data[i].productPeriod + "期）" + data[i].productName + "</a></li>";
                                    html += "<li class=\"much\">" + data[i].buyCount + "人次</li>";
                                    html += "</ul>";
                                }
                            }
                        } else {
                            for (var i = 0; i < data.length; i++) {
                                if (i % 2 == 0) {
                                    html += "<ul class=\"Record_content\" rel=\"" + data[i].productStyle + "\">";
                                    html += "<li class=\"time\">" + data[i].buyDate + "</li>";
                                    html += "<li class=\"nem\"><a href=\"" + $www + "/u/" + data[i].userId + ".html\" target=\"_blank\" class=\"blue\">" + data[i].buyer + "</a></li>";
                                    html += "<li class=\"name\"><a href=\"" + $www + "/products/" + data[i].productId + ".html\" title=\"" + data[i].productTitle + "\">（第" + data[i].productPeriod + "期）" + data[i].productName + "</a></li>";
                                    html += "<li class=\"much\">" + data[i].buyCount + "人次</li>";
                                    html += "</ul>";
                                } else {
                                    html += "<ul class=\"Record_contents\" rel=\"" + data[i].productStyle + "\">";
                                    html += "<li class=\"time\">" + data[i].buyDate + "</li>";
                                    html += "<li class=\"nem\"><a href=\"" + $www + "/u/" + data[i].userId + ".html\" target=\"_blank\" class=\"blue\">" + data[i].buyer + "</a></li>";
                                    html += "<li class=\"name\"><a href=\"" + $www + "/products/" + data[i].productId + ".html\" title=\"" + data[i].productTitle + "\">（第" + data[i].productPeriod + "期）" + data[i].productName + "</a></li>";
                                    html += "<li class=\"much\">" + data[i].buyCount + "人次</li>";
                                    html += "</ul>";
                                }
                            }
                        }
                        $("#recordList").prepend(html);
                        for (var j = 0; j < data.length; j++) {
                            $("#recordList > ul:last").remove();
                        }
                    }
                }
            }
        });
        setTimeout(c, 5000);
    };
    c();
});