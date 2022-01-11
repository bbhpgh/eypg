<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>编辑个人资料_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
    </title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutframe.css?data=20131121"/>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutsetup.css?data=20131121"/>
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
                <li class=""><a href="/user/OrderList.html">获得的商品</a></li>
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
            <h3 url="" haschild="0" class="sid-icon09 sid-hcur "><a href="/user/MemberModify.html"><b></b>个人设置</a></h3>


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
        <div class="subMenu">
            <a class="current" title="个人资料" href="/user/MemberModify.html">个人资料</a>
            <a title="修改头像" href="/user/UserPhoto.html">修改头像</a>
            <a title="收货地址" href="/user/Address.html">收货地址</a>
            <a title="密码修改" href="/user/UpdatePassWord.html">密码修改</a>
        </div>
        <div class="prompt orange"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
            不会以任何形式公开您的个人隐私，请放心填写！<s></s></div>
        <div class="info">
            <table cellspacing="8" cellpadding="0" border="0">
                <tbody>
                <!--                    <tr>-->
                <!--	                    <td align="right"><font>*</font><label>手机：</label></td>-->
                <!--                        <td><b>${user.phone }</b> -->
                <!--                        <a title="修改" href="/MobileChecking.html">修改</a>-->
                <!--                         已验证</td>-->
                <!--                    </tr>-->
                <tr>
                    <td align="right"><font>*</font><label>邮箱：</label></td>
                    <td><b>${user.mail }</b>
                        <!--                        <a title="修改" href="/user/EmailChecking.html">修改</a>-->
                        已验证
                    </td>
                </tr>
                <tr>
                    <td align="right"><font>*</font><label>昵称：</label></td>
                    <td>
                        <input type="text" value="${user.userName }" maxlength="20" class="txt gray" id="txtName"
                               name="txtName"/>
                        <input type="hidden" value="${user.userName }" id="hidOldName" name="hidOldName"/>
                        <span style=" position:relative; top:3px;">昵称长度为2-20个字符，由汉字、字母、数字、“_-”字符组成</span></td>
                </tr>
                <tr>
                    <td align="right"><font>*</font><label>手机：</label></td>
                    <td>
                        <input type="text" class="txt gray" id="phone" value="${user.phone }" name="phone"/>
                        <span style=" position:relative; top:3px;"></span></td>
                </tr>
                <tr>
                    <td align="right"><label>备用电话：</label></td>
                    <td>
                        <input type="text" class="txt gray" id="txtPhone" value="${user.mobilePhone }" name="txtPhone"/>
                        <span style=" position:relative; top:3px;"></span></td>
                </tr>
                <!--                    <tr>-->
                <!--                        <td align="right"><label>性别：</label></td>-->
                <!--                        <td class="sex"><input type="radio" class="rdo" id="rdoBoy" name="sex" value="rdoBoy"><label class="gender" for="rdoBoy">男</label> <input type="radio" class="rdo" id="rdoGirl" name="sex" value="rdoGirl"><label class="gender" for="rdoGirl">女</label> <input type="radio" class="rdo" id="rdoSecrit" name="sex" value="rdoSecrit"><label class="gender" for="rdoSecrit">保密</label></td>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <td align="right"><label>生日：</label></td>-->
                <!--                        <td><select class="sltyear gray01" id="sltYear" name="sltYear">-->
                <!--<option value="2013">2013</option><option selected="" value="2012">2012</option><option value="2011">2011</option><option value="2010">2010</option><option value="2009">2009</option><option value="2008">2008</option><option value="2007">2007</option><option value="2006">2006</option><option value="2005">2005</option><option value="2004">2004</option><option value="2003">2003</option><option value="2002">2002</option><option value="2001">2001</option><option value="2000">2000</option><option value="1999">1999</option><option value="1998">1998</option><option value="1997">1997</option><option value="1996">1996</option><option value="1995">1995</option><option value="1994">1994</option><option value="1993">1993</option><option value="1992">1992</option><option value="1991">1991</option><option value="1990">1990</option><option value="1989">1989</option><option value="1988">1988</option><option value="1987">1987</option><option value="1986">1986</option><option value="1985">1985</option><option value="1984">1984</option><option value="1983">1983</option><option value="1982">1982</option><option value="1981">1981</option><option value="1980">1980</option><option value="1979">1979</option><option value="1978">1978</option><option value="1977">1977</option><option value="1976">1976</option><option value="1975">1975</option><option value="1974">1974</option><option value="1973">1973</option><option value="1972">1972</option><option value="1971">1971</option><option value="1970">1970</option><option value="1969">1969</option><option value="1968">1968</option><option value="1967">1967</option><option value="1966">1966</option><option value="1965">1965</option><option value="1964">1964</option><option value="1963">1963</option><option value="1962">1962</option><option value="1961">1961</option><option value="1960">1960</option><option value="1959">1959</option><option value="1958">1958</option><option value="1957">1957</option><option value="1956">1956</option><option value="1955">1955</option><option value="1954">1954</option>-->
                <!--</select><em>年</em>-->
                <!--                            <select class="sltday gray01" id="sltMouth" name="sltMouth">-->
                <!--<option selected="" value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option></select><em>月</em>-->
                <!--                            <select class="sltday gray01" id="sltDay" name="sltDay">-->
                <!--<option selected="" value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option></select><em>日</em>-->
                <!--  		                    <input type="hidden" value="2012-1-1" id="hidBirthday" name="hidBirthday" />-->
                <!--                            <input type="hidden" id="hidUpdateTime" name="hidUpdateTime" />-->
                <!--		                    <span id="BirthdayMsg"></span>-->
                <!--                        </td>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <td align="right"><label>星座：</label></td>-->
                <!--                        <td><select class="star gray01" id="selCons"><option selected="" value="1">白羊座</option><option value="2">金牛座</option><option value="3">双子座</option><option value="4">巨蟹座</option><option value="5">狮子座</option><option value="6">处女座</option><option value="7">天枰座</option><option value="8">天蝎座</option><option value="9">射手座</option><option value="10">摩羯座</option><option value="11">水瓶座</option><option value="12">双鱼座</option></select>-->
                <!--                            <input type="hidden" value="1" id="hideCons" name="hideCons" />-->
                <!--                        </td>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <td align="right"><label>现居地：</label></td>-->
                <!--                        <td><select class="provincial gray01" id="sel_areaA2"><option value="0">请选择&#12288;&#12288;</option><option value="7">广东省</option><option value="3">北京</option><option value="28">天津</option><option value="11">河北省</option><option value="24">山西省</option><option value="20">内蒙古自治区</option><option value="19">辽宁省</option><option value="16">吉林省</option><option value="13">黑龙江省</option><option value="26">上海</option><option value="17">江苏省</option><option value="32">浙江省</option><option value="2">安徽省</option><option value="5">福建省</option><option value="18">江西省</option><option value="23">山东省</option><option value="12">河南省</option><option value="14">湖北省</option><option value="15">湖南省</option><option value="8">广西壮族自治区</option><option value="10">海南省</option><option value="4">重庆</option><option value="27">四川省</option><option value="9">贵州省</option><option value="31">拍南省</option><option value="29">西藏自治区</option><option value="25">陕西省</option><option value="6">甘肃省</option><option value="22">青海省</option><option value="21">宁夏回族自治区</option><option value="30">新疆维吾尔自治区</option></select>-->
                <!--                            <select class="city gray01" id="sel_areaB2"><option value="0">请选择&#12288;&#12288;</option></select><span id="errmsgArea2"></span>-->
                <!--  		                    <input type="hidden" value="0" id="hidSelareaA2" name="hidSelareaA2" />-->
                <!--							<input type="hidden" value="0" id="hidSelareaB2" name="hidSelareaB2" />-->
                <!--                        </td>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--	                    <td align="right"><label>家乡：</label></td>-->
                <!--                        <td><select class="provincial gray01" id="sel_areaA"><option value="0">请选择&#12288;&#12288;</option><option value="7">广东省</option><option value="3">北京</option><option value="28">天津</option><option value="11">河北省</option><option value="24">山西省</option><option value="20">内蒙古自治区</option><option value="19">辽宁省</option><option value="16">吉林省</option><option value="13">黑龙江省</option><option value="26">上海</option><option value="17">江苏省</option><option value="32">浙江省</option><option value="2">安徽省</option><option value="5">福建省</option><option value="18">江西省</option><option value="23">山东省</option><option value="12">河南省</option><option value="14">湖北省</option><option value="15">湖南省</option><option value="8">广西壮族自治区</option><option value="10">海南省</option><option value="4">重庆</option><option value="27">四川省</option><option value="9">贵州省</option><option value="31">拍南省</option><option value="29">西藏自治区</option><option value="25">陕西省</option><option value="6">甘肃省</option><option value="22">青海省</option><option value="21">宁夏回族自治区</option><option value="30">新疆维吾尔自治区</option></select>-->
                <!--						    <select class="city gray01" id="sel_areaB"><option value="0">请选择&#12288;&#12288;</option></select><span id="errmsgArea"></span>-->
                <!--  		                    <input type="hidden" value="0" id="hidSelareaA" name="hidSelareaA" />-->
                <!--							<input type="hidden" value="0" id="hidSelareaB" name="hidSelareaB" />-->
                <!--                        </td>-->
                <!--                    </tr>-->
                <tr>
                    <td align="right"><label style="font-family:Arial, Helvetica, sans-serif;">QQ：</label></td>
                    <td><input type="text" class="txt gray" value="${user.qq }" id="txtQQ" name="txtQQ"/>
                        <span style=" position:relative; top:3px;"></span></td>
                </tr>
                <!--                    <tr>-->
                <!--                        <td align="right"><label>月收入：</label></td>-->
                <!--                        <td><select class="gray01" id="sltMonthIncome" name="sltMonthIncome">-->
                <!--	<option value="请选择" selected="selected">请选择</option>-->
                <!--	<option value="1000以下">1000以下</option>-->
                <!--	<option value="1000-3000">1000-3000</option>-->
                <!--	<option value="3000-5000">3000-5000</option>-->
                <!--	<option value="5000-10000">5000-10000</option>-->
                <!--	<option value="10000以上">10000以上</option>-->
                <!--</select></td>-->
                <!--                    </tr>-->
                <tr>
                    <td align="right"><label>签名：</label></td>
                    <td><textarea onpropertychange="if(value.length&gt;100) value=value.substr(0,100)"
                                  class="info_txtarea gray03" id="txtSignature"
                                  name="txtSignature">${user.signature }</textarea></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input type="button" title="保存" value="保存" class="bluebut" id="butSaveSubmit"></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>
<link type="text/css" rel="stylesheet"
      href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css?data=20131121"/>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/modify.js?data=20131121"></script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js?data=20131121"></script>
<div class="pageDialogBG" id="pageDialogBG"></div>
<div class="pageDialogBorder" id="pageDialogBorder"></div>
<div class="pageDialog" id="pageDialog">
    <div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    <div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
</div>
</body>
</html>
