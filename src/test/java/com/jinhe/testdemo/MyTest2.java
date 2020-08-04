package com.jinhe.testdemo;

import java.util.UUID;

public class MyTest2 {
    public static void main(String[] args) {
        String s = UUID.randomUUID().toString();
        String uuid = s.replace("-","");
        System.out.println(uuid);
    }
}
