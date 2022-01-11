package com.eypg.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("MyCartAction")
public class MyCartAction extends ActionSupport {

    private static final long serialVersionUID = 837685801735393306L;
    RandomUtil randomUtil = new RandomUtil();
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
                if (cookie.getName().equals("products")) {
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
                        productCart.setMoneyCount(moneyCount);
                        productCart.setHeadImage(product.getHeadImage());
                        productCart.setProductCount(productCount);
                        productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                        productCart.setProductId(spellbuyproduct.getSpellbuyProductId());
                        productCart.setProductName(product.getProductName());
                        productCart.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                        productCart.setProductTitle(product.getProductTitle());
                        productCart.setProductPeriod(spellbuyproduct.getProductPeriod());
                        productCartList.add(productCart);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "index";
    }

    public String getProductCartCount() throws UnsupportedEncodingException {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        JSONArray array = null;
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("products")) {
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
                    List<Object[]> proList = spellbuyproductService.findByProductId(Integer.parseInt(obj.getString("pId")));
                    product = (Product) proList.get(0)[0];
                    spellbuyproduct = (Spellbuyproduct) proList.get(0)[1];
                    if (spellbuyproduct.getSpStatus() == 0) {
                        productCount++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Struts2Utils.renderText(String.valueOf(productCount));
        return null;
    }

    public String getMyProductCart() throws UnsupportedEncodingException {
        request = Struts2Utils.getRequest();
        productCartList = new ArrayList<ProductCart>();
        Cookie[] cookies = request.getCookies();
        JSONArray array = null;
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("products")) {
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
                        productCart.setMoneyCount(moneyCount);
                        productCart.setHeadImage(product.getHeadImage());
                        productCart.setProductCount(productCount);
                        productCart.setProductId(spellbuyproduct.getSpellbuyProductId());
                        productCart.setProductName(product.getProductName());
                        productCart.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                        productCart.setProductTitle(product.getProductTitle());
                        productCart.setProductPeriod(spellbuyproduct.getProductPeriod());
                        productCartList.add(productCart);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Struts2Utils.renderJson(productCartList);
        return null;
    }

    public String buyProductClick() throws UnsupportedEncodingException {
        request = Struts2Utils.getRequest();
        productCartList = new ArrayList<ProductCart>();
        Cookie[] cookies = request.getCookies();
        JSONArray array = null;
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("products")) {
                    String product = new StringUtil().getUTF8URLDecoder(cookie.getValue());
                    if (product != null && !product.equals("")) {
                        array = JSONArray.fromObject(product);
                    }
                }
            }
        }
        Integer moneyCount = 0;
        if (array != null && !array.toString().equals("[{}]")) {
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);
                moneyCount += obj.getInt("num");
            }
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            sb.append('{');
            sb.append("\"productCount\":\"").append(array.size()).append("\",");
            sb.append("\"moneyCount\":\"").append(moneyCount).append("\"");
            sb.append('}');
            sb.append(",");
            sb.deleteCharAt(sb.length() - 1);
            sb.append(']');
            Struts2Utils.renderJson(sb.toString());
        }
        return null;
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
                if (cookie.getName().equals("products")) {
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
                        productCart.setMoneyCount(moneyCount);
                        productCart.setHeadImage(product.getHeadImage());
                        productCart.setProductCount(productCount);
                        productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                        productCart.setProductId(spellbuyproduct.getSpellbuyProductId());
                        productCart.setProductName(product.getProductName());
                        productCart.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                        productCart.setProductTitle(product.getProductTitle());
                        productCart.setProductPeriod(spellbuyproduct.getProductPeriod());
                        productCartList.add(productCart);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "payment";
    }

    public String goPay() throws UnsupportedEncodingException, InterruptedException {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        boolean flag = false;
        String buyproduct = "";//  已经被买完的商品
        Cookie[] cookies = request.getCookies();
        productCartList = new ArrayList<ProductCart>();
        successCartList = new ArrayList<ProductJSON>();
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
//						Struts2Utils.render("text/html", "<script>alert(\"购物车中有商品已经满员，请重新购买！\");window.location.href=\"/mycart/index.html\";</script>","encoding:UTF-8");
//						flag = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (StringUtil.isNotBlank(userId)) {
            user = userService.findById(userId);
        } else {
            flag = false;
        }

        if (flag) {
            consumetable = new Consumetable();
            double money = Double.parseDouble(String.valueOf(moneyCount));
            consumetable.setBuyCount(moneyCount);
            consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
            if (userPayType == 1) {
                consumetable.setInterfaceType("余额支付");
            } else if (userPayType == 2) {
                consumetable.setInterfaceType("福分抵扣");
            } else {
                consumetable.setInterfaceType("福分+余额");
            }
            consumetable.setMoney(money);
            consumetable.setOutTradeNo(out_trade_no);
            consumetable.setUserId(Integer.parseInt(userId));
            consumetableService.add(consumetable);

            for (final ProductCart productCart : productCartList) {
                //真正要购买的数量
                Integer count = 0;
                try {
                    consumerdetail = new Consumerdetail();
                    spellbuyrecord = new Spellbuyrecord();
                    productJSON = new ProductJSON();
                    commissionquery = new Commissionquery();
                    spellbuyproduct = spellbuyproductService.findById(productCart.getProductId().toString());
                    //当前拍购人数
                    Integer CurrentPrice = spellbuyproduct.getSpellbuyCount();
                    if ((spellbuyproduct.getSpellbuyCount() + productCart.getCount()) > productCart.getProductPrice()) {
                        count = productCart.getProductPrice() - spellbuyproduct.getSpellbuyCount();
                    } else {
                        count = productCart.getCount();
                    }

                    if (count > 0) {

                        try {
                            if (userPayType == 1) {
                                // 余额支付
                                if (user.getBalance() >= count) {
                                    Double temp = user.getBalance() - count;
                                    Integer points = user.getCommissionPoints();
                                    user.setBalance(temp);
                                    user.setCommissionPoints(points + count);
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
                                        commissionquery.setBuyMoney(Double.parseDouble(String.valueOf(count)));
                                        commissionquery.setCommission((Double.parseDouble(String.valueOf(count)) * 0.08));
                                        commissionquery.setDate(DateUtil.DateTimeToStr(new Date()));
                                        commissionquery.setDescription(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + productCart.getProductId() + ")获得佣金");
                                        commissionquery.setInvitedId(user.getInvite());
                                        commissionquery.setToUserId(user.getUserId());
                                        commissionqueryService.add(commissionquery);
                                    }
                                    commissionpoints = new Commissionpoints();
                                    commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                                    commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")支付" + count + "元获得福分");
                                    commissionpoints.setPay("+" + count);
                                    commissionpoints.setToUserId(Integer.parseInt(userId));
                                    commissionpointsService.add(commissionpoints);
                                } else {
                                    Struts2Utils.render("text/html", "<script>alert(\"您的余额不足，无法完成支付\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
                                    flag = false;
                                    break;
                                }
                            } else if (userPayType == 2) {
                                //福分抵扣
                                if ((user.getCommissionPoints() / 100) >= count) {
                                    Integer points = user.getCommissionPoints();
                                    user.setCommissionPoints(points - Integer.parseInt(integral));
//									userService.add(user);
                                    consumerdetail.setBuyCount(count);
                                    consumerdetail.setBuyMoney(Double.parseDouble(String.valueOf(count)));
                                    consumerdetail.setConsumetableId(out_trade_no);
                                    consumerdetail.setProductId(productCart.getProductId());
                                    consumerdetail.setProductName(productCart.getProductName());
                                    consumerdetail.setProductPeriod(productCart.getProductPeriod());
                                    consumerdetail.setProductTitle(productCart.getProductTitle());
                                    consumerdetailService.add(consumerdetail);

                                    commissionpoints = new Commissionpoints();
                                    commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                                    commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")福分抵扣");
                                    commissionpoints.setPay("-" + integral);
                                    commissionpoints.setToUserId(Integer.parseInt(userId));
                                    commissionpointsService.add(commissionpoints);

                                } else {
                                    Struts2Utils.render("text/html", "<script>alert(\"您的余额不足，无法完成支付\");window.location.href=\"/mycart/index.html\";</script>", "encoding:UTF-8");
                                    flag = false;
                                    break;
                                }
                            } else {
                                //余额福分
                                if (user.getBalance() >= Integer.parseInt(hidUseBalance)) {
                                    Double temp = user.getBalance() - Integer.parseInt(hidUseBalance);
                                    Integer points = user.getCommissionPoints();
                                    user.setBalance(temp);
                                    user.setCommissionPoints(points + Integer.parseInt(hidUseBalance));
//									userService.add(user);
                                    if (user.getInvite() != null) {
                                        User userCommission = userService.findById(String.valueOf(user.getInvite()));
                                        double tempCommissionCount = userCommission.getCommissionCount();
                                        double commissionBalance = user.getCommissionBalance();
                                        userCommission.setCommissionCount(tempCommissionCount += (Double.parseDouble(String.valueOf(count)) * 0.08));
                                        userCommission.setCommissionBalance(commissionBalance += (Double.parseDouble(String.valueOf(count)) * 0.08));
                                        userService.add(userCommission);
                                        commissionquery.setBuyMoney(Double.parseDouble(String.valueOf(hidUseBalance)));
                                        commissionquery.setCommission((Double.parseDouble(String.valueOf(hidUseBalance)) * 0.08));
                                        commissionquery.setDate(DateUtil.DateTimeToStr(new Date()));
                                        commissionquery.setDescription(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + productCart.getProductId() + ")获得佣金");
                                        commissionquery.setInvitedId(user.getInvite());
                                        commissionquery.setToUserId(user.getUserId());
                                        commissionqueryService.add(commissionquery);
                                    }
                                    commissionpoints = new Commissionpoints();
                                    commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                                    commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")支付" + hidUseBalance + "元获得福分");
                                    commissionpoints.setPay("+" + hidUseBalance);
                                    commissionpoints.setToUserId(Integer.parseInt(userId));
                                    commissionpointsService.add(commissionpoints);
                                } else {
                                    flag = false;
                                    break;
                                }
                                if (user.getCommissionPoints() >= Integer.parseInt(integral)) {
                                    Integer points = user.getCommissionPoints();
                                    user.setCommissionPoints(points - Integer.parseInt(integral));
//									userService.add(user);
                                    commissionpoints = new Commissionpoints();
                                    commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                                    commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")福分抵扣");
                                    commissionpoints.setPay("-" + integral);
                                    commissionpoints.setToUserId(Integer.parseInt(userId));
                                    commissionpointsService.add(commissionpoints);

                                } else {
                                    flag = false;
                                    break;
                                }
                                consumerdetail.setBuyCount(count);
                                consumerdetail.setBuyMoney(Double.parseDouble(String.valueOf(count)));
                                consumerdetail.setConsumetableId(out_trade_no);
                                consumerdetail.setProductId(productCart.getProductId());
                                consumerdetail.setProductName(productCart.getProductName());
                                consumerdetail.setProductPeriod(productCart.getProductPeriod());
                                consumerdetail.setProductTitle(productCart.getProductTitle());
                                consumerdetailService.add(consumerdetail);
                            }
                        } catch (Exception e) {
                            flag = false;
                            break;
                        }

                        if (flag) {
                            spellbuyproduct.setSpellbuyCount(spellbuyproduct.getSpellbuyCount() + count);
                            if (spellbuyproduct.getSpellbuyCount() >= productCart.getProductPrice()) {
                                spellbuyproduct.setSpellbuyCount(productCart.getProductPrice());
                                //开奖状态
                                spellbuyproduct.setSpStatus(2);
                                spellbuyproduct.setSpellbuyEndDate(DateUtil.DateTimeToStr(DateUtil.subMinute(new Date(), -3)));
                                //放入开奖表
                                lotteryproductutil = new Lotteryproductutil();
                                lotteryproductutil.setLotteryProductEndDate(DateUtil.DateTimeToStr(DateUtil.subMinute(new Date(), -3)));
                                lotteryproductutil.setLotteryProductId(spellbuyproduct.getSpellbuyProductId());
                                lotteryproductutil.setLotteryProductImg(productCart.getHeadImage());
                                lotteryproductutil.setLotteryProductName(productCart.getProductName());
                                lotteryproductutil.setLotteryProductPeriod(spellbuyproduct.getProductPeriod());
                                lotteryproductutil.setLotteryProductPrice(spellbuyproduct.getSpellbuyPrice());
                                lotteryproductutil.setLotteryProductTitle(productCart.getProductTitle());
                                lotteryproductutilService.add(lotteryproductutil);
                            }
                            spellbuyproductService.add(spellbuyproduct);

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

                            List<Randomnumber> RandomnumberList = randomnumberService.query(" from Randomnumber where productId='" + spellbuyproduct.getSpellbuyProductId() + "'");
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

                            Integer experience = user.getExperience();
                            experience += (count * 10);
                            user.setExperience(experience);
                            userService.add(user);


                            productJSON.setBuyDate(spellbuyrecord.getBuyDate());
                            productJSON.setProductId(productCart.getProductId());
                            productJSON.setProductName(productCart.getProductName());
                            productJSON.setProductPeriod(productCart.getProductPeriod());
                            productJSON.setProductTitle(productCart.getProductTitle());
                            productJSON.setBuyCount(count);
                            successCartList.add(productJSON);
                        }


//						if(spellbuyproduct.getSpStatus()==2){
//							/**
//							 * 开奖工具类
//							 */
//							String lotteryId = MD5Util.encode(String.valueOf(spellbuyproduct.getSpellbuyProductId()));
//							if(MemCachedClientHelp.getIMemcachedCache().get(lotteryId)==null){
//								MemCachedClientHelp.getIMemcachedCache().put(lotteryId, "y",new Date(12*60*60*1000));
//								new Thread(){
//									public void run(){
//										try {
//											newLotteryUtil.lottery(productCart);
//										} catch (Exception e) {
//											e.printStackTrace();
//										}
//									}
//								}.start();
//							}
//						}
                    }
                } catch (Exception e) {
                    flag = false;
                    e.printStackTrace();
                }
            }

        }

        if (flag) {
            if (request.isRequestedSessionIdFromCookie()) {
                Cookie cookie = new Cookie("products", null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                response.addCookie(cookie);
            }
        }

        return "success";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductCart> getProductCartList() {
        return productCartList;
    }

    public void setProductCartList(List<ProductCart> productCartList) {
        this.productCartList = productCartList;
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

    public Integer getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(Integer moneyCount) {
        this.moneyCount = moneyCount;
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


}
