package com.eypg.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class CutImages {
    // ===源图片路径名称如：c:\1.jpg
    private String srcpath;
    // ===剪切图片存放路径名称。如：c:\2.jpg
    private String subpath;
    // ===剪切点x坐标
    private int x;
    private int y;
    // ===剪切点宽度
    private int width;
    private int height;

    public CutImages() {

    }

    public CutImages(int x, int y, int width, int height, String inFile, String outFile) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.srcpath = inFile;
        this.subpath = outFile;
    }

    /**
     * 对图片裁剪，并把裁剪完的新图片保存 。
     */

    public void cut() throws IOException {
        FileInputStream is = null;
        ImageInputStream iis = null;
        try {

            // 读取图片文件
            is = new FileInputStream(srcpath);
            /*
             *
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader
             *
             * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .
             *
             * (例如 "jpeg" 或 "tiff")等 。
             *
             */
            String fileName = srcpath.substring(srcpath.lastIndexOf(".") + 1, srcpath.length());

            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(fileName);

            ImageReader reader = it.next();

            // 获取图片流
            iis = ImageIO.createImageInputStream(is);
            /*
             *
             * <p>iis:读取源。true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。
             *
             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
             *
             * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
             *
             */

            reader.setInput(iis, true);

            /*
             *
             * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
             *
             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件
             *
             * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回
             *
             * ImageReadParam 的实例。
             *
             */

            ImageReadParam param = reader.getDefaultReadParam();

            /*
             *
             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
             *
             * 的左上顶点的坐标(x，y)、宽度和高度可以定义这个区域。
             *
             */

            Rectangle rect = new Rectangle(x, y, width, height);

            // 提供一个 BufferedImage，将其用作解码像素数据的目标。

            param.setSourceRegion(rect);

            /*
             *
             * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将
             *
             * 它作为一个完整的 BufferedImage 返回。
             *
             */

            BufferedImage bi = reader.read(0, param);
            // 保存新图片
            ImageIO.write(bi, fileName, new File(subpath));

        } finally {
            if (is != null)
                is.close();
            if (iis != null)
                iis.close();
        }
    }

    /**
     * /** 缩放图像
     *
     * @param file    源图像文件地址
     * @param result  缩放后的图像地址
     * @param widths  图片宽度
     * @param heights 图片高度
     * @return
     */
    public static File scale(File file, int widths, int heights) {
//        String newPath = CutImages.getPath(file);
        String filePath = file.getAbsolutePath();
        String fileName = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
        try {
            BufferedImage src = ImageIO.read(file); // 读入文件  
            Image image = src.getScaledInstance(widths, heights, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(widths, heights, BufferedImage.SCALE_SMOOTH);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();
            ImageIO.write(tag, fileName, file);// 输出到文件流  
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 根据路径生成新路径
     *
     * @param result 根据给定的路径生成一个同文件夹下的路径，区别是文件名称前加Copy
     * @return
     */
    public static String getPath(String file) {
        String name = file.substring(file.lastIndexOf("\\") + 1, file.lastIndexOf("."));
        file = file.replaceAll(name, name);
        return file;
    }

    /**
     * 缩放图片
     *
     * @param width  输出宽度
     * @param height 输出高度
     * @param input  输入流
     * @param output 输出流
     * @param format 输出格式
     * @return
     * @throws Exception
     */
    public static boolean convert(int width, int height, InputStream input,
                                  OutputStream output, String format) throws Exception {
        // 输入  
        BufferedImage inputImage = ImageIO.read(input);
        // 转换  
        RenderedImage im = (RenderedImage) convert(height, height, inputImage);
        // 输出  
        return ImageIO.write(im, format, output);
    }

    /**
     * 转换压缩算法
     *
     * @param input  输入文件
     * @param output 输出文件
     * @return
     * @throws Exception
     */
    public static boolean convert(File input, File output) throws Exception {
        // 输入  
        BufferedImage inputImage = ImageIO.read(input);

        // 转换  
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        RenderedImage im = (RenderedImage) convert(width, height, inputImage);
        String outputFilename = output.getName();
        String format = outputFilename.substring(outputFilename
                .lastIndexOf('.') + 1);
        // 输出  
        return ImageIO.write(im, format, output);
    }

    /**
     * 缩放图片
     *
     * @param width  输出宽度
     * @param height 输出高度
     * @param input  输入文件
     * @param output 输出文件
     * @return
     * @throws Exception
     */
    public static boolean convert(int width, int height, File input, File output)
            throws Exception {
        // 输入  
        BufferedImage inputImage = ImageIO.read(input);
        // 转换  
        RenderedImage im = (RenderedImage) convert(width, height, inputImage);
        String outputFilename = output.getName();
        String format = outputFilename.substring(outputFilename
                .lastIndexOf('.') + 1);
        // 输出  
        return ImageIO.write(im, format, output);
    }

    /**
     * 缩放图片
     *
     * @param width  输出宽度
     * @param height 输出高度
     * @param input  输入路径
     * @param output 输出路径
     * @return
     * @throws Exception
     */
    public static boolean convert(int width, int height, String inputPath,
                                  String outputPath) throws Exception {
        return convert(width, height, new File(inputPath), new File(outputPath));
    }

    /**
     * 转换
     *
     * @param width  输出宽度
     * @param height 输出高度
     * @param input  BufferedImage
     * @return BufferedImage
     * @throws Exception
     */
    private static BufferedImage convert(int width, int height,
                                         BufferedImage input) throws Exception {
        // 初始化输出图片  
        BufferedImage output = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // 重新绘图  
        Image image = input.getScaledInstance(output.getWidth(), output
                .getHeight(), output.getType());

        output.createGraphics().drawImage(image, null, null);

        return output;
    }

    /**
     * 等比缩放图片
     *
     * @param width  输出宽度
     * @param height 输出高度
     * @param input  输入流
     * @param output 输出流
     * @return
     * @throws Exception
     */
    public static boolean equimultipleConvert(int width, int height,
                                              String input, String output) throws Exception {
        return equimultipleConvert(width, height, new File(input), new File(
                output));
    }

    /**
     * 等比缩放图片
     *
     * @param width  输出宽度
     * @param height 输出高度
     * @param input  输入流
     * @param output 输出流
     * @return
     * @throws Exception
     */
    public static boolean equimultipleConvert(int width, int height,
                                              File input, File output) throws Exception {
        // 输入  
        BufferedImage image = ImageIO.read(input);

        // 重新核算尺寸  
        if (image.getWidth() > 0 && image.getHeight() > 0) {
            if ((image.getWidth() / image.getHeight()) >= (width / height)) {
                if (image.getWidth() > width) {
                    height = (image.getHeight() * width) / image.getWidth();
                } else {
                    width = image.getWidth();
                    height = image.getHeight();
                }
            } else {
                if (image.getHeight() > height) {
                    width = (image.getWidth() * height) / image.getHeight();
                } else {
                    width = image.getWidth();
                    height = image.getHeight();
                }
            }
        }

        // 转换 输出  
        return convert(width, height, input, output);
    }

    /**
     * 缩放图像（按高度和宽度缩放）
     *
     * @param srcImageFile 源图像文件地址
     * @param result       缩放后的图像地址
     * @param height       缩放后的高度
     * @param width        缩放后的宽度
     * @param bb           比例不对时是否需要补白：true为补白; false为不补白;
     * @throws Exception
     */
    public final static void scale2(String srcImageFile, String result, int height, int width, boolean bb) throws Exception {
        try {
            double ratio = 0.0; // 缩放比例
            File f = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue()
                            / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform
                        .getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {//补白
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
//            convert(width, height, f, new File(result));
            ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHeight() {

        return height;

    }

    public void setHeight(int height) {

        this.height = height;

    }

    public String getSrcpath() {

        return srcpath;

    }

    public void setSrcpath(String srcpath) {

        this.srcpath = srcpath;

    }

    public String getSubpath() {

        return subpath;

    }

    public void setSubpath(String subpath) {

        this.subpath = subpath;

    }

    public int getWidth() {

        return width;

    }

    public void setWidth(int width) {

        this.width = width;

    }

    public int getX() {

        return x;

    }

    public void setX(int x) {

        this.x = x;

    }

    public int getY() {

        return y;

    }

    public void setY(int y) {

        this.y = y;

    }

    public static void main(String[] args) throws Exception {
//		String name = "d:\\1000_blog.jpg";
//		CutImages o = new CutImages(0, 76, 100, 100,name,"d:\\2.jpg");
//		o.cut();
//		File file = new File("d:\\aboutbanner.png");
//		scale(file, 300, 300);

//		System.out.println(CutImages.equimultipleConvert(310, 310, new File(
//        "d:\\02.jpg"), new File("d:\\02_mid.jpg")));

        scale2("d:\\QQ图片20130830135505.jpg", "d:\\1231.jpg", 300, 300, true);

    }

}
