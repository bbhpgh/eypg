package com.eypg.pojo;

public class UserBuy {

    String String1;
    String String2;


    /**
     * @param buyDate
     * @param userName
     */

    public UserBuy() {
    }


    /**
     * @param string1
     * @param string2
     */
    public UserBuy(String string1, String string2) {
        super();
        String1 = string1;
        String2 = string2;
    }


    public String getString1() {
        return String1;
    }


    public void setString1(String string1) {
        String1 = string1;
    }


    public String getString2() {
        return String2;
    }


    public void setString2(String string2) {
        String2 = string2;
    }


}
