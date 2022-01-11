<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>${news.title }<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/news.css"/>
</head>

<body>
<div class="newsDetail">
    <div class="Current_nav"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">首页</a>
        &gt; <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>公告&nbsp;&gt;&nbsp;${news.title }</div>
    <div class="content" id="loadingPicBlock"><h3>${news.title }</h3>
        <div style="text-indent: 28px;">${news.content }</div>
        <div style="text-align: right;color: #666666;"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        </div>
        <div style="text-align: right;color: #666666;">${news.postDate }</div>
    </div>
</div>
</body>
</html>
