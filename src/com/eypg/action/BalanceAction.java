package com.eypg.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eypg.pojo.Consumerdetail;
import com.eypg.pojo.Consumetable;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductCart;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.User;
import com.eypg.service.ConsumerdetailService;
import com.eypg.service.ConsumetableService;
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
import com.eypg.util.StringUtil;
import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("BalanceAction")
public class BalanceAction extends ActionSupport {

    private static final long serialVersionUID = 8197993150697915816L;

    @Autowired
    ConsumetableService consumetableService;
    @Autowired
    ConsumerdetailService consumerdetailService;
    @Autowired
    private UserService userService;

    private User user;
    private String userId;
    private Consumetable consumetable;
    private ProductCart productCart;
    private Product product;
    private Spellbuyproduct spellbuyproduct;
    private Consumerdetail consumerdetail;
    private List<ProductCart> productCartList;
    private String paymentStatus;

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
    private String requestUrl;
    //---------------生成订单号 结束------------------------


    public String goBalance() throws IOException {
        HttpServletRequest request = Struts2Utils.getRequest();
        HttpServletResponse response = Struts2Utils.getResponse();
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
        System.err.println("moneyCount:" + moneyCount);
        System.err.println("bank_type" + bank_type);
        reqHandler.setParameter("partner", TenpayConfig.partner);                //商户号
        reqHandler.setParameter("out_trade_no", out_trade_no);        //商家订单号
        reqHandler.setParameter("total_fee", moneyCount + "");                    //商品金额,以分为单位
        reqHandler.setParameter("return_url", TenpayConfig.balance_return_url);            //交易完成后跳转的URL
        reqHandler.setParameter("notify_url", TenpayConfig.balance_notify_url);            //接收财付通通知的URL
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
        reqHandler.setParameter("attach", hidUseBalance);                      //附加数据，原样返回
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
            int buyConut = moneyCount / 100;
            if (hidUseBalance != null && !hidUseBalance.equals("")) {
                buyConut += Integer.parseInt(hidUseBalance);
            }
            double money = Double.parseDouble(String.valueOf(moneyCount));
            money = money * 0.01;
            consumetable.setBuyCount(buyConut);
            consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
            consumetable.setInterfaceType("tenPay");
            consumetable.setMoney(money);
            consumetable.setOutTradeNo(out_trade_no);
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        //请求的url
        requestUrl = reqHandler.getRequestURL();

        //获取debug信息,建议把请求和debug信息写入日志，方便定位问题
        String debuginfo = reqHandler.getDebugInfo();
        System.out.println("requestUrl:  " + requestUrl);
        System.out.println("sign_String:  " + debuginfo);
//		return request.getRequestDispatcher(requestUrl).forward(request, response);
        response.sendRedirect(requestUrl);
        return null;
    }

    public String returnUrl() {
        HttpServletRequest request = Struts2Utils.getRequest();
        HttpServletResponse response = Struts2Utils.getResponse();
        //创建支付应答对象
        ResponseHandler resHandler = new ResponseHandler(request, response);
        resHandler.setKey(TenpayConfig.key);
        System.out.println("前台回调返回参数:" + resHandler.getAllParameters());
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
            //用户余额支付
            String hidUseBalance = resHandler.getParameter("attach");

            if ("1".equals(trade_mode)) {       //即时到账
                if ("0".equals(trade_state)) {
                    //------------------------------
                    //即时到账处理业务开始
                    //------------------------------
                    try {
                        consumetable = consumetableService.findByOutTradeNo(out_trade_no);
                        double money = Double.parseDouble(String.valueOf(total_fee));
                        money = money * 0.01;
                        System.err.println(consumetable.getMoney());
                        System.err.println(money);
                        if (consumetable.getMoney().equals(money) && consumetable.getTransactionId().equals(transaction_id)) {
                            paymentStatus = "success";
//							Cookie[] cookies = request.getCookies();
//							JSONArray array = null;
//							if(request.isRequestedSessionIdFromCookie()){
//								for (int i = 0; i < cookies.length; i++) {
//									Cookie cookie = cookies[i];
//									if(cookie.getName().equals("userId")){
//										userId = cookie.getValue();
//										if(userId!=null && !userId.equals("")){
//											try {
//												String key = MD5Util.encode(transaction_id);
//												if(MemCachedClientHelp.getIMemcachedCache().get(key)==null){
//													user = userService.findById(userId);
//													Double temp = user.getBalance() + consumetable.getMoney();
//													System.err.println("user.getBalance()"+user.getBalance());
//													System.err.println("consumetable.getMoney()"+consumetable.getMoney());
//													System.err.println("temp:"+temp);
//													user.setBalance(temp);
//													userService.add(user);
//													MemCachedClientHelp.getIMemcachedCache().put(key, "y",new Date(12*60*60*1000));
//												}
//											} catch (Exception e) {
//												e.printStackTrace();											
//											}
//										}
//									}
//								}
//							}
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
                } else {
                    System.out.println("即时到帐付款失败");
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
        HttpServletRequest request = Struts2Utils.getRequest();
        HttpServletResponse response = Struts2Utils.getResponse();

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

            //通过通知ID查询，确保通知来至财付通
            queryReq.init();
            queryReq.setKey(TenpayConfig.key);
            queryReq.setGateUrl("https://gw.tenpay.com/gateway/simpleverifynotifyid.xml");
            queryReq.setParameter("partner", TenpayConfig.partner);
            queryReq.setParameter("notify_id", notify_id);
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
                                    userId = String.valueOf(consumetable.getUserId());
                                    if (userId != null && !userId.equals("")) {
                                        try {
                                            String key = MD5Util.encode(transaction_id);
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

                                    resHandler.sendToCFT("success");
                                }
                            } catch (Exception e) {
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

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
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

    public String getHidUseBalance() {
        return hidUseBalance;
    }

    public void setHidUseBalance(String hidUseBalance) {
        this.hidUseBalance = hidUseBalance;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }


}
