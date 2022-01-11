package com.eypg.pojo;

/**
 * SProvince entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class SProvince implements java.io.Serializable {

    // Fields

    private Integer pid;
    private String pname;

    // Constructors

    /**
     * default constructor
     */
    public SProvince() {
    }

    /**
     * full constructor
     */
    public SProvince(String pname) {
        this.pname = pname;
    }

    // Property accessors

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return this.pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

}