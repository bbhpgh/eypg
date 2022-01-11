package com.eypg.test;

import java.util.ArrayList;
import java.util.List;

import com.eypg.util.RandomUtil;


public class TestRandom {

    public static void main(String[] args) {
        String strList = RandomUtil.random(5);
        System.err.println(strList);
    }

}
