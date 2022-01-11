package com.eypg.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eypg.pojo.User;
import com.eypg.service.UserService;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.Base64;
import com.eypg.util.DateUtil;
import com.eypg.util.EmailUtil;
import com.eypg.util.HTMLFilter;
import com.eypg.util.IPSeeker;
import com.eypg.util.MD5Util;
import com.eypg.util.MemCachedClientHelp;
import com.eypg.util.StringUtil;
import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("unused")
@Component("RegsiterAction")
public class RegisterAction extends ActionSupport {
    private static final long serialVersionUID = 5054777863371691520L;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    private User user;
    private String phone;
    private String mail;
    private String userPwd;
    private String userName;
    private String forward;
    private String str;
    private String isVerify;
    private String key;
    private String date;
    private String openId;
    private String userFace;

    public static IPSeeker seeker = new IPSeeker();
    HttpServletRequest request = null;
    HttpServletResponse response = null;
    static HTMLFilter htmlFilter = new HTMLFilter();

    public String index() {
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
//			Struts2Utils.render("text/html", "<script>alert(\"您的浏览器未开启Cookie功能,无法保存购物信息,请先开启Cookie功能后继续购物！\");window.location.href=\"/help/openCookie.html\";</script>","encoding:UTF-8");
//			return null;
        }

        return "index";
    }

    public String regsiter() {
        str = htmlFilter.filter(str);
        userPwd = htmlFilter.filter(userPwd);
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        user = new User();
//		String ip = Struts2Utils.getRequest().getRemoteAddr();
        String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
        if (ip == null) {
            ip = "127.0.0.1";
        }
        String date = DateUtil.DateTimeToStr(new Date());
        if (str.indexOf("@") != -1) {
            user.setMail(str);
            user.setMailCheck("3");
        } else {
            user.setPhone(str);
            user.setMobileCheck("3");
        }
        if (userPwd != null && !userPwd.equals("")) {
            user.setUserPwd(userPwd);
        }
        user.setIpAddress(ip);
        String ipLocation = seeker.getAddress(ip);
        user.setIpLocation(ipLocation);
        user.setOldDate(date);
        user.setNewDate(date);
        user.setBalance(0.00);
        user.setCommissionBalance(0.00);
        user.setCommissionCount(0.00);
        user.setCommissionMention(0.00);
        user.setCommissionPoints(0);
        user.setFaceImg("/Images/defaultUserFace.png");
        user.setUserType("0");
        user.setExperience(0);
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("inviteId")) {
                    String inviteId = cookie.getValue();
                    if (inviteId != null && !inviteId.equals("")) {
                        user.setInvite(Integer.parseInt(inviteId));
                    }
                }
            }
        }

        try {
            this.userService.add(user);
            Struts2Utils.renderJson(user);
        } catch (Exception e) {
            e.printStackTrace();
            Struts2Utils.renderText("false");
        }
        return null;
    }

    public String mobilecheck() {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        user = userService.userByName(str);
        if (user == null) {
            Struts2Utils.render("text/html", "<script>alert(\"该邮箱没有注册,请注册！\");window.location.href=\"/register/index.html\";</script>", "encoding:UTF-8");
        }
        if (request.isRequestedSessionIdFromCookie()) {
            Cookie cookie = new Cookie("phone", str);
            cookie.setMaxAge(-1);
            cookie.setPath("/");
            cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
            response.addCookie(cookie);
            Cookie cookie2 = new Cookie("userId", String.valueOf(user.getUserId()));
            cookie2.setMaxAge(-1);
            cookie2.setPath("/");
            cookie2.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
            response.addCookie(cookie2);
            Cookie cookie3 = new Cookie("face", user.getFaceImg());
            cookie3.setMaxAge(-1);
            cookie3.setPath("/");
            cookie3.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
            response.addCookie(cookie3);
        }
//		return "mobilecheck";
        return "index_index";
    }

    public String emailcheck() {
//		System.err.println("str:"+str);
        user = userService.userByName(str);
        if (user == null) {
            Struts2Utils.render("text/html", "<script>alert(\"该邮箱没有注册,请注册！\");window.location.href=\"/register/index.html\";</script>", "encoding:UTF-8");
        }
        isVerify = user.getMailCheck();
        return "emailcheck";
    }

    public void SendRegisterMail() {
//		System.err.println("mail:"+mail);
        key = MD5Util.encode(mail) + MD5Util.encode(DateUtil.dateTimeToStr(new Date())) + Base64.getEncode(mail);
        String html = "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"border: #dddddd 1px solid; padding: 20px 0;\">" +
                "<tbody><tr>" +
                "<td>" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"border-bottom: #ff6600 2px solid; padding-bottom: 12px;\">" +
                "<tbody><tr>" +
                "<td style=\"line-height: 22px; padding-left: 20px;\"><a target=\"_blank\" title=\"" + ApplicationListenerImpl.sysConfigureJson.getSaitName() + "\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "\"><img width=\"230px\" border=\"0\" height=\"52\" src=\"" + ApplicationListenerImpl.sysConfigureJson.getImgUrl() + "/Images/mail_logo.gif\"></a></td>" +
                "<td align=\"right\" style=\"font-size: 12px; padding-right: 20px; padding-top: 30px;\"><a style=\"color: #22aaff; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "\">首页</a><b style=\"width: 1px; height: 10px; vertical-align: -1px; font-size: 1px; background: #CACACA; display: inline-block; margin: 0 5px;\"></b>" +
                "<a style=\"color: #22aaff; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/user/index.html\">我的" + ApplicationListenerImpl.sysConfigureJson.getSaitName() + "</a><b style=\"width: 1px; height: 10px; vertical-align: -1px; font-size: 1px; background: #CACACA; display: inline-block; margin: 0 5px;\"></b><a style=\"color: #22aaff; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/help/index.html\">帮助</a></td>" +
                "</tr>" +
                "</tbody></table>" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"padding: 0 20px;\">" +
                "<tbody><tr>" +
                "<td style=\"font-size: 14px; color: #333333; height: 40px; line-height: 40px; padding-top: 10px;\">亲爱的 <b style=\"color: #333333; font-family: Arial;\"><a href=\"mailto:" + mail + "\" target=\"_blank\">" + mail + "</a></b>：</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"font-size: 12px; color: #333333; line-height: 22px;\"><p style=\"text-indent: 2em; padding: 0; margin: 0;\">您好！感谢您注册" + ApplicationListenerImpl.sysConfigureJson.getSaitName() + "。</p></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"font-size: 12px; color: #333333; line-height: 22px;\"><p style=\"text-indent: 2em; padding: 0; margin: 0;\">请点击下面的按钮，完成邮箱验证。</p></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding-top: 15px; padding-left: 28px;\"><a title=\"邮箱验证\" style=\"display: inline-block; padding: 0 25px; height: 28px; line-height: 28px; text-align: center; color: #ffffff; background: #ff7700; font-size: 12px; cursor: pointer; border-radius: 2px; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/register/emailok.html?key=" + key + "\">邮箱验证</a></td>" +
                "</tr>" +
                "<tr>" +
                "<td width=\"525\" style=\"font-size: 12px; color: #333333; line-height: 22px; padding-top: 20px;\">如果上面按钮不能点击或点击后没有反应，您还可以将以下链接复制到浏览器地址栏中访问完成验证。</td>" +
                "</tr>" +
                "<tr>" +
                "<td width=\"525\" style=\"font-size: 12px; padding-top: 5px; word-break: break-all; word-wrap: break-word;\"><a style=\"font-family: Arial; color: #22aaff;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/register/emailok.html?key=" + key + "\">" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/register/emailok.html?key=" + key + "</a></td>" +
                "</tr>" +
                "</tbody></table>" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"margin-top: 60px;\">" +
                "<tbody><tr>" +
                "<td style=\"font-size: 12px; color: #777777; line-height: 22px; border-bottom: #22aaff 2px solid; padding-bottom: 8px; padding-left: 20px;\">此邮件由系统自动发出，请勿回复！</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"font-size: 12px; color: #333333; line-height: 22px; padding: 8px 20px 0;\">感谢您对" + ApplicationListenerImpl.sysConfigureJson.getSaitName() + "（<a style=\"color: #22aaff; font-family: Arial;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "\">" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "</a>）的支持，祝您好运！</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"font-size: 12px; color: #333333; line-height: 22px; padding: 0 20px;\">客服热线：<b style=\"color: #ff6600; font-family: Arial;\">" + ApplicationListenerImpl.sysConfigureJson.getServiceTel() + "</b></td>" +
                "</tr>" +
                "</tbody></table>" +
                "</td>" +
                "</tr>" +
                "</tbody></table>" +
                "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\">" +
                "<tbody><tr>" +
                "<td align=\"center\" style=\"font-size:12px; color:#bbbbbb; padding-top:10px;\"><span style=\"font-family:Arial;\">Copyright &copy; 2011 - 2013,</span> 版权所有 <span style=\"font-family:Arial;\">" + ApplicationListenerImpl.sysConfigureJson.getDomain() + "</span> " + ApplicationListenerImpl.sysConfigureJson.getIcp() + "</td>" +
                "</tr>" +
                "</tbody></table>";
        if (MemCachedClientHelp.getIMemcachedCache().get(MD5Util.encode(mail)) == null) {
            user = userService.userByName(mail);
            if (user != null) {
                if (user.getMailCheck().equals("0")) {
                    Struts2Utils.renderText("0");
                } else {
                    try {
                        boolean flag = EmailUtil.sendEmail(ApplicationListenerImpl.sysConfigureJson.getMailName(), ApplicationListenerImpl.sysConfigureJson.getMailPwd(), mail, ApplicationListenerImpl.sysConfigureJson.getSaitName() + "验证注册邮箱", html);
                        if (flag) {
                            user.setMailCheck("1");
                            userService.add(user);
                            MemCachedClientHelp.getIMemcachedCache().put(MD5Util.encode(mail), mail, new Date(10 * 60 * 1000));
                            Struts2Utils.renderText("2");
                        } else {
                            Struts2Utils.renderText("false");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Struts2Utils.renderText("false");
                    }
                }
            }
        } else {
            user = userService.userByName(mail);
            if (user != null) {
                if (user.getMailCheck().equals("1")) {
                    Struts2Utils.renderText("3");
                } else if (user.getMailCheck().equals("0")) {
                    Struts2Utils.renderText("0");
                }
            }
        }
    }

    public static void main(String[] args) {
        MemCachedClientHelp.getIMemcachedCache().put("GEFENGBKINNJIDJDMOHMOOBJDMEOHKPPMNLPIEGPDBGBGODOABGFADECBAMKNIOM", "52013594@qq.com", new Date(30 * 60 * 1000));
    }

    public String emailok() throws UnsupportedEncodingException {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
//		System.err.println("emailok:"+key);
        if (StringUtil.isNotBlank(key)) {
            key = key.substring(64, key.length());
            mail = Base64.getDecode(key);
            if (MemCachedClientHelp.getIMemcachedCache().get(MD5Util.encode(mail)) != null) {
                if (StringUtil.isNotBlank(mail)) {
                    user = userService.userByName(mail);
                    if (user != null) {
                        if (!user.getMailCheck().equals("0")) {
                            user.setMailCheck("0");
                            userService.add(user);
                            isVerify = "0";
                            if (request.isRequestedSessionIdFromCookie()) {
                                Cookie cookie = new Cookie("mail", user.getMail());
                                cookie.setMaxAge(-1);
                                cookie.setPath("/");
                                cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                                response.addCookie(cookie);
                                Cookie cookie2 = new Cookie("userId", String.valueOf(user.getUserId()));
                                cookie2.setMaxAge(-1);
                                cookie2.setPath("/");
                                cookie2.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                                response.addCookie(cookie2);
                                Cookie cookie3 = new Cookie("face", user.getFaceImg());
                                cookie3.setMaxAge(-1);
                                cookie3.setPath("/");
                                cookie3.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                                response.addCookie(cookie3);
                            }
                        } else {
                            isVerify = "0";
                        }
                    } else {
                        isVerify = "1";
                    }
                } else {
                    isVerify = "1";
                }
            } else {
                isVerify = "1";
            }
        }
        return "emailok";
    }

    /**
     * 授权注册
     */
    public void authorizeRegsiter() {
        user = new User();
//		String ip = Struts2Utils.getRequest().getRemoteAddr();
        String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
        String date = DateUtil.DateTimeToStr(new Date());
        if (userName != null && !userName.equals("")) {
            user.setUserName(userName);
        }
        user.setUserPwd(openId);
        if (ip == null) {
            ip = "127.0.0.1";
        }
        user.setIpAddress(ip);
        String ipLocation = seeker.getAddress(ip);
        user.setIpLocation(ipLocation);
        user.setOldDate(date);

        user.setBalance(0.00);
        user.setCommissionBalance(0.00);
        user.setCommissionCount(0.00);
        user.setCommissionMention(0.00);
        user.setCommissionPoints(0);
        user.setFaceImg(userFace);
        user.setUserType("0");
        user.setExperience(0);

        try {
            this.userService.add(user);
            Struts2Utils.renderJson(user);
        } catch (Exception e) {
            Struts2Utils.renderText("false");
            e.printStackTrace();
        }
    }

    public void authorizeIsExists() {
        user = userService.isNotOpenId(openId);
        if (user == null)
            Struts2Utils.renderText("true");
        else
            Struts2Utils.renderJson(user);
    }

    public String isExists() {
        user = userService.userByName(userName);
        if (user == null)
            Struts2Utils.renderText("true");
        else
            Struts2Utils.renderText("false");
        return null;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(String isVerify) {
        this.isVerify = isVerify;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserFace() {
        return userFace;
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }


}
