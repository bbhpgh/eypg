package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.dao.Pagination;
import com.eypg.pojo.Friends;
import com.eypg.pojo.News;
import com.eypg.pojo.User;
import com.eypg.pojo.Visitors;
import com.eypg.service.VisitorsService;

@Service
public class VisitorsServiceImpl implements VisitorsService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDAO;

    public void add(Visitors t) {
        baseDAO.saveOrUpdate(t);

    }

    public void delete(String id) {
        baseDAO.delById(Visitors.class, id);

    }

    public Visitors findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Visitors> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public Pagination getVisitors(String userId, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from visitors vs,user ur where vs.visitorsId = ur.userId and vs.uid = '" + userId + "' order by date desc");
//		StringBuffer sql = new StringBuffer("select count(*) from visitors vs,user ur where vs.visitorsId = ur.userId and vs.userId = '"+userId+"' order by date desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("vs", Visitors.class);
        entityMap.put("ur", User.class);
        List list = baseDAO.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(resultCount);
        return page;
    }

    public List findVisitors(String userId, String visitorsId, String startDate, String endDate) {
        StringBuffer sql = new StringBuffer("select * from visitors vs where vs.uid = '" + userId + "' and vs.visitorsId = '" + visitorsId + "' and vs.date >= '" + startDate + "' and vs.date <= '" + endDate + "'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("vs", Visitors.class);
        List list = baseDAO.sqlQueryEntity(sql, entityMap);
        return list;
    }

    public Visitors findVisitorsByUserIdAndVisitorsId(String userId,
                                                      String visitorsId) {
        StringBuffer hql = new StringBuffer("from Visitors vs  where vs.uid = '" + userId + "'  and vs.visitorsId = '" + visitorsId + "'");
        return (Visitors) baseDAO.loadObject(hql.toString());
    }

}
