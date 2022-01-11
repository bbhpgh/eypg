package com.eypg.test;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class EngineThread extends Thread {

    private EngineContainer engineContainer;
    private ProxyContainer proxyContainer;
    private HttpClient httpClient;
    private HttpGet httpGet;

    String threadName;

    public EngineThread(String threadName, EngineContainer engineContainer, ProxyContainer proxyContainer) {
        this.engineContainer = engineContainer;
        this.proxyContainer = proxyContainer;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (!engineContainer.isEmpty()) {
            String engineUrl = engineContainer.getEngineUrl();
            if (engineUrl == null)
                return;
            try {
                Proxy proxyIp = proxyContainer.getProxy();
                System.err.println("启动线程：" + threadName + "代理IP：" + proxyIp.getIp() + "端口：" + proxyIp.getPort());
                httpClient = new DefaultHttpClient();
                HttpParams params = httpClient.getParams();
                HttpConnectionParams.setConnectionTimeout(params, 60000);
                HttpConnectionParams.setSoTimeout(params, 60000);
                HttpHost proxy = new HttpHost(proxyIp.getIp(), proxyIp.getPort());
                httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
                httpClient.getParams().setParameter(CookiePolicy.BROWSER_COMPATIBILITY, null);
//				httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY); 
                httpGet = new HttpGet(engineUrl);
//				httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//				httpGet.setHeader("Accept-Encoding","gzip, deflate");
//				httpGet.setHeader("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//				httpGet.setHeader("Connection","keep-alive");
//				httpGet.setHeader("Host","www.baidu.com");
//				httpGet.setHeader("Referer",engineUrl);
//				httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:19.0) Gecko/20100101 Firefox/19.0");
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                Thread.sleep(2000);
                System.err.println("停留2秒。。。。");
                String html = httpClient.execute(httpGet, responseHandler);
                Document document = Jsoup.parse(html);
                Elements links = document.select("a[href]");
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    String linkText = link.text();
                    if (linkText.indexOf("1元拍购 - 享受购物的乐趣") != -1) {
                        System.err.println(linkHref);
                        HttpGet getMethod = new HttpGet(linkHref);
                        getMethod.setHeader("Accept", "*/*");
                        getMethod.setHeader("Accept-Encoding", "gzip, deflate");
                        getMethod.setHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
                        getMethod.setHeader("Connection", "keep-alive");
                        getMethod.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:19.0) Gecko/20100101 Firefox/19.0");
                        HttpResponse entity123 = httpClient.execute(getMethod);
                        byte[] bytes123 = EntityUtils.toByteArray(entity123.getEntity());
                        String content123 = new String(bytes123, "UTF-8");
                        if (content123.indexOf("<meta name=\"description\" content=\"1元拍购是一种全新的购物模式，是时尚、潮流的风向标，能满足个性、年轻消费者的购物需求，花极少量的钱买自己喜爱的东西，享受购物带来的乐趣。\" />") != -1) {
                            System.err.println("进入首页成功！");
                        }
                    }
                }
            } catch (Exception e) {
//				e.printStackTrace();
            } finally {
                proxyContainer.reduceTask();
            }
        }
    }

}
