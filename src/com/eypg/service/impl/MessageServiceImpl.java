package com.eypg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.pojo.Message;
import com.eypg.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void add(Message t) {
        baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    public Message findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Message> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

}
