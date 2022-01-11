package com.eypg.pojo;

/**
 * Spellbuyproduct entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Spellbuyproduct implements java.io.Serializable {

    // Fields

    private Integer spellbuyProductId;
    private Integer fkProductId;
    private String spellbuyStartDate;
    private String spellbuyEndDate;
    private Integer spellbuyCount;
    private Integer spellbuyPrice;
    private Integer productPeriod;
    private Integer spStatus;
    private Integer spellbuyType;
    private String attribute64;
    private String attribute65;

    // Constructors


    public Spellbuyproduct(Integer spellbuyProductId, Integer fkProductId,
                           String spellbuyStartDate, String spellbuyEndDate,
                           Integer spellbuyCount, Integer spellbuyPrice,
                           Integer productPeriod, Integer spStatus, Integer spellbuyType,
                           String attribute64, String attribute65) {
        super();
        this.spellbuyProductId = spellbuyProductId;
        this.fkProductId = fkProductId;
        this.spellbuyStartDate = spellbuyStartDate;
        this.spellbuyEndDate = spellbuyEndDate;
        this.spellbuyCount = spellbuyCount;
        this.spellbuyPrice = spellbuyPrice;
        this.productPeriod = productPeriod;
        this.spStatus = spStatus;
        this.spellbuyType = spellbuyType;
        this.attribute64 = attribute64;
        this.attribute65 = attribute65;
    }

    /**
     * default constructor
     */
    public Spellbuyproduct() {
    }

    /**
     * minimal constructor
     */
    public Spellbuyproduct(Integer fkProductId, String spellbuyStartDate,
                           String spellbuyEndDate, Integer spellbuyCount, Integer spellbuyPrice) {
        this.fkProductId = fkProductId;
        this.spellbuyStartDate = spellbuyStartDate;
        this.spellbuyEndDate = spellbuyEndDate;
        this.spellbuyCount = spellbuyCount;
        this.spellbuyPrice = spellbuyPrice;
    }

    /**
     * full constructor
     */


    // Property accessors
    public Integer getSpellbuyProductId() {
        return this.spellbuyProductId;
    }

    public void setSpellbuyProductId(Integer spellbuyProductId) {
        this.spellbuyProductId = spellbuyProductId;
    }

    public Integer getFkProductId() {
        return this.fkProductId;
    }

    public void setFkProductId(Integer fkProductId) {
        this.fkProductId = fkProductId;
    }

    public String getSpellbuyStartDate() {
        return this.spellbuyStartDate;
    }

    public void setSpellbuyStartDate(String spellbuyStartDate) {
        this.spellbuyStartDate = spellbuyStartDate;
    }

    public String getSpellbuyEndDate() {
        return this.spellbuyEndDate;
    }

    public void setSpellbuyEndDate(String spellbuyEndDate) {
        this.spellbuyEndDate = spellbuyEndDate;
    }

    public Integer getSpellbuyCount() {
        return this.spellbuyCount;
    }

    public void setSpellbuyCount(Integer spellbuyCount) {
        this.spellbuyCount = spellbuyCount;
    }

    public Integer getSpellbuyPrice() {
        return this.spellbuyPrice;
    }

    public void setSpellbuyPrice(Integer spellbuyPrice) {
        this.spellbuyPrice = spellbuyPrice;
    }

    public String getAttribute64() {
        return this.attribute64;
    }

    public void setAttribute64(String attribute64) {
        this.attribute64 = attribute64;
    }

    public String getAttribute65() {
        return this.attribute65;
    }

    public void setAttribute65(String attribute65) {
        this.attribute65 = attribute65;
    }

    public Integer getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }

    public Integer getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(Integer spStatus) {
        this.spStatus = spStatus;
    }

    public Integer getSpellbuyType() {
        return spellbuyType;
    }

    public void setSpellbuyType(Integer spellbuyType) {
        this.spellbuyType = spellbuyType;
    }

}