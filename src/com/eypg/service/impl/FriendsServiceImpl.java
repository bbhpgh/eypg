package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.dao.Pagination;
import com.eypg.pojo.Friends;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.User;
import com.eypg.service.FriendsService;

@Service
public class FriendsServiceImpl implements FriendsService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void add(Friends t) {
        baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    public Friends findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Friends> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public Pagination getFriends(String userId, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from friends fs,user ur where fs.friendsId = ur.userId and fs.userId = '" + userId + "'");
        StringBuffer sql = new StringBuffer("select count(*) from friends fs,user ur where fs.friendsId = ur.userId and fs.userId = '" + userId + "'");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("fs", Friends.class);
        entityMap.put("ur", User.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

}
