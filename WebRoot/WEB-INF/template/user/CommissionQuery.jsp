<%@ page language="java" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        中心_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutframe.css?data=20131121"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutinvitation.css?data=20131121"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/dateinput.css?data=20131121"/>
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
                <li class="sid-cur"><a href="/user/CommissionQuery.html">佣金明细</a></li>
                <li class=""><a href="/user/ApplyMention.html">提现申请</a></li>
                <li class=""><a href="/user/MentionList.html">提现记录</a></li>
            </ul>
            <p class="sid-line"></p>
            <h3 url="/user/UserBalance.html" haschild="1" class="sid-icon05 "><a href="javascript:;"><b></b>账户管理 <s
                    title="收起"></s></a></h3>
            <ul>
                <li class=""><a href="/user/UserBalance.html">账户明细</a></li>
                <li class=""><a href="/user/UserRecharge.html">账户充值</a></li>
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
        <input type="hidden" value="${userId }" id="userId"/>
        <input type="hidden" value="${resultCount }" id="resultCount"/>
        <div class="member-t"><h2>佣金明细</h2></div>
        <div class="total">
            <dt>
            </dt>
            <dd>累计收入：<b class="orange">${user.commissionCount }</b> 元</dd>
            <dd>累计(提现/充值)：<b class="orange">${user.commissionMention }</b> 元</dd>
            <dd>佣金余额：<b class="orange">${user.commissionBalance}</b> 元</dd>
            <dd><a class="bluebut" title="申请提现" href="/user/ApplyMention.html">申请提现</a><a class="orangebut"
                                                                                          title="充值到<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>账户"
                                                                                          href="/user/ApplyMention.html?t=">充值到<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                账户</a></dd>
            <dd class="gray02">佣金余额可实时充值到您的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                帐户，满100元时才可申请提现。
            </dd>
        </div>
        <div class="record-tit">
            <div class="record-tab">
                <a class="record-cur" href="javascript:;" name="0">全部</a>
                <a href="javascript:;" name="1">今天</a>
                <a href="javascript:;" name="2">本周</a>
                <a href="javascript:;" name="3">本月</a>
                <a href="javascript:;" name="4">最近三个月</a>
            </div>
            <div class="record-time">
                选择时间段：
                <input type="text" value="" id="txtMisTime" class="date"/>
                <input type="text" value="" id="txtMaxTime" class="date"/>
                <input type="button" class="graybut" value="搜索" id="btnSearch"/>
            </div>
        </div>
        <div class="list-tab commission gray02" id="divCommissionList">
        </div>
        <div class="page_nav" id="divPageNav">
            <div class="page_nav">
                <ul class="pageULEx" id="pagination"></ul>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagination.js?data=20131121"></script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/commissionquery.js?data=20131121"></script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/date.js?data=20131121"></script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/dateinput.js?data=20131121"></script>
</body>
</html>
