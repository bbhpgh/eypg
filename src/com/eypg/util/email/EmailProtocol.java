package com.eypg.util.email;

public class EmailProtocol {

    private String str;

    public EmailProtocol(String str) {
        this.str = str;
    }

    public String getPopProtocol() {
        String[] s = str.split("@");
        return "pop3." + s[1];
    }

    public String getSmtpProtocol() {
        String[] s = str.split("@");
        if (s[1].indexOf("sina") >= 0) {
            return "smtp.sina.com.cn";
        }
        if (s[1].indexOf("1ypg") >= 0) {
            return "smtp.ym.163.com";
        } else {
            return "smtp." + s[1];
        }
    }
}
