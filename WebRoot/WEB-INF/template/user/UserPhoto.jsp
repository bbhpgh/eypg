<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>修改头像_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutframe.css?data=20131121"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/userphoto.css?data=20131121"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/imgareaselect-animated.css?data=20131121"/>

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
            <h3 url="" haschild="0" class="sid-icon09 sid-hcur "><a href="/user/MemberModify.html"><b></b>个人设置</a></h3>


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
        <div class="subMenu">
            <a title="个人资料" href="/user/MemberModify.html">个人资料</a>
            <a class="current" title="修改头像" href="/user/UserPhoto.html">修改头像</a>
            <a title="收货地址" href="/user/Address.html">收货地址</a>
            <a title="密码修改" href="/user/UpdatePassWord.html">密码修改</a>
        </div>
        <div class="uploadCon">
            <div class="upLeft">
                <div class="upload">
                    <div style="display: none;">
                        <iframe scrolling="no" src="about:blank" id="upFrame" name="upFrame"></iframe>
                    </div>
                    <form enctype="multipart/form-data" target="upFrame" action="/user/updateFaceFile.html"
                          method="post" name="pageForm" id="pageForm">
                        <input type="hidden" value="${user.userId }" id="userId" name="userId"/>
                        <div class="upImgLoading hidden"><s></s>上传中</div>
                        <div class="localBtn">
                            <div class="btn" id="upImgBtn"></div>
                            <div class="btnFile" id="FileControl" style="top: -5px; left: -100.5px;">
                                <input type="file" style="cursor: pointer;" hidefocus="true" name="myFile" class="file"
                                       title="" id="fuploadFace"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="frameCut">
                    <div class="zhix"></div>
                    <div class="text" id="divImgTip">选择一张本地的图片编辑后上传成为头像</div>
                    <div class="pic">
                        <img width="300" height="300" style="display: none;" id="imgPhoto"
                             src="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/Images/defaultUserFace.png"/>
                    </div>
                </div>
                <span class="tishi gray02">点击图片并拉动以选择需要裁剪的区域</span>
                <div class="savePhoto">
                    <input type="button" title="保存头像" value="保存头像" class="bluebut" id="btnSavePhoto"/>
                    <!-- 起点坐标与裁剪区域大小 -->
                    <input type="hidden" value="" id="cutX"/>
                    <input type="hidden" value="" id="cutY"/>
                    <input type="hidden" value="" id="cutW"/>
                    <input type="hidden" value="" id="cutH"/>
                    <input type="hidden" id="hidPicUrl"/>
                </div>
            </div>
            <div class="upRight">
                <p class="gray02">您上传的头像将会自动生成三种尺寸，请注意头像是否清晰：</p>
                <div class="photo gray02">
                    <ul>
                        <li class="li160">
                            <div class="w160">
                                <div id="w160">
                                    <img width="160" height="160" border="0" id="img160" src="${user.faceImg }"/>
                                </div>
                            </div>
                            160X160 像素
                        </li>
                        <li class="li80">
                            <div class="w80">
                                <div id="w80">
                                    <img width="80" height="80" border="0" id="img80" src="${user.faceImg }"/>
                                </div>
                            </div>
                            80X80 像素
                        </li>
                        <li class="li30">
                            <div class="w30">
                                <div id="w30">
                                    <img width="30" height="30" border="0" id="img30" src="${user.faceImg }"/>
                                </div>
                            </div>
                            30X30 像素
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
<link type="text/css" rel="stylesheet"
      href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css?data=20131121"/>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.imgareaselect.pack.js?data=20131121"></script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/userphoto.js?data=20131121"></script>
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
