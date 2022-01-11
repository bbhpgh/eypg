package com.eypg.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eypg.dao.Pagination;
import com.eypg.pojo.BuyHistoryJSON;
import com.eypg.pojo.Consumetable;
import com.eypg.pojo.DetailBybuyerJSON;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.LotteryDetailJSON;
import com.eypg.pojo.ParticipateJSON;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductJSON;
import com.eypg.pojo.Productimage;
import com.eypg.pojo.RandomNumberJSON;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.ProductImageService;
import com.eypg.service.RandomnumberService;
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.util.DateUtil;
import com.eypg.util.StringUtil;
import com.eypg.util.Struts2Utils;
import com.eypg.util.UserNameUtil;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("LotteryDetailAction")
public class LotteryDetailAction extends ActionSupport {
    private static final long serialVersionUID = -8369327417332420791L;

    @Autowired
    @Qualifier("latestlotteryService")
    private LatestlotteryService latestlotteryService;
    @Autowired
    private RandomnumberService randomnumberService;
    @Autowired
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    private SpellbuyproductService spellbuyproductService;
    @Autowired
    private ProductImageService productImageService;

    private Latestlottery latestlottery;
    private List<Latestlottery> latestlotteryList;
    private List<RandomNumberJSON> randomNumberJSONList;
    private DetailBybuyerJSON detailBybuyerJSON;
    private List<DetailBybuyerJSON> detailBybuyerJSONList;
    private List<ParticipateJSON> ParticipateJSONList;
    private RandomNumberJSON randomNumberJSON;
    private ProductJSON productJSON;
    private Randomnumber randomnumber;
    private List<Randomnumber> randomnumberList;
    private Spellbuyrecord spellbuyrecord;
    private Product product;
    private Spellbuyproduct spellbuyproduct;
    private User user;
    private TreeMap<Integer, Integer> productPeriodList;
    private LotteryDetailJSON lotteryDetailJSON;
    private List<LotteryDetailJSON> lotteryDetailJSONList;
    private List<Productimage> productimageList;

    private String id;
    private int pageNo;
    private int pageSize = 20;
    private int pageCount;
    private int resultCount;
    private Integer buyerCount;
    private int buyResultCount;
    private int newProductId;
    private int newProductPeriod;
    private String winNumber = "";
    private String startDate;
    private String endDate;
    private Long DateSUM = 0L;
    Calendar calendar = Calendar.getInstance();

    public String index() {
        randomNumberJSONList = new ArrayList<RandomNumberJSON>();
        latestlottery = (Latestlottery) latestlotteryService.getLotteryDetail(Integer.parseInt(id)).get(0);
        randomnumberList = randomnumberService.LotteryDetailByRandomnumber(latestlottery.getUserId(), latestlottery.getSpellbuyProductId());

        List<Object[]> objectList = spellbuyproductService.productPeriodList(latestlottery.getProductId());
        productPeriodList = new TreeMap(new Comparator() {
            public int compare(Object o1, Object o2) {
                return o2.hashCode() - o1.hashCode();
            }
        });
        for (Object[] objects : objectList) {
            spellbuyproduct = (Spellbuyproduct) objects[1];
            productPeriodList.put(spellbuyproduct.getProductPeriod(), spellbuyproduct.getSpellbuyProductId());
        }

        productimageList = productImageService.findByProductId(String.valueOf(latestlottery.getProductId()), "show");

        /**
         * 获取该商品最新一期
         */
        product = (Product) objectList.get(0)[0];
        spellbuyproduct = (Spellbuyproduct) objectList.get(0)[1];
        if (product.getStatus() == 1) {
            productJSON = new ProductJSON();
            productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
            productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
            productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
            productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
        }

        resultCount = latestlotteryService.getLotteryDetailBybuyerListByCount(latestlottery.getSpellbuyProductId());

//		Pagination pagination = spellbuyrecordService.LatestParticipate(id, pageNo, pageSize);
//		buyResultCount = pagination.getResultCount();

        /**
         * 得到该商品最后购买记录
         */
//		spellbuyrecord = (Spellbuyrecord) spellbuyrecordService.getEndBuyDateByProduct(latestlottery.getSpellbuyProductId()).get(0);

        startDate = latestlottery.getAnnouncedTime();
//		System.err.println("LotteryDetail startDate:"+startDate+"   "+latestlottery.getSpellbuyProductId());
        /**
         * 某商品结束日期全站100条购买记录
         */
        Pagination page = spellbuyrecordService.getlotteryDetail(null, startDate, 0, 120);
        List<Object[]> dataList = (List<Object[]>) page.getList();
        lotteryDetailJSONList = new ArrayList<LotteryDetailJSON>();
        int buyId = ((Spellbuyrecord) dataList.get(0)[1]).getFkSpellbuyProductId();
        int i100 = 0;
        for (int j = 0; j < dataList.size(); j++) {

            if (j > 0) {
                if (startDate.equals(((Spellbuyrecord) dataList.get(j)[1]).getBuyDate()) && ((Spellbuyrecord) dataList.get(j)[1]).getFkSpellbuyProductId() != buyId) {
                    continue;
                }
            }
            if (i100++ == 100)
                break;


            lotteryDetailJSON = new LotteryDetailJSON();
            product = (Product) dataList.get(j)[0];
            spellbuyrecord = (Spellbuyrecord) dataList.get(j)[1];
            user = (User) dataList.get(j)[2];
            spellbuyproduct = (Spellbuyproduct) dataList.get(j)[3];
            lotteryDetailJSON.setBuyCount(spellbuyrecord.getBuyPrice());
            lotteryDetailJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
            lotteryDetailJSON.setProductName(product.getProductName());
            lotteryDetailJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
            lotteryDetailJSON.setProductTitle(product.getProductTitle());
            lotteryDetailJSON.setBuyDate(spellbuyrecord.getBuyDate().split(" ")[0]);
            lotteryDetailJSON.setBuyTime(spellbuyrecord.getBuyDate().split(" ")[1]);

            calendar.setTime(DateUtil.SDateTimeToDateBySSS(spellbuyrecord.getBuyDate()));
//			Integer y = calendar.get(Calendar.YEAR);
//			Integer M = calendar.get(Calendar.MONTH)+1;
//			Integer d = calendar.get(Calendar.DAY_OF_MONTH);
            Integer h = calendar.get(Calendar.HOUR_OF_DAY);
            Integer m = calendar.get(Calendar.MINUTE);
            Integer s1 = calendar.get(Calendar.SECOND);
            Integer ss1 = calendar.get(Calendar.MILLISECOND);
            String sh = "";
            String sm = "";
            String ss = "";
            String sss = "";
            if (h < 10) {
                sh = "0" + h;
            } else {
                sh = h.toString();
            }
            if (m < 10) {
                sm = "0" + m;
            } else {
                sm = m.toString();
            }
            if (s1 < 10) {
                ss = "0" + s1;
            } else {
                ss = s1.toString();
            }
            if (ss1 < 10) {
                sss = "00" + ss1;
            } else if (ss1 < 100) {
                sss = "0" + ss1;
            } else {
                sss = ss1.toString();
            }
            lotteryDetailJSON.setDateSum(sh + sm + ss + sss);
//			DateSUM += Long.parseLong(sh+sm+ss);
            DateSUM = latestlottery.getDateSum();

            String userName = UserNameUtil.userName(user);
            lotteryDetailJSON.setUserName(userName);
            lotteryDetailJSON.setUserId(user.getUserId());
            lotteryDetailJSONList.add(lotteryDetailJSON);
//			System.err.println("Idabc:"+spellbuyrecord.getFkSpellbuyProductId()+"date:"+spellbuyrecord.getBuyDate()+"buyID:"+spellbuyrecord.getSpellbuyRecordId());
        }

        char[] s = String.valueOf(latestlottery.getRandomNumber()).toCharArray();
        for (int i = 0; i < s.length; i++) {
            winNumber += "<li class=\"Code_" + s[i] + "\">" + s[i] + "<b></b></li>";
        }
        buyerCount = 0;
        for (Randomnumber randomnumber : randomnumberList) {
            randomNumberJSON = new RandomNumberJSON();
            String[] randoms = randomnumber.getRandomNumber().split(",");
            String numbers = "";
            for (String string : randoms) {
                numbers += "<b>" + string + "</b>";
                buyerCount++;
            }
            randomNumberJSON.setRandomNumbers(numbers);
            randomNumberJSON.setBuyDate(randomnumber.getBuyDate());
            randomNumberJSONList.add(randomNumberJSON);
        }
        return "index";
    }

    public static void main(String[] args) throws ParseException {
//		String number = "10020180";
//		char [] s = number.toCharArray();
//		String str = "";
//		for (int i = 0; i < s.length; i++) {
//			str += "<li class=\"Code_"+s[i]+"\">"+s[i]+"<b></b></li>";
//		}
//		System.err.println(str);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        String date = "2013-06-27 12:02:32.455";

        System.err.println(sdf.format(new Date()));


//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(sdf.parse(date));
//		
//		System.err.println(calendar.get(Calendar.YEAR));
//		System.err.println(calendar.get(Calendar.MONTH)+1);
//		System.err.println(calendar.get(Calendar.DAY_OF_MONTH));
//		System.err.println(calendar.get(Calendar.HOUR_OF_DAY));
//		System.err.println(calendar.get(Calendar.MINUTE));
//		System.err.println(calendar.get(Calendar.SECOND));

//		Integer y = Integer.parseInt(date.split("-")[0]);
//		Integer m = Integer.parseInt(date.split("-")[1]);
////		Integer d = Integer.parseInt(date.lastIndexOf("-",1));
//		System.err.println(y+"  "+m+"   ");
//		System.err.println(StringUtil.splitPreserveAllTokens("-")[1]);
    }

    /**
     * 获取该商品最新一期
     */
    public void getNewProductResult() {
        productJSON = new ProductJSON();
        List<Object[]> objectList = spellbuyproductService.productPeriodList(Integer.parseInt(id));
        product = (Product) objectList.get(0)[0];
        spellbuyproduct = (Spellbuyproduct) objectList.get(0)[1];
        productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
        Struts2Utils.renderJson(productJSON);
    }

    /**
     * 购买人详情分页
     *
     * @return
     */
    public String getLotteryDetailBybuyerListAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Pagination datePage = latestlotteryService.getLotteryDetailBybuyerList(Integer.parseInt(id), pageNo, 10);
        List<Object[]> dataList = (List<Object[]>) datePage.getList();
        detailBybuyerJSONList = new ArrayList<DetailBybuyerJSON>();
        for (int j = 0; j < dataList.size(); j++) {
            detailBybuyerJSON = new DetailBybuyerJSON();
            randomnumber = (Randomnumber) dataList.get(j)[0];
            user = (User) dataList.get(j)[1];
            String[] randoms = randomnumber.getRandomNumber().split(",");
            String numbers = "";
            for (String string : randoms) {
                numbers += "<b>" + string + "</b>";
            }
            detailBybuyerJSON.setBuyCount(randoms.length + "");
            detailBybuyerJSON.setBuyTime(randomnumber.getBuyDate());
            detailBybuyerJSON.setFaceImg(user.getFaceImg());
            detailBybuyerJSON.setRandomNumber(numbers);
            detailBybuyerJSON.setUserId(user.getUserId() + "");
            String userName = UserNameUtil.userName(user);
            detailBybuyerJSON.setUserName(userName);
            detailBybuyerJSONList.add(detailBybuyerJSON);
        }
        Struts2Utils.renderJson(detailBybuyerJSONList);
        return null;
    }

    /**
     * 所有购买记录分页
     *
     * @return
     */
    public String buyListAjaxPage() {
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
            ParticipateJSONList.add(participateJSON);
        }
        Struts2Utils.renderJson(ParticipateJSONList);
        return null;
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

    public Latestlottery getLatestlottery() {
        return latestlottery;
    }

    public void setLatestlottery(Latestlottery latestlottery) {
        this.latestlottery = latestlottery;
    }

    public List<Latestlottery> getLatestlotteryList() {
        return latestlotteryList;
    }

    public void setLatestlotteryList(List<Latestlottery> latestlotteryList) {
        this.latestlotteryList = latestlotteryList;
    }

    public List<RandomNumberJSON> getRandomNumberJSONList() {
        return randomNumberJSONList;
    }

    public void setRandomNumberJSONList(List<RandomNumberJSON> randomNumberJSONList) {
        this.randomNumberJSONList = randomNumberJSONList;
    }

    public Randomnumber getRandomnumber() {
        return randomnumber;
    }

    public void setRandomnumber(Randomnumber randomnumber) {
        this.randomnumber = randomnumber;
    }

    public Spellbuyrecord getSpellbuyrecord() {
        return spellbuyrecord;
    }

    public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord) {
        this.spellbuyrecord = spellbuyrecord;
    }

    public RandomNumberJSON getRandomNumberJSON() {
        return randomNumberJSON;
    }

    public void setRandomNumberJSON(RandomNumberJSON randomNumberJSON) {
        this.randomNumberJSON = randomNumberJSON;
    }

    public List<Randomnumber> getRandomnumberList() {
        return randomnumberList;
    }

    public void setRandomnumberList(List<Randomnumber> randomnumberList) {
        this.randomnumberList = randomnumberList;
    }

    public Integer getBuyerCount() {
        return buyerCount;
    }

    public void setBuyerCount(Integer buyerCount) {
        this.buyerCount = buyerCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DetailBybuyerJSON getDetailBybuyerJSON() {
        return detailBybuyerJSON;
    }

    public void setDetailBybuyerJSON(DetailBybuyerJSON detailBybuyerJSON) {
        this.detailBybuyerJSON = detailBybuyerJSON;
    }

    public List<DetailBybuyerJSON> getDetailBybuyerJSONList() {
        return detailBybuyerJSONList;
    }

    public void setDetailBybuyerJSONList(
            List<DetailBybuyerJSON> detailBybuyerJSONList) {
        this.detailBybuyerJSONList = detailBybuyerJSONList;
    }

    public int getBuyResultCount() {
        return buyResultCount;
    }

    public void setBuyResultCount(int buyResultCount) {
        this.buyResultCount = buyResultCount;
    }

    public List<ParticipateJSON> getParticipateJSONList() {
        return ParticipateJSONList;
    }

    public void setParticipateJSONList(List<ParticipateJSON> participateJSONList) {
        ParticipateJSONList = participateJSONList;
    }

    public int getNewProductId() {
        return newProductId;
    }

    public void setNewProductId(int newProductId) {
        this.newProductId = newProductId;
    }

    public int getNewProductPeriod() {
        return newProductPeriod;
    }

    public void setNewProductPeriod(int newProductPeriod) {
        this.newProductPeriod = newProductPeriod;
    }

    public Spellbuyproduct getSpellbuyproduct() {
        return spellbuyproduct;
    }

    public void setSpellbuyproduct(Spellbuyproduct spellbuyproduct) {
        this.spellbuyproduct = spellbuyproduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductJSON getProductJSON() {
        return productJSON;
    }

    public void setProductJSON(ProductJSON productJSON) {
        this.productJSON = productJSON;
    }

    public TreeMap<Integer, Integer> getProductPeriodList() {
        return productPeriodList;
    }

    public void setProductPeriodList(TreeMap<Integer, Integer> productPeriodList) {
        this.productPeriodList = productPeriodList;
    }

    public String getWinNumber() {
        return winNumber;
    }

    public void setWinNumber(String winNumber) {
        this.winNumber = winNumber;
    }

    public LotteryDetailJSON getLotteryDetailJSON() {
        return lotteryDetailJSON;
    }

    public void setLotteryDetailJSON(LotteryDetailJSON lotteryDetailJSON) {
        this.lotteryDetailJSON = lotteryDetailJSON;
    }

    public List<LotteryDetailJSON> getLotteryDetailJSONList() {
        return lotteryDetailJSONList;
    }

    public void setLotteryDetailJSONList(
            List<LotteryDetailJSON> lotteryDetailJSONList) {
        this.lotteryDetailJSONList = lotteryDetailJSONList;
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

    public Long getDateSUM() {
        return DateSUM;
    }

    public void setDateSUM(Long dateSUM) {
        DateSUM = dateSUM;
    }

    public List<Productimage> getProductimageList() {
        return productimageList;
    }

    public void setProductimageList(List<Productimage> productimageList) {
        this.productimageList = productimageList;
    }

}
