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
import com.eypg.pojo.Product;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.service.SpellbuyproductService;

@Service("spellbuyproductService")
public class SpellbuyproductServiceImpl implements SpellbuyproductService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void add(Spellbuyproduct t) {
        baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        baseDao.delById(Spellbuyproduct.class, id);
    }

    public Spellbuyproduct findById(String id) {
        StringBuffer hql = new StringBuffer("from Spellbuyproduct spellbuyproduct where 1=1");
        if (StringUtils.isNotBlank(id)) {
            hql.append(" and spellbuyproduct.spellbuyProductId='" + id + "'");
        }
        return (Spellbuyproduct) baseDao.loadObject(hql.toString());
    }

    public List<Spellbuyproduct> query(String hql) {
        // TODO Auto-generated method stub
        return (List<Spellbuyproduct>) baseDao.query(hql);
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    /**
     * 商品页显示页
     */
    public List findByProductId(int productId) {
        StringBuffer sql = new StringBuffer("select pt.*,st.* from spellbuyproduct st,product pt where pt.productId = st.fkProductId and st.spellbuyProductId =" + productId);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("pt", Product.class);
        entityMap.put("st", Spellbuyproduct.class);
        List list = baseDao.sqlQueryEntity(sql, entityMap);
        return list;
    }

    public Pagination upcomingAnnounced(int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select pt.*,st.* from Product pt,Spellbuyproduct st where 1=1 and st.fkProductId=pt.productId  and (st.spellbuyCount > (pt.productPrice/1.5)) and st.spStatus <> 1  GROUP by pt.productId order by st.spellbuyCount desc");
//		StringBuffer hql = new StringBuffer("select pt.*,st.* from Product pt,Spellbuyproduct st where 1=1 and st.fkProductId=pt.productId  and st.spStatus <> 1  GROUP by pt.productId order by st.spellbuyCount desc");
//		StringBuffer sql = new StringBuffer("select count(DISTINCT(pt.productId)) from product pt,spellbuyproduct st where st.fkProductId=pt.productId and (st.spellbuyCount > (pt.productPrice/1.5)) and st.status <> 1");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("pt", Product.class);
        entityMap.put("st", Spellbuyproduct.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(resultCount);
        return page;
    }

    public Pagination upcomingAnnouncedByTop(int pageNo, int pageSize) {
//		StringBuffer hql = new StringBuffer("select pt.*,st.* from Product pt,Spellbuyproduct st where 1=1 and st.fkProductId=pt.productId  and (st.spellbuyCount > (pt.productPrice/1.5)) and st.spStatus <> 1  GROUP by pt.productId order by st.spellbuyCount desc");
        StringBuffer hql = new StringBuffer("select pt.*,st.* from Product pt,Spellbuyproduct st where 1=1 and st.fkProductId=pt.productId  and st.spStatus <> 1  GROUP by pt.productId order by st.spellbuyCount desc");
//		StringBuffer sql = new StringBuffer("select count(DISTINCT(pt.productId)) from product pt,spellbuyproduct st where st.fkProductId=pt.productId and (st.spellbuyCount > (pt.productPrice/1.5)) and st.status <> 1");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("pt", Product.class);
        entityMap.put("st", Spellbuyproduct.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(resultCount);
        return page;
    }

    public List productPeriodList(Integer productId) {
        StringBuffer hql = new StringBuffer("select st.*,pt.* from spellbuyproduct st,product pt where st.fkProductId = pt.productId and pt.productId = '" + productId + "' order by st.productPeriod desc");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("st", Spellbuyproduct.class);
        entityMap.put("pt", Product.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public Spellbuyproduct findSpellbuyproductLastPeriod(Integer productId) {
        StringBuffer hql = new StringBuffer("from Spellbuyproduct spellbuyproduct where spellbuyproduct.fkProductId ='" + productId + "' order by spellbuyproduct.productPeriod desc limit 1");
        return (Spellbuyproduct) baseDao.loadObject(hql.toString());
    }

    public Spellbuyproduct findSpellbuyproductByStatus(Integer productId) {
        StringBuffer hql = new StringBuffer("from Spellbuyproduct spellbuyproduct where spellbuyproduct.fkProductId ='" + productId + "' and spellbuyproduct.spStatus=0 ");
        return (Spellbuyproduct) baseDao.loadObject(hql.toString());
    }

    public Pagination announcedProduct(int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus = 1");
        StringBuffer sql = new StringBuffer("select count(DISTINCT(st.spellbuyProductId)) from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus = 1");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("pt", Product.class);
        entityMap.put("st", Spellbuyproduct.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

    public List loadAllByType() {
        StringBuffer sql = new StringBuffer("from Spellbuyproduct st where 1=1 and st.spStatus <> 1 and st.spellbuyType = 8");
        List list = baseDao.find(sql.toString());
        return list;
    }

    public List loadAll() {
        StringBuffer sql = new StringBuffer("from Spellbuyproduct st where 1=1 and st.spStatus <> 1");
        List list = baseDao.find(sql.toString());
        return list;
    }

    public List UpdateLatestlotteryGetList() {
        StringBuffer sql = new StringBuffer("from Spellbuyproduct st where 1=1 and st.spStatus = 1");
        List list = baseDao.find(sql.toString());
        return list;
    }

    public List findSpellbuyproductByProductIdIsStatus(Integer productId) {
        StringBuffer hql = new StringBuffer("select * from spellbuyproduct st where st.fkProductId = '" + productId + "' and spStatus = 0");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("st", Spellbuyproduct.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }


}
