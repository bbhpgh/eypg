package com.eypg.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("LogoutAction")
public class LogoutAction extends ActionSupport {
    private static final long serialVersionUID = 5411610776024806651L;

    public String index() {

        return "logout";
    }

}
