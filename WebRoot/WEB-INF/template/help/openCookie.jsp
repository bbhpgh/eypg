<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>1元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>_帮助中心
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
                    <h2>开启Cookie</h2>
                    <p align="center">开启Cookie教程</p>
                    <ul>
                        <li><a href="#ie">IE浏览器</a></li>
                        <li><a href="#firefox">Firefox浏览器(火狐浏览器)</a></li>
                        <li><a href="#google">google浏览器</a></li>
                        <li><a href="#360">360浏览器</a></li>
                    </ul>
                    <h4 id="ie">IE开启Cookie：</h4>
                    <p>1.按Alt键切换显示菜单栏</p>
                    <p>选择 工具 -> Internet选项</p>
                    <br/>
                    <p><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/IE_Cookie_1.JPG"
                            alt="IE开启Cookie教程"/></p>
                    <br/>
                    <p>2.在Internet选项卡中选择隐私，在设置中把滚动条向下拉，或直接点默认值，然后确定。</p>
                    <p><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/IE_Cookie_2.JPG"
                            alt="IE开启Cookie教程"/></p>
                    <p>3.重新打开IE浏览器Cookie就开启成功了！</p>
                    <br/>
                    <h4 id="firefox">Firefox(火狐浏览器)开启Cookie：</h4>
                    <p>1.按Alt键切换显示菜单栏</p>
                    <p>选择 工具 -> 选项</p>
                    <p><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Firefox_Cookie_1.jpg"
                            alt="Firefox(火狐浏览器)开启Cookie教程"/></p>
                    <br/>
                    <p>2.在选项卡中选择隐私，在 历史 -> Firefox将会：中选择使用自定义历史记录设置 -> 勾选接受来自站点的Cookie,然后确定.</p>
                    <p><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Firefox_Cookie_2.jpg"
                            alt="Firefox(火狐浏览器)开启Cookie教程"/></p>
                    <br/>
                    <h4 id="google">google浏览器开启Cookie：</h4>
                    <p>1.在浏览器右上角点击设置图标,选择[选项]进入设置页面</p>
                    <p><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/google_Cookie_1.jpg"
                            alt="google浏览器开启Cookie教程"/></p>
                    <br/>
                    <p>2.在选项中选择高级选项 -> 隐私设置 -> 内容设置进入内容设置页面</p>
                    <p><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/google_Cookie_2.jpg"
                            alt="google浏览器开启Cookie教程"/></p>
                    <br/>
                    <p>3.在Cookie设置中选择允许设置本地数据(推荐)选项. 关闭页面Cookie开启成功.</p>
                    <p><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/google_Cookie_3.jpg"
                            alt="google浏览器开启Cookie教程"/></p>
                    <br/>
                    <h4 id="360">360浏览器开启Cookie：请参考IE设置流程.</h4>
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
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/whatPaigou.html">什么是1元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                    </a></li>
                <li>
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/paigouRule.html">1元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                        规则</a></li>
                <li>
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/paigouFlow.html">1元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
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
                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuinetwo.html">1元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
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
