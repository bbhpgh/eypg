package com.eypg.pojo;

import java.io.Serializable;

public class LotteryDetailJSON implements Serializable {

    private Integer productId;
    private String productName;
    private String productTitle;
    private Integer productPeriod; //商品期数
    private String userName;
    private Integer userId;
    private String buyTime; //购买时间
    private Integer buyCount; //购买总数
    private String buyDate;//购买日期
    private String dateSum;//日期和

    public LotteryDetailJSON() {
        super();
    }

    public LotteryDetailJSON(Integer productId, String productName,
                             String productTitle, Integer productPeriod, String userName,
                             Integer userId, String buyTime, Integer buyCount, String buyDate,
                             String dateSum) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productTitle = productTitle;
        this.productPeriod = productPeriod;
        this.userName = userName;
        this.userId = userId;
        this.buyTime = buyTime;
        this.buyCount = buyCount;
        this.buyDate = buyDate;
        this.dateSum = dateSum;
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

    public Integer getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getDateSum() {
        return dateSum;
    }

    public void setDateSum(String dateSum) {
        this.dateSum = dateSum;
    }


}
