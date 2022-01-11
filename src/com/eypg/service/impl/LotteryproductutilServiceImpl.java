package com.eypg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.pojo.Lotteryproductutil;
import com.eypg.service.LotteryproductutilService;

@Service
public class LotteryproductutilServiceImpl implements LotteryproductutilService {
    @Autowired
    BaseDAO baseDAO;

    public void add(Lotteryproductutil t) {
        this.baseDAO.saveOrUpdate(t);
    }

    public void delete(String id) {
        this.baseDAO.delById(Lotteryproductutil.class, id);
    }

    public Lotteryproductutil findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Lotteryproductutil> query(String hql) {
        return (List<Lotteryproductutil>) this.baseDAO.query(hql);
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public List<Lotteryproductutil> loadAll() {
        return (List<Lotteryproductutil>) this.baseDAO.query("from Lotteryproductutil lotteryproductutil where 1=1");
    }

}
