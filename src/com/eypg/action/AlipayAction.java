package com.eypg.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.eypg.alipay.config.AlipayConfig;
import com.eypg.alipay.util.AlipayNotify;
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
import com.opensymphony.xwork2.ActionSupport;

public class AlipayAction extends ActionSupport {

    private static final long serialVersionUID = -8970081284102469306L;

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

    //???????????? yyyyMMddHHmmss
    private String currTime = TenpayUtil.getCurrTime();
    //8?????????
    private String strTime = currTime.substring(8, currTime.length());
    //???????????????
    private String strRandom = TenpayUtil.buildRandom(4) + "";
    //10????????????,?????????????????????
    private String strReq = strTime + strRandom;
    Random random = new Random();
    HttpServletRequest request = null;
    HttpServletResponse response = null;


    public String goPay() throws UnsupportedEncodingException {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        boolean flag = false;
        //????????????
        String payment_type = "1";
        //?????????????????????
        //?????????????????????????????????
//		http://124.193.138.90/tenpay/notifyUrl.action
        String notify_url = AlipayConfig.notify_url;
        //???http://??????????????????????????????????id=123?????????????????????
        //????????????????????????????????????
//		http://124.193.138.90/tenpay/returnUrl.action
        String return_url = AlipayConfig.return_url;
        //???http://??????????????????????????????????id=123????????????????????????????????????http://localhost/
        //?????????????????????
        String seller_email = AlipayConfig.mail;
        //??????
        //???????????????
        String out_trade_no = strReq;
        //???????????????????????????????????????????????????
        //????????????
        String subject = ApplicationListenerImpl.sysConfigureJson.getSaitName() + "(" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + ")";
        //??????
        //????????????
        String total_fee = moneyCount.toString();
        //??????
        //????????????
        String body = ApplicationListenerImpl.sysConfigureJson.getSaitName() + "(" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + ")";
        //??????????????????
        String show_url = ApplicationListenerImpl.sysConfigureJson.getWwwUrl();
        //??????http://?????????????????????????????????http://www.xxx.com/myorder.html
        //??????????????????
        String anti_phishing_key = currTime;
        //??????????????????????????????submit??????query_timestamp??????
        //????????????IP??????
//		String exter_invoke_ip = request.getRemoteAddr();
        String exter_invoke_ip = request.getHeader("X-Real-IP");
        //?????????????????????IP???????????????221.0.0.1

        //??????????????????????????????
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", payment_type);
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("seller_email", seller_email);
        sParaTemp.put("out_trade_no", out_trade_no);
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", total_fee);
        sParaTemp.put("body", body);
        sParaTemp.put("show_url", show_url);
        sParaTemp.put("anti_phishing_key", anti_phishing_key);
        sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
        sParaTemp.put("extra_common_param", integral);

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
                    consumetable.setBuyCount(buyConut); //???????????? 7
                    consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
                    consumetable.setInterfaceType("aliPay");
                    consumetable.setMoney(money); //????????????????????? 6
                    consumetable.setOutTradeNo(out_trade_no);
                    consumetable.setUserId(Integer.parseInt(userId));
                    consumetableService.add(consumetable);
                } catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                }
            } else {
                flag = false;
                Struts2Utils.render("text/html", "<script>alert(\"?????????????????????????????????????????????????????????\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
            }
        }

        //????????????
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "??????");
        System.out.println(sHtmlText);
        if (flag) {
            Struts2Utils.render("text/html", sHtmlText, "encoding:UTF-8");
        } else {
            Struts2Utils.render("text/html", "<script>alert(\"?????????????????????????????????????????????????????????????????????\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
        }
        return null;
    }

    public String returnUrl() throws UnsupportedEncodingException {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        boolean flag = false;
        String buyproduct = "";//  ????????????????????????
        //???????????????GET??????????????????
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //????????????????????????????????????????????????????????????mysign???sign??????????????????????????????????????????
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //???????????????????????????????????????????????????????????????????????????????????????????????????(??????????????????)//
        //???????????????
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //??????????????????
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //??????
        String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
        //????????????
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
        //???????????????????????????????????????????????????????????????????????????????????????????????????(??????????????????)//
        String integral = new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"), "UTF-8");

        //??????????????????????????????
        boolean verify_result = AlipayNotify.verify(params);
        if (verify_result) {//????????????
            //???????????????????????????????????????????????????

            //????????????????????????????????????????????????????????????????????????????????????
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                //????????????????????????????????????????????????????????????
                //?????????????????????????????????????????????out_trade_no????????????????????????????????????????????????????????????????????????????????????????????????
                //??????????????????????????????????????????????????????
                //??????????????????????????????
                //------------------------------
                try {
                    String key = MD5Util.encode(trade_no);
                    if (MemCachedClientHelp.getIMemcachedCache().get(key) == null) {
                        MemCachedClientHelp.getIMemcachedCache().put(key, "y", new Date(12 * 60 * 60 * 1000));
                        productCartList = new ArrayList<ProductCart>();
                        successCartList = new ArrayList<ProductJSON>();
                        try {
                            consumetable = consumetableService.findByOutTradeNo(out_trade_no);//????????????
                            double money = Double.parseDouble(String.valueOf(total_fee));//????????????
                            System.err.println(consumetable.getMoney());
                            System.err.println(money);
                            if (consumetable.getMoney().equals(money) && consumetable.getTransactionId().equals(trade_no)) {
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
//												Struts2Utils.render("text/html", "<script>alert(\"??????????????????????????????????????????????????????\");window.location.href=\"/mycart/index.html\";</script>","encoding:UTF-8");
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
                                                if (StringUtil.isNotBlank(integral) && !integral.equals("0")) {
                                                    if (user.getBalance() >= (count - (Integer.parseInt(integral) / 100))) {
                                                        Double temp = user.getBalance() - (count - (Integer.parseInt(integral) / 100));
                                                        user.setBalance(temp);
//															userService.add(user);

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
                                                        //userService.add(user);
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
            }
            //?????????????????????????????????
            System.out.println("????????????<br />");
            //????????????????????????????????????????????????????????????????????????????????????
            //////////////////////////////////////////////////////////////////////////////////////////
        } else {
            //?????????????????????????????????
            System.out.println("????????????");
            paymentStatus = "error";
        }
        return "success";
    }

    public String notifyUrl() throws Exception {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        boolean flag = false;
        //???????????????POST??????????????????
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //????????????????????????????????????????????????????????????mysign???sign??????????????????????????????????????????
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }

        //???????????????????????????????????????????????????????????????????????????????????????????????????(??????????????????)//
        //???????????????

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //??????????????????
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
        String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
        //????????????
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

        String integral = new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"), "UTF-8");
        //???????????????????????????????????????????????????????????????????????????????????????????????????(??????????????????)//
        if (AlipayNotify.verify(params)) {//????????????
            //////////////////////////////////////////////////////////////////////////////////////////
            //???????????????????????????????????????????????????

            //????????????????????????????????????????????????????????????????????????????????????

            if (trade_status.equals("TRADE_FINISHED")) {
                //????????????????????????????????????????????????????????????
                //?????????????????????????????????????????????out_trade_no????????????????????????????????????????????????????????????????????????????????????????????????
                //??????????????????????????????????????????????????????
                //??????????????????????????????
                //?????????
                //?????????????????????????????????????????????
                //1?????????????????????????????????????????????????????????
                //2???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //????????????????????????????????????????????????????????????
                //?????????????????????????????????????????????out_trade_no????????????????????????????????????????????????????????????????????????????????????????????????
                //??????????????????????????????????????????????????????
                try {
                    consumetable = consumetableService.findByOutTradeNo(out_trade_no);
                    double money = Double.parseDouble(String.valueOf(total_fee));
                    System.err.println(consumetable.getMoney());
                    System.err.println(money);
                    if (consumetable.getMoney().equals(money) && consumetable.getTransactionId() == null) {
                        consumetable.setTransactionId(trade_no);
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
                //?????????
                //?????????????????????????????????????????????????????????????????????????????????????????????????????????
            }

            //????????????????????????????????????????????????????????????????????????????????????

            System.out.println("success");    //????????????????????????
            Struts2Utils.render("text/html", "success", "encoding:UTF-8");
            //////////////////////////////////////////////////////////////////////////////////////////
        } else {//????????????
            Struts2Utils.render("text/html", "fail", "encoding:UTF-8");
            System.out.println("fail");
        }
        return null;
    }

    public Integer getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(Integer moneyCount) {
        this.moneyCount = moneyCount;
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

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }


}
