package com.eypg.pojo;

/**
 * Commissionquery entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Commissionquery implements java.io.Serializable {

    // Fields

    private Integer id;
    private String date;
    private String description;
    private Double buyMoney;
    private Double commission;
    private Integer toUserId;
    private Integer invitedId;

    // Constructors

    /**
     * default constructor
     */
    public Commissionquery() {
    }

    /**
     * full constructor
     */
    public Commissionquery(String date, String description, Double buyMoney,
                           Double commission, Integer toUserId, Integer invitedId) {
        this.date = date;
        this.description = description;
        this.buyMoney = buyMoney;
        this.commission = commission;
        this.toUserId = toUserId;
        this.invitedId = invitedId;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBuyMoney() {
        return this.buyMoney;
    }

    public void setBuyMoney(Double buyMoney) {
        this.buyMoney = buyMoney;
    }

    public Double getCommission() {
        return this.commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getInvitedId() {
        return this.invitedId;
    }

    public void setInvitedId(Integer invitedId) {
        this.invitedId = invitedId;
    }

}