package com.eypg.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eypg.dao.Pagination;
import com.eypg.pojo.DetailBybuyerJSON;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.RandomnumberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext*.xml"})
@Repository
public class UpdateRandom {

    @Autowired
    @Qualifier("latestlotteryService")
    private LatestlotteryService latestlotteryService;
    @Autowired
    private RandomnumberService randomnumberService;

    @Test
    public void go() {

        for (int i = 0; i < 20; i++) {
            Pagination page = latestlotteryService.LatestAnnounced(i, 20);
            List<Latestlottery> objList = (List<Latestlottery>) page.getList();
            System.err.println(objList.size() + "********");

            for (Latestlottery latestlottery : objList) {

                for (int j = 0; j < 100; j++) {
                    System.err.println(latestlottery.getSpellbuyProductId() + "&&&&&&&&&&&&&&&&");
                    Pagination datePage = latestlotteryService.getLotteryDetailBybuyerList(latestlottery.getSpellbuyProductId(), j, 20);
                    List<Object[]> dataList = (List<Object[]>) datePage.getList();
                    if (dataList.size() == 0) {
                        break;
                    }
                    System.err.println(dataList.size() + "%%%%%%%%%%%%%%%");
                    for (int k = 0; k < dataList.size(); k++) {
                        Spellbuyrecord spellbuyrecord = (Spellbuyrecord) dataList.get(k)[0];
                        Randomnumber randomnumber = (Randomnumber) dataList.get(k)[1];
                        User user = (User) dataList.get(k)[2];
                        System.err.println(randomnumber.getId() + "++++++++++++++++++++");
                        System.err.println(spellbuyrecord.getFkSpellbuyProductId() + "==================");
                        randomnumber.setProductId(spellbuyrecord.getFkSpellbuyProductId());
                        randomnumberService.add(randomnumber);
                    }
                }


            }

        }


    }

}
