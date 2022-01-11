package com.eypg.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eypg.pojo.User;
import com.eypg.service.UserService;
import com.eypg.util.ReadFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext*.xml"})
@Repository
public class UpdateUserFaceImg {

    @Autowired
    private UserService userService;
    private User user;

    private static DefaultHttpClient httpClient;
    private static HttpGet httpGet;
    private HttpPost httpPost;

    @Test
    public void go() throws Exception {

        List<User> userList = userService.loadAll();
        Collections.shuffle(userList);

        ReadFile readFile = new ReadFile();
        List<String> strList = readFile.readFile("c:\\imgFile2.txt");
        System.err.println(strList.size());
        int i = 0;

        for (String string : strList) {
            try {
                user = userList.get(i);
                if (user.getFaceImg().equals("/Images/defaultUserFace.png")) {
                    System.err.println(string.split("-")[0]);
//					/faceImages/1000_1372755064097.png
                    Long time = System.currentTimeMillis();
                    String path = "/faceImages/" + user.getUserId() + "_" + time + string.split("-")[1];
                    System.err.println(path);
                    String localPath = "c:\\UserImg\\" + user.getUserId() + "_" + time + string.split("-")[1];
                    WeiboImg.getImage(string.split("-")[0], localPath);
                    System.err.println(localPath);
                    user.setFaceImg(path);
                    userService.add(user);
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


//		httpClient = new DefaultHttpClient();
//		org.apache.http.params.HttpParams params = httpClient.getParams();
//		org.apache.http.params.HttpConnectionParams.setConnectionTimeout(params, 60000);
//		org.apache.http.params.HttpConnectionParams.setSoTimeout(params, 60000);

//		String url = "http://www.1ypg.com/user/updateFaceFile.action";
//		System.err.println(url);
//		httpPost = new HttpPost(url);
//		httpPost.setHeader("Referer", "http://www.1ypg.com/user/UserPhoto.html");
//		httpPost.setHeader("Host", "www.1ypg.com");
//		
//		FileBody bin = new FileBody(file);
//		MultipartEntity reqEntity = new MultipartEntity();
//		reqEntity.addPart("myFile", bin);
//		httpPost.setEntity(reqEntity);
//		
//		List<BasicNameValuePair> vlaueList = new ArrayList<BasicNameValuePair>();
//		vlaueList.add(new BasicNameValuePair("userId","1000"));
//		httpPost.setEntity(new UrlEncodedFormEntity(vlaueList, HTTP.UTF_8));
//		
//		HttpResponse entity = httpClient.execute(httpPost);
//		byte[] bytes = EntityUtils.toByteArray(entity.getEntity());
//		String content = new String(bytes, "UTF-8");
//		System.err.println("content: "+content);


    }


    public static List<String> imgList(String filepath) throws FileNotFoundException, IOException {
        List<String> imgList = new ArrayList<String>();
        File file = new File(filepath);
        if (!file.isDirectory()) {
            System.err.println("文件夹不存在！");
        } else if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(filepath + "\\" + filelist[i]);
                if (!readfile.isDirectory()) {
//                             System.out.println("path=" + readfile.getPath());
//                             System.out.println("absolutepath="+ readfile.getAbsolutePath());
//                             System.out.println("name=" + readfile.getName());
                    imgList.add(readfile.getName());
                } else if (readfile.isDirectory()) {
                    readfile(filepath + "\\" + filelist[i]);
                }
            }
        }
        return imgList;
    }


    /**
     * 读取某个文件夹下的所有文件
     */
    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
//                            System.out.println("path=" + file.getPath());
//                            System.out.println("absolutepath=" + file.getAbsolutePath());
                System.out.println("name1=" + file.getName());

            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
//                                            System.out.println("path=" + readfile.getPath());
//                                            System.out.println("absolutepath="+ readfile.getAbsolutePath());
                        System.out.println("name2=" + readfile.getName());

                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
    }

}
