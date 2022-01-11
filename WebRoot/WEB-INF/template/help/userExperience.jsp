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
                <h2>福分经验值明细表</h2>
                <style>
                    .help-nav ul li a.cur04 {
                        color: #f60;
                        position: relative;
                        font-weight: bold;
                    }

                    .help-nav ul li a.cur04 b {
                        width: 4px;
                        height: 4px;
                        overflow: hidden;
                        background: #f60;
                        display: inline-block;
                        position: absolute;
                        left: -10px;
                        top: 5px;
                    }
                </style>
                <div class="help-content">
                    <div class="help_class_tab">
                        <h3 class="mat0">等级明细</h3>
                        <table cellspacing="0" cellpadding="0" border="0">
                            <tbody>
                            <tr>
                                <th width="97">等级</th>
                                <th width="110">等级名称</th>
                                <th width="97">身份标识</th>
                                <th width="124">所需经验值</th>
                                <th width="166">备注</th>
                            </tr>
                            <tr>
                                <td>一级</td>
                                <td><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>小将</td>
                                <td valign="middle" class="class01"><b><img
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/new-class-icon.png"></b>
                                </td>
                                <td>10000以下</td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>二级</td>
                                <td><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>少将</td>
                                <td valign="middle" class="class02"><b><img
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/new-class-icon.png"></b>
                                </td>
                                <td>10000</td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>三级</td>
                                <td><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>中将</td>
                                <td valign="middle" class="class03"><b><img
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/new-class-icon.png"></b>
                                </td>
                                <td>50000</td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>四级</td>
                                <td><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>上将</td>
                                <td valign="middle" class="class04"><b><img
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/new-class-icon.png"></b>
                                </td>
                                <td>200000</td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>五级</td>
                                <td><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>大将</td>
                                <td valign="middle" class="class05"><b><img
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/new-class-icon.png"></b>
                                </td>
                                <td>500000</td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>六级</td>
                                <td><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>将军</td>
                                <td valign="middle" class="class06"><b><img
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/new-class-icon.png"></b>
                                </td>
                                <td>1000000</td>
                                <td>&nbsp;</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="help_Experience_tab">
                        <h3>福分经验值明细</h3>
                        <table cellspacing="0" cellpadding="0" border="0">
                            <tbody>
                            <tr>
                                <th colspan="2">项目</th>
                                <th width="104">获得福分</th>
                                <th width="107">获得经验值</th>
                                <th width="182">备注</th>
                            </tr>
                            <tr>
                                <td colspan="2">参与<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>每消费1元
                                </td>
                                <td><strong>1</strong></td>
                                <td><strong>10</strong></td>
                                <td>生日当月享双倍福分</td>
                            </tr>
                            <tr>
                                <td colspan="2">每成功邀请1位好友并消费</td>
                                <td><strong>50</strong></td>
                                <td><strong>50</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2">成功晒单</td>
                                <td><strong>1000</strong></td>
                                <td><strong>500</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2">晒单评论</td>
                                <td><strong>1</strong></td>
                                <td><strong>10</strong></td>
                                <td>最多100福分、1000经验值每天&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2">发表话题</td>
                                <td><strong>&nbsp;</strong></td>
                                <td><strong>50</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2">话题加精</td>
                                <td><strong>&nbsp;</strong></td>
                                <td><strong>50</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2">回复话题</td>
                                <td><strong>&nbsp;</strong></td>
                                <td><strong>10</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2">加好友</td>
                                <td><strong>&nbsp;</strong></td>
                                <td><strong>5</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2">圈主</td>
                                <td><strong>&nbsp;</strong></td>
                                <td><strong>1000</strong></td>
                                <td>每月获得一次</td>
                            </tr>
                            <tr>
                                <td colspan="2">管理员</td>
                                <td><strong>&nbsp;</strong></td>
                                <td><strong>500</strong></td>
                                <td>每月获得一次</td>
                            </tr>
                            <tr>
                                <td width="100" rowspan="10">完善个人资料</td>
                                <td width="100">手机验证</td>
                                <td><strong>20</strong></td>
                                <td><strong>&nbsp;</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>邮箱验证</td>
                                <td><strong>10</strong></td>
                                <td><strong>&nbsp;</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>昵称</td>
                                <td><strong>10</strong></td>
                                <td><strong>&nbsp;</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>性别</td>
                                <td><strong>5</strong></td>
                                <td><strong>&nbsp;</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>生日星座</td>
                                <td><strong>5</strong></td>
                                <td><strong>&nbsp;</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>现居地</td>
                                <td><strong>5</strong></td>
                                <td><strong>&nbsp;</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>家乡</td>
                                <td><strong>5</strong></td>
                                <td><strong>&nbsp;</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>QQ</td>
                                <td><strong>5</strong></td>
                                <td><strong>&nbsp;</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>收入</td>
                                <td><strong>5</strong></td>
                                <td><strong>&nbsp;</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>简介</td>
                                <td><strong>10</strong></td>
                                <td><strong>&nbsp;</strong></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td height="40" align="center" colspan="5">注：恶意灌水发表回复话题，将会扣除相应双倍经验值！</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="help-left-part">
        <div class="help-nav">
            <h3>帮助中心</h3>
            <h4>新手指南</h4>
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
                <li><a style="color: #FF6600;font-weight: bold;position: relative;"
                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/userExperience.html">会员福分经验值</a>
                </li>
            </ul>
            <div class="hackbox">
            </div>
            <h4>服务保障</h4>
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
            <h4>商品配送</h4>
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
