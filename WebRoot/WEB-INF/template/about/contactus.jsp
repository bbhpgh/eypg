<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>_帮助中心
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/about.css"/>
</head>

<body>
<div class="abou-banner">
    <span>关于我们</span></div>
<div class="about-main">
    <div class="about-right-part">
        <div class="about-in-rihgt-part">
            <div class="about-content">
                <h2 class="tab"><span>联系我们</span></h2>
                <h3>
                    联系我们</h3>
                <p>
                    热 线：</p>
                <p>
                    传 真：</p>
                <p>
                    邮 箱：<%=ApplicationListenerImpl.sysConfigureJson.getMailName()%>
                </p>
                <p>
                    地 址：</p>
                <p class="bottom-space20px">
                    邮 编：</p>
                <h3>
                    商务合作</h3>
                <p>
                    <%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                    拥有国内庞大的消费群体及专业高效的电子商务平台，诚意邀请各品牌供应商与我们达成商务合作，共同创造中国电子商务的美好明天。</p>
                <p>
                    电 话：</p>
                <p>
                    传 真：</p>
                <p class="bottom-space20px">
                    邮 箱：<%=ApplicationListenerImpl.sysConfigureJson.getMailName()%>
                </p>
                <h3>
                    市场推广</h3>
                <p>
                    随着<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                    发展壮大以及全国各地市场的开拓，欢迎拥有市场推广、广告合作资源的您与我们携手共进，共同发展。 携手共进。</p>
                <p>
                    电 话：</p>
                <p>
                    传 真：</p>
                <p class="bottom-space20px">
                    邮 箱：<%=ApplicationListenerImpl.sysConfigureJson.getMailName()%>
                </p>
                <h3>
                    媒体关注</h3>
                <p>
                    随着<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                    的发展，欢迎各类媒体前来沟通指导，同时欢迎各类内容合作策划传播，你们的关注和支持，采访以及报道，将成为<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                    成长历程不可或缺的一部分。</p>
                <p>
                    电 话：</p>
                <p>
                    传 真：</p>
                <p>
                    邮 箱：<%=ApplicationListenerImpl.sysConfigureJson.getMailName()%>
                </p>
                <script type="text/javascript" language="javascript">$("body").addClass("contactus");</script>
            </div>
        </div>
    </div>
    <div class="about-left-part">
        <div class="about-nav">
            <ul>
                <li class="about"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/index.html">关于<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                </a></li>
                <li><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/jobs.html">加入我们</a>
                </li>
                <!--  <li><a href="media.shtml"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>动态</a></li>
                    <li class="about-curchn"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/About/ourteam.html">我们的团队</a></li>
                    <li><a href="media.shtml">媒体报道</a></li>-->
                <!--                     <li><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/fund/index.html"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>公益</a></li>-->
                <li><a target="_blank"
                       href="http://weibo.com/1ypg"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>微博</a>
                </li>
                <!--                    <li class="contactus"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/contactus.html">联系我们</a></li>-->
                <li class="links"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/Links.html">友情链接</a>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
