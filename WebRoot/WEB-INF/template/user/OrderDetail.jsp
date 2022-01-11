<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>获得的商品_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutframe.css?data=20131121"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutcommodity.css?data=20131121"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/dateinput.css?data=20131121"/>
</head>

<body>
<div class="main-content clearfix">

    <div class="left">
        <div class="head">
            <a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${user.userId }.html">
                <img width="160" height="160" border="0" runat="server" alt="" src="${user.faceImg }"
                     id="imgUserPhoto"/></a>
        </div>
        <div class="head-but">
            <a class="blue" href="/user/UserPhoto.html">修改头像</a>
            <a class="blue fr" href="/user/MemberModify.html">编辑资料</a>
        </div>
        <div class="sidebar-nav">
            <p class="sid-line"></p>
            <h2 class=""><a href="index.html"><b></b>我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
            </a></h2>
            <p class="sid-line"></p>
            <h3 url="/user/UserBuyList.html" haschild="1" class="sid-icon02 "><a
                    href="javascript:;"><b></b>我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <s
                    title="收起"></s></a></h3>
            <ul>
                <li class=""><a
                        href="/user/UserBuyList.html"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                    记录</a></li>
                <li class="sid-cur"><a href="/user/OrderList.html">获得的商品</a></li>
                <li class=""><a href="/user/PostSingleList.html">晒单</a></li>
            </ul>
            <!--<p class="sid-line"></p><h3 url="/user/JoinGroup.html" haschild="1" class="sid-icon03 "><a href="javascript:;"><b></b>圈子管理 <s title="收起"></s></a></h3>-->
            <!--<ul><li class=""><a href="/user/JoinGroup.html">加入的圈子</a></li>-->
            <!--<li class=""><a href="/user/Topic.html">圈子话题</a></li></ul>-->
            <p class="sid-line"></p>
            <h3 url="/user/InvitedList.html" haschild="1" class="sid-icon04 "><a href="javascript:;"><b></b>邀请管理 <s
                    title="收起"></s></a></h3>
            <ul>
                <li class=""><a href="/user/InvitedList.html">邀请好友</a></li>
                <li class=""><a href="/user/CommissionQuery.html">佣金明细</a></li>
                <li class=""><a href="/user/ApplyMention.html">提现申请</a></li>
                <li class=""><a href="/user/MentionList.html">提现记录</a></li>
            </ul>
            <p class="sid-line"></p>
            <h3 url="/user/UserBalance.html" haschild="1" class="sid-icon05 "><a href="javascript:;"><b></b>账户管理 <s
                    title="收起"></s></a></h3>
            <ul>
                <li class=""><a href="/user/UserBalance.html">账户明细</a></li>
                <li class=""><a href="/user/UserRecharge.html">账户充值</a></li>
                <li class=""><a href="/user/userCardRecharge.html">实卡充值</a></li>
            </ul>
            <!--<p class="sid-line"></p><h3 url="/user/MyFriends.html" haschild="1" class="sid-icon06 "><a href="javascript:;"><b></b>我的好友 <s title="收起"></s></a></h3>-->
            <!--<ul><li class=""><a href="/user/MyFriends.html">我的好友</a></li>-->
            <!--<li class=""><a href="/user/SearchFriends.html">查找好友</a></li>-->
            <!--<li class=""><a href="/user/FriendsApply.html">好友请求</a></li></ul>-->
            <p class="sid-line"></p>
            <h3 url="" haschild="0" class="sid-icon07 "><a href="/user/MemberPoints.html"><b></b>我的福分</a></h3>

            <!--<p class="sid-line"></p><h3 url="" haschild="0" class="sid-icon08 "><a href="/user/UserMessage.html"><b></b>消息管理</a></h3>-->

            <p class="sid-line"></p>
            <h3 url="" haschild="0" class="sid-icon09 "><a href="/user/MemberModify.html"><b></b>个人设置</a></h3>


            <p class="sid-line"></p>
        </div>
        <div class="sid-service">
            <p><a class="service-btn" target="_blank"
                  href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq() %>&site=qq&menu=yes"
                  id="btnLeftQQ"><s></s>在线客服</a></p>
            <span>客服热线(免长途费)</span>
            <b class="tel"><%=ApplicationListenerImpl.sysConfigureJson.getServiceTel() %>
            </b>
        </div>
    </div>
    <script type="text/javascript">

        var _NavState = [true, true, true, true, true];

        $("div.sidebar-nav").find("h3").each(function (i, v) {
            var _This = $(this);
            var _HasClild = _This.attr("hasChild") == "1";

            var _SObj = _This.find("s");
            _This.click(function (e) {
                if (_HasClild) {
                    var _State = _NavState[i];

                    /* 一级栏目更改样式 */
                    if (_State) {
                        _This.addClass("sid-iconcur");
                        _SObj.attr("title", "展开");
                    }
                    else {
                        _This.removeClass("sid-iconcur");
                        _SObj.attr("title", "收起");
                    }

                    /* 二级栏目显示或隐藏 */
                    _This.next("ul").children().each(function () {
                        if (_State) {
                            $(this).hide(50);
                        }
                        else {
                            $(this).show(50);
                        }
                    });
                    _NavState[i] = !_State;
                }
            });
        });

    </script>
    <div class="R-content">
        <input type="hidden" value="${userId }" id="userId"/>
        <div class="hddsp"><a title="返回" href="javascript:history.go(-1);">&lt;&lt; 返回</a><b class="gray">获得的商品 -
            交易详情</b></div>
        <div class="steps">
            <ul>
                <c:if test="${latestlottery.status==1}">
                    <li class="operatingcur">1.获得商品<span class="steps4"></span></li>
                    <li class="operating">2.确认收货地址<span class="steps3"></span></li>
                    <li class="">3.商家发货<span></span></li>
                    <li class="">4.确认收货<span></span></li>
                    <li class="">5.晒单分享</li>
                </c:if>
                <c:if test="${latestlottery.status==2}">
                    <li class="operatingcur">1.获得商品<span class="steps5"></span></li>
                    <li class="operatingcur">2.确认收货地址<span class="steps4"></span></li>
                    <li class="operating">3.商家发货<span class="steps3"></span></li>
                    <li class="">4.确认收货<span></span></li>
                    <li class="">5.晒单分享</li>
                </c:if>
                <c:if test="${latestlottery.status==3}">
                    <li class="operatingcur">1.获得商品<span class="steps5"></span></li>
                    <li class="operatingcur">2.确认收货地址<span class="steps5"></span></li>
                    <li class="operatingcur">3.商家发货<span class="steps4"></span></li>
                    <li class="operating">4.确认收货<span class="steps3"></span></li>
                    <li class="">5.晒单分享</li>
                </c:if>
                <c:if test="${latestlottery.status==4}">
                    <li class="operatingcur">1.获得商品<span class="steps5"></span></li>
                    <li class="operatingcur">2.确认收货地址<span class="steps5"></span></li>
                    <li class="operatingcur">3.商家发货<span class="steps5"></span></li>
                    <li class="operatingcur">4.确认收货<span class="steps4"></span></li>
                    <li class="operating">5.晒单分享</li>
                </c:if>
            </ul>
        </div>
        <div class="status">
            <c:if test="${latestlottery.status==1}">
                <p>当前状态：等待提交收货地址</p>
            </c:if>
            <c:if test="${latestlottery.status==2}">
                <p>当前状态：等待商家发货</p>
            </c:if>
            <c:if test="${latestlottery.status==3}">
                <p>当前状态：商家已发货，等待收货</p>
                <input type="button" value="确认收货" name="btnReceived" class="orangebut"/><br/>
            </c:if>
            <c:if test="${latestlottery.status==4}">
                <p>当前状态：等待晒单</p>
                <input type="button" onclick="javascript:location.href='/user/PostSingleAdd-${id }.html';" value="立即晒单"
                       class="orangebut"/><br/>
                <span>您可通过晒单获得相应福分奖励哦。</span>
            </c:if>
            <span>客服工作时间为（周一至周五 9:00-21:00）在此期间您可以致电<em><%=ApplicationListenerImpl.sysConfigureJson.getServiceTel() %></em>客服热线随时咨询订单物流信息。</span>
            <div class="confirm">
            </div>
            <input type="hidden" value="${id }" id="hidOrderNO" name="hidOrderNO"/>
            <table border="0" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td width="140">${fn:substring(latestlottery.announcedTime,0,16) }</td>
                    <td width="400">恭喜您<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                        成功，请尽快确认收货地址，以便我们为您配送！
                    </td>
                    <td>操作：<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                    </td>
                </tr>
                <s:iterator value="orderdetailList" var="orderdetails">
                    <tr>
                        <td width="140">${orderdetails.date }</td>
                        <td width="400">${orderdetails.detailText }</td>
                        <td>操作：${orderdetails.userName }</td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
        <c:if test="${latestlottery.status==1}">
            <div class="order_shipAddr">
                <h5>确认收货地址</h5>
                <div class="addressBox" id="addressBox">
                    <ul>
                        <s:iterator value="userbyaddressList" var="Adds" status="index">
                            <s:if test="#index.first">
                                <li>
                                    <input type="radio" checked="checked" value="${Adds.id }" id="addrId${Adds.id }"
                                           name="selectAddrID"/>
                                    <label for="addrId${Adds.id }">
                                            ${Adds.province }&nbsp;${Adds.city }&nbsp;${Adds.district }&nbsp;${Adds.address }
                                        <em>(收货人：${Adds.consignee }&nbsp;${Adds.phone })</em></label>
                                </li>
                            </s:if>
                            <s:else>
                                <li>
                                    <input type="radio" value="${Adds.id }" id="addrId${Adds.id }" name="selectAddrID"/>
                                    <label for="addrId${Adds.id }">
                                            ${Adds.province }&nbsp;${Adds.city }&nbsp;${Adds.district }&nbsp;${Adds.address }
                                        <em>(收货人：${Adds.consignee }&nbsp;${Adds.phone })</em></label>
                                </li>
                            </s:else>
                        </s:iterator>
                        <c:if test="${fn:length(userbyaddressList)<4}">
                        <li id="liAddAddrTools">
                            <input type="radio" value="" id="addrIDOther"/>
                            <label for="addrIDOther" id="lblNewAddrTitle1">使用其它地址</label>
                        </li>
                        <li class="addAddress" id="liAddAddrBox">
                            <div class="container">
                                <input type="radio" checked="checked" id="radNewAddress" name="newAddress" value=""/>
                                <label id="lblNewAddrTitle2">使用其它地址</label>
                                <div class="msg max-notice" id="msgAddrOver">
                                    <p class="attention">地址库已满，新地址为临时地址，无法保存入库，但本次可用</p>
                                </div>
                                <div class="form">
                                    <ul>
                                        <li>
                                            <label>所在省市区：</label>
                                            <span>
	                                    <ul class="addr-dist">
	                                        <li>
	                                            <select id="shipAreaA" name="shipAreaA">
	                                            </select>
	                                        </li>
	                                        <li>
	                                            <select id="shipAreaB" name="shipAreaB">
	                                            </select></li>
	                                        <li>
	                                            <select id="shipAreaC" name="shipAreaC">
	                                            </select></li>
	                                    </ul>
	                                    <div class="msg" id="shipAreaValidMsg">
	                                    </div>
	                                </span></li>
                                        <li>
                                            <label for="addrMail">街道地址：</label>
                                            <span>
	                                    <input type="text" style="width: 300px;" class="text" maxlength="100" size="100"
                                               id="shipAddress" name="shipAddress"/>&nbsp;该项中不需要填写省、市、区信息。
	                                    <div class="msg" id="shipAddressValidMsg">
	                                    </div>
	                                </span></li>
                                        <li>
                                            <label>邮政编码：</label>
                                            <span>
	                                    <input type="text" class="text" id="shipZip" maxlength="6" size="20"
                                               name="shipZip"/>&nbsp;<a href="http://alexa.ip138.com/post/Search.aspx"
                                                                        class="blue" target="_blank">邮编查询</a>
	                                    <div class="msg" id="shipZipValidMsg">
	                                    </div>
	                                </span></li>
                                        <li>
                                            <label for="addrName">收货人：</label>
                                            <span>
	                                    <input type="text" class="text" maxlength="20" size="20" id="shipName"
                                               name="shipName"/>&nbsp;请填写全名，避免"赵先生"、"孙生"、"李小姐"等，以免被误签
	                                    <div class="msg" id="shipNameValidMsg">
	                                    </div>
	                                </span></li>
                                        <li>
                                            <label>手机号码：</label>
                                            <span>
	                                    <input type="text" class="text" maxlength="20" size="20" name="shipMobile"
                                               id="shipMobile"/>
	                                    <div class="msg" id="shipMobileValidMsg">
	                                    </div>
	                                </span></li>
                                    </ul>
                                    <div class="go">
                                        <input type="button" value="保 存" id="btn_consignee_save" class="bluebut"/>
                                        <input type="button" value="取 消" id="btn_consignee_cancle" class="cancelBtn"/>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                    </c:if>
                </div>
                <!--配送方式-->
                <h5>确认配送时间<span>请选择送货上门的时间</span></h5>
                <div class="express" id="express">
                    <ul id="ulExpress">
                        <li>
                            <input type="radio" checked="checked" value="1" id="shipType1" name="shipType"/>
                            <label for="shipType1">普通快递送货上门</label>
                        </li>
                    </ul>
                    <ul>
                        <li class="time">
                            <select id="shipTime" name="shipTime">
                                <option value="周一至周五">周一至周五</option>
                                <option value="周六及公众假期">周六及公众假期</option>
                                <option selected="selected" value="时间不限">时间不限</option>
                            </select>
                        </li>
                    </ul>
                </div>
                <!--订单备注-->
                <h5>添加订单备注<span>请备注与订单相关的信息，如商品为手机充值卡可以填写要充值的手机号码等</span></h5>
                <div class="Remark" id="notes">
                    <textarea rows="3" cols="100" id="shipRemark" name="shipRemark"></textarea>
                    <div class="msg" id="shipRemarkValidMsg">
                    </div>
                </div>
                <div class="order_go"><input type="button" value="确认提交配送信息" id="btnSubmitCart" class="orangebut"/></div>
            </div>
        </c:if>

        <div class="information">
            <p>商品信息</p>
            <table border="0" cellspacing="0" cellpadding="0">
                <tbody>
                <tr class="infoTitle">
                    <td class="comm">商品</td>
                    <td class="bor">市场价</td>
                    <td>数量</td>
                </tr>
                <tr>
                    <td class="commodity">
                        <a target="_blank"
                           href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${latestlottery.spellbuyProductId }.html">
                            <img width="70" height="70" border="0" align="left" alt="${latestlottery.productTitle }"
                                 src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${latestlottery.productImg }"/></a>
                        <em class="orange">(第${latestlottery.productPeriod }期)</em><a target="_blank"
                                                                                      href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${latestlottery.spellbuyProductId }.html">${latestlottery.productName }</a>
                        <br/>幸运编号：${latestlottery.randomNumber }<br/>揭晓时间：${latestlottery.announcedTime }</td>
                    <td>￥${latestlottery.productPrice }.00</td>
                    <td>1</td>
                </tr>
                </tbody>
            </table>
            <p>配送信息</p>
            <table border="0" cellspacing="0" cellpadding="0" class="send">
                <tbody>
                <tr class="infoTitle">
                    <td class="comm">快递单号</td>
                    <td class="bor">快递公司</td>
                    <td class="bor">发货时间</td>
                    <td>备注</td>
                </tr>

                <tr class="sub">
                    <td class="num">${orderdetailaddress.expressNo }</td>
                    <td><a title="快递追踪" target="_blank" href="#">${orderdetailaddress.expressCompany }</a></td>
                    <td>${orderdetailaddress.deliverTime }</td>
                    <td width="300">${orderdetailaddress.deliverRemarks }</td>
                </tr>

                </tbody>
            </table>
            <p>收货信息</p>
            <div class="useInfo">
                收 货 人：${orderdetailaddress.consignee }<br/>
                联系电话：${orderdetailaddress.phone}<br/>
                收货地址：${orderdetailaddress.address }<br/>
                配送时间：${orderdetailaddress.postDate }<br/>
                订单备注：${orderdetailaddress.orderRemarks }<br/>
            </div>
        </div>
    </div>
</div>
<div class="clear_process"></div>
<link type="text/css" rel="stylesheet"
      href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css"/>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/orderdetail.js"></script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
<div class="pageDialogBG" id="pageDialogBG"></div>
<div class="pageDialogBorder" id="pageDialogBorder"></div>
<div class="pageDialog" id="pageDialog">
    <div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    <div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
</div>
</body>
</html>
