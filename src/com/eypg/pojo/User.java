package com.eypg.pojo;

/**
 * User entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

    // Fields

    private Integer userId;
    private String userName;
    private String userPwd;
    private String changePwdNo;
    private String changePwdTime;
    private String realName;
    private String cardNo;
    private String mobilePhone;
    private String mobileCheck;
    private String phone;
    private String qq;
    private String mail;
    private String mailCheck;
    private String sex;
    private String birthday;
    private String faceImg;
    private String location;
    private String postNo;
    private String ipAddress;
    private String ipLocation;
    private String oldIpAddress;
    private String maritalStatus;
    private String monthlyIncome;
    private String interests;
    private String attribute22;
    private String oldDate;
    private String newDate;
    private Double balance;
    private String userType;
    private Integer experience;
    private String signature;
    private Integer invite;
    private Double commissionCount;
    private Double commissionBalance;
    private Double commissionMention;
    private Integer commissionPoints;


    public User(Integer userId, String userName, String userPwd,
                String changePwdNo, String changePwdTime, String realName,
                String cardNo, String mobilePhone, String mobileCheck,
                String phone, String qq, String mail, String mailCheck, String sex,
                String birthday, String faceImg, String location, String postNo,
                String ipAddress, String ipLocation, String oldIpAddress,
                String maritalStatus, String monthlyIncome, String interests,
                String attribute22, String oldDate, String newDate, Double balance,
                String userType, Integer experience, String signature,
                Integer invite, Double commissionCount, Double commissionBalance,
                Double commissionMention, Integer commissionPoints) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.changePwdNo = changePwdNo;
        this.changePwdTime = changePwdTime;
        this.realName = realName;
        this.cardNo = cardNo;
        this.mobilePhone = mobilePhone;
        this.mobileCheck = mobileCheck;
        this.phone = phone;
        this.qq = qq;
        this.mail = mail;
        this.mailCheck = mailCheck;
        this.sex = sex;
        this.birthday = birthday;
        this.faceImg = faceImg;
        this.location = location;
        this.postNo = postNo;
        this.ipAddress = ipAddress;
        this.ipLocation = ipLocation;
        this.oldIpAddress = oldIpAddress;
        this.maritalStatus = maritalStatus;
        this.monthlyIncome = monthlyIncome;
        this.interests = interests;
        this.attribute22 = attribute22;
        this.oldDate = oldDate;
        this.newDate = newDate;
        this.balance = balance;
        this.userType = userType;
        this.experience = experience;
        this.signature = signature;
        this.invite = invite;
        this.commissionCount = commissionCount;
        this.commissionBalance = commissionBalance;
        this.commissionMention = commissionMention;
        this.commissionPoints = commissionPoints;
    }

    /**
     * default constructor
     */
    public User() {
    }

    /**
     * minimal constructor
     */
    public User(String userPwd, String userType) {
        this.userPwd = userPwd;
        this.userType = userType;
    }

    /**
     * full constructor
     */


    // Property accessors
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return this.userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getChangePwdNo() {
        return this.changePwdNo;
    }

    public void setChangePwdNo(String changePwdNo) {
        this.changePwdNo = changePwdNo;
    }

    public String getChangePwdTime() {
        return this.changePwdTime;
    }

    public void setChangePwdTime(String changePwdTime) {
        this.changePwdTime = changePwdTime;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMobileCheck() {
        return this.mobileCheck;
    }

    public void setMobileCheck(String mobileCheck) {
        this.mobileCheck = mobileCheck;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMailCheck() {
        return this.mailCheck;
    }

    public void setMailCheck(String mailCheck) {
        this.mailCheck = mailCheck;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFaceImg() {
        return this.faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostNo() {
        return this.postNo;
    }

    public void setPostNo(String postNo) {
        this.postNo = postNo;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpLocation() {
        return this.ipLocation;
    }

    public void setIpLocation(String ipLocation) {
        this.ipLocation = ipLocation;
    }

    public String getMaritalStatus() {
        return this.maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMonthlyIncome() {
        return this.monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getInterests() {
        return this.interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getAttribute22() {
        return this.attribute22;
    }

    public void setAttribute22(String attribute22) {
        this.attribute22 = attribute22;
    }

    public String getOldDate() {
        return this.oldDate;
    }

    public void setOldDate(String oldDate) {
        this.oldDate = oldDate;
    }

    public String getNewDate() {
        return this.newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getExperience() {
        return this.experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getInvite() {
        return this.invite;
    }

    public void setInvite(Integer invite) {
        this.invite = invite;
    }

    public Double getCommissionCount() {
        return commissionCount;
    }

    public void setCommissionCount(Double commissionCount) {
        this.commissionCount = commissionCount;
    }

    public Double getCommissionBalance() {
        return commissionBalance;
    }

    public void setCommissionBalance(Double commissionBalance) {
        this.commissionBalance = commissionBalance;
    }

    public Double getCommissionMention() {
        return commissionMention;
    }

    public void setCommissionMention(Double commissionMention) {
        this.commissionMention = commissionMention;
    }

    public Integer getCommissionPoints() {
        return commissionPoints;
    }

    public void setCommissionPoints(Integer commissionPoints) {
        this.commissionPoints = commissionPoints;
    }

    public String getOldIpAddress() {
        return oldIpAddress;
    }

    public void setOldIpAddress(String oldIpAddress) {
        this.oldIpAddress = oldIpAddress;
    }


}