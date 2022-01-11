package com.eypg.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eypg.pojo.Product;
import com.eypg.pojo.Productimage;
import com.eypg.service.ProductImageService;
import com.eypg.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("ProductInfoAction")
public class ProductInfoAction extends ActionSupport {

    private static final long serialVersionUID = -5354743639843709687L;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService productImageService;


    private String id;
    private Product product;
    private List<Productimage> productimageList;

    public String index() {

        product = productService.findById(id);

        productimageList = productImageService.findByProductId(String.valueOf(product.getProductId()), "show");

        return "index";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Productimage> getProductimageList() {
        return productimageList;
    }

    public void setProductimageList(List<Productimage> productimageList) {
        this.productimageList = productimageList;
    }


}
