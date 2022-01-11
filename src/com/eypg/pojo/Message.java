package com.eypg.pojo;

/**
 * Message entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer userId;
    private Integer sender;
    private String message;
    private String date;
    private Integer type;
    private Integer status;

    // Constructors

    /**
     * default constructor
     */
    public Message() {
    }

    /**
     * full constructor
     */
    public Message(Integer userId, Integer sender, String message, String date,
                   Integer type, Integer status) {
        this.userId = userId;
        this.sender = sender;
        this.message = message;
        this.date = date;
        this.type = type;
        this.status = status;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSender() {
        return this.sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}