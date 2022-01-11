package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.dao.Pagination;
import com.eypg.pojo.Cardpassword;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.User;
import com.eypg.pojo.Userbyaddress;
import com.eypg.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void add(User user) {
        baseDao.saveOrUpdate(user);
    }

    public void delete(String id) {
        baseDao.delById(User.class, id);
    }

    public User findById(String id) {
        StringBuffer hql = new StringBuffer("from User user where 1=1");
        if (StringUtils.isNotBlank(id)) {
            hql.append(" and user.userId='" + id + "'");
        }
        return (User) baseDao.loadObject(hql.toString());
    }

    public List<User> query(String hql) {
        return (List<User>) baseDao.query(hql);
    }

    public void update(String hql) {
        baseDao.update(hql);
    }

    public User phoneLogin(String phone, String userPwd) {
        StringBuffer hql = new StringBuffer();
        if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(userPwd)) {
            hql.append("from User user where user.phone ='" + phone + "' and user.userPwd ='" + userPwd + "'");
        }
        return (User) this.baseDao.loadObject(hql.toString());
    }

    public User mailLogin(String mail, String userPwd) {
        StringBuffer hql = new StringBuffer();
        if (StringUtils.isNotBlank(mail) && StringUtils.isNotBlank(userPwd)) {
            hql.append("from User user where user.mail ='" + mail + "' and user.userPwd ='" + userPwd + "'");
        }
        return (User) this.baseDao.loadObject(hql.toString());
    }

    public User userByName(String userName) {
        StringBuffer hql = new StringBuffer("from User user where 1=1");
        if (StringUtils.isNotBlank(userName)) {
            hql.append(" and user.phone='" + userName + "' or user.mail='" + userName + "'");
        }
        return (User) this.baseDao.loadObject(hql.toString());
    }

    public List getUserbyaddress(String userId) {
        StringBuffer hql = new StringBuffer("select us.* from userbyaddress us where us.userId = '" + userId + "'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("us", Userbyaddress.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public void addAddress(Userbyaddress userbyaddress) {
        baseDao.saveOrUpdate(userbyaddress);
    }

    public void delAddress(Integer id) {
        baseDao.delById(Userbyaddress.class, id);
    }

    public Userbyaddress findAddressById(Integer id) {
        StringBuffer hql = new StringBuffer("from Userbyaddress userbyaddress where id='" + id + "'");
        return (Userbyaddress) baseDao.loadObject(hql.toString());
    }

    public void setDefaultAddress(String userId, Integer id) {
        StringBuffer hql = new StringBuffer("update Userbyaddress u set u.status = 0 where u.userId='" + userId + "' and u.id <> '" + id + "'");
        baseDao.update(hql.toString());
    }

    public void defaultAddress(String userId, Integer id) {
        StringBuffer hql = new StringBuffer("update Userbyaddress u set u.status = 1 where u.userId='" + userId + "' and u.id = '" + id + "'");
        baseDao.update(hql.toString());
    }

    public User isUserName(String userName, String userId) {
        StringBuffer hql = new StringBuffer("from User user where 1=1");
        if (StringUtils.isNotBlank(userName)) {
            hql.append(" and user.userName='" + userName + "' and user.userId <> '" + userId + "'");
        }
        return (User) this.baseDao.loadObject(hql.toString());
    }

    public List loadAll() {
        StringBuffer sql = new StringBuffer("from User u where 1=1 and u.userPwd = '1ypg.com'");
        List list = baseDao.find(sql.toString());
        return list;
    }

    public User userByIsUserName(String userName) {
        StringBuffer hql = new StringBuffer("from User user where 1=1");
        if (StringUtils.isNotBlank(userName)) {
            hql.append(" and user.userName='" + userName + "'");
        }
        return (User) this.baseDao.loadObject(hql.toString());
    }

    public Pagination getInvitedList(String userId, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from user u where  u.invite = '" + userId + "' order by u.oldDate desc");
//		StringBuffer sql = new StringBuffer("select count(*) from user u where  u.invite = '"+userId+"'");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("u", User.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
//		int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
//		page.setResultCount(resultCount);
        return page;
    }

    public Integer getInvitedListByCount(String userId) {
        StringBuffer sql = new StringBuffer("select count(*) from user u where  u.invite = '" + userId + "'");
        return baseDao.getAllNum(sql);
    }

    public List getInvitedListByData(String userId) {
        StringBuffer hql = new StringBuffer("select u.* from user u where u.invite = '" + userId + "'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("u", User.class);
        List list = baseDao.sqlQueryEntity(hql, entityMap);
        return list;
    }

    public Pagination adminUserList(String typeId, String keyword, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from user u where  1=1 and u.userType = 0");
        if (StringUtils.isNotBlank(typeId)) {
            if (typeId.equals("userId")) {
                hql.append(" and u.userId='" + keyword + "'");
            }
            if (typeId.equals("userName")) {
                hql.append(" and u.userName='" + keyword + "'");
            }
            if (typeId.equals("mail")) {
                hql.append(" and u.mail='" + keyword + "'");
            }
            if (typeId.equals("phone")) {
                hql.append(" and u.phone='" + keyword + "'");
            }
        }
        hql.append(" order by u.oldDate desc");
        StringBuffer sql = new StringBuffer("select count(*) from user");
        if (StringUtils.isNotBlank(typeId)) {
            if (typeId.equals("userId")) {
                sql.append(" and u.userId='" + keyword + "'");
            }
            if (typeId.equals("userName")) {
                sql.append(" and u.userName='" + keyword + "'");
            }
            if (typeId.equals("mail")) {
                sql.append(" and u.mail='" + keyword + "'");
            }
            if (typeId.equals("phone")) {
                sql.append(" and u.phone='" + keyword + "'");
            }
        }
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("u", User.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

    public Integer getCountUser() {
        StringBuffer sql = new StringBuffer("select count(*) from user");
        return baseDao.getAllNum(sql);
    }

    public User isNotOpenId(String openId) {
        StringBuffer hql = new StringBuffer("from User user where 1=1");
        if (StringUtils.isNotBlank(openId)) {
            hql.append(" and user.userPwd='" + openId + "'");
        }
        return (User) this.baseDao.loadObject(hql.toString());
    }


}
