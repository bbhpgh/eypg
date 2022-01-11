package com.eypg.pojo;

/**
 * SysConfigure entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class SysConfigure implements java.io.Serializable {

    // Fields

    private Integer id;
    private String imgUrl;
    private String skinUrl;
    private String wwwUrl;
    private String domain;
    private String saitName;
    private String shortName;
    private String saitTitle;
    private String saitLogo;
    private String keyword;
    private String description;
    private String mailName;
    private String mailPwd;
    private String tenpayPartner;
    private String tenpayKey;
    private Integer tenpayStatus;
    private String alipayPartner;
    private String alipayKey;
    private String alipayMail;
    private Integer alipayStatus;
    private String yeepayKey;
    private String yeepayPartner;
    private Integer yeepayStatus;
    private String icp;
    private String serviceQq;
    private String serviceTel;
    private String qqAppId;
    private String qqAppKey;
    private Integer qqAppStatus;

    public SysConfigure(String imgUrl, String skinUrl, String wwwUrl,
                        String domain, String saitName, String shortName, String saitTitle,
                        String saitLogo, String keyword, String description,
                        String mailName, String mailPwd, String tenpayPartner,
                        String tenpayKey, Integer tenpayStatus, String alipayPartner,
                        String alipayKey, String alipayMail, Integer alipayStatus,
                        String yeepayKey, String yeepayPartner, Integer yeepayStatus,
                        String icp, String serviceQq, String serviceTel, String qqAppId,
                        String qqAppKey, Integer qqAppStatus) {
        super();
        this.imgUrl = imgUrl;
        this.skinUrl = skinUrl;
        this.wwwUrl = wwwUrl;
        this.domain = domain;
        this.saitName = saitName;
        this.shortName = shortName;
        this.saitTitle = saitTitle;
        this.saitLogo = saitLogo;
        this.keyword = keyword;
        this.description = description;
        this.mailName = mailName;
        this.mailPwd = mailPwd;
        this.tenpayPartner = tenpayPartner;
        this.tenpayKey = tenpayKey;
        this.tenpayStatus = tenpayStatus;
        this.alipayPartner = alipayPartner;
        this.alipayKey = alipayKey;
        this.alipayMail = alipayMail;
        this.alipayStatus = alipayStatus;
        this.yeepayKey = yeepayKey;
        this.yeepayPartner = yeepayPartner;
        this.yeepayStatus = yeepayStatus;
        this.icp = icp;
        this.serviceQq = serviceQq;
        this.serviceTel = serviceTel;
        this.qqAppId = qqAppId;
        this.qqAppKey = qqAppKey;
        this.qqAppStatus = qqAppStatus;
    }

    /**
     * default constructor
     */
    public SysConfigure() {
    }

    /**
     * full constructor
     */


    // Property accessors
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSkinUrl() {
        return this.skinUrl;
    }

    public void setSkinUrl(String skinUrl) {
        this.skinUrl = skinUrl;
    }

    public String getWwwUrl() {
        return this.wwwUrl;
    }

    public void setWwwUrl(String wwwUrl) {
        this.wwwUrl = wwwUrl;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSaitName() {
        return this.saitName;
    }

    public void setSaitName(String saitName) {
        this.saitName = saitName;
    }

    public String getSaitLogo() {
        return this.saitLogo;
    }

    public void setSaitLogo(String saitLogo) {
        this.saitLogo = saitLogo;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMailName() {
        return this.mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
    }

    public String getMailPwd() {
        return this.mailPwd;
    }

    public void setMailPwd(String mailPwd) {
        this.mailPwd = mailPwd;
    }

    public String getTenpayPartner() {
        return this.tenpayPartner;
    }

    public void setTenpayPartner(String tenpayPartner) {
        this.tenpayPartner = tenpayPartner;
    }

    public String getTenpayKey() {
        return this.tenpayKey;
    }

    public void setTenpayKey(String tenpayKey) {
        this.tenpayKey = tenpayKey;
    }

    public Integer getTenpayStatus() {
        return this.tenpayStatus;
    }

    public void setTenpayStatus(Integer tenpayStatus) {
        this.tenpayStatus = tenpayStatus;
    }

    public String getAlipayPartner() {
        return this.alipayPartner;
    }

    public void setAlipayPartner(String alipayPartner) {
        this.alipayPartner = alipayPartner;
    }

    public String getAlipayKey() {
        return this.alipayKey;
    }

    public void setAlipayKey(String alipayKey) {
        this.alipayKey = alipayKey;
    }

    public String getAlipayMail() {
        return this.alipayMail;
    }

    public void setAlipayMail(String alipayMail) {
        this.alipayMail = alipayMail;
    }

    public Integer getAlipayStatus() {
        return this.alipayStatus;
    }

    public void setAlipayStatus(Integer alipayStatus) {
        this.alipayStatus = alipayStatus;
    }

    public String getYeepayKey() {
        return this.yeepayKey;
    }

    public void setYeepayKey(String yeepayKey) {
        this.yeepayKey = yeepayKey;
    }

    public String getYeepayPartner() {
        return this.yeepayPartner;
    }

    public void setYeepayPartner(String yeepayPartner) {
        this.yeepayPartner = yeepayPartner;
    }

    public Integer getYeepayStatus() {
        return this.yeepayStatus;
    }

    public void setYeepayStatus(Integer yeepayStatus) {
        this.yeepayStatus = yeepayStatus;
    }

    public String getIcp() {
        return this.icp;
    }

    public void setIcp(String icp) {
        this.icp = icp;
    }

    public String getServiceQq() {
        return this.serviceQq;
    }

    public void setServiceQq(String serviceQq) {
        this.serviceQq = serviceQq;
    }

    public String getServiceTel() {
        return this.serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSaitTitle() {
        return saitTitle;
    }

    public void setSaitTitle(String saitTitle) {
        this.saitTitle = saitTitle;
    }

    public String getQqAppId() {
        return qqAppId;
    }

    public void setQqAppId(String qqAppId) {
        this.qqAppId = qqAppId;
    }

    public String getQqAppKey() {
        return qqAppKey;
    }

    public void setQqAppKey(String qqAppKey) {
        this.qqAppKey = qqAppKey;
    }

    public Integer getQqAppStatus() {
        return qqAppStatus;
    }

    public void setQqAppStatus(Integer qqAppStatus) {
        this.qqAppStatus = qqAppStatus;
    }

}