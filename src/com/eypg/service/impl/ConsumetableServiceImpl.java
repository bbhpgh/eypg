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
import com.eypg.pojo.Consumetable;
import com.eypg.pojo.Product;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.service.ConsumetableService;

@Service("consumetableService")
public class ConsumetableServiceImpl implements ConsumetableService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void add(Consumetable t) {
        baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        baseDao.delById(Consumetable.class, id);
    }

    public Consumetable findById(String id) {
        StringBuffer hql = new StringBuffer("from Consumetable consumetable where 1=1");
        if (StringUtils.isNotBlank(id)) {
            hql.append(" and consumetable.id='" + id + "'");
        }
        return (Consumetable) baseDao.loadObject(hql.toString());
    }

    public Consumetable findByOutTradeNo(String id) {
        StringBuffer hql = new StringBuffer("from Consumetable consumetable where 1=1");
        if (StringUtils.isNotBlank(id)) {
            hql.append(" and consumetable.outTradeNo='" + id + "'");
        }
        return (Consumetable) baseDao.loadObject(hql.toString());
    }

    public List<Consumetable> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public Pagination userByConsumetableByDelta(String userId, String startDate,
                                                String endDate, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from consumetable ce where 1=1");
//		StringBuffer sql = new StringBuffer("select count(*) from consumetable ce where ce.userId = '"+userId+"' and ce.transactionId is not null");
        if (StringUtils.isNotBlank(userId)) {
            hql.append(" and ce.userId = '" + userId + "'");
        }
        if (StringUtils.isNotBlank(startDate)) {
            hql.append(" and ce.date >= '" + startDate + "'");
//			sql.append(" and ce.date >='"+startDate+"'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            hql.append(" and ce.date <='" + endDate + "'");
//			sql.append(" and ce.date <='"+endDate+"'");
        }
        hql.append(" and ce.transactionId is not null order by ce.date desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ce", Consumetable.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(resultCount);
        return page;
    }

    public Integer userByConsumetableByDeltaByCount(String userId, String startDate, String endDate) {
        StringBuffer sql = new StringBuffer("select count(*) from consumetable ce where 1=1 ");
        if (StringUtils.isNotBlank(userId)) {
            sql.append(" and ce.userId = '" + userId + "'");
        }
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and ce.date >='" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and ce.date <='" + endDate + "'");
        }
        sql.append(" and ce.transactionId is not null");
        return baseDao.getAllNum(sql);
    }

    public Pagination userByConsumetable(String userId, String startDate,
                                         String endDate, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from consumetable ce where 1=1 ");
//		StringBuffer sql = new StringBuffer("select count(*) from consumetable ce where ce.userId = '"+userId+"' and ((ce.interfaceType = 'tenPay' and ce.transactionId is not null) or ce.interfaceType = '余额支付')");
        if (StringUtils.isNotBlank(userId)) {
            hql.append(" and ce.userId = '" + userId + "'");
        }
        if (StringUtils.isNotBlank(startDate)) {
            hql.append(" and ce.date >= '" + startDate + "'");
//			sql.append(" and ce.date >='"+startDate+"'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            hql.append(" and ce.date <='" + endDate + "'");
//			sql.append(" and ce.date <='"+endDate+"'");
        }
        hql.append(" and ((ce.interfaceType = 'tenPay' and ce.transactionId is not null) or (ce.interfaceType = 'aliPay' and ce.transactionId is not null) or (ce.interfaceType = 'yeePay' and ce.transactionId is not null) or ce.interfaceType = '余额支付')  and ce.outTradeNo in(select consumetableId from consumerdetail) order by ce.date desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ce", Consumetable.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(resultCount);
        return page;
    }

    public Integer userByConsumetableByCount(String userId, String startDate, String endDate) {
        StringBuffer sql = new StringBuffer("select count(*) from consumetable ce where 1=1");
        if (StringUtils.isNotBlank(userId)) {
            sql.append(" and ce.userId = '" + userId + "'");
        }
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and ce.date >='" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and ce.date <='" + endDate + "'");
        }
        sql.append("  and ((ce.interfaceType = 'tenPay' and ce.transactionId is not null) or (ce.interfaceType = 'aliPay' and ce.transactionId is not null) or (ce.interfaceType = 'yeePay' and ce.transactionId is not null) or ce.interfaceType = '余额支付')  and ce.outTradeNo in(select consumetableId from consumerdetail)");
        return baseDao.getAllNum(sql);
    }

    public Pagination orderList(String userKey, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from consumetable ce  where 1=1 and ((ce.interfaceType = 'tenPay' and ce.transactionId is not null) or (ce.interfaceType = 'aliPay' and ce.transactionId is not null) or (ce.interfaceType = 'yeePay' and ce.transactionId is not null) or ce.interfaceType = '余额支付')  and ce.outTradeNo in(select consumetableId from consumerdetail) ");
        StringBuffer sql = new StringBuffer("select count(*) from consumetable ce  where 1=1 and ((ce.interfaceType = 'tenPay' and ce.transactionId is not null) or (ce.interfaceType = 'aliPay' and ce.transactionId is not null) or (ce.interfaceType = 'yeePay' and ce.transactionId is not null)  or ce.interfaceType = '余额支付')  and ce.outTradeNo in(select consumetableId from consumerdetail)");
        if (StringUtils.isNotBlank(userKey)) {
            hql.append(" and ce.userId='" + userKey.trim() + "'");
            sql.append(" and ce.userId='" + userKey.trim() + "'");
        }
        hql.append(" order by ce.date desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ce", Consumetable.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }


}
