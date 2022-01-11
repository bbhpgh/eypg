package com.eypg.action;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("HelpAction")
public class HelpAction extends ActionSupport {

    private static final long serialVersionUID = -8756837342149838857L;

    public String index() {

        return "index";
    }

    public String openCookie() {

        return "openCookie";
    }

    public String whatPaigou() {

        return "whatPaigou";
    }

    public String paigouRule() {

        return "paigouRule";
    }

    public String paigouFlow() {

        return "paigouFlow";
    }

    public String questionDetail() {

        return "questionDetail";
    }

    public String agreement() {

        return "agreement";
    }

    public String genuinetwo() {

        return "genuinetwo";
    }

    public String genuine() {

        return "genuine";
    }

    public String securepayment() {

        return "securepayment";
    }

    public String ship() {

        return "ship";
    }

    public String suggestion() {

        return "suggestion";
    }

    public String deliveryFees() {

        return "deliveryFees";
    }

    public String prodCheck() {

        return "prodCheck";
    }

    public String shiptwo() {

        return "shiptwo";
    }

    public String privacy() {

        return "privacy";
    }

    public String userExperience() {

        return "userExperience";
    }

    public String qqgroup() {

        return "qqgroup";
    }

}
