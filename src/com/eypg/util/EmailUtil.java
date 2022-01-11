/**
 * 发送邮件的工具类
 * 异步
 * 多线程
 */
package com.eypg.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.eypg.util.email.EmailProtocol;
import com.eypg.util.email.SendMail;

/**
 * @author Ryan <a href="mailto:song316@gmail.com">song316@gmail.com</a>
 *
 */
public class EmailUtil {
    private static ApplicationContext ctx = null;
    public static JavaMailSenderImpl sender = null;
    public static ExecutorService pool = null;
    private static final Log log = LogFactory.getLog(EmailUtil.class);
//	static{
//		ctx = new FileSystemXmlApplicationContext("src/springMail.xml");
//		sender = (JavaMailSenderImpl) ctx.getBean("mailSender");	
//		 创建一个可重用固定线程数的线程池
//		pool = Executors.newFixedThreadPool(5);
//	}

    public static void sendEmail(String mailFrom, String password, String mailto, String subject, String content, List<String> attachList) {
        JavaMail javaMail = new JavaMail(mailFrom, password, mailto, subject, content, attachList);
        EmailUtil.pool.execute(javaMail);
        EmailUtil.pool.shutdown();
    }

    public static boolean sendEmail(String mailFrom, String password, String mailto, String subject, String content) {
        EmailProtocol eProtocol = new EmailProtocol(mailFrom);
        SendMail sendMail = new SendMail(mailFrom, password, eProtocol.getSmtpProtocol(), mailto, subject, content, null);
        return sendMail.sendMail();
    }

    /**
     * 群发邮件.
     * @param srcFilePath  发件人文件.帐号密码用|分开.
     * @param desFilePath  收件人帐号文件.
     */
    public static void batchSendEmail(String srcFilePath, String desFilePath, String subject, String content, List<String> attachList) {
        if (StringUtil.isBlank(srcFilePath)) {
            log.error("群发邮件时srcFilePath为空!");
            return;
        }
        if (StringUtil.isBlank(desFilePath)) {
            log.error("群发邮件时desFilePath为空!");
            return;
        }
        Map<String, String> srcMap = null;
        Map<String, String> desMap = null;
        int i = 0;
        try {
            srcMap = FileIOUtil.readFile2Map(srcFilePath, "[|]");
            desMap = FileIOUtil.readFile2Map(desFilePath, "[|]");
            if (srcMap != null && srcMap.size() > 0 && desMap != null && desMap.size() > 0) {
                String mailFrom = null;
                String password = null;
                String[] srcArray = new String[srcMap.size()];
                srcMap.keySet().toArray(srcArray);
                //循环收件人.
                for (String mailto : desMap.keySet()) {
                    //如果循环到最后一个发件人,则i置0.
                    if (i >= srcMap.size()) {
                        i = 0;
                    }
                    mailFrom = srcArray[i];
                    password = srcMap.get(mailFrom);
                    JavaMail javaMail = new JavaMail(mailFrom, password, mailto, subject, content, attachList);
                    EmailUtil.pool.execute(javaMail);
                    i++;
                }
                EmailUtil.pool.shutdown();
            } else {
                log.error("群发邮件时发件人或者收件人为空!");
            }
        } catch (IOException e) {
            log.error("群发邮件时读取文件失败!", e);
        }
    }
}

/**
 * 用javaMail发邮件.
 * @author Ryan <a href="mailto:song316@gmail.com">song316@gmail.com</a>
 *
 */
class JavaMail implements Runnable {
    String mailFrom = null;
    String password = null;
    String mailto = null;
    String subject = null;
    String content = null;
    List<String> attachList = null;

    /**
     * 全参构造方法.
     */
    JavaMail(String mailFrom, String password, String mailto, String subject, String content, List<String> attachList) {
        this.mailFrom = mailFrom;
        this.password = password;
        this.mailto = mailto;
        this.subject = subject;
        this.content = content;
        this.attachList = attachList;
    }

    public void run() {
        this.sendMailByJavaMail(mailFrom, password, mailto, subject, content, attachList);
    }

    /**
     * 发送邮件.可加多个附件.用javaMail发送邮件.
     * @param sender    发件人邮箱号
     * @param password  发件人密码
     * @param recipient 收件人
     * @param subject   主题
     * @param fileAttachment  List<String> 附件本地地址集合
     * @param content  邮件内容
     * @return 发送成功返回true, 失败返回false
     */
    private synchronized boolean sendMailByJavaMail(String mailFrom, String password, String mailto, String subject, String content, List<String> attachList) {
        EmailProtocol eProtocol = new EmailProtocol(mailFrom);
        SendMail sendMail = new SendMail(mailFrom, password, eProtocol.getSmtpProtocol(), mailto, subject, content, attachList);
        return sendMail.sendMail();
    }
}

/**
 * 用spring发邮件.
 * @author Ryan <a href="mailto:song316@gmail.com">song316@gmail.com</a>
 */
class SpringEmail implements Runnable {
    private final Log log = LogFactory.getLog(SpringEmail.class);
    JavaMailSenderImpl sender = null;
    String mailto = null;
    String subject = null;
    String content = null;
    List<String> attachList = null;

    /**
     * 全参构造方法.
     */
    public SpringEmail(JavaMailSenderImpl sender, String mailto, String subject, String content, List<String> attachList) {
        this.sender = sender;
        this.mailto = mailto;
        this.subject = subject;
        this.content = content;
        this.attachList = attachList;
    }

    public void run() {
        this.sendMailBySpring(sender, mailto, subject, content, attachList);
    }

    /**
     * 发送邮件.可加多个附件.用spring发送邮件.
     * 但不加附件时候,有些邮箱可能收不到.
     * 126.com,gmail.com,qq.com  可以收简单邮件.
     * sunyard.com 收不到简单邮件.
     * @param sender   JavaMailSenderImpl对象
     * @param mailto   发件人.
     * @param subject  主题
     * @param content  邮件内容
     * @param attachList  附件集合
     * @return 如果发送成功返回1, 发送失败返回0.
     */
    private synchronized boolean sendMailBySpring(JavaMailSenderImpl sender, String mailto, String subject, String content, List<String> attachList) {
        MimeMessage msg = sender.createMimeMessage();
        try {
            //创建MimeMessageHelper实例，第2个参数表示是否为multipart邮件，第3个参数表示MimeMessage的编码
            //true表示为multipart邮件
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "GBK");
            helper.setFrom(sender.getUsername());
            helper.setTo(mailto);
            helper.setSubject(MimeUtility.encodeWord(subject));
            helper.setText("<META http-equiv=Content-Type content='text/html; charset=GBK'>" + content, true);
            if (attachList != null && attachList.size() > 0) {
                for (String str : attachList) {
                    helper.addAttachment(MimeUtility.encodeWord(str), new File(str));
                }
            }
            sender.send(msg);
        } catch (MessagingException e) {
            log.error("邮件发送失败!!! 标题:" + subject + "  收件人:" + mailto + "  发件人:" + sender.getUsername(), e);
            return false;
        } catch (UnsupportedEncodingException e) {
            log.error("邮件发送失败!!! 标题:" + subject + "  收件人:" + mailto + "  发件人:" + sender.getUsername(), e);
            return false;
        }
        log.info("邮件发送成功! 标题:" + subject + "  收件人:" + mailto + "  发件人:" + sender.getUsername());
        return true;
    }
}