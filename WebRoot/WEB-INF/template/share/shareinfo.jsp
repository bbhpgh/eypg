<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>晒单分享_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/sharedetail.css"/>
</head>
<body class="share">
<div class="Current_nav"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">首页</a> &gt; <a
        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/share/new20.html">晒单分享</a> &gt; 晒单详请
</div>
<div id="loadingPicBlock" class="share_box">
    <div class="share_box_left" id="DCMainLeft">
        <div class="share_main">
            <!--用户晒单部分-->
            <div class="share_title">
                <h3>${shareInfoPro.shareTitle }</h3>
                <div class="share_time">
                    晒单时间：<span>${shareInfoPro.shareDate}</span></div>
            </div>
            <div class="share_goods">
                <div class="share-get">
                    <c:if test="${shareInfoPro.reward==1}">
                        <i></i>
                    </c:if>
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${shareInfoPro.userId }.html"
                       target="_blank" class="fl-img"><img
                            src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${shareInfoPro.winUserFace }"/></a>
                    <div class="share-getinfo">
                        <p class="getinfo-name">幸运获得者：<a
                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${shareInfoPro.userId }.html"
                                target="_blank" class="blue Fb">${shareInfoPro.winUserName }</a></p>
                        <p>总共<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>：<b
                                class="orange">${shareInfoPro.buyNumberCount}</b>人次</p>
                        <!--                            <p>幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码：${shareInfoPro.winRandomNumber }</p>-->
                        <p>揭晓时间：${shareInfoPro.announcedTime }</p>
                    </div>
                </div>
                <div class="share-Conduct">
                    <div class="arrow arrow_Rleft">
                        <em>◆</em><span>◆</span></div>
                    <a target="_blank"
                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${shareInfoPro.productId }.html"
                       class="fl-img"><img border="0" alt="${shareInfoPro.productTitle }"
                                           src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${shareInfoPro.productImg }"/></a>
                    <div class="share-getinfo">
                        <p class="getinfo-title"><a target="_blank"
                                                    href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${shareInfoPro.productId }.html"
                                                    class="gray01">(第${shareInfoPro.productPeriod }期)${shareInfoPro.productName }</a>
                        </p>
                        <p>价值：￥${shareInfoPro.productPrice }.00</p>
                        <p id="GoToBuy"></p>
                    </div>
                </div>
            </div>
            <div class="share_content">
                <p class="content-pad">${shareInfoPro.shareContent }</p>
                <s:iterator value="shareimageList" var="shareImages">
                    <p><img class="scrollLoading"
                            data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/Big/${shareImages.images }"
                            src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/goods_loading.gif"/>
                    </p>
                </s:iterator>
            </div>
            <!--用户晒单部分结束-->
            <!-- 分享按钮 -->
            <div class="mood">
                <div class="moodwm">
                    <div class="moodm hidden" style="display: block;">
                        <span id="emHits" class="smile"><i></i><b>羡慕嫉妒恨(<em>${shareInfoPro.upCount }</em>)</b></span>
                        <span class="much"> <i></i>评论(<em id="emReplyCount">${shareInfoPro.replyCount }</em>)</span>
                    </div>
                    <div class="share">
                        <span class="fen">分享到：</span>
                        <!-- Baidu Button BEGIN -->
                        <div class="bdshare_t bds_tools get-codes-bdshare" id="bdshare">
                            <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt" target="_blank"><img
                                    src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/jiathis.gif"
                                    border="0"/></a>
                        </div>
                        <!-- Baidu Button END -->
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" value="${resultCount }" id="hidReplyCount" name="hidReplyCount"/>
        <input type="hidden" id="resultCount" value="${resultCount }"/>
        <input type="hidden" id="shareId" value="${shareInfoPro.shareId }"/>
        <input type="hidden" id="productId" value="${shareInfoPro.productId }"/>
        <div class="qcomment_bottom_reply clearfix" id="bottomComment">
            <div class="Comment_Reply clearfix">
                <div class="Comment-pic">
                    <s:if test="user==null">
                        <img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/defaultUserFace.png"
                             name="imgUserPhoto"/>
                    </s:if>
                    <s:else>
                        <img src="${user.faceImg }" name="imgUserPhoto"/>
                    </s:else>
                </div>
                <div class="Comment_form">
                    <div style="display:none;" class="Comment-name" id="divCommTo"></div>
                    <div class="Comment_textbox">
                        <div class="reply_form">
                            <div class="reply_textbox">
                                <textarea class="hidden Comment-txt" name="replyTA" id="replyContent"></textarea>
                                <div class="reply_login" id="notLogin">
                                    请您<a href="javascript:;" name="replyLoginBtn" id="linklogin">登录</a>或<a
                                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/register/index.html?forward=rego">注册</a>后再回复评论
                                </div>
                            </div>
                        </div>
                        <div class="comment_box_reply_send">
                            <div class="qcomment_reply_send_left">您还可以输入<span id="wordNumber">150</span>个字！</div>
                            <div class="qcomment_reply_send_right">
                                <input type="hidden" id="hidReplyContent"/>
                                <input type="button" class="reply_unbotton" value="提交评论" id="btnSubmit"
                                       name="btnSubmit"/>
                            </div>
                        </div>
                    </div>
                    <!--            <div class="Comment_textbox">-->
                    <!--	            <div class="reply_form">-->
                    <!--	            	<textarea class="hidden Comment-txt" name="replyTA" id="replyContent"></textarea>-->
                    <!--	            </div>-->
                    <!--            	<div runat="server" class="Comment_login" name="replyLogin" id="notLogin">请您<a name="replyLoginBtn" id="linklogin" class="blue" href="javascript:;">登录</a>或<a class="blue" href="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/register/index.html?forward=rego">注册</a>后再回复评论</div>-->
                    <!--             	<div class="comment_box_reply_send">-->
                    <!--                    <div class="qcomment_reply_send_left">您还可以输入<span id="wordNumber">150</span>个字！</div>-->
                    <!--                    <div class="qcomment_reply_send_right">-->
                    <!--                        <input type="hidden" id="hidReplyContent"/>-->
                    <!--                        <input type="button" class="reply_unbotton" value="提交评论" id="btnSubmit" name="btnSubmit"/>-->
                    <!--                    </div>-->
                    <!--                </div>-->
                    <!--            </div>-->
                </div>
            </div>
        </div>
        <div style="display: block;" id="commentMain" class="qcomment_main">
            <div class="qcomment_main_topline">
            </div>
            <div class="qcomment_content">
                <div class="qcomment_content_tit">
                    <h4>大家都在说<em>...</em></h4>
                </div>
                <div id="commentItems"></div>
                <div class="pages">
                    <ul id="pagination" class="pageULEx"></ul>
                </div>
            </div>
        </div>
    </div>
    <!--晒单左侧结束-->
    <!--晒单右侧-->
    <div class="Comment_share">
        <h4>最新晒单</h4>
        <s:iterator value="ShareJSONList" var="ShareJSONs">
            <div class="New-single"><p class="New-single-time"><a
                    href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ShareJSONs.userId }.html"
                    target="_blank" class="blue">${ShareJSONs.userName }</a>${ShareJSONs.shareDate }</p>
                <p class="New-single-C"><a target="_blank"
                                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html">${ShareJSONs.shareContent }</a>
                </p>
                <div class="New-singleImg">
                    <div class="arrow arrow_Rleft"><em>◆</em></div>
                    <a target="_blank"
                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html">
                        <img border="0" alt="${ShareJSONs.shareTitle }"
                             src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/Small/${ShareJSONs.shareImages }"/></a>
                </div>
            </div>
        </s:iterator>
    </div>
</div>

<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/postdetail.js"></script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagination.js"></script>
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.scrollloading-min.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css"/>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
<div class="pageDialogBG" id="pageDialogBG"></div>
<div class="pageDialogBorder" id="pageDialogBorder"></div>
<div class="pageDialog" id="pageDialog">
    <div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    <div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
</div>
</body>
</html>
