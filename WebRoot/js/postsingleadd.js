var userUploadFun = null;
$(function () {
    var l = $skin;
    var k = "";
    var h = function () {
        var m = "<div class=\"tips_main\"><div class=\"title1\">关于晒单分享奖励制度调整的公告</div><div class=\"text\"><p>亲爱的拍友：<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为进一步提升用户体验，并结合广大拍友提出的宝贵建议，自<span class=\"red\">2014年3月1日</span>起（以晒单时间为准），我们将对晒单分享奖励制度进行优化升级，实行阶梯式的晒单福分奖励制度，制度以晒单内容的总体质量为准，奖励幅度为<span class=\"red\">400-1500</span>福分，原先固定的1000福分奖励制度将不再实行。详细制度如下：</p><p>1、 晒单内容越详细，获得的福分奖励就越多（限时商品除外）；<br />2、 您的文字描述应不少于<span class=\"red\">100</span>字，高清的商品实拍照不少于<span class=\"red\">3</span>张；<br />3、 请避免晒单内容中文字与图片的大量重复；<br />4、 为提高晒单的真实性，您可以展示真实有效的<span class=\"red\">快递单号</span>\uff1b<br />5、 建议晒出<span class=\"red\">您与商品的合照</span>，您的<span class=\"red\">手机短信、邮件通知</span>及" + $shortName + "网上的<span class=\"red\">交易详情</span>页面截图等； <br />6、 注意保持晒单内容与获得商品的关联性，请勿使用其他网站的图片，请勿违反国家相关规定，否则我们有权删除并冻结账号，且保留追究其法律责任的权利；<br />7、 如果晒单内容非常新颖，那么您有可能会获得更高的奖励！</p><p><a href=\"#\" target=\"_blank\">详情请点击</a></p></div><div class=\"btn\"><a id=\"btnClose\" href=\"javascript:;\" class=\"btn_OK\">知道了</a> </div></div>";
        $.PageDialog(m, {
            W: 780,
            H: 500,
            close: false,
            autoClose: false,
            obj: $("div.R-content"),
            oL: 0,
            oT: -695,
            ready: function () {
                $("#btnClose").click(function () {
                    SetCookieByExpires("_memPostTip", "1", 1);
                    $.PageDialog.close();
                });
            }
        });
    };
    if (getCookie("_memPostTip") != 1) {
        h();
    }

    function SetCookieByExpires(name, value, date) //存cookie 有过期时限
    {
        var Days = date;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";id=1ypg;path=/;expires=" + exp.toGMTString() + ";domain=" + $domain;
    }

    function getCookie(name)//取cookies函数        
    {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null) return unescape(arr[2]);
        return null;
    }

    var g = $("#pageForm");
    var b = g.find("div.localBtn");
    var i = g.find("div.upImgLoading");
    var e, j;
    var a = b.find("div.btn");
    var d = $("#fuploadFile");
    b.parent().mousemove(function (n) {
        var m = $(window).width();
        if (j != m) {
            j = m;
            e = b.offset();
        }
        $("#FileControl").css({top: (n.pageY - e.top - 7) + "px", left: (n.pageX - e.left - 190) + "px"});
        a.attr("class", "btnhov");
    }).mouseout(function (m) {
        a.attr("class", "btn");
    });
    d.mouseover(function () {
        $(this).css("cursor", "pointer");
    });
    var f = function () {
        this.ShowUploadPic = function (n) {
            g.get(0).reset();
            i.addClass("hidden");
            b.removeClass("hidden");
            var o = $("#ulPicBox");
            var q = $("#hidPicAll");
            var p = q.val();
            if (p.indexOf(n) < 0) {
                p += (p == "" ? "" : ",") + n;
                q.val(p);
                var m = "<div id=\"img" + n.substr(0, 17) + "\"><img src=\"" + k + "/Temp/" + n + "\" alt=\"\" /><a href=\"javascript:gotoClick();\" onclick=\"javascript:delPostPic('" + n + "');\">删除此图片</a></div>";
                o.append(m).show();
                $.PageDialog("<dl class=\"sAltOK\"><dd>图片上传成功！</dd></dl>", {W: 180, H: 50, autoClose: true});
            } else {
                return false;
            }
        };
        this.ShowUploadErr = function (m) {
            g.get(0).reset();
            i.addClass("hidden");
            b.removeClass("hidden");
            $.PageDialog("<dl class=\"sAltFail\"><dd>" + m + "</dd></dl>", {W: 250, H: 50});
        };
    };
    userUploadFun = new f();
    d.bind("change", function () {
        var n = $(this).val();
        if (n != "") {
            var m = (n.substr(n.length - 5)).substr((n.substr(n.length - 5)).indexOf(".") + 1).toLowerCase();
            if (m != "gif" && m != "jpg" && m != "bmp") {
                $(this).val("");
                $.PageDialog("<dl class=\"sAltFail\"><dd>只能上传JPG、GIF或BMP图片!</dd></dl>", {
                    W: 250,
                    H: 50,
                    autoClose: true
                });
                return false;
            } else {
                b.addClass("hidden");
                i.removeClass("hidden");
                g.submit();
            }
        } else {
            $.PageDialog("<dl class=\"sAltFail\"><dd>请先选择需要上传的文件!</dd></dl>", {W: 210, H: 50, autoClose: true});
            return false;
        }
    });
    var c = function () {
        var o = ["标题不少于5个字", "恭喜您" + $shortName + "成功，在此输入您的" + $shortName + "感言吧，内容不少于100字，审核通过即可获得相应福分奖励哦！"];
        var m = $("#codeID").val();
        k = $("#imgFileUrl").val();
        var n = function (p, q) {
            $.PageDialog("<dl class=\"sAltFail\"><dd>" + q + "</dd></dl>", {W: 220, H: 50, autoClose: true});
            if (p != null) {
                p.focus().val(p.val());
            }
        };
        $("#but_ok").click(function () {
            if (!isLoaded) {
                return false;
            }
            var t = $("#postTitle").val();
            var r = $("#postTitle");
            var q = $("#postContent").val();
            var s = $("#postContent");
            var p = $("#hidPicAll").val();
            if (t == "" || t == o[0]) {
                n(r, "请输入主题！");
                return false;
            }
            if (t.length > 100) {
                n(r, "主题不能超过100字！");
                return false;
            }
            if (t.length < 5) {
                n(r, "主题不能少于5个字！");
                return false;
            }
            if (q == "" || q == o[1]) {
                n(s, "请输入" + $shortName + "感言！");
                return false;
            }
            if (q.length > 800) {
                n(s, "" + $shortName + "感言不能超过800字！");
                return false;
            }
            if (q.length < 100) {
                n(s, "" + $shortName + "感言不能少于100个字！");
                return false;
            }
            if (p.length < 65) {
                n(null, "请上传3张以上原创图片！");
                return false;
            }
//			$.post("http://member.1yyg.com/JPData", {action:"insertPostSingle", postCodeID:m, postTitle:t, postContent:q, postAllPic:p}, function (u) {
//				if (u != null && u.code == 0) {
//					OKDialog("\u63d0\u4ea4\u6210\u529f!", 160);
//					location.replace("/PostSingleList.html");
//				} else {
//					if (u != null && u.code == 10) {
//						location.reload();
//					} else {
//						n(null, "\u63d0\u4ea4\u5931\u8d25\uff01");
//					}
//				}
//			}, "json");
        });
        $("#postTitle").focus(function () {
            var p = $(this).val();
            if (p == o[0]) {
                $(this).val("").attr("class", "title_click");
            }
        }).blur(function () {
            if ($(this).val() == "") {
                $(this).val(o[0]).attr("class", "title");
            }
        });
        $("#postContent").focus(function () {
            var p = $(this).val();
            if (p == o[1]) {
                $(this).val("").attr("class", "textarea_click");
            }
        }).blur(function () {
            if ($(this).val() == "") {
                $(this).val(o[1]).attr("class", "textarea");
            }
        });
        $("#addnewPostPic").click(function () {
            var p = $("#hidPicAll").split(",");
            if (p.length < 10) {
                $.PageDialog("<iframe name=\"PostSingleFrame\" id=\"PostSingleFrame\"  scrolling=\"no\" width=\"398\" height=\"100\" frameborder=\"0\" src=\"about:blank\"></iframe>", {
                    W: 398,
                    H: 150,
                    title: "上传新晒单图片"
                });
                $("#PostSingleFrame").ready(function () {
                    $("#PostSingleFrame").attr("src", "uploadPostImg.html");
                });
            } else {
                alert("最多只能上传10张图片！");
            }
        });
        isLoaded = true;
    };
//	Base.getScript(l + "/JS/AjaxFun.js?date=20130123", c);
    c();
});

function UploadFileScriptAPI(b, a) {
    if (b != "") {
        userUploadFun.ShowUploadErr(b);
    } else {
        userUploadFun.ShowUploadPic(a);
    }
}

function delPostPic(a) {
    var b = function () {
        var c = $("#hidPicAll");
        var d = c.val();
        if (d.indexOf(a) != -1) {
            d = ("," + d + ",").replace("," + a + ",", ",");
            c.val(d.substr(1, d.length - 2));
        }
        $("#img" + a.substr(0, 17)).remove();
    };
    $.PageDialog.showConfirm("您确定要删除该图片吗？", b);
}

