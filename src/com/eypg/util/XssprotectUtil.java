package com.eypg.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import com.blogspot.radialmind.html.HTMLParser;
import com.blogspot.radialmind.html.HandlingException;
import com.blogspot.radialmind.xss.XSSFilter;

public class XssprotectUtil {

    public static String protectAgainstXSS(String html) {
        StringReader reader = new StringReader(html);
        StringWriter writer = new StringWriter();
        String text = null;
        try {
            // Parse incoming string from the "html" variable  
            HTMLParser.process(reader, writer, new XSSFilter(), true);
            // Return the parsed and cleaned up string  
            text = writer.toString();
        } catch (HandlingException e) {
            // Handle the error here in accordance with your coding policies...  
        } finally {
            try {
                writer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return text;
    }

}
