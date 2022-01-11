package com.eypg.service;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Consumetable;

public interface ConsumetableService extends TService<Consumetable, Integer> {

    public Consumetable findByOutTradeNo(String id);

    /**
     * 冲值记录
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination userByConsumetableByDelta(String userId, String startDate, String endDate, int pageNo, int pageSize);

    /**
     * 冲值记录ByCount
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Integer userByConsumetableByDeltaByCount(String userId, String startDate, String endDate);


    /**
     * 消费记录
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination userByConsumetable(String userId, String startDate, String endDate, int pageNo, int pageSize);

    /**
     * 消费记录ByCount
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Integer userByConsumetableByCount(String userId, String startDate, String endDate);

    /**
     * 订单列表
     *
     * @param userKey
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination orderList(String userKey, int pageNo, int pageSize);

}
