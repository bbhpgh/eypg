package com.eypg.action;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("AboutAction")
public class AboutAction extends ActionSupport {

    private static final long serialVersionUID = -6979064905632902960L;

    public String index() {

        return "index";
    }

    public String jobs() {

        return "jobs";
    }

    public String contactus() {

        return "contactus";
    }

    public String Links() {

        return "Links";
    }

}
