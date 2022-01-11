package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.pojo.Productimage;
import com.eypg.service.ProductImageService;

@Service("productImageService")
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void add(Productimage t) {
        baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        baseDao.delById(Productimage.class, id);
    }

    public Productimage findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Productimage> query(String hql) {
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public List findByProductId(String productId, String type) {
        StringBuffer hql = new StringBuffer("select pe.* from productimage pe where pe.piProductId = '" + productId + "' and pe.Attribute_75 = '" + type + "' order by pe.image asc");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("pe", Productimage.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

}
