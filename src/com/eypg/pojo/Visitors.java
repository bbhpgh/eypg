package com.eypg.pojo;

/**
 * Visitors entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Visitors implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer uid;
    private Integer visitorsId;
    private String date;
    private String address;

    // Constructors

    /**
     * default constructor
     */
    public Visitors() {
    }


    public Visitors(Integer id, Integer uid, Integer visitorsId, String date,
                    String address) {
        super();
        this.id = id;
        this.uid = uid;
        this.visitorsId = visitorsId;
        this.date = date;
        this.address = address;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getVisitorsId() {
        return visitorsId;
    }

    public void setVisitorsId(Integer visitorsId) {
        this.visitorsId = visitorsId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}