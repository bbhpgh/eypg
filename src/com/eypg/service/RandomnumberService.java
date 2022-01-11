package com.eypg.service;

import java.math.BigDecimal;
import java.util.List;

import com.eypg.pojo.Randomnumber;

public interface RandomnumberService extends TService<Randomnumber, Integer> {

    public List LotteryDetailByRandomnumber(Integer userId, Integer spellbuyProductId);

    /**
     * 查询某商品某用户总共总买人次
     *
     * @param userId
     * @param spellbuyProductId
     * @return
     */
    public BigDecimal RandomNumberByUserBuyCount(String userId, Integer spellbuyProductId);

    public Randomnumber findRandomnumberByspellbuyrecordId(Integer spellbuyrecordId);

}
