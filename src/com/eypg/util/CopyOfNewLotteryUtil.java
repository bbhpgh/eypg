package com.eypg.util;

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
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.SpellbuyrecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext*.xml"})
@Repository
@Service("CopyOfNewLotteryUtil")
public class CopyOfNewLotteryUtil {
    @Autowired
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    private SpellbuyproductService spellbuyproductService;
    @Autowired
    private LatestlotteryService latestlotteryService;
    @Autowired
    private ProductService productService;

    private Product product;
    private User user;
    private Spellbuyproduct spellbuyproduct;
    private Latestlottery latestlottery;
    private Spellbuyrecord spellbuyrecord;
    private ProductCart productCart;


    Calendar calendar = Calendar.getInstance();


    @Test
    public void go() throws InterruptedException {
        productCart = new ProductCart();
        List<Object[]> proList = spellbuyproductService.findByProductId(149);
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

    public void lottery(ProductCart productCart) throws InterruptedException {
        Thread.sleep(10000);
        /**
         * 得到该商品最后购买记录
         */
        spellbuyrecord = (Spellbuyrecord) spellbuyrecordService.getEndBuyDateByProduct(productCart.getProductId()).get(0);
        String startDate = spellbuyrecord.getBuyDate();
        System.err.println("NewLotteryUtil startDate:" + startDate + "   " + productCart.getProductId());
        /**
         * 某商品结束日期全站100条购买记录
         */
        Pagination page = spellbuyrecordService.getlotteryDetail(null, startDate, 0, 100);
        List<Object[]> dataList = (List<Object[]>) page.getList();
        Long DateSUM = 0L;
        for (int k = 0; k < dataList.size(); k++) {
            product = (Product) dataList.get(k)[0];
            spellbuyrecord = (Spellbuyrecord) dataList.get(k)[1];
            user = (User) dataList.get(k)[2];
            spellbuyproduct = (Spellbuyproduct) dataList.get(k)[3];
//				
            calendar.setTime(DateUtil.SDateTimeToDate(spellbuyrecord.getBuyDate()));
//				Integer y = calendar.get(Calendar.YEAR);
//				Integer M = calendar.get(Calendar.MONTH)+1;
//				Integer d = calendar.get(Calendar.DAY_OF_MONTH);
            Integer h = calendar.get(Calendar.HOUR_OF_DAY);
            Integer m = calendar.get(Calendar.MINUTE);
            Integer s1 = calendar.get(Calendar.SECOND);
            String shs = "";
            String sms = "";
            String sss = "";
            if (h < 10) {
                shs = "0" + h;
            } else {
                shs = h.toString();
            }
            if (m < 10) {
                sms = "0" + m;
            } else {
                sms = m.toString();
            }
            if (s1 < 10) {
                sss = "0" + s1;
            } else {
                sss = s1.toString();
            }
            DateSUM += Long.parseLong(shs + sms + sss);

        }
        System.err.println("NewLotteryUtil DateSUM:" + DateSUM + "    " + productCart.getProductId());
        /**
         * 计算出中奖码
         */
        Integer winNumber = Integer.parseInt(String.valueOf(((DateSUM % productCart.getProductPrice()) + 10000001)));
        System.err.println("NewLotteryUtil winNmuber:" + winNumber + "    " + productCart.getProductId());

        System.err.println("winNmuber:" + winNumber);
        boolean flag = false;
        Integer productPrice = productCart.getProductPrice();
        int count = productPrice;
        while (!flag) {
            List<Object[]> objList = spellbuyrecordService.WinRandomNumber(productCart.getProductId(), winNumber);
            if (objList.size() == 0) {
                winNumber++;
                if (winNumber > (10000000 + productPrice)) {
                    winNumber = 10000001;
                }
            } else {
                flag = true;
            }
            System.err.println("++" + winNumber);
            count--;
            if (count == 0) {
                winNumber = Integer.parseInt(String.valueOf(((DateSUM % productCart.getProductPrice()) + 10000001)));
                break;
            }
        }
        System.err.println("++" + winNumber);
        List<Object[]> objList = spellbuyrecordService.randomByBuyHistoryByspellbuyProductId(productCart.getProductId(), String.valueOf(winNumber));
        Randomnumber randomnumber = (Randomnumber) objList.get(0)[0];
        Spellbuyrecord spellbuyrecord = (Spellbuyrecord) objList.get(0)[1];
        User user2 = (User) objList.get(0)[2];

        spellbuyproduct = spellbuyproductService.findById(productCart.getProductId().toString());
        /**
         * 开奖到计时
         */
        Thread.sleep(30000);

        latestlottery = new Latestlottery();
        latestlottery.setProductId(spellbuyproduct.getFkProductId());
        latestlottery.setProductName(productCart.getProductName());
        latestlottery.setProductTitle(productCart.getProductTitle());
        latestlottery.setProductPrice(productCart.getProductPrice());
        latestlottery.setProductImg(productCart.getHeadImage());
        latestlottery.setProductPeriod(productCart.getProductPeriod());
        latestlottery.setAnnouncedTime(startDate); //揭晓时间
        latestlottery.setBuyTime(spellbuyrecord.getBuyDate()); //购买时间
        latestlottery.setSpellbuyRecordId(spellbuyrecord.getSpellbuyRecordId()); //购买记录ID
        latestlottery.setSpellbuyProductId(spellbuyrecord.getFkSpellbuyProductId()); //某期商品ID
        latestlottery.setBuyNumberCount(spellbuyrecord.getBuyPrice()); //购买总数
        latestlottery.setRandomNumber(winNumber); //中奖码
        latestlottery.setLocation(user2.getIpLocation());
        latestlottery.setUserId(user2.getUserId());
        if (StringUtils.isNotEmpty(user2.getUserName())) {
            latestlottery.setUserName(user2.getUserName());
        }
        if (StringUtils.isNotEmpty(user2.getPhone())) {
            latestlottery.setBuyUser(user2.getPhone());
        }
        if (StringUtils.isNotEmpty(user2.getMail())) {
            latestlottery.setBuyUser(user2.getMail());
        }
        latestlottery.setUserFace(user2.getFaceImg());
        latestlottery.setStatus(1);
        latestlottery.setShareStatus(-1);
        latestlottery.setShareId(null);
        latestlotteryService.add(latestlottery);

        spellbuyrecord.setSpRandomNo(String.valueOf(winNumber));
        spellbuyrecord.setSpWinningStatus("1");
        spellbuyrecord.setBuyStatus("1");
        spellbuyrecordService.add(spellbuyrecord);

        int productPeriod = productCart.getProductPeriod();
        Spellbuyproduct spellbuyproduct2 = new Spellbuyproduct();
        spellbuyproduct2.setFkProductId(spellbuyproduct.getFkProductId());
        spellbuyproduct2.setProductPeriod(++productPeriod);
        spellbuyproduct2.setSpellbuyCount(0);
        spellbuyproduct2.setSpellbuyEndDate(DateUtil.DateTimeToStr(new Date()));
        spellbuyproduct2.setSpellbuyPrice(productCart.getProductPrice());
        spellbuyproduct2.setSpellbuyStartDate(DateUtil.DateTimeToStr(new Date()));
        spellbuyproduct2.setSpStatus(0);
        spellbuyproductService.add(spellbuyproduct2);

        product = productService.findById(String.valueOf(spellbuyproduct.getFkProductId()));
        int Period = Integer.parseInt(product.getAttribute71());
        ++Period;
        product.setAttribute71(String.valueOf(Period));
        product.setStatus(1);
        productService.add(product);

        spellbuyproduct.setSpStatus(1);
        spellbuyproductService.add(spellbuyproduct);
    }

}
