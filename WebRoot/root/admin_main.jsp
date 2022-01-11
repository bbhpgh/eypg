<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title><d:title default="1ypg.com"/></title>
    <link href="/css/comm.css" rel="stylesheet"/>
    <link href="/admin_css/bootstrap.css" rel="stylesheet"/>
    <link href="/admin_css/style.css" rel="stylesheet"/>
    <d:head/>
</head>
<body<d:getProperty property="body.id" writeEntireProperty="true"/><d:getProperty property="body.name"
                                                                                  writeEntireProperty="true"/><d:getProperty
        property="body.class" writeEntireProperty="true"/><d:getProperty property="body.rf"
                                                                         writeEntireProperty="true"/>>
<!--<div align="center">-->
<!--<a href="/admin_back/toAddProduct.action">添加商品</a> | -->
<!--<a href="/admin_back/announcedProduct.action">已揭晓商品管理</a> |  -->
<!--<a href="/admin_back/index.action?id=hot20">商品期数管理</a> | -->
<!--<a href="/admin_back/productList.action">商品管理</a> |   -->
<!--<a href="/admin_back/crawl.action">商品抓取</a> |-->
<!--<a href="/admin_back/newsList.action">新闻管理</a> |    -->
<!--<a href="/admin_back/latestList.action">揭晓管理</a> |      -->
<!--<a href="/admin_back/shareList.action?typeId=hot20">晒单管理</a> |      -->
<!--<input value="返回" type="button" onclick="javascript:history.go(-1)"/>-->
<!--</div>-->
<div class="navbar navbar-default navbar-fixed-top" id="head-nav">
    <div class="container-fluid">
        <div class="navbar-header">
            <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle" type="button">
                <span class="fa fa-gear"></span>
            </button>
            <a href="#" class="navbar-brand"
               style="overflow: hidden; height: 34px;"><span><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>管理中心</span></a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">首页</a></li>
                <li><a href="#about">关于我们</a></li>
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">快捷操作<b class="caret"></b></a>
                    <ul class="dropdown-menu col-menu-2">
                        <li class="col-sm-6 no-padding">
                            <ul>
                                <li class="dropdown-header"><i class="fa fa-group"></i>会员管理</li>
                                <li><a href="/admin_back/userListAll.action">会员列表</a></li>
                                <li class="dropdown-header"><i class="fa fa-gear"></i>配置信息</li>
                                <li><a href="/admin_back/main.action">系统信息</a></li>
                                <li><a href="/admin_back/sysInfo.action">系统配置</a></li>
                            </ul>
                        </li>
                        <li class="col-sm-6 no-padding">
                            <ul>
                                <li class="dropdown-header"><i class="fa fa-legal"></i>商品管理</li>
                                <li><a href="/admin_back/toAddProduct.action">添加商品</a></li>
                                <li><a href="/admin_back/productList.action">商品列表</a></li>
                                <li><a href="/admin_back/index.action?id=hot20">商品期数管理</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right user-nav">
                <li class="dropdown profile_menu">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#"><span>admin</span> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>" target="_blank">网站首页</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="/admin_back/logOut.action">登出</a></li>
                    </ul>
                </li>
            </ul>
        </div><!--/.nav-collapse animate-collapse -->
    </div>
</div>
<!-- 顶部结束 -->
<div id="cl-wrapper" class="fixed-menu">
    <!-- 左侧开始 -->
    <div data-intro="&lt;strong&gt;Fixed Sidebar&lt;/strong&gt; &lt;br/&gt; It adjust to your needs." data-step="1"
         data-position="right" class="cl-sidebar">
        <div class="cl-toggle"><i class="fa fa-bars"></i></div>
        <div class="cl-navblock">
            <div class="menu-space nano nscroller has-scrollbar" style="height: 505px;">
                <div class="content" tabindex="0" style="right: -17px;">
                    <div class="side-user">
                        <div class="avatar"></div>
                        <div class="info">
                            <a href="#"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                            </a>
                            <img alt="Status" src="/admin_img/state_online.png"/> <span>系统运行正常</span>
                        </div>
                    </div>
                    <ul class="cl-vnavigation">
                        <li id="sysInfo"><a href="/admin_back/main.action"><i class="fa fa-home">系统信息</i></a>
                        </li>
                        <li class="parent"><a href="#"><i class="fa fa-smile-o">商品管理</i></a>
                            <ul class="sub-menu" style="display: block;">
                                <li><a href="/admin_back/toAddProduct.action">添加商品</a></li>
                                <li><a href="/admin_back/productList.action">商品列表</a></li>
                                <li><a href="/admin_back/index.action?id=hot20">商品期数管理</a></li>
                            </ul>
                        </li>
                        <li class="parent"><a href="#"><i class="fa fa-list-alt">会员管理</i></a>
                            <ul class="sub-menu" style="display: block;">
                                <li><a href="/admin_back/userListAll.action">会员列表</a></li>
                            </ul>
                        </li>
                        <li class="parent"><a href="#"><i class="fa fa-table">新闻管理</i></a>
                            <ul class="sub-menu" style="display: block;">
                                <li><a href="/admin_back/toAddNews.action">添加新闻</a></li>
                                <li><a href="/admin_back/newsList.action">新闻列表</a></li>
                                <li><a href="/admin_back/replaceNews.action">更新新闻</a></li>
                            </ul>
                        </li>
                        <li><a href="/admin_back/productTypeList.action"><i class="fa fa-text-height">商品分类管理</i></a>
                        </li>
                        <li><a href="/admin_back/latestList.action"><i class="fa fa-text-height">揭晓商品管理</i></a></li>
                        <li><a href="/admin_back/shareList.action?typeId=hot20"><i class="fa fa-text-height">商品晒单管理</i></a>
                        </li>
                        <li><a href="/admin_back/orderdetailaddressList.action"><i
                                class="fa fa-text-height">发货管理</i></a></li>
                        <li><a href="/admin_back/orderList.action"><i class="fa fa-text-height">订单管理</i></a></li>
                        <li><a href="/admin_back/cardList.action"><i class="fa fa-text-height">卡密管理</i></a></li>
                        <li><a href="/admin_back/applymentionList.action"><i class="fa fa-text-height">提现管理</i></a></li>
                        <li><a href="/admin_back/sysInfo.action"><i class="fa fa-bar-chart-o">系统配置</i></a></li>
                        <li><a href="/admin_back/updateAdminPwd.action"><i class="fa fa-bar-chart-o">管理密码</i></a></li>
                    </ul>
                </div>
                <div class="pane" style="display: none;">
                    <div class="slider" style="height: 426px; top: 0px;"></div>
                </div>
            </div>
            <div style="padding:7px 5px;" class="text-right collapse-button">
                <!--          <input type="text" placeholder="搜索..." class="form-control search" />-->
            </div>
        </div>
    </div>
    <!-- 左侧结束 -->
    <!-- 右侧开始 -->
    <div id="pcont" class="container-fluid">
        <d:body/>
    </div>
    <!-- 右侧结束 -->
</div>

<script type="text/javascript" src="/admin_js/jquery.js"></script>
<script type="text/javascript" src="/admin_js/general.js"></script>
<script type="text/javascript" src="/admin_js/jquery.nanoscroller.js"></script>
<!-- Bootstrap core JavaScript
  ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript">
    $(document).ready(function () {
        //initialize the javascript
        App.init();
        //App.dashBoard();        
        //introJs().setOption('showBullets', false).start();

    });
</script>
<script src="/admin_js/bootstrap.min.js"></script>
</body>
</html>
