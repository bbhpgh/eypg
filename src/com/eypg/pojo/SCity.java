package com.eypg.pojo;

/**
 * SCity entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class SCity implements java.io.Serializable {

    // Fields

    private Integer cid;
    private String cname;
    private String cpostcode;
    private Integer pid;

    // Constructors

    /**
     * default constructor
     */
    public SCity() {
    }

    /**
     * full constructor
     */
    public SCity(String cname, String cpostcode, Integer pid) {
        this.cname = cname;
        this.cpostcode = cpostcode;
        this.pid = pid;
    }

    // Property accessors

    public Integer getCid() {
        return this.cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCpostcode() {
        return this.cpostcode;
    }

    public void setCpostcode(String cpostcode) {
        this.cpostcode = cpostcode;
    }

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

}