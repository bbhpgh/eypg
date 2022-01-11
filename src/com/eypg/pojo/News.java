package com.eypg.pojo;

/**
 * News entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class News implements java.io.Serializable {

    // Fields

    private Integer newsId;
    private String title;
    private String postDate;
    private String content;

    // Constructors

    /**
     * default constructor
     */
    public News() {
    }

    /**
     * full constructor
     */
    public News(String title, String postDate, String content) {
        this.title = title;
        this.postDate = postDate;
        this.content = content;
    }

    // Property accessors


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostDate() {
        return this.postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

}