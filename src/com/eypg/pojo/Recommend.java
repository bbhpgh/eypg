package com.eypg.pojo;

/**
 * Recommend entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Recommend implements java.io.Serializable {

    // Fields

    private Integer recommendId;
    private Integer spellbuyProductId;
    private String date;

    public Recommend() {
        super();
    }

    public Recommend(Integer recommendId, Integer spellbuyProductId, String date) {
        super();
        this.recommendId = recommendId;
        this.spellbuyProductId = spellbuyProductId;
        this.date = date;
    }

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public Integer getSpellbuyProductId() {
        return spellbuyProductId;
    }

    public void setSpellbuyProductId(Integer spellbuyProductId) {
        this.spellbuyProductId = spellbuyProductId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}