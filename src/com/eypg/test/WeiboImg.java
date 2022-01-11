package com.eypg.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeiboImg {


    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; // 用数据装
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outstream.write(buffer, 0, len);
        }
        outstream.close();
        inStream.close();
        // 关闭流一定要记得。

        return outstream.toByteArray();

    }

    public static void getImage(String urlPath, String filePath) throws Exception {

        // 要下载的图片的地址，
//	  urlPath = "http://tp1.sinaimg.cn/2328409452/50/5609062079";

        URL url = new URL(urlPath);// 获取到路径

        // http协议连接对象
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");// 这里是不能乱写的，详看API方法

        conn.setConnectTimeout(6 * 1000);

        // 别超过10秒。

//	  System.out.println(conn.getResponseCode());

        if (conn.getResponseCode() == 200) {
            InputStream inputStream = conn.getInputStream();

            byte[] data = readStream(inputStream);

            File file = new File(filePath);// 给图片起名子

            FileOutputStream outStream = new FileOutputStream(file);// 写出对象

            outStream.write(data);// 写入

            outStream.close(); // 关闭流
        }
    }

    public static void main(String[] args) throws Exception {
        WeiboImg testImg = new WeiboImg();
        testImg.getImage("http://tp1.sinaimg.cn/2328409452/50/5609062079", "e:\\weiboImg\\i.jpg");
    }
}
