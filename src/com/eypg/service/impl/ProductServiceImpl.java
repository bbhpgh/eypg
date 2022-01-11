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
import com.eypg.pojo.Producttype;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.User;
import com.eypg.service.ProductService;
import com.eypg.util.StringUtil;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void add(Product t) {
        baseDao.saveOrUpdate(t);

    }

    public void delete(String id) {
        baseDao.delById(Product.class, id);
    }

    public void update(String hql) {
        baseDao.update(hql);
    }

    public Product findById(String id) {
        StringBuffer hql = new StringBuffer("from Product product where 1=1");
        if (StringUtils.isNotBlank(id)) {
            hql.append(" and product.productId='" + id + "'");
        }
        return (Product) baseDao.loadObject(hql.toString());
    }

    public List<Product> query(String hql) {

        return (List<Product>) baseDao.query(hql);
    }

    public Pagination searchSpellbuyproduct(String keyword, String orderName, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId");
        StringBuffer sql = new StringBuffer("select count(DISTINCT(pt.productId)) from product pt,spellbuyproduct st where st.fkProductId=pt.productId ");
        if (StringUtil.isNotBlank(keyword)) {
            hql.append(" and pt.productName like '%" + keyword + "%'");
            sql.append(" and pt.productName like '%" + keyword + "%'");
        }
        if (orderName.equals("hot")) {
            hql.append(" and (st.spellbuyCount > (pt.productPrice/2))  GROUP by st.spellbuyProductId order by st.spellbuyCount desc");
            sql.append(" and (st.spellbuyCount > (pt.productPrice/2))");
        }
        if (orderName.equals("date")) {
            hql.append(" GROUP by st.spellbuyProductId order by st.spellbuyStartDate desc");
        }
        if (orderName.equals("price")) {
            hql.append(" GROUP by st.spellbuyProductId order by pt.productPrice desc");
        }
        if (orderName.equals("priceAsc")) {
            hql.append(" GROUP by st.spellbuyProductId order by pt.productPrice asc");
        }
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

    public Pagination searchProduct(String typeId, String keyword, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select pt.* from product pt where 1=1");
        StringBuffer sql = new StringBuffer("select count(*) from product pt where 1=1");
        if (StringUtil.isNotBlank(typeId)) {
            hql.append(" and pt.productType = '" + typeId + "'");
            sql.append(" and pt.productType = '" + typeId + "'");
        }
        if (StringUtil.isNotBlank(keyword)) {
            hql.append(" and pt.productName like '%" + keyword + "%'");
            sql.append(" and pt.productName like '%" + keyword + "%'");
        }
        hql.append(" order by pt.productId desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("pt", Product.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

    public Product findProductByName(String productName) {
        StringBuffer hql = new StringBuffer("from Product product where 1=1");
        if (StringUtils.isNotBlank(productName)) {
            hql.append(" and product.productName='" + productName + "'");
        }
        return (Product) baseDao.loadObject(hql.toString());
    }

    public Pagination ProductListByTypeIdList(String typeId, String orderName,
                                              int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from product pt where pt.status = 1");
        StringBuffer sql = new StringBuffer("select count(*) from product pt where pt.status = 1");
        if (typeId != null && !typeId.equals("")) {
            hql.append(" and (1=2");
            sql.append(" and (1=2");
            hql.append(" or (pt.productType= " + typeId + ")");
            sql.append(" or (pt.productType= " + typeId + ")");
            StringBuffer _hql = new StringBuffer("from Producttype where fTypeId =" + typeId);
            List<Producttype> objList = (List<Producttype>) baseDao.query(_hql.toString());
            if (objList != null && objList.size() > 0) {
                for (Producttype producttype : objList) {
                    hql.append(" or (pt.productType= " + producttype.getTypeId() + ")");
                    sql.append(" or (pt.productType= " + producttype.getTypeId() + ")");
                }
            }
            hql.append(")");
            sql.append(")");
        }
//		if(orderName.equals("hot")){
//			hql.append(" GROUP by st.spellbuyProductId order by st.spellbuyCount desc");
//		}
//		if(orderName.equals("date")){
//			hql.append(" GROUP by st.spellbuyProductId order by st.spellbuyStartDate desc");
//		}
//		if(orderName.equals("price")){
//			hql.append(" GROUP by st.spellbuyProductId order by pt.productPrice desc");
//		}
//		if(orderName.equals("priceAsc")){
//			hql.append(" GROUP by st.spellbuyProductId order by pt.productPrice asc");
//		}
//		if(orderName.equals("about")){
//			hql.append(" and (st.spellbuyCount > (pt.productPrice/1.5)) GROUP by pt.productId order by st.spellbuyCount desc");
//			sql.append(" and (st.spellbuyCount > (pt.productPrice/1.5))");
//		}
//		if(orderName.equals("surplus")){
//			hql.append("  GROUP by pt.productId order by (pt.productPrice -st.spellbuyCount) asc");
//		}
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("pt", Product.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

}
