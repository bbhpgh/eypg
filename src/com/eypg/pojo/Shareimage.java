package com.eypg.pojo;

/**
 * Shareimage entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Shareimage implements java.io.Serializable {

    // Fields

    private Integer uid;
    private Integer shareInfoId;
    private String images;


    public Shareimage() {
        super();
    }


    public Shareimage(Integer uid, Integer shareInfoId, String images) {
        super();
        this.uid = uid;
        this.shareInfoId = shareInfoId;
        this.images = images;
    }


    public Integer getUid() {
        return uid;
    }


    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public Integer getShareInfoId() {
        return shareInfoId;
    }


    public void setShareInfoId(Integer shareInfoId) {
        this.shareInfoId = shareInfoId;
    }


    public String getImages() {
        return images;
    }


    public void setImages(String images) {
        this.images = images;
    }


}