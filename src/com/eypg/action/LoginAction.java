package com.eypg.action;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eypg.pojo.User;
import com.eypg.service.UserService;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.DateUtil;
import com.eypg.util.HTMLFilter;
import com.eypg.util.StringUtil;
import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("unused")
@Component("LoginAction")
public class LoginAction extends ActionSupport {
    private static final long serialVersionUID = -6356307819518359036L;

    private String userName;
    private String pwd;
    private String forward;
    private String shareId;
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    HttpServletRequest request = null;
    static HTMLFilter htmlFilter = new HTMLFilter();

    public String index() {
        try {
            if (StringUtil.isNotBlank(forward)) {
                forward = htmlFilter.filter(forward);
            }
            request = Struts2Utils.getRequest();
            Cookie[] cookies = request.getCookies();
            if (request.isRequestedSessionIdFromCookie()) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals("userId")) {
                        String userId = cookie.getValue();
                        if (userId != null && !userId.equals("")) {
                            return "index_index";
                        }
                    }
                }
            } else {
                Struts2Utils.render("text/html", "<script>alert(\"您的浏览器未开启Cookie功能,无法保存购物信息,请先开启Cookie功能后继续购物！\");window.location.href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/help/openCookie.html\";</script>", "encoding:UTF-8");
//				return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    public String login() {
        userName = htmlFilter.filter(userName);
        pwd = htmlFilter.filter(pwd);
        User user = userService.userByName(userName);
//		String ip = Struts2Utils.getRequest().getRemoteAddr();
        String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
        if (ip == null) {
            ip = "127.0.0.1";
        }
        String date = DateUtil.DateTimeToStr(new Date());
        if (user != null) {
            if (userName.indexOf("@") != -1) {
                User u1 = userService.mailLogin(userName, pwd);
                if (u1 != null) {
                    try {
                        if (u1.getMailCheck().equals("0")) {
                            Struts2Utils.renderJson(u1);
                            user.setIpAddress(ip);
                            String ipLocation = RegisterAction.seeker.getAddress(ip);
                            user.setIpLocation(ipLocation);
//							user.setOldDate(user.getNewDate());
                            user.setNewDate(date);
                            userService.add(user);
                        } else {
                            Struts2Utils.renderText("check");
                        }
                    } catch (Exception e) {
                        Struts2Utils.renderText("check");
                        e.printStackTrace();
                    }

                } else {
                    Struts2Utils.renderText("pwdError");
                }
            } else {
                User u2 = userService.phoneLogin(userName, pwd);
                if (u2 != null) {
                    Struts2Utils.renderJson(u2);
                    user.setIpAddress(ip);
                    String ipLocation = RegisterAction.seeker.getAddress(ip);
                    user.setIpLocation(ipLocation);
//					user.setOldDate(user.getNewDate());
                    user.setNewDate(date);
                    userService.add(user);
                } else {
                    Struts2Utils.renderText("pwdError");
                }
            }
        } else {
            Struts2Utils.renderText("userError");
        }
        return null;
    }

    public String fastLogin() {

        return "fastLogin";
    }

    public String buyCartLogin() {

        return "buyCartLogin";
    }

    public String postCommentLogin() {

        return "postCommentLogin";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }


}
