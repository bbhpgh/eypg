package com.eypg.util;

import java.io.UnsupportedEncodingException;

import org.mozilla.intl.chardet.HtmlCharsetDetector;
import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;
import org.mozilla.intl.chardet.nsPSMDetector;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

public class MozillaEncoder {

    private volatile static MozillaEncoder singleteon;

    private MozillaEncoder() {
    }

    public static MozillaEncoder getinstance() {
        if (singleteon == null) {
            synchronized (MozillaEncoder.class) {
                singleteon = new MozillaEncoder();
            }
        }
        return singleteon;
    }

    public static String getEncoding(byte[] content) {
        nsDetector det = new nsDetector(nsPSMDetector.ALL);
        nsICharsetDetectionObserverImp nsIC = new nsICharsetDetectionObserverImp();
        det.Init(nsIC);
        det.DoIt(content, content.length, false);
        det.DataEnd();
        String encode = nsIC.getEncoding();
//		if (encode == null || encode.equals("")) {
//			try {
//				encode = getCharset(content);
//			} catch (Exception e) {
//			}
//		}
//		if (encode == null || encode.equals("")) {
//			encode = "GB2312";
//		}
        if (encode != null && !encode.equals("") && !encode.equalsIgnoreCase("utf-8")) {
            encode = "GB2312";
        }
        return encode;
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(MozillaEncoder.getEncoding("gongwenhua���Ļ�".getBytes("UTF-8")));
    }

    public static String getCharset(byte[] content) {
        CharsetDetector detector;
        CharsetMatch match;
        detector = new CharsetDetector();
        try {
            detector.setText(content);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        detector.enableInputFilter(true);
        match = detector.detect();
        String charset = match.getName();
        return charset;
    }

}

class nsICharsetDetectionObserverImp implements nsICharsetDetectionObserver {
    String encod = "";

    public void Notify(String charset) {
        HtmlCharsetDetector.found = true;
        encod = charset;
    }

    public String getEncoding() {
        return encod;
    }
}
