package com.eypg.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Lotteryproductutil;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductCart;
import com.eypg.pojo.ProductJSON;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.LotteryproductutilService;
import com.eypg.service.ProductService;
import com.eypg.service.RandomnumberService;
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.DateUtil;
import com.eypg.util.MD5Util;
import com.eypg.util.MemCachedClientHelp;
import com.eypg.util.PaginationUtil;
import com.eypg.util.Struts2Utils;
import com.eypg.util.UserNameUtil;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("LotteryAction")
public class LotteryAction extends ActionSupport {

    private static final long serialVersionUID = 2321693841189871589L;

    @Autowired
    private LatestlotteryService latestlotteryService;
    @Autowired
    private SpellbuyproductService spellbuyproductService;
    @Autowired
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RandomnumberService randomnumberService;
    @Autowired
    private LotteryproductutilService lotteryproductutilService;

    private Latestlottery latestlottery;
    private List<Latestlottery> latestlotteryList;
    private ProductJSON productJSON;
    private ProductCart productCart;
    private Product product;
    private Spellbuyproduct spellbuyproduct;
    private Spellbuyrecord spellbuyrecord;
    private List<Lotteryproductutil> LotteryproductutilList;
    private User user;
    private String id;
    private int pageNo;
    private String pages;
    private String pageString;
    private int pageSize = 14;
    private int pageCount;
    private int resultCount;
    private long time;
    Calendar calendar = Calendar.getInstance();
    private static List<ProductJSON> upcomingAnnouncedList;
    private static List<ProductJSON> upcomingAnnouncedByTopList;
    private static Long nowDateByUpcomingAnnounced = System.currentTimeMillis();
    private static Long beginDateByUpcomingAnnounced;
    private static Long nowDateByUpcomingAnnouncedByTop = System.currentTimeMillis();
    private static Long beginDateByUpcomingAnnouncedByTop;

    public String index() {
        if (pageNo == 0) {
            pageNo = 1;
        }
        if (pages != null) {
            pageNo = Integer.parseInt(pages.split("_")[1]);
        }
        Pagination page = latestlotteryService.LatestAnnounced(pageNo, pageSize);
        resultCount = page.getResultCount();
        List<Latestlottery> objList = (List<Latestlottery>) page.getList();
        latestlotteryList = new ArrayList<Latestlottery>();
        for (int i = 0; i < objList.size(); i++) {
            latestlottery = new Latestlottery();
            latestlottery = objList.get(i);
            String userName = "";
            if (latestlottery.getUserName() != null && !latestlottery.getUserName().equals("")) {
                userName = latestlottery.getUserName();
            } else if (latestlottery.getBuyUser() != null && !latestlottery.getBuyUser().equals("")) {
                userName = latestlottery.getBuyUser();
                if (userName.indexOf("@") != -1) {
                    String[] u = userName.split("@");
                    String u1 = u[0].substring(0, 2) + "***";
                    userName = u1 + "@" + u[1];
                } else {
                    userName = userName.substring(0, 4) + "*** " + userName.substring(7);
                }
            }
            latestlottery.setBuyUser(userName);
            latestlotteryList.add(latestlottery);
        }
        pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/lottery/index/p_");
        return "index";
    }

    public void lotteryproductutilList() {
        LotteryproductutilList = lotteryproductutilService.loadAll();
        if (LotteryproductutilList.size() > 0) {
            Struts2Utils.renderJson(LotteryproductutilList);
        }
    }

    public synchronized void lotteryUtil() {
        String lotteryId = MD5Util.encode(id);
        if (MemCachedClientHelp.getIMemcachedCache().get(lotteryId) == null) {
            spellbuyproduct = spellbuyproductService.findById(id);
            if (spellbuyproduct.getSpStatus() == 2) {
                /**
                 * 某商品结束日期全站100条购买记录
                 */
                spellbuyrecord = (Spellbuyrecord) spellbuyrecordService.getEndBuyDateByProduct(spellbuyproduct.getSpellbuyProductId()).get(0);
                String newDate = spellbuyrecord.getBuyDate();
                List<Spellbuyrecord> dataList = spellbuyrecordService.getSpellbuyRecordByLast100(null, spellbuyproduct.getSpellbuyEndDate());
                Long DateSUM = 0L;
                Integer buyId = dataList.get(0).getFkSpellbuyProductId();
                int i100 = 0;
                for (int k = 0; k < dataList.size(); k++) {

                    if (k > 0) {
                        if (newDate.equals(dataList.get(k).getBuyDate()) && dataList.get(k).getFkSpellbuyProductId() != buyId) {
                            continue;
                        }
                    }
                    if (i100++ == 100)
                        break;

                    spellbuyrecord = dataList.get(k);
//					
                    calendar.setTime(DateUtil.SDateTimeToDate(spellbuyrecord.getBuyDate()));
//					Integer y = calendar.get(Calendar.YEAR);
//					Integer M = calendar.get(Calendar.MONTH)+1;
//					Integer d = calendar.get(Calendar.DAY_OF_MONTH);
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
                    DateSUM += Long.parseLong(sh + sm + ss + sss);
//					System.err.println("Id:"+spellbuyrecord.getFkSpellbuyProductId()+"date:"+spellbuyrecord.getBuyDate()+"buyID:"+spellbuyrecord.getSpellbuyRecordId());

                }
                System.err.println("NewLotteryUtil DateSUM:" + DateSUM + "    " + id);
                /**
                 * 计算出中奖码
                 */
                Integer winNumber = Integer.parseInt(String.valueOf(((DateSUM % spellbuyproduct.getSpellbuyPrice()) + 10000001)));

                System.err.println("NewLotteryUtil winNmuber:" + winNumber + "    " + spellbuyproduct.getSpellbuyProductId());
                boolean flag = false;
                Integer productPrice = spellbuyproduct.getSpellbuyPrice();
                //需要修改时间的Randomnumber
                //修改
                Randomnumber randomnumberUP = null;
                long winCount = 0;
                long count = productPrice;
                while (!flag) {
                    List<Object[]> objList = spellbuyrecordService.WinRandomNumber(spellbuyproduct.getSpellbuyProductId(), winNumber);
                    if (objList.size() == 0) {
                        winNumber++;
                        if (winNumber > (10000000 + productPrice)) {
                            winNumber = 10000001;
                        }
                    } else {
                        flag = true;
                        randomnumberUP = (Randomnumber) objList.get(0)[0];
                    }
                    count--;
                    if (count == 0) {
                        winNumber = Integer.parseInt(String.valueOf(((DateSUM % spellbuyproduct.getSpellbuyPrice()) + 10000001)));
                        break;
                    }
                }
                long price = productPrice;
                long sucess = (winNumber - 10000001); //我要的中奖号码  2
                long real = DateSUM % price; //真实的中奖号码 1
                long update = DateSUM; //我要的时间总和 14

                update += (sucess - real);
                winCount = sucess - real;

                if (randomnumberUP != null) {
                    String updateDate = randomnumberUP.getBuyDate();
                    Long dateTime = DateUtil.SDateTimeToDateBySSS(updateDate).getTime();
                    dateTime += winCount;
                    Date date = new Date(dateTime);
                    Spellbuyrecord spellbuyrecord2 = spellbuyrecordService.findById(String.valueOf(randomnumberUP.getSpellbuyrecordId()));
                    spellbuyrecord2.setBuyDate(DateUtil.DateTimeToStrBySSS(date));
                    randomnumberUP.setBuyDate(DateUtil.DateTimeToStrBySSS(date));
                    spellbuyrecordService.add(spellbuyrecord2);
                    randomnumberService.add(randomnumberUP);
                }

                List<Object[]> objList = spellbuyrecordService.randomByBuyHistoryByspellbuyProductId(spellbuyproduct.getSpellbuyProductId(), String.valueOf(winNumber));
                Randomnumber randomnumber = (Randomnumber) objList.get(0)[0];
                Spellbuyrecord spellbuyrecord = (Spellbuyrecord) objList.get(0)[1];
                user = (User) objList.get(0)[2];


                spellbuyrecord.setSpRandomNo(String.valueOf(winNumber));
                spellbuyrecord.setSpWinningStatus("1");
                spellbuyrecord.setBuyStatus("1");
                spellbuyrecordService.add(spellbuyrecord);

                int productPeriod = spellbuyproduct.getProductPeriod();

//				int Period = Integer.parseInt(product.getAttribute71());
//				++Period;
//				product.setAttribute71(String.valueOf(Period)); 
//				product.setStatus(1);
//				productService.add(product);

                spellbuyproduct.setSpStatus(1);
                spellbuyproductService.add(spellbuyproduct);

                product = productService.findById(String.valueOf(spellbuyproduct.getFkProductId()));
                if (product.getStatus() == 1) {
                    List<Spellbuyproduct> spellbuyproductOld = spellbuyproductService.findSpellbuyproductByProductIdIsStatus(spellbuyproduct.getFkProductId());
                    System.err.println("spellbuyproductOld:" + spellbuyproductOld);
                    System.err.println("spellbuyproductOld-size:" + spellbuyproductOld.size());
                    if (spellbuyproductOld.size() == 0) {
                        Spellbuyproduct spellbuyproduct2 = new Spellbuyproduct();
                        spellbuyproduct2.setFkProductId(spellbuyproduct.getFkProductId());
                        spellbuyproduct2.setProductPeriod(++productPeriod);
                        spellbuyproduct2.setSpellbuyCount(0);
                        spellbuyproduct2.setSpellbuyType(0);
                        spellbuyproduct2.setSpellbuyEndDate(DateUtil.DateTimeToStr(new Date()));
                        spellbuyproduct2.setSpellbuyPrice(spellbuyproduct.getSpellbuyPrice());
                        spellbuyproduct2.setSpellbuyStartDate(DateUtil.DateTimeToStr(new Date()));
                        spellbuyproduct2.setSpStatus(0);
                        if (product.getAttribute71().equals("hot")) {
                            spellbuyproduct2.setSpellbuyType(8);
                        } else {
                            spellbuyproduct2.setSpellbuyType(0);
                        }
                        spellbuyproductService.add(spellbuyproduct2);
                    }
                }


                List list = latestlotteryService.getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(spellbuyproduct.getSpellbuyProductId());

                if (list.size() == 0) {
                    latestlottery = new Latestlottery();
                    latestlottery.setProductId(spellbuyproduct.getFkProductId());
                    latestlottery.setProductName(product.getProductName());
                    latestlottery.setProductTitle(product.getProductTitle());
                    latestlottery.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                    latestlottery.setProductImg(product.getHeadImage());
                    latestlottery.setProductPeriod(spellbuyproduct.getProductPeriod());
                    latestlottery.setAnnouncedTime(newDate); //揭晓时间
                    latestlottery.setAnnouncedType(spellbuyproduct.getSpellbuyType());
                    latestlottery.setDateSum(update);
                    latestlottery.setBuyTime(spellbuyrecord.getBuyDate()); //购买时间
                    latestlottery.setSpellbuyRecordId(spellbuyrecord.getSpellbuyRecordId()); //购买记录ID
                    latestlottery.setSpellbuyProductId(spellbuyrecord.getFkSpellbuyProductId()); //某期商品ID
                    BigDecimal buyNumberCount = randomnumberService.RandomNumberByUserBuyCount(String.valueOf(user.getUserId()), spellbuyproduct.getSpellbuyProductId());
                    latestlottery.setBuyNumberCount(Integer.parseInt(String.valueOf(buyNumberCount))); //购买总数
                    latestlottery.setRandomNumber(winNumber); //中奖码
                    latestlottery.setLocation(user.getIpLocation());
                    latestlottery.setUserId(user.getUserId());
                    latestlottery.setUserName(UserNameUtil.userName(user));
                    latestlottery.setUserFace(user.getFaceImg());
                    latestlottery.setStatus(1);
                    latestlottery.setShareStatus(-1);
                    latestlottery.setShareId(null);
                    latestlotteryService.add(latestlottery);

                }
                MemCachedClientHelp.getIMemcachedCache().put(lotteryId, "y", new Date(12 * 60 * 60 * 1000));
                Struts2Utils.renderText("true");
            }
        } else {
            List list = latestlotteryService.getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(Integer.parseInt(id));
            if (list.size() > 0) {
                Struts2Utils.renderText("true");
            }
        }
    }

    public void lotteryUtilAjax() {
        String lotteryId = MD5Util.encode(id);
        if (MemCachedClientHelp.getIMemcachedCache().get(lotteryId) == null) {
            MemCachedClientHelp.getIMemcachedCache().put(lotteryId, "y", new Date(12 * 60 * 60 * 1000));
            spellbuyproduct = spellbuyproductService.findById(id);
            if (spellbuyproduct.getSpStatus() == 2) {
                /**
                 * 某商品结束日期全站100条购买记录
                 */
                List<Spellbuyrecord> dataList = spellbuyrecordService.getSpellbuyRecordByLast100(null, spellbuyproduct.getSpellbuyEndDate());
                Long DateSUM = 0L;
                String newDate = "";
                Integer buyId = dataList.get(0).getFkSpellbuyProductId();
                newDate = dataList.get(0).getBuyDate();
                int i100 = 0;
                for (int k = 0; k < dataList.size(); k++) {

                    if (k > 0) {
                        if (newDate.equals(dataList.get(k).getBuyDate()) && dataList.get(k).getFkSpellbuyProductId() != buyId) {
                            continue;
                        }
                    }
                    if (i100++ == 100)
                        break;

                    spellbuyrecord = dataList.get(k);
//					
                    calendar.setTime(DateUtil.SDateTimeToDate(spellbuyrecord.getBuyDate()));
//					Integer y = calendar.get(Calendar.YEAR);
//					Integer M = calendar.get(Calendar.MONTH)+1;
//					Integer d = calendar.get(Calendar.DAY_OF_MONTH);
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
                    DateSUM += Long.parseLong(sh + sm + ss + sss);
//					System.err.println("Id:"+spellbuyrecord.getFkSpellbuyProductId()+"date:"+spellbuyrecord.getBuyDate()+"buyID:"+spellbuyrecord.getSpellbuyRecordId());

                }
                System.err.println("NewLotteryUtil DateSUM:" + DateSUM + "    " + id);
                /**
                 * 计算出中奖码
                 */
                Integer winNumber = Integer.parseInt(String.valueOf(((DateSUM % spellbuyproduct.getSpellbuyPrice()) + 10000001)));

                System.err.println("NewLotteryUtil winNmuber:" + winNumber + "    " + spellbuyproduct.getSpellbuyProductId());
                boolean flag = false;
                Integer productPrice = productCart.getProductPrice();
                //需要修改时间的Randomnumber
                Randomnumber randomnumberUP = null;
                long winCount = 0;
                long count = productPrice;
                while (!flag) {
                    List<Object[]> objList = spellbuyrecordService.WinRandomNumber(productCart.getProductId(), winNumber);
                    if (objList.size() == 0) {
                        winNumber++;
                        if (winNumber > (10000000 + productPrice)) {
                            winNumber = 10000001;
                        }
                    } else {
                        flag = true;
                        randomnumberUP = (Randomnumber) objList.get(0)[0];
                    }
                    count--;
                    if (count == 0) {
                        winNumber = Integer.parseInt(String.valueOf(((DateSUM % productCart.getProductPrice()) + 10000001)));
                        break;
                    }
                }
                long price = productPrice;
                long sucess = (winNumber - 10000000); //我要的中奖号码  2
                long real = DateSUM % price; //真实的中奖号码 1
                long update = DateSUM; //我要的时间总和 14

                update += (sucess - real);
                winCount = sucess - real;

                if (randomnumberUP != null) {
                    String updateDate = randomnumberUP.getBuyDate();
                    Long dateTime = DateUtil.SDateTimeToDateBySSS(updateDate).getTime();
                    dateTime += winCount;
                    Date date = new Date(dateTime);
                    Spellbuyrecord spellbuyrecord2 = spellbuyrecordService.findById(String.valueOf(randomnumberUP.getSpellbuyrecordId()));
                    spellbuyrecord2.setBuyDate(DateUtil.DateTimeToStrBySSS(date));
                    randomnumberUP.setBuyDate(DateUtil.DateTimeToStrBySSS(date));
                    spellbuyrecordService.add(spellbuyrecord2);
                    randomnumberService.add(randomnumberUP);
                }

                List<Object[]> objList = spellbuyrecordService.randomByBuyHistoryByspellbuyProductId(spellbuyproduct.getSpellbuyProductId(), String.valueOf(winNumber));
                Randomnumber randomnumber = (Randomnumber) objList.get(0)[0];
                Spellbuyrecord spellbuyrecord = (Spellbuyrecord) objList.get(0)[1];
                user = (User) objList.get(0)[2];


                spellbuyrecord.setSpRandomNo(String.valueOf(winNumber));
                spellbuyrecord.setSpWinningStatus("1");
                spellbuyrecord.setBuyStatus("1");
                spellbuyrecordService.add(spellbuyrecord);

                int productPeriod = spellbuyproduct.getProductPeriod();

//				int Period = Integer.parseInt(product.getAttribute71());
//				++Period;
//				product.setAttribute71(String.valueOf(Period)); 
//				product.setStatus(1);
//				productService.add(product);

                spellbuyproduct.setSpStatus(1);
                spellbuyproductService.add(spellbuyproduct);

                product = productService.findById(String.valueOf(spellbuyproduct.getFkProductId()));
                if (product.getStatus() == 1) {
                    List<Spellbuyproduct> spellbuyproductOld = spellbuyproductService.findSpellbuyproductByProductIdIsStatus(spellbuyproduct.getFkProductId());
                    System.err.println("spellbuyproductOld:" + spellbuyproductOld);
                    System.err.println("spellbuyproductOld-size:" + spellbuyproductOld.size());
                    if (spellbuyproductOld.size() == 0) {
                        Spellbuyproduct spellbuyproduct2 = new Spellbuyproduct();
                        spellbuyproduct2.setFkProductId(spellbuyproduct.getFkProductId());
                        spellbuyproduct2.setProductPeriod(++productPeriod);
                        spellbuyproduct2.setSpellbuyCount(0);
                        spellbuyproduct2.setSpellbuyType(0);
                        spellbuyproduct2.setSpellbuyEndDate(DateUtil.DateTimeToStr(new Date()));
                        spellbuyproduct2.setSpellbuyPrice(spellbuyproduct.getSpellbuyPrice());
                        spellbuyproduct2.setSpellbuyStartDate(DateUtil.DateTimeToStr(new Date()));
                        spellbuyproduct2.setSpStatus(0);
                        if (product.getAttribute71().equals("hot")) {
                            spellbuyproduct2.setSpellbuyType(8);
                        } else {
                            spellbuyproduct2.setSpellbuyType(0);
                        }
                        spellbuyproductService.add(spellbuyproduct2);
                    }
                }


                List list = latestlotteryService.getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(spellbuyproduct.getSpellbuyProductId());

                if (list.size() == 0) {
                    latestlottery = new Latestlottery();
                    latestlottery.setProductId(spellbuyproduct.getFkProductId());
                    latestlottery.setProductName(product.getProductName());
                    latestlottery.setProductTitle(product.getProductTitle());
                    latestlottery.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                    latestlottery.setProductImg(product.getHeadImage());
                    latestlottery.setProductPeriod(spellbuyproduct.getProductPeriod());
                    latestlottery.setAnnouncedTime(newDate); //揭晓时间
                    latestlottery.setAnnouncedType(spellbuyproduct.getSpellbuyType());
                    latestlottery.setDateSum((DateSUM + winCount));
                    latestlottery.setBuyTime(spellbuyrecord.getBuyDate()); //购买时间
                    latestlottery.setSpellbuyRecordId(spellbuyrecord.getSpellbuyRecordId()); //购买记录ID
                    latestlottery.setSpellbuyProductId(spellbuyrecord.getFkSpellbuyProductId()); //某期商品ID
                    BigDecimal buyNumberCount = randomnumberService.RandomNumberByUserBuyCount(String.valueOf(user.getUserId()), spellbuyproduct.getSpellbuyProductId());
                    latestlottery.setBuyNumberCount(Integer.parseInt(String.valueOf(buyNumberCount))); //购买总数
                    latestlottery.setRandomNumber(winNumber); //中奖码
                    latestlottery.setLocation(user.getIpLocation());
                    latestlottery.setUserId(user.getUserId());
                    latestlottery.setUserName(UserNameUtil.userName(user));
                    latestlottery.setUserFace(user.getFaceImg());
                    latestlottery.setStatus(1);
                    latestlottery.setShareStatus(-1);
                    latestlottery.setShareId(null);
                    latestlotteryService.add(latestlottery);

                }
                Struts2Utils.renderJson(latestlottery);
            }
        } else {
            List list = latestlotteryService.getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(Integer.parseInt(id));
            if (list.size() > 0) {
                latestlottery = (Latestlottery) list.get(0);
                Struts2Utils.renderJson(latestlottery);
            }
        }
    }

    /**
     * 即将揭晓
     *
     * @return
     */

    public String upcomingAnnounced() {
        nowDateByUpcomingAnnounced = System.currentTimeMillis();
        if (beginDateByUpcomingAnnounced == null) {
            Pagination datePage = spellbuyproductService.upcomingAnnounced(pageNo, pageSize);
            List<Object[]> dateList = (List<Object[]>) datePage.getList();
            upcomingAnnouncedList = new ArrayList<ProductJSON>();
            for (int i = 0; i < dateList.size(); i++) {
                productJSON = new ProductJSON();
                product = (Product) dateList.get(i)[0];
                spellbuyproduct = (Spellbuyproduct) dateList.get(i)[1];
                productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                productJSON.setHeadImage(product.getHeadImage());
                productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(product.getProductName());
                productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                productJSON.setProductTitle(product.getProductTitle());
                upcomingAnnouncedList.add(productJSON);
            }
            beginDateByUpcomingAnnounced = System.currentTimeMillis();
            Struts2Utils.renderJson(upcomingAnnouncedList);
        } else {
            if ((nowDateByUpcomingAnnounced - beginDateByUpcomingAnnounced) < 5000) {
                Struts2Utils.renderJson(upcomingAnnouncedList);
            } else {
                beginDateByUpcomingAnnounced = System.currentTimeMillis();
                Pagination datePage = spellbuyproductService.upcomingAnnounced(pageNo, pageSize);
                List<Object[]> dateList = (List<Object[]>) datePage.getList();
                upcomingAnnouncedList = new ArrayList<ProductJSON>();
                for (int i = 0; i < dateList.size(); i++) {
                    productJSON = new ProductJSON();
                    product = (Product) dateList.get(i)[0];
                    spellbuyproduct = (Spellbuyproduct) dateList.get(i)[1];
                    productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                    productJSON.setHeadImage(product.getHeadImage());
                    productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                    productJSON.setProductName(product.getProductName());
                    productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                    productJSON.setProductTitle(product.getProductTitle());
                    upcomingAnnouncedList.add(productJSON);
                }
                Struts2Utils.renderJson(upcomingAnnouncedList);
            }
        }

        return null;
    }


    /**
     * 人气TOP榜
     *
     * @return
     */
    public String upcomingAnnouncedByTop() {
        nowDateByUpcomingAnnouncedByTop = System.currentTimeMillis();
        if (beginDateByUpcomingAnnouncedByTop == null) {
            Pagination datePage = spellbuyproductService.upcomingAnnouncedByTop(pageNo, pageSize);
            List<Object[]> dateList = (List<Object[]>) datePage.getList();
            upcomingAnnouncedByTopList = new ArrayList<ProductJSON>();
            for (int i = 0; i < dateList.size(); i++) {
                productJSON = new ProductJSON();
                product = (Product) dateList.get(i)[0];
                spellbuyproduct = (Spellbuyproduct) dateList.get(i)[1];
                productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                productJSON.setHeadImage(product.getHeadImage());
                productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(product.getProductName());
                productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                productJSON.setProductTitle(product.getProductTitle());
                upcomingAnnouncedByTopList.add(productJSON);
            }
            beginDateByUpcomingAnnouncedByTop = System.currentTimeMillis();
            Struts2Utils.renderJson(upcomingAnnouncedByTopList);
        } else {
            if ((nowDateByUpcomingAnnouncedByTop - beginDateByUpcomingAnnouncedByTop) < 5000) {
                Struts2Utils.renderJson(upcomingAnnouncedByTopList);
            } else {
                beginDateByUpcomingAnnouncedByTop = System.currentTimeMillis();
                Pagination datePage = spellbuyproductService.upcomingAnnouncedByTop(pageNo, pageSize);
                List<Object[]> dateList = (List<Object[]>) datePage.getList();
                upcomingAnnouncedByTopList = new ArrayList<ProductJSON>();
                for (int i = 0; i < dateList.size(); i++) {
                    productJSON = new ProductJSON();
                    product = (Product) dateList.get(i)[0];
                    spellbuyproduct = (Spellbuyproduct) dateList.get(i)[1];
                    productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                    productJSON.setHeadImage(product.getHeadImage());
                    productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                    productJSON.setProductName(product.getProductName());
                    productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                    productJSON.setProductTitle(product.getProductTitle());
                    upcomingAnnouncedByTopList.add(productJSON);
                }
                Struts2Utils.renderJson(upcomingAnnouncedByTopList);
            }
        }
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

    public Spellbuyrecord getSpellbuyrecord() {
        return spellbuyrecord;
    }

    public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord) {
        this.spellbuyrecord = spellbuyrecord;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPageString() {
        return pageString;
    }

    public void setPageString(String pageString) {
        this.pageString = pageString;
    }

    public ProductCart getProductCart() {
        return productCart;
    }

    public void setProductCart(ProductCart productCart) {
        this.productCart = productCart;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


}
	
