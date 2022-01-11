package com.eypg.service;

import java.util.List;


public interface RegionService {

    /**
     * 得到省List
     *
     * @return
     */
    public List getProvinceList();

    /**
     * 得到市List
     *
     * @param provinceId
     * @return
     */
    public List getCityListByProvinceId(String provinceId);

    /**
     * 得到区List
     *
     * @param cityId
     * @return
     */
    public List getDistrictListByCityId(String cityId);

}
