package com.eypg.pojo;

/**
 * Spellbuyrecord entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Spellbuyrecord implements java.io.Serializable {

    // Fields

    private Integer spellbuyRecordId;
    private Integer fkSpellbuyProductId;
    private Integer buyer;
    private Integer buyPrice;
    private String buyDate;
    private String spRandomNo;
    private String spWinningStatus;
    private String buyStatus;
    private String attribute66;
    private String attribute67;

    // Constructors

    /**
     * default constructor
     */
    public Spellbuyrecord() {
    }

    /**
     * minimal constructor
     */
    public Spellbuyrecord(Integer fkSpellbuyProductId, Integer buyer,
                          Integer buyPrice, String buyDate, String spRandomNo,
                          String spWinningStatus, String buyStatus) {
        this.fkSpellbuyProductId = fkSpellbuyProductId;
        this.buyer = buyer;
        this.buyPrice = buyPrice;
        this.buyDate = buyDate;
        this.spRandomNo = spRandomNo;
        this.spWinningStatus = spWinningStatus;
        this.buyStatus = buyStatus;
    }

    /**
     * full constructor
     */
    public Spellbuyrecord(Integer fkSpellbuyProductId, Integer buyer,
                          Integer buyPrice, String buyDate, String spRandomNo,
                          String spWinningStatus, String buyStatus, String attribute66,
                          String attribute67) {
        this.fkSpellbuyProductId = fkSpellbuyProductId;
        this.buyer = buyer;
        this.buyPrice = buyPrice;
        this.buyDate = buyDate;
        this.spRandomNo = spRandomNo;
        this.spWinningStatus = spWinningStatus;
        this.buyStatus = buyStatus;
        this.attribute66 = attribute66;
        this.attribute67 = attribute67;
    }

    // Property accessors

    public Integer getSpellbuyRecordId() {
        return this.spellbuyRecordId;
    }

    public void setSpellbuyRecordId(Integer spellbuyRecordId) {
        this.spellbuyRecordId = spellbuyRecordId;
    }

    public Integer getFkSpellbuyProductId() {
        return this.fkSpellbuyProductId;
    }

    public void setFkSpellbuyProductId(Integer fkSpellbuyProductId) {
        this.fkSpellbuyProductId = fkSpellbuyProductId;
    }

    public Integer getBuyer() {
        return this.buyer;
    }

    public void setBuyer(Integer buyer) {
        this.buyer = buyer;
    }

    public Integer getBuyPrice() {
        return this.buyPrice;
    }

    public void setBuyPrice(Integer buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getBuyDate() {
        return this.buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getSpRandomNo() {
        return this.spRandomNo;
    }

    public void setSpRandomNo(String spRandomNo) {
        this.spRandomNo = spRandomNo;
    }

    public String getSpWinningStatus() {
        return this.spWinningStatus;
    }

    public void setSpWinningStatus(String spWinningStatus) {
        this.spWinningStatus = spWinningStatus;
    }

    public String getBuyStatus() {
        return this.buyStatus;
    }

    public void setBuyStatus(String buyStatus) {
        this.buyStatus = buyStatus;
    }

    public String getAttribute66() {
        return this.attribute66;
    }

    public void setAttribute66(String attribute66) {
        this.attribute66 = attribute66;
    }

    public String getAttribute67() {
        return this.attribute67;
    }

    public void setAttribute67(String attribute67) {
        this.attribute67 = attribute67;
    }

}