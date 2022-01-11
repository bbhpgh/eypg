package com.eypg.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomUtil {

    public static String random(int size) {
        List<String> ranList = new ArrayList<String>();
        int r = 10000000;
        Random random = new Random();
        for (int j = 0; j < size; j++) {
            int a = random.nextInt(1);
            int b = random.nextInt(1);
            int c = random.nextInt(9);
            int d = random.nextInt(9);
            int e = random.nextInt(9);
            int f = random.nextInt(9);
            int g = random.nextInt(9);
            ranList.add(String.valueOf(r + Integer.parseInt(a + "" + b + "" + c + "" + d + "" + e + "" + f + "" + g)));
        }
        return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
    }

    public static String newRandom(int size, int price, List<String> oldRandomList) {
        List<String> randomList = new ArrayList<String>();
        int r = 10000000;
        for (int i = 1; i <= price; i++) {
            if (!oldRandomList.contains(String.valueOf(r + i)))
                randomList.add(String.valueOf(r + i));
        }

        List<String> ranList = new ArrayList<String>();
        for (int j = 0; j < size; j++) {
            if (randomList.size() > 0) {
                int ran = new Random().nextInt(randomList.size());
                ranList.add(randomList.get(ran));
                randomList.remove(ran);
            }
        }
        return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
    }

    public static void main(String[] args) {
        List<String> oldRandomList = new ArrayList<String>();
//		oldRandomList.add("10000003");
//		oldRandomList.add("10000002");
        String strList = RandomUtil.newRandom(8, 10, oldRandomList);
        System.err.println(strList);
    }
}
