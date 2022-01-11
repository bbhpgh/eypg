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
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Orderdetail;
import com.eypg.pojo.Orderdetailaddress;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.User;
import com.eypg.service.LatestlotteryService;

@Service("latestlotteryService")
public class LatestlotteryServiceImpl implements LatestlotteryService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public Pagination LatestAnnounced(int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from latestlottery order by AnnouncedTime desc");
        StringBuffer sql = new StringBuffer("select count(*) from latestlottery");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ly", Latestlottery.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

    public void add(Latestlottery t) {
        baseDao.saveOrUpdate(t);

    }

    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    public Latestlottery findById(String id) {
        StringBuffer hql = new StringBuffer("from Latestlottery ly where 1=1");
        if (StringUtils.isNotBlank(id)) {
            hql.append(" and ly.spellbuyProductId='" + id + "'");
        }
        return (Latestlottery) baseDao.loadObject(hql.toString());
    }

    public List<Latestlottery> query(String hql) {
        return (List<Latestlottery>) baseDao.query(hql);
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public List getBuyHistoryByDetail(Integer spellbuyProductId) {
        StringBuffer hql = new StringBuffer("select * from latestlottery ly where ly.spellbuyProductId = '" + spellbuyProductId + "'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ly", Latestlottery.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public Pagination getProductByUser(String userId, String startDate, String endDate, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from latestlottery ly where ly.userId = '" + userId + "'");
//		StringBuffer sql = new StringBuffer("select count(*) from latestlottery ly where ly.userId = '"+userId+"'");
        if (StringUtils.isNotBlank(startDate)) {
            hql.append(" and ly.announcedTime >='" + startDate + "'");
//			sql.append(" and ly.announcedTime >='"+startDate+"'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            hql.append(" and ly.announcedTime <='" + endDate + "'");
//			sql.append(" and ly.announcedTime <='"+endDate+"'");
        }
        hql.append(" order by ly.announcedTime desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ly", Latestlottery.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(resultCount);
        return page;
    }

    public Integer getProductByUserByCount(String userId, String startDate, String endDate) {
        StringBuffer sql = new StringBuffer("select count(*) from latestlottery ly where ly.userId = '" + userId + "'");
        if (StringUtils.isNotBlank(startDate)) {
            sql.append(" and ly.announcedTime >='" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and ly.announcedTime <='" + endDate + "'");
        }
        return baseDao.getAllNum(sql);
    }

    public List indexWinningScroll() {
        StringBuffer hql = new StringBuffer("select * from latestlottery ly order by ly.announcedTime desc limit 6");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ly", Latestlottery.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public List getLotteryDetail(Integer spellbuyProductId) {
        StringBuffer hql = new StringBuffer("select * from latestlottery ly where ly.spellbuyProductId = '" + spellbuyProductId + "'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ly", Latestlottery.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public Integer getCountByLotteryDetail(String spellbuyProductId) {
        StringBuffer hql = new StringBuffer("select count(DISTINCT buyer) from spellbuyrecord where fkSpellbuyProductId = '" + spellbuyProductId + "'");
        return baseDao.getAllNum(hql);
    }

    public Pagination getLotteryDetailBybuyerList(Integer SpellbuyProductId, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from user ur,randomnumber rr where rr.userId=ur.userId and rr.productId= '" + SpellbuyProductId + "' order by rr.buyDate desc ");
//		StringBuffer sql = new StringBuffer("select count(*) from user ur,randomnumber rr where rr.userId=ur.userId and rr.productId = '"+SpellbuyProductId+"'");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("rr", Randomnumber.class);
        entityMap.put("ur", User.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(resultCount);
        return page;
    }

    public Integer getLotteryDetailBybuyerListByCount(Integer SpellbuyProductId) {
        StringBuffer sql = new StringBuffer("select count(*) from user ur,randomnumber rr where rr.userId=ur.userId and rr.productId = '" + SpellbuyProductId + "'");
        return baseDao.getAllNum(sql);
    }

    public List getProductOtherWinUser(String productId, String shareId) {
        StringBuffer hql = new StringBuffer("select * from latestlottery where productId = '" + productId + "' and shareId <> '" + shareId + "' limit 6");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ly", Latestlottery.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public List getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(
            Integer SpellbuyProductId) {
        StringBuffer hql = new StringBuffer("select * from latestlottery ly where ly.spellbuyProductId = '" + SpellbuyProductId + "' limit 1");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ly", Latestlottery.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public Latestlottery findLatestlotteryByspellbuyrecordId(Integer spellbuyrecordId) {
        StringBuffer hql = new StringBuffer("from Latestlottery latestlottery where latestlottery.spellbuyRecordId='" + spellbuyrecordId + "' ");
        return (Latestlottery) baseDao.loadObject(hql.toString());
    }

    public List orderdetailListById(String id) {
        StringBuffer hql = new StringBuffer("select * from orderdetail ol where ol.orderDetailId = '" + id + "'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ol", Orderdetail.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public Orderdetailaddress orderdetailaddressFindByOrderdetailId(String id) {
        StringBuffer hql = new StringBuffer("from Orderdetailaddress os where os.orderDetailId = '" + id + "'");

        return (Orderdetailaddress) baseDao.loadObject(hql.toString());
    }

    public void addOrderdetailaddress(Orderdetailaddress orderdetailaddress) {
        baseDao.saveOrUpdate(orderdetailaddress);
    }

}
