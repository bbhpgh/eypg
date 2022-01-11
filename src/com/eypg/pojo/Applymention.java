package com.eypg.pojo;

/**
 * Applymention entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Applymention implements java.io.Serializable {

    // Fields

    private Integer id;
    private String date;
    private Double money;
    private Double fee;
    private String bankName;
    private String bankUser;
    private String bankSubbranch;
    private String bankNo;
    private String phone;
    private Integer userId;
    private String status;

    // Constructors

    /**
     * @param date
     * @param money
     * @param fee
     * @param bankName
     * @param bankUser
     * @param bankSubbranch
     * @param bankNo
     * @param phone
     * @param userId
     * @param status
     */
    public Applymention(String date, Double money, Double fee, String bankName,
                        String bankUser, String bankSubbranch, String bankNo, String phone,
                        Integer userId, String status) {
        super();
        this.date = date;
        this.money = money;
        this.fee = fee;
        this.bankName = bankName;
        this.bankUser = bankUser;
        this.bankSubbranch = bankSubbranch;
        this.bankNo = bankNo;
        this.phone = phone;
        this.userId = userId;
        this.status = status;
    }

    /**
     * default constructor
     */
    public Applymention() {
    }

    /**
     * full constructor
     */


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

    public Double getMoney() {
        return this.money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getFee() {
        return this.fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankUser() {
        return this.bankUser;
    }

    public void setBankUser(String bankUser) {
        this.bankUser = bankUser;
    }

    public String getBankSubbranch() {
        return this.bankSubbranch;
    }

    public void setBankSubbranch(String bankSubbranch) {
        this.bankSubbranch = bankSubbranch;
    }

    public String getBankNo() {
        return this.bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}