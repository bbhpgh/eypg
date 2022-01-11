package com.eypg.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public List<String> readIpFile() {
        List<String> strList = new ArrayList<String>();
        try {
            FileReader reader = new FileReader("E:\\ip.txt");
            BufferedReader br = new BufferedReader(reader);
            String s = null;
            while ((s = br.readLine()) != null) {
//		          System.out.println(s);
                strList.add(s);
            }
            br.close();
            reader.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return strList;
    }


    public List<String> readFile(String fileName) {
        List<String> strList = new ArrayList<String>();
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);
            String s = null;
            while ((s = br.readLine()) != null) {
//		          System.out.println(s);
                strList.add(s);
            }
            br.close();
            reader.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return strList;
    }

}
