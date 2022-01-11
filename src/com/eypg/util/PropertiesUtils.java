package com.eypg.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 读写properties文件
 *
 * @author whip
 */
public class PropertiesUtils {
    private Properties propertie;
    private FileInputStream inputFile;
    private FileOutputStream outputFile;


    public PropertiesUtils() {
        propertie = new Properties();
    }


    public PropertiesUtils(String filePath) {
        propertie = new Properties();
        try {
            inputFile = new FileInputStream(filePath);
            propertie.load(inputFile);
            inputFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("装载文件--->失败!");
            ex.printStackTrace();
        }
    }


    public String getValue(String key) {
        if (propertie.containsKey(key)) {
            String value = propertie.getProperty(key);
            return value;
        } else
            return "";
    }


    public String getValue(String fileName, String key) {
        try {
            String value = "";
            inputFile = new FileInputStream(fileName);
            propertie.load(inputFile);
            inputFile.close();
            if (propertie.containsKey(key)) {
                value = propertie.getProperty(key);
                return value;
            } else
                return value;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }


    public void clear() {
        propertie.clear();
    }


    public void setValue(String key, String value) {
        propertie.setProperty(key, value);
    }


    public void saveFile(String fileName, String description) {
        try {
            outputFile = new FileOutputStream(fileName);
            propertie.store(outputFile, description);
            outputFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PropertiesUtils pu = new PropertiesUtils("./config/system.properties");

        String admin = pu.getValue("system.admin");
        String password = pu.getValue("system.password");

        System.out.println("admin = " + admin);
        System.out.println("password = " + password);

        PropertiesUtils pu_ = new PropertiesUtils();

        String admin_ = pu_.getValue("./config/system.properties", "system.admin");
        String password_ = pu_.getValue("./config/system.properties", "system.password");

        System.out.println("admin_ = " + admin_);
        System.out.println("password_ = " + password_);
//	        pu_.clear();
//        pu_.setValue("min", "999");
//        pu_.setValue("max", "1000");
//        pu_.saveFile("./config/system.properties", "test");

//	        PropertiesUtils _pu = new PropertiesUtils();
//	        _pu.setValue("min", "10");
//	        _pu.setValue("max", "1000");
//	        _pu.saveFile(".\config\system.properties");
    }
}