package com.eypg.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.eypg.tenpay.RequestHandler;
import com.eypg.tenpay.ResponseHandler;
import com.eypg.tenpay.client.ClientResponseHandler;
import com.eypg.tenpay.client.TenpayHttpClient;
import com.eypg.tenpay.config.TenpayConfig;
import com.eypg.tenpay.util.TenpayUtil;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.DateUtil;
import com.eypg.util.MD5Util;
import com.eypg.util.MemCachedClientHelp;
import com.eypg.util.NewLotteryUtil;
import com.eypg.util.RandomUtil;
import com.eypg.util.StringUtil;
import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("TenpayAction")
public class TenpayAction extends ActionSupport {

    private static final long serialVersionUID = 2460509323066698846L;
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

    //---------------生成订单号 开始------------------------
    //当前时间 yyyyMMddHHmmss
    private String currTime = TenpayUtil.getCurrTime();
    //8位日期
    private String strTime = currTime.substring(8, currTime.length());
    //四位随机数
    private String strRandom = TenpayUtil.buildRandom(4) + "";
    //10位序列号,可以自行调整。
    private String strReq = strTime + strRandom;
    //订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
    private String out_trade_no = strReq;
    private Integer moneyCount;
    private String productBody = "";
    private String productName;
    private String bank_type;
    private String hidUseBalance;
    private String integral;
    private String requestUrl;

    Random random = new Random();
    Calendar calendar = Calendar.getInstance();
    HttpServletRequest request = null;
    HttpServletResponse response = null;

    //---------------生成订单号 结束------------------------


    public String goPay() throws ServletException, IOException {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        boolean flag = false;
        //创建支付请求对象
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init();

        //设置密钥
        reqHandler.setKey(TenpayConfig.key);
        //设置支付网关
        reqHandler.setGateUrl("https://gw.tenpay.com/gateway/pay.htm");
        //-----------------------------
        //设置支付参数
        //-----------------------------
        moneyCount = moneyCount * 100;
        reqHandler.setParameter("partner", TenpayConfig.partner);                //商户号
        reqHandler.setParameter("out_trade_no", out_trade_no);        //商家订单号
        reqHandler.setParameter("total_fee", moneyCount + "");                    //商品金额,以分为单位
        reqHandler.setParameter("return_url", TenpayConfig.return_url);            //交易完成后跳转的URL
        reqHandler.setParameter("notify_url", TenpayConfig.notify_url);            //接收财付通通知的URL
        reqHandler.setParameter("body", ApplicationListenerImpl.sysConfigureJson.getSaitName() + "(" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + ")");                        //商品描述
        reqHandler.setParameter("bank_type", bank_type);            //银行类型(中介担保时此参数无效)
//		reqHandler.setParameter("spbill_create_ip",request.getRemoteAddr());   //用户的公网ip，不是商户服务器IP
        reqHandler.setParameter("spbill_create_ip", request.getHeader("X-Real-IP"));   //用户的公网ip，不是商户服务器IP
        reqHandler.setParameter("fee_type", "1");                    //币种，1人民币
        reqHandler.setParameter("subject", productName);              //商品名称(中介交易时必填)

        //系统可选参数
        reqHandler.setParameter("sign_type", "MD5");                //签名类型,默认：MD5
        reqHandler.setParameter("service_version", "1.0");            //版本号，默认为1.0
        reqHandler.setParameter("input_charset", "UTF-8");            //字符编码
        reqHandler.setParameter("sign_key_index", "1");             //密钥序号


        //业务可选参数
        reqHandler.setParameter("attach", integral);                      //附加数据，原样返回
//		reqHandler.setParameter("product_fee", "");                 //商品费用，必须保证transport_fee + product_fee=total_fee
//		reqHandler.setParameter("transport_fee", "0");               //物流费用，必须保证transport_fee + product_fee=total_fee
//		reqHandler.setParameter("time_start", currTime);            //订单生成时间，格式为yyyymmddhhmmss
//		reqHandler.setParameter("time_expire", "");                 //订单失效时间，格式为yyyymmddhhmmss
//		reqHandler.setParameter("buyer_id", "");                    //买方财付通账号
//		reqHandler.setParameter("goods_tag", "");                   //商品标记
//		reqHandler.setParameter("trade_mode", "1");                 //交易模式，1即时到账(默认)，2中介担保，3后台选择（买家进支付中心列表选择）
//		reqHandler.setParameter("transport_desc", "");              //物流说明
//		reqHandler.setParameter("trans_type", "1");                  //交易类型，1实物交易，2虚拟交易
//		reqHandler.setParameter("agentid", "");                     //平台ID
//		reqHandler.setParameter("agent_type", "");                  //代理模式，0无代理(默认)，1表示卡易售模式，2表示网店模式
//		reqHandler.setParameter("seller_id", "");                   //卖家商户号，为空则等同于partner


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
                    money = money * 0.01;
                    consumetable.setBuyCount(buyConut); //要花的钱 7
                    consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
                    consumetable.setInterfaceType("tenPay");
                    consumetable.setMoney(money); //财付通充值的钱 6
                    consumetable.setOutTradeNo(out_trade_no);
                    consumetable.setUserId(Integer.parseInt(userId));
                    consumetableService.add(consumetable);
//					for (ProductCart productCart : productCartList) {
//						try {
//							consumerdetail = new Consumerdetail();
//							consumerdetail.setBuyCount(productCart.getCount());
//							consumerdetail.setBuyMoney(Double.parseDouble(productCart.getCount().toString()));
//							consumerdetail.setConsumetableId(out_trade_no);
//							consumerdetail.setProductId(productCart.getProductId());
//							consumerdetail.setProductName(productCart.getProductName());
//							consumerdetail.setProductPeriod(productCart.getProductPeriod());
//							consumerdetail.setProductTitle(productCart.getProductTitle());
//							consumerdetailService.add(consumerdetail);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
                } catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                }
            } else {
                flag = false;
                Struts2Utils.render("text/html", "<script>alert(\"购物车中有商品已经满员，请选择下一期！\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
            }
        }

        //请求的url
        requestUrl = reqHandler.getRequestURL();

        //获取debug信息,建议把请求和debug信息写入日志，方便定位问题
        String debuginfo = reqHandler.getDebugInfo();
        System.out.println("requestUrl:  " + requestUrl);
        System.out.println("sign_String:  " + debuginfo);
//		return request.getRequestDispatcher(requestUrl).forward(request, response);
        if (flag) {
            response.sendRedirect(requestUrl);
        } else {
            Struts2Utils.render("text/html", "<script>alert(\"购物车中有商品已经满员，请该商品的选择下一期！\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
        }
        return null;
    }

    public String returnUrl() {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        //创建支付应答对象
        ResponseHandler resHandler = new ResponseHandler(request, response);
        resHandler.setKey(TenpayConfig.key);
        System.out.println("前台回调返回参数:" + resHandler.getAllParameters());
        boolean flag = false;
        String buyproduct = "";//  已经被买完的商品
        //判断签名
        if (resHandler.isTenpaySign()) {
            //通知id
            String notify_id = resHandler.getParameter("notify_id");
            //商户订单号
            String out_trade_no = resHandler.getParameter("out_trade_no");
            //财付通订单号
            String transaction_id = resHandler.getParameter("transaction_id");
            //金额,以分为单位
            String total_fee = resHandler.getParameter("total_fee");
            //如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
            String discount = resHandler.getParameter("discount");
            //支付结果
            String trade_state = resHandler.getParameter("trade_state");
            //交易模式，1即时到账，2中介担保
            String trade_mode = resHandler.getParameter("trade_mode");

            String integral = resHandler.getParameter("attach");

            System.err.println("returnUrl   integral:" + integral);

            if ("1".equals(trade_mode)) {       //即时到账
                if ("0".equals(trade_state)) {
                    //------------------------------
                    //即时到账处理业务开始
                    //------------------------------
                    try {
                        String key = MD5Util.encode(transaction_id);
                        if (MemCachedClientHelp.getIMemcachedCache().get(key) == null) {
                            MemCachedClientHelp.getIMemcachedCache().put(key, "y", new Date(12 * 60 * 60 * 1000));
                            productCartList = new ArrayList<ProductCart>();
                            successCartList = new ArrayList<ProductJSON>();
                            try {
                                consumetable = consumetableService.findByOutTradeNo(out_trade_no);//找到订单
                                double money = Double.parseDouble(String.valueOf(total_fee));//充值的钱 *100
                                money = money * 0.01;
                                System.err.println(consumetable.getMoney());
                                System.err.println(money);
                                if (consumetable.getMoney().equals(money)) {
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
//													Struts2Utils.render("text/html", "<script>alert(\"购物车中有商品已经满员，请重新购买！\");window.location.href=\"/mycart/index.html\";</script>","encoding:UTF-8");
//													flag = false;
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
                                                    if (StringUtil.isNotBlank(integral) || !integral.equals("0")) {
                                                        if (user.getBalance() >= (count - (Integer.parseInt(integral) / 100))) {
                                                            Double temp = user.getBalance() - (count - (Integer.parseInt(integral) / 100));
                                                            user.setBalance(temp);
//																userService.add(user);

                                                            consumerdetail = new Consumerdetail();
                                                            consumerdetail.setBuyCount(count);
                                                            consumerdetail.setBuyMoney(Double.parseDouble(String.valueOf(count)));
                                                            consumerdetail.setConsumetableId(out_trade_no);
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

                                                            consumerdetail = new Consumerdetail();
                                                            consumerdetail.setBuyCount(count);
                                                            consumerdetail.setBuyMoney(Double.parseDouble(String.valueOf(count)));
                                                            consumerdetail.setConsumetableId(out_trade_no);
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

//														if(spellbuyproduct.getSpStatus()==2){
//															/**
//															 * 开奖工具类
//															 */
//															String lotteryId = MD5Util.encode(String.valueOf(spellbuyproduct.getSpellbuyProductId()));
//															if(MemCachedClientHelp.getIMemcachedCache().get(lotteryId)==null){
//																MemCachedClientHelp.getIMemcachedCache().put(lotteryId, "y",new Date(12*60*60*1000));
//																new Thread(){
//																	public void run(){
//																		try {
//																			newLotteryUtil.lottery(productCart);
//																		} catch (Exception e) {
//																			e.printStackTrace();
//																		}
//																	}
//																}.start();
//															}
//														}
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
                } else {
                    System.out.println("即时到帐付款失败");
                    paymentStatus = "error";
                }
            } else if ("2".equals(trade_mode)) {    //中介担保
                if ("0".equals(trade_state)) {
                    //------------------------------
                    //中介担保处理业务开始
                    //------------------------------

                    //注意交易单不要重复处理
                    //注意判断返回金额

                    //------------------------------
                    //中介担保处理业务完毕
                    //------------------------------

                    System.out.println("中介担保付款成功");
                } else {
                    System.out.println("trade_state=" + trade_state);
                }
            }
        } else {
            System.out.println("认证签名失败");
        }

        //获取debug信息,建议把debug信息写入日志，方便定位问题
        String debuginfo = resHandler.getDebugInfo();
        System.out.println("debuginfo:" + debuginfo);
        return "success";
    }

    public String notifyUrl() throws Exception {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        boolean flag = false;
        String buyproduct = "";//  已经被买完的商品
        //创建支付应答对象
        ResponseHandler resHandler = new ResponseHandler(request, response);
        resHandler.setKey(TenpayConfig.key);
        System.out.println("后台回调返回参数:" + resHandler.getAllParameters());
        //判断签名
        if (resHandler.isTenpaySign()) {
            //通知id
            String notify_id = resHandler.getParameter("notify_id");
            //创建请求对象
            RequestHandler queryReq = new RequestHandler(null, null);
            //通信对象
            TenpayHttpClient httpClient = new TenpayHttpClient();
            //应答对象
            ClientResponseHandler queryRes = new ClientResponseHandler();

            String integral = resHandler.getParameter("attach");

            //通过通知ID查询，确保通知来至财付通
            queryReq.init();
            queryReq.setKey(TenpayConfig.key);
            queryReq.setGateUrl("https://gw.tenpay.com/gateway/simpleverifynotifyid.xml");
            queryReq.setParameter("partner", TenpayConfig.partner);
            queryReq.setParameter("notify_id", notify_id);
            queryReq.setParameter("attach", integral);
            //通信对象
            httpClient.setTimeOut(5);
            //设置请求内容
            httpClient.setReqContent(queryReq.getRequestURL());
            System.out.println("验证ID请求字符串:" + queryReq.getRequestURL());
            //后台调用
            if (httpClient.call()) {
                //设置结果参数
                queryRes.setContent(httpClient.getResContent());
                System.out.println("验证ID返回字符串:" + httpClient.getResContent());
                queryRes.setKey(TenpayConfig.key);
                //获取id验证返回状态码，0表示此通知id是财付通发起
                String retcode = queryRes.getParameter("retcode");
                //商户订单号
                String out_trade_no = resHandler.getParameter("out_trade_no");
                //财付通订单号
                String transaction_id = resHandler.getParameter("transaction_id");
                //金额,以分为单位
                String total_fee = resHandler.getParameter("total_fee");
                //如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
                String discount = resHandler.getParameter("discount");
                //支付结果
                String trade_state = resHandler.getParameter("trade_state");
                //交易模式，1即时到账，2中介担保
                String trade_mode = resHandler.getParameter("trade_mode");
                //判断签名及结果
                if (queryRes.isTenpaySign() && "0".equals(retcode)) {
                    System.out.println("id验证成功");
                    if ("1".equals(trade_mode)) {       //即时到账
                        if ("0".equals(trade_state)) {
                            //------------------------------
                            //即时到账处理业务开始
                            try {
                                consumetable = consumetableService.findByOutTradeNo(out_trade_no);
                                double money = Double.parseDouble(String.valueOf(total_fee));
                                money = money * 0.01;
                                System.err.println(consumetable.getMoney());
                                System.err.println(money);
                                if (consumetable.getMoney().equals(money) && consumetable.getTransactionId() == null) {
                                    consumetable.setTransactionId(transaction_id);
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

                            //------------------------------

                            //处理数据库逻辑
                            //注意交易单不要重复处理
                            //注意判断返回金额


                            //------------------------------
                            //即时到账处理业务完毕
                            //------------------------------

//							System.out.println("即时到账支付成功");
                            //给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
                            resHandler.sendToCFT("success");
                        } else {
                            System.out.println("即时到账支付失败");
                            resHandler.sendToCFT("fail");
                        }
                    } else if ("2".equals(trade_mode)) {    //中介担保
                        //------------------------------
                        //中介担保处理业务开始
                        //------------------------------

                        //处理数据库逻辑
                        //注意交易单不要重复处理
                        //注意判断返回金额

                        int iStatus = TenpayUtil.toInt(trade_state);
                        switch (iStatus) {
                            case 0:        //付款成功

                                break;
                            case 1:        //交易创建

                                break;
                            case 2:        //收获地址填写完毕

                                break;
                            case 4:        //卖家发货成功

                                break;
                            case 5:        //买家收货确认，交易成功

                                break;
                            case 6:        //交易关闭，未完成超时关闭

                                break;
                            case 7:        //修改交易价格成功

                                break;
                            case 8:        //买家发起退款

                                break;
                            case 9:        //退款成功

                                break;
                            case 10:    //退款关闭

                                break;
                            default:
                        }

                        //------------------------------
                        //中介担保处理业务完毕
                        //------------------------------

                        System.out.println("trade_state = " + trade_state);
                        //给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
                        resHandler.sendToCFT("success");
                    }
                } else {
                    //错误时，返回结果未签名，记录retcode、retmsg看失败详情。
                    System.out.println("查询验证签名失败或id验证失败" + ",retcode:" + queryRes.getParameter("retcode"));
                }
            } else {
                System.out.println("后台调用通信失败");
                System.out.println(httpClient.getResponseCode());
                System.out.println(httpClient.getErrInfo());
                //有可能因为网络原因，请求已经处理，但未收到应答。
            }
        } else {
            System.out.println("通知签名验证失败");
        }
        return null;
    }

    public Integer getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(Integer moneyCount) {
        this.moneyCount = moneyCount;
    }

    public String getProductBody() {
        return productBody;
    }

    public void setProductBody(String productBody) {
        this.productBody = productBody;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
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

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getHidUseBalance() {
        return hidUseBalance;
    }

    public void setHidUseBalance(String hidUseBalance) {
        this.hidUseBalance = hidUseBalance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Consumetable getConsumetable() {
        return consumetable;
    }

    public void setConsumetable(Consumetable consumetable) {
        this.consumetable = consumetable;
    }

    public List<ProductCart> getProductCartList() {
        return productCartList;
    }

    public void setProductCartList(List<ProductCart> productCartList) {
        this.productCartList = productCartList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Consumerdetail getConsumerdetail() {
        return consumerdetail;
    }

    public void setConsumerdetail(Consumerdetail consumerdetail) {
        this.consumerdetail = consumerdetail;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }


}
