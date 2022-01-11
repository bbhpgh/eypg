package com.eypg.pojo;

/**
 * Consumerdetail entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Consumerdetail implements java.io.Serializable {

    // Fields

    private Integer id;
    private String consumetableId; //消费记录ID
    private Integer productId;//商品ID
    private Integer buyCount;//拍购次数
    private Double buyMoney;//金额
    private String productTitle;//商品标题
    private String productName;//商品名称
    private Integer productPeriod;//商品期数

    // Constructors

    /**
     * default constructor
     */
    public Consumerdetail() {
    }

    /**
     * minimal constructor
     */
    public Consumerdetail(String consumetableId, Integer productId,
                          Integer buyCount, Double buyMoney, Integer productPeriod) {
        this.consumetableId = consumetableId;
        this.productId = productId;
        this.buyCount = buyCount;
        this.buyMoney = buyMoney;
        this.productPeriod = productPeriod;
    }

    /**
     * full constructor
     */
    public Consumerdetail(String consumetableId, Integer productId,
                          Integer buyCount, Double buyMoney, String productTitle,
                          String productName, Integer productPeriod) {
        this.consumetableId = consumetableId;
        this.productId = productId;
        this.buyCount = buyCount;
        this.buyMoney = buyMoney;
        this.productTitle = productTitle;
        this.productName = productName;
        this.productPeriod = productPeriod;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsumetableId() {
        return consumetableId;
    }

    public void setConsumetableId(String consumetableId) {
        this.consumetableId = consumetableId;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBuyCount() {
        return this.buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Double getBuyMoney() {
        return this.buyMoney;
    }

    public void setBuyMoney(Double buyMoney) {
        this.buyMoney = buyMoney;
    }

    public String getProductTitle() {
        return this.productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPeriod() {
        return this.productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }

}