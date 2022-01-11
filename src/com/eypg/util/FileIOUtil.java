package com.eypg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class FileIOUtil {
    /**
     * 从文本文件里读出用户名密码.
     * 文件里存的是用户名和密码键值对.
     * 文件必须是用户名和密码用regular隔开．一行写一对用户名密码对.用户名为key
     *
     * @param path    文件路径
     * @param regular
     * @throws Exception
     * @throws IOException
     * @author 宋冲    <mailto:song316@gmail.com/>
     * @return 返回用户名密码组成的Map．	Map<username,password>;
     */
    public static Map<String, String> readFile2Map(String path, String regular) throws IOException {
        if (StringUtils.isBlank(regular)) {
            return null;
        }
        if (StringUtils.isBlank(path)) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        File file = new File(path);
        FileReader fReader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(fReader);
        String temp = null;
        String[] tempArr = null;
        while ((temp = bReader.readLine()) != null) {
            if (StringUtils.isNotBlank(temp)) {
                tempArr = temp.split(regular);
                if (tempArr.length > 1) {
                    String username = tempArr[0];
                    String password = tempArr[1];
                    if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                        map.put(username.trim(), password.trim());
                    }
                }
            }
            temp = null;
            tempArr = null;
        }
        bReader.close();
        fReader.close();
        file = null;
        return map;
    }

    /**
     * 利用BufferedReader类读取path路径文件内容为String
     *
     * @param path 文件路径
     * @return 文件内容
     */
    public static String readFile2Str(String path) {
        if (StringUtils.isBlank(path)) {
            return null;
        }
        FileInputStream fis = null;
        InputStreamReader isr = null;
        StringBuilder str = null;
        try {
            fis = new FileInputStream(path);
            isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            str = new StringBuilder();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                str.append(temp);
                temp = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fis = null;
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isr = null;
            }
        }
        return str.toString();
    }

    /**
     * 把输入流转换成String返回.
     *
     * @param is       输入流
     * @param encoding 编码
     * @return 转换后的String
     */
    public static String convertInputStream2Str(InputStream is, String encoding) {
        if (is == null) {
            return null;
        }
        if (StringUtils.isBlank(encoding)) {
            return null;
        }
        StringBuffer sb = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, encoding));
            String tempbf;
            sb = new StringBuffer(100);
            while ((tempbf = br.readLine()) != null) {
                sb.append(tempbf);
                tempbf = null;
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                br = null;
            }
        }
        return null;
    }

    /**
     * 传入一个String和path路径.把这个String写入path生成的文件里.
     *
     * @param content String内容
     * @param path    文件路径.
     * @throws Exception
     * @return 如果写入成功返回true.失败返回flase.
     */
    public static boolean writeString2File(String content, String path) throws Exception {
        if (StringUtils.isBlank(content)) {
            return false;
        }
        if (StringUtils.isBlank(path)) {
            return false;
        }
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileWriter = null;
            }
            if (file != null) {
                file = null;
            }
        }
        return false;
    }
}
