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

public class YeepayAction extends ActionSupport {

    private static final long serialVersionUID = -4161171772234804569L;

    RandomUtil randomUtil = new RandomUtil();
    @Autowired
    ConsumetableService consumetableService;
    @Autowired
    ConsumerdetailService consumerdetailService;
    @Autowired
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    private RandomnumberService randomnumberService;
    @Autowired
    private UserService userService;
    @Autowired
    private SpellbuyproductService spellbuyproductService;
    @Autowired
    private LatestlotteryService latestlotteryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private NewLotteryUtil newLotteryUtil;
    @Autowired
    CommissionqueryService commissionqueryService;
    @Autowired
    CommissionpointsService commissionpointsService;

    private User user;
    private String userId;
    private Consumetable consumetable;
    private ProductCart productCart;
    private Product product;
    private Spellbuyproduct spellbuyproduct;
    private Spellbuyrecord spellbuyrecord;
    private Randomnumber randomnumber;
    private Latestlottery latestlottery;
    private Consumerdetail consumerdetail;
    private List<ProductCart> productCartList;
    private List<ProductJSON> successCartList;
    private ProductJSON productJSON;
    private String paymentStatus;
    private Commissionquery commissionquery;
    private Commissionpoints commissionpoints;

    private Integer moneyCount;
    private String integral;
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

    public String goPay() throws UnsupportedEncodingException {
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
        String p8_Url = ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/yeepay/notifyUrl.action";            // 商户接收支付成功数据的地址
        String p9_SAF = "0";            // 需要填写送货信息 0：不需要  1:需要
        String pa_MP = integral;                // 商户扩展信息
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

        /**
         * 业务开始
         */
        productCartList = new ArrayList<ProductCart>();
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
                if (cookie.getName().equals("products")) {
                    String product = new StringUtil().getUTF8URLDecoder(cookie.getValue());
                    if (product != null && !product.equals("")) {
                        array = JSONArray.fromObject(product);
                    }
                }
            }
        }

        if (StringUtil.isNotBlank(userId)) {
            Integer buyConut = 0;
            Integer productCount = 0;
            if (array != null && !array.toString().equals("[{}]")) {
                for (int i = 0; i < array.size(); i++) {
                    try {
                        JSONObject obj = (JSONObject) array.get(i);
                        productCart = new ProductCart();
                        List<Object[]> proList = spellbuyproductService.findByProductId(Integer.parseInt(obj.getString("pId")));
                        product = (Product) proList.get(0)[0];
                        spellbuyproduct = (Spellbuyproduct) proList.get(0)[1];
                        if (spellbuyproduct.getSpStatus() == 0) {
                            Integer count = 0;
                            //当前拍购人数
                            Integer CurrentPrice = spellbuyproduct.getSpellbuyCount();
                            if ((spellbuyproduct.getSpellbuyCount() + obj.getInt("num")) > spellbuyproduct.getSpellbuyPrice()) {
                                count = spellbuyproduct.getSpellbuyPrice() - spellbuyproduct.getSpellbuyCount();
                            } else {
                                count = obj.getInt("num");
                            }
                            buyConut += count;
                            productCount++;
                            productCart.setCount(count);
                            productCart.setHeadImage(product.getHeadImage());
//							productCart.setMoneyCount(buyConut);  
                            productCart.setProductCount(productCount);
                            productCart.setProductId(spellbuyproduct.getSpellbuyProductId());
                            productCart.setProductName(product.getProductName());
                            productCart.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                            productCart.setProductTitle(product.getProductTitle());
                            productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                            productCart.setProductPeriod(spellbuyproduct.getProductPeriod());
                            productCartList.add(productCart);
                            flag = true;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
//			moneyCount  财付通充值的钱 600 
//			buyConut    要花的钱   7
//			hidUseBalance    余额的钱 1   buyConut=moneyCount+hidUseBalance
            if (flag) {
                try {
                    consumetable = new Consumetable();
                    double money = Double.parseDouble(String.valueOf(moneyCount));
                    consumetable.setBuyCount(buyConut); //要花的钱 7
                    consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
                    consumetable.setInterfaceType("yeePay");
                    consumetable.setMoney(money); //财付通充值的钱 6
                    consumetable.setOutTradeNo(p2_Order);
                    consumetable.setUserId(Integer.parseInt(userId));
                    consumetableService.add(consumetable);
                } catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                }
            } else {
                flag = false;
                Struts2Utils.render("text/html", "<script>alert(\"购物车中有商品已经满员，请选择下一期！\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
            }
        }

        //建立请求
        String sHtmlText = buildRequest(sParaTemp, nodeAuthorizationURL, "POST", "确认");
        System.out.println(sHtmlText);
        if (flag) {
            Struts2Utils.render("text/html", sHtmlText, "encoding:UTF-8");
        } else {
            Struts2Utils.render("text/html", "<script>alert(\"购物车中有商品已经满员，请该商品的选择下一期！\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
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

        boolean flag = false;
        String buyproduct = "";//  已经被买完的商品

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
                    String key = MD5Util.encode(r2_TrxId);
                    if (MemCachedClientHelp.getIMemcachedCache().get(key) == null) {
                        MemCachedClientHelp.getIMemcachedCache().put(key, "y", new Date(12 * 60 * 60 * 1000));
                        productCartList = new ArrayList<ProductCart>();
                        successCartList = new ArrayList<ProductJSON>();
                        try {
                            consumetable = consumetableService.findByOutTradeNo(r6_Order);//找到订单
                            double money = Double.parseDouble(String.valueOf(r3_Amt));//充值的钱
                            System.err.println(consumetable.getMoney());
                            System.err.println(money);
                            if (consumetable.getMoney().equals(money) && consumetable.getTransactionId().equals(r2_TrxId)) {
                                Cookie[] cookies = request.getCookies();
                                JSONArray array = null;
                                if (request.isRequestedSessionIdFromCookie()) {
                                    for (int i = 0; i < cookies.length; i++) {
                                        Cookie cookie = cookies[i];
                                        if (cookie.getName().equals("userId")) {
                                            userId = cookie.getValue();
                                        }
                                        if (cookie.getName().equals("products")) {
                                            String product = new StringUtil().getUTF8URLDecoder(cookie.getValue());
                                            if (product != null && !product.equals("")) {
                                                array = JSONArray.fromObject(product);
                                            }
                                        }
                                    }
                                }

                                //总购买钱数
                                Integer moneyCount = 0;
                                Integer productCount = 0;
                                if (array != null && !array.toString().equals("[{}]")) {
                                    for (int i = 0; i < array.size(); i++) {
                                        try {
                                            JSONObject obj = (JSONObject) array.get(i);
                                            productCart = new ProductCart();
                                            List<Object[]> proList = spellbuyproductService.findByProductId(Integer.parseInt(obj.getString("pId")));
                                            product = (Product) proList.get(0)[0];
                                            spellbuyproduct = (Spellbuyproduct) proList.get(0)[1];
                                            if (spellbuyproduct.getSpStatus() == 0) {
                                                Integer count = 0;
                                                //当前拍购人数
                                                Integer CurrentPrice = spellbuyproduct.getSpellbuyCount();
                                                if ((spellbuyproduct.getSpellbuyCount() + obj.getInt("num")) > spellbuyproduct.getSpellbuyPrice()) {
                                                    count = spellbuyproduct.getSpellbuyPrice() - spellbuyproduct.getSpellbuyCount();
                                                } else {
                                                    count = obj.getInt("num");
                                                }
                                                moneyCount += count;
                                                productCount++;
                                                productCart.setCount(count);
                                                productCart.setHeadImage(product.getHeadImage());
                                                productCart.setMoneyCount(moneyCount);
                                                productCart.setProductCount(productCount);
                                                productCart.setProductId(spellbuyproduct.getSpellbuyProductId());
                                                productCart.setProductName(product.getProductName());
                                                productCart.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                                                productCart.setProductTitle(product.getProductTitle());
                                                productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                                                productCart.setProductPeriod(spellbuyproduct.getProductPeriod());
                                                productCartList.add(productCart);
                                                flag = true;
                                            } else {
                                                buyproduct += "您购买的商品中 <a href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/products/" + spellbuyproduct.getSpellbuyProductId() + ".html\" targer=\"_blank\">" + product.getProductName() + "</a>  已经满员.<br/>";
//												Struts2Utils.render("text/html", "<script>alert(\"购物车中有商品已经满员，请重新购买！\");window.location.href=\"/mycart/index.html\";</script>","encoding:UTF-8");
//												flag = false;
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                if (StringUtil.isNotBlank(userId)) {
                                    user = userService.findById(userId);
                                }

                                if (flag) {
                                    for (final ProductCart productCart : productCartList) {
                                        try {
                                            spellbuyproduct = spellbuyproductService.findById(productCart.getProductId().toString());
                                            //真正要购买的数量
                                            Integer count = 0;
                                            //当前拍购人数
                                            Integer CurrentPrice = spellbuyproduct.getSpellbuyCount();
                                            if ((spellbuyproduct.getSpellbuyCount() + productCart.getCount()) > productCart.getProductPrice()) {
                                                count = productCart.getProductPrice() - spellbuyproduct.getSpellbuyCount();
                                            } else {
                                                count = productCart.getCount();
                                            }

                                            if (count > 0) {
                                                if (StringUtil.isNotBlank(integral) && !integral.equals("0")) {
                                                    if (user.getBalance() >= (count - (Integer.parseInt(integral) / 100))) {
                                                        Double temp = user.getBalance() - (count - (Integer.parseInt(integral) / 100));
                                                        user.setBalance(temp);
//															userService.add(user);

                                                        consumerdetail = new Consumerdetail();
                                                        consumerdetail.setBuyCount(count);
                                                        consumerdetail.setBuyMoney(Double.parseDouble(String.valueOf(count)));
                                                        consumerdetail.setConsumetableId(r6_Order);
                                                        consumerdetail.setProductId(productCart.getProductId());
                                                        consumerdetail.setProductName(productCart.getProductName());
                                                        consumerdetail.setProductPeriod(productCart.getProductPeriod());
                                                        consumerdetail.setProductTitle(productCart.getProductTitle());
                                                        consumerdetailService.add(consumerdetail);

                                                        if (user.getInvite() != null) {
                                                            User userCommission = userService.findById(String.valueOf(user.getInvite()));
                                                            double tempCommissionCount = userCommission.getCommissionCount();
                                                            double commissionBalance = user.getCommissionBalance();
                                                            userCommission.setCommissionCount(tempCommissionCount += (Double.parseDouble(String.valueOf(count)) * 0.08));
                                                            userCommission.setCommissionBalance(commissionBalance += (Double.parseDouble(String.valueOf(count)) * 0.08));
                                                            userService.add(userCommission);
                                                            commissionquery = new Commissionquery();
                                                            commissionquery.setBuyMoney(Double.parseDouble(String.valueOf(count)));
                                                            commissionquery.setCommission((Double.parseDouble(String.valueOf(count)) * 0.08));
                                                            commissionquery.setDate(DateUtil.DateTimeToStr(new Date()));
                                                            commissionquery.setDescription(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + productCart.getProductId() + ")获得佣金");
                                                            commissionquery.setInvitedId(user.getInvite());
                                                            commissionquery.setToUserId(user.getUserId());
                                                            commissionqueryService.add(commissionquery);
                                                        }
                                                    } else {
                                                        Struts2Utils.render("text/html", "<script>alert(\"您的余额不足，无法完成支付\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
                                                    }
                                                } else {
                                                    if (user.getBalance() >= count) {
                                                        Double temp = user.getBalance() - count;
                                                        user.setBalance(temp);
                                                        //userService.add(user);
                                                        consumerdetail = new Consumerdetail();
                                                        consumerdetail.setBuyCount(count);
                                                        consumerdetail.setBuyMoney(Double.parseDouble(String.valueOf(count)));
                                                        consumerdetail.setConsumetableId(r6_Order);
                                                        consumerdetail.setProductId(productCart.getProductId());
                                                        consumerdetail.setProductName(productCart.getProductName());
                                                        consumerdetail.setProductPeriod(productCart.getProductPeriod());
                                                        consumerdetail.setProductTitle(productCart.getProductTitle());
                                                        consumerdetailService.add(consumerdetail);

                                                        if (user.getInvite() != null) {
                                                            User userCommission = userService.findById(String.valueOf(user.getInvite()));
                                                            double tempCommissionCount = userCommission.getCommissionCount();
                                                            double commissionBalance = user.getCommissionBalance();
                                                            userCommission.setCommissionCount(tempCommissionCount += (Double.parseDouble(String.valueOf(count)) * 0.08));
                                                            userCommission.setCommissionBalance(commissionBalance += (Double.parseDouble(String.valueOf(count)) * 0.08));
                                                            userService.add(userCommission);
                                                            commissionquery = new Commissionquery();
                                                            commissionquery.setBuyMoney(Double.parseDouble(String.valueOf(count)));
                                                            commissionquery.setCommission((Double.parseDouble(String.valueOf(count)) * 0.08));
                                                            commissionquery.setDate(DateUtil.DateTimeToStr(new Date()));
                                                            commissionquery.setDescription(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + productCart.getProductId() + ")获得佣金");
                                                            commissionquery.setInvitedId(user.getInvite());
                                                            commissionquery.setToUserId(user.getUserId());
                                                            commissionqueryService.add(commissionquery);
                                                        }
                                                    } else {
                                                        Struts2Utils.render("text/html", "<script>alert(\"您的余额不足，无法完成支付\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
                                                    }
                                                }

                                                spellbuyproduct.setSpellbuyCount(spellbuyproduct.getSpellbuyCount() + count);
                                                if (spellbuyproduct.getSpellbuyCount() >= productCart.getProductPrice()) {
                                                    spellbuyproduct.setSpellbuyCount(productCart.getProductPrice());
                                                    //开奖状态
                                                    spellbuyproduct.setSpStatus(2);
                                                    spellbuyproduct.setSpellbuyEndDate(DateUtil.DateTimeToStr(DateUtil.subMinute(new Date(), -3)));
                                                }
                                                spellbuyproductService.add(spellbuyproduct);

                                                spellbuyrecord = new Spellbuyrecord();
                                                spellbuyrecord.setFkSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
                                                spellbuyrecord.setBuyer(user.getUserId());
                                                spellbuyrecord.setBuyPrice(count);
                                                spellbuyrecord.setBuyDate(DateUtil.DateTimeToStrBySSS(new Date()));
                                                spellbuyrecord.setSpWinningStatus("0");
                                                spellbuyrecord.setBuyStatus("0");
                                                spellbuyrecord.setSpRandomNo("");
                                                spellbuyrecordService.add(spellbuyrecord);
                                                randomnumber = new Randomnumber();
                                                randomnumber.setProductId(productCart.getProductId());

                                                List<Randomnumber> RandomnumberList = randomnumberService.query(" from Randomnumber where productId='" + spellbuyproduct.getSpellbuyProductId()
                                                        + "'");
                                                List<String> oldRandomList = new ArrayList<String>();
                                                for (Randomnumber randomnumber : RandomnumberList) {
                                                    if (randomnumber.getRandomNumber().contains(",")) {
                                                        String[] rs = randomnumber.getRandomNumber().split(",");
                                                        for (String string : rs) {
                                                            oldRandomList.add(string);
                                                        }
                                                    } else {
                                                        oldRandomList.add(randomnumber.getRandomNumber());
                                                    }
                                                }
                                                randomnumber.setRandomNumber(randomUtil.newRandom(count, spellbuyproduct.getSpellbuyPrice(), oldRandomList));

                                                randomnumber.setSpellbuyrecordId(spellbuyrecord.getSpellbuyRecordId());
                                                randomnumber.setBuyDate(spellbuyrecord.getBuyDate());
                                                randomnumber.setUserId(Integer.parseInt(userId));
                                                randomnumberService.add(randomnumber);

                                                if (StringUtil.isNotBlank(integral) && !integral.equals("0")) {
                                                    commissionpoints = new Commissionpoints();
                                                    commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                                                    commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")福分抵扣");
                                                    commissionpoints.setPay("-" + integral);
                                                    commissionpoints.setToUserId(Integer.parseInt(userId));
                                                    commissionpointsService.add(commissionpoints);
                                                    Integer points = user.getCommissionPoints();
                                                    user.setCommissionPoints(points - Integer.parseInt(integral));
                                                }

                                                commissionpoints = new Commissionpoints();
                                                commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                                                commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")支付" + count + "元获得福分");
                                                commissionpoints.setPay("+" + (count - (Integer.parseInt(integral) / 100)));
                                                commissionpoints.setToUserId(Integer.parseInt(userId));
                                                commissionpointsService.add(commissionpoints);

                                                Integer points = user.getCommissionPoints();
                                                user.setCommissionPoints(points + (count - (Integer.parseInt(integral) / 100)));

                                                Integer experience = user.getExperience();
                                                experience += (count * 10);
                                                user.setExperience(experience);
                                                userService.add(user);

                                                productJSON = new ProductJSON();
                                                productJSON.setBuyDate(spellbuyrecord.getBuyDate());
                                                productJSON.setProductId(productCart.getProductId());
                                                productJSON.setProductName(productCart.getProductName());
                                                productJSON.setProductPeriod(productCart.getProductPeriod());
                                                productJSON.setProductTitle(productCart.getProductTitle());
                                                productJSON.setBuyCount(count);
                                                successCartList.add(productJSON);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            flag = false;
                                        }
                                    }
                                }
                                if (flag) {
                                    /**
                                     * 清空购物车
                                     */
                                    if (request.isRequestedSessionIdFromCookie()) {
                                        Cookie cookie = new Cookie("products", null);
                                        cookie.setMaxAge(0);
                                        cookie.setPath("/");
                                        cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                                        response.addCookie(cookie);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //注意交易单不要重复处理
                        //注意判断返回金额

                        //------------------------------
                        //即时到账处理业务完毕
                        //------------------------------
                        request.setAttribute("buyproduct", buyproduct);
                        System.out.println("即时到帐付款成功");
                        paymentStatus = "success";

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else {
            paymentStatus = "error";
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

                        user = userService.findById(String.valueOf(consumetable.getUserId()));
                        user.setBalance(money + user.getBalance());
                        userService.add(user);
                        flag = true;
                    }
                } catch (Exception e) {
                    flag = false;
                    e.printStackTrace();
                }
                String sHtmlText = buildRequest(sParaTemp, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/yeepay/returnUrl.html", "POST", "确认");
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

    public NewLotteryUtil getNewLotteryUtil() {
        return newLotteryUtil;
    }

    public void setNewLotteryUtil(NewLotteryUtil newLotteryUtil) {
        this.newLotteryUtil = newLotteryUtil;
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

    public ProductCart getProductCart() {
        return productCart;
    }

    public void setProductCart(ProductCart productCart) {
        this.productCart = productCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Spellbuyproduct getSpellbuyproduct() {
        return spellbuyproduct;
    }

    public void setSpellbuyproduct(Spellbuyproduct spellbuyproduct) {
        this.spellbuyproduct = spellbuyproduct;
    }

    public Spellbuyrecord getSpellbuyrecord() {
        return spellbuyrecord;
    }

    public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord) {
        this.spellbuyrecord = spellbuyrecord;
    }

    public Randomnumber getRandomnumber() {
        return randomnumber;
    }

    public void setRandomnumber(Randomnumber randomnumber) {
        this.randomnumber = randomnumber;
    }

    public Latestlottery getLatestlottery() {
        return latestlottery;
    }

    public void setLatestlottery(Latestlottery latestlottery) {
        this.latestlottery = latestlottery;
    }

    public Consumerdetail getConsumerdetail() {
        return consumerdetail;
    }

    public void setConsumerdetail(Consumerdetail consumerdetail) {
        this.consumerdetail = consumerdetail;
    }

    public List<ProductCart> getProductCartList() {
        return productCartList;
    }

    public void setProductCartList(List<ProductCart> productCartList) {
        this.productCartList = productCartList;
    }

    public List<ProductJSON> getSuccessCartList() {
        return successCartList;
    }

    public void setSuccessCartList(List<ProductJSON> successCartList) {
        this.successCartList = successCartList;
    }

    public ProductJSON getProductJSON() {
        return productJSON;
    }

    public void setProductJSON(ProductJSON productJSON) {
        this.productJSON = productJSON;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Commissionquery getCommissionquery() {
        return commissionquery;
    }

    public void setCommissionquery(Commissionquery commissionquery) {
        this.commissionquery = commissionquery;
    }

    public Commissionpoints getCommissionpoints() {
        return commissionpoints;
    }

    public void setCommissionpoints(Commissionpoints commissionpoints) {
        this.commissionpoints = commissionpoints;
    }

    public Integer getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(Integer moneyCount) {
        this.moneyCount = moneyCount;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }


}
