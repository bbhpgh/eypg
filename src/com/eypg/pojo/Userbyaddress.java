package com.eypg.pojo;

/**
 * Userbyaddress entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Userbyaddress implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer userId;
    private String province;
    private String city;
    private String district;
    private String address;
    private Integer zipCode;
    private String consignee;
    private String phone;
    private Integer status = 1;


    /**
     *
     */
    public Userbyaddress() {
        super();
    }

    /**
     * @param id
     * @param userId
     * @param province
     * @param city
     * @param district
     * @param address
     * @param zipCode
     * @param consignee
     * @param phone
     * @param status
     */
    public Userbyaddress(Integer id, Integer userId, String province,
                         String city, String district, String address, Integer zipCode,
                         String consignee, String phone, Integer status) {
        super();
        this.id = id;
        this.userId = userId;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.zipCode = zipCode;
        this.consignee = consignee;
        this.phone = phone;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}