$(document).ready(function () {

    $("[name=goPay]").click(function () {
        var price = $(this).attr("price");
        var prId = $(this).attr("prId");

        var count = parseInt(price);
        var codeid = prId;
        var json = getCookie("buyProduct");
        if (json == null || json == "") {
            var val = "[{'pId':" + codeid + ",'num':" + count + "}]";
            SetCookieByExpires("buyProduct", val, 1);
        } else {
            var flag = false;
            json = eval('(' + json + ')');
            $.each(json, function (n, value) {
                if (codeid == value.pId) {
                    flag = true;
                }
            });
            if (!flag) {
                json.push({"pId": +codeid, "num": count});
            }
            json = JSON.stringify(json);
            SetCookieByExpires("buyProduct", json, 1);
        }
        location.href = $www + "/cartPay/index.html";
    });


    function SetCookie(name, value) //存cookie
    {
        document.cookie = name + "=" + escape(value) + ";id=1ypg;path=/;domain=" + $domain;
    };

    function SetCookieByExpires(name, value, date) //存cookie 有过期时限
    {
        var Days = date;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";id=1ypg;path=/;expires=" + exp.toGMTString() + ";domain=" + $domain;
    };

    function getCookie(name)//取cookies函数        
    {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null) return unescape(arr[2]);
        return null;
    };

    function delCookie(name)//删除cookie
    {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null) document.cookie = name + "=" + cval + ";id=1ypg;path=/;expires=" + exp.toGMTString() + ";domain=" + $domain;
    };

    $(".jqzoom").imagezoom();
    $("#mycarousel li").eq(0).addClass("curr");
    var T = $("#onload");
    $("#mycarousel li").click(function () {
        $(this).addClass("curr").siblings().removeClass("curr");
        T.show();
        $(".jqzoom").attr('src', $(this).find("img").attr("big")).load(function () {
            T.hide();
        });
        $(".jqzoom").attr('rel', $(this).find("img").attr("big"));
    });
});