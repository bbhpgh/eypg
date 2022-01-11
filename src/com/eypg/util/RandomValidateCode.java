package com.eypg.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eypg.action.ListAction;

public class RandomValidateCode {

    public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";//放到session中的key
    private Random random = new Random();
    private String randString = "23456789ABCDEFGHJKLMNOPQRSTUVWXYZ";//随机产生的字符串
//    private String randString = "我们是1元拍购";//随机产生的字符串

    private int width = 100;//图片宽
    private int height = 35;//图片高
    private int lineSize = 0;//干扰线数量
    private int stringNum = 5;//随机产生字符数量

    /*
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 26);
    }

    /*
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片
     */
    public void getRandcode(HttpServletRequest request,
                            HttpServletResponse response) {
        try {
//          HttpSession session = request.getSession();
            //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
            BufferedImage image = new BufferedImage(width, height, BufferedImage.SCALE_SMOOTH);
            Graphics g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
            g.fillRect(0, 0, width, height);
//          g.setFont(new Font("Times New Roman",Font.LAYOUT_LEFT_TO_RIGHT,26));
//          g.setColor(getRandColor(110, 133));
            //绘制干扰线
//          for(int i=0;i<=lineSize;i++){
//              drowLine(g);
//          }
            //绘制随机字符
            String randomString = "";
            for (int i = 1; i <= stringNum; i++) {
                randomString = drowString(g, randomString, i);
            }
            if (request.isRequestedSessionIdFromCookie()) {
                Cookie cookie = new Cookie("rndCode", randomString);
                cookie.setMaxAge(-1);
                cookie.setPath("/");
                cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                response.addCookie(cookie);
            }
//          session.removeAttribute(RANDOMCODEKEY);
//          session.setAttribute(RANDOMCODEKEY, randomString);
//          System.out.println(randomString);
            g.dispose();
            ImageIO.write(image, "JPEG", response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
//        g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        g.setColor(new Color(20, 223, 228));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString += rand;
        g.translate(19, 0);
//        g.drawString(rand, 13*i, 16);
        try {
            g.drawString(rand, -18, 26);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return randomString;
    }

    /*
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /*
     * 获取随机的字符
     */
    public String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }
}