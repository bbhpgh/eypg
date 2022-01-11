package com.eypg.pojo;

/**
 * Lotteryproductutil entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Lotteryproductutil implements java.io.Serializable {

    // Fields

    private Integer lotteryId;
    private Integer lotteryProductId;
    private String lotteryProductName;
    private String lotteryProductTitle;
    private Integer lotteryProductPrice;
    private Integer lotteryProductPeriod;
    private String lotteryProductImg;
    private String lotteryProductEndDate;

    // Constructors

    /**
     * default constructor
     */
    public Lotteryproductutil() {
    }

    /**
     * full constructor
     */
    public Lotteryproductutil(Integer lotteryProductId,
                              String lotteryProductName, String lotteryProductTitle,
                              Integer lotteryProductPrice, Integer lotteryProductPeriod,
                              String lotteryProductImg, String lotteryProductEndDate) {
        this.lotteryProductId = lotteryProductId;
        this.lotteryProductName = lotteryProductName;
        this.lotteryProductTitle = lotteryProductTitle;
        this.lotteryProductPrice = lotteryProductPrice;
        this.lotteryProductPeriod = lotteryProductPeriod;
        this.lotteryProductImg = lotteryProductImg;
        this.lotteryProductEndDate = lotteryProductEndDate;
    }

    // Property accessors

    public Integer getLotteryId() {
        return this.lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    public Integer getLotteryProductId() {
        return this.lotteryProductId;
    }

    public void setLotteryProductId(Integer lotteryProductId) {
        this.lotteryProductId = lotteryProductId;
    }

    public String getLotteryProductName() {
        return this.lotteryProductName;
    }

    public void setLotteryProductName(String lotteryProductName) {
        this.lotteryProductName = lotteryProductName;
    }

    public String getLotteryProductTitle() {
        return this.lotteryProductTitle;
    }

    public void setLotteryProductTitle(String lotteryProductTitle) {
        this.lotteryProductTitle = lotteryProductTitle;
    }

    public Integer getLotteryProductPrice() {
        return this.lotteryProductPrice;
    }

    public void setLotteryProductPrice(Integer lotteryProductPrice) {
        this.lotteryProductPrice = lotteryProductPrice;
    }

    public Integer getLotteryProductPeriod() {
        return this.lotteryProductPeriod;
    }

    public void setLotteryProductPeriod(Integer lotteryProductPeriod) {
        this.lotteryProductPeriod = lotteryProductPeriod;
    }

    public String getLotteryProductImg() {
        return this.lotteryProductImg;
    }

    public void setLotteryProductImg(String lotteryProductImg) {
        this.lotteryProductImg = lotteryProductImg;
    }

    public String getLotteryProductEndDate() {
        return this.lotteryProductEndDate;
    }

    public void setLotteryProductEndDate(String lotteryProductEndDate) {
        this.lotteryProductEndDate = lotteryProductEndDate;
    }

}