login = {
    loginload: function () {
        function ckMobile(B) {
            var A = /^1\d{10}$/;
            if (!A.exec(B)) {
                return false;
            } else {
                return true;
            }
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

        function SetCookie(name, value) //存cookie
        {
            document.cookie = name + "=" + escape(value) + ";id=1ypg;path=/;domain=" + $domain;
        }

        function loadingLogin() {
            $("#btnSubmitLogin").remove();
            $("#loginSubmitStatus").show();
        }

        $(function () {
            var G = $("#username");
            var K = $("#pwd");
            var B = $("#name_ts");
            var H = $("#pwd_ts");
            var E = null;
            var L = null;
            var C = 1;
            var D = 1;
            var I = function (M, N) {
                M.attr("class", "ts_wrong").html(N);
            };
            G.focus(function () {
                $(this).attr("class", "text_name_focus");
            }).blur(function () {
                $(this).attr("class", "text_name");
                if (C == 0) {
                    B.attr("class", "ts").html("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u624b\u673a\u53f7/\u90ae\u7bb1");
                }
            });
            K.focus(function () {
                $(this).attr("class", "text_password_focus");
            }).blur(function () {
                $(this).attr("class", "text_password");
                if (D == 0) {
                    H.attr("class", "ts").html("\u8bf7\u586b\u5199\u957f\u5ea6\u4e3a6-16\u957f\u5ea6\u7684\u5b57\u7b26\u5bc6\u7801");
                }
            });
            var J = function () {
                E = G.val();
                L = K.val();
                if (E == "" || (!ckMobile(E) && !ckEmail(E))) {
                    C = 0;
                    G.focus();
                    I(B, "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u624b\u673a\u53f7/\u90ae\u7bb1");
                    return false;
                }
                if (L == "") {
                    D = 0;
                    K.focus();
                    I(H, "\u8bf7\u8f93\u5165\u767b\u5f55\u5bc6\u7801");
                    return false;
                } else {
                    if (!ckPasswd(L)) {
                        D = 0;
                        K.focus();
                        I(H, "\u767b\u5f55\u5bc6\u7801\u4e3a6-16\u957f\u5ea6\u7684\u5b57\u7b26");
                        return false;
                    }
                }
                C = 1;
                D = 1;
                return true;
            };
            var newE = "";
            var forward = $("#forward").val();
            var F = function () {
                if (!isLoaded) {
                    return;
                }
                if (J()) {
                    if (newE != E) {
                        $.ajax({
                            url: "/login/login.html",
                            type: "post",
                            data: "userName=" + E + "&pwd=" + L,
                            beforeSend: loadingLogin,
                            success: function (data) {
                                if (data == "userError") {
                                    C = 0;
                                    G.focus();
                                    I(B, "\u767B\u5F55\u5E10\u53F7\u4E0D\u5B58\u5728\uFF0C\u8BF7\u91CD\u65B0\u8F93\u5165\uFF01");
                                    $("#loginText").prepend("<input id=\"btnSubmitLogin\" type=\"button\" value=\"登录\" class=\"login_init\" tabindex=\"3\" />");
                                    $("#loginSubmitStatus").hide();
                                    newE = E;
                                } else if (data == "pwdError") {
                                    D = 0;
                                    K.focus();
                                    I(H, "\u767B\u5F55\u5BC6\u7801\u9519\u8BEF\uFF0C\u8BF7\u91CD\u65B0\u8F93\u5165\uFF01");
                                    $("#loginText").prepend("<input id=\"btnSubmitLogin\" type=\"button\" value=\"登录\" class=\"login_init\" tabindex=\"3\" />");
                                    $("#loginSubmitStatus").hide();
                                } else if (data == "check") {
                                    C = 0;
                                    G.focus();
                                    I(B, "注册用户没有验证，请登录邮箱进行验证！");
                                    $("#loginText").prepend("<input id=\"btnSubmitLogin\" type=\"button\" value=\"登录\" class=\"login_init\" tabindex=\"3\" />");
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
                                    if (forward == "rego") {
                                        window.location.href = $www;
                                    } else if (forward == "mycart") {
                                        window.location.href = $www + "/mycart/index.html";
                                    } else if (forward == "myUser") {
                                        window.location.href = $www + "/user/index.html";
                                    } else if (forward == "PostSingleList") {
                                        window.location.href = $www + "/user/PostSingleList.html";
                                    } else if (forward == "auth") {
                                        window.location.href = $www + "/referAuth.html";
                                    } else if (forward == "cartPay") {
                                        window.location.href = $www + "/cartPay/index.html";
                                    } else {
                                        window.location.href = $www;
                                    }
                                }
                            },
                            error: function () {
                                alert("\u8FDE\u63A5\u670D\u52A1\u5668\u5931\u8D25\uFF0C\u8BF7\u7A0D\u540E\u518D\u8BD5\uFF01");
                                window.location.href = $www;
                            }
                        });
                    } else {
                        I(B, "\u767B\u5F55\u5E10\u53F7\u4E0D\u5B58\u5728\uFF0C\u8BF7\u91CD\u65B0\u8F93\u5165\uFF01");
                        C = 0;
                        G.focus();
                    }

                }
            };
            $("#LoginForm").keydown(function (N) {
                var M = (window.event) ? event.keyCode : N.keyCode;
                if (M == 32) {
                    return false;
                } else {
                    if (M == 13) {
                        F();
                    }
                }
                return true;
            });
            $("#btnSubmitLogin").live("click", function () {
                F();
            });
            var A = function () {
                GetLogoInfo(9, "getbysortid", false, function (M) {
                    $("#loadingPicBlock").html(M);
                });
            };
            isLoaded = true;
            G.focus();
        });

//		 //新浪微博登录
//		WB2.anyWhere(function(W){
//		    W.widget.connectButton({
//		        id: "wb_connect_btn",	
//		        type:"3,2",
//		        callback : {
//		            login:function(o){	//登录后的回调函数
//		            	$.ajax({
//			 				url:"/register/authorizeIsExists.action?userName="+encodeURIComponent(o.screen_name.replace(/(\/)/g, "")),
//			 				type:"get",
//			 				success:function(data){
//			 					if(data=="true"){
//			 						$.ajax({
//									   type: "POST",
//									   url:"/register/authorizeRegsiter.action",
//									   data: "userName="+encodeURIComponent(o.screen_name.replace(/(\/)/g, "")),
//									   success: function(data){
//									     SetCookie("userId",data.userId);
//						            	 SetCookie("mail",data.userName);
//						            	 SetCookie("loginType",1);
//						            	 window.location.href=$www;
//									   }
//									}); 
//			 					}else{
//			 						SetCookie("userId",data.userId);
//					            	SetCookie("mail",data.userName);
//					            	SetCookie("loginType",1);
//					            	window.location.href=$www;
//			 					}
//			 				}
//			 			});
//		            }
//		        }
//		    });
//		});
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
                                            window.location.href = $www;
                                        }
                                    });
                                } else {
                                    SetCookie("userId", data.userId);
                                    SetCookie("userName", data.userName);
                                    SetCookie("face", data.faceImg);
                                    SetCookie("loginType", 2);
                                    window.location.href = $www;
                                }
                            }
                        });
                    });
                }
            }
        );
    }
};

jQuery(function ($) {
    login.loginload();
});