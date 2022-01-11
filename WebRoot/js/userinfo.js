$(function () {
    var resultCount = $("#resultCount").val();
    var pageNo = 0;
    var id = $("#hidPageUserID").val();
    $.ajax({
        url: "/u/visitorsList.action?pageNo=" + pageNo + "&userId=" + id,
        type: "get",
        data: "json",
        success: function (data) {
            data = eval("(" + data + ")");
            $("#visitors").empty();
            for (var i = 0; i < data.length; i++) {
                $("<dl class=\"clearfix sid-guest\"><dt class=\"guest-pic\"><a href=\"" + $www + "/u/" + data[i].userId + ".html\" type=\"showCard\" uweb=\"" + data[i].userId + "\"><img width=\"50\" height=\"50\" border=\"0\" alt=\"" + data[i].userName + "\" src=\"" + data[i].faceImg + "\"></a></dt>" +
                    "<dd class=\"guest-con\"><p class=\"sid-mane\"><a rel=\"nofollow\" class=\"blue\" href=\"" + $www + "/u/" + data[i].userId + ".html\" type=\"showCard\" uweb=\"" + data[i].userId + "\">" + data[i].userName + "</a></p><p class=\"sid-address gray02\">" + data[i].ipLocation + "</p><p class=\"sid-time gray02\">" + data[i].newDate + "</p></dd></dl>").appendTo("#visitors");
            }
        }
    });
});

