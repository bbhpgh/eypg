package com.eypg.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.Date;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts2.ServletActionContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eypg.pojo.Product;
import com.eypg.pojo.Productimage;
import com.eypg.service.ProductImageService;
import com.eypg.service.ProductService;
import com.eypg.util.CutImages;
import com.eypg.util.UploadImagesUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext*.xml"})
@Repository
public class CrawlBuy360Test {
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;

    private static final int BUFFER_SIZE = 100 * 1024;
    private DefaultHttpClient httpClient;
    private HttpGet httpGet;
    private HttpPost httpPost;

    //文件上传
    private static void copy(File src, File dst) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                while (in.read(buffer) > 0) {
                    out.write(buffer);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void go() throws Exception {
        String url = "http://www.360buy.com/products/652-653-655.html";
//		 http://www.360buy.com/products/652-653-655-0-0-0-0-0-0-0-1-1-2.html
        httpClient = new DefaultHttpClient();
        org.apache.http.params.HttpParams params = httpClient.getParams();
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(params, 60000);
        org.apache.http.params.HttpConnectionParams.setSoTimeout(params, 60000);
        httpGet = new HttpGet(url);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String content = httpClient.execute(httpGet, responseHandler);
        Document document = Jsoup.parse(new URL(url), 60000);
        Elements elements = document.select("div.plist > ul > li");
        for (Element element : elements) {
            String productImg = "";
            String productName = "";
            String productTitle = "";
            int productType = 1000;
            int productPrice = 0;
            int productRealPrice = 0;
            String productDetail = "";
            String headImage = "";
            String Attribute_71 = "1";
            String strImg = element.select("div.p-img > a > img").first().attr("src");
            if (strImg.indexOf("360buyimg.com") != -1) {
                productImg = strImg;
            } else {
                String strImg2 = element.select("div.p-img > a > img").first().attr("src2");
                productImg = strImg2;
            }
            productName = element.select("div.p-name > a").text();

            String proUrl = element.select("div.p-name > a").attr("href");
            System.err.println(proUrl);
            Product product = new Product();
            File myFile = new File(productImg);
            String myFileFileName = myFile.getName();
            product.setAttribute71(String.valueOf(1));
            product.setProductDetail(proUrl);
            product.setProductName(productName);
            product.setProductPrice(0);
            product.setProductRealPrice(String.valueOf(0));
            product.setProductTitle(productName);
            product.setProductType(1001);
            productService.add(product);
            String productImgPath = "/productImg/show";
            if (myFile != null) {
                myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
                String imageFileName = new Date().getTime() + myFileFileName;
                File imageFile = new File(UploadImagesUtil.getFolder(ServletActionContext.getServletContext().getRealPath(productImgPath) + "/", String.valueOf(product.getProductId())) + "/" + imageFileName);
                copy(myFile, imageFile);
                CutImages.equimultipleConvert(200, 200, imageFile, imageFile);
                product.setHeadImage(productImgPath + "/" + String.valueOf(product.getProductId()) + "/" + imageFileName);
            }

            productService.add(product);

            Document document2 = Jsoup.parse(new URL(proUrl), 60000);
//			 System.err.println(document2);
            Elements element2 = document2.select("div.spec-items > ul > li");
            for (Element element3 : element2) {
                String img = element3.select("img").attr("src");
                System.err.println(img);
                Productimage productimage = new Productimage();
                String productImgPath2 = "/productImg/imagezoom";
                long fileDateName = 0;
                String imagePath = null;
                File myFile2 = new File(new URI(img));
                String myFileFileName2 = myFile2.getName();
                if (myFile2 != null) {
                    myFileFileName2 = myFileFileName2.substring(myFileFileName2.lastIndexOf("."), myFileFileName2.length());
                    fileDateName = new Date().getTime();
                    String imageFileName = fileDateName + myFileFileName;
                    imagePath = UploadImagesUtil.getFolder(ServletActionContext.getServletContext().getRealPath(productImgPath2) + "/", String.valueOf(product.getProductId())) + "/";
                    File imageFile = new File(imagePath + imageFileName);
                    copy(myFile, imageFile);
                    CutImages.equimultipleConvert(310, 310, imageFile, new File(imagePath + fileDateName + "_mid" + myFileFileName));
                    CutImages.equimultipleConvert(40, 40, imageFile, new File(imagePath + fileDateName + "_small" + myFileFileName));
                    productimage = new Productimage();
                    productimage.setPiProductId(product.getProductId());
                    productimage.setImage(String.valueOf(fileDateName));
                    productimage.setImageType(myFileFileName);
                    productimage.setAttribute75("show");
                    productImageService.add(productimage);
                }
            }

            break;
//			 System.err.println(productImg);
//			 System.err.println(productName);

        }

    }

}
