package com.eypg.pojo;

import java.io.Serializable;

public class BuyHistoryJSON implements Serializable {

    private Integer productId;
    private String productName;
    private String productTitle;
    private String productImg;
    private Integer productPeriod; //商品期数
    private Integer buyStatus;//商品状态
    private String winUser; //获得者
    private String buyTime; //购买时间
    private Long buyCount; //购买总数
    private Integer historyId; //购买记录ID
    private Integer winUserId; //获得者ID
    private Integer winId; //幸运编号
    private String winDate; //揭晓时间
    private Integer spellbuyCount; //当前购买数
    private Integer productPrice;//商品价格

    public BuyHistoryJSON() {
        super();
    }


    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }


    public Integer getSpellbuyCount() {
        return spellbuyCount;
    }

    public void setSpellbuyCount(Integer spellbuyCount) {
        this.spellbuyCount = spellbuyCount;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getWinUserId() {
        return winUserId;
    }

    public void setWinUserId(Integer winUserId) {
        this.winUserId = winUserId;
    }

    public Integer getWinId() {
        return winId;
    }

    public void setWinId(Integer winId) {
        this.winId = winId;
    }

    public String getWinDate() {
        return winDate;
    }

    public void setWinDate(String winDate) {
        this.winDate = winDate;
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

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Integer getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }

    public Integer getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(Integer buyStatus) {
        this.buyStatus = buyStatus;
    }

    public String getWinUser() {
        return winUser;
    }

    public void setWinUser(String winUser) {
        this.winUser = winUser;
    }

    public Long getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Object buyCount) {
        this.buyCount = Long.parseLong((buyCount.toString()));
    }


    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

}
