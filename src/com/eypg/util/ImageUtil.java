package com.eypg.util;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Encoder;

/**
 * 图片处理类
 */
public class ImageUtil {

    public ImageUtil() {
        super();
    }

    protected final static Log _log = LogFactory.getLog(ImageUtil.class);

    public static void main(String[] args) {
        String imgFile = "d:\\123.jpg";// 待处理的图片
        String netUrl = "http://t3.qlogo.cn/mbloghead/35f3469a2fa055c25ec2/120";
//		String _netUrl = "http://www.google.cn/intl/zh-CN/images/logo_cn.gif";
        String fileFolder = "D:/weibo-image/20110120/123/456/789/";
        String fileName = "posterface.jpg";
//		readImageToLabel(netUrl);
//		System.out.println(imageToBase64(imgFile));
        String meinv_url = "http://126.fm/nlC2n";
        String face_url = "http://tp4.sinaimg.cn/1427388087/180/1297002209/1";
        for (int i = 0; i < 100; i++) {
            readNetImageToLocal(meinv_url, "d:/meinv/", i + "meinv.jpg");
        }

    }

    public static String imageToBase64(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        String cc = encoder.encode(data);// 返回Base64编码过的字节数组字符串
        return cc;
    }

    /**
     * 把image读到awt的label中
     */
    public static void readImageToLabel(String netUrl) {
        Image image = null;
        try {
            // // Read from a file
            // File sourceimage = new File("source.gif");
            // //source.gif图片要与HelloJava.java同在一目录下
            // image = ImageIO.read(sourceimage);
            //
            // // Read from an input stream
            // InputStream is = new BufferedInputStream(
            // new FileInputStream("mid.jpg"));
            // //mid.jpg图片要与HelloJava.java同在一目录下
            // image = ImageIO.read(is);
            // Read from a URL
            URL url = new URL(netUrl);
//			URL url = new URL("http://t0.qlogo.cn/mbloghead/ebdf8d7094f7c6a7b98a/120");
            image = ImageIO.read(url);
        } catch (IOException e) {
        }
        // Use a label to display the image
        JFrame frame = new JFrame();
        JLabel label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        // 关闭窗口--退出调试
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void makeDir(String fileFolder) {
        File file = new File(fileFolder);
        if (!file.exists() && !file.isDirectory())
            file.mkdir();
    }

    /**
     * 打开文件夹
     */
    private static void openFileSystemDir(String dir) {
        String openDirCmdString = "cmd.exe /c start ";
        String openDirCmd = openDirCmdString + dir;
        System.out.println(openDirCmd);
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(openDirCmd);
            BufferedReader bf = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String line = "";
            while ((line = bf.readLine()) != null)
                System.out.println(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readNetImageToLocal(String netUrl, String fileFolder, String fileName) {
        String filePath = fileFolder + fileName;
//		System.out.println("filePath:"+filePath);
        List<String> list = new ArrayList<String>();
        String supDir = StringUtils.substring(fileFolder, 0, StringUtils.indexOf(fileFolder, "/") + 1);
        String subDir = StringUtils.substring(fileFolder, StringUtils.indexOf(fileFolder, "/") + 1);
        for (int i = 0; i < StringUtils.countMatches(fileFolder, "/") - 1; i++) {
            String dir = supDir + StringUtils.substring(subDir, 0, StringUtils.indexOf(subDir, "/") + 1);
            subDir = StringUtils.substring(subDir, StringUtils.indexOf(subDir, "/") + 1);
            list.add(dir);
            supDir = dir;
        }
        for (String s : list) {
//			System.out.println("s:"+s);
            makeDir(s);
        }
        AbstractNetImage ani = new JpegNetImage();
        try {
            ani.getImageFromUrl(netUrl, filePath);
            System.out.println("图片下载成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
//		openFileSystemDir(fileFolder);
    }

}