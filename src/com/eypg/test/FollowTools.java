package com.eypg.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

public class FollowTools {
    /**
     * 读取IP
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static List<Proxy> readProxyIP(String path) throws Exception {
//		Map<String, String> map = new HashMap<String, String>();
        List<Proxy> IpLists = new ArrayList<Proxy>();
        File file = new File(path);
        FileReader fReader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(fReader);
        String temp = null;
        String[] tempArr = null;
        while ((temp = bReader.readLine()) != null) {
            Proxy ipList = new Proxy();
            if (StringUtils.isNotBlank(temp)) {
                tempArr = temp.split("\\:");
                if (tempArr.length > 1) {
                    String ip = tempArr[0];
                    String proxy = tempArr[1];
                    if (StringUtils.isNotBlank(ip) && StringUtils.isNotBlank(proxy)) {
                        ipList.setIp(ip);
                        ipList.setPort(Integer.valueOf(proxy));
                        IpLists.add(ipList);
                    }
                }
            }
            temp = null;
            tempArr = null;
        }
        return IpLists;
    }


    public static String getPasswod() throws Exception {
        Random random = new Random();
        double pwd = random.nextLong();
        String pass = String.valueOf(pwd);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pass.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        String password = buf.toString();
        password = password.substring(0, 15);
        return password;
    }


    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {

    }

}
