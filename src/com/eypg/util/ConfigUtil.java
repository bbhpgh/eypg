/**
 * 配置文件工具类
 */
package com.eypg.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext*.xml"})
@Repository
public class ConfigUtil {
    final static Log log = LogFactory.getLog(ConfigUtil.class);
    private static Properties properties;

    static {
        InputStream is = ConfigUtil.class.getClassLoader().getResourceAsStream(
                "config.properties");
        properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            log.error("配置文件config.properties加载失败!", e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                log.error("关闭流失败!", e);
            }
        }
    }

    /**
     * 根据key返回值。
     *
     * @param key
     * @return 如果没有相关值。则返回“”
     */
    public static String getValue(String key) {
        if (StringUtil.isBlank(key)) {
            return null;
        }
        String temp = properties.getProperty(key);
        if (StringUtil.isBlank(temp) || "null".equals(temp)) {
            return "";
        }
        return temp;
    }

    public static void setValue(String key, String value) throws IOException {
        if (StringUtil.isNotBlank(key) && StringUtil.isNotBlank(value)) {
            String separator = System.getProperty("file.separator");
            String path = System.getProperty("user.dir") + separator
                    + "config.properties";
            System.err.println(path);
            OutputStream fos = new FileOutputStream(path);
            properties.setProperty(key, value);
            properties.store(fos, "Update'" + key + "'value");
        }
    }

    public static void main(String[] args) {
//		String readfile = "d:" + File.separator + "readfile.properties";
//		String writefile = "d:" + File.separator + "writefile.properties";
//		String readxmlfile = "d:" + File.separator + "readxmlfile.xml";
//		String writexmlfile = "d:" + File.separator + "writexmlfile.xml";
//		String readtxtfile = "d:" + File.separator + "readtxtfile.txt";
//		String writetxtfile = "d:" + File.separator + "writetxtfile.txt";
//
//		readPropertiesFile(readfile); // 读取properties文件
//		writePropertiesFile(writefile); // 写properties文件
//		readPropertiesFileFromXML(readxmlfile); // 读取XML文件
//		writePropertiesFileToXML(writexmlfile); // 写XML文件
//		readPropertiesFile(readtxtfile); // 读取txt文件
//		writePropertiesFile(writetxtfile); // 写txt文件
    }

    // 读取资源文件,并处理中文乱码
    public static void readPropertiesFile(String filename) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filename);
            properties.load(inputStream);
            inputStream.close(); // 关闭流
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username = properties.getProperty("username");
        String passsword = properties.getProperty("password");
        String chinese = properties.getProperty("chinese");
        try {
            chinese = new String(chinese.getBytes("ISO-8859-1"), "GBK"); // 处理中文乱码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(username);
        System.out.println(passsword);
        System.out.println(chinese);
    }

    // 读取XML文件,并处理中文乱码
    public static void readPropertiesFileFromXML(String filename) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filename);
            properties.loadFromXML(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username = properties.getProperty("username");
        String passsword = properties.getProperty("password");
        String chinese = properties.getProperty("chinese"); // XML中的中文不用处理乱码，正常显示
        System.out.println(username);
        System.out.println(passsword);
        System.out.println(chinese);
    }

    // 写资源文件，含中文
    public static void writePropertiesFile(String filename) {
        Properties properties = new Properties();
        try {
            OutputStream outputStream = new FileOutputStream(filename);
            properties.setProperty("username", "myname");
            properties.setProperty("password", "mypassword");
            properties.setProperty("chinese", "中文");
            properties.store(outputStream, "author: shixing_11@sina.com");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 写资源文件到XML文件，含中文
    public static void writePropertiesFileToXML(String filename) {
        Properties properties = new Properties();
        try {
            OutputStream outputStream = new FileOutputStream(filename);
            properties.setProperty("username", "myname");
            properties.setProperty("password", "mypassword");
            properties.setProperty("chinese", "中文");
            properties.storeToXML(outputStream, "author: shixing_11@sina.com");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
