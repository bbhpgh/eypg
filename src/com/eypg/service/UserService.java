package com.eypg.service;

import java.util.List;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Cardpassword;
import com.eypg.pojo.User;
import com.eypg.pojo.Userbyaddress;

public interface UserService extends TService<User, Integer> {
    public User phoneLogin(String phone, String userPwd);

    public User mailLogin(String mail, String userPwd);

    public User userByName(String userName);

    public User userByIsUserName(String userName);

    public User isUserName(String userName, String userId);

    public List getUserbyaddress(String userId);

    public void addAddress(Userbyaddress userbyaddress);

    public void delAddress(Integer id);

    public Userbyaddress findAddressById(Integer id);

    /**
     * 修改除id 外其它的地址为0
     *
     * @param userId
     * @param id
     */
    public void setDefaultAddress(String userId, Integer id);

    /**
     * 设置默认收货地址
     *
     * @param userId
     * @param id
     */
    public void defaultAddress(String userId, Integer id);


    public List loadAll();

    /**
     * 获取邀请好友列表
     *
     * @param userId
     * @return
     */
    public Pagination getInvitedList(String userId, int pageNo, int pageSize);

    /**
     * 获取邀请好友列表统计数据
     *
     * @param userId
     * @return
     */
    public List getInvitedListByData(String userId);

    /**
     * 获取邀请好友列表By Count
     *
     * @param userId
     * @return
     */
    public Integer getInvitedListByCount(String userId);

    /**
     * 会员列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination adminUserList(String typeId, String keyword, int pageNo, int pageSize);

    /**
     * 会员总数
     *
     * @return
     */
    public Integer getCountUser();

    /**
     * 判断是否已经存在OpenId
     *
     * @param openId
     * @return
     */
    public User isNotOpenId(String openId);


}
