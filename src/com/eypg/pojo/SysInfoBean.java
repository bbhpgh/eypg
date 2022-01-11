package com.eypg.pojo;

import java.io.Serializable;

public class SysInfoBean implements Serializable {

    private static final long serialVersionUID = -408939127393554870L;

    public String saitName;
    public String saitUrl;
    public String keyword;
    public String description;
    public String mailName;
    public String mailPwd;
    public String icp;
    public String tenpayPartner; //财付通商户号
    public String tenpayKey;  //财付通商户密钥
    public String alipayPartner; //支付宝商户号
    public String alipayKey;  //支付宝商户密钥
    public String alipayMail; //支付宝帐号
    public String domain;  //域名域   （.1ypg.com）
    public String skin;  //CSS、JS 域名
    public String img; //图片域名

    public String getSaitName() {
        return saitName;
    }

    public void setSaitName(String saitName) {
        this.saitName = saitName;
    }

    public String getSaitUrl() {
        return saitUrl;
    }

    public void setSaitUrl(String saitUrl) {
        this.saitUrl = saitUrl;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
    }

    public String getMailPwd() {
        return mailPwd;
    }

    public void setMailPwd(String mailPwd) {
        this.mailPwd = mailPwd;
    }

    public String getIcp() {
        return icp;
    }

    public void setIcp(String icp) {
        this.icp = icp;
    }

    public String getTenpayPartner() {
        return tenpayPartner;
    }

    public void setTenpayPartner(String tenpayPartner) {
        this.tenpayPartner = tenpayPartner;
    }

    public String getTenpayKey() {
        return tenpayKey;
    }

    public void setTenpayKey(String tenpayKey) {
        this.tenpayKey = tenpayKey;
    }

    public String getAlipayPartner() {
        return alipayPartner;
    }

    public void setAlipayPartner(String alipayPartner) {
        this.alipayPartner = alipayPartner;
    }

    public String getAlipayKey() {
        return alipayKey;
    }

    public void setAlipayKey(String alipayKey) {
        this.alipayKey = alipayKey;
    }

    public String getAlipayMail() {
        return alipayMail;
    }

    public void setAlipayMail(String alipayMail) {
        this.alipayMail = alipayMail;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
