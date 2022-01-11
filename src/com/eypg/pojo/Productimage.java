package com.eypg.pojo;

/**
 * Productimage entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Productimage implements java.io.Serializable {

    // Fields

    private Integer productImageId;
    private Integer piProductId;
    private String image;
    private String imageType;
    private String attribute75;

    // Constructors

    /**
     * default constructor
     */
    public Productimage() {
    }

    // Property accessors

    public Productimage(Integer productImageId, Integer piProductId,
                        String image, String imageType, String attribute75) {
        super();
        this.productImageId = productImageId;
        this.piProductId = piProductId;
        this.image = image;
        this.imageType = imageType;
        this.attribute75 = attribute75;
    }

    public Integer getProductImageId() {
        return this.productImageId;
    }

    public void setProductImageId(Integer productImageId) {
        this.productImageId = productImageId;
    }

    public Integer getPiProductId() {
        return this.piProductId;
    }

    public void setPiProductId(Integer piProductId) {
        this.piProductId = piProductId;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAttribute75() {
        return this.attribute75;
    }

    public void setAttribute75(String attribute75) {
        this.attribute75 = attribute75;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

}