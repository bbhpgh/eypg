<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title><d:title default="<%=ApplicationListenerImpl.sysConfigureJson.getSaitTitle() %>"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="/favicon.ico"/>
    <meta name="description" content="<%=ApplicationListenerImpl.sysConfigureJson.getDescription() %>"/>
    <meta name="keywords" content="<%=ApplicationListenerImpl.sysConfigureJson.getKeyword() %>"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/comm.css"/>
    <script language="javascript" type="text/javascript"
            src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery-1.4.4-min.js"></script>
    <d:head/>
</head>
<body<d:getProperty property="body.id" writeEntireProperty="true"/><d:getProperty property="body.class"
                                                                                  writeEntireProperty="true"/>>
<d:body/>
<!-- 尾部开始 -->
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
    <div class="copyright">Copyright &copy; 2011 - 2014,
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
<!-- 尾部结束 -->
<div style="display: none;">
    <script type="text/javascript">
        var $img = "<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>";
        var $skin = "<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>";
        var $www = "<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>";
        var $domain = "<%=ApplicationListenerImpl.sysConfigureJson.getDomain()%>";
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
