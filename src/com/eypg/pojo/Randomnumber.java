package com.eypg.pojo;

/**
 * Randomnumber entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Randomnumber implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer spellbuyrecordId;
    private Integer userId;
    private Integer productId;
    private String randomNumber;
    private String buyDate;

    // Constructors

    public Randomnumber(Integer id, Integer spellbuyrecordId, Integer userId,
                        Integer productId, String randomNumber, String buyDate) {
        super();
        this.id = id;
        this.spellbuyrecordId = spellbuyrecordId;
        this.userId = userId;
        this.productId = productId;
        this.randomNumber = randomNumber;
        this.buyDate = buyDate;
    }

    /**
     * default constructor
     */
    public Randomnumber() {
    }

    /**
     * full constructor
     */

    // Property accessors
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpellbuyrecordId() {
        return this.spellbuyrecordId;
    }

    public void setSpellbuyrecordId(Integer spellbuyrecordId) {
        this.spellbuyrecordId = spellbuyrecordId;
    }

    public String getRandomNumber() {
        return this.randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}