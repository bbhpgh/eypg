package com.eypg.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductCart;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.ProductService;
import com.eypg.service.RandomnumberService;
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.SpellbuyrecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext*.xml"})
@Repository
@Service("NewLotteryUtilByData")
public class NewLotteryUtilByData {
    @Autowired
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    private SpellbuyproductService spellbuyproductService;
    @Autowired
    private LatestlotteryService latestlotteryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RandomnumberService randomnumberService;

    private Product product;
    private User user;
    private Spellbuyproduct spellbuyproduct;
    private Latestlottery latestlottery;
    private Spellbuyrecord spellbuyrecord;
    private ProductCart productCart;


    Calendar calendar = Calendar.getInstance();


    @Test
    public void go() throws InterruptedException {
        List<Spellbuyproduct> list = spellbuyproductService.query("from Spellbuyproduct spellbuyproduct where spellbuyproduct.spStatus = 1");
        for (Spellbuyproduct spellbuyproduct : list) {
            productCart = new ProductCart();
            List<Object[]> proList = spellbuyproductService.findByProductId(spellbuyproduct.getSpellbuyProductId());
            product = (Product) proList.get(0)[0];
            spellbuyproduct = (Spellbuyproduct) proList.get(0)[1];
            productCart.setHeadImage(product.getHeadImage());
            productCart.setProductId(spellbuyproduct.getSpellbuyProductId());
            productCart.setProductName(product.getProductName());
            productCart.setProductPrice(product.getProductPrice());
            productCart.setProductTitle(product.getProductTitle());
            productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
            productCart.setProductPeriod(spellbuyproduct.getProductPeriod());

            lottery(productCart);
        }

    }

    public void lottery(ProductCart productCart) throws InterruptedException {
        /**
         * ???????????????
         */
//			Thread.sleep(175000);
        /**
         * ?????????????????????????????????
         */
        spellbuyrecord = (Spellbuyrecord) spellbuyrecordService.getEndBuyDateByProduct(productCart.getProductId()).get(0);
        String startDate = spellbuyrecord.getBuyDate();
//			String startDate = "2013-07-23 15:05:56";
        System.err.println("NewLotteryUtil startDate:" + startDate + "   " + productCart.getProductId());


        /**
         * ????????????100???????????????
         */
        Pagination page = spellbuyrecordService.getlotteryDetail(null, startDate, 0, 120);
        List<Object[]> dataList = (List<Object[]>) page.getList();
        Long DateSUM = 0L;
        String newDate = "";
        int buyId = ((Spellbuyrecord) dataList.get(0)[1]).getFkSpellbuyProductId();
        newDate = ((Spellbuyrecord) dataList.get(0)[1]).getBuyDate();
        int i100 = 0;
        for (int k = 0; k < dataList.size(); k++) {

            if (k > 0) {
                if (newDate.equals(((Spellbuyrecord) dataList.get(k)[1]).getBuyDate()) && ((Spellbuyrecord) dataList.get(k)[1]).getFkSpellbuyProductId() != buyId) {
                    continue;
                }
            }
            if (i100++ == 100)
                break;

            spellbuyrecord = (Spellbuyrecord) dataList.get(k)[1];
            calendar.setTime(DateUtil.SDateTimeToDateBySSS(spellbuyrecord.getBuyDate()));
//				Integer y = calendar.get(Calendar.YEAR);
//				Integer M = calendar.get(Calendar.MONTH)+1;
//				Integer d = calendar.get(Calendar.DAY_OF_MONTH);
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
//				System.err.println("Id:"+spellbuyrecord.getFkSpellbuyProductId()+"date:"+spellbuyrecord.getBuyDate()+"buyID:"+spellbuyrecord.getSpellbuyRecordId());

        }
        System.err.println("NewLotteryUtil DateSUM:" + DateSUM + "    " + productCart.getProductId());
        /**
         * ??????????????????
         */
        Integer winNumber = Integer.parseInt(String.valueOf(((DateSUM % productCart.getProductPrice()) + 10000001)));

        System.err.println("NewLotteryUtil winNmuber:" + winNumber + "    " + productCart.getProductId());
        boolean flag = false;
        Integer productPrice = productCart.getProductPrice();
        //?????????????????????Randomnumber
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
        long sucess = winNumber; //?????????????????????  2
        long real = DateSUM % price; //????????????????????? 1
        long update = DateSUM; //????????????????????? 14

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


        List<Object[]> objList = spellbuyrecordService.randomByBuyHistoryByspellbuyProductId(productCart.getProductId(), String.valueOf(winNumber));
        Randomnumber randomnumber = (Randomnumber) objList.get(0)[0];
        Spellbuyrecord spellbuyrecord = (Spellbuyrecord) objList.get(0)[1];
        User user2 = (User) objList.get(0)[2];

        spellbuyproduct = spellbuyproductService.findById(productCart.getProductId().toString());

        List list = latestlotteryService.getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(productCart.getProductId());

        if (list.size() == 0) {
            latestlottery = new Latestlottery();
            latestlottery.setProductId(spellbuyproduct.getFkProductId());
            latestlottery.setProductName(productCart.getProductName());
            latestlottery.setProductTitle(productCart.getProductTitle());
            latestlottery.setProductPrice(spellbuyproduct.getSpellbuyPrice());
            latestlottery.setProductImg(productCart.getHeadImage());
            latestlottery.setProductPeriod(spellbuyproduct.getProductPeriod());
            latestlottery.setAnnouncedTime(startDate); //????????????
            latestlottery.setAnnouncedType(spellbuyproduct.getSpellbuyType());
            latestlottery.setDateSum(update);
            latestlottery.setBuyTime(spellbuyrecord.getBuyDate()); //????????????
            latestlottery.setSpellbuyRecordId(spellbuyrecord.getSpellbuyRecordId()); //????????????ID
            latestlottery.setSpellbuyProductId(spellbuyrecord.getFkSpellbuyProductId()); //????????????ID
            BigDecimal buyNumberCount = randomnumberService.RandomNumberByUserBuyCount(String.valueOf(user2.getUserId()), spellbuyproduct.getSpellbuyProductId());
            latestlottery.setBuyNumberCount(Integer.parseInt(String.valueOf(buyNumberCount))); //????????????
            latestlottery.setRandomNumber(winNumber); //?????????
            latestlottery.setLocation(user2.getIpLocation());
            latestlottery.setUserId(user2.getUserId());
            latestlottery.setUserName(UserNameUtil.userName(user2));
            latestlottery.setUserFace(user2.getFaceImg());
            latestlottery.setStatus(1);
            latestlottery.setShareStatus(-1);
            latestlottery.setShareId(null);
            latestlotteryService.add(latestlottery);

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
                    spellbuyproduct2.setSpellbuyPrice(productCart.getProductPrice());
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
        }
    }

    public static void main(String[] args) throws InterruptedException {
//		System.err.println(11341449%2700);

        Calendar calendar = Calendar.getInstance();

        Long DateSUM = 0l;

        calendar.setTime(DateUtil.SDateTimeToDateBySSS("2013-12-11 15:38:19.095"));
//		Integer y = calendar.get(Calendar.YEAR);
//		Integer M = calendar.get(Calendar.MONTH)+1;
//		Integer d = calendar.get(Calendar.DAY_OF_MONTH);
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
        System.err.println(sh);
        System.err.println(sm);
        System.err.println(ss);
        System.err.println(sss);
        System.err.println(sh + sm + ss + sss);
        DateSUM += Long.parseLong(sh + sm + ss + sss);

        System.err.println(DateSUM);


        System.err.println(DateUtil.SDateTimeToDateBySSS("2013-12-11 15:38:19.095").getTime());
        System.err.println(DateUtil.SDateTimeToDateBySSS("2013-12-11 15:38:19.095").getTime() + 500);

//		for (int i = 0; i < 1000; i++) {
//			Thread.sleep(10);
//			String date = org.apache.commons.httpclient.util.DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
//			System.err.println(date);
//			
//		}

    }

}
