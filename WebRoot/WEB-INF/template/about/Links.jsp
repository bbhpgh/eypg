<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
<div class="about-main">
    <div class="about-right-part">
        <div class="about-in-rihgt-part">
            <div class="about-content">
                <h2 class="tab">
                    <span>友情链接</span></h2>
                <h3 class="about-links-title">图片链接</h3>
                <div class="about-links-con">
                    <ul>
                        <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>"><img
                                src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/link_logo.jpg"
                                alt="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>" "/></a></li>
                    </ul>
                </div>
                <h3 class="about-links-title">文字链接</h3>
                <div class="about-links-con">
                    <ul>
                        <li>
                            <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                            </a></li>
                        <li><a href="http://www.moonbasa.com">梦芭莎</a></li>
                        <li><a href="http://www.xiango.cn">购物分享网</a></li>
                        <li><a href="http://www.rongzinet.com">蓝海项目融资</a></li>
                    </ul>
                </div>
                <h3 class="about-links-title">
                    链接说明</h3>
                <div class="about-links-con">
                    <p>
                        <strong>联系方式：</strong><br/>
                        邮箱：service@1ypg.com<br/>
                        QQ：<a target="_blank"
                              href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq() %>&site=qq&menu=yes">52013594</a><br/>
                        <strong>请提供相关资料：</strong><br/>
                        1、请提供需要链接的图片或文字，链接指定的URL，以及您认为必要的其它资料
                        <br/>
                        2、并在贵站适当的位置添加以下HTML链接代码
                        <textarea readonly="readonly" onclick="this.select()"
                                  style="width: 650px; color: #666; font-size: 12px; overflow: hidden; padding: 5px; height: 36px; line-height: 18px;"
                                  cols="2">&lt;a href="http://<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/"&gt;&lt;img src="http://img.1ypg.com/Images/link_logo.jpg" alt="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>"/&gt;&lt;/a&gt;</textarea>
                        <br/>
                        图示：<a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>"><img align="middle"
                                                                                                    src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/link_logo.jpg"></a>
                        <br/>
                        文字链接：<a
                            href="http://<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl() %>/"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName() %>
                    </a> (http://<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl() %>)
                    </p>
                    <p>&nbsp;
                    </p>
                </div>
                <script type="text/javascript" language="javascript">$("body").addClass("links");</script>
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
