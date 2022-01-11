package com.eypg.pojo;

/**
 * Consumetable entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Consumetable implements java.io.Serializable {

    // Fields

    private Integer id;
    private Double money;//金额
    private String date;//交易时间
    private Integer buyCount;//拍购次数
    private Integer userId;//用户ID
    private String transactionId;//财付通订单号
    private String outTradeNo;//商户订单号
    private String interfaceType;//接口类型

    // Constructors

    /**
     * default constructor
     */
    public Consumetable() {
    }

    /**
     * minimal constructor
     */
    public Consumetable(Double money, String date,
                        Integer userId) {
        this.money = money;
        this.date = date;
        this.userId = userId;
    }

    /**
     * full constructor
     */
    public Consumetable(Double money,
                        String date, Integer buyCount, Integer userId,
                        String transactionId, String outTradeNo, String interfaceType) {
        this.money = money;
        this.date = date;
        this.buyCount = buyCount;
        this.userId = userId;
        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;
        this.interfaceType = interfaceType;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMoney() {
        return this.money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getBuyCount() {
        return this.buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return this.outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getInterfaceType() {
        return this.interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

}