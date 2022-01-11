package com.eypg.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.pojo.Producttype;
import com.eypg.service.ProducttypeService;

@Service
public class ProducttypeServiceImpl implements ProducttypeService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void add(Producttype t) {
        baseDao.saveOrUpdate(t);

    }

    public void delete(String id) {
        baseDao.delById(Producttype.class, id);

    }

    public Producttype findById(String id) {
        StringBuffer hql = new StringBuffer("from Producttype p where 1=1");
        if (StringUtils.isNotBlank(id)) {
            hql.append(" and p.typeId='" + id + "'");
        }
        return (Producttype) baseDao.loadObject(hql.toString());
    }

    public List<Producttype> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public List<Producttype> queryAllProductType() {
        String hql = " from Producttype p where 1=1";
        return (List<Producttype>) baseDao.query(hql);
    }

}
