package com.eypg.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
import com.eypg.util.MD5Util;
import com.eypg.util.MemCachedClientHelp;
import com.eypg.util.NewLotteryUtil;
import com.eypg.util.RandomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext*.xml"})
@Repository
public class VirtualUserBuy {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

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
    @Autowired
    private NewLotteryUtil newLotteryUtil;

    private ProductCart productCart;
    private Product product;
    private Spellbuyproduct spellbuyproduct;
    private Spellbuyrecord spellbuyrecord;
    private Randomnumber randomnumber;
    private Latestlottery latestlottery;
    Calendar calendar = Calendar.getInstance();
    static List<User> userList = null;

    @Test
    public void goBuy() {
        Random random = new Random();
        if (random.nextInt(15) != 5) {
            return;
        }
        boolean flag = true;
        List<Spellbuyproduct> spellbuyproductList = spellbuyproductService.loadAllByType();
        for (int i = 0; i < 2; i++) {
            Collections.shuffle(spellbuyproductList);
        }
        if (userList == null) {
            userList = userService.loadAll();
        }
        for (int j = 0; j < 1; j++) {
            if (spellbuyproductList.get(j).getSpStatus() != 1) {
                Integer productId = spellbuyproductList.get(j).getSpellbuyProductId();
                int BuyCount = 1;
                Collections.shuffle(userList);
                for (int i = 1; i <= BuyCount; i++) {
                    try {
                        User user = userList.get(random.nextInt(userList.size()));
                        int[] buyNumbers = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 5, 1, 2, 4, 3, 5, 5, 1, 3, 2, 4, 5, 1, 1, 2, 2, 2, 8, 8, 6, 7, 10, 1,
                                2, 10, 2, 1, 5, 10, 2, 1, 20, 5, 20, 1, 2, 1, 15, 2, 1, 10, 1, 2, 15, 5, 1, 2, 16, 1, 6, 8, 4, 1, 2, 1, 2, 1, 2, 30, 2, 1,
                                30, 1, 2, 50, 1, 2, 1, 50, 1, 2, 50, 1, 2, 5, 1, 2, 1, 5, 2, 1, 100, 2, 22, 1, 11, 2, 5, 1, 2, 2, 1, 2, 3, 1, 4, 40, 5, 1,
                                2, 2, 4, 5, 6, 1, 1, 2, 1, 2, 2, 4, 3, 5, 6, 1, 1, 2, 2, 25, 6, 2, 5, 3, 2, 1, 2, 4, 7, 8, 1, 2, 3, 1, 2, 55, 1, 2, 1, 2, 3, 2,
                                1, 2, 1, 23, 2, 3, 7, 8, 7, 3, 3, 4, 1, 2, 2, 3, 1, 2, 2, 2, 2, 3, 2, 1, 2, 5, 1, 2, 500, 2, 3, 3, 2, 1, 1, 2, 3, 3, 2, 1, 2, 5, 6, 7,
                                2, 20, 3, 1, 2, 3, 1, 2, 2, 100, 1, 2, 6, 23, 8, 25, 1, 25, 6, 1, 2, 3, 2, 1, 1, 23, 3, 4, 1, 2, 2, 24, 4, 2, 1, 21, 2, 12, 10,
                                1, 2, 2, 1, 2, 3, 5, 6, 10, 1, 1, 2, 2, 1, 1, 2, 2, 20, 10, 5, 3, 1, 5, 1, 12, 6, 4, 5, 6, 1, 2, 3, 6, 10, 8, 1, 23, 4, 7, 2, 2};
                        int buyCount = (buyNumbers[random.nextInt(buyNumbers.length)]);
                        productCart = new ProductCart();
                        List<Object[]> proList = spellbuyproductService.findByProductId(productId);
                        product = (Product) proList.get(0)[0];
                        spellbuyproduct = (Spellbuyproduct) proList.get(0)[1];
                        if ((int) productId == (int) spellbuyproduct.getSpellbuyProductId()) {
                            spellbuyrecord = new Spellbuyrecord();
                            productCart.setCount(buyCount);
                            productCart.setHeadImage(product.getHeadImage());
                            productCart.setProductId(spellbuyproduct.getSpellbuyProductId());
                            productCart.setProductName(product.getProductName());
                            productCart.setProductPrice(product.getProductPrice());
                            productCart.setProductTitle(product.getProductTitle());
                            productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                            productCart.setProductPeriod(spellbuyproduct.getProductPeriod());

                            try {
                                Integer count = 0;
                                //当前拍购人数
                                Integer CurrentPrice = spellbuyproduct.getSpellbuyCount();
                                if ((spellbuyproduct.getSpellbuyCount() + buyCount) > product.getProductPrice()) {
                                    count = productCart.getProductPrice() - spellbuyproduct.getSpellbuyCount();
                                } else {
                                    count = buyCount;
                                }
                                if (count > 0) {
                                    spellbuyproduct.setSpellbuyCount(spellbuyproduct.getSpellbuyCount() + count);
                                    if (spellbuyproduct.getSpellbuyCount() >= product.getProductPrice()) {
                                        spellbuyproduct.setSpellbuyCount(product.getProductPrice());
                                        spellbuyproduct.setSpStatus(2);
                                        spellbuyproduct.setSpellbuyEndDate(DateUtil.DateTimeToStr(DateUtil.subMinute(new Date(), -3)));
                                    }
                                    spellbuyproductService.add(spellbuyproduct);

//									Date randomDate = DateUtil.subMinute(new Date(), random.nextInt(5));
                                    String dates = sdf.format(new Date());
                                    int s = random.nextInt(60);
                                    String ss = "";
                                    if (s < 10) {
                                        ss = "0" + String.valueOf(s);
                                    } else {
                                        ss = String.valueOf(s);
                                    }

                                    dates += ":" + ss;

                                    String sss = "";
                                    Integer rnd = random.nextInt(1000);
                                    if (rnd < 10) {
                                        sss = "00" + rnd;
                                    } else if (rnd < 100) {
                                        sss = "0" + rnd;
                                    } else {
                                        sss = rnd.toString();
                                    }
                                    dates += "." + sss;


                                    spellbuyrecord.setFkSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
                                    spellbuyrecord.setBuyer(user.getUserId());
                                    spellbuyrecord.setBuyPrice(count);
                                    spellbuyrecord.setBuyDate(dates);
                                    spellbuyrecord.setSpWinningStatus("0");
                                    spellbuyrecord.setBuyStatus("0");
                                    spellbuyrecord.setSpRandomNo("");
                                    spellbuyrecordService.add(spellbuyrecord);

                                    randomnumber = new Randomnumber();
                                    randomnumber.setProductId(productCart.getProductId());

                                    List<Randomnumber> RandomnumberList = randomnumberService.query(" from Randomnumber where productId='" + spellbuyproduct.getSpellbuyProductId()
                                            + "'");
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
                                    randomnumber.setUserId(user.getUserId());
                                    randomnumberService.add(randomnumber);

                                    Integer experience = user.getExperience();
                                    experience += (count * 10);
                                    user.setExperience(experience);
                                    userService.add(user);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                flag = false;
                            }
                        }
                        if (flag) {
                            if (spellbuyproduct.getSpStatus() == 2) {
                                String lotteryId = MD5Util.encode(String.valueOf(spellbuyproduct.getSpellbuyProductId()));
                                if (MemCachedClientHelp.getIMemcachedCache().get(lotteryId) == null) {
                                    MemCachedClientHelp.getIMemcachedCache().put(lotteryId, "y", new Date(12 * 60 * 60 * 1000));
                                    new Thread() {
                                        public void run() {
                                            try {
                                                newLotteryUtil.lottery(productCart);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }.start();
                                }
                            }
                        }
                        if (!flag)
                            break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Random random = new Random();
//		int s = random.nextInt(60);
//		String ss = "";
//		if(s<10){
//			ss = "0"+String.valueOf(s);
//		}else{
//			ss = String.valueOf(s);
//		}
//		Date randomDate = DateUtil.subMinute(new Date(), random.nextInt(10));
//		System.err.println(randomDate);
//		String dates = sdf.format(randomDate)+":"+ss;
//		System.err.println(dates);

        int[] buyNumbers = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 5, 1, 2, 4, 3, 5, 5, 1, 3, 2, 4, 5, 1, 1, 2, 2, 2, 8, 8, 6, 7, 10, 1,
                2, 10, 2, 1, 5, 10, 2, 1, 20, 5, 20, 1, 2, 1, 15, 2, 1, 10, 1, 2, 15, 5, 1, 2, 16, 1, 6, 8, 4, 1, 2, 1, 2, 1, 2, 30, 2, 1,
                30, 1, 2, 50, 1, 2, 1, 50, 1, 2, 50, 1, 2, 5, 1, 2, 1, 5, 2, 1, 100, 2, 22, 1, 11, 2, 5, 1, 2, 2, 1, 2, 3, 1, 4, 40, 5, 1,
                2, 2, 4, 5, 6, 1, 1, 2, 1, 2, 2, 4, 3, 5, 6, 1, 1, 2, 2, 25, 6, 2, 5, 3, 2, 1, 2, 4, 7, 8, 1, 2, 3, 1, 2, 55, 1, 2, 1, 2, 3, 2,
                1, 2, 1, 23, 2, 3, 7, 8, 7, 3, 3, 4, 1, 2, 2, 3, 1, 2, 2, 2, 2, 3, 2, 1, 2, 5, 1, 2, 2, 3, 3, 2, 1, 1, 2, 3, 3, 2, 1, 2, 5, 6, 7,
                2, 20, 3, 1, 2, 3, 1, 2, 2, 1, 2, 6, 23, 8, 25, 1, 25, 6, 1, 2, 3, 2, 1, 1, 23, 3, 4, 1, 2, 2, 24, 4, 2, 1, 21, 2, 12, 10,
                1, 2, 2, 1, 2, 3, 5, 6, 10, 1, 1, 2, 2, 1, 1, 2, 2, 20, 10, 5, 3, 1, 5, 1, 12, 6, 4, 5, 6, 1, 2, 3, 6, 10, 8, 1, 23, 4, 7, 2, 2};
        for (int i = 0; i < 100; i++) {
            System.err.println(buyNumbers[random.nextInt(buyNumbers.length)]);
        }
    }

}
