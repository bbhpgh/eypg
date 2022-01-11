package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.pojo.Product;
import com.eypg.pojo.Recommend;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.service.RecommendService;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    public Recommend findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Recommend> query(String hql) {
        // TODO Auto-generated method stub
        return (List<Recommend>) baseDao.query(hql);
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public List getRecommend() {
        StringBuffer sql = new StringBuffer("select * from recommend rd,spellbuyproduct st,product pt where rd.spellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId order by rd.date desc limit 1");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("rd", Recommend.class);
        entityMap.put("st", Spellbuyproduct.class);
        entityMap.put("pt", Product.class);
        List list = baseDao.sqlQueryEntity(sql, entityMap);
        return list;
    }

    public void add(Recommend t) {
        this.baseDao.saveOrUpdate(t);
    }

}
