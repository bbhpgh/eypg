package com.eypg.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.ParticipateJSON;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductInfo;
import com.eypg.pojo.Productimage;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.ProductImageService;
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.util.DateUtil;
import com.eypg.util.MD5Util;
import com.eypg.util.MemCachedClientHelp;
import com.eypg.util.Struts2Utils;
import com.eypg.util.UserNameUtil;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("ProductsAction")
public class ProductsAction extends ActionSupport {

    private static final long serialVersionUID = 1626790673064716640L;

    @Autowired
    @Qualifier("spellbuyrecordService")
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    @Qualifier("spellbuyproductService")
    private SpellbuyproductService spellbuyproductService;
    @Autowired
    private LatestlotteryService latestlotteryService;
    @Autowired
    private ProductImageService productImageService;

    private ProductInfo productInfo;
    private Product product;
    private List<ParticipateJSON> ParticipateJSONList;
    private Spellbuyrecord spellbuyrecord;
    private List<Spellbuyrecord> spellbuyrecordList;
    private Spellbuyproduct spellbuyproduct;
    private Latestlottery latestlottery;
    private TreeMap<Integer, Integer> productPeriodList;
    private List<Productimage> productimageList;
    private User user;
    private String id;
    private String userId;
    private int pageNo;
    private int pageSize = 20;
    private int pageCount;
    private int resultCount;

    //	private static Long isStatusBeginDate;
//	private static Long isStatusNowDate = System.currentTimeMillis();
    private String isLotteryJSON;
    private Long endDate;
    private Long nowDate;

    HttpServletRequest request = null;

    public String index() throws ServletException, IOException {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        ParticipateJSONList = new ArrayList<ParticipateJSON>();
        List<Object[]> proList = spellbuyproductService.findByProductId(Integer.parseInt(id));
        productInfo = new ProductInfo();
        product = (Product) proList.get(0)[0];
        spellbuyproduct = (Spellbuyproduct) proList.get(0)[1];
        productInfo.setProductPeriod(spellbuyproduct.getProductPeriod());
        productInfo.setStatus(spellbuyproduct.getSpStatus());
        productInfo.setHeadImage(product.getHeadImage());
        productInfo.setProductDetail(product.getProductDetail());
        productInfo.setProductName(product.getProductName());
        productInfo.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productInfo.setProductTitle(product.getProductTitle());
        productInfo.setSpellbuyCount(spellbuyproduct.getSpellbuyCount());
        productInfo.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
//		if(spellbuyproduct.getSpStatus()==1){
//			latestlottery = (Latestlottery) latestlotteryService.getLotteryDetail(Integer.parseInt(id)).get(0);
//		}

        List<Object[]> objectList = spellbuyproductService.productPeriodList(spellbuyproduct.getFkProductId());
        productPeriodList = new TreeMap(new Comparator() {
            public int compare(Object o1, Object o2) {
                return o2.hashCode() - o1.hashCode();
            }
        });
        for (Object[] objects : objectList) {
            spellbuyproduct = (Spellbuyproduct) objects[1];
            productPeriodList.put(spellbuyproduct.getProductPeriod(), spellbuyproduct.getSpellbuyProductId());
        }

        productimageList = productImageService.findByProductId(String.valueOf(product.getProductId()), "show");

        Pagination pagination = spellbuyrecordService.LatestParticipate(id, pageNo, 6);
        List<Object[]> list = (List<Object[]>) pagination.getList();
        for (int i = 0; i < list.size(); i++) {
            ParticipateJSON participateJSON = new ParticipateJSON();
            spellbuyrecord = (Spellbuyrecord) list.get(i)[0];
            user = (User) list.get(i)[1];
            String userName = UserNameUtil.userName(user);
            participateJSON.setBuyCount(spellbuyrecord.getBuyPrice().toString());
            participateJSON.setBuyDate(DateUtil.getTime(DateUtil.SDateTimeToDate(spellbuyrecord.getBuyDate())));
            participateJSON.setIp_address(user.getIpAddress());
            participateJSON.setIp_location(user.getIpLocation());
            participateJSON.setUserName(userName);
            participateJSON.setUserId(String.valueOf(user.getUserId()));
            participateJSON.setUserFace(user.getFaceImg());
            ParticipateJSONList.add(participateJSON);
        }
        resultCount = spellbuyrecordService.LatestParticipateByCount(id);
        if (productInfo.getStatus() == 2) {
            return "lottery";
        } else if (productInfo.getStatus() == 1) {
            HttpServletResponse response = Struts2Utils.getResponse();
            response.sendRedirect("/lotteryDetail/" + productInfo.getSpellbuyProductId() + ".html");
//			Struts2Utils.render("text/html", "<script>window.location.href=\"/lotteryDetail/"+productInfo.getSpellbuyProductId()+".html\";</script>","encoding:UTF-8");
            return null;
//			HttpServletRequest request = Struts2Utils.getRequest();
//			HttpServletResponse response = Struts2Utils.getResponse();
//			request.getRequestDispatcher("/lotteryDetail/index.action?id="+productInfo.getSpellbuyProductId()).forward(request,response);
        } else {
            return "index";
        }
    }

    /**
     * 本商品往期拍购列表
     *
     * @return
     */
    public String getProductNewList() {
        spellbuyproduct = spellbuyproductService.findById(id);
        List<Object[]> objectList = spellbuyproductService.productPeriodList(spellbuyproduct.getFkProductId());
        productPeriodList = new TreeMap<Integer, Integer>();
        for (Object[] objects : objectList) {
            spellbuyproduct = (Spellbuyproduct) objects[1];
            productPeriodList.put(spellbuyproduct.getProductPeriod(), spellbuyproduct.getSpellbuyProductId());
        }
        Struts2Utils.renderJson(productPeriodList);
        return null;
    }

    public String ajaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        ParticipateJSONList = new ArrayList<ParticipateJSON>();
        Pagination pagination = spellbuyrecordService.LatestParticipate(id, pageNo, pageSize);
        List<Object[]> list = (List<Object[]>) pagination.getList();
        for (int i = 0; i < list.size(); i++) {
            ParticipateJSON participateJSON = new ParticipateJSON();
            spellbuyrecord = (Spellbuyrecord) list.get(i)[0];
            user = (User) list.get(i)[1];
            String userName = UserNameUtil.userName(user);
            participateJSON.setBuyCount(spellbuyrecord.getBuyPrice().toString());
            participateJSON.setBuyDate(spellbuyrecord.getBuyDate());
            participateJSON.setIp_address(user.getIpAddress());
            participateJSON.setIp_location(user.getIpLocation());
            participateJSON.setUserName(userName);
            participateJSON.setUserId(String.valueOf(user.getUserId()));
            participateJSON.setUserFace(user.getFaceImg());
            ParticipateJSONList.add(participateJSON);
        }
        Struts2Utils.renderJson(ParticipateJSONList);
        return null;
    }

    /**
     * 我的购买记录
     */
    public void getUserByHistory() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        spellbuyrecordList = spellbuyrecordService.getUserByHistory(userId, id);
                        Struts2Utils.renderJson(spellbuyrecordList);
                    }
                }
            }
        }
    }

    /**
     * 开奖倒计时请求状态
     */
    public void isLottery() {
        String key = MD5Util.encode(id + "status");
        if (MemCachedClientHelp.getIMemcachedCache().get(key) == null) {
            spellbuyproduct = spellbuyproductService.findById(id);
            endDate = DateUtil.SDateTimeToDate(spellbuyproduct.getSpellbuyEndDate()).getTime();
            nowDate = System.currentTimeMillis();
            isLotteryJSON = "{\"spStatus\":\"" + spellbuyproduct.getSpStatus() + "\",\"date\":\"" + ((endDate - nowDate) / 1000) + "\"}";
            MemCachedClientHelp.getIMemcachedCache().put(key, String.valueOf(endDate), new Date(10 * 60 * 1000));
            Struts2Utils.renderJson(isLotteryJSON);
        } else {
            endDate = Long.parseLong((String) MemCachedClientHelp.getIMemcachedCache().get(key));
            nowDate = System.currentTimeMillis();
            isLotteryJSON = "{\"spStatus\":\"2\",\"date\":\"" + ((endDate - nowDate) / 1000) + "\"}";
            Struts2Utils.renderJson(isLotteryJSON);
        }
//		isStatusNowDate = System.currentTimeMillis();
//		if(isStatusBeginDate==null){
//			spellbuyproduct = spellbuyproductService.findById(id);
//			endDate = DateUtil.SDateTimeToDate(spellbuyproduct.getSpellbuyEndDate()).getTime();
//			nowDate = System.currentTimeMillis();
//			isLotteryJSON = "{\"spStatus\":\""+spellbuyproduct.getSpStatus()+"\",\"date\":\""+((endDate-nowDate)/1000)+"\"}";
//			isStatusBeginDate = System.currentTimeMillis();
//			Struts2Utils.renderJson(isLotteryJSON);
//		}else{
//			if((isStatusNowDate-isStatusBeginDate)<1000){
//				Struts2Utils.renderJson(isLotteryJSON);
//			}else{
//				isStatusBeginDate = System.currentTimeMillis();
//				spellbuyproduct = spellbuyproductService.findById(id);
//				endDate = DateUtil.SDateTimeToDate(spellbuyproduct.getSpellbuyEndDate()).getTime();
//				nowDate = System.currentTimeMillis();
//				isLotteryJSON = "{\"spStatus\":\""+spellbuyproduct.getSpStatus()+"\",\"date\":\""+((endDate-nowDate)/1000)+"\"}";
//				Struts2Utils.renderJson(isLotteryJSON);
//			}
//		}
    }

    public static void main(String[] args) {
//		Long endDate = DateUtil.SDateTimeToDate("2013-11-27 14:12:38").getTime();
//		Long nowDate = System.currentTimeMillis();
//		System.err.println((endDate-nowDate)/1000);
//		System.err.println(DateUtil.DateTimeToStr(DateUtil.subMinute(new Date(), -3)));
        String lotteryId = MD5Util.encode("10022");
        String a = (String) MemCachedClientHelp.getIMemcachedCache().get(lotteryId);
        System.err.println(a);
        MemCachedClientHelp.getIMemcachedCache().remove(lotteryId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
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

    public List<ParticipateJSON> getParticipateJSONList() {
        return ParticipateJSONList;
    }

    public void setParticipateJSONList(List<ParticipateJSON> participateJSONList) {
        ParticipateJSONList = participateJSONList;
    }

    public Spellbuyrecord getSpellbuyrecord() {
        return spellbuyrecord;
    }

    public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord) {
        this.spellbuyrecord = spellbuyrecord;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public TreeMap<Integer, Integer> getProductPeriodList() {
        return productPeriodList;
    }

    public void setProductPeriodList(TreeMap<Integer, Integer> productPeriodList) {
        this.productPeriodList = productPeriodList;
    }

    public Latestlottery getLatestlottery() {
        return latestlottery;
    }

    public void setLatestlottery(Latestlottery latestlottery) {
        this.latestlottery = latestlottery;
    }

    public List<Productimage> getProductimageList() {
        return productimageList;
    }

    public void setProductimageList(List<Productimage> productimageList) {
        this.productimageList = productimageList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Spellbuyrecord> getSpellbuyrecordList() {
        return spellbuyrecordList;
    }

    public void setSpellbuyrecordList(List<Spellbuyrecord> spellbuyrecordList) {
        this.spellbuyrecordList = spellbuyrecordList;
    }


}
