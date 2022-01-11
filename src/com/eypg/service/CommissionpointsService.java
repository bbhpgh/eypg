package com.eypg.service;

import java.util.List;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Commissionpoints;
import com.eypg.pojo.Commissionquery;

public interface CommissionpointsService extends TService<Commissionpoints, Integer> {

    /**
     * 福分明细
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination CommissionPoints(String userId, String startDate, String endDate, int pageNo, int pageSize);

    /**
     * 佣金明细 ByCount
     *
     * @param userId
     * @return
     */
    public Integer getCommissionPointsListByCount(String userId, String startDate, String endDate);


}
