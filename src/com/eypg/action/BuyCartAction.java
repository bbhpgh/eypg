package com.eypg.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eypg.pojo.Product;
import com.eypg.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("BuyCartAction")
public class BuyCartAction extends ActionSupport {

    private static final long serialVersionUID = -4695580454502546044L;

    private String id;

    @Autowired
    private ProductService productService;
    private Product product;


    public String index() {

        product = productService.findById(id);

        return "index";
    }

}
