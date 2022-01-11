package com.eypg.service;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Product;

public interface ProductService extends TService<Product, Integer> {

    /**
     * 商品期数列表
     *
     * @param keyword
     * @param orderName
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination searchSpellbuyproduct(String keyword, String orderName, int pageNo, int pageSize);

    /**
     * 商品列表
     *
     * @param keyword
     * @param orderName
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination searchProduct(String typeId, String keyword, int pageNo, int pageSize);

    public Product findProductByName(String productName);


    /**
     * 直接购买商品列表
     *
     * @param typeId
     * @param orderName
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination ProductListByTypeIdList(String typeId, String orderName, int pageNo, int pageSize);
}
