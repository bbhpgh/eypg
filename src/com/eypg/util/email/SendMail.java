package com.eypg.util.email;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eypg.action.ListAction;
import com.eypg.util.ApplicationListenerImpl;


public class SendMail {

    String sender;
    String password;
    String smtpServer;
    String recipient;
    String subject;
    List<String> fileAttachment;
    String content;
    private final Log log = LogFactory.getLog(SendMail.class);


    public SendMail(String sender, String password, String smtpServer,
                    String recipient, String subject, String content
            , List<String> fileAttachment
    ) {
        // 初始化信息
        this.sender = sender;
        this.password = password;
        this.smtpServer = smtpServer;
        this.recipient = recipient;
        this.subject = subject;
        this.fileAttachment = fileAttachment;
        this.content = content;
    }

    public boolean sendMail() {
        boolean flag = false;
        // 配置服务器属性
        Properties proper = new Properties();
        proper.put("mail.smtp.host", smtpServer); // smtp服务器
        proper.put("mail.smtp.auth", "true"); // 是否smtp认证
        proper.put("mail.smtp.port", "25"); // 设置smtp端口
        proper.put("mail.transport.protocol", "smtp"); // 发邮件协议
        proper.put("mail.store.protocol", "pop3"); // 收邮件协议
        proper.setProperty("mail.pop3.disabletop", "true");
        // 配置邮件接收地址
        InternetAddress[] receiveAddress = new InternetAddress[1];
        try {
            receiveAddress[0] = new InternetAddress(recipient);
        } catch (AddressException e) {
            e.printStackTrace();
        }
        // smtp认证，获取Session
        SmtpAuth sa = new SmtpAuth();
        sa.setUserinfo(sender, password);
        Session session = Session.getInstance(proper, sa);
        session.setPasswordAuthentication(new URLName(smtpServer), sa.getPasswordAuthentication());
        // 构建邮件体
        MimeMessage sendMess = new MimeMessage(session);
        MimeBodyPart mbp = new MimeBodyPart();
        MimeMultipart mmp = new MimeMultipart();
        try {
            // 邮件文本内容text/plain为文本形式.内容若有链接刚链接不可用.text/html为html形式.链接可用.
            mbp.setContent(content, "text/html; charset=UTF-8");
            mmp.addBodyPart(mbp);
            // 附件处理
            if (fileAttachment != null && fileAttachment.size() > 0) {
                for (String filePath : fileAttachment) {
                    if (filePath != null && !"".equals(filePath.trim())) {
                        DataSource source = new FileDataSource(filePath);
                        String name = source.getName();
                        mbp = new MimeBodyPart();
                        mbp.setDataHandler(new DataHandler(source));
                        // 将附件名称转码，防止中文乱码问题
                        mbp.setFileName(MimeUtility.encodeText(name));
                        mmp.addBodyPart(mbp);
                    }
                }
            }

            // 邮件整体
            sendMess.setSubject(subject);
            sendMess.setContent(mmp);
            //设置自定义发件人昵称
            String nick = "";
            try {
                nick = javax.mail.internet.MimeUtility.encodeText(ApplicationListenerImpl.sysConfigureJson.getSaitName());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            // 发送邮件
            sendMess.setFrom(new InternetAddress(sender, nick));
            //邮件紧急度
            sendMess.setHeader("X-Priority", "1");
            sendMess.setRecipients(Message.RecipientType.TO, receiveAddress);
            Transport.send(sendMess);
            flag = true;
            log.info("邮件发送成功! 标题:" + subject + "  收件人:" + recipient + "  发件人:" + sender);
        } catch (Exception ex) {
            log.error("邮件发送失败!!! 标题:" + subject + "  收件人:" + recipient + "  发件人:" + sender, ex);
        }

        return flag;
    }
}