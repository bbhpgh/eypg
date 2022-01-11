<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="template_footer"/>
    <title>购物车_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/cartlist.css"/>
</head>

<body>
<div class="logo">
    <div class="float">
        <span class="logo_pic"><a title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>" class="a"
                                  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>"></a></span><span
            class="tel"><a style="color:#999;" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">返回首页</a></span>
    </div>
</div>
<div class="shop_payment">
    <ul class="payment">
        <li class="first_step">第一步：提交订单</li>
        <li class="arrow_1"></li>
        <li class="secend_step">第二步：网银支付</li>
        <li class="arrow_3"></li>
        <li class="third_step">第三步：支付成功 等待揭晓</li>
        <li class="arrow_2"></li>
        <li class="fourth_step">第四步：揭晓获得者</li>
        <li class="arrow_2"></li>
        <li class="fifth_step">第五步：晒单奖励</li>
    </ul>
    <div class="payment_list">
        <ul class="order_list">
            <li class="top"><span class="name">商品名称</span><span class="moneys">市场价</span><span
                    class="money"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>价</span><span
                    class="num"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>人次</span><span class="all">小计</span>
            </li>
            <s:iterator var="productCarts" value="productCartList">
                <li class="end">
                    <span class="name"><a title="${productCarts.productTitle }"
                                          href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${productCarts.productId }.html">${productCarts.productName } ${productCarts.productTitle }</a></span>
                    <span class="moneys">￥${productCarts.productPrice}</span>
                    <span class="money">￥1.00</span>
                    <span class="num">${productCarts.count }</span>
                    <span class="moneys">￥${productCarts.count }.00</span>
                </li>
            </s:iterator>

            <li class="bottom">
                <p style="float: left;padding-left: 35px; padding-top: 6px;">
                    <a style="color: #666;background: none repeat scroll 0 0 #F8F8F8;border: 1px solid #D6D6D6;color: #797777;font-size: 12px;margin-right: 5px;padding: 0 15px;display: inline-block;height: 25px;line-height: 25px;border-radius: 2px;"
                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/mycart/index.html">返回购物车修改订单</a>
                </p>
                商品合计：<span>${productCarts.moneyCount }.00</span> 元
            </li>

            <input type="hidden" value="${user.balance }" id="hidBalance"/>
            <input type="hidden" value="${user.commissionPoints }" id="hidUserPointst"/>
            <input type="hidden" value="${productCarts.moneyCount }" id="hidCountMoney"/>
            <li class="point_out point_gray" id="liPayByPoints">
            </li>

            <li class="point_in point_gray" id="liPayByBalance">
            </li>
            <li style="display: none;" class="point_in point_bank" id="liPayByOther">
                <div class="payment_List_Lc gary01 Fb">您的账户余额不足，请选择以下方式完成支付</div>
                <p class="payment_List_Rc">网银支付：<span class="orange F20">${productCarts.moneyCount }.00</span> 元</p>
            </li>
        </ul>
    </div>
    <!--        <div class="account">-->
    <!--            <ul>-->
    <!--                <li id="liPayAlt1">-->
    <!--                    <p>-->
    <!--                    您的帐户余额为：￥<span>${user.balance }</span>-->
    <!--                    <input type="hidden" value="${user.balance }" id="hidBalance"/><input type="hidden" value="${productCarts.moneyCount }" id="hidCountMoney"/>-->
    <!--                    </p>-->
    <!--                </li>-->
    <!--                <li class="tit" id="liPayAlt2"></li>-->
    <!--            </ul>-->
    <!--        </div>-->
    <div id="other" class="other">
        <!--            <h3>请选择以下支付方式，总需支付金额：￥<span class="yue" id="yue">${productCarts.moneyCount }.00</span></h3>-->
        <div>银行卡支付：</div>
        <ul class="bank_logo">
            <li><input type="radio" checked="checked" id="bankType1001" name="account" value="1001"><label
                    for="bankType1001"><span class="zh_bank"></span></label></li>
            <li><input type="radio" id="bankType1002" name="account" value="1002"><label for="bankType1002"><span
                    class="gh_bank"></span></label></li>
            <li><input type="radio" id="bankType1003" name="account" value="1003"><label for="bankType1003"><span
                    class="jh_bank"></span></label></li>
            <li><input type="radio" id="bankType1005" name="account" value="1005"><label for="bankType1005"><span
                    class="nh_bank"></span></label></li>
            <li><input type="radio" id="bankType1004" name="account" value="1004"><label for="bankType1004"><span
                    class="pf_bank"></span></label></li>

            <li><input type="radio" id="bankType1008" name="account" value="1008"><label for="bankType1008"><span
                    class="sf_bank"></span></label></li>
            <li><input type="radio" id="bankType1009" name="account" value="1009"><label for="bankType1009"><span
                    class="xy_bank"></span></label></li>
            <li><input type="radio" id="bankType1032" name="account" value="1032"><label for="bankType1032"><span
                    class="bj_bank"></span></label></li>
            <li><input type="radio" id="bankType1022" name="account" value="1022"><label for="bankType1022"><span
                    class="gd_bank"></span></label></li>
            <li><input type="radio" id="bankType1006" name="account" value="1006"><label for="bankType1006"><span
                    class="ms_bank"></span></label></li>

            <li><input type="radio" id="bankType1021" name="account" value="1021"><label for="bankType1021"><span
                    class="zx_bank"></span></label></li>
            <li><input type="radio" id="bankType1027" name="account" value="1027"><label for="bankType1027"><span
                    class="gf_bank"></span></label></li>
            <li><input type="radio" id="bankType1010" name="account" value="1010"><label for="bankType1010"><span
                    class="pa_bank"></span></label></li>
            <li><input type="radio" id="bankType1052" name="account" value="1052"><label for="bankType1052"><span
                    class="zg_bank"></span></label></li>
            <li><input type="radio" id="bankType1020" name="account" value="1020"><label for="bankType1020"><span
                    class="jt_bank"></span></label></li>
        </ul>
        <div>信用卡支付：</div>
        <ul class="bank_logo">
            <li><input type="radio" id="bankType_CMB_C" name="account" value="CMB_C"><label for="bankType_CMB_C"><span
                    class="zh_bank"></span></label></li>
            <li><input type="radio" id="bankType_ICBC_C" name="account" value="ICBC_C"><label
                    for="bankType_ICBC_C"><span class="gh_bank"></span></label></li>
            <li><input type="radio" id="bankType_CCB_C" name="account" value="CCB_C"><label for="bankType_CCB_C"><span
                    class="jh_bank"></span></label></li>
            <li><input type="radio" id="bankType_CIB_C" name="account" value="CIB_C"><label for="bankType_CIB_C"><span
                    class="xy_bank"></span></label></li>
            <li><input type="radio" id="bankType_CEB_C" name="account" value="CEB_C"><label for="bankType_CEB_C"><span
                    class="gd_bank"></span></label></li>

            <li><input type="radio" id="bankType_CMBC_C" name="account" value="CMBC_C"><label
                    for="bankType_CMBC_C"><span class="ms_bank"></span></label></li>
            <li><input type="radio" id="bankType_GDB_C" name="account" value="GDB_C"><label for="bankType_GDB_C"><span
                    class="gf_bank"></span></label></li>
            <li><input type="radio" id="bankType_BOC_C" name="account" value="BOC_C"><label for="bankType_BOC_C"><span
                    class="zg_bank"></span></label></li>
            <li><input type="radio" id="bankType_COMM_C" name="account" value="COMM_C"><label
                    for="bankType_COMM_C"><span class="jt_bank"></span></label></li>

            <li><input type="radio" id="bankType_BEA_C" name="account" value="BEA_C"><label for="bankType_BEA_C"><span
                    class="dy_bank"></span></label></li>
        </ul>
        <div class="bor">支付平台支付：</div>
        <ul>
            <li><input type="radio" id="Tenpay" name="account" value="Tenpay"><label for="Tenpay"><span
                    class="cft"></span></label></li>
            <!--                <li><input type="radio" id="Chinabank" name="account" value="Chinabank"><label for="Chinabank"><span class="wy"></span></label></li>-->
            <!--                <li><input type="radio" id="QuickMoney" name="account" value="QuickMoney"><label for="QuickMoney"><span class="kq"></span></label></li>-->
            <!--                <li><input type="radio" id="Alipay" name="account" value="Alipay"><label for="Alipay"><span class="zfb"></span></label></li>-->

        </ul>
    </div>
    <form target="_blank" method="post"
          action="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/tenpay/goPay.action" name="toPayForm"
          id="toPayForm">
        <input type="hidden" value="0" name="bank_type" id="hidPayBank"/>
        <input type="hidden" value="" name="moneyCount" id="moneyCount"/>
        <input type="hidden" value="" name="hidUseBalance" id="hidUseBalance"/>
        <div class="payment_but">
            <p class="pay_but_txt" id="pNewPointNum">成功支付可获得<b>${productCarts.moneyCount }</b>福分</p>
            <input type="submit" value="" name="submit" class="shop_pay_but" id="submit_ok"/>
        </div>
    </form>
    <div class="answer">
        <h6>
            <span></span>付款遇到问题</h6>
        <ul class="answer_list">
            <li>1、如您未开通网上银行，即可以使用储蓄卡快捷支付轻松完成付款；</li>
            <li>2、如果您没有网银，可以使用银联在线支付，银联有支持无需开通网银的快捷支付和储值卡支付；</li>
            <li>3、如果您有财付通或快钱、手机支付账户，可将款项先充入相应账户内，然后使用账户余额进行一次性支付；</li>
            <li>4、如果银行卡已经扣款，但您的账户中没有显示，有可能因为网络原因导致，将在第二个工作日恢复。</li>
            <li class="more"><a target="_blank"
                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/questionDetail.html">更多帮助</a>&nbsp;&nbsp;<a
                    href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/index.html">进入我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                中心&gt;&gt;</a></li>
        </ul>
    </div>
</div>
<div class="clear_process"></div>
<div class="pageDialogBG" id="pageDialogBG"></div>
<div class="pageDialogBorder" id="pageDialogBorder"></div>
<div class="pageDialog" id="pageDialog">
    <div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    <div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
</div>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/payment.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css"/>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
</body>
</html>
