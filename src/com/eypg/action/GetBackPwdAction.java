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
import com.eypg.util.MD5Util;
import com.eypg.util.MemCachedClientHelp;
import com.eypg.util.RandomValidateCode;
import com.eypg.util.StringUtil;
import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("GetBackPwdAction")
public class GetBackPwdAction extends ActionSupport {

    private static final long serialVersionUID = 686157280940778943L;
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    private String rnd;
    private String mail;
    private String key;
    private User user;
    private String newPwd;

    HttpServletRequest request = null;
    HttpServletResponse response = null;

    public String index() {

        return "index";
    }

    public void getBackPwd() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("rndCode")) {
                    String rndCode = cookie.getValue();
                    System.err.println(rndCode);
                    if (rnd.trim().equalsIgnoreCase(rndCode)) {
                        System.err.println(mail);
                        user = userService.userByName(mail);
                        if (user == null) {
                            Struts2Utils.renderText("false");
                        } else {
                            Struts2Utils.renderText("1");
                        }
                    } else {
                        Struts2Utils.renderText("3");
                    }
                }
            }
        }
    }

    public void getRandomCode() {
        request = Struts2Utils.getRequest();
        response = Struts2Utils.getResponse();
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findemailcheck() {

        return "findemailcheck";
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//		System.err.println(Base64.getEncode("{\"mail\":\"65615609@qq.com\",\"key\":\"NjU2MTU2MDlAcXEuY29t\"}"));
//		System.err.println(Base64.getDecode("eyJtYWlsIjoiNjU2MTU2MDlAcXEuY29tIiwia2V5IjoiTmpVMk1UVTJNRGxBY1hFdVkyOXQifQ=="));
//		String key = "GEFENGBKINNJIDJDMOHMOOBJDMEOHKPPOEEMKGGAOOMCDKLLEPCCDCBNNILOOCICNTIwMTM1OTRAcXEuY29t";
//		key = key.substring(64,key.length());
        String key = MD5Util.encode("127.0.0.1" + 1000);
        MemCachedClientHelp.getIMemcachedCache().remove(key);
    }

    public void sendFindPwdMail() {
        request = Struts2Utils.getRequest();
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
                "<td style=\"font-size: 12px; color: #333333; line-height: 22px;\"><p style=\"text-indent: 2em; padding: 0; margin: 0;\">您好！请点击下面的按钮，重新设置您的密码：</p></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding-top: 15px; padding-left: 28px;\"><a title=\"重设密码\" style=\"display: inline-block; padding: 0 25px; height: 28px; line-height: 28px; text-align: center; color: #ffffff; background: #ff7700; font-size: 12px; cursor: pointer; border-radius: 2px; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/getbackpwd/findreset.html?key=" + key + "\">重设密码</a></td>" +
                "</tr>" +
                "<tr>" +
                "<td width=\"525\" style=\"font-size: 12px; color: #333333; line-height: 22px; padding-top: 20px;\">如果上面按钮不能点击或点击后没有反应，您还可以将以下链接复制到浏览器地址栏中访问完成重设密码。</td>" +
                "</tr>" +
                "<tr>" +
                "<td width=\"525\" style=\"font-size: 12px; padding-top: 5px; word-break: break-all; word-wrap: break-word;\"><a style=\"font-family: Arial; color: #22aaff;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/getbackpwd/findreset.html?key=" + key + "\">" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/getbackpwd/findreset.html?key=" + key + "</a></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"font-size:12px; color:#333333; line-height:22px; padding-top:30px;\"><p style=\"text-indent:2em; padding:0; margin:0;\"><b>如果您现在想起了您的密码：</b></p></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"font-size:12px; color:#333333; line-height:22px;\"><p style=\"text-indent:2em; padding:0; margin:0;\">可不必重设密码，继续用原来的密码登录。</p></td>" +
                "</tr>" +
                "</tbody></table>" +
                "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"margin-top: 60px;\">" +
                "<tbody><tr>" +
                "<td style=\"font-size: 12px; color: #777777; line-height: 22px; border-bottom: #22aaff 2px solid; padding-bottom: 8px; padding-left: 20px;\">此邮件由系统自动发出，请勿回复！</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"font-size: 12px; color: #333333; line-height: 22px; padding: 8px 20px 0;\">感谢您对" + ApplicationListenerImpl.sysConfigureJson.getSaitName() + "（<a style=\"color: #22aaff; font-family: Arial;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "\">" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "</a>）的支持，祝您好运！</td>" +
                "</tr>" +
//                    "<tr>"+
//                        "<td style=\"font-size: 12px; color: #333333; line-height: 22px; padding: 0 20px;\">客服热线：<b style=\"color: #ff6600; font-family: Arial;\">400-685-9800</b></td>"+
//                    "</tr>"+
                "</tbody></table>" +
                "</td>" +
                "</tr>" +
                "</tbody></table>" +
                "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\">" +
                "<tbody><tr>" +
                "<td align=\"center\" style=\"font-size:12px; color:#bbbbbb; padding-top:10px;\"><span style=\"font-family:Arial;\">Copyright &copy; 2011 - 2013,</span> 版权所有 <span style=\"font-family:Arial;\">" + ApplicationListenerImpl.sysConfigureJson.getDomain() + "</span> " + ApplicationListenerImpl.sysConfigureJson.getIcp() + "</td>" +
                "</tr>" +
                "</tbody></table>";

        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("rndCode")) {
                    String rndCode = cookie.getValue();
                    if (rnd.trim().equalsIgnoreCase(rndCode)) {
                        if (MemCachedClientHelp.getIMemcachedCache().get(MD5Util.encode(mail)) == null) {
                            boolean flag = EmailUtil.sendEmail(ApplicationListenerImpl.sysConfigureJson.getMailName(), ApplicationListenerImpl.sysConfigureJson.getMailPwd(), mail, ApplicationListenerImpl.sysConfigureJson.getSaitName() + "取回密码", html);
                            if (flag) {
                                MemCachedClientHelp.getIMemcachedCache().put(MD5Util.encode(mail), mail, new Date(10 * 60 * 1000));
                                Struts2Utils.renderText("0");
                            } else {
                                Struts2Utils.renderText("false");
                            }
                        } else {
                            Struts2Utils.renderText("3");
                        }
                    } else {
                        Struts2Utils.renderText("2");
                    }
                }
            }
        }
    }

    public String findreset() throws UnsupportedEncodingException {
        if (StringUtil.isNotBlank(key)) {
            key = key.substring(64, key.length());
            mail = Base64.getDecode(key);
            if (StringUtil.isNotBlank(mail)) {
                user = userService.userByName(mail);
                if (user != null) {
                    return "findreset";
                }
            }
        }
        return "index_index";
    }

    public void updatePwd() {
        user = userService.userByName(mail);
        if (user != null) {
            user.setUserPwd(newPwd);
            userService.add(user);
            Struts2Utils.renderText("0");
        } else {
            Struts2Utils.renderText("false");
        }
    }

    public String findok() {

        return "findok";
    }


    public String getRnd() {
        return rnd;
    }

    public void setRnd(String rnd) {
        this.rnd = rnd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }


}
