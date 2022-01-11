<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>消费记录-消费详情_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/member.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/consumerrecordsdetail.css"/>
</head>

<body>
<div class="member">
    <div class="location">
        <p>
            您的现在的位置：<a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">首页</a> &gt; <a
                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/index.html">我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        </a> &gt; 消费记录-消费详情</p>
    </div>
    <div class="member_center">
        <div class="member_right">
            <div class="member_right_main">
                <h3>消费记录-消费详情</h3>
                <div class="consumer_records">
                    <input type="hidden" value="${id }" id="DetailId"/>
                    <input type="hidden" value="${resultCount }" id="resultCount"/>
                    <p><a href="javascript:history.go(-1);" class="back">返回列表</a></p>
                    <div id="RecordList" class="consumer_records_list">
                        <ul class="gtitle">
                            <li class="time">商品详情</li>
                            <li class="much"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>人次</li>
                            <li class="do"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>金额（元）</li>
                        </ul>
                        <div id="consumer_records_list"></div>
                        <div class="page_nav">
                            <ul class="pageUL" id="pagination"></ul>
                        </div>
                    </div>
                    <p><a href="javascript:history.go(-1);" class="back">返回列表</a></p>
                </div>
            </div>
        </div>

        <div class="member_left">
            <h2>
                我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
            </h2>
            <ul>
                <li class="member_border"><span class="join"></span><a href=""></a></li>
                <li class=""><span class="join"></span><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/index.html">用户中心</a>
                    <p></p></li>
                <li class=""><span class="win"></span><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/UserBuyList.html"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                    记录</a></li>
                <li class=""><span class="share"></span><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/OrderList.html">获得的商品</a>
                </li>
                <li class=""><span class="wdsd"></span><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/PostSingleList.html">我的晒单</a>
                </li>
                <li class="member_border"><span class="join"></span><a href=""></a></li>
                <li class=""><span class="myzh"></span><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/UserBalance.html">余额查询/充值</a>
                </li>
                <li class="click"><span class="yecx"></span><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/ConsumeList.html">消费记录</a>
                </li>
                <li class=""><span class="xfjl"></span><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/RechargeList.html">充值记录</a>
                </li>
                <li class="member_border"><span class="message"></span><a href=""></a></li>
                <li class=""><span class="xgzl"></span><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/Address.html">收货地址</a></li>
                <li class=""><span class="xgzl"></span><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/MemberModify.html">编辑个人资料</a>
                </li>
                <li class=""><span class="xgzl"></span><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/UserPhoto.html">修改头像</a>
                    <p></p></li>
                <li class=""><span class="password"></span><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/UpdatePassWord.html">修改密码</a>
                </li>
                <li class="member_border"><span class="message"></span><a href=""></a></li>

            </ul>
            <div class="member_service">
                <dl>
                    <dt><a class="live800link" href="javascript:;">在线客服</a></dt>
                    <dd>客服热线</dd>
                    <dd class="tel">15201558450</dd>
                </dl>
            </div>
        </div>
        <div style="clear: both">
        </div>
    </div>
</div>
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagination.js"></script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/consumedetail.js"></script>
</body>
</html>
