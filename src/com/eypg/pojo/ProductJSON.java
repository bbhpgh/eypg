package com.eypg.pojo;

import java.io.Serializable;

public class ProductJSON implements Serializable {

    private static final long serialVersionUID = -8927491985109183983L;

    private Integer productId;//商品ID
    private String productName;//商品名称
    private Integer productPrice;//商品价格|商品总份数
    private String productTitle;//商品标题
    private String headImage;//商品图片
    private Integer currentBuyCount;//当前购买数
    private String buyer;//购买人
    private String userId;
    private Integer productPeriod; //商品期数
    private String buyDate; //购买时间
    private Integer buyCount;//购买数量
    private String productStyle; //商品种类


    public ProductJSON() {
        super();
    }


    public ProductJSON(Integer productId, String productName,
                       Integer productPrice, String productTitle, String headImage,
                       Integer currentBuyCount, String buyer, String userId,
                       Integer productPeriod, String buyDate, Integer buyCount,
                       String productStyle) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productTitle = productTitle;
        this.headImage = headImage;
        this.currentBuyCount = currentBuyCount;
        this.buyer = buyer;
        this.userId = userId;
        this.productPeriod = productPeriod;
        this.buyDate = buyDate;
        this.buyCount = buyCount;
        this.productStyle = productStyle;
    }


    public Integer getProductId() {
        return productId;
    }


    public void setProductId(Integer productId) {
        this.productId = productId;
    }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public Integer getProductPrice() {
        return productPrice;
    }


    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }


    public String getProductTitle() {
        return productTitle;
    }


    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }


    public String getHeadImage() {
        return headImage;
    }


    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }


    public Integer getCurrentBuyCount() {
        return currentBuyCount;
    }


    public void setCurrentBuyCount(Integer currentBuyCount) {
        this.currentBuyCount = currentBuyCount;
    }


    public String getBuyer() {
        return buyer;
    }


    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }


    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Integer getProductPeriod() {
        return productPeriod;
    }


    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }


    public String getBuyDate() {
        return buyDate;
    }


    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }


    public Integer getBuyCount() {
        return buyCount;
    }


    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }


    public String getProductStyle() {
        return productStyle;
    }


    public void setProductStyle(String productStyle) {
        this.productStyle = productStyle;
    }


}
