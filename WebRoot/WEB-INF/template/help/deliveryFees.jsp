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
          href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/css/help.css"/>
</head>

<body>
<div class="help-main">
    <div class="help-right-part" id="ltlContents">
        <div class="help-right-part">
            <div class="help-in-rihgt-part">
                <div class="help-content">
                    <h2>
                        配送费用</h2>
                    <p>
                        所有商品全国免费配送。（港澳地区除外）</p>
                </div>
            </div>
        </div>
    </div>
    <div class="help-left-part">
        <div class="help-nav">
            <h3>
                帮助中心</h3>
            <h4>
                新手指南</h4>
            <ul>
                <li>
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/whatPaigou.html">什么是<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                    </a></li>
                <li>
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/paigouRule.html"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                        规则</a></li>
                <li>
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/paigouFlow.html"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                        流程</a></li>
                <li>
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/questionDetail.html">常见问题</a>
                </li>
                <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/agreement.html">服务协议</a>
                </li>
                <li>
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/userExperience.html">会员福分经验值</a>
                </li>
            </ul>
            <div class="hackbox">
            </div>
            <h4>
                服务保障</h4>
            <ul>
                <li>
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuinetwo.html"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                        保障体系</a></li>
                <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuine.html">正品承诺</a></li>
                <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/securepayment.html">安全支付</a>
                </li>
                <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/ship.html">商品配送</a></li>
                <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/suggestion.html">投诉建议</a>
                </li>
            </ul>
            <div class="hackbox">
            </div>
            <h4>
                商品配送</h4>
            <ul>

                <li><a style="color: #FF6600;font-weight: bold;position: relative;"
                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/deliveryFees.html">配送费用</a>
                </li>
                <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/prodCheck.html">商品验货与签收</a>
                </li>
                <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/shiptwo.html">长时间未收到商品问题</a>
                </li>
            </ul>
            <div class="hackbox">
                <h4>关于<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                </h4>
                <ul>
                    <li><a class="cur13"
                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/index.html"><b></b>关于我们</a>
                    </li>
                    <li><a class="cur15" target="_blank"
                           href="http://e.weibo.com/1ypg"><b></b><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                        微博</a></li>
                    <li><a class="cur17"
                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/Links.html"><b></b>友情链接</a>
                    </li>
                </ul>
                <div class="hackbox"></div>
            </div>
        </div>
        <div class="help-contact">
            <p>如果不能在帮助内容中找到答案，或者您有其他建议、投诉，您还可以：</p>
            <ul>
                <li class="CustomerCon"><a class="Customer" target="_blank"
                                           href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq() %>&site=qq&menu=yes"><b></b>在线客服</a>
                </li>
                <li>电话客服热线(免长途费)</li>
                <li class="tel"><span><%=ApplicationListenerImpl.sysConfigureJson.getServiceTel() %></span></li>
                <li>邮件联系：<a
                        href="mailto:<%=ApplicationListenerImpl.sysConfigureJson.getMailName() %>"><%=ApplicationListenerImpl.sysConfigureJson.getMailName() %>
                </a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
