package com.eypg.pojo;

import java.io.Serializable;

public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 2984681206487401036L;

    private String productName;
    private String productTitle;
    private Integer productPrice;
    private String headImage;
    private String productDetail;
    private Integer spellbuyProductId;
    private Integer spellbuyCount;
    private Integer productPeriod;
    private Integer status;

    public ProductInfo() {
        super();
    }

    public ProductInfo(String productName, String productTitle,
                       Integer productPrice, String headImage, String productDetail,
                       Integer spellbuyProductId, Integer spellbuyCount,
                       Integer productPeriod, Integer status) {
        super();
        this.productName = productName;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.headImage = headImage;
        this.productDetail = productDetail;
        this.spellbuyProductId = spellbuyProductId;
        this.spellbuyCount = spellbuyCount;
        this.productPeriod = productPeriod;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public Integer getSpellbuyProductId() {
        return spellbuyProductId;
    }

    public void setSpellbuyProductId(Integer spellbuyProductId) {
        this.spellbuyProductId = spellbuyProductId;
    }

    public Integer getSpellbuyCount() {
        return spellbuyCount;
    }

    public void setSpellbuyCount(Integer spellbuyCount) {
        this.spellbuyCount = spellbuyCount;
    }

    public Integer getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


}
