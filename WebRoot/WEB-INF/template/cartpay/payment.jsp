<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="template_footer"/>
    <title>购物车_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/payment.css"/>
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
        <li class="secend_step orange_Tech">第二步：网银支付</li>
        <li class="arrow_3"></li>
        <li class="third_step">第三步：支付成功 等待配送</li>
        <li class="arrow_2"></li>
        <li class="fourth_step">第四步：确认收货</li>
        <li class="arrow_2"></li>
        <li class="fifth_step">第五步：晒单奖励</li>
    </ul>
    <div class="payment_Con">
        <ul class="order_list">
            <li class="top"><span class="name">商品名称</span><span class="moneys">价值</span><span class="all">小计</span></li>
            <s:iterator var="productCarts" value="productCartList">
                <li class="end">
                    <span class="name"><a title="${productCarts.productTitle }"
                                          href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${productCarts.productId }.html"
                                          class="blue">${productCarts.productName }</a></span>
                    <span class="moneys">￥${productCarts.productPrice}</span>
                    <span class="all">${productCarts.productPrice }.00</span>
                </li>
            </s:iterator>
            <li class="payment_Total">
                <div class="payment_List_Lc"><a class="form_ReturnBut"
                                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/cartPay/index.html">返回购物车修改订单</a>
                </div>
                <p class="payment_List_Rc">商品合计：<span class="orange F20">${productCarts.moneyCount }.00</span> 元</p>
            </li>

            <li class="point_out point_gray" id="liPayByPoints">
                <div class="payment_List_Lc"><b class="input_gray"></b><input type="checkbox" disabled="disabled"
                                                                              id="ckPoints" class="input_choice"
                                                                              style="display: none;"/><label
                        for="ckPoints">使用福分抵扣现金：您的福分${user.commissionPoints }(本次消费最多可抵扣现金<span
                        class="orange Fb"><fmt:formatNumber type="number" value="${(user.commissionPoints/100)} "
                                                            maxFractionDigits="0"/></span>元)</label>，我要使用 <input
                        type="text" disabled="disabled" name="costPoint" class="pay_input_text_gray" maxlength="8"/> 福分
                </div>
                <p style="display:none;" class="pay_Value" id="pPointsTip"></p>
                <p class="payment_List_Rc"></p>
            </li>

            <li class="point_in point_gray" id="liPayByBalance">
                <div class="payment_List_Lc"><b class="input_gray"></b><input type="checkbox" class="input_choice"
                                                                              disabled="disabled"
                                                                              style="display: none;"/>使用账户余额支付，账户余额：<span
                        class="green F18">${user.balance}</span>元
                </div>
                <p style="display:none;" class="pay_Value" id="pBalanceTip"><span>◆</span><em>◆</em>账户余额支付更快捷，<a
                        class="blue" target="_blank"
                        href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/UserRecharge.html">立即充值</a>
                </p>
                <p class="payment_List_Rc"></p>
            </li>

            <li style="display: list-item;" class="point_in point_bank" id="liPayByOther">
                <div class="payment_List_Lc gary01 Fb">您的账户余额不足，请选择以下方式完成支付</div>
                <p class="payment_List_Rc">网银支付：<span class="orange F20">${productCarts.moneyCount }.00</span> 元</p>
            </li>
        </ul>
        <input id="hidBalance" type="hidden" value="${user.balance }"/>
        <input id="hidCountMoney" type="hidden" value="${productCarts.moneyCount }"/>
        <input id="hidPoints" type="hidden" value=""/>
        <input id="hidAvailablePoints" type="hidden" value="${user.commissionPoints }"/>
    </div>
    <div style="display: block;" id="divBankList" class="pay_bankC">
        <div class="bank_arrow"><span>◆</span><em>◆</em></div>
        <h2>银行支付：</h2>
        <ul class="bank_logo">
            <li><input type="radio" id="bankType1001" name="account" value="1001"><label for="bankType1001"><span
                    class="zh_bank"></span></label></li>
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
        <h3 class="bor">信用卡支付：</h3>
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
        <h3 class="bor">支付平台支付：</h3>
        <ul>
            <%
                if (ApplicationListenerImpl.sysConfigureJson.getTenpayStatus() == 0) {
            %>
            <li><input type="radio" checked="checked" id="Tenpay" name="account" value="Tenpay"/><label
                    for="Tenpay"><span class="cft"></span></label></li>
            <%
                }
            %>
            <!--                <li><input type="radio" id="Chinabank" name="account" value="Chinabank"><label for="Chinabank"><span class="wy"></span></label></li>-->
            <!--                <li><input type="radio" id="QuickMoney" name="account" value="QuickMoney"><label for="QuickMoney"><span class="kq"></span></label></li>-->
            <%
                if (ApplicationListenerImpl.sysConfigureJson.getAlipayStatus() == 0) {
            %>
            <li><input type="radio" id="Alipay" name="account" value="Alipay"/><label for="Alipay"><span
                    class="zfb"></span></label></li>
            <%
                }
            %>
            <%
                if (ApplicationListenerImpl.sysConfigureJson.getYeepayStatus() == 0) {
            %>
            <li><input type="radio" id="Yeepay" name="account" value="Yeepay"/><label for="Yeepay"><span
                    style="background: url('/Images/yeepay.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;"></span></label>
            </li>
            <%
                }
            %>

        </ul>
    </div>

    <form target="_blank" method="post"
          action="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/tenpay/goPay.action" name="toPayForm"
          id="toPayForm">
        <input type="hidden" value="0" name="bank_type" id="hidPayBank"/>
        <input type="hidden" value="" name="moneyCount" id="moneyCount"/>
        <input type="hidden" value="" name="hidUseBalance" id="hidUseBalance"/>
        <input type="hidden" value="0" name="integral" id="hidIntegral"/>
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
<div id="payAltBox" style="display: none;">
    <div class="payment_ts">
        <h3><s></s>请在新开窗口完成支付！</h3>
        <ul>
            <li class="payment_ts_con">如您的浏览器不支持，请复制以下链接到IE浏览器打开，完成付款后跟据您的情况进行以下操作！</li>
            <li class="payment_ts_links"><%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/mycart/payment.html
            </li>
            <li class="payment_ts_but"><a id="btnBuyOk" href="javascript:;" class="pay_tipsbut">完成支付</a><a
                    id="btnReSelect" href="javascript:;" class="blue">支付遇到问题？返回重新选择</a></li>
        </ul>
    </div>
</div>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/buypayment.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css"/>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
</body>
</html>
