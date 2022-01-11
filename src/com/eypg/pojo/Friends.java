package com.eypg.pojo;

/**
 * Friends entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Friends implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer friendsId;
    private Integer userId;

    // Constructors

    /**
     * default constructor
     */
    public Friends() {
    }

    /**
     * full constructor
     */
    public Friends(Integer friendsId, Integer userId) {
        this.friendsId = friendsId;
        this.userId = userId;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFriendsId() {
        return this.friendsId;
    }

    public void setFriendsId(Integer friendsId) {
        this.friendsId = friendsId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}