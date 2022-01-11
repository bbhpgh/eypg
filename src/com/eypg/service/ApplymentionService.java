package com.eypg.service;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Applymention;

public interface ApplymentionService extends TService<Applymention, Integer> {


    /**
     * 提现记录
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination getApplymentionList(String userId, String startDate, String endDate, int pageNo, int pageSize);

    /**
     * 提现记录 ByCount
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    public Integer getApplymentionListByCount(String userId, String startDate, String endDate);

}
