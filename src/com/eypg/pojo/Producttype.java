package com.eypg.pojo;

/**
 * Producttype entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Producttype implements java.io.Serializable {

    // Fields

    private Integer typeId;
    private String typeName;
    private String ftypeId;
    private String stypeId;
    private String attribute70;

    // Constructors

    /**
     * default constructor
     */
    public Producttype() {
    }

    /**
     * minimal constructor
     */
    public Producttype(String typeName, String ftypeId, String stypeId) {
        this.typeName = typeName;
        this.ftypeId = ftypeId;
        this.stypeId = stypeId;
    }

    /**
     * full constructor
     */
    public Producttype(String typeName, String ftypeId, String stypeId,
                       String attribute70) {
        this.typeName = typeName;
        this.ftypeId = ftypeId;
        this.stypeId = stypeId;
        this.attribute70 = attribute70;
    }

    // Property accessors

    public Integer getTypeId() {
        return this.typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getFtypeId() {
        return this.ftypeId;
    }

    public void setFtypeId(String ftypeId) {
        this.ftypeId = ftypeId;
    }

    public String getStypeId() {
        return this.stypeId;
    }

    public void setStypeId(String stypeId) {
        this.stypeId = stypeId;
    }

    public String getAttribute70() {
        return this.attribute70;
    }

    public void setAttribute70(String attribute70) {
        this.attribute70 = attribute70;
    }

}