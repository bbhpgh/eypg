package com.eypg.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpClientTest {

    private static DefaultHttpClient httpClient;
    private static HttpGet httpGet;
    private HttpPost httpPost;


    public static void main(String[] args) throws ClientProtocolException, IOException {
        httpClient = new DefaultHttpClient();
        org.apache.http.params.HttpParams params = httpClient.getParams();
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(params, 60000);
        org.apache.http.params.HttpConnectionParams.setSoTimeout(params, 60000);
        String index = "http://www.1ypg.com";
        httpGet = new HttpGet(index);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String content = httpClient.execute(httpGet, responseHandler);
        Document document = Jsoup.parse(content);
        Elements elements = document.select("a");
        for (Element element : elements) {
            try {
                String url = element.attr("href");
                if (url.indexOf("http://www.1ypg.com") != -1) {
                    System.err.println(url);
                    go(url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void go(String url) throws ClientProtocolException, IOException {
        httpClient = new DefaultHttpClient();
        org.apache.http.params.HttpParams params = httpClient.getParams();
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(params, 60000);
        org.apache.http.params.HttpConnectionParams.setSoTimeout(params, 60000);
        httpGet = new HttpGet(url);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String content = httpClient.execute(httpGet, responseHandler);
        System.err.println(content);
    }

}
