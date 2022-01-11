package com.eypg.util;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.RandomnumberService;
import com.eypg.service.SpellbuyrecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext*.xml"})
@Repository
public class UpdateDateTimeUtil {

    @Autowired
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    private RandomnumberService randomnumberService;
    @Autowired
    private LatestlotteryService latestlotteryService;

    Randomnumber randomnumber;
    Latestlottery latestlottery;

    @Test
    public void go() {
        Random random = new Random();
        System.err.println("start");
        try {
            List<Spellbuyrecord> spellbuyrecordList = spellbuyrecordService.query("from Spellbuyrecord spellbuyrecord where spellbuyrecord.buyDate not like '%.%'");
            System.err.println("spellbuyrecordList size:" + spellbuyrecordList.size());
            for (Spellbuyrecord spellbuyrecord : spellbuyrecordList) {
                try {
                    String date = spellbuyrecord.getBuyDate();
                    String buyDate = "";
                    String sss = "";
                    Integer rnd = random.nextInt(1000);
                    if (rnd < 10) {
                        sss = "00" + rnd;
                    } else if (rnd < 100) {
                        sss = "0" + rnd;
                    } else {
                        sss = rnd.toString();
                    }
                    buyDate = date + "." + sss;

                    spellbuyrecord.setBuyDate(buyDate);
                    spellbuyrecordService.add(spellbuyrecord);
                    randomnumber = randomnumberService.findRandomnumberByspellbuyrecordId(spellbuyrecord.getSpellbuyRecordId());
                    randomnumber.setBuyDate(buyDate);
                    randomnumberService.add(randomnumber);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            List<Spellbuyrecord> spellbuyrecordList2 = spellbuyrecordService.query("from Spellbuyrecord spellbuyrecord where 1=1");
            for (Spellbuyrecord spellbuyrecord2 : spellbuyrecordList2) {
                try {
                    if (spellbuyrecord2.getBuyStatus().equals("1")) {
                        System.err.println("spellbuyrecord2:" + spellbuyrecord2.getSpellbuyRecordId());
                        Spellbuyrecord spellbuyrecord3 = (Spellbuyrecord) spellbuyrecordService.getEndBuyDateByProduct(spellbuyrecord2.getFkSpellbuyProductId()).get(0);
                        latestlottery = latestlotteryService.findLatestlotteryByspellbuyrecordId(spellbuyrecord2.getSpellbuyRecordId());
                        latestlottery.setBuyTime(spellbuyrecord2.getBuyDate());
                        latestlottery.setAnnouncedTime(spellbuyrecord3.getBuyDate());
                        latestlotteryService.add(latestlottery);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.err.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            String date = "2013-07-01 11:42:56";
            String sss = "";
            Integer rnd = random.nextInt(1000);
            if (rnd < 10) {
                sss = "00" + rnd;
            } else if (rnd < 100) {
                sss = "0" + rnd;
            } else {
                sss = rnd.toString();
            }
            date += "." + sss;
            System.err.println(date);

        }
    }


}
