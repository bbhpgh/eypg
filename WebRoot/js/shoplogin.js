jQuery(function ($) {
    var index = 0;

    function isDigit(B) {
        var A = /^[0-9]{6}$/;
        if (!A.exec(B)) {
            return false;
        }
        return true;
    }

    function ckEmail(A) {
        var B = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (B.test(A)) {
            return true;
        }
        return false;
    }

    function ckPasswd(B) {
        var A = /^[^ \f\n\r\t\v]{6,16}$/;
        if (!A.exec(B)) {
            return false;
        }
        return true;
    }

    function ckMobile(B) {
        var A = /^1\d{10}$/;
        if (!A.exec(B)) {
            return false;
        } else {
            return true;
        }
    }

    function SetCookie(name, value) //存cookie
    {
        document.cookie = name + "=" + escape(value) + ";id=1ypg;path=/;domain=" + $domain;
    }

    function loadingLogin() {
        $("#btnSubmitLogin").remove();
        $("#loginSubmitStatus").show();
    }

    $(function () {
        var D = $("#username");
        var G = $("#pwd");
        var A = $("#name_ts");
        var E = $("#pwd_ts");
        var B = null;
        var I = null;
        var F = function (J, K) {
            $("#liError").html("<div id=\"div_login\"><p class=\"ts\"></p>" + J + "</div>");
            $(K).focus();
        };
        D.focus().attr("class", "text_name_focus");
        D.focus(function () {
            $(this).attr("class", "text_name_focus");
            if (A.attr("class") == "ts") {
                A.attr("class", "ts").html("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u624b\u673a\u53f7/\u90ae\u7bb1");
            }
            $(this).parent().attr("class", "click");
        }).blur(function () {
            $(this).attr("class", "num");
            $(this).parent().attr("class", "");
            H(false, false);
        });
        G.focus(function () {
            $(this).attr("class", "text_name_focus");
            if (E.attr("class") == "ts") {
                E.attr("class", "ts").html("\u8bf7\u586b\u5199\u957f\u5ea6\u4e3a6-20\u957f\u5ea6\u7684\u5b57\u7b26\u5bc6\u7801");
            }
            $(this).parent().attr("class", "click");
        }).blur(function () {
            $(this).attr("class", "num");
            $(this).parent().attr("class", "");
            H(false, false);
        });
        var H = function (M, L) {
            var K = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
            var J = /^1[3-9]\d{9}$/;
            B = D.val();
            I = G.val();
            A.attr("class", "ts").html("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u624b\u673a\u53f7/\u90ae\u7bb1");
            E.attr("class", "ts").html("\u8bf7\u586b\u5199\u957f\u5ea6\u4e3a6-20\u957f\u5ea6\u7684\u5b57\u7b26\u5bc6\u7801");
            if (B == "") {
                if (M) {
                    D.focus();
                }
                G.attr("class", "num");
                if (L) {
                    A.attr("class", "wrong").html("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u624b\u673a\u53f7/\u90ae\u7bb1\uff01");
                }
                return false;
            } else {
                if (!ckMobile(B) && !ckEmail(B)) {
                    if (M) {
                        D.focus();
                    }
                    G.attr("class", "num");
                    A.attr("class", "wrong").html("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u624b\u673a\u53f7/\u90ae\u7bb1\uff01");
                    return false;
                }
            }
            if (I == "") {
                if (M) {
                    G.focus();
                }
                D.attr("class", "num");
                if (L) {
                    E.attr("class", "wrong").html("\u8bf7\u8f93\u5165\u767b\u5f55\u5bc6\u7801\uff01");
                }
                return false;
            } else {
                if (!ckPasswd(I)) {
                    if (M) {
                        G.focus();
                    }
                    D.attr("class", "num");
                    E.attr("class", "wrong").html("\u767b\u5f55\u5bc6\u7801\u4e3a6-20\u957f\u5ea6\u7684\u5b57\u7b26\uff01");
                    return false;
                }
            }
            return true;
        };
        var newE = "";
        var C = function (K) {
            if (!isLoaded) {
                return;
            }
            var M = false;
            var L = false;
            if (K == 0) {
                M = true;
                L = true;
            }
            if (K == 1) {
                M = true;
                L = false;
            }
            if (H(M, L)) {
                if (newE != B) {
                    $.ajax({
                        url: "/login/login.html?userName=" + B + "&pwd=" + I,
                        type: "post",
                        data: "String",
                        beforeSend: loadingLogin,
                        success: function (data) {
                            if (data == "userError") {
                                D.focus();
                                A.attr("class", "wrong").html("\u767B\u5F55\u5E10\u53F7\u4E0D\u5B58\u5728\uFF0C\u8BF7\u91CD\u65B0\u8F93\u5165\uFF01");
                                $("#loginSubmitStatus").before("<input id=\"btnSubmitLogin\" type=\"button\" name=\"login_init\" value=\"登录\" class=\"login_init\"/>");
                                $("#loginSubmitStatus").hide();
                                newE = B;
                            } else if (data == "pwdError") {
                                G.focus();
                                E.attr("class", "wrong").html("\u767B\u5F55\u5BC6\u7801\u9519\u8BEF\uFF0C\u8BF7\u91CD\u65B0\u8F93\u5165\uFF01");
                                $("#loginSubmitStatus").before("<input id=\"btnSubmitLogin\" type=\"button\" name=\"login_init\" value=\"登录\" class=\"login_init\"/>");
                                $("#loginSubmitStatus").hide();
                            } else {
                                if (data.userId) {
                                    SetCookie("userId", data.userId);
                                    SetCookie("face", data.faceImg);
                                }
                                if (data.userName) {
                                    SetCookie("userName", data.userName);
                                } else if (data.mail) {
                                    SetCookie("mail", data.mail);
                                } else if (data.phone) {
                                    SetCookie("phone", data.phone);
                                }
                                SetCookie("loginType", 0);
                                parent.location.href = $www + "/mycart/payment.html";
                            }
                        },
                        error: function () {
                            alert("\u8FDE\u63A5\u670D\u52A1\u5668\u5931\u8D25\uFF0C\u8BF7\u7A0D\u540E\u518D\u8BD5\uFF01");
                            window.location.href = $www;
                        }
                    });
                } else {
                    A.attr("class", "wrong").html("\u767B\u5F55\u5E10\u53F7\u4E0D\u5B58\u5728\uFF0C\u8BF7\u91CD\u65B0\u8F93\u5165\uFF01");
                    D.focus();
                }
            }
        };
        $("#LoginForm").keydown(function (K) {
            var J = (window.event) ? event.keyCode : K.keyCode;
            if (J == 32) {
                return false;
            } else {
                if (J == 13) {
                    C(1);
                }
            }
            return true;
        });
        $("#btnSubmitLogin").live("click", function () {
            C(0);
        });
        isLoaded = true;

//	 //新浪微博登录
//	WB2.anyWhere(function(W){
//	    W.widget.connectButton({
//	        id: "wb_connect_btn",	
//	        type:"3,2",
//	        callback : {
//	            login:function(o){	//登录后的回调函数
//	            	$.ajax({
//		 				url:"/register/authorizeIsExists.action?userName="+o.screen_name,
//		 				type:"get",
//		 				success:function(data){
//		 					if(data=="true"){
//		 						$.ajax({
//								   type: "POST",
//								   url:"/register/authorizeRegsiter.action",
//								   data: "userName="+o.screen_name,
//								   success: function(data){
//								     SetCookie("userId",data.userId,1);
//					            	 SetCookie("mail",data.userName,1);
//					            	 SetCookie("loginType",1,1);
//					            	 parent.location.href = $www+"/mycart/payment.html";
//								   }
//								}); 
//		 					}else{
//		 						SetCookie("userId",data.userId,1);
//				            	SetCookie("mail",data.userName,1);
//				            	SetCookie("loginType",1,1);
//				            	parent.location.href = $www+"/mycart/payment.html";
//		 					}
//		 				}
//		 			});
//	            }
//	        }
//	    });
//	});
//	
//		//腾讯QQ登录
        QC.Login({//按默认样式插入QQ登录按钮
                btnId: "qqLoginBtn",	//插入按钮的节点id
                scope: "get_user_info,add_share",
                size: "B_M"
            }, function (reqData, opts) {//登录成功
                if (QC.Login.check()) {//如果已登录
                    QC.Login.getMe(function (openId, accessToken) {
                        //alert(["当前登录用户的", "openId为："+openId, "accessToken为："+accessToken,"nickname为"+reqData.nickname].join("\n"));
                        $.ajax({
                            url: "/register/authorizeIsExists.action",
                            type: "POST",
                            data: "openId=" + encodeURIComponent(openId.replace(/(\/)/g, "")),
                            success: function (data) {
                                if (data == "true") {
                                    $.ajax({
                                        type: "POST",
                                        url: "/register/authorizeRegsiter.action",
                                        data: "userName=" + encodeURIComponent(reqData.nickname.replace(/(\/)/g, "")) + "&openId=" + openId + "&userFace=" + reqData.figureurl_2,
                                        success: function (data) {
                                            SetCookie("userId", data.userId);
                                            SetCookie("userName", data.userName);
                                            SetCookie("face", data.faceImg);
                                            SetCookie("loginType", 2);
                                            parent.location.href = $www + "/mycart/payment.html";
                                        }
                                    });
                                } else {
                                    SetCookie("userId", data.userId);
                                    SetCookie("userName", data.userName);
                                    SetCookie("face", data.faceImg);
                                    SetCookie("loginType", 2);
                                    parent.location.href = $www + "/mycart/payment.html";
                                }
                            }
                        });
                    });
                }
            }
        );

    });
});