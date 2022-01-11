package com.eypg.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.pojo.News;
import com.eypg.pojo.SysConfigure;
import com.eypg.service.SysConfigureService;

@Service
public class SysConfigureServiceImpl implements SysConfigureService {
    @Autowired
    BaseDAO baseDao;

    public void add(SysConfigure t) {
        this.baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    public SysConfigure findById(String id) {
        StringBuffer hql = new StringBuffer("from SysConfigure s where 1=1");
        if (StringUtils.isNotBlank(id)) {
            hql.append(" and s.id='" + id + "'");
        }
        return (SysConfigure) baseDao.loadObject(hql.toString());
    }

    public List<SysConfigure> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

}
