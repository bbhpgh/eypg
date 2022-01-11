package com.eypg.service;

import java.util.List;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Commissionquery;

public interface CommissionqueryService extends TService<Commissionquery, Integer> {

    /**
     * 佣金明细
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination getCommissionQueryList(String userId, String startDate, String endDate, int pageNo, int pageSize);

    /**
     * 佣金明细 ByCount
     *
     * @param userId
     * @return
     */
    public Integer getCommissionQueryListByCount(String userId, String startDate, String endDate);

    /**
     * 佣金明细 ByData
     *
     * @param userId
     * @return
     */
    public List getCommissionQueryListByData(String userId);

}
