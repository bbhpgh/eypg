package com.eypg.pojo;

/**
 * Orderdetailaddress entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Orderdetailaddress implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer orderDetailId;
    private String consignee;
    private String phone;
    private String address;
    private String postDate;
    private String orderRemarks;
    private String expressNo;
    private String expressCompany;
    private String deliverTime;
    private String deliverRemarks;

    // Constructors

    /**
     * default constructor
     */
    public Orderdetailaddress() {
    }

    /**
     * full constructor
     */
    public Orderdetailaddress(Integer orderDetailId, String consignee,
                              String phone, String address, String postDate, String orderRemarks,
                              String expressNo, String expressCompany, String deliverTime,
                              String deliverRemarks) {
        this.orderDetailId = orderDetailId;
        this.consignee = consignee;
        this.phone = phone;
        this.address = address;
        this.postDate = postDate;
        this.orderRemarks = orderRemarks;
        this.expressNo = expressNo;
        this.expressCompany = expressCompany;
        this.deliverTime = deliverTime;
        this.deliverRemarks = deliverRemarks;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderDetailId() {
        return this.orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getConsignee() {
        return this.consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostDate() {
        return this.postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getOrderRemarks() {
        return this.orderRemarks;
    }

    public void setOrderRemarks(String orderRemarks) {
        this.orderRemarks = orderRemarks;
    }

    public String getExpressNo() {
        return this.expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressCompany() {
        return this.expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getDeliverTime() {
        return this.deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getDeliverRemarks() {
        return this.deliverRemarks;
    }

    public void setDeliverRemarks(String deliverRemarks) {
        this.deliverRemarks = deliverRemarks;
    }

}