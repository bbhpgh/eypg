package com.eypg.pojo;

/**
 * Product entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

    // Fields

    private Integer productId;
    private String productName;
    private Integer productPrice;
    private String productRealPrice;
    private String productTitle;
    private String productDetail;
    private Integer productType;
    private String headImage;
    private Integer status;
    private String style;
    private String attribute71;

    // Constructors

    /**
     * default constructor
     */
    public Product() {
    }

    /**
     * minimal constructor
     */
    public Product(String productName, Integer productPrice,
                   String productTitle, String productDetail, Integer productType,
                   String headImage) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productTitle = productTitle;
        this.productDetail = productDetail;
        this.productType = productType;
        this.headImage = headImage;
    }

    /**
     * full constructor
     */
    public Product(String productName, Integer productPrice,
                   String productRealPrice, String productTitle, String productDetail,
                   Integer productType, String headImage, String attribute71) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productRealPrice = productRealPrice;
        this.productTitle = productTitle;
        this.productDetail = productDetail;
        this.productType = productType;
        this.headImage = headImage;
        this.attribute71 = attribute71;
    }

    // Property accessors

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductRealPrice() {
        return this.productRealPrice;
    }

    public void setProductRealPrice(String productRealPrice) {
        this.productRealPrice = productRealPrice;
    }

    public String getProductTitle() {
        return this.productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDetail() {
        return this.productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public Integer getProductType() {
        return this.productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getHeadImage() {
        return this.headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getAttribute71() {
        return this.attribute71;
    }

    public void setAttribute71(String attribute71) {
        this.attribute71 = attribute71;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }


}