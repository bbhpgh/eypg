<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title><d:title default="1ypg.com"/></title>
    <link href="/admin_css/global.css" rel="stylesheet"/>
    <link href="/admin_css/index.css" rel="stylesheet"/>
    <script language="javascript" type="text/javascript" src="/js/jquery123.js"></script>
    <script language="javascript" type="text/javascript" src="/admin_js/layer.min.js"></script>
    <script language="javascript" type="text/javascript" src="/admin_js/global.js"></script>
    <d:head/>
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
            $("#iframe_src").attr("src", "/admin_back/main.html");
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
            color: #2a8bbb;
            font-weight: bold;
            margin-bottom: 10px;
        }

    </style>
</head>
<body onResize="set_div();" <d:getProperty property="body.id" writeEntireProperty="true"/><d:getProperty
        property="body.name" writeEntireProperty="true"/><d:getProperty property="body.class"
                                                                        writeEntireProperty="true"/><d:getProperty
        property="body.rf" writeEntireProperty="true"/>>
<div id="header">
    <div class="logo"
         style="width: 500px; padding-top: 20px; color: rgb(255, 255, 255); font-family: '黑体'; font-size: 36px;"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        管理中心
    </div>
    <div class="header_case">
        欢迎您：<a title="test" href="javascript:;">test [超级管理员]</a>
        <a title="退出" href="http:/www.1ypg.com/admin/user/out">[退出]</a>
        <a target="_blank" title="网站首页" href="/index.html">网站首页</a>
        <a title="地图" href="http:/www.1ypg.com/admin/index/map">地图</a>
        <a target="_blank" title="官方网站" href="http:/www.1ypg.com/">官方网站</a>
        <button onclick="document.location.hash='hello'" style="width:0px;height:0px;"></button>
    </div>
    <div id="nav" class="nav">
        <ul>
            <li class="current"><a onclick="secBoard('nav',0,'setting','系统设置');" href="javascript:;">系统设置</a></li>
            <li class="normal"><a onclick="secBoard('nav',1,'content','内容管理');" href="javascript:;">内容管理</a></li>
            <li class="normal"><a onclick="secBoard('nav',2,'shop','商品管理');" href="javascript:;">商品管理</a></li>
            <li class="normal"><a onclick="secBoard('nav',3,'user','用户管理');" href="javascript:;">用户管理</a></li>
            <li class="normal"><a onclick="secBoard('nav',4,'template','界面管理');" href="javascript:;">界面管理</a></li>
            <li class="normal"><a onclick="secBoard('nav',5,'yunapp','云应用');" href="javascript:;">云应用</a></li>
        </ul>
    </div>
</div>

<div id="left">
    <ul id="setting" class="left_date" style="display: none;">
        <li class="head">站点设置</li>
        <li class=""><a src="http:/www.1ypg.com/admin/setting/webcfg" href="javascript:;">SEO设置</a></li>
        <li class=""><a src="http:/www.1ypg.com/admin/setting/config" href="javascript:;">基本设置</a></li>
        <li><a src="http:/www.1ypg.com/admin/setting/upload" href="javascript:;">上传设置</a></li>
        <li><a src="http:/www.1ypg.com/admin/setting/watermark" href="javascript:;">水印设置</a></li>
        <li><a src="http:/www.1ypg.com/admin/setting/email" href="javascript:;">邮箱配置</a></li>
        <li><a src="http:/www.1ypg.com/admin/setting/mobile" href="javascript:;">短信配置</a></li>
        <li><a src="http:/www.1ypg.com/pay/pay/pay_list" href="javascript:;">支付方式</a></li>
        <li><a src="http:/www.1ypg.com/admin/setting/empower" href="javascript:;">授权证书</a></li>
        <li><a src="http:/www.1ypg.com/admin/setting/domain" href="javascript:;">模块域名绑定</a></li>
        <li><a src="http:/www.1ypg.com/admin/qq_admin" href="javascript:;">官方QQ群</a></li>
        <li class="head">管理员管理</li>
        <li><a src="http:/www.1ypg.com/admin/user/lists" href="javascript:;">管理员管理</a></li>
        <li><a src="http:/www.1ypg.com/admin/user/reg" href="javascript:;">添加管理员</a></li>
        <li><a src="http:/www.1ypg.com/admin/user/edit/2" href="javascript:;">修改密码</a></li>
        <li class="head">站长运营</li>
        <li><a src="http:/www.1ypg.com/admin/yunwei/websitemap" href="javascript:;">站点地图</a></li>
        <li><a src="http:/www.1ypg.com/admin/yunwei/websubmit" href="javascript:;">网站提交</a></li>
        <li><a src="http:/www.1ypg.com/admin/yunwei/webtongji" href="javascript:;">站长统计</a></li>
        <li class="head">后台首页</li>
        <li class=""><a src="http:/www.1ypg.com/admin/index/Tdefault" href="javascript:;">后台首页</a></li>
        <li class="head">其他</li>
        <li><a src="http:/www.1ypg.com/admin/cache/init" href="javascript:;">清空缓存</a></li>
    </ul>
    <ul id="content" class="left_date" style="display: none;">
        <li class="head">文章管理</li>
        <li><a src="http:/www.1ypg.com/admin/content/article_add" href="javascript:;">添加文章</a></li>
        <li><a src="http:/www.1ypg.com/admin/content/article_list" href="javascript:;">文章列表</a></li>
        <li><a src="http:/www.1ypg.com/admin/category/lists/article" href="javascript:;">文章分类</a></li>
        <li class="head">单页管理</li>
        <li><a src="http:/www.1ypg.com/admin/category/addcate/danweb" href="javascript:;">添加单页</a></li>
        <li><a src="http:/www.1ypg.com/admin/category/lists/single" href="javascript:;">单页列表</a></li>
        <li class="head">附件管理</li>
        <li><a src="http:/www.1ypg.com/admin/upload/lists" href="javascript:;">上传文件管理</a></li>
        <!--<li><a href="javascript:;" src="http:/www.1ypg.com/admin/content/lists">管理内容</a></li>-->
        <li class="head">其他</li>
        <li><a src="http:/www.1ypg.com/admin/content/model" href="javascript:;">内容模型</a></li>
        <li><a src="http:/www.1ypg.com/admin/category/lists" href="javascript:;">栏目管理</a></li>
        <li class="head">模块管理</li>
        <li><a src="http:/www.1ypg.com/group/quanzi" href="javascript:;">圈子模块</a></li>
        <li><a src="http:/www.1ypg.com/admin/link/lists" href="javascript:;">友情链接</a></li>
        <li><a src="http:/www.1ypg.com/admanage/admanage_admin" href="javascript:;">广告模块</a></li>
        <li><a src="http:/www.1ypg.com/vote/vote_admin/" href="javascript:;">投票模块</a></li>

    </ul>
    <ul id="shop" class="left_date" style="display: block;">
        <li class="head">商品管理</li>
        <li><a src="http:/www.1ypg.com/admin/content/goods_add" href="javascript:;">添加新商品</a></li>
        <li class="set"><a src="http:/www.1ypg.com/admin/content/goods_list" href="javascript:;">商品列表</a></li>
        <li class=""><a src="http:/www.1ypg.com/admin/category/lists/goods" href="javascript:;">商品分类</a></li>
        <li class=""><a src="http:/www.1ypg.com/admin/brand/lists" href="javascript:;">品牌管理</a></li>
        <li class=""><a src="http:/www.1ypg.com/admin/brand/insert" href="javascript:;">添加品牌</a></li>
        <li><a src="http:/www.1ypg.com/admin/content/goods_del_list" href="javascript:;">商品回收站</a></li>
        <li class="head">商品其他</li>
        <li><a src="http:/www.1ypg.com/api/position/lists" href="javascript:;">推荐位管理</a></li>
        <li class="head">订单管理</li>
        <li><a src="http:/www.1ypg.com/admin/dingdan/lists" href="javascript:;">订单列表</a></li>
        <li><a src="http:/www.1ypg.com/admin/dingdan/select" href="javascript:;">订单查询</a></li>
        <li><a src="http:/www.1ypg.com/admin/dingdan/lists/zj" href="javascript:;">中奖订单</a></li>
        <li><a src="http:/www.1ypg.com/admin/dingdan/lists/notsend" href="javascript:;">未发货订单</a></li>
        <li class="head">促销管理</li>
        <li><a src="http:/www.1ypg.com/admin/content/goods_list/xianshi" href="javascript:;">限时揭晓商品</a></li>
        <li class="head">其他</li>
        <li><a src="http:/www.1ypg.com/go/shaidan_admin/init" href="javascript:;">晒单查看</a></li>
    </ul>
    <ul id="user" class="left_date" style="display: none;">
        <li class="head">用户管理</li>
        <li><a src="http:/www.1ypg.com/member/member/lists" href="javascript:;">会员列表</a></li>
        <li><a src="http:/www.1ypg.com/member/member/select" href="javascript:;">查找会员</a></li>
        <li><a src="http:/www.1ypg.com/member/member/insert" href="javascript:;">添加会员</a></li>
        <li><a src="http:/www.1ypg.com/member/member/config" href="javascript:;">会员配置</a></li>
        <li><a src="http:/www.1ypg.com/member/member/recharge" href="javascript:;">充值记录</a></li>
        <li><a src="http:/www.1ypg.com/member/member/pay_list" href="javascript:;">消费记录</a></li>
        <li><a src="http:/www.1ypg.com/member/member/member_group" href="javascript:;">会员组</a></li>
        <li><a src="http:/www.1ypg.com/member/member/commissions" href="javascript:;">佣金申请提现管理</a></li>

    </ul>
    <ul id="template" class="left_date" style="display: none;">
        <li class="head">界面管理</li>
        <li><a src="http:/www.1ypg.com/admin/ments/navigation" href="javascript:;">导航条管理</a></li>
        <li><a src="http:/www.1ypg.com/admin/slide" href="javascript:;">幻灯管理</a></li>
        <li><a src="http:/www.1ypg.com/admin/recom" href="javascript:;">推荐位图片</a></li>
        <li class="head">模板风格</li>
        <li><a src="http:/www.1ypg.com/admin/template/" href="javascript:;">模板设置</a></li>
        <li><a src="http:/www.1ypg.com/admin/template/see" href="javascript:;">查看模板</a></li>
        <li class="head">后台界面</li>
        <li><a src="http:/www.1ypg.com/admin/index/map" href="javascript:;">后台地图</a></li>

    </ul>
    <ul id="yunapp" class="left_date" style="display: none;">
        <li class="head">插件管理</li>
        <li><a src="http:/www.1ypg.com/api/upfile" href="javascript:;">在线升级</a></li>
        <!--<li><a href="javascript:;" src="http:/www.1ypg.com/api/plugin/get/bom">BOM检测</a></li>-->
        <li><a src="http:/www.1ypg.com/api/plugin/admin/egglotter/listlotter" href="javascript:;">游戏设置</a></li>
        <li><a src="http:/www.1ypg.com/api/qqlogin/qq_set_config" href="javascript:;">QQ登陆</a></li>
        <li><a src="http:/www.1ypg.com/admin/fund/fundset" href="javascript:;">公益基金</a></li>
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
        <ul id="R_label" class="R_label">当前位置: 商品管理 &gt; 商品列表 &gt;</ul>
        <ul class="R_btn">
            <a class="system_button" onclick="btn_iframef5();" href="javascript:;"><span>刷新框架</span></a>
            <a class="system_button" onclick="btn_caches();" href="javascript:;"><span>清空缓存</span></a>
            <a class="system_button" target="_blank"
               href="http://www.yungoucms.com/member.php?c=gongdan"><span>提交工单</span></a>
            <a class="system_button" onclick="btn_map('http:/www.1ypg.com/admin/index/map');" href="javascript:;"><span>后台地图</span></a>
        </ul>
    </div>
    <div class="right_left">
        <a id="off_on" title="全屏" val="open" href="#">全屏</a>
    </div>
    <div id="right_iframe">

        <iframe frameborder="no" allowtransparency="yes" scrolling="auto" src="/admin_back/main.html" marginheight="0"
                marginwidth="0" border="1" class="iframe" name="iframe" id="iframe_src">
        </iframe>

    </div>
</div>
</body>
</html>
