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
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/help.css"/>
</head>

<body>
<div class="help-main">
    <div class="help-right-part" id="ltlContents">
        <div class="help-right-part">
            <div class="help-in-rihgt-part">
                <div class="help-content">
                    <h2><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>流程</h2>
                    <div style="display:none;" class="liucheng_pic">
                        <ul class="yg-flow">
                            <li class="select">选择您喜欢的商品</li>
                            <li class="pay">支付1元获得1个<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>编号</li>
                            <li class="lottery">达到指定参与人次，揭晓商品获得者</li>
                            <li class="share">成功晒单，奖励1000福分</li>
                        </ul>
                    </div>
                    <h4>
                        1、挑选商品</h4>
                    <p>分类浏览或直接搜索商品，点击“立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>”。

                    </p><h4>
                    2、支付1元</h4>
                    <p>
                        选择在线支付方式，支付1元获得一个“<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                        码”。同一件商品可购买多次或一次购买多份，购买的“<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                        码”越多，获得商品的几率越大。</p>
                    <h4>3、揭晓获得者</h4>
                    <p>当一件商品所有“等份”售出后，<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                        根据“<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                        规则”公布<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>结果，并且会通过手机短信或邮件通知商品获得者。</p>
                    <p>注：</p>
                    <p>1、商品揭晓后您可登录“我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                        ”查询详情，未获得商品的用户不会收到短信或邮件通知；</p>
                    <p>2、商品揭晓后，请及时登录“我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                        ”完善个人资料，以便我们能够准确无误地为您配送商品。</p>
                    <h4>
                        4、晒单分享</h4>
                    <p>晒出您收到商品的实物照片和您的靓照，说出您的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                        心得，让大家一起分享您的快乐。</p>
                    <p>通过审核的晒单可获得1000福分奖励。</p>
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
                <li><a style="color: #FF6600;font-weight: bold;position: relative;"
                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/paigouFlow.html"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
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

                <li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/deliveryFees.html">配送费用</a>
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
