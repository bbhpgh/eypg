package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.dao.Pagination;
import com.eypg.pojo.Commissionquery;
import com.eypg.pojo.User;
import com.eypg.service.CommissionqueryService;

@Service
public class CommissionqueryServiceImpl implements CommissionqueryService {
    @Autowired
    BaseDAO baseDao;

    public Pagination getCommissionQueryList(String userId, String startDate, String endDate, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from commissionquery cy,user ur where cy.toUserId=ur.userId and cy.invitedId = '" + userId + "'");
        if (StringUtils.isNotBlank(startDate)) {
            hql.append(" and cy.date >='" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            hql.append(" and cy.date <='" + endDate + "'");
        }
        hql.append(" order by cy.date desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("cy", Commissionquery.class);
        entityMap.put("ur", User.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        page.setList(list);
        return page;
    }

    public Integer getCommissionQueryListByCount(String userId, String startDate, String endDate) {
        StringBuffer sql = new StringBuffer("select count(*) from commissionquery cy,user ur where cy.toUserId=ur.userId and cy.invitedId = '" + userId + "'");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and cy.date >='" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and cy.date <='" + endDate + "'");
        }
        return baseDao.getAllNum(sql);
    }

    public List getCommissionQueryListByData(String userId) {
        StringBuffer hql = new StringBuffer("select * from commissionquery cy where cy.invitedId = '" + userId + "'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("cy", Commissionquery.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public void add(Commissionquery t) {
        this.baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    public Commissionquery findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Commissionquery> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

}
