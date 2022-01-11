$(document).ready(function () {
    var a = "http://skin.1ypg.com";
    var b = function () {
        var j = $("#retrySend");
        var f = $("#hidRegEmail").val();
        var d = $("#hidIsVerify").val();
        var g = $("#divVerify");
        var h = $("#divError");
        var e = $("#divNormal");
        var k = function () {
            h.html("").hide();
            e.show();
        };
        var c = function (l) {
            g.hide();
            e.hide();
            h.html("<span class=\"orange Fb\">" + l + "</span>").show();
        };
        var i = function () {
            if (!isLoaded) {
                return;
            }
            $.ajax({
                url: "/register/SendRegisterMail.html?mail=" + f,
                data: "string",
                type: "post",
                beforeSend: OnStart,
                success: function (data) {
                    if (data == 2) {
                        k();
                    } else {
                        if (data == 0) {
                            c("此邮箱已通过验证，可直接登录！");
                        } else {
                            if (data == 1) {
                                c("此邮箱已被禁用，请与客服联系！");
                            } else if (data == 3) {
                                c("验证邮件已经发送，请在前往邮箱验证，如果在10分钟后仍未收到验证邮件请再点击重新发送按钮。");
                            } else {
                                c("验证邮件发送失败，请重试！");
                            }
                        }
                    }
                    isLoaded = true;
                    j.attr("class", "login_SendoutbutClick");
                },
                error: function () {
                    c("验证邮件发送失败，请重试！");
                }
            });
//			var l = new JQAjax("/DataServer/SendRegisterMail");
//			l.OnStart(function () {
//				isLoaded = false;
//				c("系统正在发送验证邮件，请稍后……");
//				j.attr("class", "login_Sendoutbut");
//			});
//			l.OnStop(function () {
//				isLoaded = true;
//				j.attr("class", "login_SendoutbutClick");
//			});
//			l.OnSuccess(function (m) {
//				_Code = m.d.Code;
//				if (_Code == 2) {
//					k();
//				} else {
//					if (_Code == 0) {
//						c("此邮箱已通过验证，可直接登录！");
//					} else {
//						if (_Code == 1) {
//							c("此邮箱已被禁用，请与客服联系！");
//						} else {
//							c("验证邮件发送失败，请重试！");
//						}
//					}
//				}
//			});
//			l.OnSend("{'email':'" + f + "'}", "json", true);
            function OnStart() {
                isLoaded = false;
                c("系统正在发送验证邮件，请稍后……");
                j.attr("class", "login_Sendoutbut");
            }
        };
        j.bind("click", i);
        isLoaded = true;
        if (d == "1") {
            g.show();
            h.html("").hide();
            j.attr("class", "login_SendoutbutClick");
        } else {
            i();
        }
    };
    b();
});

