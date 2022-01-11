package com.eypg.service;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Consumerdetail;

public interface ConsumerdetailService extends TService<Consumerdetail, Integer> {

    /**
     * 消费记录-查看详情
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination userByConsumetableDetail(String id, int pageNo, int pageSize);

    /**
     * 消费记录-查看详情ByCount
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Integer userByConsumetableDetailByCount(String id);

    /**
     * 订单详情
     *
     * @param userKey
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination orderInfo(String id, int pageNo, int pageSize);

}
