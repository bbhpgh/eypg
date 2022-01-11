<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>
        邀请有礼 <%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/referrals.css"/>
</head>

<body class="cooperation">
<div class="referrals_box">
    <div class="W-left fl">
        <h4><i></i></h4>
        <div class="wqyl">
            <div class="wqylL">
                <dl><img src="http://skin.1yyg.com/Info/Invitation/Images/pic_03.gif"/></dl>
                <dl>
                    <h3>一重礼 50福分</h3>
                    您邀请的每一位好友成功参与<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>，<br/>即可获得50福分(福分能当钱花哦！)
                </dl>
            </div>
            <div class="wqylR">
                <dl><img src="http://skin.1yyg.com/Info/Invitation/Images/pic_05.gif"/></dl>
                <dl>
                    <h3>二重礼 8%的现金提成</h3>
                    经您邀请的所有好友，成功参与<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %><br/>后，您都可以获得8%的现金奖<br/>赏，并且永久有效。
                </dl>
            </div>
        </div>

        <c:choose>
            <c:when test="${uid==null}">
                <div class="login_reg">
                    请先<a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/login/index.html?forward=auth">登录</a>或者<a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/register/index.html?forward=auth">注册</a>，获取您的专属邀请链接。
                </div>
                <div class="login_button">
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/login/index.html?forward=auth">立即登录邀请好友</a>
                </div>
            </c:when>
            <c:otherwise>
                <input type="hidden" id="hidUID" value="${uid }"/>
                <input name="hidRefTitle" type="hidden" id="hidRefTitle"/>
                <div class="Invitation-t">专用邀请链接</div>
                <div class="Invitation-C1">
                    <p class="fs14">这是您的专属邀请链接，请通过 MSN 或 QQ 发送给您的好友</p>
                    <div class="">
                        <textarea class="textarea" id="copyShareText" name="copyShareText"></textarea>
                    </div>
                    <input type="button" title="复制" value="复 制" id="btnCopy"/>
                </div>
                <div class="Invitation-C2">
                    <p class="fs14">通过分享方式邀请好友，立即分享到您的QQ、MSN、人人、开心、微博上的朋友吧！</p>
                    <div class="bdshare_t bds_tools get-codes-bdshare" id="bdshare">
                        <a class="bds_qzone qqkj" href="#" title="分享到QQ空间">QQ空间</a>
                        <a class="bds_msn msn" href="#" title="分享到Myspace">MSN</a>
                        <a class="bds_fx feixin" href="#" title="分享到飞信">飞信</a>
                        <a class="bds_taobao tjh" href="#" title="分享到淘宝">淘宝</a>
                        <a class="bds_renren rrw" href="#" title="分享到人人网">人人网</a>
                        <a class="bds_kaixin001 kxw" href="#" title="分享到开心网">开心网</a>
                        <a class="bds_douban db" href="#" title="分享到豆瓣网">豆瓣网</a>
                        <a class="bds_tsina xlwb" href="#" title="分享到新浪微博">新浪微博</a>
                        <a class="bds_tqq txwb" href="#" title="分享到腾讯微博">腾讯微博</a>
                        <a class="bds_tsohu shwb" href="#" title="分享到搜狐微博">搜狐微博</a>
                        <span class="bds_more">更多</span>
                    </div>
                </div>
                <div class="Invitation-C3">
                    <p class="fs14">您可以直接通过发送邮件邀请好友：</p>
                    <ul>
                        <li><a class="M126" target="_blank" href="http://mail.126.com"></a></li>
                        <li><a class="M163" target="_blank" href="http://mail.163.com"></a></li>
                        <li><a class="Mmsn" target="_blank" href="http://login.live.com"></a></li>
                        <li><a class="Msohu" target="_blank" href="http://mail.sohu.com"></a></li>
                        <li><a class="Mgmail" target="_blank" href="https://mail.google.com"></a></li>
                        <li><a class="Msina" target="_blank" href="http://mail.sina.com.cn"></a></li>
                        <li><a class="Myahoo" target="_blank" href="http://mail.cn.yahoo.com"></a></li>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
    <div class="W-right fr">
        <h4>温馨提示</h4>
        <div class="rig_con">
            <ul>
                <li><h5>1、在哪里可以看到我的佣金？</h5>
                    <p>在【<a target="_blank"
                            href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/index.html?forward=myUser">我的个人中心</a>】的【<a
                            target="_blank"
                            href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/CommissionQuery.html">佣金明细</a>】里可看到您的每次返现记录。佣金满100及以上可申请提现，任何时候都可充值到<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                        帐户参与<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>。</p></li>
                <li><h5>2、哪些情况会导致佣金失效？</h5>
                    <p>借助软件及其他平台，恶意获取佣金，一经查实，扣除一切佣金且封号。</p></li>
                <li><h5>3、自己邀请自己也能获得佣金吗？</h5>
                    <p>不可以。我们会人工核查，对于查实的作弊行为，扣除一切佣金，取消邀请佣金的资格并清除您的账户。</p></li>
                <li class="none"><h5>4、如何知道我有没有邀请成功</h5>
                    <p>您可以在【<a target="_blank"
                               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/index.html?forward=myUser">我的个人中心</a>】的【<a
                            target="_blank"
                            href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/InvitedList.html">成功邀请的会员</a>】里面查看。
                    </p></li>
            </ul>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/referauth.js"></script>
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
