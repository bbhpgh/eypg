package com.eypg.action;

import org.springframework.stereotype.Component;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("SetNamePwdAction")
public class SetNamePwdAction extends ActionSupport {

    private static final long serialVersionUID = -3714908489142137768L;

    public String index() {

        return "index";
    }

    public String setNewPwd() {

        return null;
    }

}
