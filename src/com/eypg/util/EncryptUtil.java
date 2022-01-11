package com.eypg.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密工具类
 *
 * @author Ryan  <a href="mailto:song316@gmail.com"></a>
 * 已做同步
 */
public class EncryptUtil {

    /**
     * Base64加密
     *
     * @param str 要加密的字符串
     * @return 返回加密后字符串.
     */
    public synchronized static String encryptBASE64(String str) {
        if (str == null || "".equals(str.trim())) {
            System.err.println("EncryptUtil.encryptBASE64()==>>>>参数为空!");
            return null;
        }
        return (new BASE64Encoder()).encodeBuffer(str.getBytes());
    }

    /**
     * Base64解密
     *
     * @param key 要解密的字符串
     * @return 返回解密后的字符串
     */
    public synchronized static String decryptBASE64(String str) {
        if (str == null || "".equals(str.trim())) {
            System.err.println("EncryptUtil.decryptBASE64()==>>>>参数为空!");
            return null;
        }
        byte[] tmp;
        try {
            tmp = (new BASE64Decoder()).decodeBuffer(str);
            return new String(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MD5加密
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串.
     */
    public synchronized static String encryptMD5(String str) {
        if (str == null || "".equals(str.trim())) {
            System.err.println("EncryptUtil.encryptMD5()==>>>>参数为空!");
            return null;
        }
        MessageDigest md5;
        // 用来将字节转换成 16 进制表示的字符
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] tmp = md5.digest();
            // 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32 个字符
            char chStr[] = new char[16 * 2];
            // 表示转换结果中对应的字符位置
            int k = 0;
            // 从第一个字节开始，对 MD5 的每一个字节进行 16 进制字符的转换
            for (int i = 0; i < 16; i++) {
                byte bTmp = tmp[i];
                // 取字节中高 4 位的数字转换,>>> 为逻辑右移，将符号位一起右移
                chStr[k++] = hexDigits[bTmp >>> 4 & 0xf];
                // 取字节中低 4 位的数字转换
                chStr[k++] = hexDigits[bTmp & 0xf];
            }
            return new String(chStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

//	public static void main(String[] args) {
//		System.out.println(EncryptUtil.encryptBASE64("123adf"));
//		System.out.println(EncryptUtil.decryptBASE64("MTIzYWRm"));
//		System.out.println(EncryptUtil.encryptMD5("123adf"));
//	}
}
