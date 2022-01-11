package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.dao.Pagination;
import com.eypg.pojo.Consumerdetail;
import com.eypg.service.ConsumerdetailService;

@Service("consumerdetailService")
public class ConsumerdetailServiceImpl implements ConsumerdetailService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void add(Consumerdetail t) {
        baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        baseDao.delById(Consumerdetail.class, id);
    }

    public Consumerdetail findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Consumerdetail> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public Pagination userByConsumetableDetail(String id, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from consumerdetail cl where cl.consumetableId = '" + id + "'");
//		StringBuffer sql = new StringBuffer("select count(*) from consumerdetail cl where cl.consumetableId = '"+id+"'");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("cl", Consumerdetail.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(resultCount);
        return page;
    }

    public Integer userByConsumetableDetailByCount(String id) {
        StringBuffer sql = new StringBuffer("select count(*) from consumerdetail cl where cl.consumetableId = '" + id + "'");
        return baseDao.getAllNum(sql);
    }

    public Pagination orderInfo(String id, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from consumerdetail cl where cl.consumetableId = '" + id + "'");
        StringBuffer sql = new StringBuffer("select count(*) from consumerdetail where consumetableId = '" + id + "'");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("cl", Consumerdetail.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

}
