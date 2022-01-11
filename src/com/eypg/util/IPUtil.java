
package com.eypg.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

/**
 * @author song
 */
public class IPUtil {

    /**
     * 根据IP地址,访问http://www.ip.cn.并返回该IP地址的归属地.
     *
     * @param str IP地址.
     * @return 返回IP地址的归属地.可能会包含网络商名.
     */
    public synchronized static String getSourceFromIP(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        URL url;
        HttpURLConnection urlConn;
        BufferedInputStream bis = null;
        StringBuffer sBuffer = new StringBuffer();
        try {
            url = new URL("http://www.ip.cn/getip.php?action=getip&ip_url=" + str);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setUseCaches(true);
            urlConn.setRequestMethod("GET");
            urlConn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
            bis = new BufferedInputStream(urlConn.getInputStream());
            int l;
            byte[] tmp = new byte[2048];
            while ((l = bis.read(tmp)) != -1) {
                sBuffer.append(new String(tmp, 0, l, "GB2312"));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bis = null;
            }
        }
//		System.err.println(sBuffer.toString());
        if (StringUtils.isNotBlank(sBuffer.toString())) {
            return StringUtils.substringBetween(sBuffer.toString(), "来自：", "</p>");
        }
        return null;
    }

    public static void main(String[] args) {
        System.err.println(IPUtil.getSourceFromIP("124.193.138.90"));
    }

}
