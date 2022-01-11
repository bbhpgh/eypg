package com.eypg.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.jdesktop.swingx.util.OS;

public class CompressJSorCSS {
    public static void main(String[] args) throws IOException, InterruptedException {
        Compress(null, null);
    }

    public static void Compress(String inputStr, String outputStr) throws IOException, InterruptedException {
//		java -jar yuicompressor-2.4.2.jar index.js -o index.min.js --charset utf-8
        String windowsPath = "C:\\";
        String linuxPath = "/root/";
        List<String> cmd = new ArrayList<String>();
        if (OS.isWindows()) {
            cmd.add(windowsPath);
            cmd.add("java -jar yuicompressor-2.4.2.jar index.js -o index.min.js --charset utf-8");
            ProcessBuilder pb = new ProcessBuilder();
            pb.directory(new File("C:\\"));
            pb.command(cmd);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            int w = process.waitFor();
            System.out.println("status:" + w);
        } else if (OS.isLinux()) {
            cmd.add(linuxPath);
            cmd.add("/root/phantomjs-1.7.0/examples/rasterize.js");
            ProcessBuilder pb = new ProcessBuilder();
            pb.directory(new File("/root/phantomjs-1.7.0"));
            pb.command(cmd);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            int w = process.waitFor();
            System.err.println(cmd.toString());
            System.out.println("status:" + w);
        }
    }

}
