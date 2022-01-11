package com.eypg.pojo;

public class DetailBybuyerJSON {

    private String userId;
    private String userName;
    private String buyTime;
    private String faceImg;
    private String randomNumber;
    private String buyCount;


    public DetailBybuyerJSON() {
        super();
    }

    public DetailBybuyerJSON(String userId, String userName, String buyTime,
                             String faceImg, String randomNumber, String buyCount) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.buyTime = buyTime;
        this.faceImg = faceImg;
        this.randomNumber = randomNumber;
        this.buyCount = buyCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount;
    }


}
