package com.eypg.action;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Applymention;
import com.eypg.pojo.BuyHistoryJSON;
import com.eypg.pojo.Cardpassword;
import com.eypg.pojo.Commissionpoints;
import com.eypg.pojo.Commissionquery;
import com.eypg.pojo.CommissionqueryJSON;
import com.eypg.pojo.Consumerdetail;
import com.eypg.pojo.Consumetable;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.News;
import com.eypg.pojo.Orderdetail;
import com.eypg.pojo.Orderdetailaddress;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductJSON;
import com.eypg.pojo.RandomNumberJSON;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.SCity;
import com.eypg.pojo.SDistrict;
import com.eypg.pojo.SProvince;
import com.eypg.pojo.ShareJSON;
import com.eypg.pojo.Shareinfo;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.pojo.Userbyaddress;
import com.eypg.service.ApplymentionService;
import com.eypg.service.CardpasswordService;
import com.eypg.service.CommissionpointsService;
import com.eypg.service.CommissionqueryService;
import com.eypg.service.ConsumerdetailService;
import com.eypg.service.ConsumetableService;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.NewsService;
import com.eypg.service.RegionService;
import com.eypg.service.ShareService;
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.service.UserService;
import com.eypg.util.CutImages;
import com.eypg.util.DateUtil;
import com.eypg.util.HTMLFilter;
import com.eypg.util.IPUtil;
import com.eypg.util.StringUtil;
import com.eypg.util.Struts2Utils;
import com.eypg.util.UserNameUtil;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("UserAction")
public class UserAction extends ActionSupport {
    private static final long serialVersionUID = 6146740235643445087L;

    @Autowired
    private UserService userService;
    @Autowired
    private SpellbuyproductService spellbuyproductService;
    @Autowired
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    private LatestlotteryService latestlotteryService;
    @Autowired
    private ShareService shareService;
    @Autowired
    private ConsumetableService consumetableService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private CommissionqueryService commissionqueryService;
    @Autowired
    private CommissionpointsService commissionpointsService;
    @Autowired
    private ApplymentionService applymentionService;
    @Autowired
    private CardpasswordService cardpasswordService;

    private String forward;
    private List<ProductJSON> productList;
    private List<ProductJSON> newDateList;
    private ProductJSON productJSON;
    private BuyHistoryJSON buyHistoryJSON;
    private ShareJSON shareJSON;
    private List<BuyHistoryJSON> buyHistoryJSONList;
    private List<RandomNumberJSON> randomNumberJSONList;
    private RandomNumberJSON randomNumberJSON;
    private List<ShareJSON> shareJSONList;
    private List<Userbyaddress> userbyaddressList;
    private List<News> newsList;
    private List<SProvince> sProvinceList;
    private List<SCity> sCityList;
    private List<SDistrict> sDistrictList;
    private List<User> userList;
    private List<Commissionquery> commissionqueryList;
    private List<Commissionpoints> commissionpointsList;
    private List<Applymention> applymentionList;
    private List<CommissionqueryJSON> commissionqueryJSONList;
    private List<Orderdetail> orderdetailList;
    private Orderdetailaddress orderdetailaddress;
    private CommissionqueryJSON commissionqueryJSON;
    private Applymention applymention;
    private Commissionquery commissionquery;
    private Commissionpoints commissionpoints;
    private Userbyaddress userbyaddress;
    private Product product;
    private Spellbuyproduct spellbuyproduct;
    private Spellbuyrecord spellbuyrecord;
    private Randomnumber randomnumber;
    private Latestlottery latestlottery;
    private Shareinfo shareinfo;
    private User user;
    private String userJSON;
    private String userId;
    private String id;
    private int pageNo;
    private int pageSize = 12;
    private int pageCount;
    private int resultCount;
    private String startDate;
    private String endDate;
    private String selectTime;
    private File myFile;
    private String myFileFileName;
    private String myFileContentType;
    private String imageFileName;
    private static final int BUFFER_SIZE = 100 * 1024;
    private int x1;
    private int y1;
    private int w;
    private int h;
    private String hidPicUrl;

    HttpServletRequest request = null;
    static HTMLFilter htmlFilter = new HTMLFilter();

    //文件上传
    private static void copy(File src, File dst) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                while (in.read(buffer) > 0) {
                    out.write(buffer);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String index() {
        if (StringUtil.isNotBlank(forward)) {
            forward = htmlFilter.filter(forward);
        }
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        /**
                         * 即将揭晓商品
                         */
                        Pagination datePage = spellbuyproductService.upcomingAnnounced(pageNo, 5);
                        List<Object[]> dataList = (List<Object[]>) datePage.getList();
                        productList = new ArrayList<ProductJSON>();
                        for (int j = 0; j < dataList.size(); j++) {
                            productJSON = new ProductJSON();
                            product = (Product) dataList.get(j)[0];
                            spellbuyproduct = (Spellbuyproduct) dataList.get(j)[1];
                            productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                            productJSON.setHeadImage(product.getHeadImage());
                            productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                            productJSON.setProductName(product.getProductName());
                            productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                            productJSON.setProductTitle(product.getProductTitle());
                            productList.add(productJSON);
                        }

//						/**
//						 * 最新上架
//						 */
//						Pagination datePage2 = spellbuyrecordService.nowUpProducts(pageNo, 5);
//						List<Object[]> dataList2 = (List<Object[]>) datePage2.getList();
//						newDateList = new ArrayList<ProductJSON>();
//						for (int j = 0; j < dataList2.size(); j++) {
//							productJSON = new ProductJSON();
//							product = (Product) dataList2.get(j)[0];
//							spellbuyproduct = (Spellbuyproduct) dataList2.get(j)[1];
//							productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
//							productJSON.setHeadImage(product.getHeadImage());
//							productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
//							productJSON.setProductName(product.getProductName());
//							productJSON.setProductPrice(product.getProductPrice());
//							productJSON.setProductTitle(product.getProductTitle());
//							newDateList.add(productJSON);
//						}

                        /**
                         * 新闻
                         */
                        newsList = newsService.indexNews();
                        return "index";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 拍购记录
     *
     * @return
     */
    public String UserBuyList() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        if (pageNo == 0) {
                            pageNo = 1;
                        } else {
                            pageNo += 1;
                        }
                        resultCount = spellbuyrecordService.buyHistoryByUserByCount(userId, startDate, endDate);
                        return "UserBuyList";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 选择日期查询得到拍购记录resultCount 总数
     */
    public void getuserBuyListAjaxPageResultCount() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        resultCount = spellbuyrecordService.buyHistoryByUserByCount(userId, startDate, endDate);
        Struts2Utils.renderText(resultCount + "");
    }

    /**
     * 拍购记录ajax分页请求
     *
     * @return
     */
    public String userBuyListAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        Pagination datePage = spellbuyrecordService.buyHistoryByUser(userId, startDate, endDate, pageNo, 5);
        List<BuyHistoryJSON> dataList = (List<BuyHistoryJSON>) datePage.getList();
        buyHistoryJSONList = new ArrayList<BuyHistoryJSON>();

        for (int j = 0; j < dataList.size(); j++) {
            try {
                buyHistoryJSON = dataList.get(j);
                if (buyHistoryJSON.getBuyStatus() == 1) {
                    latestlottery = (Latestlottery) latestlotteryService.getBuyHistoryByDetail(buyHistoryJSON.getProductId()).get(0);
                    buyHistoryJSON.setWinDate(latestlottery.getAnnouncedTime());
                    buyHistoryJSON.setWinId(latestlottery.getRandomNumber());
                    String userer = null;
                    if (latestlottery.getUserName() != null && !latestlottery.getUserName().equals("")) {
                        userer = latestlottery.getUserName();
                    } else {
                        if (latestlottery.getBuyUser() != null && !latestlottery.getBuyUser().equals("")) {
                            userer = latestlottery.getBuyUser();
                            if (userer.indexOf("@") != -1) {
                                String[] u = userer.split("@");
                                String u1 = u[0].substring(0, 2) + "***";
                                userer = u1 + "@" + u[1];
                            } else {
                                userer = userer.substring(0, 4) + "*** " + userer.substring(7);
                            }
                        }
                    }
                    buyHistoryJSON.setWinUser(userer);
                    buyHistoryJSON.setWinUserId(latestlottery.getUserId());
                }
                buyHistoryJSONList.add(buyHistoryJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Struts2Utils.renderJson(buyHistoryJSONList);
        return null;
    }

    /**
     * 查看商品详情
     *
     * @return
     */
    public String UserBuyDetail() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                try {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals("userId")) {
                        userId = cookie.getValue();
                        if (userId != null && !userId.equals("")) {
                            user = userService.findById(userId);
                            buyHistoryJSON = (BuyHistoryJSON) spellbuyrecordService.getBuyHistoryByDetail(id, userId).get(0);
                            if (buyHistoryJSON.getBuyStatus() == 1) {
                                latestlottery = (Latestlottery) latestlotteryService.getBuyHistoryByDetail(buyHistoryJSON.getProductId()).get(0);
                                buyHistoryJSON.setWinDate(latestlottery.getAnnouncedTime());
                                buyHistoryJSON.setWinId(latestlottery.getRandomNumber());
                                String userer = null;
                                if (latestlottery.getUserName() != null && !latestlottery.getUserName().equals("")) {
                                    userer = latestlottery.getUserName();
                                } else {
                                    if (latestlottery.getBuyUser() != null && !latestlottery.getBuyUser().equals("")) {
                                        userer = latestlottery.getBuyUser();
                                        if (userer.indexOf("@") != -1) {
                                            String[] u = userer.split("@");
                                            String u1 = u[0].substring(0, 2) + "***";
                                            userer = u1 + "@" + u[1];
                                        } else {
                                            userer = userer.substring(0, 4) + "*** " + userer.substring(7);
                                        }
                                    }
                                }
                                buyHistoryJSON.setWinUser(userer);
                                buyHistoryJSON.setWinUserId(latestlottery.getUserId());
                            }
                            resultCount = spellbuyrecordService.getRandomNumberListPageByCount(id, userId);
                            return "UserBuyDetail";
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "login_index";
    }

    /**
     * 取得某条购买记录的拍购码(list 页)
     */
    public void getRandomNumberList() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        List<Randomnumber> dataList = spellbuyrecordService.getRandomNumberList(id, userId);
        randomNumberJSONList = new ArrayList<RandomNumberJSON>();
        String numbers = "";
        for (Randomnumber randomnumber : dataList) {
            String[] randoms = randomnumber.getRandomNumber().split(",");
            for (String string : randoms) {
                numbers += "<li>" + string + "</li>";
            }
        }
        Struts2Utils.renderText(numbers);
    }

    /**
     * 取得某条购买记录的拍购码(详细页面)
     */
    public void getRandomNumberListPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Pagination datePage = spellbuyrecordService.getRandomNumberListPage(id, userId, pageNo, 50);
        List<Randomnumber> dataList = (List<Randomnumber>) datePage.getList();
        randomNumberJSONList = new ArrayList<RandomNumberJSON>();
        for (Randomnumber randomnumber : dataList) {
            try {
                randomNumberJSON = new RandomNumberJSON();
                String[] randoms = randomnumber.getRandomNumber().split(",");
                String numbers = "";
                for (String string : randoms) {
                    numbers += "<span>" + string + "</span>";
                }
                randomNumberJSON.setRandomNumbers(numbers);
                randomNumberJSON.setBuyCount(String.valueOf(randoms.length));
                randomNumberJSON.setBuyDate(randomnumber.getBuyDate());
                randomNumberJSONList.add(randomNumberJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Struts2Utils.renderJson(randomNumberJSONList);
    }

    /**
     * 获得的商品
     *
     * @return
     */
    public String OrderList() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        resultCount = latestlotteryService.getProductByUserByCount(userId, startDate, endDate);
                        return "OrderList";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 获得的商品AJAX分页
     *
     * @return
     */
    public String OrderListAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        Pagination datePage = latestlotteryService.getProductByUser(userId, startDate, endDate, pageNo, 5);
        List<Latestlottery> dataList = (List<Latestlottery>) datePage.getList();
        buyHistoryJSONList = new ArrayList<BuyHistoryJSON>();
        for (int j = 0; j < dataList.size(); j++) {
            try {
                buyHistoryJSON = new BuyHistoryJSON();
                latestlottery = dataList.get(j);
                buyHistoryJSON.setProductName(latestlottery.getProductName());
                buyHistoryJSON.setProductTitle(latestlottery.getProductTitle());
                buyHistoryJSON.setProductImg(latestlottery.getProductImg());
                buyHistoryJSON.setProductId(latestlottery.getSpellbuyProductId());
                buyHistoryJSON.setProductPrice(latestlottery.getProductPrice());
                buyHistoryJSON.setProductPeriod(latestlottery.getProductPeriod());
                buyHistoryJSON.setWinId(latestlottery.getRandomNumber());
                buyHistoryJSON.setWinDate(latestlottery.getAnnouncedTime());
                buyHistoryJSON.setBuyStatus(latestlottery.getStatus());
                buyHistoryJSONList.add(buyHistoryJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Struts2Utils.renderJson(buyHistoryJSONList);
        return null;
    }

    /**
     * 选择日期查询得到获得的商品resultCount 总数
     */
    public void OrderListAjaxPageResultCount() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        resultCount = latestlotteryService.getProductByUserByCount(userId, startDate, endDate);
        Struts2Utils.renderText(resultCount + "");
    }

    /**
     * 获取的商品详情
     *
     * @return
     */
    public String OrderDetail() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                try {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals("userId")) {
                        userId = cookie.getValue();
                        if (userId != null && !userId.equals("")) {
                            user = userService.findById(userId);
                            latestlottery = latestlotteryService.findById(id);
                            if (latestlottery.getStatus() == 1) {
                                userbyaddressList = userService.getUserbyaddress(userId);
                                sProvinceList = regionService.getProvinceList();
                            } else {
                                orderdetailList = latestlotteryService.orderdetailListById(id);
                                orderdetailaddress = latestlotteryService.orderdetailaddressFindByOrderdetailId(id);
                            }
                            return "OrderDetail";
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "login_index";
    }

    /**
     * 获取的商品详情-确认收货地址
     *
     * @return
     */
    public void OrderDetailAddAddress() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                try {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals("userId")) {
                        userId = cookie.getValue();
                        if (userId != null && !userId.equals("")) {
                            JSONObject object = JSONObject.fromObject(userJSON);
                            String orderRemarks = object.getString("orderRemarks");
                            String postDate = object.getString("postDate");
                            String hidOrderNO = object.getString("hidOrderNO");
                            id = object.getString("id");
                            userbyaddress = userService.findAddressById(Integer.parseInt(id));
                            orderdetailaddress = new Orderdetailaddress();
                            orderdetailaddress.setAddress(userbyaddress.getProvince() + " " + userbyaddress.getCity() + " " + userbyaddress.getDistrict() + " " + userbyaddress.getAddress());
                            orderdetailaddress.setConsignee(userbyaddress.getConsignee());
                            orderdetailaddress.setOrderRemarks(orderRemarks);//订单备注
                            orderdetailaddress.setPostDate(postDate);//配送时间
                            orderdetailaddress.setPhone(userbyaddress.getPhone());
                            orderdetailaddress.setOrderDetailId(Integer.parseInt(hidOrderNO));
                            latestlotteryService.addOrderdetailaddress(orderdetailaddress);
                            latestlottery = latestlotteryService.findById(hidOrderNO);
                            latestlottery.setStatus(2);
                            latestlotteryService.add(latestlottery);
                            Struts2Utils.renderText("success");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取的商品详情-确认收到货物
     *
     * @return
     */
    public void confirmOrderDetail() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                try {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals("userId")) {
                        userId = cookie.getValue();
                        if (userId != null && !userId.equals("")) {
                            latestlottery = latestlotteryService.findById(id);
                            latestlottery.setStatus(4);
                            latestlottery.setShareStatus(-1);
                            latestlotteryService.add(latestlottery);
                            Struts2Utils.renderText("success");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String PostSingleAdd() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                try {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals("userId")) {
                        userId = cookie.getValue();
                        if (userId != null && !userId.equals("")) {
                            user = userService.findById(userId);

                            return "PostSingleAdd";
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "login_index";
    }

    /**
     * 我的晒单
     *
     * @return
     */
    public String PostSingleList() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
//						Pagination datePage = shareService.shareByUser(userId,startDate,endDate, pageNo, 5);
//						resultCount = datePage.getResultCount();
                        return "PostSingleList";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 我的晒单分页AJAX
     *
     * @return
     */
    public String PostSingleListAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        Pagination datePage = shareService.shareByUser(userId, startDate, endDate, pageNo, 5);
        List<Latestlottery> pageList = (List<Latestlottery>) datePage.getList();
        buyHistoryJSONList = new ArrayList<BuyHistoryJSON>();
        for (int j = 0; j < pageList.size(); j++) {
            try {
                buyHistoryJSON = new BuyHistoryJSON();
                latestlottery = pageList.get(j);
                buyHistoryJSON.setProductName(latestlottery.getProductName());
                buyHistoryJSON.setProductTitle(latestlottery.getProductTitle());
                buyHistoryJSON.setProductImg(latestlottery.getProductImg());
                buyHistoryJSON.setProductId(latestlottery.getProductId());
                buyHistoryJSON.setProductPeriod(latestlottery.getProductPeriod());
                buyHistoryJSON.setWinId(latestlottery.getRandomNumber());
                buyHistoryJSON.setWinDate(latestlottery.getAnnouncedTime());
                buyHistoryJSON.setBuyStatus(latestlottery.getShareStatus());
                buyHistoryJSON.setWinUserId(latestlottery.getShareId());
                buyHistoryJSON.setHistoryId(latestlottery.getId());
                buyHistoryJSONList.add(buyHistoryJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Struts2Utils.renderJson(buyHistoryJSONList);
        return null;
    }

    /**
     * 选择日期查询得到我的晒单resultCount 总数
     */
    public void PostSingleListAjaxPageResultCount() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        Pagination datePage = shareService.shareByUser(userId, startDate, endDate, pageNo, 5);
        resultCount = datePage.getResultCount();
        Struts2Utils.renderText(resultCount + "");
    }

    /**
     * 邀请好友
     *
     * @return
     */
    public String InvitedList() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        resultCount = userService.getInvitedListByCount(userId);
                        userList = userService.getInvitedListByData(userId);
                        for (User user : userList) {
                            if (user.getExperience() > 0) {
                                w++;
                                h += 50;
                            }
                        }
                        return "InvitedList";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 邀请好友分页AJAX
     *
     * @return
     */
    public String InvitedListAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Pagination datePage = userService.getInvitedList(userId, pageNo, pageSize);
        userList = (List<User>) datePage.getList();
        for (User user : userList) {
            user.setUserName(UserNameUtil.userName(user));
        }
        Struts2Utils.renderJson(userList);
        return null;
    }

    /**
     * 佣金明细
     *
     * @return
     */
    public String CommissionQuery() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        resultCount = commissionqueryService.getCommissionQueryListByCount(userId, startDate, endDate);
                        return "CommissionQuery";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 佣金明细 AJAX
     *
     * @return
     */
    public String CommissionQueryAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        Pagination datePage = commissionqueryService.getCommissionQueryList(userId, startDate, endDate, pageNo, pageSize);
        List<Object[]> dateList = (List<Object[]>) datePage.getList();
        if (dateList.size() > 0) {
            commissionqueryJSONList = new ArrayList<CommissionqueryJSON>();
            for (int i = 0; i < dateList.size(); i++) {
                commissionqueryJSON = new CommissionqueryJSON();
                user = (User) dateList.get(i)[0];
                commissionquery = (Commissionquery) dateList.get(i)[1];
                commissionqueryJSON.setBuyMoney(commissionquery.getBuyMoney());
                commissionqueryJSON.setCommission(commissionquery.getCommission());
                commissionqueryJSON.setDate(commissionquery.getDate());
                commissionqueryJSON.setDescription(commissionquery.getDescription());
                commissionqueryJSON.setUserId(String.valueOf(user.getUserId()));
                commissionqueryJSON.setUserName(UserNameUtil.userName(user));
                commissionqueryJSONList.add(commissionqueryJSON);
            }
        }
        Struts2Utils.renderJson(commissionqueryJSONList);
        return null;
    }

    /**
     * 佣金明细 AJAX ByCount
     */
    public void getCommissionQueryAjaxPageResultCount() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        resultCount = commissionqueryService.getCommissionQueryListByCount(userId, startDate, endDate);
        Struts2Utils.renderText(resultCount + "");
    }

    /**
     * 我的福分
     *
     * @return
     */
    public String MemberPoints() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        resultCount = commissionpointsService.getCommissionPointsListByCount(userId, startDate, endDate);
                        return "CommissionPoints";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 福分明细 AJAX
     *
     * @return
     */
    public String CommissionPointsAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Pagination datePage = commissionpointsService.CommissionPoints(userId, startDate, endDate, pageNo, pageSize);
        commissionpointsList = (List<Commissionpoints>) datePage.getList();
        Struts2Utils.renderJson(commissionpointsList);
        return null;
    }

    /**
     * 提现申请
     *
     * @return
     */
    public String ApplyMention() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        return "ApplyMention";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 提现申请 ADD
     */
    public void ApplyMentionAdd() {
        try {
            JSONObject object = JSONObject.fromObject(userJSON);
            userId = object.getString("userId");
            user = userService.findById(userId);
            double money = Double.parseDouble(object.getString("money"));
            double commissionBalance = user.getCommissionBalance();
            double commissionMention = user.getCommissionMention();
            if (commissionBalance < money) {
                Struts2Utils.renderText("moneyError");
            } else {
                user.setCommissionBalance(commissionBalance - money);
                user.setCommissionMention(commissionMention + money);
                userService.add(user);
                applymention = new Applymention();
                applymention.setBankName(object.getString("bankName"));
                applymention.setBankNo(object.getString("bankNo"));
                applymention.setBankSubbranch(object.getString("bankSubbranch"));
                applymention.setBankUser(object.getString("bankUser"));
                applymention.setDate(DateUtil.DateTimeToStr(new Date()));
                applymention.setFee(0.0);
                applymention.setMoney(money);
                applymention.setPhone(object.getString("phone"));
                applymention.setStatus("审核中");
                applymention.setUserId(Integer.parseInt(userId));
                applymentionService.add(applymention);
                Struts2Utils.renderText("success");
            }
        } catch (Exception e) {
            Struts2Utils.renderText("error");
            e.printStackTrace();
        }

    }

    /**
     * 佣金充值到拍购帐户
     */
    public void ApplyMentionAddToUser() {
        try {
            JSONObject object = JSONObject.fromObject(userJSON);
            double recharge = Double.parseDouble(object.getString("recharge"));
            userId = object.getString("userId");
            user = userService.findById(userId);
            double tempCommissionBalance = user.getCommissionBalance();
            double commissionMention = user.getCommissionMention();
            if (tempCommissionBalance < recharge) {
                Struts2Utils.renderText("moneyError");
            } else {
                user.setCommissionMention(commissionMention + recharge);
                user.setCommissionBalance(tempCommissionBalance - recharge);
                double tempBalance = user.getBalance();
                user.setBalance(tempBalance + recharge);
                userService.add(user);
                Struts2Utils.renderText("success");
            }
        } catch (Exception e) {
            Struts2Utils.renderText("error");
            e.printStackTrace();
        }

    }

    /**
     * 提现记录
     *
     * @return
     */
    public String MentionList() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        resultCount = applymentionService.getApplymentionListByCount(userId, startDate, endDate);
                        return "MentionList";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 提现记录 AJAX
     *
     * @return
     */
    public String MentionListAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        Pagination datePage = applymentionService.getApplymentionList(userId, startDate, endDate, pageNo, pageSize);
        List<Applymention> dataList = (List<Applymention>) datePage.getList();
        Struts2Utils.renderJson(dataList);
        return null;
    }

    /**
     * 提现记录 AJAX ByCount
     */
    public void getMentionListAjaxPageResultCount() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        resultCount = applymentionService.getApplymentionListByCount(userId, startDate, endDate);
        Struts2Utils.renderText(resultCount + "");
    }

    /**
     * 网银充值
     *
     * @return
     */
    public String UserRecharge() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        return "UserRecharge";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 卡密充值
     *
     * @return
     */
    public String userCardRecharge() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        return "userCardRecharge";
                    }
                }
            }
        }
        return "login_index";
    }

    public void doCardRecharge() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        try {
                            String randomNo = id.substring(0, 32);
                            String cardPwd = id.substring(32, id.length());
                            Cardpassword cardpassword = cardpasswordService.doCardRecharge(randomNo, cardPwd);
                            if (cardpassword != null) {
                                Double temp = user.getBalance();
                                cardpasswordService.deleteByID(cardpassword.getId());
                                user.setBalance(temp + cardpassword.getMoney());
                                userService.add(user);
                                Consumetable consumetable = new Consumetable();
                                consumetable.setBuyCount(Integer.parseInt(new java.text.DecimalFormat("0").format(cardpassword.getMoney())));
                                consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
                                consumetable.setInterfaceType("卡密冲值");
                                consumetable.setMoney(cardpassword.getMoney());
                                consumetable.setOutTradeNo("");
                                String s = UUID.randomUUID().toString().toUpperCase();
                                s = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
                                consumetable.setTransactionId(s);
                                consumetable.setUserId(Integer.parseInt(userId));
                                consumetableService.add(consumetable);
                                Struts2Utils.renderText("yes");
                            } else {
                                Struts2Utils.renderText("no");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Struts2Utils.renderText("no");
                        }
                    }
                }
            }
        }
    }

    /**
     * 账户明细
     *
     * @return
     */
    public String UserBalance() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        resultCount = consumetableService.userByConsumetableByDeltaByCount(userId, startDate, endDate);
                        return "UserBalance";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 消费记录
     *
     * @return
     */
    public String ConsumeList() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        resultCount = consumetableService.userByConsumetableByCount(userId, startDate, endDate);
                        return "ConsumeList";
                    }
                }
            }
        }
        return "login_index";
    }

    /***
     * 消费记录分页
     * @return
     */
    public String ConsumeListAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        Pagination datePage = consumetableService.userByConsumetable(userId, startDate, endDate, pageNo, pageSize);
        List<Consumetable> dataList = (List<Consumetable>) datePage.getList();
        Struts2Utils.renderJson(dataList);
        return null;
    }

    /**
     * 选择日期查询得到消费记录resultCount 总数
     */
    public void ConsumeListAjaxPageResultCount() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        resultCount = consumetableService.userByConsumetableByCount(userId, startDate, endDate);
        Struts2Utils.renderText(resultCount + "");
    }


    /**
     * 充值记录
     *
     * @return
     */
    public String RechargeList() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        resultCount = consumetableService.userByConsumetableByDeltaByCount(userId, startDate, endDate);
                        return "RechargeList";
                    }
                }
            }
        }
        return "login_index";
    }

    /***
     * 充值记录分页
     * @return
     */
    public String ConsumeListByDeltaAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        Pagination datePage = consumetableService.userByConsumetableByDelta(userId, startDate, endDate, pageNo, pageSize);
        List<Consumetable> dataList = (List<Consumetable>) datePage.getList();
        Struts2Utils.renderJson(dataList);
        return null;
    }

    /**
     * 选择日期查询得到充值记录resultCount 总数
     */
    public void ConsumeListByDeltaAjaxPageResultCount() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Date date = new Date();
        if (StringUtil.isNotBlank(selectTime)) {
            if (selectTime.equals("0")) {
                startDate = null;
                endDate = null;
            } else if (selectTime.equals("1")) {
                startDate = DateUtil.DateToStr(date) + " 00:00:00";
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
            } else if (selectTime.equals("2")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00";
            } else if (selectTime.equals("3")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00";
            } else if (selectTime.equals("4")) {
                endDate = DateUtil.DateToStr(date) + " 23:59:59";
                startDate = DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00";
            }
        } else {
            startDate += " 00:00:00";
            endDate += " 23:59:59";
        }
        resultCount = consumetableService.userByConsumetableByDeltaByCount(userId, startDate, endDate);
        Struts2Utils.renderText(resultCount + "");
    }

    /**
     * 收货地址
     *
     * @return
     */
    public String Address() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        return "Address";
                    }
                }
            }
        }
        return "login_index";
    }

    public void getAddressList() {
        userbyaddressList = userService.getUserbyaddress(userId);
        Struts2Utils.renderJson(userbyaddressList);
    }

    /**
     * 添加新收新地址
     *
     * @return
     */
    public String addAddress() {
        try {
            JSONObject object = JSONObject.fromObject(userJSON);
            int userId = object.getInt("userId");
            userbyaddressList = userService.getUserbyaddress(userId + "");
            if (userbyaddressList.size() < 4) {
                String province = object.getString("province");
                String city = object.getString("city");
                String district = object.getString("district");
                String address = object.getString("address");
                int zipCode = object.getInt("zipCode");
                String consignee = object.getString("consignee");
                String phone = object.getString("phone");
                userbyaddress = new Userbyaddress();
                userbyaddress.setAddress(address);
                userbyaddress.setCity(city);
                userbyaddress.setConsignee(consignee);
                userbyaddress.setDistrict(district);
                userbyaddress.setPhone(phone);
                userbyaddress.setProvince(province);
                userbyaddress.setStatus(1);
                userbyaddress.setUserId(userId);
                userbyaddress.setZipCode(zipCode);
                userService.addAddress(userbyaddress);
                if (userbyaddress.getId() != null) {
                    userService.setDefaultAddress(String.valueOf(userId), userbyaddress.getId());
                }
                Struts2Utils.renderText("success");
            } else {
                Struts2Utils.renderText("sizeError");
            }
        } catch (Exception e) {
            Struts2Utils.renderText("false");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置默认收货地址
     */
    public void setDefaultAddress() {
        try {
            userService.defaultAddress(userId, Integer.parseInt(id));
            userService.setDefaultAddress(String.valueOf(userId), Integer.parseInt(id));
            Struts2Utils.renderText("success");
        } catch (Exception e) {
            Struts2Utils.renderText("false");
            e.printStackTrace();
        }
    }

    /**
     * 删除收货地址
     */
    public void delAddress() {
        try {
            userService.delAddress(Integer.parseInt(id));
            Struts2Utils.renderText("success");
        } catch (Exception e) {
            e.printStackTrace();
            Struts2Utils.renderText("false");
        }

    }

    /**
     * 省
     */
    public void getProvinceList() {
        sProvinceList = regionService.getProvinceList();
        Struts2Utils.renderJson(sProvinceList);
    }

    /**
     * 市
     */
    public void getCityList() {
        sCityList = regionService.getCityListByProvinceId(id);
        Struts2Utils.renderJson(sCityList);
    }

    /**
     * 区
     */
    public void getDistrictList() {
        sDistrictList = regionService.getDistrictListByCityId(id);
        Struts2Utils.renderJson(sDistrictList);
    }


    /**
     * 编辑个人资料
     *
     * @return
     */
    public String MemberModify() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        return "MemberModify";
                    }
                }
            }
        }
        return "login_index";
    }


    /**
     * 修改用户资料
     */
    public void updateUser() {
        try {
            user = userService.findById(id);
            JSONObject object = JSONObject.fromObject(userJSON);
            user.setUserName(object.getString("userName"));
            String mobilePhone = object.getString("mobilePhone");
            if (!mobilePhone.equals("undefined") && !mobilePhone.equals("")) {
                user.setMobilePhone(mobilePhone);
            }
            String phone = object.getString("phone");
            if (!phone.equals("undefined") && !phone.equals("")) {
                user.setPhone(phone);
            }
            String mail = object.getString("mail");
            if (!mail.equals("undefined") && !mail.equals("")) {
                user.setMail(mail);
            }
            String qq = object.getString("qq");
            if (!qq.equals("undefined") && !qq.equals("")) {
                user.setQq(qq);
            }
            String userSign = object.getString("userSign");
            if (!userSign.equals("undefined") && !userSign.equals("")) {
                user.setSignature(userSign);
            }
            userService.add(user);
            Struts2Utils.renderText("true");
        } catch (Exception e) {
            e.printStackTrace();
            Struts2Utils.renderText("false");
        }
    }

    /**
     * 用户昵称是否存在
     */
    public void isUserNameExists() {
        User user = userService.isUserName(id, userId);
        if (user == null)
            Struts2Utils.renderText("true");
        else
            Struts2Utils.renderText("false");
    }

    /**
     * 修改头像
     *
     * @return
     */
    public String UserPhoto() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        return "UserPhoto";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 上传图像文件
     *
     * @return
     * @throws Exception
     */
    public String updateFaceFile() throws Exception {
        try {
            myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
            imageFileName = userId + "_" + new Date().getTime() + myFileFileName;
            String filePath = ServletActionContext.getServletContext().getRealPath("/uploadImages") + "/" + imageFileName;
            File imageFile = new File(filePath);
            if (myFile != null) {
                copy(myFile, imageFile);
//			       CutImages.equimultipleConvert(300, 300, imageFile, imageFile);
                CutImages.scale2(filePath, filePath, 300, 300, true);
                Struts2Utils.renderText("/uploadImages/" + imageFileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传图像
     *
     * @throws IOException
     */
    public void updateFace() throws IOException {
        hidPicUrl = hidPicUrl.replace("/", "\\");
        hidPicUrl = hidPicUrl.substring(hidPicUrl.lastIndexOf("\\") + 1, hidPicUrl.length());
        String fileName = ServletActionContext.getServletContext().getRealPath("uploadImages") + "/" + hidPicUrl;
        String faceName = ServletActionContext.getServletContext().getRealPath("faceImages") + "/" + hidPicUrl;
        try {
            CutImages cutImages = new CutImages(x1, y1, w, h, fileName, faceName);
            cutImages.cut();
            faceName = faceName.substring(faceName.lastIndexOf("ROOT") + 4, faceName.length()).replace("\\", "/");
            user = userService.findById(userId);
            user.setFaceImg(faceName);
            userService.add(user);
            Struts2Utils.renderText("success");
        } catch (Exception e) {
            e.printStackTrace();
            Struts2Utils.renderText("false");
        }
    }

    /**
     * 修改密码
     *
     * @return
     */
    public String UpdatePassWord() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        return "UpdatePassWord";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 修改密码
     */
    public void updatePwd() {
        if (StringUtil.isNotBlank(userId)) {
            try {
                user = userService.findById(userId);
                if (user != null) {
                    if (!user.getUserPwd().equals(id)) {
                        Struts2Utils.renderText("pwdError");
                    } else {
                        user.setUserPwd(userJSON);
                        userService.add(user);
                        Struts2Utils.renderText("success");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public List<ProductJSON> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductJSON> productList) {
        this.productList = productList;
    }

    public List<ProductJSON> getNewDateList() {
        return newDateList;
    }

    public void setNewDateList(List<ProductJSON> newDateList) {
        this.newDateList = newDateList;
    }

    public ProductJSON getProductJSON() {
        return productJSON;
    }

    public void setProductJSON(ProductJSON productJSON) {
        this.productJSON = productJSON;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public Spellbuyrecord getSpellbuyrecord() {
        return spellbuyrecord;
    }

    public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord) {
        this.spellbuyrecord = spellbuyrecord;
    }

    public BuyHistoryJSON getBuyHistoryJSON() {
        return buyHistoryJSON;
    }

    public void setBuyHistoryJSON(BuyHistoryJSON buyHistoryJSON) {
        this.buyHistoryJSON = buyHistoryJSON;
    }

    public List<BuyHistoryJSON> getBuyHistoryJSONList() {
        return buyHistoryJSONList;
    }

    public void setBuyHistoryJSONList(List<BuyHistoryJSON> buyHistoryJSONList) {
        this.buyHistoryJSONList = buyHistoryJSONList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSelectTime() {
        return selectTime;
    }

    public void setSelectTime(String selectTime) {
        this.selectTime = selectTime;
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

    public Shareinfo getShareinfo() {
        return shareinfo;
    }

    public void setShareinfo(Shareinfo shareinfo) {
        this.shareinfo = shareinfo;
    }

    public ShareJSON getShareJSON() {
        return shareJSON;
    }

    public void setShareJSON(ShareJSON shareJSON) {
        this.shareJSON = shareJSON;
    }

    public List<ShareJSON> getShareJSONList() {
        return shareJSONList;
    }

    public void setShareJSONList(List<ShareJSON> shareJSONList) {
        this.shareJSONList = shareJSONList;
    }

    public List<Userbyaddress> getUserbyaddressList() {
        return userbyaddressList;
    }

    public void setUserbyaddressList(List<Userbyaddress> userbyaddressList) {
        this.userbyaddressList = userbyaddressList;
    }

    public Userbyaddress getUserbyaddress() {
        return userbyaddress;
    }

    public void setUserbyaddress(Userbyaddress userbyaddress) {
        this.userbyaddress = userbyaddress;
    }

    public String getUserJSON() {
        return userJSON;
    }

    public List<RandomNumberJSON> getRandomNumberJSONList() {
        return randomNumberJSONList;
    }

    public void setRandomNumberJSONList(List<RandomNumberJSON> randomNumberJSONList) {
        this.randomNumberJSONList = randomNumberJSONList;
    }

    public RandomNumberJSON getRandomNumberJSON() {
        return randomNumberJSON;
    }

    public void setRandomNumberJSON(RandomNumberJSON randomNumberJSON) {
        this.randomNumberJSON = randomNumberJSON;
    }

    public void setUserJSON(String userJSON) {
        this.userJSON = userJSON;
    }

    public File getMyFile() {
        return myFile;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    public String getMyFileFileName() {
        return myFileFileName;
    }

    public void setMyFileFileName(String myFileFileName) {
        this.myFileFileName = myFileFileName;
    }

    public String getMyFileContentType() {
        return myFileContentType;
    }

    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public String getHidPicUrl() {
        return hidPicUrl;
    }

    public void setHidPicUrl(String hidPicUrl) {
        this.hidPicUrl = hidPicUrl;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public List<SProvince> getSProvinceList() {
        return sProvinceList;
    }

    public void setSProvinceList(List<SProvince> provinceList) {
        sProvinceList = provinceList;
    }

    public List<SCity> getSCityList() {
        return sCityList;
    }

    public void setSCityList(List<SCity> cityList) {
        sCityList = cityList;
    }

    public List<SDistrict> getSDistrictList() {
        return sDistrictList;
    }

    public void setSDistrictList(List<SDistrict> districtList) {
        sDistrictList = districtList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Commissionquery> getCommissionqueryList() {
        return commissionqueryList;
    }

    public void setCommissionqueryList(List<Commissionquery> commissionqueryList) {
        this.commissionqueryList = commissionqueryList;
    }

    public List<Applymention> getApplymentionList() {
        return applymentionList;
    }

    public void setApplymentionList(List<Applymention> applymentionList) {
        this.applymentionList = applymentionList;
    }

    public Applymention getApplymention() {
        return applymention;
    }

    public void setApplymention(Applymention applymention) {
        this.applymention = applymention;
    }

    public Commissionquery getCommissionquery() {
        return commissionquery;
    }

    public void setCommissionquery(Commissionquery commissionquery) {
        this.commissionquery = commissionquery;
    }

    public List<CommissionqueryJSON> getCommissionqueryJSONList() {
        return commissionqueryJSONList;
    }

    public void setCommissionqueryJSONList(
            List<CommissionqueryJSON> commissionqueryJSONList) {
        this.commissionqueryJSONList = commissionqueryJSONList;
    }

    public CommissionqueryJSON getCommissionqueryJSON() {
        return commissionqueryJSON;
    }

    public void setCommissionqueryJSON(CommissionqueryJSON commissionqueryJSON) {
        this.commissionqueryJSON = commissionqueryJSON;
    }

    public List<Commissionpoints> getCommissionpointsList() {
        return commissionpointsList;
    }

    public void setCommissionpointsList(List<Commissionpoints> commissionpointsList) {
        this.commissionpointsList = commissionpointsList;
    }

    public Commissionpoints getCommissionpoints() {
        return commissionpoints;
    }

    public void setCommissionpoints(Commissionpoints commissionpoints) {
        this.commissionpoints = commissionpoints;
    }

    public List<Orderdetail> getOrderdetailList() {
        return orderdetailList;
    }

    public void setOrderdetailList(List<Orderdetail> orderdetailList) {
        this.orderdetailList = orderdetailList;
    }

    public Orderdetailaddress getOrderdetailaddress() {
        return orderdetailaddress;
    }

    public void setOrderdetailaddress(Orderdetailaddress orderdetailaddress) {
        this.orderdetailaddress = orderdetailaddress;
    }


}
