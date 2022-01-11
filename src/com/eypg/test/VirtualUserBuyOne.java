package com.eypg.test;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
import com.eypg.service.UserService;
import com.eypg.util.DateUtil;
import com.eypg.util.RandomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext*.xml"})
@Repository
public class VirtualUserBuyOne {
    @Autowired
    private UserService userService;
    RandomUtil randomUtil = new RandomUtil();
    @Autowired
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    private LatestlotteryService latestlotteryService;
    @Autowired
    private RandomnumberService randomnumberService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SpellbuyproductService spellbuyproductService;
    private ProductCart productCart;
    private Product product;
    private Spellbuyproduct spellbuyproduct;
    private Spellbuyrecord spellbuyrecord;
    private Randomnumber randomnumber;
    private Latestlottery latestlottery;
    private User user;

    Calendar calendar = Calendar.getInstance();

    @Test
    public void goBuy() {

        productCart = new ProductCart();
        List<Object[]> proList = spellbuyproductService.findByProductId(11582);
        product = (Product) proList.get(0)[0];
        spellbuyproduct = (Spellbuyproduct) proList.get(0)[1];
        productCart.setHeadImage(product.getHeadImage());
        productCart.setProductId(spellbuyproduct.getSpellbuyProductId());
        productCart.setProductName(product.getProductName());
        productCart.setProductPrice(product.getProductPrice());
        productCart.setProductTitle(product.getProductTitle());
        productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productCart.setProductPeriod(spellbuyproduct.getProductPeriod());


        if (spellbuyproduct.getSpStatus() == 1) {
            /**
             * 某商品结束日期全站100条购买记录
             */

            /**
             * 得到该商品最后购买记录
             */
            spellbuyrecord = (Spellbuyrecord) spellbuyrecordService.getEndBuyDateByProduct(productCart.getProductId()).get(0);

            Pagination page = spellbuyrecordService.getlotteryDetail(null, spellbuyrecord.getBuyDate(), 0, 100);
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

            System.err.println(DateSUM + "************************");
            System.err.println((DateSUM % productCart.getProductPrice()) + "---------------");
            System.err.println(String.valueOf(((DateSUM % productCart.getProductPrice()) + 10000001)) + "&&&&&&&&&&&&&&&&&&&&&&&");
            /**
             * 计算出中奖码
             */
            Integer winNmuber = Integer.parseInt(String.valueOf(((DateSUM % productCart.getProductPrice()) + 10000001)));


            System.err.println("winNmuber+++" + winNmuber);

            List<Object[]> objList = spellbuyrecordService.randomByBuyHistoryByspellbuyProductId(productCart.getProductId(), String.valueOf(winNmuber));
            Randomnumber randomnumber = (Randomnumber) objList.get(0)[0];
            Spellbuyrecord spellbuyrecord = (Spellbuyrecord) objList.get(0)[1];
            User user2 = (User) objList.get(0)[2];

            spellbuyproduct = spellbuyproductService.findById(productCart.getProductId().toString());
//			
            latestlottery = new Latestlottery();
            latestlottery.setProductId(spellbuyproduct.getFkProductId());
            latestlottery.setProductName(productCart.getProductName());
            latestlottery.setProductTitle(productCart.getProductTitle());
            latestlottery.setProductPrice(productCart.getProductPrice());
            latestlottery.setProductImg(productCart.getHeadImage());
            latestlottery.setProductPeriod(productCart.getProductPeriod());
            latestlottery.setAnnouncedTime(DateUtil.DateTimeToStr(new Date())); //揭晓时间
            latestlottery.setBuyTime(spellbuyrecord.getBuyDate()); //购买时间
            latestlottery.setSpellbuyRecordId(spellbuyrecord.getSpellbuyRecordId()); //购买记录ID
            latestlottery.setSpellbuyProductId(spellbuyrecord.getFkSpellbuyProductId()); //某期商品ID
            latestlottery.setBuyNumberCount(spellbuyrecord.getBuyPrice()); //购买总数
            latestlottery.setRandomNumber(winNmuber); //中奖码
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

            spellbuyrecord.setSpRandomNo(String.valueOf(winNmuber));
            spellbuyrecord.setSpWinningStatus("1");
            spellbuyrecord.setBuyStatus("1");
            spellbuyrecordService.add(spellbuyrecord);

            int productPeriod = productCart.getProductPeriod();
            spellbuyproduct = new Spellbuyproduct();
            spellbuyproduct.setFkProductId(spellbuyproduct.getFkProductId());
            spellbuyproduct.setProductPeriod(productPeriod);
            spellbuyproduct.setSpellbuyCount(0);
            spellbuyproduct.setSpellbuyEndDate(DateUtil.DateTimeToStr(new Date()));
            spellbuyproduct.setSpellbuyPrice(productCart.getProductPrice());
            spellbuyproduct.setSpellbuyStartDate(DateUtil.DateTimeToStr(new Date()));
            spellbuyproduct.setSpStatus(0);
//			spellbuyproductService.add(spellbuyproduct);

            product = productService.findById(String.valueOf(spellbuyproduct.getFkProductId()));
            product.setAttribute71(String.valueOf((productPeriod + 1)));
            product.setStatus(1);
//			productService.add(product);
        }

    }

//	@Test
//	public void test(){
//		Random random = new Random();
//		List<Object []> objList = spellbuyrecordService.randomByBuyHistoryByspellbuyProductId(10004);
//		int number = random.nextInt(objList.size());
//		System.err.println(number);
//		Randomnumber randomnumber = (Randomnumber) objList.get(number)[0];
//		Spellbuyrecord spellbuyrecord = (Spellbuyrecord) objList.get(number)[1];
//		User user2 = (User) objList.get(number)[2];
//		
//		String num [] = randomnumber.getRandomNumber().split(",");
//		
//		System.err.println(randomnumber.getRandomNumber());
//		System.err.println(num[random.nextInt(num.length)]);
//		System.err.println(spellbuyrecord.getBuyer() +" | "+spellbuyrecord.getBuyDate());
//		System.err.println(user2.getUserId() + " | " +user2.getNewDate());
//	}
//	
}
