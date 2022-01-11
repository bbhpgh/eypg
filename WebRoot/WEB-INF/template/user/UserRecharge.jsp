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
                <li class="sid-cur"><a href="/user/UserRecharge.html">账户充值</a></li>
                <li class=""><a href="/user/userCardRecharge.html">实卡充值</a></li>
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
        <div class="select">
            <b class="gray01">请您选择充值金额</b>
            <ul id="ulMoneyList">
                <li style="margin-left:0;" class="selected"><input type="radio" checked="checked" value="10"
                                                                   name="money" id="rd10" onfocus="this.blur()"/> <label
                        for="rd10">充值 <strong>￥</strong><b>10</b></label></li>
                <li class=""><input type="radio" id="rd50" value="50" name="money" onfocus="this.blur()"/> <label
                        for="rd50">充值 <strong>￥</strong><b>50</b></label></li>
                <li class=""><input type="radio" id="rd100" value="100" name="money" onfocus="this.blur()"/> <label
                        for="rd100">充值 <strong>￥</strong><b>100</b></label></li>
                <li><input type="radio" id="rd200" value="200" name="money" onfocus="this.blur()"/> <label for="rd200">充值
                    <strong>￥</strong><b>200</b></label></li>
                <li class="custom"><input type="radio" id="rdOther" name="money" value="0" onfocus="this.blur()"/>
                    <label for="rdOther">其它金额</label><input type="text"
                                                            onkeypress="if(event.keyCode&lt;48 || event.keyCode&gt;57)event.returnValue=false;"
                                                            onpaste="return false;" maxlength="7" class="enter"
                                                            id="txtOtherMoney"></li>
            </ul>
        </div>
        <div class="charge_money_list">
            <p class="title gray01">请选择支付方式</p>
            <p class="leix">银行支付：</p>
            <ul style="border-bottom:1px dashed #e6e7e8;" class="payment">
                <li class="top">
                    <input type="radio" id="bankType1001" name="account" value="1001"><label for="bankType1001"><span
                        class="zh_bank"></span></label>
                    <input type="radio" id="bankType1002" name="account" value="1002"><label for="bankType1002"><span
                        class="gh_bank"></span></label>
                    <input type="radio" id="bankType1003" name="account" value="1003"><label for="bankType1003"><span
                        class="jh_bank"></span></label>
                    <input type="radio" id="bankType1005" name="account" value="1005"><label for="bankType1005"><span
                        class="nh_bank"></span></label>
                </li>

                <li class="top">
                    <input type="radio" id="bankType1004" name="account" value="1004"><label for="bankType1004"><span
                        class="pf_bank"></span></label>
                    <input type="radio" id="bankType1008" name="account" value="1008"><label for="bankType1008"><span
                        class="sf_bank"></span></label>
                    <input type="radio" id="bankType1009" name="account" value="1009"><label for="bankType1009"><span
                        class="xy_bank"></span></label>
                    <input type="radio" id="bankType1032" name="account" value="1032"><label for="bankType1032"><span
                        class="bj_bank"></span></label>
                </li>

                <li class="top">
                    <input type="radio" id="bankType1022" name="account" value="1022"><label for="bankType1022"><span
                        class="gd_bank"></span></label>
                    <input type="radio" id="bankType1006" name="account" value="1006"><label for="bankType1006"><span
                        class="ms_bank"></span></label>
                    <input type="radio" id="bankType1021" name="account" value="1021"><label for="bankType1021"><span
                        class="zx_bank"></span></label>
                    <input type="radio" id="bankType1027" name="account" value="1027"><label for="bankType1027"><span
                        class="gf_bank"></span></label>
                </li>

                <li class="top">
                    <input type="radio" id="bankType1010" name="account" value="1010"><label for="bankType1010"><span
                        class="pa_bank"></span></label>
                    <input type="radio" id="bankType1052" name="account" value="1052"><label for="bankType1052"><span
                        class="zg_bank"></span></label>
                    <input type="radio" id="bankType1020" name="account" value="1020"><label for="bankType1020"><span
                        class="jt_bank"></span></label>
                </li>
            </ul>
            <p class="leix">支付平台支付：</p>
            <ul class="payment">
                <li class="top">
                    <%
                        if (ApplicationListenerImpl.sysConfigureJson.getTenpayStatus() == 0) {
                    %>
                    <input type="radio" id="Tenpay" checked="checked" name="account" value="Tenpay"/>
                    <label for="Tenpay"><span class="cft">财付通</span></label>
                    <%
                        }
                    %>
                    <%
                        if (ApplicationListenerImpl.sysConfigureJson.getAlipayStatus() == 0) {
                    %>
                    <input type="radio" id="Alipay" name="account" value="Alipay"/>
                    <label for="Alipay"><span class="zfb">支付宝</span></label>
                    <%
                        }
                    %>
                    <%
                        if (ApplicationListenerImpl.sysConfigureJson.getYeepayStatus() == 0) {
                    %>
                    <input type="radio" id="Yeepay" name="account" value="Yeepay"/>
                    <label for="Yeepay"><span
                            style="background: url('/Images/yeepay.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">易宝支付</span></label>
                    <%
                        }
                    %>
                    <!--                        <input type="radio" id="Chinabank" name="account" value="Chinabank" />-->
                    <!--                        <label for="Chinabank">-->
                    <!--                            <span class="wy">网银在线</span>-->
                    <!--                        </label>-->
                    <!--                        <input type="radio" id="QuickMoney" name="account" value="QuickMoney" />-->
                    <!--                            <label for="QuickMoney">-->
                    <!--                            <span class="kq">快钱</span>-->
                    <!--                        </label>-->
                    <!--                        <input type="radio" id="Unionpay" name="account" value="Unionpay" />-->
                    <!--                        <label for="Unionpay">-->
                    <!--                            <span class="online">在线支付</span>-->
                    <!--                        </label>-->
                </li>
            </ul>
            <p class="much">应付金额：<span class="yf"><strong>￥</strong><span id="Money">10</span></span></p>
            <h6>
                <form target="_blank" method="post"
                      action="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/balance/goBalance.html"
                      name="toPayForm" id="toPayForm">
                    <input type="hidden" value="" name="payName" id="hidPayName"/>
                    <input type="hidden" value="0" name="bank_type" id="hidPayBank"/>
                    <input type="hidden" value="10" name="moneyCount" id="hidMoney"/>
                    <input type="submit" title="立即充值" value="立即充值" name="submit" class="bluebut imm" id="submit_ok"/>
                    <!--                        <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/userCardRecharge.html"><input type="button" title="卡密充值" value="卡密充值" style="background: none repeat scroll 0 0 #f60;border: 1px solid #f70;color: #fff;" class="bluebut imm" id="cardButton" /></a>-->
                </form>
            </h6>
            <div style="display:none;" id="payAltBox">
                <div class="prompt_box">
                    <p class="pic"><em></em>请您在新开的页面上完成支付！</p>
                    <p class="ts">付款完成之前，请不要关闭本窗口！<br/>完成付款后跟据您的个人情况完成此操作！</p>
                    <ul>
                        <li><a title="查看充值记录" class="look" href="/user/UserBalance.html">查看充值记录 </a></li>
                        <li><a title="重选支付方式" id="btnReSelect" class="look" href="javascript:;">重选支付方式</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
<link type="text/css" rel="stylesheet"
      href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css?data=20131121"/>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js?data=20131121"></script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/userrecharge.js?data=20131121"></script>
<div class="clear_process"></div>
<div class="pageDialogBG" id="pageDialogBG"></div>
<div class="pageDialogBorder" id="pageDialogBorder"></div>
<div class="pageDialog" id="pageDialog">
    <div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    <div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
</div>
</body>
</html>
