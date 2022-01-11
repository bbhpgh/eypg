$(document).ready(function () {
    function getCookie(name)//取cookies函数        
    {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null) return unescape(arr[2]);
        return null;
    }

    function SetCookieByExpires(name, value, date) //存cookie 有过期时限
    {
        var Days = date;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";id=1ypg;path=/;expires=" + exp.toGMTString() + ";domain=" + $domain;
    }

    function saveProduct(ID, nums) {
        var json = getCookie("products");
        if (json == null || json == "") {
            var val = "[{'pId':" + ID + ",'num':" + nums + "}]";
            SetCookieByExpires("products", val, 1);
        } else {
            var flag = false;
            json = eval('(' + json + ')');
            $.each(json, function (n, value) {
                if (ID == value.pId) {
                    value.num = parseInt(nums);
                    flag = true;
                }
            });
            if (!flag) {
                json.push({"pId": +ID, "num": nums});
            }
            json = JSON.stringify(json);
            SetCookieByExpires("products", json, 1);
        }
    }

    $.ajax({
        url: "/lottery/upcomingAnnounced.action?pageNo=" + 1 + "&pageSize=" + 3,
        type: "get",
        data: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("<div class=\"fast_list\">" +
                    "<h4><a title=\"" + data[i].productName + "\" href=\"" + $www + "/products/" + data[i].productId + ".html\">" +
                    "<img alt=\"" + data[i].productName + "\" src=\"" + $img + data[i].headImage + "\"></a></h4>" +
                    "<ul><li class=\"title\"><a title=\"" + data[i].productTitle + "\" href=\"" + $www + "/products/" + data[i].productId + ".html\">" + data[i].productName + "</a></li>" +
                    "<li>市场参考价：￥<span>" + data[i].productPrice + ".00</span></li><li>需要 <span style=\"color: #0082f0\">" + data[i].productPrice + "</span> 人次参与</li>" +
                    "<li>已参与 <span style=\"color: #ff0000; font-size: 16px; family: arial\">" + data[i].currentBuyCount + "</span> 人次</li>" +
                    "<li class=\"buy\"><a class=\"gotoCart\" href=\"javascript:void(0);\">加入购物车</a><input type=\"hidden\" value=\"" + data[i].productId + "\"></li></ul>" +
                    "</div>").appendTo($(".fast"));
            }
        }
    });
    $("li.buy > a").live("click", function () {
        $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功!</dd></dl>", {W: 160, H: 50, autoClose: true});
        setTimeout(function () {
            location.replace($www + "/mycart/index.html");
        }, 1500);
        var codeid = $(this).parent().find("input").val();
        var json = getCookie("products");
        if (json == null || json == "") {
            var val = "[{'pId':" + codeid + ",'num':1}]";
            SetCookieByExpires("products", val, 1);
        } else {
            var flag = false;
            json = eval('(' + json + ')');
            $.each(json, function (n, value) {
                if (codeid == value.pId) {
                    value.num = (parseInt(value.num) + parseInt(1));
                    flag = true;
                }
            });
            if (!flag) {
                json.push({"pId": +codeid, "num": 1});
            }
            json = JSON.stringify(json);
            SetCookieByExpires("products", json, 1);
        }
    });
});

