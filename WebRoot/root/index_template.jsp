<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title><d:title default="1ypg.com"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="/favicon.ico"/>
    <meta name="description" content="<%=ApplicationListenerImpl.sysConfigureJson.getDescription()%>"/>
    <meta name="keywords" content="<%=ApplicationListenerImpl.sysConfigureJson.getKeyword()%>"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/comm.css"/>
    <script language="javascript" type="text/javascript"
            src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery123.js"></script>
    <d:head/>
</head>

<body<d:getProperty property="body.id" writeEntireProperty="true"/><d:getProperty property="body.class"
                                                                                  writeEntireProperty="true"/><d:getProperty
        property="body.rf" writeEntireProperty="true"/>>
<!-- 头部开始 -->
<div class="header">
    <div class="site_top">
        <div class="head_top">
            <p class="collect"><a id="addSiteFavorite"
                                  href="javascript:;">收藏<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
            </a></p>
            <!-- 		<p class="Ht-qqicon"><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/qqgroup.html" class="gray01">官方QQ群</a></p>-->
            <p class="head_weibo"><a title="新浪微博" class="sina" target="_blank" href="http://weibo.com/1ypg">新浪</a>
                <a title="腾讯微博" class="qq" target="_blank" href="http://t.qq.com/admin-1ypg">腾讯</a></p>
            <ul style="display: block;" class="login_info">
                <li id="logininfo" class="h_wel"><i>您好，欢迎光临！</i><a class="gray01"
                                                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/login/index.html?forward=rego"
                                                                   rel="nofollow">登录</a><span>|</span><a class="gray01"
                                                                                                         href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/register/index.html?forward=rego"
                                                                                                         rel="nofollow">注册</a>
                </li>
                <li class="h_1ypg">
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/index.html?forward=myUser"
                       rel="nofollow">我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName() %><b></b></a>
                    <div style="display:none;" class="h_1ypg_eject">
                        <dl>
                            <dt>
                                <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/index.html?forward=myUser"
                                   rel="nofollow">我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%><i></i></a>
                            </dt>
                            <dd>
                                <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/UserBuyList.html"
                                   rel="nofollow"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</a>
                            </dd>
                            <dd><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/OrderList.html"
                                   rel="nofollow">获得的商品</a></dd>
                            <dd>
                                <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/UserRecharge.html"
                                   rel="nofollow">帐户充值</a></dd>
                            <dd>
                                <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/MemberModify.html"
                                   rel="nofollow">个人设置</a></dd>
                        </dl>
                    </div>
                </li>
                <li style="display:none;" id="liMsgTip" class="h_news">
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/userMessage.html" rel="nofollow">消息<b></b></a>
                    <div style="display:none;" class="h_news_down">
                        <div class="h_news_downT"><a
                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/userMessage.html"
                                rel="nofollow">消息<i></i></a></div>
                        <div class="h_news_downC"></div>
                    </div>
                </li>
                <!-- 			<li class="h_Mobile"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/app/desktop.html" target="_blank" rel="nofollow">桌面版</a></li>-->
                <!-- 			<li class="h_Mobile"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/app/touch.html" target="_blank" rel="nofollow">手机版</a></li>-->
                <li class="h_help"><a
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/whatPaigou.html"
                        target="_blank" rel="nofollow">帮助</a></li>
                <li class="h_inv"><a
                        href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq() %>&site=qq&menu=yes"
                        target="_blank" rel="nofollow">在线客服</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="head_mid">
    <div style="position:relative;" class="head_mid_bg">
        <s style="background:url( <%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/Images/HOT.gif) no-repeat; display:block; width:20px; height:15px; position:absolute; left:505px; top:76px; z-index:50;"></s>
        <h1 class="logo_1ypg"><a title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName() %>"
                                 href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>"
                                 class="logo_1ypg_img"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName() %>
        </a></h1>
        <h1 class="logo_jd"><a title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName() %>"
                               class="logo_jd_img"></a></h1>
        <div class="newbie_guide" id="topJackaroo"></div>
        <div class="head_number"><a target="_blank"
                                    href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/getNewRecord.html">已<span
                id="spBuyCount"
                style="color: rgb(34, 170, 255); background: none repeat scroll 0% 0% rgb(245, 245, 245); opacity: 1;">000000</span>人次参与<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        </a></div>
        <div class="head_search">
            <input type="text" value="输入“iPhone5” 试试" class="init" id="txtSearch"/><input type="button" value="搜索"
                                                                                          class="search_submit"
                                                                                          id="butSearch"/>
            <div class="keySearch">
                <a target="_blank"
                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/hot20/苹果.html">苹果</a>
                <a target="_blank"
                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/hot20/iPhone.html">iPhone</a>
                <a target="_blank"
                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/hot20/手机.html">手机</a>
                <a target="_blank"
                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/hot20/3G手机.html">3G手机</a>
                <a target="_blank"
                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/hot20/宝马.html">宝马</a>
                <a target="_blank"
                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/hot20/单反.html">单反</a>
            </div>
        </div>
    </div>
</div>
<div class="head_nav">
    <div class="nav_center">
        <ul class="nav_list">
            <li class="home-back"><a class="home"
                                     href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">首页</a></li>
            <li id="slideSort" class="sort-all"><a
                    href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20.html"
                    class="sort"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName() %>
            </a></li>
            <li class="buy-all"><a
                    href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/productList/hot20.html"
                    class="sort">商品购买</a></li>
            <li class="new-lottery"><a
                    href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lottery/index.html">最新揭晓</a></li>
            <li class="share"><a
                    href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/share/new20.html">晒单分享</a></li>
            <!-- 			<li class="nav_Cloud"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/autolottery.html">限时揭晓</a></li>-->
            <li class="cooperation"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/referAuth.html"
                                       rel="nofollow">邀请</a></li>
            <li class="what-1ypg"><a class="what-yg"
                                     href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/index.html"
                                     rel="nofollow">新手指南</a></li>
        </ul>
        <div id="sCart" class="mini_mycart">
            <input type="hidden" value="0" id="hidChanged"/>
            <a class="cart" id="sCartNavi"
               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/mycart/index.html" rel="nofollow"><s></s>购物车<span
                    id="sCartTotal">0</span></a><a class="checkout"
                                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/mycart/index.html"
                                                   rel="nofollow">去结算</a>
            <div style="display:none; z-index:99999" id="sCartlist" class="mycart_list">
                <div id="sCartLoading" class="goods_loding"><img border="0"
                                                                 src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/goods_loading.gif"
                                                                 alt=""/>正在加载......
                </div>
                <p id="p1">共计 <span id="sCartTotal2"> 0</span> 件商品 金额总计：<span class="rmbred"
                                                                              id="sCartTotalM">0.00</span></p>
                <h3><input type="button" value="去购物车并结算" id="sGotoCart"/></h3>
            </div>
        </div>
    </div>
</div>
<div class="nav_class">
    <ul>
        <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1001.html">手机数码</a></li>
        <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1002.html">电脑办公</a></li>
        <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1003.html">家用电器</a></li>
        <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1004.html">化妆个护</a></li>
        <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1005.html">钟表首饰</a></li>
        <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1006.html">礼品箱包</a></li>
        <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1007.html">其它商品</a></li>
        <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20.html">全部</a></li>
    </ul>
</div>
<!-- 头部结束 -->
<d:body/>
<!-- 尾部开始 -->
<div class="footer_content">
    <div class="footer_line"></div>
    <div class="footservice">
        <div class="support">
            <dl class="ft-newbie">
                <dt><span>新手指南</span></dt>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/whatPaigou.html">了解<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                </a></dd>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/questionDetail.html">常见问题</a>
                </dd>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/agreement.html">服务协议</a>
                </dd>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/userExperience.html">会员福分经验值</a>
                </dd>
            </dl>
            <dl class="ft-wares">
                <dt><span><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>保障</span></dt>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuinetwo.html"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                    保障体系</a></dd>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuine.html">正品保障</a>
                </dd>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/securepayment.html">安全支付</a>
                </dd>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/suggestion.html">投诉建议</a>
                </dd>
            </dl>
            <dl class="ft-delivery">
                <dt><span>商品配送</span></dt>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/ship.html">商品配送</a>
                </dd>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/deliveryFees.html">配送费用</a>
                </dd>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/prodCheck.html">商品验货与签收</a>
                </dd>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/shiptwo.html">长时间未收到商品</a>
                </dd>
            </dl>
            <dl class="ft-ygjj">
                <dt><span>帮助中心</span></dt>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/paigouRule.html"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                    规则</a></dd>
                <dd><b></b><a target="_blank" rel="nofollow"
                              href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/paigouFlow.html"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                    流程</a></dd>
            </dl>
            <dl class="ft-fwrx">
                <dt><span>客服执线</span></dt>
                <dd class="ft-fwrx-tel"><i><%=ApplicationListenerImpl.sysConfigureJson.getServiceTel() %>
                </i></dd>
                <dd class="ft-fwrx-time">周一至周日 9:00-21:00</dd>
                <dd class="ft-fwrx-service"></dd>
            </dl>
            <dl class="ft-weixin">
                <dt><span>微信扫一扫</span></dt>
                <dd class="ft-weixin-img"><s></s></dd>
                <dd class="gray02">关注<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>免费抽大奖</dd>
            </dl>
        </div>
    </div>
    <div class="service-promise">
        <ul>
            <li class="sp-fair"><a target="_blank" rel="nofollow"
                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuinetwo.html"><span>100%公平公正</span></a>
            </li>
            <li class="sp-wares"><a target="_blank" rel="nofollow"
                                    href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuine.html"><span>100%正品保障</span></a>
            </li>
            <li class="sp-free-delivery"><a target="_blank" rel="nofollow"
                                            href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/deliveryFees.html"><span>全国免费配送</span></a>
            </li>
            <li class="sp-business service-promise-border-r0"><a rel="nofollow"><span>商务合作</span></a></li>
        </ul>
    </div>
</div>
<div class="footer">
    <div class="footer_links">
        <a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">首页</a>
        <b></b>
        <a rel="nofollow"
           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/index.html">关于<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        </a>
        <b></b>
        <a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/privacy.html">隐私声明</a>
        <b></b>
        <a rel="nofollow"
           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/jobs.html">加入<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        </a>
        <b></b>
        <a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/Links.html">友情链接</a>
    </div>
    <div class="copyright">Copyright &copy; 2011 - 2013,
        版权所有  <%=ApplicationListenerImpl.sysConfigureJson.getDomain()%>  <%=ApplicationListenerImpl.sysConfigureJson.getIcp()%>
    </div>
    <div style="width:512px;" class="footer_icon">
        <ul>
            <li class="fi_ectrustchina"><span></span></li>
            <li class="fi_cnnic"><span></span></li>
            <li class="fi_pingan"><span></span></li>
            <li class="fi_alipay"><span></span></li>
        </ul>
    </div>
</div>
<div style="display: none;" class="quickBack" id="divRighTool">
    <dl class="quick_But">
        <dd style="display: none;" class="quick_cart"><a class="quick_cartA" target="_blank" href="/mycart/index.html"
                                                         id="btnMyCart"><b>购物车</b><s></s></a>
            <div class="Roll_mycart" id="rCartlist" style="display:none;">
                <ul style="display:none;"></ul>
                <div id="rCartLoading" class="quick_goods_loding" style="z-index:99999;"><img border="0"
                                                                                              src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/goods_loading.gif"
                                                                                              alt=""/>正在努力加载中......
                </div>
                <p style="display:none;" id="p1"></p>
                <h3 style="display:none;"><input type="button" id="rGotoCart" value="去购物车结算"/></h3>
            </div>
        </dd>
        <dd class="quick_service"><a class="quick_serviceA" target="_blank"
                                     href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq() %>&site=qq&menu=yes"
                                     id="btnRigQQ"><b>在线客服</b><s></s></a></dd>
        <dd class="quick_Collection"><a class="quick_CollectionA" href="javascript:;"
                                        id="btnFavorite"><b>收藏本站</b><s></s></a></dd>
        <dd class="quick_Return"><a class="quick_ReturnA" href="javascript:;" id="btnGotoTop"><b>返回顶部</b><s></s></a>
        </dd>
    </dl>
</div>
<div style="right: 59.5px;display:none;" class="fixed_wx_r" id="divWechat">
    <a class="fixed_wx_close" href="javascript:void(0);"></a>
    <div class="ft-weixin-img"><s></s></div>
    <p>关注微信免费抽大奖</p>
</div>
<!-- 尾部结束 -->
<div style="display: none;">
    <script type="text/javascript">
        var $img = "<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>";
        var $skin = "<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>";
        var $www = "<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>";
        var $domain = "<%=ApplicationListenerImpl.sysConfigureJson.getDomain()%>";
        var $shortName = "<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>";
    </script>
    <script language="javascript" type="text/javascript"
            src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/bottomfun.js?data=20131121"></script>
    <script type="text/javascript">
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-32269333-1']);
        _gaq.push(['_setDomainName', '1ypg.com']);
        _gaq.push(['_addOrganic', 'soso', 'w']);
        _gaq.push(['_addOrganic', 'sogou', 'query']);
        _gaq.push(['_addOrganic', 'youdao', 'q']);
        _gaq.push(['_addOrganic', 'baidu', 'word']);
        _gaq.push(['_addOrganic', 'baidu', 'q1']);
        _gaq.push(['_addOrganic', '360', 'q']);
        _gaq.push(['_trackPageview']);
        var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
        document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F874caa3b9536373fecc72345e5e0ef19' type='text/javascript'%3E%3C/script%3E"));
    </script>
</div>
</body>
</html>
