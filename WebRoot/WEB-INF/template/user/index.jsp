<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layouthome.css?data=20131121"/>
</head>

<body>
<div class="layout980 clearfix">

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
            <h2 class="sid-cur"><a
                    href="index.html"><b></b>我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
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
    <div class="center">
        <div class="per-info">
            <ul>
                <li class="info-mane gray02"><b class="gray01">${user.userName }</b>(
                    <s:if test="user.phone!=null">
                        ${user.phone }
                    </s:if>
                    <s:else>
                        ${user.mail }
                    </s:else>
                    )
                    <br/>
                    <span><a class="blue" target="_blank"
                             href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${user.userId }.html"><s></s> <%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${user.userId }.html</a></span>
                </li>
                <li class="info-class"><em class="gray02">经验值：</em><i class="gray01">${user.experience }</i>
                    <s:if test="user.experience<10000">
                        <span class="class-icon01"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>小将</span>
                        <em class="gray03">(还差${50000-user.experience }经验值即可升级到<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                            少将)</em>
                    </s:if>
                    <s:elseif test="user.experience<50000">
                        <span class="class-icon02"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>少将</span>
                        <em class="gray03">(还差${100000-user.experience }经验值即可升级到<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                            中将)</em>
                    </s:elseif>
                    <s:elseif test="user.experience<100000">
                        <span class="class-icon03"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>中将</span>
                        <em class="gray03">(还差${500000-user.experience }经验值即可升级到<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                            上将)</em>
                    </s:elseif>
                    <s:elseif test="user.experience<500000">
                        <span class="class-icon04"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>上将</span>
                        <em class="gray03">(还差${1000000-user.experience }经验值即可升级到<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                            少校)</em>
                    </s:elseif>
                    <s:elseif test="user.experience<1000000">
                        <span class="class-icon05"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>少校</span>
                        <em class="gray03">(还差${2000000-user.experience }经验值即可升级到<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                            中校)</em>
                    </s:elseif>
                    <s:elseif test="user.experience<2000000">
                        <span class="class-icon06"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>中校</span>
                        <em class="gray03">(还差${5000000-user.experience }经验值即可升级到<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                            上校)</em>
                    </s:elseif>
                    <s:elseif test="user.experience<5000000">
                        <span class="class-icon07"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>上校</span>
                        <em class="gray03">(还差${10000000-user.experience }经验值即可升级到<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                            元帅)</em>
                    </s:elseif>
                </li>
                <li class="account-money"><em class="gray02">帐户余额：</em><span
                        class="money-red"><s></s>${user.balance}</span>&nbsp;&nbsp;<a class="blue" title="充值"
                                                                                      href="/user/UserRecharge.html">充值</a>
                </li>
            </ul>
            <input type="hidden" id="hidUID" value="${user.userId }"/>
            <input name="hidRefTitle" type="hidden" id="hidRefTitle"/>
            <div class="per-invitation gray02" id="divInvited">
                <label>复制链接邀请好友：</label><span><input type="text" onpaste="return false" value="" id="txtInfo"/></span>
                <a class="bluebut" href="javascript:;" id="btnCopy">复制</a>
            </div>

        </div>
        <div class="New-content">
            <br/>
            <div class="R-tit">即将揭晓商品</div>
            <s:iterator value="productList" var="buyHistoryJSONs">
                <div class="scroll-list">
                    <div class="buy-com"><p class="buy-pic"><a
                            href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${buyHistoryJSONs.productId }.html"
                            target="_blank">
                        <img border="0" alt=""
                             src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${buyHistoryJSONs.headImage }"/></a>
                    </p>
                        <div class="buy-rcon"><p class="buy-name"><a class="blue"
                                                                     href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${buyHistoryJSONs.productId }.html"
                                                                     target="_blank">(第${buyHistoryJSONs.productPeriod }期)${buyHistoryJSONs.productName } </a>
                        </p>
                            <p class="buy-money gray02">价值：<span class="money"><i
                                    class="rmb"></i>${buyHistoryJSONs.productPrice }.00</span></p>
                            <div class="Progress-bar">
                                <p title="${fn:replace( fn:substring((buyHistoryJSONs.currentBuyCount/buyHistoryJSONs.productPrice)*100, 0, 3),'.','' )} %">
                                    <span style="width: ${fn:replace( fn:substring((buyHistoryJSONs.currentBuyCount/buyHistoryJSONs.productPrice)*100, 0, 3),'.','' )}%"></span>
                                </p>
                                <ul class="Pro-bar-li">
                                    <li class="P-bar01"><em>${buyHistoryJSONs.currentBuyCount }</em>已参与人次</li>
                                    <li class="P-bar02"><em>${buyHistoryJSONs.productPrice }</em>总需人次</li>
                                    <li class="P-bar03">
                                        <em>${buyHistoryJSONs.productPrice - buyHistoryJSONs.currentBuyCount}</em>剩余人次
                                    </li>
                                </ul>
                            </div>
                            <p><a class="orangebut"
                                  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${buyHistoryJSONs.productId }.html"
                                  target="_blank">马上抢拍</a></p></div>
                    </div>
                </div>
            </s:iterator>
            <div class="msgNoMore" id="divNoMore">没有了</div>
        </div>
    </div>
    <script type="text/javascript" language="javascript">
        /* 格式化回复内容 */
        var FormatHtml = function (str) {
            //return str.replace(/&lt;/ig,'&amp;lt;')
            //.replace(/&gt;/ig,'&amp;gt;')
            return str.replace(/\[(\/)?(b|br)\]/ig, '&lt;$1$2&gt;')
                .replace(/\[s:(\d+)\]/ig, '&lt;img src="http://skin.1ypg.com/Images/$1.gif" alt="" /&gt;')
                .replace(/\[url=([^\]]*)\]([^\[]+)\[\/url\]/ig, '&lt;a href="$1" target="_blank" class="blue"&gt;' + '$2' + '&lt;/a&gt;');
        }
        $("p[name='replystr']").each(function () {
            var _Hander = $(this);//.find('span.about-reply');
            _Hander.html(FormatHtml(_Hander.html()));
        });
    </script>
    <div class="right">

        <!--		    <div class="interest">-->
        <!--			    <div class="R-grtit">-->
        <!--				    <span><a class="blue" href="javascript:;" id="btnSelPeople">换一换</a></span><h3>可能感兴趣的人</h3>-->
        <!--			    </div>-->
        <!--			    <ul class="interest-con" id="peopleList">-->
        <!--			    <li><div class="interest-img"><a href="http://u.1ypg.com/1000879525" title="94*@qq.com" target="_blank"><img border="0" alt="" src="http://faceimg.1ypg.com/UserFace/20131009121544132.jpg"></a></div><div class="interest-info"><p class="interest-name"><a title="94*@qq.com" href="http://u.1ypg.com/1000879525" class="blue" target="_blank">94*@qq.com</a></p><p class="interest-class gray02"><span class="class-icon01"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>小将</span></p><p class="interest-address gray02"><span class=""></span></p></div></li>-->
        <!--			    </ul>-->
        <!--		    </div>-->
        <!--		    <p class="r-line"></p>-->
        <!--		    <div class="groups-shadow clearfix">-->
        <!--			    <div class="R-grtit">-->
        <!--				    <h3>圈子热门话题</h3>-->
        <!--			    </div>-->
        <!--			    <ul class="R-list">-->
        <!--		            <li>-->
        <!--			            <p class="groups-t"><a title="亲们~~~回复我的帖。。。马上就能中奖了额~~~" class="gray" target="_blank" href="http://group.1ypg.com/Topic-731.html">亲们~~~回复我的帖。。。马上就能中奖了额~~~</a></p>-->
        <!--			            <p class="groups-c gray02">活动专区<span class="gray03"> | </span>347条回复</p>-->
        <!--		            </li>-->
        <!--			    </ul>-->
        <!--		    </div>-->
        <!--		    <p class="r-line"></p>-->
        <div class="gg-content">
            <div class="R-grtit"><h3>公告栏</h3></div>
            <ul class="gg-list">
                <s:iterator value="newsList" var="news">
                    <li><span class="point"></span><span class="info"><a title="${news.title }" class="gray"
                                                                         target="_blank"
                                                                         href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/news/${news.newsId }.html">${news.title }</a></span>
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/member.js?data=20131121"></script>
<link type="text/css" rel="stylesheet"
      href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css?data=20131121"/>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js?data=20131121"></script>
<div class="pageDialogBG" id="pageDialogBG"></div>
<div class="pageDialogBorder" id="pageDialogBorder"></div>
<div class="pageDialog" id="pageDialog">
    <div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    <div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
</div>
</body>
</html>
