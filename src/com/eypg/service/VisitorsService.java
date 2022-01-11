package com.eypg.service;

import java.util.List;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Visitors;

public interface VisitorsService extends TService<Visitors, Integer> {

    /**
     * 访客列表
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination getVisitors(String userId, int pageNo, int pageSize);

    /**
     * 查询某用户是否有访问记录
     *
     * @param userId
     * @param visitorsId
     * @return
     */
    public Visitors findVisitorsByUserIdAndVisitorsId(String userId, String visitorsId);

    /**
     * 查询一小时内是否访问过
     *
     * @return
     */
    public List findVisitors(String userId, String visitorsId, String startDate, String endDate);

}
