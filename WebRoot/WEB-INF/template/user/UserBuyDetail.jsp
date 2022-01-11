<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        详情_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutframe.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutrecords.css"/>
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
            <a class="blue" href="/user/UserPhoto.html">修改头像</a><a class="blue fr"
                                                                   href="/user/MemberModify.html">编辑资料</a>
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
                <li class="sid-cur"><a
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
            </ul>
            <li class=""><a href="/user/userCardRecharge.html">实卡充值</a></li>
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
        <div class="member-t">
            <h2><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</h2>
        </div>
        <div class="yg_record_goods">
            <a title="${buyHistoryJSON.productName }" class="fl-img" target="_blank"
               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${buyHistoryJSON.productId }.html"><img
                    src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${buyHistoryJSON.productImg }"/></a>
            <div class="yg_record_r">
                <li><span class="orange">(第${buyHistoryJSON.productPeriod }期)</span> <a class="blue"
                                                                                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${buyHistoryJSON.productId }.html"
                                                                                        target="_blank">${buyHistoryJSON.productName }</a>
                </li>
                <c:choose>
                    <c:when test="${buyHistoryJSON.buyStatus==1}">
                        <li class="gray02">本期商品<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>已结束 【获得者：<a
                                class="blue" target="_blank"
                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${buyHistoryJSON.winUserId }.html">${buyHistoryJSON.winUser }</a>
                            幸运编号：${buyHistoryJSON.winId } 揭晓时间：${buyHistoryJSON.winDate }】
                        </li>
                        <li><a class="bluebut"
                               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${buyHistoryJSON.productId }.html"
                               target="_blank">查看详情</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a class="bluebut"
                               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${buyHistoryJSON.productId }.html"
                               target="_blank">查看详情</a></li>
                    </c:otherwise>
                </c:choose>
                <li></li>
            </div>
        </div>
        <input type="hidden" value="${resultCount }" id="resultCount"/>
        <input type="hidden" value="${buyHistoryJSON.productId }" id="productId"/>
        <input type="hidden" value="${userId }" id="userId"/>
        <div class="goods-tit gray02">
            <p class="fl">
                本期商品您总共<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %><span>${buyHistoryJSON.buyCount }</span>人次
                拥有<span>${buyHistoryJSON.buyCount }</span>个<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                码</p><a class="blue fr" href="UserBuyList.html">&lt;&lt; 返回列表</a></div>
        <div id="userbuylist" class="list-tab goodsList">

        </div>
        <div class="page_nav" id="divPageNav">
            <div class="page_nav">
                <ul class="pageULEx" id="pagination"></ul>
            </div>
        </div>
        <div class="goods-tit gray02">
            <a class="blue fr" href="UserBuyList.html">&lt;&lt; 返回列表</a></div>
    </div>

</div>
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagination.js"></script>
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/userbuydetail.js"></script>
</body>
</html>
