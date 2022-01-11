package com.eypg.util;

import cn.emay.sdk.client.api.Client;

/**
 * 发短信接口
 *
 * @author <a href="mailto:TianXiang.Mr@gmail.com">TianXiang.Mr@gmail.com</a>
 */
public class MessageHelp {

    public MessageHelp() {
        super();
    }

    /**
     * @param 客户端序列号，必须输入
     * @throws Exception
     * final static String SERIALNUMBER = "3SDK-EMY-0130-KFTUQ";
     */
    final static String SERIALNUMBER = "3SDK-EMY-0130-MCXOO";
    /**
     * @param 要注册的关键字，必须输入
     * 用户自定义key值， 长度不超过15个字符的字符串(可包含数字和字母) 将key做好备份，不要遗忘
     * @throws Exception
     * final static String KEY = "123456";
     */
    final static String KEY = "123456";
    /**
     * @param 软件序列号密码，密码（6位），必须输入
     * @throws Exception
     * final static String PASSWORD = "921405";
     */
    final static String PASSWORD = "854141";

    /**
     * 特服号:022555
     * */

    /**
     * 发短信
     *
     * @param text   要发送的内容
     * @param mobile 要发送的手机号码 为一个String数组
     */

    public static int checkBalance() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        int a = 1000;
        try {
            Client sdkclient = new Client(SERIALNUMBER, KEY);
            sdkclient.registEx(PASSWORD);
            double balance = sdkclient.getBalance();
            System.out.println("余额:" + balance);
            sdkclient.closeChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return a;
    }

    public static int send(String[] mobile, String text) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        int a = 1000;
        try {
            Client sdkclient = new Client(SERIALNUMBER, KEY);
            int i = sdkclient.registEx(PASSWORD);
            System.out.println("注册函数结果:" + i);
            //测试用
            //mobile = new String[]{"15110289349"};
            a = sdkclient.sendSMS(mobile, text, 3);
            System.out.println("短信发送结果:" + a);
            double balance = sdkclient.getBalance();
            System.out.println("余额:" + balance);
            double eachFee = sdkclient.getEachFee();
            System.out.println("每条短信费用:" + eachFee);
            sdkclient.closeChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return a;
    }

    public static void main(String[] args) {
        checkBalance();
    }
}