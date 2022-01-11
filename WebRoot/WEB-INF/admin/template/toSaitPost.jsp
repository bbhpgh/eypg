<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>1元拍购_管理中心</title>
    <link href="/admin_css/global.css" rel="stylesheet" type="text/css"/>
    <link href="/admin_css/admin_style.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
    <style>
        .topk3 {
        }

        .topk3 li {
            line-height: 35px;
            border-bottom: 1px solid #eee
        }

        .topk3 li a {
            margin-left: 30px;
            color: #9CC
        }
    </style>
</head>

<body>
<div class="header-title lr10"><b>站点提交</b></div>
<div class="bk10"></div>
<div class="header-data lr10">
    <b>搜索引擎网站收录地址大全</b>
</div>
<div class="bk10"></div>
<div class="table-list lr10">
    <div class="topk3 lr10">
        <ul>
            <li>百度搜索网址提交入口：<a rel="nofollow" target="_blank" href="http://zhanzhang.baidu.com/sitesubmit/index">http://zhanzhang.baidu.com/sitesubmit/index</a>
            </li>

            <li>360搜索引擎登录入口：<a rel="nofollow" target="_blank" href="http://info.so.360.cn/site_submit.html">http://info.so.360.cn/site_submit.html</a>
            </li>
            <li>360新闻源收录入口：<a rel="nofollow" target="_blank" href="http://info.so.360.cn/news_submit.html">http://info.so.360.cn/news_submit.html</a>
            </li>
            <li>Google网址提交入口：<a rel="nofollow" target="_blank"
                                href="https://www.google.com/webmasters/tools/submit-url?pli=1">https://www.google.com/webmasters/tools/submit-url?pli=1</a>
            </li>
            <li>Google新闻网站内容：<a rel="nofollow" target="_blank"
                                href="http://www.google.com/support/news_pub/bin/request.py?contact_type=suggest_content&amp;hl=cn">http://www.google.com/support/news_pub/bin/request.py?contact_type=suggest_content&amp;hl=cn</a>
            </li>
            <li>搜狗网站收录提交入口:<a rel="nofollow" target="_blank" href="http://www.sogou.com/feedback/urlfeedback.php">http://www.sogou.com/feedback/urlfeedback.php</a>
            </li>
            <li>SOSO搜搜网站收录提交入口:<a rel="nofollow" target="_blank" href="http://www.soso.com/help/usb/urlsubmit.shtml">http://www.soso.com/help/usb/urlsubmit.shtml</a>
            </li>

            <li>即刻搜索网站提交入口：<a rel="nofollow" target="_blank" href="http://zz.jike.com/submit/genUrlForm">http://zz.jike.com/submit/genUrlForm</a>
            </li>
            <li>盘古数据开放平台：<a rel="nofollow" target="_blank" href="http://open.panguso.com/data/resource/url/new">http://open.panguso.com/data/resource/url/new</a>
            </li>
            <li>bing(必应)网页提交登录入口：<a rel="nofollow" target="_blank" href="http://www.bing.com/toolbox/submit-site-url">http://www.bing.com/toolbox/submit-site-url</a>
            </li>
            <li>简搜搜索引擎登陆口：<a rel="nofollow" target="_blank" href="http://www.jianso.com/add_site.html">http://www.jianso.com/add_site.html</a>
            </li>
            <li>雅虎中国网站登录口：<a rel="nofollow" target="_blank" href="http://sitemap.cn.yahoo.com/">http://sitemap.cn.yahoo.com/</a>
            </li>
            <li>网易有道搜索引擎登录口：<a rel="nofollow" target="_blank" href="http://tellbot.youdao.com/report">http://tellbot.youdao.com/report</a>
            </li>
            <li>中搜免费登录服务：<a rel="nofollow" target="_blank"
                            href="http://register.zhongsou.com/NetSearch/frontEnd/free_protocol.htm">http://register.zhongsou.com/NetSearch/frontEnd/free_protocol.htm</a>
            </li>
            <li>MSN必应网站登录口：<a rel="nofollow" target="_blank" href="http://cn.bing.com/docs/submit.aspx?FORM=WSDD2">http://cn.bing.com/docs/submit.aspx?FORM=WSDD2</a>
            </li>
            <li>Alexa网站登录入口：<a rel="nofollow" target="_blank" href="http://www.alexa.com/help/webmasters">http://www.alexa.com/help/webmasters</a>
            </li>
            <li>TOM搜索网站登录口：<a rel="nofollow" target="_blank" href="http://search.tom.com/tools/weblog/log.php">http://search.tom.com/tools/weblog/log.php</a>
            </li>
            <li>铭万网B2B(必途)网址登陆口：<a rel="nofollow" target="_blank" href="http://search.b2b.cn/pageIncluded/AddPage.php">http://search.b2b.cn/pageIncluded/AddPage.php</a>
            </li>
            <li>博客大全提交：<a rel="nofollow" target="_blank" href="http://lusongsong.com/daohang/">http://lusongsong.com/daohang/login.asp</a>
            </li>
            <li>蚁搜搜索网站登录口：<a rel="nofollow" target="_blank" href="http://www.antso.com/apply.asp">http://www.antso.com/apply.asp</a>
            </li>
            <li>快搜搜索网站登录口：<a rel="nofollow" target="_blank" href="http://www.kuaisou.com/main/inputweb.asp">http://www.kuaisou.com/main/inputweb.asp</a>
            </li>
            <li>汕头搜索登录口：<a rel="nofollow" target="_blank" href="http://www.stsou.com/join.asp">http://www.stsou.com/join.asp</a>
            </li>
            <li>孙悟空搜索网站登录：<a rel="nofollow" target="_blank" href="http://www.swkong.com/add.php">http://www.swkong.com/add.php</a>
            </li>
            <li>博客大全提交：<a rel="nofollow" target="_blank" href="http://lusongsong.com/">http://lusongsong.com/daohang/login.asp</a>
            </li>
            <li>天网网站登陆口：<a rel="nofollow" target="_blank" href="http://home.tianwang.com/denglu.htm">http://home.tianwang.com/denglu.htm</a>
            </li>
            <li>速搜全球登陆口：<a rel="nofollow" target="_blank" href="http://www.suso.com.cn/suso/link.asp">http://www.suso.com.cn/suso/link.asp</a>
            </li>
            <li>酷帝网站目录提交入口：<a target="_blank" rel="nofollow" href="http://www.coodir.com/accounts/addsite.asp">http://www.coodir.com/accounts/addsite.asp</a>
            </li>
            <li>快搜网站登陆口：<a rel="nofollow" target="_blank" href="http://www.kuaisou.com/main/inputweb.asp">http://www.kuaisou.com/main/inputweb.asp</a>
            </li>
            <li>找人网登陆口：<a rel="nofollow" target="_blank" href="http://m.zhaoren.net/djxunzhi.htm">http://m.zhaoren.net/djxunzhi.htm</a>
            </li>
            <li>爱读小说搜搜索引擎登录入口：<a rel="nofollow" target="_blank" href="http://www.25xsw.com/search/url_submit.php">http://www.25xsw.com/search/url_submit.php</a>
            </li>

            <li>搜猫搜索引擎登录入口：<a rel="nofollow" target="_blank" href="http://test.somao123.com/search/url_submit.php">http://test.somao123.com/search/url_submit.php</a>
            </li>
            <li>泽许搜索网站登录入口：<a rel="nofollow" target="_blank"
                              href="http://wap.zxyt.cn/guide/?m=adc4&amp;Nsid=b936a850e4a451c2/&amp;wver=c">http://wap.zxyt.cn/guide/?m=adc4&amp;Nsid=b936a850e4a451c2/&amp;wver=c</a>
            </li>
            <li>一淘网开放搜索申请入口：<a rel="nofollow" target="_blank"
                               href="http://open.etao.com/apply_intro.htm?spm=0.0.0.0.Voi9lJ">http://open.etao.com/apply_intro.htm?spm=0.0.0.0.Voi9lJ</a>
            </li>
            <li>站长之家网站排行榜：<a rel="nofollow" target="_blank" href="http://top.chinaz.com/include.aspx">http://top.chinaz.com/include.aspx</a>
            </li>
            <li>爱搜搜索引擎登录入口：<a rel="nofollow" target="_blank" href="http://www.aiso0.com/search/url_submit.php">http://www.aiso0.com/search/url_submit.php</a>
            </li>
        </ul>
    </div>
</div>
<div class="bk30"></div>
</body>
</html>
