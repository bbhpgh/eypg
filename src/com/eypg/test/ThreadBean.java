package com.eypg.test;

public class ThreadBean {

    public void makeStart() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            System.err.println(i);
            System.err.println("休息5秒");
            Thread.sleep(5000);
        }
    }

}
