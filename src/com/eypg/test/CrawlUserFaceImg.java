package com.eypg.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eypg.service.UserService;
import com.eypg.util.ReadFile;
import com.eypg.util.SaveFile;


public class CrawlUserFaceImg {

    private static DefaultHttpClient httpClient;
    private static HttpGet httpGet;
    private HttpPost httpPost;

    @Autowired
    private UserService userService;

    @Test
    public void go() throws Exception {
        httpClient = new DefaultHttpClient();
        org.apache.http.params.HttpParams params = httpClient.getParams();
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(params, 60000);
        org.apache.http.params.HttpConnectionParams.setSoTimeout(params, 60000);
        int count = 1400;
        int codeID = 24069;
        int i = 1;
        int j = 10;
        long startDate = System.currentTimeMillis();
        int imgCount = 0;
        List<String> imgList = new ArrayList<String>();
        while (count-- > 0) {
            try {
                System.err.println(i + "    " + j);
//				http://dataserver.1yyg.com/JPData?action=GetUserBuyListByCode&codeID=11807&FIdx=51&EIdx=60&isCount=0&fun=jsonp1362634201995&_=1362644662143
                String url = "http://dataserver.1yyg.com/JPData?action=GetUserBuyListByCode&codeID=" + codeID + "&FIdx=" + i + "&EIdx=" + j + "&fun=jsonp" + startDate + "&_=" + System.currentTimeMillis();
                System.err.println(url);
                httpGet = new HttpGet(url);
                httpGet.setHeader("Accept", "*/*");
                httpGet.setHeader("Accept-Encoding", "gzip, deflate");
                httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
                httpGet.setHeader("Connection", "keep-alive");
                httpGet.setHeader("Cookie", "__utma=248799431.1617822012.1357546987.1357546987.1357611571.2; __utmz=248799431.1357546987.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmb=248799431.4.10.1357611571; __utmc=248799431");
                httpGet.setHeader("Host", "dataserver.1yyg.com");
                httpGet.setHeader("Referer", "http://www.1yyg.com/products/21252.html");
                httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:17.0) Gecko/20100101 Firefox/17.0");
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                String content = httpClient.execute(httpGet, responseHandler);
                content = content.replaceAll("jsonp" + startDate, "");
                content = content.substring(1, content.length());
                content = content.substring(0, content.length() - 1);
                JSONObject object = JSONObject.fromObject(content);
                JSONObject data = JSONObject.fromObject(object.getString("Data"));
                JSONObject Tables = JSONObject.fromObject(data.getString("Tables"));
                JSONObject BuyList = JSONObject.fromObject(Tables.getString("BuyList"));
                JSONArray Rows = JSONArray.fromObject(BuyList.getString("Rows"));
                for (Object object2 : Rows) {
                    JSONObject obj = JSONObject.fromObject(object2);
//			    	System.err.println(obj);
//					System.err.println(obj.getString("userName"));
//					System.err.println(obj.getString("buyNum"));
//					System.err.println(obj.getString("buyIP"));
//					System.err.println(obj.getString("buyIPAddr"));
//					System.err.println(obj.getString("buyTime"));
//					System.err.println(obj.getString("userPhoto"));


                    if (!obj.getString("userPhoto").equals("00000000000000000.jpg")) {
                        String imgUrl = "http://faceimg.1yyg.com/UserFace/160/" + obj.getString("userPhoto");
                        System.err.println(imgUrl);
//			    		WeiboImg.getImage(imgUrl, "c:\\userImgTemp\\"+obj.getString("userPhoto"));
                        SaveFile.svaeWeibo("c:\\imgFile2.txt", imgUrl + "-." + obj.getString("userPhoto").split("\\.")[1]);
//			    		System.err.println(obj.getString("userPhoto"));
//			    		System.err.println(obj.getString("userPhoto").split("\\.")[1]);

//			    		WeiboImg.getImage(imgUrl, "c:\\UserImg\\"+imgCount+"."+obj.getString("userPhoto").split("\\.")[1]);
//			    		imgCount++;
                    }
                }
                startDate += 1;
                i += 10;
                j += 10;
//				break;
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

        }
    }

    public static void main(String[] args) throws Exception {
        CrawlUserFaceImg crawlUserFaceImg = new CrawlUserFaceImg();
//		System.err.println(new Date(Long.parseLong("1372731188001")));
//		System.err.println(DateUtils.addMinutes(new Date(), -5).getTime());
//		HttpHost proxy = new HttpHost("64.85.181.46", 8080);
//		crawlUserFaceImg.loginWeibo("15201558450","epgwyc11",proxy);
//		crawlUserFaceImg.createUserFace("1000546330");
//		getUserDate();
//		readfile("c:\\UserImg\\");
//		List<String> list = imgList("c:\\UserImg\\");
//		for (String string : list) {
//			System.err.println(string);
//		}
//		WeiboImg.getImage("http://faceimg.1yyg.com/UserFace/160/00000000000000035.gif", "c:\\123\\123.gif");
        Map<String, String> map = new TreeMap<String, String>();

        ReadFile readFile = new ReadFile();
        List<String> strList = readFile.readFile("c:\\imgFile.txt");
        for (String string : strList) {
            map.put(string, string);
        }
        System.err.println(map.size());
        for (Object object : map.keySet()) {
//			System.err.println(map.get(object));
            WeiboImg.getImage(map.get(object).split("-")[0], "c:\\UserImg\\" + System.currentTimeMillis() + map.get(object).split("-")[1]);
        }


//		map.put("00000000000000035", "http://faceimg.1yyg.com/UserFace/160/00000000000000035.gif-.gif");
//		map.put("00000000000000035", "http://faceimg.1yyg.com/UserFace/160/00000000000000035.gif-.gif");
//		map.put("00000000000000035", "http://faceimg.1yyg.com/UserFace/160/00000000000000035.gif-.gif");
//		map.put("00000000000000035", "http://faceimg.1yyg.com/UserFace/160/00000000000000035.gif-.gif");
//		map.put("123", "http://faceimg.1yyg.com/UserFace/160/00000000000000035.gif-.gif");
//		for (Object object : map.keySet()) {
//			System.err.println(map.get(object)+object);
//		}
    }

    public synchronized String loginWeibo(String username, String password, HttpHost proxy) throws Exception {
        httpClient = new DefaultHttpClient();
        org.apache.http.params.HttpParams params = httpClient.getParams();
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(params, 60000);
        org.apache.http.params.HttpConnectionParams.setSoTimeout(params, 60000);

//		String cookie = "U_TRS1=0000005a.da7c329.501f3982.97007c28; path=/; expires=Thu, 04-Aug-22 03:26:58 GMT; domain=.sina.com.cn U_TRS2=0000005a.da90329.501f3982.95384a8f; path=/; domain=.sina.com.cn";
//		String domain = "http://weibo.com/jiejingkong";

//		httpClient.getParams().setCookiePolicy(CookiePolicy.RFC_2109);
//		HttpState initialState = new HttpState();
//		String[] cookieNameAndValue = getCookieNameAndCookieValue(setSinaCookie(cookie));
//		initialState.addCookie(new Cookie(domain, cookieNameAndValue[0], cookieNameAndValue[1], "/", new Date(new Date().getTime() + 1000 * 60 * 60 * 24), false));

//		HttpPost httpPost = new HttpPost("http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.1)");
//		ResponseHandler<String> responseHandler = new BasicResponseHandler();
//		httpPost.setEntity(new UrlEncodedFormEntity(postList, HTTP.UTF_8));
//		String responseBody = httpClient.execute(httpPost, responseHandler);
        String url = "http://passport.1yyg.com/JPData?action=userlogin&name=" + username + "&pwd=" + password + "&fun=jsonp" + DateUtils.addMinutes(new Date(), -5).getTime() + "&_=" + System.currentTimeMillis();
        httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "text/javascript, application/javascript, */*");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cookie", "__utma=248799431.455476130.1371809745.1372668973.1372729700.19; __utmz=248799431.1372385864.12.6.utmcsr=1yyg.com|utmccn=(referral)|utmcmd=referral|utmcct=/; CNZZDATA3362429=cnzz_eid%3D1594148446-1372212393-http%253A%252F%252Fpassport.1yyg.com%26ntime%3D1372729700%26cnzz_a%3D0%26retime%3D1372731187996%26sin%3Dhttp%253A%252F%252Fu.1yyg.com%252F1000652467%26ltime%3D1372731187996%26rtime%3D2; __utmb=248799431.7.10.1372729700; __utmc=248799431");
        httpGet.setHeader("Host", "passport.1yyg.com");
        httpGet.setHeader("Referer", "http://passport.1yyg.com/login.html?forward=rego");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
        httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String result1 = httpClient.execute(httpGet, responseHandler);
        System.err.println(result1);
        return null;
    }

    public synchronized String createUserFace(String id) throws Exception {
        if (httpClient == null) {
            throw new Exception("httpClientΪnull...");
        }
        String url = "http://u.1yyg.com/" + id;
        httpGet = new HttpGet(url);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String result1 = httpClient.execute(httpGet, responseHandler);
        System.err.println(result1);
        return null;
    }

    public static String getUserDate() throws ClientProtocolException, IOException {
        httpClient = new DefaultHttpClient();
        org.apache.http.params.HttpParams params = httpClient.getParams();
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(params, 60000);
        org.apache.http.params.HttpConnectionParams.setSoTimeout(params, 60000);
        int count = 1400;
        int codeID = 11807;
        int i = 1;
        int j = 10;
        long startDate = System.currentTimeMillis();
        while (count-- > 0) {
            System.err.println(i + "    " + j);
//			http://dataserver.1yyg.com/JPData?action=GetUserBuyListByCode&codeID=11807&FIdx=51&EIdx=60&isCount=0&fun=jsonp1362634201995&_=1362644662143
            String url = "http://dataserver.1yyg.com/JPData?action=GetUserBuyListByCode&codeID=" + codeID + "&FIdx=" + i + "&EIdx=" + j + "&fun=jsonp" + startDate + "&_=" + System.currentTimeMillis();
            System.err.println(url);
            httpGet = new HttpGet(url);
            httpGet.setHeader("Accept", "*/*");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate");
            httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Cookie", "__utma=248799431.1617822012.1357546987.1357546987.1357611571.2; __utmz=248799431.1357546987.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmb=248799431.4.10.1357611571; __utmc=248799431");
            httpGet.setHeader("Host", "dataserver.1yyg.com");
            httpGet.setHeader("Referer", "http://www.1yyg.com/products/21252.html");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:17.0) Gecko/20100101 Firefox/17.0");
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String content = httpClient.execute(httpGet, responseHandler);
            content = content.replaceAll("jsonp" + startDate, "");
            content = content.substring(1, content.length());
            content = content.substring(0, content.length() - 1);
            JSONObject object = JSONObject.fromObject(content);
            JSONObject data = JSONObject.fromObject(object.getString("Data"));
            JSONObject Tables = JSONObject.fromObject(data.getString("Tables"));
            JSONObject BuyList = JSONObject.fromObject(Tables.getString("BuyList"));
            JSONArray Rows = JSONArray.fromObject(BuyList.getString("Rows"));
            for (Object object2 : Rows) {
                JSONObject obj = JSONObject.fromObject(object2);
//		    	System.err.println(obj);
//				System.err.println(obj.getString("userName"));
//				System.err.println(obj.getString("buyNum"));
//				System.err.println(obj.getString("buyIP"));
//				System.err.println(obj.getString("buyIPAddr"));
//				System.err.println(obj.getString("buyTime"));
//				System.err.println(obj.getString("userPhoto"));
                if (!obj.getString("userPhoto").equals("00000000000000000.jpg")) {
                    System.err.println("http://faceimg.1yyg.com/UserFace/160/" + obj.getString("userPhoto"));
                }
            }
            startDate += 1;
            i += 10;
            j += 10;
            break;
        }

        return null;
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

    private static String[] getCookieNameAndCookieValue(Map<String, String> cookie) {
        String[] result = new String[2];
        String cookieName = null;
        String cookieValue = "";
        String key;
        Iterator<String> iterator = cookie.keySet().iterator();
        while (iterator.hasNext()) {
            if (cookieName == null)
                cookieName = iterator.next();
            else {
                key = iterator.next();
                cookieValue = cookieValue + key + "=" + cookie.get(key) + ";";
            }
        }
        cookieValue = cookie.get(cookieName) + ";" + cookieValue;
        result[0] = cookieName;
        result[1] = cookieValue;
        return result;
    }

    public static Map<String, String> setSinaCookie(String sinacookie) {
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < sinacookie.split(";").length; i = i + 1) {
            try {
                map.put(sinacookie.split(";")[i].split("=")[0].trim(), sinacookie.split(";")[i].split("=")[1]);
                // System.out.println(sinacookie.split(";")[i].split("=")[0].trim()
                // + " = " + sinacookie.split(";")[i].split("=")[1].trim());
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return map;
    }

    public static String encryptPassword(String password, String servertime, String code) {
        return Encrypt(Encrypt(Encrypt(password)) + servertime + code);
    }

    public static String Encrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Invalid algorithm.");
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}
