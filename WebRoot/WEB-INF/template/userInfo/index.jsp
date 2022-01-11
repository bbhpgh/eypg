<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>${userName }的个人主页_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/userinfo.css"/>
</head>

<body>
<div class="layout980 clearfix">

    <div class="sidebar">
        <div class="head"><a
                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${user.userId }.html"><img width="160"
                                                                                                             height="160"
                                                                                                             border="0"
                                                                                                             alt="${userName }"
                                                                                                             src="${user.faceImg }"/></a>
        </div>
        <!--    <div class="head-but" id="pageLeft_divBtn"><a class="button01" href="javascript:;" id="btnAddFriend"><s></s>加好友</a><a class="button02 fr" href="javascript:;" id="btnSendMsg"><i></i>发私信</a></div>-->

        <div class="Pop-news" id="divMsgBox">
            <div class="Pop-Con">
                <div class="arrow arrow_left"><em>◆</em><span>◆</span></div>
                <div class="Close"><a href="javascript:;"></a></div>
                <div class="Comment_form"><textarea id="txtPrivateMsg"></textarea></div>
            </div>
        </div>
        <!--最近访客-->
        <h4 class="sid-tit Fb gray01" id="h4Guest">最近访客</h4>
        <div id="visitors"></div>
    </div>
    <div class="content clearfix">
        <div class="per-info">
            <ul>
                <li class="info-mane gray02"><b class="gray01">${userName }</b></li>
                <li class="info-address"><span><a class="blue"
                                                  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${user.userId }.html"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${user.userId }.html</a></span>
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
                <li class="info-intro gray02">${user.signature }</li>
            </ul>
        </div>
        <input type="hidden" value="${user.userId }" id="hidPageUserID"/>
        <input type="hidden" value="0" id="hidIsLogin"/>
        <input type="hidden" value="${resultCount }" id="resultCount"/>
        <div class="subnav">
            <ul>
                <li class="poa-4"><a class="cur">TA的主页<s></s></a></li>
                <li class="poa-4"><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/${user.userId }-userBuy.html"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                    记录<s></s></a></li>
                <li class="poa-4"><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/${user.userId }-userRaffle.html">获得的商品<s></s></a>
                </li>
                <li class="poa-2"><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/${user.userId }-userPost.html">晒单<s></s></a>
                </li>
                <!--<li class="poa-4"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/${user.userId }-userGroup.html">加入的圈子<s></s></a></li>-->
                <!--<li class="poa-2"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/${user.userId }-userTopic.html">话题<s></s></a></li>-->
                <li class="poa-2"><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/${user.userId }-userFriends.html">好友<s></s></a>
                </li>
            </ul>
        </div>
        <div class="New-content" id="divInfo0">
            <s:iterator value="buyHistoryJSONList" var="buyHistoryJSONs">
                <c:choose>
                    <c:when test="${buyHistoryJSONs.buyStatus==0}">
                        <div class="scroll-list">
                            <div class="R-tit">
                                <span class="tit-name gray01">${buyHistoryJSONs.winUser}</span><i>在${buyHistoryJSONs.buyTime }<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>了</i><span
                                    class="tit blue"><a class="blue"
                                                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${buyHistoryJSONs.productId }.html"
                                                        title="${buyHistoryJSONs.productName }"
                                                        target="_blank">${fn:substring(buyHistoryJSONs.productName,0,45) }</a>
								</span>
                            </div>
                            <div class="buy-com">
                                <p class="buy-pic">
                                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${buyHistoryJSONs.productId }.html"
                                       target="_blank"><img border="0" alt="${buyHistoryJSONs.productName }"
                                                            src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${buyHistoryJSONs.productImg }"/></a>
                                </p>
                                <div class="buy-rcon">
                                    <p class="buy-name">
                                        <a class="blue"
                                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${buyHistoryJSONs.productId }.html"
                                           rel="nofollow"
                                           target="_blank">(第${buyHistoryJSONs.productPeriod }期)${buyHistoryJSONs.productName }</a>
                                    </p>
                                    <p class="buy-money gray02">
                                        价值：<span class="money"><i
                                            class="rmb"></i>${buyHistoryJSONs.productPrice }.00</span>
                                    </p>
                                    <div class="Progress-bar">
                                        <p title="${fn:replace( fn:substring((buyHistoryJSONs.spellbuyCount/buyHistoryJSONs.productPrice)*100, 0, 2),'.','' )} %">
                                            <span style="width: ${fn:replace( fn:substring((buyHistoryJSONs.spellbuyCount/buyHistoryJSONs.productPrice)*100, 0, 2),'.','' )}%"></span>
                                        </p>
                                        <ul class="Pro-bar-li">
                                            <li class="P-bar01"><em>${buyHistoryJSONs.spellbuyCount }</em>已参与人次</li>
                                            <li class="P-bar02"><em>${buyHistoryJSONs.productPrice }</em>总需人次</li>
                                            <li class="P-bar03">
                                                <em>${buyHistoryJSONs.productPrice - buyHistoryJSONs.spellbuyCount}</em>剩余人次
                                            </li>
                                        </ul>
                                    </div>
                                    <p>
                                        <a class="button03"
                                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${buyHistoryJSONs.productId }.html"
                                           target="_blank">跟随<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                                        </a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${buyHistoryJSONs.winUserId==user.userId}">
                                <div class="scroll-list">
                                    <div class="R-tit"><span
                                            class="tit-name gray01">${buyHistoryJSONs.winUser}</span><i>在${buyHistoryJSONs.buyTime }获得了</i><span
                                            class="tit-con blue"><a class="blue"
                                                                    href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${buyHistoryJSONs.productId }.html"
                                                                    target="_blank">${buyHistoryJSONs.productName }</a></span>
                                    </div>
                                    <div class="buy-com">
                                        <p class="buy-pic"><a
                                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${buyHistoryJSONs.productId }.html"
                                                target="_blank" rel="nofollow"><img border="0"
                                                                                    alt="${buyHistoryJSONs.productName }"
                                                                                    src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${buyHistoryJSONs.productImg }"/></a>
                                        </p>
                                        <div class="buy-rcon">
                                            <p class="buy-name"><a class="blue"
                                                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${buyHistoryJSONs.productId }.html"
                                                                   rel="nofollow"
                                                                   target="_blank">${buyHistoryJSONs.productName }</a>
                                            </p>
                                            <p class="buy-money gray02">价值：<span class="money"><i
                                                    class="rmb"></i>${buyHistoryJSONs.productPrice }.00</span></p>
                                            <p class="buy-code gray02">
                                                幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码：<span
                                                    class="orange">${buyHistoryJSONs.winId}</span></p>
                                            <p class="buy-time gray02">揭晓时间：${buyHistoryJSONs.winDate }</p>
                                            <p><a class="button03"
                                                  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${buyHistoryJSONs.productId }.html"
                                                  rel="nofollow" target="_blank">去看看</a></p>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="scroll-list">
                                    <div class="R-tit"><span
                                            class="tit-name gray01">${userName}</span><i>在${buyHistoryJSONs.buyTime }<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>了</i><span
                                            class="tit-con blue"><a class="blue"
                                                                    href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${buyHistoryJSONs.productId }.html"
                                                                    target="_blank">${buyHistoryJSONs.productName }</a></span>
                                    </div>
                                    <div class="buy-com">
                                        <p class="buy-pic"><a
                                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${buyHistoryJSONs.productId }.html"
                                                target="_blank" rel="nofollow"><img border="0"
                                                                                    alt="${buyHistoryJSONs.productName }"
                                                                                    src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${buyHistoryJSONs.productImg }"/></a>
                                        </p>
                                        <div class="buy-rcon">
                                            <p class="buy-name"><a class="blue"
                                                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${buyHistoryJSONs.productId }.html"
                                                                   rel="nofollow"
                                                                   target="_blank">${buyHistoryJSONs.productName }</a>
                                            </p>
                                            <p class="buy-money gray02">价值：<span class="money"><i
                                                    class="rmb"></i>${buyHistoryJSONs.productPrice }.00</span></p>
                                            <p class="buy-code gray02">
                                                幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码：<span
                                                    class="orange">${buyHistoryJSONs.winId}</span></p>
                                            <p class="buy-time gray02">揭晓时间：${buyHistoryJSONs.winDate }</p>
                                            <p><a class="button03"
                                                  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${buyHistoryJSONs.productId }.html"
                                                  rel="nofollow" target="_blank">去看看</a></p>
                                        </div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </s:iterator>

            <div class="msgNoMore" id="divNoMore">●●●</div>

        </div>
    </div>
</div>
<script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/userinfo.js"></script>
</body>
</html>
