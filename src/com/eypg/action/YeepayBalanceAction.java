package com.eypg.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.eypg.alipay.util.AlipaySubmit;
import com.eypg.pojo.Commissionpoints;
import com.eypg.pojo.Commissionquery;
import com.eypg.pojo.Consumerdetail;
import com.eypg.pojo.Consumetable;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductCart;
import com.eypg.pojo.ProductJSON;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.service.CommissionpointsService;
import com.eypg.service.CommissionqueryService;
import com.eypg.service.ConsumerdetailService;
import com.eypg.service.ConsumetableService;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.ProductService;
import com.eypg.service.RandomnumberService;
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.service.UserService;
import com.eypg.tenpay.util.TenpayUtil;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.DateUtil;
import com.eypg.util.MD5Util;
import com.eypg.util.MemCachedClientHelp;
import com.eypg.util.NewLotteryUtil;
import com.eypg.util.RandomUtil;
import com.eypg.util.StringUtil;
import com.eypg.util.Struts2Utils;
import com.eypg.yeepay.config.PaymentForOnlineService;
import com.opensymphony.xwork2.ActionSupport;

public class YeepayBalanceAction extends ActionSupport {

    private static final long serialVersionUID = 1198398565998377267L;
    @Autowired
    ConsumetableService consumetableService;
    @Autowired
    ConsumerdetailService consumerdetailService;
    @Autowired
    private UserService userService;

    private User user;
    private String userId;
    private Consumetable consumetable;
    private Integer moneyCount;
    private String hidUseBalance;
    private String paymentStatus;


    //当前时间 yyyyMMddHHmmss
    private String currTime = TenpayUtil.getCurrTime();
    //8位日期
    private String strTime = currTime.substring(8, currTime.length());
    //四位随机数
    private String strRandom = TenpayUtil.buildRandom(4) + "";
    //10位序列号,可以自行调整。
    private String strReq = strTime + strRandom;
    private static String nodeAuthorizationURL = "https://www.yeepay.com/app-merchant-proxy/node";
    Random random = new Random();
    HttpServletRequest request = null;
    HttpServletResponse response = null;

    String formatString(String text) {
        if (text == null) {
            return "";
        }
        return text;
    }

    public String index() {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        boolean flag = false;
        String keyValue = ApplicationListenerImpl.sysConfigureJson.getYeepayKey();   // 商家密钥
        // 商家设置用户购买商品的支付信息
        String p0_Cmd = formatString("Buy");                                // 在线支付请求，固定值 ”Buy”
        String p1_MerId = ApplicationListenerImpl.sysConfigureJson.getYeepayPartner();        // 商户编号
        String p2_Order = strReq;           // 商户订单号
        String p3_Amt = moneyCount.toString();            // 支付金额
        String p4_Cur = formatString("CNY");                                        // 交易币种
        String p5_Pid = ApplicationListenerImpl.sysConfigureJson.getWwwUrl();                // 商品名称
        String p6_Pcat = ApplicationListenerImpl.sysConfigureJson.getWwwUrl();            // 商品种类
        String p7_Pdesc = ApplicationListenerImpl.sysConfigureJson.getWwwUrl();            // 商品描述
        String p8_Url = ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/yeepay/returnUrl.action";            // 商户接收支付成功数据的地址
        String p9_SAF = "0";            // 需要填写送货信息 0：不需要  1:需要
        String pa_MP = "";                // 商户扩展信息
        String pd_FrpId = "";        // 支付通道编码
        // 银行编号必须大写
        pd_FrpId = pd_FrpId.toUpperCase();
        String pr_NeedResponse = formatString("1");    // 默认为"1"，需要应答机制
        String hmac = formatString("");                                            // 交易签名串

        // 获得MD5-HMAC签名
        hmac = PaymentForOnlineService.getReqMd5HmacForOnlinePayment(p0_Cmd,
                p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
                p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("p0_Cmd", p0_Cmd);
        sParaTemp.put("p1_MerId", p1_MerId);
        sParaTemp.put("p2_Order", p2_Order);
        sParaTemp.put("p3_Amt", p3_Amt);
        sParaTemp.put("p4_Cur", p4_Cur);
        sParaTemp.put("p5_Pid", p5_Pid);
        sParaTemp.put("p6_Pcat", p6_Pcat);
        sParaTemp.put("p7_Pdesc", p7_Pdesc);
        sParaTemp.put("p8_Url", p8_Url);
        sParaTemp.put("p9_SAF", p9_SAF);
        sParaTemp.put("pa_MP", pa_MP);
        sParaTemp.put("pd_FrpId", pd_FrpId);
        sParaTemp.put("pr_NeedResponse", pr_NeedResponse);
        sParaTemp.put("hmac", hmac);

        Cookie[] cookies = request.getCookies();
        JSONArray array = null;
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
//					if(userId!=null && !userId.equals("")){
//						user = userService.findById(userId);
//					}
                }
            }
        }

        try {
            consumetable = new Consumetable();
            int buyConut = moneyCount;
            if (hidUseBalance != null && !hidUseBalance.equals("")) {
                buyConut += Integer.parseInt(hidUseBalance);
            }
            double money = Double.parseDouble(String.valueOf(moneyCount));
            consumetable.setBuyCount(buyConut);
            consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
            consumetable.setInterfaceType("yeePay");
            consumetable.setMoney(money);
            consumetable.setOutTradeNo(p2_Order);
            consumetable.setUserId(Integer.parseInt(userId));
            consumetableService.add(consumetable);
//			consumerdetail = new Consumerdetail();
//			consumerdetail.setBuyCount(productCart.getCount());
//			consumerdetail.setBuyMoney(Double.parseDouble(productCart.getCount().toString()));
//			consumerdetail.setConsumetableId(out_trade_no);
//			consumerdetail.setProductId(productCart.getProductId());
//			consumerdetail.setProductName(productCart.getProductName());
//			consumerdetail.setProductPeriod(productCart.getProductPeriod());
//			consumerdetail.setProductTitle(productCart.getProductTitle());
//			consumerdetailService.add(consumerdetail);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        //建立请求
        String sHtmlText = buildRequest(sParaTemp, nodeAuthorizationURL, "POST", "确认");
        System.out.println(sHtmlText);
        if (flag) {
            Struts2Utils.render("text/html", sHtmlText, "encoding:UTF-8");
        } else {
            Struts2Utils.render("text/html", "<script>alert(\"充值失败，请联系管理员！\");window.location.href=\"/index.html\";</script>", "encoding:UTF-8");
        }
        return null;
    }

    public String returnUrl() throws UnsupportedEncodingException {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        String keyValue = ApplicationListenerImpl.sysConfigureJson.getYeepayKey();   // 商家密钥
        String r0_Cmd = formatString(request.getParameter("r0_Cmd")); // 业务类型
        String p1_MerId = ApplicationListenerImpl.sysConfigureJson.getYeepayPartner();   // 商户编号
        String r1_Code = formatString(request.getParameter("r1_Code"));// 支付结果
        String r2_TrxId = formatString(request.getParameter("r2_TrxId"));// 易宝支付交易流水号
        String r3_Amt = formatString(request.getParameter("r3_Amt"));// 支付金额
        String r4_Cur = formatString(request.getParameter("r4_Cur"));// 交易币种
        String r5_Pid = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"), "gbk");// 商品名称
        String r6_Order = formatString(request.getParameter("r6_Order"));// 商户订单号
        String r7_Uid = formatString(request.getParameter("r7_Uid"));// 易宝支付会员ID
        String integral = new String(formatString(request.getParameter("integral")).getBytes("iso-8859-1"), "gbk");// 商户扩展信息
        String r9_BType = formatString(request.getParameter("r9_BType"));// 交易结果返回类型
        String hmac = formatString(request.getParameter("hmac"));// 签名数据

        boolean isOK = false;
        // 校验返回数据包
        isOK = PaymentForOnlineService.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code,
                r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, integral, r9_BType, keyValue);
        if (isOK) {
            //在接收到支付结果通知后，判断是否进行过业务逻辑处理，不要重复进行业务逻辑处理
            if (r1_Code.equals("1")) {
                // 产品通用接口支付成功返回-浏览器重定向
                if (r9_BType.equals("1")) {
                    System.out.println("callback方式:产品通用接口支付成功返回-浏览器重定向");
                    // 产品通用接口支付成功返回-服务器点对点通讯
                } else if (r9_BType.equals("2")) {
                    // 如果在发起交易请求时	设置使用应答机制时，必须应答以"success"开头的字符串，大小写不敏感
                    System.out.println("SUCCESS");
                    // 产品通用接口支付成功返回-电话支付返回
                }
                // 下面页面输出是测试时观察结果使用
                System.out.println("<br>交易成功!<br>商家订单号:" + r6_Order + "<br>支付金额:" + r3_Amt + "<br>易宝支付交易流水号:" + r2_TrxId);
                //即时到账处理业务开始
                //------------------------------
                try {
                    try {
                        consumetable = consumetableService.findByOutTradeNo(r6_Order);
                        double money = Double.parseDouble(String.valueOf(r3_Amt));
                        System.err.println(consumetable.getMoney());
                        System.err.println(money);
                        if (consumetable.getMoney().equals(money) && consumetable.getTransactionId().equals(r2_TrxId)) {
                            paymentStatus = "success";
                        } else {
                            paymentStatus = "error";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //注意交易单不要重复处理
                    //注意判断返回金额

                    //------------------------------
                    //即时到账处理业务完毕
                    //------------------------------
                    System.out.println("即时到帐付款成功");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("交易签名被篡改!");
        }

        return "success";
    }


    public String notifyUrl() throws UnsupportedEncodingException {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        boolean flag = false;
        String keyValue = ApplicationListenerImpl.sysConfigureJson.getYeepayKey();   // 商家密钥
        String r0_Cmd = formatString(request.getParameter("r0_Cmd")); // 业务类型
        String p1_MerId = ApplicationListenerImpl.sysConfigureJson.getYeepayPartner();   // 商户编号
        String r1_Code = formatString(request.getParameter("r1_Code"));// 支付结果
        String r2_TrxId = formatString(request.getParameter("r2_TrxId"));// 易宝支付交易流水号
        String r3_Amt = formatString(request.getParameter("r3_Amt"));// 支付金额
        String r4_Cur = formatString(request.getParameter("r4_Cur"));// 交易币种
        String r5_Pid = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"), "gbk");// 商品名称
        String r6_Order = formatString(request.getParameter("r6_Order"));// 商户订单号
        String r7_Uid = formatString(request.getParameter("r7_Uid"));// 易宝支付会员ID
        String integral = new String(formatString(request.getParameter("r8_MP")).getBytes("iso-8859-1"), "gbk");// 商户扩展信息
        String r9_BType = formatString(request.getParameter("r9_BType"));// 交易结果返回类型
        String hmac = formatString(request.getParameter("hmac"));// 签名数据

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("r0_Cmd", r0_Cmd);
        sParaTemp.put("p1_MerId", p1_MerId);
        sParaTemp.put("r1_Code", r1_Code);
        sParaTemp.put("r2_TrxId", r2_TrxId);
        sParaTemp.put("r3_Amt", r3_Amt);
        sParaTemp.put("r4_Cur", r4_Cur);
        sParaTemp.put("r5_Pid", r5_Pid);
        sParaTemp.put("r6_Order", r6_Order);
        sParaTemp.put("r7_Uid", r7_Uid);
        sParaTemp.put("integral", integral);
        sParaTemp.put("r9_BType", r9_BType);
        sParaTemp.put("hmac", hmac);


        boolean isOK = false;
        // 校验返回数据包
        isOK = PaymentForOnlineService.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code,
                r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, integral, r9_BType, keyValue);
        if (isOK) {
            //在接收到支付结果通知后，判断是否进行过业务逻辑处理，不要重复进行业务逻辑处理
            if (r1_Code.equals("1")) {
                // 产品通用接口支付成功返回-浏览器重定向
                if (r9_BType.equals("1")) {
                    System.out.println("callback方式:产品通用接口支付成功返回-浏览器重定向");
                    // 产品通用接口支付成功返回-服务器点对点通讯
                } else if (r9_BType.equals("2")) {
                    // 如果在发起交易请求时	设置使用应答机制时，必须应答以"success"开头的字符串，大小写不敏感
                    System.out.println("SUCCESS");
                    Struts2Utils.render("text/html", "success", "encoding:UTF-8");
                    // 产品通用接口支付成功返回-电话支付返回
                }
                // 下面页面输出是测试时观察结果使用
                System.out.println("<br>交易成功!<br>商家订单号:" + r6_Order + "<br>支付金额:" + r3_Amt + "<br>易宝支付交易流水号:" + r2_TrxId);
                //即时到账处理业务开始
                //------------------------------

                try {
                    consumetable = consumetableService.findByOutTradeNo(r6_Order);
                    double money = Double.parseDouble(String.valueOf(r3_Amt));
                    System.err.println(consumetable.getMoney());
                    System.err.println(money);
                    if (consumetable.getMoney().equals(money) && consumetable.getTransactionId() == null) {
                        consumetable.setTransactionId(r2_TrxId);
                        consumetableService.add(consumetable);
                        userId = String.valueOf(consumetable.getUserId());
                        if (userId != null && !userId.equals("")) {
                            try {
                                String key = MD5Util.encode(r2_TrxId);
                                if (MemCachedClientHelp.getIMemcachedCache().get(key) == null) {
                                    user = userService.findById(userId);
                                    Double temp = user.getBalance() + consumetable.getMoney();
                                    System.err.println("user.getBalance()" + user.getBalance());
                                    System.err.println("consumetable.getMoney()" + consumetable.getMoney());
                                    System.err.println("temp:" + temp);
                                    user.setBalance(temp);
                                    userService.add(user);
                                    MemCachedClientHelp.getIMemcachedCache().put(key, "y", new Date(12 * 60 * 60 * 1000));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String sHtmlText = buildRequest(sParaTemp, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/yeepayBalance/returnUrl.html", "POST", "确认");
                System.out.println(sHtmlText);
                Struts2Utils.render("text/html", sHtmlText, "encoding:UTF-8");
                System.out.println("success");    //请不要修改或删除
                Struts2Utils.render("text/html", "success", "encoding:UTF-8");

            }
        } else {
            System.out.println("交易签名被篡改!");
        }
        //建立请求
        return null;
    }

    /**
     * 建立请求，以表单HTML形式构造（默认）
     *
     * @param sParaTemp     请求参数数组
     * @param strMethod     提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String postUrl, String strMethod, String strButtonName) {
        //待请求参数数组
        Map<String, String> sPara = sParaTemp;
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"yeepaysubmit\" name=\"yeepaysubmit\" action=\"" + postUrl + "\" method=\"" + strMethod + "\">");
        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);
            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['yeepaysubmit'].submit();</script>");

        return sbHtml.toString();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Consumetable getConsumetable() {
        return consumetable;
    }

    public void setConsumetable(Consumetable consumetable) {
        this.consumetable = consumetable;
    }

    public Integer getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(Integer moneyCount) {
        this.moneyCount = moneyCount;
    }

    public String getHidUseBalance() {
        return hidUseBalance;
    }

    public void setHidUseBalance(String hidUseBalance) {
        this.hidUseBalance = hidUseBalance;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCurrTime() {
        return currTime;
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public String getStrRandom() {
        return strRandom;
    }

    public void setStrRandom(String strRandom) {
        this.strRandom = strRandom;
    }

    public String getStrReq() {
        return strReq;
    }

    public void setStrReq(String strReq) {
        this.strReq = strReq;
    }

}	