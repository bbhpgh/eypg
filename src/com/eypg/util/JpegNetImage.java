package com.eypg.util;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class JpegNetImage extends AbstractNetImage {
    public JpegNetImage(String _url) {
        super(_url);
    }

    public JpegNetImage() {
    }

    // JPEG编码
    protected void encode(FileOutputStream out, BufferedImage bufferedImage)
            throws ImageFormatException, IOException {
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(bufferedImage);
    }

}
