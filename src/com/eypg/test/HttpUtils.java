package com.eypg.test;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.ProtocolException;
import org.apache.commons.httpclient.RedirectException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.eypg.util.StringUtil;

public class HttpUtils {
    final static String[] ipAndPort = {"127.0.0.1", "48100"};
//	final static String[] ipAndPort = { "122.49.11.168", "48100" };

//	final static WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_7_0, ipAndPort[0], Integer.valueOf(ipAndPort[1]));
//	static {
//		webClient.setJavaScriptEnabled(false);
//		webClient.setTimeout(30000);
//	}

    private static String currentDynamicProxyIp;
    static int start;
    public static List<String> ipList = new ArrayList<String>();
    static Map<String, Integer> disabledIpMap = new HashMap<String, Integer>();

    static {
        start = 0;
//		ipList.add("111.85.41.14:6675");
//		ipList.add("219.138.47.213:6666");
//		ipList.add("61.189.226.33:6675");
//		ipList.add("61.189.212.162:6675");
//		ipList.add("61.189.209.4:6675");
//		ipList.add("58.16.206.250:6675");
//		ipList.add("111.176.146.80:6675");
//		ipList.add("111.177.32.222:6675");
//		ipList.add("111.176.221.119:6675");
        ipList.add("111.176.157.103:6675");
        try {
//			ipList = HttpUtils.getAvailableIpAndPort(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertSomeIpToIpList(int count) {
        List<String> list = HttpUtils.getAvailableIpAndPort(count);
        if (list.size() > 0) {
            ipList.addAll(list);
        }
    }

    private static String getDynamicIp() {
        for (int i = 0; i < 10; i++) {
            if (ipList.size() == 0) {
                insertSomeIpToIpList(1);
            } else {
                break;
            }
        }
        if (ipList.size() > 0) {
            String dynamicIp = ipList.get(start);
            start++;
            if (start == ipList.size()) {
                start = 0;
            }
            return dynamicIp;
        }
        return "111.111.111.11:1111";
    }

    private static void removeUnavailableIp(String unavailableIp) {
        //如果disabledIpMap中包含该ip 就去查看该ip一共出现了几次
        if (disabledIpMap.containsKey(unavailableIp)) {
            int count = disabledIpMap.get(unavailableIp);
            disabledIpMap.put(unavailableIp, count + 1);
            System.out.println("该无效ip:" + unavailableIp + " 在disabledIpMap已有记录 出现次数为：" + disabledIpMap.get(unavailableIp));
            //如果累计请求不成功的次数超过5次 那么就移除该ip
            if (disabledIpMap.get(unavailableIp) > 5) {
                try {
                    ipList.remove(unavailableIp);
                    System.out.println("移除无用IP成功：" + unavailableIp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    String ip = HttpUtils.getAvailableIpAndPort(1).get(0);
                    ipList.add(ip);
                    System.out.println("添加有效IP成功：" + ip);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //同样从disabledIpMap中移除该ip
                disabledIpMap.remove(unavailableIp);
                System.out.println("该无效ip:" + unavailableIp + " 在disabledIpMap出现超过5次，从disabledIpMap中移除");
            }
        } else {
            System.out.println("该无效ip:" + unavailableIp + " 在disabledIpMap无记录，首次出现");
            disabledIpMap.put(unavailableIp, 1);
        }
		
		/*
		try {
			ipList.remove(unavailableIp);
			System.out.println("移除无用IP成功："+unavailableIp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String ip = HttpUtils.getAvailableIpAndPort(1).get(0);
			ipList.add(ip);
			System.out.println("添加有效IP成功："+ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 * */
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getSogouContentUseProxy("http://iframe.ip138.com/ic.asp", "gb2312"));
        List<String> list = HttpUtils.ipList;
        for (String s : list) {
            System.out.println("@@@@@@@@:" + s);
        }
    }

    public static String generateRandom(Integer digits) {
        String s = "";
        for (int i = 0; i < digits; i++) {
            s += "0";
        }
        int multiplier = Integer.valueOf(1 + s);
        int temp = (int) (Math.random() * multiplier);
        s = String.valueOf(temp);
        return s;
    }

    public static List<String> getAvailableIpAndPort(int size) {
        String dq = "";
        try {
            dq = URLEncoder.encode("北京", "gb2312");
        } catch (Exception e) {
            System.out.println("encode err：" + e.getMessage());
        }
        String getUrl = "http://60.173.11.232:2222/api.asp?ddbh=225988240275820&old=1&dq=" + dq + "&sl=" + size;
        List<String> list = new ArrayList<String>();
        GetMethod getMethod = null;
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
            getMethod = new GetMethod(getUrl);
            getMethod.addRequestHeader("Connection", "close");

            getMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0");

            getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "gb2312");
            httpClient.getParams().setContentCharset("gb2312");
            httpClient.getParams().setCredentialCharset("gb2312");
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            String html = getMethod.getResponseBodyAsString();
            System.out.println("@@:" + html);
            String left = StringUtils.substringBetween(html, "</head>", "IP");
            html = StringUtils.substringAfterLast(html, "QQ:");
            html = StringUtils.substringAfter(html, "<hr>");
            html = StringUtils.substringBefore(html, "<hr>");
            String[] ipAndPort = StringUtils.split(html, "<br>");
            //格式为：118.73.111.238:6666
            for (String s : ipAndPort) {
                try {
                    list.add(s.trim());
//					System.out.println(s.trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("剩余IP数量:" + getFormatStr(left));
        } catch (RedirectException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (getMethod != null)
                getMethod.releaseConnection();
        }
        return list;
    }

    public static String getFormatStr(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static String getSogouContentUseProxy(String url, String charset) throws IOException {
        GetMethod getMethod = null;
        currentDynamicProxyIp = getDynamicIp();
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.getHostConfiguration().setProxy(currentDynamicProxyIp.split(":")[0], Integer.parseInt(currentDynamicProxyIp.split(":")[1]));
            System.out.println("当前使用的代理ip是：" + currentDynamicProxyIp);
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
            httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
            getMethod = new GetMethod(url);
            getMethod.addRequestHeader("Connection", "close");
            getMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0");
            getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
            httpClient.getParams().setContentCharset(charset);
            httpClient.getParams().setCredentialCharset(charset);
            getMethod.addRequestHeader("Accept-Encoding", "gzip, deflate");
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            return getMethod.getResponseBodyAsString();

//			int statusCode = httpClient.executeMethod(getMethod);
//			if (statusCode != HttpStatus.SC_OK) {
//				return null;
//			}
//			
//		    InputStream is = getMethod.getResponseBodyAsStream();   
//            GZIPInputStream gzin = new GZIPInputStream(is);   
//           
//            InputStreamReader isr = new InputStreamReader(gzin, charset);    
//            java.io.BufferedReader br = new java.io.BufferedReader(isr);   
//            StringBuffer sb = new StringBuffer();   
//            String tempbf;   
//            while ((tempbf = br.readLine()) != null) {   
//            	sb.append(tempbf);   
//            	sb.append("\r\n");   
//            }   
//            isr.close();   
//            gzin.close();  
//		    return sb.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            //先移除无用的IP
            removeUnavailableIp(currentDynamicProxyIp);
            //再切换IP重新登录
            currentDynamicProxyIp = getDynamicIp();
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            //先移除无用的IP
            removeUnavailableIp(currentDynamicProxyIp);
            //再切换IP重新登录
            currentDynamicProxyIp = getDynamicIp();
        } catch (ProtocolException e) {
            e.printStackTrace();
            //先移除无用的IP
            removeUnavailableIp(currentDynamicProxyIp);
            //再切换IP重新登录
            currentDynamicProxyIp = getDynamicIp();
        } catch (ConnectException e) {
            e.printStackTrace();
            //先移除无用的IP
            removeUnavailableIp(currentDynamicProxyIp);
            //再切换IP重新登录
            currentDynamicProxyIp = getDynamicIp();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (getMethod != null)
                getMethod.releaseConnection();
        }
        return null;
    }

    public static String getContentUseHtmlUnitProxy(String url, String charset) throws IOException {
        String result = null;
        ;
//		String random1 = generateRandom(3);
//		String random2 = generateRandom(2);
//		String random3 = generateRandom(4);
//		String random4 = generateRandom(4);
//		// String cookie =
//		// "PREF=ID=2f6a699f8423f164:U=772dae8a8758ad05:FF=2:LD=zh-CN:NW=1:TM=1307259001:LM=1308324493:S=pY10KdNbKMd6ia_6;
//		// NID=48=NT7eyEss_bjOKob_ZSz3hEIu4qxI0lgzknJCnd6upHeLdCQ1oDGepTU4IxiON4B67p5JyUSuSfG0ZDCCDs3yQE3SSySwmOR7UuhJhaqsDfK4X8pSKOKP2L6OefOXYMM3";
//		String randomCookie = "PREF=ID=2f6a699f8423f" + random1 + ":U=772dae8a8758ad" + random2 + ":FF=2:LD=zh-CN:NW=1:TM=130725" + random3 + ":LM=130832" + random4
//				+ ":S=pY10KdNbKMd6ia_6; NID=48=NT7eyEss_bjOKob_ZSz3hEIu4qxI0lgzknJCnd6upHeLdCQ1oDGepTU4IxiON4B67p5JyUSuSfG0ZDCCDs3yQE3SSySwmOR7UuhJhaqsDfK4X8pSKOKP2L6OefOXYMM3";
//
//		webClient.addRequestHeader("Cookie", randomCookie);
//
//		try {
//			Page page = webClient.getPage(url);
//			result = page.getWebResponse().getContentAsString();
//		} catch (Exception e) {
//			try {
//				Page page = webClient.getPage(url);
//				result = page.getWebResponse().getContentAsString();
//			} catch (Exception e1) {
//				Page page = webClient.getPage(url);
//				result = page.getWebResponse().getContentAsString();
//			}
//		}
        return result;
    }

    public static String getContentUseProxy(String url, String charset) throws IOException {
        GetMethod getMethod = null;
        currentDynamicProxyIp = getDynamicIp();
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.getHostConfiguration().setProxy(currentDynamicProxyIp.split(":")[0], Integer.parseInt(currentDynamicProxyIp.split(":")[1]));
            System.out.println("当前使用的代理ip是：" + currentDynamicProxyIp);
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
            getMethod = new GetMethod(url);
            getMethod.addRequestHeader("Connection", "close");
            getMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0");
            getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
            httpClient.getParams().setContentCharset(charset);
            httpClient.getParams().setCredentialCharset(charset);
//			getMethod.addRequestHeader("Accept-Encoding", "gzip, deflate");
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            return getMethod.getResponseBodyAsString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            //先移除无用的IP
            removeUnavailableIp(currentDynamicProxyIp);
            //再切换IP重新登录
            currentDynamicProxyIp = getDynamicIp();
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            //先移除无用的IP
            removeUnavailableIp(currentDynamicProxyIp);
            //再切换IP重新登录
            currentDynamicProxyIp = getDynamicIp();
        } catch (ProtocolException e) {
            e.printStackTrace();
            //先移除无用的IP
            removeUnavailableIp(currentDynamicProxyIp);
            //再切换IP重新登录
            currentDynamicProxyIp = getDynamicIp();
        } catch (ConnectException e) {
            e.printStackTrace();
            //先移除无用的IP
            removeUnavailableIp(currentDynamicProxyIp);
            //再切换IP重新登录
            currentDynamicProxyIp = getDynamicIp();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (getMethod != null)
                getMethod.releaseConnection();
        }
        return null;
    }


    public static String getContent(String url, String encoder) throws IOException {
        GetMethod getMethod = null;
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            //httpClient.getHostConfiguration().setProxy("111.161.163.222", 6665);
            httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
            getMethod = new GetMethod(url);
            getMethod.addRequestHeader("Connection", "close");

            getMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0");

            getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 3000);
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoder);
            httpClient.getParams().setContentCharset(encoder);
            httpClient.getParams().setCredentialCharset(encoder);

            if (url.indexOf("http://cn.bing.com") > -1) {
//				getMethod.setRequestHeader("Cookie", "SRCHHPGUSR=NEWWND=1&ADLT=STRICT&NRSLT=50&NRSPH=2&SRCHLANG=&AS=1;");
            } else if (url.indexOf("http://www.youdao.com/search") > -1) {
                // getMethod.getParams().setParameter("http.protocol.cookie-policy",
                // CookiePolicy.BROWSER_COMPATIBILITY);
                getMethod
                        .setRequestHeader(
                                "Cookie",
                                "sbt=1303958840550; OUTFOX_SEARCH_USER_ID=-1678663480@114.132.243.206; JSESSIONID=abc8jdg-_JhcveiolaA_s; YOUDAO_MOBILE_ACCESS_TYPE=1; _PREF_ANONYUSER__SUGGEST=c3VnZ2VzdD1vcGVu; _PREF_ANONYUSER__WEB=bWFnbmlmaWVyPTpyZXN1bHRWaWV3PV9ibGFuazpsYW5ndWFnZT0yNTU6YmxvZ1NlYXJjaD1hcnRp_Y2xlOnJlc3VsdENvdW50RGlzcGxheT0zMDptYWduaWZpZXJJY29uPQ==; _PREF_ANONYUSER__DICT=dHJhbnNsYXRpb249ZXhwYW5kX2ZpcnN0; _PREF_ANONYUSER__IMAGE=aW1hZ2VTbGlkZVNob3dUaW1lPTM=; _PREF_ANONYUSER__IMAGE-LOCAL=cmVzdWx0Q291bnREaXNwbGF5SW1hZ2U9MjQ=");
            } else if (url.indexOf("http://www.qihoo.com") > -1) {
                getMethod
                        .setRequestHeader(
                                "Cookie",
                                "B=ID=984051309159946:V=2:S=b8de9df6b5; __utma=194495986.754955894.1309160408.1309160408.1309247171.2; __utmz=194495986.1309160408.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); QT_UID=63ff28bba397b03b; UT=T=1309159946:F=cWlob28uY29t:S=1:REF=qihoo.com; __utmc=194495986; __utmb=194495986.2.10.1309247171");
            }
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            return getMethod.getResponseBodyAsString();
        } catch (RedirectException e) {
            String result = getContentUseProxy(url, encoder);
            if (result == null)
                result = getContentUseProxy(url, encoder);
            if (result == null)
                result = getContentUseProxy(url, encoder);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (getMethod != null)
                getMethod.releaseConnection();
        }
        return null;
    }

    public static String down(String url) {
        GetMethod getMethod = null;
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
            httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
            getMethod = new GetMethod(url);
            getMethod.addRequestHeader("Connection", "close");
            getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
            getMethod.setFollowRedirects(true);
            getMethod.addRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows XP)");
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode == HttpStatus.SC_OK) {
                return StringUtil.encodingHtml(getMethod.getResponseBodyAsStream(), getMethod.getRequestCharSet());
            }
            return null;
        } catch (Exception e) {
            // System.out.println("error url: " + url);
            System.err.println(url + "---" + e.getMessage());
        } finally {
            if (getMethod != null)
                getMethod.releaseConnection();
        }
        return null;
    }

    /**
     * 删除input字符串中的html格式
     *
     * @param input
     * @param length
     * @return
     */
    public static String splitAndFilterString(String input, int length) {
        if (input == null || input.trim().equals("")) {
            return "";
        }
        // 去掉所有html元素,
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");
        int len = str.length();
        if (len <= length) {
            return str;
        } else {
            str = str.substring(0, length);
        }
        return str;
    }

    /**
     * 去掉html代码中的注释 包括<!-- --> 和 <!--- ---> <!-- --> 和 <!---
     * --->会影响htmlparser的抓取
     *
     * @param html
     * @return
     */
    public static String throwAnnotation(String html) {
        while (html.indexOf("<!--") >= 0) {
            if (html.substring(html.indexOf("<!--") + 4).indexOf("-->") >= 0) {
                String prefixString = html.substring(0, html.indexOf("<!--"));
                String postfixString = html.substring(html.indexOf("<!--") + 4);
                postfixString = postfixString.substring(postfixString.indexOf("-->") + 3);
                html = prefixString + postfixString;
            } else {
                break;
            }
            if (html.length() > 1000000)
                break;
        }
        html = html.replaceAll("<!--", "");
        return html;
    }

    /**
     * unicodeToUtf8
     *
     * @param theString
     * @return
     */
    public String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        String temp = "0";
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '&') {
                aChar = theString.charAt(x++);
                if (aChar == '#') {
                    int value = 0;
                    for (int i = 0; i < 6; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                temp = Integer.toHexString(value);
                                break;
                            case ';':
                                break;
                            default:
                                x--;
                        }
                    }
                    outBuffer.append((char) Integer.valueOf(temp).intValue());
                } else {
                    outBuffer.append('&');
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    // 把unicode转化为汉字
    public static String unicodeConvert(String str) {
        char[] in = str.toCharArray();
        int off = 0;
        int len = str.length();
        char[] out = new char[len];
        char aChar;
        int outLen = 0;
        while (off < len) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = in[off++];
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++] = (char) value;
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    out[outLen++] = aChar;
                }
            } else {
                out[outLen++] = (char) aChar;
            }
        }
        return new String(out, 0, outLen);
    }

    public static String trimEmpty(String content) {
        return content.replace("\r\n", "").replace("\t", "").replace(" ", "");
    }

    public static String replaceBlank(String content) {
        return content.replace("\r", " ").replace("\t", " ").replace("\n", " ").replaceAll(" +", " ").replace("&nbsp;", "").trim();
    }

}
