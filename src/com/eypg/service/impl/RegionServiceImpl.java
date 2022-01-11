package com.eypg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public List getCityListByProvinceId(String provinceId) {
        StringBuffer hql = new StringBuffer("select * from s_city where pid = '" + provinceId + "'");
        return this.baseDao.querySQL(hql.toString());
    }

    public List getDistrictListByCityId(String cityId) {
        StringBuffer hql = new StringBuffer("select * from s_district where cid = '" + cityId + "'");
        return this.baseDao.querySQL(hql.toString());
    }

    public List getProvinceList() {
        StringBuffer hql = new StringBuffer("select * from s_province");
        return this.baseDao.querySQL(hql.toString());
    }

}
