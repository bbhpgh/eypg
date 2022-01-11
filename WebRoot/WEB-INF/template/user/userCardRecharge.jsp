<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>帐户充值_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutframe.css?data=20131121"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutrecharge.css?data=20131121"/>
</head>

<body>
<div class="main-content clearfix">

    <div class="left">
        <div class="head">
            <a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${user.userId }.html">
                <img width="160" height="160" border="0" runat="server" alt="" src="${user.faceImg }"
                     id="imgUserPhoto"/></a>
        </div>
        <div class="head-but">
            <a class="blue" href="/user/UserPhoto.html">修改头像</a>
            <a class="blue fr" href="/user/MemberModify.html">编辑资料</a>
        </div>
        <div class="sidebar-nav">
            <p class="sid-line"></p>
            <h2 class=""><a href="index.html"><b></b>我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
            </a></h2>
            <p class="sid-line"></p>
            <h3 url="/user/UserBuyList.html" haschild="1" class="sid-icon02 "><a
                    href="javascript:;"><b></b>我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <s
                    title="收起"></s></a></h3>
            <ul>
                <li class=""><a
                        href="/user/UserBuyList.html"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                    记录</a></li>
                <li class=""><a href="/user/OrderList.html">获得的商品</a></li>
                <li class=""><a href="/user/PostSingleList.html">晒单</a></li>
            </ul>
            <!--<p class="sid-line"></p><h3 url="/user/JoinGroup.html" haschild="1" class="sid-icon03 "><a href="javascript:;"><b></b>圈子管理 <s title="收起"></s></a></h3>-->
            <!--<ul><li class=""><a href="/user/JoinGroup.html">加入的圈子</a></li>-->
            <!--<li class=""><a href="/user/Topic.html">圈子话题</a></li></ul>-->
            <p class="sid-line"></p>
            <h3 url="/user/InvitedList.html" haschild="1" class="sid-icon04 "><a href="javascript:;"><b></b>邀请管理 <s
                    title="收起"></s></a></h3>
            <ul>
                <li class=""><a href="/user/InvitedList.html">邀请好友</a></li>
                <li class=""><a href="/user/CommissionQuery.html">佣金明细</a></li>
                <li class=""><a href="/user/ApplyMention.html">提现申请</a></li>
                <li class=""><a href="/user/MentionList.html">提现记录</a></li>
            </ul>
            <p class="sid-line"></p>
            <h3 url="/user/UserBalance.html" haschild="1" class="sid-icon05 "><a href="javascript:;"><b></b>账户管理 <s
                    title="收起"></s></a></h3>
            <ul>
                <li class=""><a href="/user/UserBalance.html">账户明细</a></li>
                <li class=""><a href="/user/UserRecharge.html">账户充值</a></li>
                <li class="sid-cur"><a href="/user/userCardRecharge.html">实卡充值</a></li>
            </ul>
            <!--<p class="sid-line"></p><h3 url="/user/MyFriends.html" haschild="1" class="sid-icon06 "><a href="javascript:;"><b></b>我的好友 <s title="收起"></s></a></h3>-->
            <!--<ul><li class=""><a href="/user/MyFriends.html">我的好友</a></li>-->
            <!--<li class=""><a href="/user/SearchFriends.html">查找好友</a></li>-->
            <!--<li class=""><a href="/user/FriendsApply.html">好友请求</a></li></ul>-->
            <p class="sid-line"></p>
            <h3 url="" haschild="0" class="sid-icon07 "><a href="/user/MemberPoints.html"><b></b>我的福分</a></h3>

            <!--<p class="sid-line"></p><h3 url="" haschild="0" class="sid-icon08 "><a href="/user/UserMessage.html"><b></b>消息管理</a></h3>-->

            <p class="sid-line"></p>
            <h3 url="" haschild="0" class="sid-icon09 "><a href="/user/MemberModify.html"><b></b>个人设置</a></h3>


            <p class="sid-line"></p>
        </div>
        <div class="sid-service">
            <p><a class="service-btn" target="_blank"
                  href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq() %>&site=qq&menu=yes"
                  id="btnLeftQQ"><s></s>在线客服</a></p>
            <span>客服热线(免长途费)</span>
            <b class="tel"><%=ApplicationListenerImpl.sysConfigureJson.getServiceTel() %>
            </b>
        </div>
    </div>
    <script type="text/javascript">

        var _NavState = [true, true, true, true, true];

        $("div.sidebar-nav").find("h3").each(function (i, v) {
            var _This = $(this);
            var _HasClild = _This.attr("hasChild") == "1";

            var _SObj = _This.find("s");
            _This.click(function (e) {
                if (_HasClild) {
                    var _State = _NavState[i];

                    /* 一级栏目更改样式 */
                    if (_State) {
                        _This.addClass("sid-iconcur");
                        _SObj.attr("title", "展开");
                    }
                    else {
                        _This.removeClass("sid-iconcur");
                        _SObj.attr("title", "收起");
                    }

                    /* 二级栏目显示或隐藏 */
                    _This.next("ul").children().each(function () {
                        if (_State) {
                            $(this).hide(50);
                        }
                        else {
                            $(this).show(50);
                        }
                    });
                    _NavState[i] = !_State;
                }
            });
        });

    </script>
    <div class="R-content">
        <div class="member-t"><h2>账户充值</h2></div>
        <div align="center" style="padding-top: 50px;padding-left: 5px;">
            <span style="color: #f60;font-size: 24px;margin-bottom: 10px;float: left;">请输入卡密密码：</span>
            <input type="text" value="" id="txtCard" maxlength="64"
                   style="width: 770px;border: 2px solid #f60;color: #333;padding: 0 3px;height: 34px;line-height: 34px;font-size: 24px;"/>
            <input type="submit" title="确定充值" value="确定充值"
                   style="background: none repeat scroll 0 0 #f60;border: 1px solid #f70;color: #fff;margin-top: 10px;padding: 6px 25px;font-size: 14px;"
                   class="bluebut imm" id="cardButton"/>
        </div>

        <div class="member-t">
            <h2 style="float: none;">一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>充值卡问答：</h2>
            <br/>
            <p style="font-size: 14px; font-weight: bold;color: #f60;padding: 5px;">
                1.什么是一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>充值卡？</p>
            <p style="padding: 5px;">答：一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                卡是一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                (<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>
                )发行的自有预付卡，卡内资金可以在一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>商城内使用。</p>
            <p style=" font-size: 14px; font-weight: bold;color: #f60;padding: 5px;">
                2.如何购买一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>卡？</p>
            <p style="padding: 5px;">答：为了保障您的合法权益，请通过一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                认可的正规网点购买一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>卡，或淘宝进行购买。</p>
            <p style="font-size: 14px; font-weight: bold;color: #f60;padding: 5px;">
                3.购买一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>卡是否有手续费？</p>
            <p style="padding: 5px;">答：无需手续费，所有一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                充值卡都是全额到账！</p>
        </div>
    </div>

</div>
<link type="text/css" rel="stylesheet"
      href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css?data=20131121"/>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js?data=20131121"></script>
<script language="javascript" type="text/javascript">
    $("#txtCard").focus();
    var T = function () {
        $.ajax({
            url: "/user/doCardRecharge.html",
            type: "POST",
            data: "id=" + $("#txtCard").val(),
            beforeSend: function () {
                $("#cardButton").attr("value", "确认中...").attr("disabled", "disabled");
            },
            success: function (msg) {
                if (msg == "yes") {
                    $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功!</dd></dl>", {W: 160, H: 50, autoClose: true});
                    location.replace("/user/UserBalance.html");
                } else {
                    $.PageDialog("<dl class=\"sAltFail\"><dd>充值失败!该卡密不存在或已经被冲值，请重试!</dd></dl>", {
                        W: 360,
                        H: 50,
                        autoClose: false
                    });
                    setTimeout(function () {
                        window.location.href = $www + "/user/userCardRecharge.html";
                    }, 3000);
                }
            },
            error: function () {
                alert("网络错误，请稍后再试！");
            }
        });
    }
    $("#cardButton").click(function () {
        var key = $("#txtCard").val();
        if (key == "") {
            $.PageDialog("<dl class=\"sAltFail\"><dd>请输入卡密密码!</dd></dl>", {W: 180, H: 50, autoClose: true});
            $("#txtCard").focus();
            return false;
        } else if (key.length < 64) {
            $.PageDialog("<dl class=\"sAltFail\"><dd>请输入卡密密码长度不正确!</dd></dl>", {W: 250, H: 50, autoClose: true});
            $("#txtCard").focus();
            return false;
        } else {
            $.PageDialog.showConfirm("您确定充值该卡密吗?", T);
        }
    });
</script>
<div class="clear_process"></div>
<div class="pageDialogBG" id="pageDialogBG"></div>
<div class="pageDialogBorder" id="pageDialogBorder"></div>
<div class="pageDialog" id="pageDialog">
    <div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    <div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
</div>
</body>
</html>
