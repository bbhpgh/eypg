package com.eypg.test;

public class TestThread {

    final static ThreadBean tb = new ThreadBean();

    public static void main(String[] args) {
        new Thread() {
            public void run() {
                try {
                    tb.makeStart();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        System.err.println("end");
    }

}
