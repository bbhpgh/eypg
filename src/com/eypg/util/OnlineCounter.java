package com.eypg.util;

/**
 * 在线人数的统计
 */
public class OnlineCounter {

    private static long online = 0;

    public static long getOnline() {
        return online;
    }

    public static void raise() {
        online++;
    }

    public static void reduce() {
        online--;
    }

}
