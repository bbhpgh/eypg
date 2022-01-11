package com.eypg.util;

import com.eypg.pojo.User;

public class UserNameUtil {

    public static String userName(User user) {
        String userName = null;
        if (user != null) {
            if (StringUtil.isNotBlank(user.getUserName())) {
                userName = user.getUserName();
            } else if (StringUtil.isNotBlank(user.getMail())) {
                userName = user.getMail().split("@")[0].substring(0, 2) + "***@" + user.getMail().split("@")[1];
            } else if (StringUtil.isNotBlank(user.getPhone())) {
                userName = user.getPhone().substring(0, 4) + "****" + user.getPhone().substring(8);
            }
        }
        return userName;
    }

    public static void main(String[] args) {
        User user = new User();
//		user.setUserName("测试");
        user.setMail("ye@163.com");
//		user.setPhone("15201558450");
        System.err.println(userName(user));
    }


}
