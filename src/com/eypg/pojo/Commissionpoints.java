package com.eypg.pojo;

/**
 * Commissionquery entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Commissionpoints implements java.io.Serializable {

    private static final long serialVersionUID = 5429099233990126477L;

    private Integer id;
    private Integer toUserId;
    private String date;
    private String pay;
    private String detailed;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }


}