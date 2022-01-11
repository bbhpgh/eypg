package com.eypg.pojo;

public class ProductCart implements java.io.Serializable {

    private static final long serialVersionUID = 7721286969488307469L;
    private Integer productId;
    private String productName;
    private String productTitle;
    private Integer productPrice;
    private String headImage;
    private Integer productCount;
    private Integer count;
    private Integer moneyCount;
    private Integer currentBuyCount;
    private Integer productPeriod;

    public ProductCart() {
        super();
    }

    public ProductCart(Integer productId, String productName,
                       String productTitle, Integer productPrice, String headImage,
                       Integer productCount, Integer count, Integer moneyCount,
                       Integer currentBuyCount, Integer productPeriod) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.headImage = headImage;
        this.productCount = productCount;
        this.count = count;
        this.moneyCount = moneyCount;
        this.currentBuyCount = currentBuyCount;
        this.productPeriod = productPeriod;
    }

    public Integer getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }

    public Integer getCurrentBuyCount() {
        return currentBuyCount;
    }

    public void setCurrentBuyCount(Integer currentBuyCount) {
        this.currentBuyCount = currentBuyCount;
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

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(Integer moneyCount) {
        this.moneyCount = moneyCount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


}
