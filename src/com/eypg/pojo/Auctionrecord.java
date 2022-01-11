package com.eypg.pojo;

/**
 * Auctionrecord entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Auctionrecord implements java.io.Serializable {

    // Fields

    private Integer auctionRecordId;
    private Integer fkAuctionProductId;
    private Integer bidder;
    private Integer bidderPrice;
    private String bidderDate;
    private String arRandomNo;
    private String arWinningStatus;
    private String bidderStatus;
    private String attribute68;
    private String attribute69;

    // Constructors

    /**
     * default constructor
     */
    public Auctionrecord() {
    }

    /**
     * minimal constructor
     */
    public Auctionrecord(Integer fkAuctionProductId, Integer bidder,
                         Integer bidderPrice, String bidderDate, String arRandomNo,
                         String arWinningStatus, String bidderStatus) {
        this.fkAuctionProductId = fkAuctionProductId;
        this.bidder = bidder;
        this.bidderPrice = bidderPrice;
        this.bidderDate = bidderDate;
        this.arRandomNo = arRandomNo;
        this.arWinningStatus = arWinningStatus;
        this.bidderStatus = bidderStatus;
    }

    /**
     * full constructor
     */
    public Auctionrecord(Integer fkAuctionProductId, Integer bidder,
                         Integer bidderPrice, String bidderDate, String arRandomNo,
                         String arWinningStatus, String bidderStatus, String attribute68,
                         String attribute69) {
        this.fkAuctionProductId = fkAuctionProductId;
        this.bidder = bidder;
        this.bidderPrice = bidderPrice;
        this.bidderDate = bidderDate;
        this.arRandomNo = arRandomNo;
        this.arWinningStatus = arWinningStatus;
        this.bidderStatus = bidderStatus;
        this.attribute68 = attribute68;
        this.attribute69 = attribute69;
    }

    // Property accessors

    public Integer getAuctionRecordId() {
        return this.auctionRecordId;
    }

    public void setAuctionRecordId(Integer auctionRecordId) {
        this.auctionRecordId = auctionRecordId;
    }

    public Integer getFkAuctionProductId() {
        return this.fkAuctionProductId;
    }

    public void setFkAuctionProductId(Integer fkAuctionProductId) {
        this.fkAuctionProductId = fkAuctionProductId;
    }

    public Integer getBidder() {
        return this.bidder;
    }

    public void setBidder(Integer bidder) {
        this.bidder = bidder;
    }

    public Integer getBidderPrice() {
        return this.bidderPrice;
    }

    public void setBidderPrice(Integer bidderPrice) {
        this.bidderPrice = bidderPrice;
    }

    public String getBidderDate() {
        return this.bidderDate;
    }

    public void setBidderDate(String bidderDate) {
        this.bidderDate = bidderDate;
    }

    public String getArRandomNo() {
        return this.arRandomNo;
    }

    public void setArRandomNo(String arRandomNo) {
        this.arRandomNo = arRandomNo;
    }

    public String getArWinningStatus() {
        return this.arWinningStatus;
    }

    public void setArWinningStatus(String arWinningStatus) {
        this.arWinningStatus = arWinningStatus;
    }

    public String getBidderStatus() {
        return this.bidderStatus;
    }

    public void setBidderStatus(String bidderStatus) {
        this.bidderStatus = bidderStatus;
    }

    public String getAttribute68() {
        return this.attribute68;
    }

    public void setAttribute68(String attribute68) {
        this.attribute68 = attribute68;
    }

    public String getAttribute69() {
        return this.attribute69;
    }

    public void setAttribute69(String attribute69) {
        this.attribute69 = attribute69;
    }

}