package com.eypg.test;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eypg.pojo.User;
import com.eypg.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext*.xml"})
@Repository
public class CrawlByUserIP {
    @Autowired
    private UserService userService;
    private User user;

    private static DefaultHttpClient httpClient;
    private static HttpGet httpGet;
    private HttpPost httpPost;

    @Test
    public void go() throws ClientProtocolException, IOException {
        httpClient = new DefaultHttpClient();
        org.apache.http.params.HttpParams params = httpClient.getParams();
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(params, 60000);
        org.apache.http.params.HttpConnectionParams.setSoTimeout(params, 60000);
        int count = 1400;
        int codeID = 24064;
        int i = 1;
        int j = 10;
        long startDate = System.currentTimeMillis();
        while (count-- > 0) {
            System.err.println(i + "    " + j);
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
                System.err.println(obj.getString("userName") + obj.getString("buyNum") + obj.getString("buyIP") + obj.getString("buyIPAddr") + obj.getString("buyTime"));
                user = new User();
                String nowDate = obj.getString("buyTime");
                nowDate = nowDate.substring(0, nowDate.length() - 3);
                String userName = obj.getString("userName");
                user.setUserName(userName);
                user.setUserPwd("1ypg.com");
                user.setIpLocation(obj.getString("buyIPAddr"));
                user.setIpAddress(obj.getString("buyIP"));
                user.setNewDate(nowDate);
                user.setFaceImg("/Images/defaultUserFace.png");
                user.setUserType("0");
                user.setBalance(0.00);
                user.setExperience(0);
                user.setCommissionBalance(0.00);
                user.setCommissionCount(0.00);
                user.setCommissionMention(0.00);
//				user.setSignature("这家伙很懒，什么也没留下。");
                User user2 = userService.userByIsUserName(userName);
                if (user2 == null) {
                    userService.add(user);
                }
            }
            startDate += 1;
            i += 10;
            j += 10;
//			break;
        }
    }

    public static void main(String[] args) throws ClientProtocolException, IOException {
        httpClient = new DefaultHttpClient();
        org.apache.http.params.HttpParams params = httpClient.getParams();
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(params, 60000);
        org.apache.http.params.HttpConnectionParams.setSoTimeout(params, 60000);
        int count = 1400;
        int codeID = 24064;
        int i = 1;
        int j = 20;
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
                System.err.println(obj);
                System.err.println(obj.getString("userName"));
                System.err.println(obj.getString("buyNum"));
                System.err.println(obj.getString("buyIP"));
                System.err.println(obj.getString("buyIPAddr"));
                System.err.println(obj.getString("buyTime"));
            }
            startDate += 1;
            i += 20;
            j += 20;
            break;
        }
//		 System.err.println(DateUtil.DateTimeToStr(new Date(Long.parseLong("1357613245334"))));
//		 System.err.println(DateUtil.DateTimeToStr(new Date(Long.parseLong("1357614225335"))));
    }

}
