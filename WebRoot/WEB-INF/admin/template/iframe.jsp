<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.pojo.User" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>管理中心</title>
    <link href="/admin_css/global.css" rel="stylesheet"/>
    <link href="/admin_css/index.css" rel="stylesheet"/>
    <script language="javascript" type="text/javascript" src="/admin_js/jquery-1.8.3.min.js"></script>
    <script language="javascript" type="text/javascript" src="/admin_js/layer.min.js"></script>
    <script language="javascript" type="text/javascript" src="/admin_js/global.js"></script>
    <script type="text/javascript">
        var ready = 1;
        var kj_width;
        var kj_height;
        var header_height = 100;
        var R_label;
        var R_label_one = "当前位置: 系统设置 >";


        function left(init) {
            var left = document.getElementById("left");
            var leftlist = left.getElementsByTagName("ul");

            for (var k = 0; k < leftlist.length; k++) {
                leftlist[k].style.display = "none";
            }
            document.getElementById(init).style.display = "block";
        }

        function secBoard(elementID, n, init, r_lable) {

            var elem = document.getElementById(elementID);
            var elemlist = elem.getElementsByTagName("li");
            for (var i = 0; i < elemlist.length; i++) {
                elemlist[i].className = "normal";
            }
            elemlist[n].className = "current";
            R_label_one = "当前位置: " + r_lable + " >";
            R_label.text(R_label_one);
            left(init);
        }


        function set_div() {
            kj_width = $(window).width();
            kj_height = $(window).height();
            if (kj_width < 1000) {
                kj_width = 1000;
            }
            if (kj_height < 500) {
                kj_height = 500;
            }

            $("#header").css('width', kj_width);
            $("#header").css('height', header_height);
            $("#left").css('height', kj_height - header_height);
            $("#right").css('height', kj_height - header_height);
            $("#left").css('top', header_height);
            $("#right").css('top', header_height);

            $("#left").css('width', 180);
            $("#right").css('width', kj_width - 182);
            $("#right").css('left', 182);

            $("#right_iframe").css('width', kj_width - 206);
            $("#right_iframe").css('height', kj_height - 148);

            $("#iframe_src").css('width', kj_width - 208);
            $("#iframe_src").css('height', kj_height - 150);

            $("#off_on").css('height', kj_height - 180);

            var nav = $("#nav");
            nav.css("left", (kj_width - nav.get(0).offsetWidth) / 2);
            nav.css("top", 61);
        }


        $(document).ready(function () {
            set_div();
            $("#off_on").click(function () {
                if ($(this).attr('val') == 'open') {
                    $(this).attr('val', 'exit');
                    $("#right").css('width', kj_width);
                    $("#right").css('left', 1);
                    $("#right_iframe").css('width', kj_width - 25);
                    $("iframe").css('width', kj_width - 27);
                } else {
                    $(this).attr('val', 'open');
                    $("#right").css('width', kj_width - 182);
                    $("#right").css('left', 182);
                    $("#right_iframe").css('width', kj_width - 206);
                    $("iframe").css('width', kj_width - 208);
                }
            });

            left('setting');
            $(".left_date a").click(function () {
                $(".left_date li").removeClass("set");
                $(this).parent().addClass("set");
                R_label.text(R_label_one + ' ' + $(this).text() + ' >');
                $("#iframe_src").attr("src", $(this).attr("src"));
            });
            $("#iframe_src").attr("src", "/admin_back/sysInfo.action");
            R_label = $("#R_label");
            $('body').bind('contextmenu', function () {
                return false;
            });
            $('body').bind("selectstart", function () {
                return false;
            });

        });

        function api_off_on_open(key) {
            if (key == 'open') {
                $("#off_on").attr('val', 'exit');
                $("#right").css('width', kj_width);
                $("#right").css('left', 1);
                $("#right_iframe").css('width', kj_width - 25);
                $("iframe").css('width', kj_width - 27);
            } else {
                $("#off_on").attr('val', 'open');
                $("#right").css('width', kj_width - 182);
                $("#right").css('left', 182);
                $("#right_iframe").css('width', kj_width - 206);
                $("iframe").css('width', kj_width - 208);
            }
        }
    </script>

    <style>
        .header_case {
            position: absolute;
            right: 10px;
            top: 10px;
            color: #fff
        }

        .header_case a {
            padding-left: 5px
        }

        .header_case a:hover {
            color: #fff;
        }

        .left_date a {
            color: #444;
        }

        .left_date {
            overflow: hidden;
        }

        .left_date ul {
            margin: 0px;
            padding: 0px;
        }

        .left_date li {
            line-height: 25px;
            height: 25px;
            margin: 0px 10px;
            margin-left: 15px;
            overflow: hidden;
        }

        .left_date li a {
            display: block;
            text-indent: 5px;
            overflow: hidden
        }

        .left_date li a:hover {
            background-color: #d3e8f2;
            text-decoration: none;
            border-radius: 3px;
        }

        .left_date .set a {
            background-color: #d3e8f2;
            border-radius: 3px;
            font-weight: bold
        }

        .head {
            border-bottom: 1px solid #c5e8f1;
            color: #af0808;
            font-weight: bold;
            margin-bottom: 10px;
        }

    </style>
</head>
<body onResize="set_div();">
<div id="header">
    <div class="logo"
         style="width: 500px; padding: 20px; color: rgb(255, 255, 255); font-family: '黑体'; font-size: 36px;"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        管理中心
    </div>
    <div class="header_case">
        欢迎您：<a title="test" href="javascript:;"><%= ((User) request.getSession().getAttribute("admin")).getUserName() %>
        [超级管理员]</a>
        <a title="退出" href="/admin_back/logOut.action">[退出]</a>
        <a target="_blank" title="网站首页" href="/">网站首页</a>
        <a title="地图" href="/admin_back/index/map">地图</a>
        <a target="_blank" title="官方网站" href="http://www.1ypg.com">官方网站</a>
    </div>
    <div id="nav" class="nav">
        <ul>
            <li class="current"><a onclick="secBoard('nav',0,'setting','系统设置');" href="javascript:;">系统设置</a></li>
            <li class="normal"><a onclick="secBoard('nav',1,'content','内容管理');" href="javascript:;">内容管理</a></li>
            <li class="normal"><a onclick="secBoard('nav',2,'shop','商品管理');" href="javascript:;">商品管理</a></li>
            <li class="normal"><a onclick="secBoard('nav',3,'user','用户管理');" href="javascript:;">用户管理</a></li>
            <!--             <li class="normal"><a onclick="secBoard('nav',4,'template','界面管理');" href="javascript:;">界面管理</a></li>-->
            <li class="normal"><a onclick="secBoard('nav',4,'yunapp','云应用');" href="javascript:;">云应用</a></li>
        </ul>
    </div>
</div>

<div id="left">
    <ul id="setting" class="left_date" style="display: none;">
        <li class="head">站点设置</li>
        <li class=""><a src="/admin_back/toSeoSet.action" href="javascript:;">SEO设置</a></li>
        <li class=""><a src="/admin_back/toBasicSet.action" href="javascript:;">基本设置</a></li>
        <li><a src="/admin_back/toMailSet.action" href="javascript:;">邮箱配置</a></li>
        <li><a src="/admin_back/toMessageSet.action" href="javascript:;">短信配置</a></li>
        <li><a src="/admin_back/toPaySet.action" href="javascript:;">支付方式</a></li>
        <li class="head">管理员管理</li>
        <li><a src="/admin_back/toAdminList.action" href="javascript:;">管理员管理</a></li>
        <li><a src="/admin_back/toAddAdmin.action" href="javascript:;">添加管理员</a></li>
        <li><a src="/admin_back/updateAdminPwd.action" href="javascript:;">修改密码</a></li>
        <li class="head">站长运营</li>
        <li><a src="/admin_back/toSaitMap.action" href="javascript:;">站点地图</a></li>
        <li><a src="/admin_back/toSaitPost.action" href="javascript:;">网站提交</a></li>
        <li><a src="/admin_back/toSaitCount.action" href="javascript:;">站长统计</a></li>
        <li class="head">后台首页</li>
        <li class=""><a src="/admin_back/sysInfo.html" href="javascript:;">后台首页</a></li>
    </ul>
    <ul id="content" class="left_date" style="display: none;">
        <li class="head">新闻管理</li>
        <li><a src="/admin_back/toAddNews.action" href="javascript:;">添加新闻</a></li>
        <li><a src="/admin_back/newsList.action" href="javascript:;">新闻列表</a></li>
        <li><a src="/admin_back/replaceNews.action" href="javascript:;">更新新闻</a></li>

    </ul>
    <ul id="shop" class="left_date" style="display: block;">
        <li class="head">商品管理</li>
        <li><a src="/admin_back/toAddProduct.action" href="javascript:;">添加商品</a></li>
        <li class="set"><a src="/admin_back/productList.action" href="javascript:;">商品列表</a></li>
        <li><a src="/admin_back/toAddProductType.action" href="javascript:;">添加商品分类</a></li>
        <li class=""><a src="/admin_back/productTypeList.action" href="javascript:;">商品分类列表</a></li>
        <li class=""><a src="/admin_back/index.action?id=hot20" href="javascript:;">在售商品管理</a></li>
        <li class="head">订单管理</li>
        <li><a src="/admin_back/orderList.action" href="javascript:;">订单列表</a></li>
        <li><a src="/admin_back/latestList.action" href="javascript:;">中奖订单</a></li>
        <li><a src="/admin_back/latestList.action" href="javascript:;">未发货订单</a></li>
        <li class="head">促销管理</li>
        <li><a src="/admin_back/toAddCard.action" href="javascript:;">生成卡密</a></li>
        <li><a src="/admin_back/cardList.action" href="javascript:;">卡密管理</a></li>
        <li class="head">晒单管理</li>
        <li><a src="/admin_back/shareList.action?typeId=hot20" href="javascript:;">晒单查看</a></li>
    </ul>
    <ul id="user" class="left_date" style="display: none;">
        <li class="head">用户管理</li>
        <li><a src="/admin_back/userListAll.action" href="javascript:;">会员列表</a></li>
        <li><a src="/admin_back/toAddUser.action" href="javascript:;">添加会员</a></li>
        <li><a src="/admin_back/rechargeAllList.action" href="javascript:;">充值记录</a></li>
        <li><a src="/admin_back/consumeAllList.action" href="javascript:;">消费记录</a></li>
        <li><a src="/admin_back/applymentionList.action" href="javascript:;">提现管理</a></li>
        <li><a src="/admin_back/orderdetailaddressList.action" href="javascript:;">发货管理</a></li>

    </ul>
    <!--    <ul id="template" class="left_date" style="display: none;">   -->
    <!--     	<li class="head">界面管理</li>-->
    <!--        <li><a src="" href="javascript:;">导航条管理</a></li>-->
    <!--        <li><a src="" href="javascript:;">幻灯管理</a></li>-->
    <!--        <li><a src="" href="javascript:;">推荐位图片</a></li>-->
    <!--        <li class="head">模板风格</li>-->
    <!--        <li><a src="" href="javascript:;">模板设置</a></li>       -->
    <!--		<li><a src="" href="javascript:;">查看模板</a></li>-->
    <!--        <li class="head">后台界面</li>-->
    <!--        <li><a src="" href="javascript:;">后台地图</a></li>   -->
    <!--    </ul>    -->
    <ul id="yunapp" class="left_date" style="display: none;">
        <li class="head">插件管理</li>
        <li><a src="/admin_back/lineUpdate.action" href="javascript:;">在线升级</a></li>
        <li><a src="/admin_back/qqSetInfo.action" href="javascript:;">QQ互联登陆</a></li>
    </ul>
    <div style="padding:30px 10px; color:#ccc">
        <p>
            &copy; 2014 1ypg.com<br/>
            All Rights Reserved.
        </p>
    </div>
</div>


<div id="right">
    <div class="right_top">
        <ul id="R_label" class="R_label">当前位置: 系统设置 &gt; 后台主页 &gt;</ul>
        <!--    	<ul class="R_btn">-->
        <!--    	<a class="system_button" onclick="btn_iframef5();" href="javascript:;"><span>刷新框架</span></a>-->
        <!--        <a class="system_button" onclick="btn_caches();" href="javascript:;"><span>清空缓存</span></a>    -->
        <!--		<a class="system_button" target="_blank" href=""><span>提交工单</span></a>		-->
        <!--        <a class="system_button" onclick="btn_map('');" href="javascript:;"><span>后台地图</span></a>-->
        <!--        </ul>-->
    </div>
    <div class="right_left">
        <a id="off_on" title="全屏" val="open" href="#">全屏</a>
    </div>
    <div id="right_iframe">

        <iframe frameborder="no" allowtransparency="yes" scrolling="auto" src="/admin_back/sysInfo.action"
                marginheight="0" marginwidth="0" border="1" class="iframe" name="iframe" id="iframe_src">
        </iframe>

    </div>
</div>
</body>
</html>
