package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.dao.Pagination;
import com.eypg.pojo.Commissionpoints;
import com.eypg.pojo.Commissionquery;
import com.eypg.pojo.User;
import com.eypg.service.CommissionpointsService;

@Service
public class CommissionpointsServiceImpl implements CommissionpointsService {
    @Autowired
    BaseDAO baseDao;

    public Pagination CommissionPoints(String userId, String startDate,
                                       String endDate, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from commissionpoints cs where cs.toUserid = '" + userId + "'");
        if (StringUtils.isNotBlank(startDate)) {
            hql.append(" and cs.date >='" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            hql.append(" and cs.date <='" + endDate + "'");
        }
        hql.append(" order by cs.date desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("cs", Commissionpoints.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        page.setList(list);
        return page;
    }

    public void add(Commissionpoints t) {
        this.baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    public Commissionpoints findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Commissionpoints> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public Integer getCommissionPointsListByCount(String userId,
                                                  String startDate, String endDate) {
        StringBuffer sql = new StringBuffer("select count(*) from commissionpoints cs where cs.toUserid = '" + userId + "'");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and cs.date >='" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and cs.date <='" + endDate + "'");
        }
        return baseDao.getAllNum(sql);
    }

}
