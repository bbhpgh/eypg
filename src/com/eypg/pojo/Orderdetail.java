package com.eypg.pojo;

/**
 * Orderdetail entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Orderdetail implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer orderDetailId;
    private String date;
    private String detailText;
    private String userName;
    private Integer addressId;

    // Constructors

    /**
     * default constructor
     */
    public Orderdetail() {
    }

    /**
     * full constructor
     */
    public Orderdetail(Integer orderDetailId, String date, String detailText,
                       String userName, Integer addressId) {
        this.orderDetailId = orderDetailId;
        this.date = date;
        this.detailText = detailText;
        this.userName = userName;
        this.addressId = addressId;
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

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetailText() {
        return this.detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

}