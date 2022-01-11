package com.eypg.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.pojo.Randomnumber;
import com.eypg.service.RandomnumberService;

@Service("RandomnumberService")
public class RandomnumberServiceImpl implements RandomnumberService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void add(Randomnumber t) {
        baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        baseDao.delById(Randomnumber.class, id);

    }

    public Randomnumber findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Randomnumber> query(String hql) {
        // TODO Auto-generated method stub
        return (List<Randomnumber>) baseDao.query(hql);
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public List LotteryDetailByRandomnumber(Integer userId, Integer spellbuyProductId) {
        StringBuffer hql = new StringBuffer("select * from randomnumber rr where  rr.userId = '" + userId + "' and rr.productId = '" + spellbuyProductId + "' order by rr.buyDate desc");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("rr", Randomnumber.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public BigDecimal RandomNumberByUserBuyCount(String userId, Integer spellbuyProductId) {
        StringBuffer hql = new StringBuffer("select sum(buyPrice) from spellbuyrecord where buyer = '" + userId + "' and fkSpellbuyProductId='" + spellbuyProductId + "'");
        return baseDao.getSelectSum(hql);
    }

    public Randomnumber findRandomnumberByspellbuyrecordId(Integer spellbuyrecordId) {
        StringBuffer hql = new StringBuffer("from Randomnumber randomnumber where randomnumber.spellbuyrecordId='" + spellbuyrecordId + "' ");
        return (Randomnumber) baseDao.loadObject(hql.toString());
    }


}
