package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.dao.Pagination;
import com.eypg.pojo.Applymention;
import com.eypg.service.ApplymentionService;

@Service
public class ApplymentionServiceImpl implements ApplymentionService {
    @Autowired
    BaseDAO baseDao;

    public void add(Applymention t) {
        this.baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    public Applymention findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Applymention> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public Pagination getApplymentionList(String userId, String startDate, String endDate, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from applymention an where 1=1");
        if (StringUtils.isNotBlank(userId)) {
            hql.append(" and  an.userId = '" + userId + "'");
        }
        if (StringUtils.isNotBlank(startDate)) {
            hql.append(" and an.date >='" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            hql.append(" and an.date <='" + endDate + "'");
        }
        hql.append(" order by an.date desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("an", Applymention.class);
        List list = baseDao.sqlQueryBean(hql, entityMap, page);
        page.setList(list);
        return page;
    }

    public Integer getApplymentionListByCount(String userId, String startDate,
                                              String endDate) {
        StringBuffer sql = new StringBuffer("select count(*) from applymention an where 1=1");
        if (StringUtils.isNotBlank(userId)) {
            sql.append(" and  an.userId = '" + userId + "'");
        }
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and an.date >='" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and an.date <='" + endDate + "'");
        }
        return baseDao.getAllNum(sql);
    }

}
