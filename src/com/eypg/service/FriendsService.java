package com.eypg.service;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Friends;

public interface FriendsService extends TService<Friends, Integer> {

    /**
     * 好友
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination getFriends(String userId, int pageNo, int pageSize);

}
