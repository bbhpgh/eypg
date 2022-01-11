package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.dao.Pagination;
import com.eypg.pojo.Cardpassword;
import com.eypg.pojo.Commissionpoints;
import com.eypg.pojo.Latestlottery;
import com.eypg.service.CardpasswordService;

@Service
public class CardpasswordServiceImpl implements CardpasswordService {
    @Autowired
    BaseDAO baseDAO;

    public Cardpassword doCardRecharge(String randomNo, String cardPwd) {
        StringBuffer hql = new StringBuffer();
        if (StringUtils.isNotBlank(randomNo) && StringUtils.isNotBlank(cardPwd)) {
            hql.append("from Cardpassword cd where cd.randomNo ='" + randomNo + "' and cd.cardPwd ='" + cardPwd + "'");
        }
        return (Cardpassword) this.baseDAO.loadObject(hql.toString());
    }

    public void delete(String id) {
        this.baseDAO.delById(Cardpassword.class, id);
    }

    public void deleteByID(Integer id) {
        this.baseDAO.delById(Cardpassword.class, id);
    }

    public List query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public void add(Cardpassword t) {
        this.baseDAO.saveOrUpdate(t);

    }


    public Cardpassword findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }


    public Pagination cardRechargeList(int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from cardpassword cd where 1=1 order by cd.date desc");
        StringBuffer sql = new StringBuffer("select count(*) from cardpassword ");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("cd", Cardpassword.class);
        List list = baseDAO.sqlQuery(hql, entityMap, page);
        int resultCount = baseDAO.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }


}
