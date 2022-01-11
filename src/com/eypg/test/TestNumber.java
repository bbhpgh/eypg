package com.eypg.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.RandomNumberJSON;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.RandomnumberService;
import com.eypg.service.SpellbuyproductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext*.xml"})
@Repository
@Service("TestNumber")
public class TestNumber {

    @Autowired
    private LatestlotteryService latestlotteryService;
    @Autowired
    private RandomnumberService randomnumberService;
    @Autowired
    private SpellbuyproductService spellbuyproductService;

    private List<Randomnumber> randomnumberList;
    private List<Latestlottery> latestlotteryList;


    @Test
    public void go() {
        List<Spellbuyproduct> spellbuyproductList = spellbuyproductService.query("from Spellbuyproduct s where s.spStatus=0");
        for (Spellbuyproduct spellbuyproduct : spellbuyproductList) {
            int price = spellbuyproduct.getSpellbuyPrice();
            randomnumberList = randomnumberService.query("from Randomnumber r where r.productId='" + spellbuyproduct.getSpellbuyProductId() + "'");
            for (Randomnumber randomnumber : randomnumberList) {
                String[] randoms = randomnumber.getRandomNumber().split(",");
                String numbers = "";
                int count = 0;
                for (String string : randoms) {
                    int num = Integer.parseInt(string);
                    num -= 10000000;
                    if (num > price) {
                        if (num > 1000) {
                            num -= 1000;
                        } else if (num > 100) {
                            num -= 100;
                        }
                    }
                    num += 10000000;
//					System.err.println("num:"+num);
                    numbers += num + ",";
                }
                numbers = numbers.substring(0, numbers.length() - 1);
                System.err.println("numbers:" + numbers);
                randomnumber.setRandomNumber(numbers);
                randomnumberService.add(randomnumber);
            }
        }
    }

//	@Test
//	public void go(){
//		latestlotteryList = latestlotteryService.query("from Latestlottery l where 1=1");
//		for (Latestlottery latestlottery : latestlotteryList) {
////			Latestlottery latestlottery = (Latestlottery) latestlotteryService.getLotteryDetail(784).get(0);
//			int price = latestlottery.getProductPrice();
//			randomnumberList = randomnumberService.LotteryDetailByRandomnumber(latestlottery.getUserId(), latestlottery.getSpellbuyProductId());
//			for (Randomnumber randomnumber : randomnumberList) {
//				String [] randoms = randomnumber.getRandomNumber().split(",");
//				String numbers = "";
//				int count=0;
//				for (String string : randoms) {
//					int num = Integer.parseInt(string);
//					num -= 10000000;
//					if(num >price){
//						if(num>1000){
//							num-=1000;
//						}else if(num>100){
//							num-=100;
//						}
//					}
//					num+=10000000;
////					System.err.println("num:"+num);
//					numbers +=num+",";
//				}
//				numbers = numbers.substring(0,numbers.length()-1);
//				System.err.println("numbers:"+numbers);
//				randomnumber.setRandomNumber(numbers);
//				randomnumberService.add(randomnumber);
//			}
//		}
//	}

}
