package com.eypg.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.beans.factory.annotation.Qualifier;

import com.eypg.pojo.Commissionpoints;
import com.eypg.pojo.Commissionquery;
import com.eypg.pojo.Consumerdetail;
import com.eypg.pojo.Consumetable;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Lotteryproductutil;
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
import com.eypg.service.LotteryproductutilService;
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

public class CartPayAction extends ActionSupport {

    private static final long serialVersionUID = 2516892117051908997L;

    @Autowired
    @Qualifier("spellbuyproductService")
    private SpellbuyproductService spellbuyproductService;
    @Autowired
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    private RandomnumberService randomnumberService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConsumetableService consumetableService;
    @Autowired
    private ConsumerdetailService consumerdetailService;
    @Autowired
    private LatestlotteryService latestlotteryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private NewLotteryUtil newLotteryUtil;
    @Autowired
    CommissionqueryService commissionqueryService;
    @Autowired
    LotteryproductutilService lotteryproductutilService;
    @Autowired
    CommissionpointsService commissionpointsService;

    RandomUtil randomUtil = new RandomUtil();

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
    private String paymentStatus;

    private Consumetable consumetable;
    private Consumerdetail consumerdetail;
    private List<ProductCart> productCartList;
    private List<ProductJSON> successCartList;
    private ProductJSON productJSON;
    private ProductCart productCart;
    private Spellbuyproduct spellbuyproduct;
    private Spellbuyrecord spellbuyrecord;
    private Randomnumber randomnumber;
    private Latestlottery latestlottery;
    private Commissionquery commissionquery;
    private Lotteryproductutil lotteryproductutil;
    private Commissionpoints commissionpoints;
    private Product product;
    private String id;
    private User user;
    private String userId;
    private Integer moneyCount;
    private Integer userPayType;
    private String integral;
    private String hidUseBalance;

    private static String nodeAuthorizationURL = "https://www.yeepay.com/app-merchant-proxy/node";
    Random random = new Random();
    Calendar calendar = Calendar.getInstance();
    HttpServletRequest request = null;
    HttpServletResponse response = null;

    public String index() throws UnsupportedEncodingException {
        request = Struts2Utils.getRequest();
        productCartList = new ArrayList<ProductCart>();
        Cookie[] cookies = request.getCookies();
        JSONArray array = null;
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("buyProduct")) {
                    String product = new StringUtil().getUTF8URLDecoder(cookie.getValue());
                    if (product != null && !product.equals("")) {
                        array = JSONArray.fromObject(product);
                    }
                }
            }
        }
        Integer productCount = 0;
        if (array != null && !array.toString().equals("[{}]")) {
            for (int i = 0; i < array.size(); i++) {
                try {
                    JSONObject obj = (JSONObject) array.get(i);
                    productCart = new ProductCart();
                    product = productService.findById(obj.getString("pId"));
                    productCount++;
                    productCart.setMoneyCount(Integer.parseInt(obj.getString("num")));
                    productCart.setHeadImage(product.getHeadImage());
                    productCart.setProductCount(productCount);
                    productCart.setProductId(product.getProductId());
                    productCart.setProductName(product.getProductName());
                    productCart.setProductPrice(product.getProductPrice());
                    productCart.setProductTitle(product.getProductTitle());
                    productCartList.add(productCart);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "index";
    }

    public String payment() throws UnsupportedEncodingException {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        productCartList = new ArrayList<ProductCart>();
        JSONArray array = null;
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                    }
                }
                if (cookie.getName().equals("buyProduct")) {
                    String product = new StringUtil().getUTF8URLDecoder(cookie.getValue());
                    if (product != null && !product.equals("")) {
                        array = JSONArray.fromObject(product);
                    }
                }
            }
        }
        Integer moneyCount = 0;
        Integer productCount = 0;
        if (array != null && !array.toString().equals("[{}]")) {
            for (int i = 0; i < array.size(); i++) {
                try {
                    JSONObject obj = (JSONObject) array.get(i);
                    productCart = new ProductCart();
                    product = productService.findById(obj.getString("pId"));
                    moneyCount += Integer.parseInt(obj.getString("num"));
                    productCount++;
                    productCart.setMoneyCount(moneyCount);
                    productCart.setHeadImage(product.getHeadImage());
                    productCart.setProductCount(productCount);
                    productCart.setProductId(product.getProductId());
                    productCart.setProductName(product.getProductName());
                    productCart.setProductPrice(product.getProductPrice());
                    productCart.setProductTitle(product.getProductTitle());
                    productCartList.add(productCart);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "payment";
    }

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
        String keyValue = ApplicationListenerImpl.sysConfigureJson.getYeepayKey();   // ????????????
        // ?????????????????????????????????????????????
        String p0_Cmd = formatString("Buy");                                // ?????????????????????????????? ???Buy???
        String p1_MerId = ApplicationListenerImpl.sysConfigureJson.getYeepayPartner();        // ????????????
        String p2_Order = strReq;           // ???????????????
        String p3_Amt = moneyCount.toString();            // ????????????
        String p4_Cur = formatString("CNY");                                        // ????????????
        String p5_Pid = ApplicationListenerImpl.sysConfigureJson.getWwwUrl();                // ????????????
        String p6_Pcat = ApplicationListenerImpl.sysConfigureJson.getWwwUrl();            // ????????????
        String p7_Pdesc = ApplicationListenerImpl.sysConfigureJson.getWwwUrl();            // ????????????
        String p8_Url = ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/yeepay/notifyUrl.action";            // ???????????????????????????????????????
        String p9_SAF = "0";            // ???????????????????????? 0????????????  1:??????
        String pa_MP = integral;                // ??????????????????
        String pd_FrpId = "";        // ??????????????????
        // ????????????????????????
        pd_FrpId = pd_FrpId.toUpperCase();
        String pr_NeedResponse = formatString("1");    // ?????????"1"?????????????????????
        String hmac = formatString("");                                            // ???????????????

        // ??????MD5-HMAC??????
        hmac = PaymentForOnlineService.getReqMd5HmacForOnlinePayment(p0_Cmd,
                p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
                p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

        //??????????????????????????????
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
                if (cookie.getName().equals("buyProduct")) {
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
                        product = productService.findById(obj.getString("pId"));
                        moneyCount += Integer.parseInt(obj.getString("num"));
                        productCount++;
                        productCart.setMoneyCount(moneyCount);
                        productCart.setHeadImage(product.getHeadImage());
                        productCart.setProductCount(productCount);
                        productCart.setProductId(product.getProductId());
                        productCart.setProductName(product.getProductName());
                        productCart.setProductPrice(product.getProductPrice());
                        productCart.setProductTitle(product.getProductTitle());
                        productCartList.add(productCart);
                        flag = true;
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
                    consumetable.setInterfaceType("yeePay");
                    consumetable.setMoney(money); //????????????????????? 6
                    consumetable.setOutTradeNo(p2_Order);
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
        String sHtmlText = buildRequest(sParaTemp, nodeAuthorizationURL, "POST", "??????");
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
        String keyValue = ApplicationListenerImpl.sysConfigureJson.getYeepayKey();   // ????????????
        String r0_Cmd = formatString(request.getParameter("r0_Cmd")); // ????????????
        String p1_MerId = ApplicationListenerImpl.sysConfigureJson.getYeepayPartner();   // ????????????
        String r1_Code = formatString(request.getParameter("r1_Code"));// ????????????
        String r2_TrxId = formatString(request.getParameter("r2_TrxId"));// ???????????????????????????
        String r3_Amt = formatString(request.getParameter("r3_Amt"));// ????????????
        String r4_Cur = formatString(request.getParameter("r4_Cur"));// ????????????
        String r5_Pid = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"), "gbk");// ????????????
        String r6_Order = formatString(request.getParameter("r6_Order"));// ???????????????
        String r7_Uid = formatString(request.getParameter("r7_Uid"));// ??????????????????ID
        String integral = new String(formatString(request.getParameter("integral")).getBytes("iso-8859-1"), "gbk");// ??????????????????
        String r9_BType = formatString(request.getParameter("r9_BType"));// ????????????????????????
        String hmac = formatString(request.getParameter("hmac"));// ????????????

        boolean flag = false;

        boolean isOK = false;
        // ?????????????????????
        isOK = PaymentForOnlineService.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code,
                r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, integral, r9_BType, keyValue);
        if (isOK) {
            //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            if (r1_Code.equals("1")) {
                // ????????????????????????????????????-??????????????????
                if (r9_BType.equals("1")) {
                    System.out.println("callback??????:????????????????????????????????????-??????????????????");
                    // ????????????????????????????????????-????????????????????????
                } else if (r9_BType.equals("2")) {
                    // ??????????????????????????????	?????????????????????????????????????????????"success"???????????????????????????????????????
                    System.out.println("SUCCESS");
                    // ????????????????????????????????????-??????????????????
                }
                // ????????????????????????????????????????????????
                System.out.println("<br>????????????!<br>???????????????:" + r6_Order + "<br>????????????:" + r3_Amt + "<br>???????????????????????????:" + r2_TrxId);
                //??????????????????????????????
                //------------------------------
                try {
                    String key = MD5Util.encode(r2_TrxId);
                    if (MemCachedClientHelp.getIMemcachedCache().get(key) == null) {
                        MemCachedClientHelp.getIMemcachedCache().put(key, "y", new Date(12 * 60 * 60 * 1000));
                        productCartList = new ArrayList<ProductCart>();
                        successCartList = new ArrayList<ProductJSON>();
                        try {
                            consumetable = consumetableService.findByOutTradeNo(r6_Order);//????????????
                            double money = Double.parseDouble(String.valueOf(r3_Amt));//????????????
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
                                        if (cookie.getName().equals("buyProduct")) {
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
                                            product = productService.findById(obj.getString("pId"));
                                            moneyCount += Integer.parseInt(obj.getString("num"));
                                            productCount++;
                                            productCart.setMoneyCount(moneyCount);
                                            productCart.setHeadImage(product.getHeadImage());
                                            productCart.setProductCount(productCount);
                                            productCart.setProductId(product.getProductId());
                                            productCart.setProductName(product.getProductName());
                                            productCart.setProductPrice(product.getProductPrice());
                                            productCart.setProductTitle(product.getProductTitle());
                                            productCartList.add(productCart);
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
                                            Integer count = productCart.getProductPrice();
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
                                                        consumerdetail.setConsumetableId(r6_Order);
                                                        consumerdetail.setProductId(productCart.getProductId());
                                                        consumerdetail.setProductName(productCart.getProductName());
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
                                                productJSON.setBuyDate(DateUtil.DateTimeToStr(new Date()));
                                                productJSON.setProductId(productCart.getProductId());
                                                productJSON.setProductName(productCart.getProductName());
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
                                        Cookie cookie = new Cookie("buyProduct", null);
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
                        System.out.println("????????????????????????");
                        paymentStatus = "success";

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else {
            paymentStatus = "error";
            System.out.println("?????????????????????!");
        }
        return "success";
    }

    public String notifyUrl() throws UnsupportedEncodingException {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        boolean flag = false;
        String keyValue = ApplicationListenerImpl.sysConfigureJson.getYeepayKey();   // ????????????
        String r0_Cmd = formatString(request.getParameter("r0_Cmd")); // ????????????
        String p1_MerId = ApplicationListenerImpl.sysConfigureJson.getYeepayPartner();   // ????????????
        String r1_Code = formatString(request.getParameter("r1_Code"));// ????????????
        String r2_TrxId = formatString(request.getParameter("r2_TrxId"));// ???????????????????????????
        String r3_Amt = formatString(request.getParameter("r3_Amt"));// ????????????
        String r4_Cur = formatString(request.getParameter("r4_Cur"));// ????????????
        String r5_Pid = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"), "gbk");// ????????????
        String r6_Order = formatString(request.getParameter("r6_Order"));// ???????????????
        String r7_Uid = formatString(request.getParameter("r7_Uid"));// ??????????????????ID
        String integral = new String(formatString(request.getParameter("r8_MP")).getBytes("iso-8859-1"), "gbk");// ??????????????????
        String r9_BType = formatString(request.getParameter("r9_BType"));// ????????????????????????
        String hmac = formatString(request.getParameter("hmac"));// ????????????

        //??????????????????????????????
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
        // ?????????????????????
        isOK = PaymentForOnlineService.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code,
                r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, integral, r9_BType, keyValue);
        if (isOK) {
            //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            if (r1_Code.equals("1")) {
                // ????????????????????????????????????-??????????????????
                if (r9_BType.equals("1")) {
                    System.out.println("callback??????:????????????????????????????????????-??????????????????");
                    // ????????????????????????????????????-????????????????????????
                } else if (r9_BType.equals("2")) {
                    // ??????????????????????????????	?????????????????????????????????????????????"success"???????????????????????????????????????
                    System.out.println("SUCCESS");
                    Struts2Utils.render("text/html", "success", "encoding:UTF-8");
                    // ????????????????????????????????????-??????????????????
                }
                // ????????????????????????????????????????????????
                System.out.println("<br>????????????!<br>???????????????:" + r6_Order + "<br>????????????:" + r3_Amt + "<br>???????????????????????????:" + r2_TrxId);
                //??????????????????????????????
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
                String sHtmlText = buildRequest(sParaTemp, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/yeepay/returnUrl.html", "POST", "??????");
                System.out.println(sHtmlText);
                Struts2Utils.render("text/html", sHtmlText, "encoding:UTF-8");
                System.out.println("success");    //????????????????????????
                Struts2Utils.render("text/html", "success", "encoding:UTF-8");

            }
        } else {
            System.out.println("?????????????????????!");
        }
        //????????????
        return null;
    }

    /**
     * ????????????????????????HTML????????????????????????
     *
     * @param sParaTemp     ??????????????????
     * @param strMethod     ?????????????????????????????????post???get
     * @param strButtonName ????????????????????????
     * @return ????????????HTML??????
     */
    public static String buildRequest(Map<String, String> sParaTemp, String postUrl, String strMethod, String strButtonName) {
        //?????????????????????
        Map<String, String> sPara = sParaTemp;
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"yeepaysubmit\" name=\"yeepaysubmit\" action=\"" + postUrl + "\" method=\"" + strMethod + "\">");
        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);
            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit???????????????????????????name??????
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

    public Consumetable getConsumetable() {
        return consumetable;
    }

    public void setConsumetable(Consumetable consumetable) {
        this.consumetable = consumetable;
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

    public ProductCart getProductCart() {
        return productCart;
    }

    public void setProductCart(ProductCart productCart) {
        this.productCart = productCart;
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

    public Commissionquery getCommissionquery() {
        return commissionquery;
    }

    public void setCommissionquery(Commissionquery commissionquery) {
        this.commissionquery = commissionquery;
    }

    public Lotteryproductutil getLotteryproductutil() {
        return lotteryproductutil;
    }

    public void setLotteryproductutil(Lotteryproductutil lotteryproductutil) {
        this.lotteryproductutil = lotteryproductutil;
    }

    public Commissionpoints getCommissionpoints() {
        return commissionpoints;
    }

    public void setCommissionpoints(Commissionpoints commissionpoints) {
        this.commissionpoints = commissionpoints;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(Integer moneyCount) {
        this.moneyCount = moneyCount;
    }

    public Integer getUserPayType() {
        return userPayType;
    }

    public void setUserPayType(Integer userPayType) {
        this.userPayType = userPayType;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
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

}
