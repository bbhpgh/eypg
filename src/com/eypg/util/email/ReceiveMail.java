package com.eypg.util.email;


import java.util.Properties;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class ReceiveMail {

    String host = "";
    String username = "";
    String password = "";

    public ReceiveMail(String host, String username, String password) {
        // 初始化主机
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public Folder receiveMail() throws MessagingException {
        // 配置服务器属性
        Properties props = new Properties();

        props.put("mail.smtp.port", "25"); // 设置smtp端口
        props.put("mail.transport.protocol", "smtp"); // 发邮件协议
        props.put("mail.store.protocol", "pop3"); // 收邮件协议
        // 获取会话
        Session session = Session.getDefaultInstance(props, null);
        // 2. 获取 Store 并连接到服务器
        Store store = session.getStore("pop3");
        store.connect(host, username, password);
        // 获取该用户Folder对象，并以只读方式打开
        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_ONLY);
        // 检索所有邮件，按需填充
        return folder;
    }
}
