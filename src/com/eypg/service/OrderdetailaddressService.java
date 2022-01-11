package com.eypg.service;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Orderdetailaddress;

public interface OrderdetailaddressService extends TService<Orderdetailaddress, Integer> {

    public Pagination orderdetailaddressList(int pageNo, int pageSize);

}
