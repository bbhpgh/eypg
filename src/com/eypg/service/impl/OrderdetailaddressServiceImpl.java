package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.dao.Pagination;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Orderdetailaddress;
import com.eypg.service.OrderdetailaddressService;

@Service
public class OrderdetailaddressServiceImpl implements OrderdetailaddressService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void add(Orderdetailaddress t) {
        this.baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    public Orderdetailaddress findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Orderdetailaddress> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public Pagination orderdetailaddressList(int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from orderdetailaddress os order by os.deliverTime desc");
        StringBuffer sql = new StringBuffer("select count(*) from orderdetailaddress");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("os", Orderdetailaddress.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

}
