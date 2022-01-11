package com.eypg.service;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Cardpassword;

public interface CardpasswordService extends TService<Cardpassword, Integer> {

    /**
     * 卡密充值
     *
     * @param id
     * @return
     */
    public Cardpassword doCardRecharge(String randomNo, String cardPwd);

    public Pagination cardRechargeList(int pageNo, int pageSize);

    public void deleteByID(Integer id);

}
