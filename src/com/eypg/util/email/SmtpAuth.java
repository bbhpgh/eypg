package com.eypg.util.email;

public class SmtpAuth extends javax.mail.Authenticator {
    private String user, password;

    /**
     * @param getuser
     * @param getpassword
     */
    public void setUserinfo(String getuser, String getpassword) {
        user = getuser;
        password = getpassword;
    }

    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        return new javax.mail.PasswordAuthentication(user, password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
} 