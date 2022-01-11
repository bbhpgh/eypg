package com.eypg.service;

import java.util.List;

import com.eypg.pojo.Productimage;

public interface ProductImageService extends TService<Productimage, Integer> {

    public List findByProductId(String productId, String type);

}
