package com.eypg.service;

import java.util.List;

import com.eypg.pojo.Producttype;

public interface ProducttypeService extends TService<Producttype, Integer> {

    public List<Producttype> queryAllProductType();

}
