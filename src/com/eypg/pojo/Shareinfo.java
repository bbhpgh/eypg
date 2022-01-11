package com.eypg.pojo;

/**
 * Shareinfo entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Shareinfo implements java.io.Serializable {

    // Fields

    private Integer uid;
    private Integer productId;
    private String shareTitle;
    private String shareContent;
    private Integer upCount;
    private Integer replyCount;
    private Integer reward;
    private String shareDate;
    private Integer userId;
    private Integer status = 0;

    // Constructors


    /**
     * default constructor
     */
    public Shareinfo() {
    }

    public Shareinfo(Integer productId, String shareTitle, String shareContent,
                     Integer upCount, Integer replyCount, Integer reward,
                     String shareDate, Integer userId, Integer status) {
        super();
        this.productId = productId;
        this.shareTitle = shareTitle;
        this.shareContent = shareContent;
        this.upCount = upCount;
        this.replyCount = replyCount;
        this.reward = reward;
        this.shareDate = shareDate;
        this.userId = userId;
        this.status = status;
    }


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public Integer getUpCount() {
        return upCount;
    }

    public void setUpCount(Integer upCount) {
        this.upCount = upCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public String getShareDate() {
        return shareDate;
    }

    public void setShareDate(String shareDate) {
        this.shareDate = shareDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

}