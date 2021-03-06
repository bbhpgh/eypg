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

    //---------------??????????????? ??????------------------------
    //???????????? yyyyMMddHHmmss
    private String currTime = TenpayUtil.getCurrTime();
    //8?????????
    private String strTime = currTime.substring(8, currTime.length());
    //???????????????
    private String strRandom = TenpayUtil.buildRandom(4) + "";
    //10????????????,?????????????????????
    private String strReq = strTime + strRandom;
    //???????????????????????????????????????????????????????????????????????????????????????????????????????????????
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

    //---------------??????????????? ??????------------------------


    public String goPay() throws ServletException, IOException {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        boolean flag = false;
        //????????????????????????
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init();

        //????????????
        reqHandler.setKey(TenpayConfig.key);
        //??????????????????
        reqHandler.setGateUrl("https://gw.tenpay.com/gateway/pay.htm");
        //-----------------------------
        //??????????????????
        //-----------------------------
        moneyCount = moneyCount * 100;
        reqHandler.setParameter("partner", TenpayConfig.partner);                //?????????
        reqHandler.setParameter("out_trade_no", out_trade_no);        //???????????????
        reqHandler.setParameter("total_fee", moneyCount + "");                    //????????????,???????????????
        reqHandler.setParameter("return_url", TenpayConfig.return_url);            //????????????????????????URL
        reqHandler.setParameter("notify_url", TenpayConfig.notify_url);            //????????????????????????URL
        reqHandler.setParameter("body", ApplicationListenerImpl.sysConfigureJson.getSaitName() + "(" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + ")");                        //????????????
        reqHandler.setParameter("bank_type", bank_type);            //????????????(??????????????????????????????)
//		reqHandler.setParameter("spbill_create_ip",request.getRemoteAddr());   //???????????????ip????????????????????????IP
        reqHandler.setParameter("spbill_create_ip", request.getHeader("X-Real-IP"));   //???????????????ip????????????????????????IP
        reqHandler.setParameter("fee_type", "1");                    //?????????1?????????
        reqHandler.setParameter("subject", productName);              //????????????(?????????????????????)

        //??????????????????
        reqHandler.setParameter("sign_type", "MD5");                //????????????,?????????MD5
        reqHandler.setParameter("service_version", "1.0");            //?????????????????????1.0
        reqHandler.setParameter("input_charset", "UTF-8");            //????????????
        reqHandler.setParameter("sign_key_index", "1");             //????????????


        //??????????????????
        reqHandler.setParameter("attach", integral);                      //???????????????????????????
//		reqHandler.setParameter("product_fee", "");                 //???????????????????????????transport_fee + product_fee=total_fee
//		reqHandler.setParameter("transport_fee", "0");               //???????????????????????????transport_fee + product_fee=total_fee
//		reqHandler.setParameter("time_start", currTime);            //??????????????????????????????yyyymmddhhmmss
//		reqHandler.setParameter("time_expire", "");                 //??????????????????????????????yyyymmddhhmmss
//		reqHandler.setParameter("buyer_id", "");                    //?????????????????????
//		reqHandler.setParameter("goods_tag", "");                   //????????????
//		reqHandler.setParameter("trade_mode", "1");                 //???????????????1????????????(??????)???2???????????????3???????????????????????????????????????????????????
//		reqHandler.setParameter("transport_desc", "");              //????????????
//		reqHandler.setParameter("trans_type", "1");                  //???????????????1???????????????2????????????
//		reqHandler.setParameter("agentid", "");                     //??????ID
//		reqHandler.setParameter("agent_type", "");                  //???????????????0?????????(??????)???1????????????????????????2??????????????????
//		reqHandler.setParameter("seller_id", "");                   //????????????????????????????????????partner


        /**
         * ????????????
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
                            //??????????????????
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
//			moneyCount  ????????????????????? 600 
//			buyConut    ????????????   7
//			hidUseBalance    ???????????? 1   buyConut=moneyCount+hidUseBalance
            if (flag) {
                try {
                    consumetable = new Consumetable();
                    double money = Double.parseDouble(String.valueOf(moneyCount));
                    money = money * 0.01;
                    consumetable.setBuyCount(buyConut); //???????????? 7
                    consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
                    consumetable.setInterfaceType("tenPay");
                    consumetable.setMoney(money); //????????????????????? 6
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
                Struts2Utils.render("text/html", "<script>alert(\"?????????????????????????????????????????????????????????\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
            }
        }

        //?????????url
        requestUrl = reqHandler.getRequestURL();

        //??????debug??????,??????????????????debug???????????????????????????????????????
        String debuginfo = reqHandler.getDebugInfo();
        System.out.println("requestUrl:  " + requestUrl);
        System.out.println("sign_String:  " + debuginfo);
//		return request.getRequestDispatcher(requestUrl).forward(request, response);
        if (flag) {
            response.sendRedirect(requestUrl);
        } else {
            Struts2Utils.render("text/html", "<script>alert(\"?????????????????????????????????????????????????????????????????????\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
        }
        return null;
    }

    public String returnUrl() {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        //????????????????????????
        ResponseHandler resHandler = new ResponseHandler(request, response);
        resHandler.setKey(TenpayConfig.key);
        System.out.println("????????????????????????:" + resHandler.getAllParameters());
        boolean flag = false;
        String buyproduct = "";//  ????????????????????????
        //????????????
        if (resHandler.isTenpaySign()) {
            //??????id
            String notify_id = resHandler.getParameter("notify_id");
            //???????????????
            String out_trade_no = resHandler.getParameter("out_trade_no");
            //??????????????????
            String transaction_id = resHandler.getParameter("transaction_id");
            //??????,???????????????
            String total_fee = resHandler.getParameter("total_fee");
            //???????????????????????????discount?????????total_fee+discount=????????????total_fee
            String discount = resHandler.getParameter("discount");
            //????????????
            String trade_state = resHandler.getParameter("trade_state");
            //???????????????1???????????????2????????????
            String trade_mode = resHandler.getParameter("trade_mode");

            String integral = resHandler.getParameter("attach");

            System.err.println("returnUrl   integral:" + integral);

            if ("1".equals(trade_mode)) {       //????????????
                if ("0".equals(trade_state)) {
                    //------------------------------
                    //??????????????????????????????
                    //------------------------------
                    try {
                        String key = MD5Util.encode(transaction_id);
                        if (MemCachedClientHelp.getIMemcachedCache().get(key) == null) {
                            MemCachedClientHelp.getIMemcachedCache().put(key, "y", new Date(12 * 60 * 60 * 1000));
                            productCartList = new ArrayList<ProductCart>();
                            successCartList = new ArrayList<ProductJSON>();
                            try {
                                consumetable = consumetableService.findByOutTradeNo(out_trade_no);//????????????
                                double money = Double.parseDouble(String.valueOf(total_fee));//???????????? *100
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

                                    //???????????????
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
                                                    //??????????????????
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
                                                    buyproduct += "????????????????????? <a href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/products/" + spellbuyproduct.getSpellbuyProductId() + ".html\" targer=\"_blank\">" + product.getProductName() + "</a>  ????????????.<br/>";
//													Struts2Utils.render("text/html", "<script>alert(\"??????????????????????????????????????????????????????\");window.location.href=\"/mycart/index.html\";</script>","encoding:UTF-8");
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
                                                //????????????????????????
                                                Integer count = 0;
                                                //??????????????????
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
                                                                commissionquery.setDescription(ApplicationListenerImpl.sysConfigureJson.getShortName() + "????????????(" + productCart.getProductId() + ")????????????");
                                                                commissionquery.setInvitedId(user.getInvite());
                                                                commissionquery.setToUserId(user.getUserId());
                                                                commissionqueryService.add(commissionquery);
                                                            }
                                                        } else {
                                                            Struts2Utils.render("text/html", "<script>alert(\"???????????????????????????????????????\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
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
                                                                commissionquery.setDescription(ApplicationListenerImpl.sysConfigureJson.getShortName() + "????????????(" + productCart.getProductId() + ")????????????");
                                                                commissionquery.setInvitedId(user.getInvite());
                                                                commissionquery.setToUserId(user.getUserId());
                                                                commissionqueryService.add(commissionquery);
                                                            }
                                                        } else {
                                                            Struts2Utils.render("text/html", "<script>alert(\"???????????????????????????????????????\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
                                                        }
                                                    }


                                                    spellbuyproduct.setSpellbuyCount(spellbuyproduct.getSpellbuyCount() + count);
                                                    if (spellbuyproduct.getSpellbuyCount() >= productCart.getProductPrice()) {
                                                        spellbuyproduct.setSpellbuyCount(productCart.getProductPrice());
                                                        //????????????
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
                                                        commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "????????????(" + spellbuyproduct.getSpellbuyProductId() + ")????????????");
                                                        commissionpoints.setPay("-" + integral);
                                                        commissionpoints.setToUserId(Integer.parseInt(userId));
                                                        commissionpointsService.add(commissionpoints);
                                                        Integer points = user.getCommissionPoints();
                                                        user.setCommissionPoints(points - Integer.parseInt(integral));
                                                    }
                                                    commissionpoints = new Commissionpoints();
                                                    commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                                                    commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "????????????(" + spellbuyproduct.getSpellbuyProductId() + ")??????" + count + "???????????????");
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
//															 * ???????????????
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
                                         * ???????????????
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

                            //?????????????????????????????????
                            //????????????????????????

                            //------------------------------
                            //??????????????????????????????
                            //------------------------------
                            request.setAttribute("buyproduct", buyproduct);
                            System.out.println("????????????????????????");
                            paymentStatus = "success";

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("????????????????????????");
                    paymentStatus = "error";
                }
            } else if ("2".equals(trade_mode)) {    //????????????
                if ("0".equals(trade_state)) {
                    //------------------------------
                    //??????????????????????????????
                    //------------------------------

                    //?????????????????????????????????
                    //????????????????????????

                    //------------------------------
                    //??????????????????????????????
                    //------------------------------

                    System.out.println("????????????????????????");
                } else {
                    System.out.println("trade_state=" + trade_state);
                }
            }
        } else {
            System.out.println("??????????????????");
        }

        //??????debug??????,?????????debug???????????????????????????????????????
        String debuginfo = resHandler.getDebugInfo();
        System.out.println("debuginfo:" + debuginfo);
        return "success";
    }

    public String notifyUrl() throws Exception {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        boolean flag = false;
        String buyproduct = "";//  ????????????????????????
        //????????????????????????
        ResponseHandler resHandler = new ResponseHandler(request, response);
        resHandler.setKey(TenpayConfig.key);
        System.out.println("????????????????????????:" + resHandler.getAllParameters());
        //????????????
        if (resHandler.isTenpaySign()) {
            //??????id
            String notify_id = resHandler.getParameter("notify_id");
            //??????????????????
            RequestHandler queryReq = new RequestHandler(null, null);
            //????????????
            TenpayHttpClient httpClient = new TenpayHttpClient();
            //????????????
            ClientResponseHandler queryRes = new ClientResponseHandler();

            String integral = resHandler.getParameter("attach");

            //????????????ID????????????????????????????????????
            queryReq.init();
            queryReq.setKey(TenpayConfig.key);
            queryReq.setGateUrl("https://gw.tenpay.com/gateway/simpleverifynotifyid.xml");
            queryReq.setParameter("partner", TenpayConfig.partner);
            queryReq.setParameter("notify_id", notify_id);
            queryReq.setParameter("attach", integral);
            //????????????
            httpClient.setTimeOut(5);
            //??????????????????
            httpClient.setReqContent(queryReq.getRequestURL());
            System.out.println("??????ID???????????????:" + queryReq.getRequestURL());
            //????????????
            if (httpClient.call()) {
                //??????????????????
                queryRes.setContent(httpClient.getResContent());
                System.out.println("??????ID???????????????:" + httpClient.getResContent());
                queryRes.setKey(TenpayConfig.key);
                //??????id????????????????????????0???????????????id??????????????????
                String retcode = queryRes.getParameter("retcode");
                //???????????????
                String out_trade_no = resHandler.getParameter("out_trade_no");
                //??????????????????
                String transaction_id = resHandler.getParameter("transaction_id");
                //??????,???????????????
                String total_fee = resHandler.getParameter("total_fee");
                //???????????????????????????discount?????????total_fee+discount=????????????total_fee
                String discount = resHandler.getParameter("discount");
                //????????????
                String trade_state = resHandler.getParameter("trade_state");
                //???????????????1???????????????2????????????
                String trade_mode = resHandler.getParameter("trade_mode");
                //?????????????????????
                if (queryRes.isTenpaySign() && "0".equals(retcode)) {
                    System.out.println("id????????????");
                    if ("1".equals(trade_mode)) {       //????????????
                        if ("0".equals(trade_state)) {
                            //------------------------------
                            //??????????????????????????????
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

                            //?????????????????????
                            //?????????????????????????????????
                            //????????????????????????


                            //------------------------------
                            //??????????????????????????????
                            //------------------------------

//							System.out.println("????????????????????????");
                            //????????????????????????????????????????????????????????????????????????????????????????????????
                            resHandler.sendToCFT("success");
                        } else {
                            System.out.println("????????????????????????");
                            resHandler.sendToCFT("fail");
                        }
                    } else if ("2".equals(trade_mode)) {    //????????????
                        //------------------------------
                        //??????????????????????????????
                        //------------------------------

                        //?????????????????????
                        //?????????????????????????????????
                        //????????????????????????

                        int iStatus = TenpayUtil.toInt(trade_state);
                        switch (iStatus) {
                            case 0:        //????????????

                                break;
                            case 1:        //????????????

                                break;
                            case 2:        //????????????????????????

                                break;
                            case 4:        //??????????????????

                                break;
                            case 5:        //?????????????????????????????????

                                break;
                            case 6:        //????????????????????????????????????

                                break;
                            case 7:        //????????????????????????

                                break;
                            case 8:        //??????????????????

                                break;
                            case 9:        //????????????

                                break;
                            case 10:    //????????????

                                break;
                            default:
                        }

                        //------------------------------
                        //??????????????????????????????
                        //------------------------------

                        System.out.println("trade_state = " + trade_state);
                        //????????????????????????????????????????????????????????????????????????????????????????????????
                        resHandler.sendToCFT("success");
                    }
                } else {
                    //??????????????????????????????????????????retcode???retmsg??????????????????
                    System.out.println("???????????????????????????id????????????" + ",retcode:" + queryRes.getParameter("retcode"));
                }
            } else {
                System.out.println("????????????????????????");
                System.out.println(httpClient.getResponseCode());
                System.out.println(httpClient.getErrInfo());
                //????????????????????????????????????????????????????????????????????????
            }
        } else {
            System.out.println("????????????????????????");
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
