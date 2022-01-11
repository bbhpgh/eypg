package com.eypg.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.sun.image.codec.jpeg.ImageFormatException;

public abstract class AbstractNetImage {

    private String url = null;

    public AbstractNetImage(String url) {
        this.url = url;
    }

    public AbstractNetImage() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 设立该抽象方法的意图是要使具体子类实现不同格式图片的编码方法(jpeg等)
     */
    protected abstract void encode(FileOutputStream out,
                                   BufferedImage bufferedImage) throws ImageFormatException,
            IOException;

    private void getImageImpl(String newFilePath, int sizeReduceRank)
            throws MalformedURLException, IOException {
        if (url == null)
            return;
        Image image = javax.imageio.ImageIO.read(new URL(url));
        int width = image.getWidth(null) / sizeReduceRank;
        int height = image.getHeight(null) / sizeReduceRank;
        // 更改图片大小 sizeRank是原图的缩小的比例 若为2意思为将下载的文件保存为原理图片长宽的1/2
        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        FileOutputStream out = new FileOutputStream(newFilePath);
        encode(out, bufferedImage);
        image.flush();
        bufferedImage.flush();
        out.close();
    }

    /**
     * 取得原图片，缩小尺寸有参数sizeReduceRank决定
     */
    public final void getImage(String newFilePath, int sizeReduceRank)
            throws MalformedURLException, IOException {
        getImageImpl(newFilePath, sizeReduceRank);
    }

    /**
     * 取得原图片，尺寸不变
     */
    public final void getImage(String newFilePath)
            throws MalformedURLException, IOException {
        getImageImpl(newFilePath, 1);
    }

    /**
     * 直接指定网络图片的位置，和下载到本地文件系统的位置
     */
    public void getImageFromUrl(String url, String newFilePath)
            throws MalformedURLException, IOException {
        this.setUrl(url);
        this.getImage(newFilePath);
    }
}