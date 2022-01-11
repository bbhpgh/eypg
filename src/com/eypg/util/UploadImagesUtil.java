package com.eypg.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadImagesUtil {

    /**
     * 根据字符串创建本地目录 并按照日期建立子目录返回
     *
     * @param path
     * @return
     */
    public static String getFolder(String path, String id) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
//		path += "/" + formater.format(new Date());
        path += "/" + id;
        File dir = new File(path);
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    public static void main(String[] args) {
        getFolder("d:\\123", "201211");
    }


}
