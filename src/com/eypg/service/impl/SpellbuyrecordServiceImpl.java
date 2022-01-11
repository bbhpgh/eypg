package com.eypg.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.dao.Pagination;
import com.eypg.pojo.BuyHistoryJSON;
import com.eypg.pojo.Product;
import com.eypg.pojo.Producttype;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.util.DateUtil;

@Service("spellbuyrecordService")
public class SpellbuyrecordServiceImpl implements SpellbuyrecordService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void add(Spellbuyrecord t) {
        baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        baseDao.delById(Spellbuyrecord.class, id);
    }

    public Spellbuyrecord findById(String id) {
        StringBuffer hql = new StringBuffer("from Spellbuyrecord spellbuyrecord where 1=1");
        if (StringUtils.isNotBlank(id)) {
            hql.append(" and spellbuyrecord.spellbuyRecordId='" + id + "'");
        }
        return (Spellbuyrecord) baseDao.loadObject(hql.toString());
    }

    public List<Spellbuyrecord> query(String hql) {
        // TODO Auto-generated method stub
        return (List<Spellbuyrecord>) baseDao.query(hql);
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    /**
     * 首页最热门商品
     */
    public Pagination findHotProductList(int pageNo, int pageSize) {
//		StringBuffer hql = new StringBuffer("select pt.*,st.* from Product pt,Spellbuyproduct st where 1=1 and st.fkProductId=pt.productId  and (st.spellbuyCount > (pt.productPrice/2))  and st.spStatus <> 1  GROUP by pt.productId order by st.spellbuyCount desc");
        StringBuffer hql = new StringBuffer("select pt.*,st.* from Product pt,Spellbuyproduct st where 1=1 and st.fkProductId=pt.productId  and st.spStatus <> 1  GROUP by pt.productId order by st.spellbuyCount desc");
//		StringBuffer sql = new StringBuffer("select count(*) from Spellbuyrecord");
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

    /**
     * Ta他们正在购买
     */
    public Pagination getNowBuyList(int pageNo, int pageSize) {
        Date sDate = DateUtil.subMinute(new Date(), 20);
        String startDate = sdf.format(sDate);
        String endDate = sdf.format(new Date());
        StringBuffer hql = new StringBuffer("select ur.*,sd.*,pt.*,st.* from User ur, Spellbuyrecord sd,Product pt,Spellbuyproduct st where 1=1 and sd.buyer=ur.userId and sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId order by sd.buyDate desc");
//		StringBuffer sql = new StringBuffer("select count(*) from Spellbuyrecord");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ur", User.class);
        entityMap.put("sd", Spellbuyrecord.class);
        entityMap.put("pt", Product.class);
        entityMap.put("st", Spellbuyproduct.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(6);
        return page;
    }

    /**
     * 首页按商品类型查询商品
     */
    public Pagination findProductByTypeId(String typeId, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus <> 1");
//		StringBuffer sql = new StringBuffer("select count(*) from Spellbuyrecord");
        if (typeId != null && !typeId.equals("")) {
            hql.append(" and (1=2");
            hql.append(" or (1=1 and pt.productType= " + typeId + ")");
            StringBuffer _hql = new StringBuffer("from Producttype where fTypeId =" + typeId);
            List<Producttype> objList = (List<Producttype>) baseDao.query(_hql.toString());
            if (objList != null && objList.size() > 0) {
                for (Producttype producttype : objList) {
                    hql.append(" or (1=1 and pt.productType= " + producttype.getTypeId() + ")");
                }
            }
            hql.append(")");
        }
        hql.append(" GROUP by pt.productId");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("pt", Product.class);
        entityMap.put("st", Spellbuyproduct.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(8);
        return page;
    }

    /**
     * 商品页按商品类型查询商品
     */
    public Pagination ProductByTypeIdList(String typeId, String orderName, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus <> 1");
        StringBuffer sql = new StringBuffer("select count(DISTINCT(st.spellbuyProductId)) from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus <> 1");
        if (typeId != null && !typeId.equals("")) {
            hql.append(" and (1=2");
            sql.append(" and (1=2");
            hql.append(" or (1=1 and pt.productType= " + typeId + ")");
            sql.append(" or (1=1 and pt.productType= " + typeId + ")");
            StringBuffer _hql = new StringBuffer("from Producttype where fTypeId =" + typeId);
            List<Producttype> objList = (List<Producttype>) baseDao.query(_hql.toString());
            if (objList != null && objList.size() > 0) {
                for (Producttype producttype : objList) {
                    hql.append(" or (1=1 and pt.productType= " + producttype.getTypeId() + ")");
                    sql.append(" or (1=1 and pt.productType= " + producttype.getTypeId() + ")");
                }
            }
            hql.append(")");
            sql.append(")");
        }
        if (orderName.equals("hot")) {
            hql.append(" GROUP by st.spellbuyProductId order by st.spellbuyCount desc");
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
        if (orderName.equals("about")) {
            hql.append(" and (st.spellbuyCount > (pt.productPrice/1.5)) GROUP by pt.productId order by st.spellbuyCount desc");
            sql.append(" and (st.spellbuyCount > (pt.productPrice/1.5))");
        }
        if (orderName.equals("surplus")) {
            hql.append("  GROUP by pt.productId order by (pt.productPrice -st.spellbuyCount) asc");
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

    /**
     * 搜索商品
     */
    public Pagination searchProduct(String keyword, String orderName, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus <> 1 and pt.productName like '%" + keyword + "%'");
        StringBuffer sql = new StringBuffer("select count(DISTINCT(pt.productId)) from product pt,spellbuyproduct st where st.fkProductId=pt.productId  and st.spStatus <> 1 and pt.productName like '%" + keyword + "%'");
        if (orderName.equals("hot")) {
            hql.append(" GROUP by st.spellbuyProductId order by st.spellbuyCount desc");
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
        if (orderName.equals("about")) {
            hql.append(" and (st.spellbuyCount > (pt.productPrice/1.5)) GROUP by pt.productId order by st.spellbuyCount desc");
        }
        if (orderName.equals("surplus")) {
            hql.append("  GROUP by pt.productId order by (pt.productPrice -st.spellbuyCount) asc");
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

    /**
     * 最新参与
     */
    public Pagination LatestParticipate(String spellbuyProductId, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select ur.*, sd.* from user ur, spellbuyrecord sd where sd.buyer=ur.userId and sd.fkSpellbuyProductId = '" + spellbuyProductId + "'  order by sd.buyDate desc");
//		StringBuffer sql = new StringBuffer("select count(*) from user ur, spellbuyrecord sd where sd.buyer=ur.userId and sd.fkSpellbuyProductId ='"+spellbuyProductId+"'");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ur", User.class);
        entityMap.put("sd", Spellbuyrecord.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(resultCount);
        return page;
    }

    public Integer LatestParticipateByCount(String spellbuyProductId) {
        StringBuffer sql = new StringBuffer("select count(*) from user ur, spellbuyrecord sd where sd.buyer=ur.userId and sd.fkSpellbuyProductId ='" + spellbuyProductId + "'");
        return baseDao.getAllNum(sql);
    }

    /**
     * 得到某条购买记录的随机码(list 页)
     */
    public List getRandomNumberList(String spellbuyProductId, String userId) {
        StringBuffer hql = new StringBuffer("SELECT * from randomnumber rr where rr.productId = '" + spellbuyProductId + "' and rr.userId = '" + userId + "' limit 6");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("rr", Randomnumber.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    /**
     * 得到某条购买记录的随机码(详细页面)
     */
    public Pagination getRandomNumberListPage(String spellbuyProductId, String userId, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("SELECT * from randomnumber rr where rr.productId = '" + spellbuyProductId + "' and rr.userId = '" + userId + "' order by rr.buyDate desc");
//		StringBuffer sql = new StringBuffer("select count(*) from randomnumber rr where rr.productId = '"+spellbuyProductId+"' and rr.userId = '"+userId+"'");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("rr", Randomnumber.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(resultCount);
        return page;
    }

    public Integer getRandomNumberListPageByCount(String spellbuyProductId, String userId) {
        StringBuffer sql = new StringBuffer("select count(*) from randomnumber rr where rr.productId = '" + spellbuyProductId + "' and rr.userId = '" + userId + "'");
        return baseDao.getAllNum(sql);
    }

    /**
     * 查询某用户的购买记录
     */
    public Pagination buyHistoryByUser(String userId, String startDate, String endDate, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select st.spellbuyProductId as productId,pt.productName as productName,pt.productTitle as productTitle,pt.headImage as productImg,st.productPeriod as productPeriod,st.spStatus as buyStatus,sd.buyDate as buyTime,sum(sd.buyPrice) as buyCount,sd.spellbuyRecordId as historyId,st.spellbuyCount as spellbuyCount,st.spellbuyPrice as productPrice from spellbuyrecord sd,spellbuyproduct st,product pt,user ur where sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.buyer=ur.userId  and sd.buyer='" + userId + "'");
//		StringBuffer sql = new StringBuffer("select count(*) from (select count(*) from spellbuyrecord sd,spellbuyproduct st,product pt,user ur where sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.buyer=ur.userId and sd.buyer='"+userId+"' ");
        if (StringUtils.isNotBlank(startDate)) {
            hql.append(" and sd.buyDate >='" + startDate + "'");
//			sql.append(" and sd.buyDate >='"+startDate+"'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            hql.append(" and sd.buyDate <='" + endDate + "'");
//			sql.append(" and sd.buyDate <='"+endDate+"'");
        }
        hql.append(" group by st.spellbuyProductId  order by sd.buyDate desc");
//		sql.append(" group by st.spellbuyProductId) as counts");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("buyCount", BuyHistoryJSON.class);
        List list = baseDao.sqlQueryBean(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(resultCount);
        return page;
    }

    public Integer buyHistoryByUserByCount(String userId, String startDate, String endDate) {
        StringBuffer sql = new StringBuffer("select count(*) from (select count(*) from spellbuyrecord sd,spellbuyproduct st,product pt,user ur where sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.buyer=ur.userId and sd.buyer='" + userId + "' ");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and sd.buyDate >='" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and sd.buyDate <='" + endDate + "'");
        }
        sql.append(" group by st.spellbuyProductId) as counts");
        return baseDao.getAllNum(sql);
    }

    /**
     * 查询某用户购买记录详情
     */
    public List getBuyHistoryByDetail(String productId, String userId) {
        StringBuffer hql = new StringBuffer("select st.spellbuyProductId as productId,pt.productName as productName,pt.productTitle as productTitle,pt.headImage as productImg,st.productPeriod as productPeriod,st.spStatus as buyStatus,sd.buyDate as buyTime,sum(sd.buyPrice) as buyCount,sd.spellbuyRecordId as historyId,st.spellbuyCount as spellbuyCount,st.spellbuyPrice as productPrice from spellbuyrecord sd,spellbuyproduct st,product pt,user ur where sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.buyer=ur.userId  and sd.buyer='" + userId + "' and st.spellbuyProductId='" + productId + "'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("buyCount", BuyHistoryJSON.class);
        List list = baseDao.sqlQueryEntityBean(hql, entityMap);
        return list;
    }

    public Pagination nowUpProducts(int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus <> 1  GROUP by st.spellbuyProductId order by st.spellbuyStartDate desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("pt", Product.class);
        entityMap.put("st", Spellbuyproduct.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        page.setList(list);
        return page;
    }

    public List randomByBuyHistoryByspellbuyProductId(Integer productId, String randomNumber) {
        StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd,user ur,randomnumber rr where sd.buyer=ur.userId and sd.spellbuyRecordId = rr.spellbuyrecordId and sd.fkSpellbuyProductId='" + productId + "' and rr.randomNumber like '%" + randomNumber + "%'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("sd", Spellbuyrecord.class);
        entityMap.put("ur", User.class);
        entityMap.put("rr", Randomnumber.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public List WinRandomNumber(Integer productId, Integer randomNumber) {
        StringBuffer hql = new StringBuffer("select * from user ur,randomnumber rr where ur.userId = rr.userId and ur.userPwd = '1ypg.com' and  rr.productId = '" + productId + "' and rr.randomNumber like '%" + randomNumber + "%'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ur", User.class);
        entityMap.put("rr", Randomnumber.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public BigDecimal getAllByCount() {
        StringBuffer hql = new StringBuffer("select sum(buyPrice) from spellbuyrecord");
        return baseDao.getSelectSum(hql);
    }

    public Pagination getAllBuyRecord(String startDate, String endDate,
                                      int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd,spellbuyproduct st,product pt,user ur where sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.buyer=ur.userId ");
        StringBuffer sql = new StringBuffer("select count(*) from spellbuyrecord sd,spellbuyproduct st,product pt,user ur where sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.buyer=ur.userId ");
        if (StringUtils.isNotBlank(startDate)) {
            hql.append(" and sd.buyDate >='" + startDate + "'");
            sql.append(" and sd.buyDate >='" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            hql.append(" and sd.buyDate <='" + endDate + "'");
            sql.append(" and sd.buyDate <='" + endDate + "'");
        }
        hql.append(" order by sd.buyDate desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("sd", Spellbuyrecord.class);
        entityMap.put("st", Spellbuyproduct.class);
        entityMap.put("pt", Product.class);
        entityMap.put("ur", User.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

    public Pagination getlotteryDetail(String startDate, String endDate,
                                       int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd,spellbuyproduct st,product pt,user ur where sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.buyer=ur.userId ");
        if (StringUtils.isNotBlank(startDate)) {
            hql.append(" and sd.buyDate >='" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            hql.append(" and sd.buyDate <='" + endDate + "'");
        }
        hql.append(" order by sd.buyDate desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("sd", Spellbuyrecord.class);
        entityMap.put("st", Spellbuyproduct.class);
        entityMap.put("pt", Product.class);
        entityMap.put("ur", User.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        page.setList(list);
        return page;
    }

    public List getSpellbuyRecordByLast100(String startDate, String endDate) {
        StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd where 1=1");
        if (StringUtils.isNotBlank(startDate)) {
            hql.append(" and sd.buyDate >='" + startDate + ".000" + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            hql.append(" and sd.buyDate <='" + endDate + ".999" + "'");
        }
        hql.append(" order by sd.buyDate desc limit 120");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("sd", Spellbuyrecord.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public List getEndBuyDateByProduct(Integer fkSpellbuyProductId) {
        StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd where fkSpellbuyProductId = '" + fkSpellbuyProductId + "' order by buyDate desc limit 1");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("sd", Spellbuyrecord.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public BigDecimal findSumByBuyPriceByUserId(String userId) {
        StringBuffer hql = new StringBuffer("select sum(buyPrice) from spellbuyrecord where buyer = '" + userId + "'");
        return baseDao.getSelectSum(hql);
    }

    public List getUserByHistory(String userId, String fkSpellbuyProductId) {
        StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd where sd.buyer = '" + userId + "' and sd.fkSpellbuyProductId = '" + fkSpellbuyProductId + "' order by sd.buyDate desc limit 8");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("sd", Spellbuyrecord.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public Pagination getNowBuyAjaxList(int pageNo, int pageSize, int spellbuyRecordId) {
        Date sDate = DateUtil.subMinute(new Date(), 20);
        String startDate = sdf.format(sDate);
        String endDate = sdf.format(new Date());
        StringBuffer hql = new StringBuffer("select ur.*,sd.*,pt.*,st.* from User ur, Spellbuyrecord sd,Product pt,Spellbuyproduct st where 1=1 and sd.buyer=ur.userId and sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.spellbuyRecordId > '" + spellbuyRecordId + "' order by sd.buyDate desc");
//		StringBuffer sql = new StringBuffer("select count(*) from Spellbuyrecord");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ur", User.class);
        entityMap.put("sd", Spellbuyrecord.class);
        entityMap.put("pt", Product.class);
        entityMap.put("st", Spellbuyproduct.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(6);
        return page;
    }


}
