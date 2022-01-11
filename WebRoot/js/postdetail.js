$(function () {
    var productId = $("#productId").val();
    var shareId = $("#shareId").val();
    var resultCount = $("#resultCount").val();
    var pageNo = 0;
    var n = function () {
        var y = false;
        var x = $("#emHits");
        var c = $("#shareId").val();
        var B = $("#emReplyCount");
        var v = x.find("em");
        var w = v.html();
        if (getCookie("UP_" + c) == "1") {
            y = true;
            x.addClass("smile-have").find("b").html("\u5df2\u7fa1\u6155(" + w + ")");
        } else {
            x.unbind("click").bind("click", function () {
                if (y) {
                    return false;
                }
                $.ajax({
                    url: "/shareShow/upShareInfo.action?shareId=" + c,
                    type: "post",
                    data: "string"
                });
                SetCookieByExpires("UP_" + c, "1", 1);
                y = true;
                x.addClass("smile-have").find("b").html("\u5df2\u7fa1\u6155(" + (parseInt(w) + 1) + ")");
                var A = $("<div class='ts'>\u7fa1\u6155+1</div>");
                x.append(A);
                A.fadeTo(3000, 0, function () {
                    A.remove();
                });
            });
        }
        x.parent().show();
    };
    n();
//分页事件
    $("#pagination").pagination(resultCount, {
        current_page: pageNo,
        prev_text: "上一页",
        next_text: "下一页",
        num_display_entries: 5,
        num_edge_entries: 1,
        link_to: "",
        prev_show_always: false,
        next_show_always: false,
        items_per_page: 5,
        callback: pageselectCallback
    });

    function pageselectCallback(pageNo) {
        var url = "/shareShow/shareCommentListAjaxPage.action?pageNo=" + pageNo + "&shareId=" + shareId;
        $(".pageUL").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\"" + $img + "/Images/loding.gif\" /></li>");
        $.ajax({
            url: url,
            type: "post",
            data: "json",
            beforeSend: loading,
            success: function (data) {
                data = eval("(" + data + ")");
                $("#commentItems").empty();
                $(".total_page").empty();
                $(".pageUL").prepend("<li class=\"total_page\">页次<i>" + (pageNo + 1) + "/" + Math.ceil(resultCount / 5) + "</i>&nbsp;&nbsp;共<i>" + resultCount + "</i>条记录</li>");
                for (var i = 0; i < data.length; i++) {
                    $("<div class=\"qcomment_content_box\"><div class=\"qcomment_content_box_usericon\"><a class=\"blue\" href=\"" + $www + "/u/" + data[i].userId + ".html\" target=\"_blank\"><img alt=\"" + data[i].userName + "\" src=\"" + $img + data[i].userFace + "\"></a></div>" +
                        "<div class=\"qcomment_box\"><div class=\"qcomment_box_top\"><span style=\"color:#22AAFF;\"><a class=\"blue\" href=\"" + $www + "/u/" + data[i].userId + ".html\" target=\"_blank\">" + data[i].userName + "</a></span>说：</div><div class=\"qcomment_box_main\"><xmp>" + data[i].content + "</xmp></div>" +
                        "<div class=\"qcomment_box_foot\"><ul><li class=\"time\">" + data[i].createDate + "</li><li><a href=\"javascript:;\" name=\"" + data[i].uid + "\" class=\"signReplay\">回复(<span>" + data[i].reCount + "</span>)</a></li></ul></div>" +
                        "<div class=\"qcomment_box_reply\" name=\"Replybox\"><div class=\"qcomment_box_reply_topbj\"></div>" +
                        "<div class=\"qcomment_reply_module\" name=\"ReplyForm\"></div>" +
                        "<div class=\"qcomment_reply_list_module\" name=\"ReplyList\"></div>" +
                        "</div><div class=\"qhackbox\"></div><div class=\"qcomment_box_bottom\"></div></div></div>").appendTo($("#commentItems"));
                }
                $("#pageLoading").hide();
                $(".qcomment_content_box").each(function (i) {
                    var reComm = null;
                    var userId = null;
                    var signReplay = $(this).find(".signReplay");
                    var reCount = signReplay.find("span").text();
                    var replybox = $(this).find("[name=Replybox]");
                    var replyList = $(this).find("[name=ReplyList]");
                    var replyForm = $(this).find("[name=ReplyForm]");
                    signReplay.bind("click", function () {
                        if (replybox.is(":visible")) {
                            replybox.hide();
                        } else {
                            replybox.show();
                        }
                        if (reComm == null && reCount > 0) {
                            replyList.empty();
                            var shareCommentId = signReplay.attr("name");
                            var url = "/shareShow/getReCommentList.action?shareCommentId=" + shareCommentId;
                            $.ajax({
                                url: url,
                                type: "get",
                                data: "json",
                                success: function (data) {
                                    data = eval("(" + data + ")");
                                    reComm = data.length;
                                    for (var i = 0; i < data.length; i++) {
                                        $("<div class=\"qcomment_reply_list\"><div class=\"qcomment_reply_list_left\"><a class=\"blue\" href=\"" + $www + "/u/" + data[i].userId + ".html\" target=\"_blank\"><img alt=\"" + data[i].userName + "\" src=\"" + $img + data[i].userFace + "\"></a></div>" +
                                            "<div class=\"qcomment_reply_list_right\" name=\"replyBoxEX\"><div class=\"qcomment_reply_list_r_top\"><em>" + (i + 1) + "楼</em><span style=\"color:#22AAFF;\"><a class=\"blue\" href=\"" + $www + "/u/" + data[i].userId + ".html\" target=\"_blank\">" + data[i].userName + "</a></span> 说：</div>" +
                                            "<div class=\"qcomment_reply_list_r_m\"><xmp>" + data[i].content + "</xmp></div><div class=\"qcomment_reply_list_r_b\">" + data[i].createDate + "</div></div><div class=\"qhackbox\"></div></div>").appendTo(replyList);
                                    }
                                    replybox.show();
                                }
                            });
                            userId = getCookie("userId");
                            if (userId != null) {
                                $("<div class=\"qcomment_box_reply_form\"><div class=\"qcomment_reply_form_textbox\"><textarea onpaste=\"return false\" oncontextmenu=\"return false;\" name=\"replyTA\"></textarea></div>" +
                                    "<div class=\"qcomment_reply_form_closebt\"><a title=\"关闭\" class=\"closereply\" href=\"javascript:;\"><span>关闭</span></a></div></div>" +
                                    "<div class=\"qcomment_box_reply_send\"><div class=\"qcomment_reply_send_left\">您还可以输入<span>150</span>个字！</div>" +
                                    "<div class=\"qcomment_reply_send_right\"><input type=\"button\" class=\"reply_botton\" value=\"提交回复\"></div></div>").appendTo(replyForm);
                                replyForm.find(".closereply").bind("click", function () {
                                    replybox.hide();
                                });
                                var replyContent = replyForm.find("[name=replyTA]");
                                O(replyContent);
                                var X = replyForm.find(".reply_botton");
                                X.bind("click", function () {
                                    var Z = replyForm.find("[name=replyTA]").val().trim();
                                    var reCommentId = signReplay.attr("name");
                                    if (Z.length < 3) {
                                        $.PageDialog("<dl class=\"sAltOK\"><dd>\u8bc4\u8bba\u5185\u5bb9\u4e0d\u80fd\u5c11\u4e8e3\u4e2a\u5b57!</dd></dl>", {
                                            W: 210,
                                            H: 50,
                                            autoClose: true
                                        });
                                        return false;
                                    }
                                    var url = "/shareShow/postComment.html";
                                    //		encodeURIComponent();
                                    $.ajax({
                                        url: url,
                                        type: "post",
                                        data: "commentText=" + encodeURIComponent(Z) + "&shareId=" + shareId + "&userId=" + userId + "&reCommentId=" + reCommentId,
                                        success: function (data) {
                                            if (data == "true") {
                                                $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功!</dd></dl>", {
                                                    W: 160,
                                                    H: 50,
                                                    autoClose: true
                                                });
                                                pageselectCallback(parseInt($(".curr_page").text() - 1));
                                            } else {
                                                $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功!</dd></dl>", {
                                                    W: 160,
                                                    H: 50,
                                                    autoClose: true
                                                });
                                                return false;
                                            }
                                        }
                                    });
                                });
                            } else {
                                $("<div class=\"qcomment_box_reply_form\"><div class=\"qcomment_reply_form_textbox\"><textarea onpaste=\"return false\" oncontextmenu=\"return false;\" class=\"hiden\" name=\"replyTA\"></textarea>" +
                                    "<div class=\"reply_login\" name=\"replyLogin\">请您<a name=\"replyLoginBtn\" href=\"javascript:;\">登录</a>或<a href=\"" + $www + "/register/index.html?forward=rego\">注册</a>后再回复</div></div>" +
                                    "<div class=\"qcomment_reply_form_closebt\"><a title=\"关闭\" class=\"closereply\" href=\"javascript:;\"><span>关闭</span></a></div></div>").appendTo(replyForm);
                                replyForm.find(".closereply").bind("click", function () {
                                    replybox.hide();
                                });
                                $("[name=replyLoginBtn]").bind("click", function () {
                                    var content = "<iframe frameborder=\"0\" style=\"width:700px;height:500px;padding:0px;overflow:auto;\" src=\"" + $www + "/login/postCommentLogin.html?shareId=" + shareId + "\" id=\"fastLoginFrame\" name=\"fastLoginFrame\"></iframe>";
                                    $.PageDialog(content, {W: 484, H: 365, title: "会员登录"});
                                });
                            }
                        } else {
                            replyForm.empty();
                            userId = getCookie("userId");
                            if (userId != null) {
                                $("<div class=\"qcomment_box_reply_form\"><div class=\"qcomment_reply_form_textbox\"><textarea onpaste=\"return false\" oncontextmenu=\"return false;\" name=\"replyTA\"></textarea></div>" +
                                    "<div class=\"qcomment_reply_form_closebt\"><a title=\"关闭\" class=\"closereply\" href=\"javascript:;\"><span>关闭</span></a></div></div>" +
                                    "<div class=\"qcomment_box_reply_send\"><div class=\"qcomment_reply_send_left\">您还可以输入<span>150</span>个字！</div>" +
                                    "<div class=\"qcomment_reply_send_right\"><input type=\"button\" class=\"reply_botton\" value=\"提交回复\"></div></div>").appendTo(replyForm);
                                replyForm.find(".closereply").bind("click", function () {
                                    replybox.hide();
                                });
                                var replyContent = replyForm.find("[name=replyTA]");
                                O(replyContent);
                                var X = replyForm.find(".reply_botton");
                                X.bind("click", function () {
                                    var Z = replyForm.find("[name=replyTA]").val().trim();
                                    var reCommentId = signReplay.attr("name");
                                    if (Z.length < 3) {
                                        $.PageDialog("<dl class=\"sAltOK\"><dd>\u8bc4\u8bba\u5185\u5bb9\u4e0d\u80fd\u5c11\u4e8e3\u4e2a\u5b57!</dd></dl>", {
                                            W: 210,
                                            H: 50,
                                            autoClose: true
                                        });
                                        return false;
                                    }
                                    var url = "/shareShow/postComment.html";
                                    //		encodeURIComponent();
                                    $.ajax({
                                        url: url,
                                        type: "post",
                                        data: "commentText=" + encodeURIComponent(Z) + "&shareId=" + shareId + "&userId=" + userId + "&reCommentId=" + reCommentId,
                                        success: function (data) {
                                            if (data == "true") {
                                                $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功!</dd></dl>", {
                                                    W: 160,
                                                    H: 50,
                                                    autoClose: true
                                                });
                                                pageselectCallback(parseInt($(".curr_page").text() - 1));
                                            } else {
                                                $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功!</dd></dl>", {
                                                    W: 160,
                                                    H: 50,
                                                    autoClose: true
                                                });
                                                return false;
                                            }
                                        }
                                    });
                                });
                            } else {
                                $("<div class=\"qcomment_box_reply_form\"><div class=\"qcomment_reply_form_textbox\"><textarea onpaste=\"return false\" oncontextmenu=\"return false;\" class=\"hiden\" name=\"replyTA\"></textarea>" +
                                    "<div class=\"reply_login\" name=\"replyLogin\">请您<a name=\"replyLoginBtn\" href=\"javascript:;\">登录</a>或<a href=\"" + $www + "/register/index.html?forward=rego\">注册</a>后再回复</div></div>" +
                                    "<div class=\"qcomment_reply_form_closebt\"><a title=\"关闭\" class=\"closereply\" href=\"javascript:;\"><span>关闭</span></a></div></div>").appendTo(replyForm);
                                replyForm.find(".closereply").bind("click", function () {
                                    replybox.hide();
                                });
                                $("[name=replyLoginBtn]").bind("click", function () {
                                    var content = "<iframe frameborder=\"0\" style=\"width:700px;height:500px;padding:0px;overflow:auto;\" src=\"" + $www + "/login/postCommentLogin.html?shareId=" + shareId + "\" id=\"fastLoginFrame\" name=\"fastLoginFrame\"></iframe>";
                                    $.PageDialog(content, {W: 484, H: 265, title: "会员登录"});
                                });
                            }
                        }
                    });
                });
            }
        });
    }

    function loading() {
        $("#pageLoading").show();
    }

    var O = function (S) {
        var R = S.parent().parent().next("div").find("div:first").find("span");
        S.bind("keyup", function (W) {
            var U = (window.event) ? event.keyCode : W.keyCode;
            var T = $(this);
            var V = T.val();
            if (U == 13) {
                if (V.indexOf("\n\n\n") != -1) {
                    V = V.replace("\n\n\n", "\n\n");
                    T.val(V);
                } else {
                    S.css("height", (S.height() + 20) + "px");
                }
            }
            if (V.length <= 150) {
                R.html(150 - V.length);
            } else {
                R.html("0");
                T.val(V.substr(0, 150));
            }
        }).bind("keydown", function (U) {
            if (!window.event) {
                var T = U.keyCode;
                var T = String.fromCharCode(T).toLowerCase();
                if (U.ctrlKey && T == "v") {
                    U.preventDefault();
                    U.stopPropagation();
                }
            }
        });
    };
    var userId = getCookie("userId");
    if (userId != null) {
        $("#notLogin").hide();
        $("#replyContent").show();
        $("#btnSubmit").attr("class", "reply_botton");
        var replyContent = $("#replyContent");
        O(replyContent);
        var X = $("#btnSubmit");
        X.bind("click", function () {
            var Z = $("#replyContent").val().trim();
            if (Z.length < 3) {
                $.PageDialog("<dl class=\"sAltOK\"><dd>\u8bc4\u8bba\u5185\u5bb9\u4e0d\u80fd\u5c11\u4e8e3\u4e2a\u5b57!</dd></dl>", {
                    W: 210,
                    H: 50,
                    autoClose: true
                });
                return false;
            }
            var url = "/shareShow/postComment.html";
//		encodeURIComponent();
            $.ajax({
                url: url,
                type: "post",
                data: "commentText=" + encodeURIComponent(Z) + "&shareId=" + shareId + "&userId=" + userId,
                success: function (data) {
                    if (data == "true") {
                        $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功!</dd></dl>", {W: 160, H: 50, autoClose: true});
                        pageselectCallback(0);
                        $("#replyContent").val("");
                        $("#wordNumber").html("150");
                        var e = parseInt(B.html()) + 1;
                        $("#resultCount").val(parseInt(resultCount) + 1);
                        B.html(e);
                    } else {
                        $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功!</dd></dl>", {W: 160, H: 50, autoClose: true});
                        return false;
                    }
                }
            });
        });
    } else {
        $("#notLogin").show();
        $("#replyContent").hide();
        $("#btnSubmit").attr("class", "reply_unbotton");
    }
    $("#linklogin").bind("click", function () {
        var content = "<iframe frameborder=\"0\" style=\"width:700px;height:500px;padding:0px;overflow:auto;\" src=\"" + $www + "/login/postCommentLogin.html?shareId=" + shareId + "\" id=\"fastLoginFrame\" name=\"fastLoginFrame\"></iframe>";
        $.PageDialog(content, {W: 484, H: 265, title: "会员登录"});
    });

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

    $(".scrollLoading").scrollLoading();
});

