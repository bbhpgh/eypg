package com.eypg.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EngineTest {

    public static void main(String[] args) {
        new EngineTest().run();
    }

    public void run() {
        try {

            // 获得所有账号信息
            String separator = System.getProperty("file.separator");
//			String path = System.getProperty("user.dir") + separator + "config" + separator + "account.txt";
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "account.txt";

            List<String> engineList = new ArrayList<String>();
            //1元拍购
            engineList.add("http://www.baidu.com/s?wd=1%E5%85%83%E6%8B%8D%E8%B4%AD&rsv_bp=0&ch=&tn=baidu&bar=&rsv_spt=3&ie=utf-8&rsv_sug3=4&rsv_sug=0&rsv_sug1=4&rsv_sug4=51&inputT=5488");
            //1元拍
            engineList.add("http://www.baidu.com/s?ie=utf-8&bs=1%E5%85%83%E6%8B%8D&f=8&rsv_bp=1&rsv_spt=3&wd=1%E5%85%83%E6%8B%8D&inputT=0");
            engineList.add("http://www.baidu.com/s?ie=utf-8&bs=1%E5%85%83%E6%8B%8D%E8%B4%AD&f=8&rsv_bp=1&rsv_spt=3&wd=1%E5%85%83%E6%8B%8D&rsv_sug3=1&rsv_sug1=1&rsv_sug4=20&inputT=3520");
            engineList.add("http://www.baidu.com/link?url=jGs2GJqjJ4zBBpC8yDF8xDh8vibiRE26EyoEbodO");
            engineList.add("http://www.baidu.com/s?ie=utf-8&bs=%E6%8B%8D%E8%B4%AD%E7%BD%91&f=8&rsv_bp=1&wd=%E6%8B%8D%E8%B4%AD&rsv_sug3=1&rsv_sug=0&rsv_sug1=1&rsv_sug4=17&inputT=590");
            engineList.add("http://www.baidu.com/s?ie=utf-8&bs=%E6%8B%8D%E8%B4%AD&f=8&rsv_bp=1&wd=1%E5%85%83%E6%8B%8D%E8%B4%AD%E7%BD%91&rsv_n=2&rsv_sug3=1&rsv_sug1=1&rsv_sug4=12&inputT=710");
            engineList.add("http://www.baidu.com/s?ie=utf-8&bs=%E4%B8%80%E5%85%83%E6%8B%8D%E8%B4%AD&f=8&rsv_bp=1&wd=%E4%B8%80%E5%85%83%E6%8B%8D%E8%B4%AD+1ypg.com&rsv_sug3=7&rsv_sug1=6&rsv_sug4=61&inputT=3199");
            engineList.add("http://www.baidu.com/s?ie=utf-8&bs=%E4%B8%80%E5%85%83%E8%B4%AD%E7%89%A9&f=8&rsv_bp=1&wd=%E4%B8%80%E5%85%83%E8%B4%AD%E7%89%A9&inputT=0");
            engineList.add("http://www.baidu.com/s?ie=utf-8&bs=%E4%B8%80%E5%85%83%E8%B4%AD%E7%89%A9&f=8&rsv_bp=1&wd=1%E5%85%83%E8%B4%AD%E7%89%A9&rsv_sug3=1&rsv_sug1=1&rsv_sug4=9&inputT=224");
            engineList.add("http://www.baidu.com/s?ie=utf-8&bs=1%E5%85%83%E8%B4%AD%E7%89%A9&f=8&rsv_bp=1&wd=1%E5%85%83%E8%B4%AD%E7%89%A9&inputT=0");

//			engineList.add("http://hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1280x768&et=0&fl=11.6&ja=0&ln=zh-CN&lo=0&lt=1364534679&nv=1&rnd=186738380&se=1&si=874caa3b9536373fecc72345e5e0ef19&st=2&su=http%3A%2F%2Fwww.baidu.com%2Fs%3Fie%3Dutf-8%26bs%3D1%25E5%2585%2583%25E8%25B4%25AD%25E7%2589%25A9%26f%3D8%26rsv_bp%3D1%26wd%3D1%25E5%2585%2583%25E8%25B4%25AD%25E7%2589%25A9%26inputT%3D0&sw=1%25E5%2585%2583%25E8%25B4%25AD%25E7%2589%25A9&sse=0&v=1.0.40&lv=3&tt=1%E5%85%83%E6%8B%8D%E8%B4%AD%20-%20%E4%BA%AB%E5%8F%97%E8%B4%AD%E7%89%A9%E7%9A%84%E4%B9%90%E8%B6%A3");
//			engineList.add("http://hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1280x768&et=0&fl=11.6&ja=0&ln=zh-CN&lo=0&lt=1364534796&nv=1&rnd=907471097&se=1&si=874caa3b9536373fecc72345e5e0ef19&st=2&su=http%3A%2F%2Fwww.baidu.com%2Fs%3Fwd%3D1%25E5%2585%2583%25E6%258B%258D%25E8%25B4%25AD%26rsv_bp%3D0%26ch%3D%26tn%3Dbaidu%26bar%3D%26rsv_spt%3D3%26ie%3Dutf-8%26rsv_sug3%3D4%26rsv_sug%3D0%26rsv_sug1%3D4%26rsv_sug4%3D51%26inputT%3D5488&sw=1%25E5%2585%2583%25E6%258B%258D%25E8%25B4%25AD&sse=0&v=1.0.40&lv=3&tt=1%E5%85%83%E6%8B%8D%E8%B4%AD%20-%20%E4%BA%AB%E5%8F%97%E8%B4%AD%E7%89%A9%E7%9A%84%E4%B9%90%E8%B6%A3");
//			engineList.add("http://hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1280x768&et=0&fl=11.6&ja=0&ln=zh-CN&lo=0&lt=1364534830&nv=1&rnd=1169128642&se=1&si=874caa3b9536373fecc72345e5e0ef19&st=2&su=http%3A%2F%2Fwww.baidu.com%2Fs%3Fie%3Dutf-8%26bs%3D1%25E5%2585%2583%25E6%258B%258D%26f%3D8%26rsv_bp%3D1%26rsv_spt%3D3%26wd%3D1%25E5%2585%2583%25E6%258B%258D%26inputT%3D0&sw=1%25E5%2585%2583%25E6%258B%258D&sse=0&v=1.0.40&lv=3&tt=1%E5%85%83%E6%8B%8D%E8%B4%AD%20-%20%E4%BA%AB%E5%8F%97%E8%B4%AD%E7%89%A9%E7%9A%84%E4%B9%90%E8%B6%A3");
//			engineList.add("http://hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1280x768&et=0&fl=11.6&ja=0&ln=zh-CN&lo=0&lt=1364534924&nv=1&rnd=1188301869&se=1&si=874caa3b9536373fecc72345e5e0ef19&st=2&su=http%3A%2F%2Fwww.baidu.com%2Fs%3Fie%3Dutf-8%26bs%3D1%25E5%2585%2583%25E6%258B%258D%25E8%25B4%25AD%26f%3D8%26rsv_bp%3D1%26rsv_spt%3D3%26wd%3D1%25E5%2585%2583%25E6%258B%258D%26rsv_sug3%3D1%26rsv_sug1%3D1%26rsv_sug4%3D20%26inputT%3D3520&sw=1%25E5%2585%2583%25E6%258B%258D&sse=0&v=1.0.40&lv=3&tt=1%E5%85%83%E6%8B%8D%E8%B4%AD%20-%20%E4%BA%AB%E5%8F%97%E8%B4%AD%E7%89%A9%E7%9A%84%E4%B9%90%E8%B6%A3");
//			engineList.add("http://hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1280x768&et=0&fl=11.6&ja=0&ln=zh-CN&lo=0&lt=1364534924&nv=0&rnd=267843145&si=874caa3b9536373fecc72345e5e0ef19&st=4&v=1.0.40&lv=3&tt=1%E5%85%83%E6%8B%8D%E8%B4%AD%20-%20%E4%BA%AB%E5%8F%97%E8%B4%AD%E7%89%A9%E7%9A%84%E4%B9%90%E8%B6%A3");
//			engineList.add("http://hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1280x768&et=0&fl=11.6&ja=0&ln=zh-CN&lo=0&lt=1364535034&nv=1&rnd=849236387&se=1&si=874caa3b9536373fecc72345e5e0ef19&st=2&su=http%3A%2F%2Fwww.baidu.com%2Fs%3Fie%3Dutf-8%26bs%3D%25E6%258B%258D%25E8%25B4%25AD%25E7%25BD%2591%26f%3D8%26rsv_bp%3D1%26wd%3D%25E6%258B%258D%25E8%25B4%25AD%26rsv_sug3%3D1%26rsv_sug%3D0%26rsv_sug1%3D1%26rsv_sug4%3D17%26inputT%3D590&sw=%25E6%258B%258D%25E8%25B4%25AD&sse=0&v=1.0.40&lv=3&tt=1%E5%85%83%E6%8B%8D%E8%B4%AD%20-%20%E4%BA%AB%E5%8F%97%E8%B4%AD%E7%89%A9%E7%9A%84%E4%B9%90%E8%B6%A3");
//			engineList.add("http://hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1280x768&et=0&fl=11.6&ja=0&ln=zh-CN&lo=0&lt=1364535101&nv=1&rnd=1642236941&se=1&si=874caa3b9536373fecc72345e5e0ef19&st=2&su=http%3A%2F%2Fwww.baidu.com%2Fs%3Fie%3Dutf-8%26bs%3D%25E6%258B%258D%25E8%25B4%25AD%26f%3D8%26rsv_bp%3D1%26wd%3D1%25E5%2585%2583%25E6%258B%258D%25E8%25B4%25AD%25E7%25BD%2591%26rsv_n%3D2%26rsv_sug3%3D1%26rsv_sug1%3D1%26rsv_sug4%3D12%26inputT%3D710&sw=1%25E5%2585%2583%25E6%258B%258D%25E8%25B4%25AD%25E7%25BD%2591&sse=0&v=1.0.40&lv=3&tt=1%E5%85%83%E6%8B%8D%E8%B4%AD%20-%20%E4%BA%AB%E5%8F%97%E8%B4%AD%E7%89%A9%E7%9A%84%E4%B9%90%E8%B6%A3");
//			engineList.add("http://hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1280x768&et=0&fl=11.6&ja=0&ln=zh-CN&lo=0&lt=1364535131&nv=1&rnd=1418806658&se=1&si=874caa3b9536373fecc72345e5e0ef19&st=2&su=http%3A%2F%2Fwww.baidu.com%2Fs%3Fie%3Dutf-8%26bs%3D%25E4%25B8%2580%25E5%2585%2583%25E6%258B%258D%25E8%25B4%25AD%26f%3D8%26rsv_bp%3D1%26wd%3D%25E4%25B8%2580%25E5%2585%2583%25E6%258B%258D%25E8%25B4%25AD%2B1ypg.com%26rsv_sug3%3D7%26rsv_sug1%3D6%26rsv_sug4%3D61%26inputT%3D3199&sw=%25E4%25B8%2580%25E5%2585%2583%25E6%258B%258D%25E8%25B4%25AD%2B1ypg.com&sse=0&v=1.0.40&lv=3&tt=1%E5%85%83%E6%8B%8D%E8%B4%AD%20-%20%E4%BA%AB%E5%8F%97%E8%B4%AD%E7%89%A9%E7%9A%84%E4%B9%90%E8%B6%A3");
//			engineList.add("http://hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1280x768&et=0&fl=11.6&ja=0&ln=zh-CN&lo=0&lt=1364535159&nv=1&rnd=1840284625&se=1&si=874caa3b9536373fecc72345e5e0ef19&st=2&su=http%3A%2F%2Fwww.baidu.com%2Fs%3Fie%3Dutf-8%26bs%3D%25E4%25B8%2580%25E5%2585%2583%25E8%25B4%25AD%25E7%2589%25A9%26f%3D8%26rsv_bp%3D1%26wd%3D%25E4%25B8%2580%25E5%2585%2583%25E8%25B4%25AD%25E7%2589%25A9%26inputT%3D0&sw=%25E4%25B8%2580%25E5%2585%2583%25E8%25B4%25AD%25E7%2589%25A9&sse=0&v=1.0.40&lv=3&tt=1%E5%85%83%E6%8B%8D%E8%B4%AD%20-%20%E4%BA%AB%E5%8F%97%E8%B4%AD%E7%89%A9%E7%9A%84%E4%B9%90%E8%B6%A3");
//			engineList.add("http://hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1280x768&et=0&fl=11.6&ja=0&ln=zh-CN&lo=0&lt=1364535198&nv=1&rnd=509173837&se=1&si=874caa3b9536373fecc72345e5e0ef19&st=2&su=http%3A%2F%2Fwww.baidu.com%2Fs%3Fie%3Dutf-8%26bs%3D%25E4%25B8%2580%25E5%2585%2583%25E8%25B4%25AD%25E7%2589%25A9%26f%3D8%26rsv_bp%3D1%26wd%3D1%25E5%2585%2583%25E8%25B4%25AD%25E7%2589%25A9%26rsv_sug3%3D1%26rsv_sug1%3D1%26rsv_sug4%3D9%26inputT%3D224&sw=1%25E5%2585%2583%25E8%25B4%25AD%25E7%2589%25A9&sse=0&v=1.0.40&lv=3&tt=1%E5%85%83%E6%8B%8D%E8%B4%AD%20-%20%E4%BA%AB%E5%8F%97%E8%B4%AD%E7%89%A9%E7%9A%84%E4%B9%90%E8%B6%A3");
//			engineList.add("http://hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1280x768&et=0&fl=11.6&ja=0&ln=zh-CN&lo=0&lt=1364535245&nv=1&rnd=1258558338&se=1&si=874caa3b9536373fecc72345e5e0ef19&st=2&su=http%3A%2F%2Fwww.baidu.com%2Fs%3Fie%3Dutf-8%26bs%3D1%25E5%2585%2583%25E8%25B4%25AD%25E7%2589%25A9%26f%3D8%26rsv_bp%3D1%26wd%3D1%25E5%2585%2583%25E8%25B4%25AD%25E7%2589%25A9%26inputT%3D0&sw=1%25E5%2585%2583%25E8%25B4%25AD%25E7%2589%25A9&sse=0&v=1.0.40&lv=3&tt=1%E5%85%83%E6%8B%8D%E8%B4%AD%20-%20%E4%BA%AB%E5%8F%97%E8%B4%AD%E7%89%A9%E7%9A%84%E4%B9%90%E8%B6%A3");


            // 获得所有代理信息
//			String ip = System.getProperty("user.dir") + separator + "config" + separator + "ip.txt";
            String ip = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "ip.txt";
            List<Proxy> proxyList = FollowTools.readProxyIP(ip);
            Collections.shuffle(proxyList);
            System.err.println("proxyList:" + proxyList.size());

            // 计数器
            CountDownLatch countDownLatch = new CountDownLatch(engineList.size());
            // 搜索引擎地址容器
            EngineContainer engineContainer = new EngineContainer(engineList);
            // 代理容器
            ProxyContainer proxyContainer = new ProxyContainer(proxyList, countDownLatch);

            System.out.println(engineList.size());
            //线程池
            ExecutorService exec = Executors.newFixedThreadPool(10);
            for (int i = 0; i < 10; i++)
                exec.submit(new EngineThread("抓取详细线程" + i, engineContainer, proxyContainer));
            try {
                countDownLatch.await();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            exec.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
