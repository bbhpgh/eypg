package com.eypg.pojo;

/**
 * SDistrict entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class SDistrict implements java.io.Serializable {

    // Fields

    private Integer did;
    private String dname;
    private Integer cid;

    // Constructors

    /**
     * default constructor
     */
    public SDistrict() {
    }

    /**
     * full constructor
     */
    public SDistrict(String dname, Integer cid) {
        this.dname = dname;
        this.cid = cid;
    }

    // Property accessors

    public Integer getDid() {
        return this.did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return this.dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getCid() {
        return this.cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

}