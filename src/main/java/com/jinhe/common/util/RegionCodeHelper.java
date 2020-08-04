package com.jinhe.common.util;

import lombok.experimental.var;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegionCodeHelper {

    public static String VaildCode(String code) {

        if (code.isEmpty()) return "0";
        if (code.length() < 12) {
            int len = code.length();
            for (int i = 0; i < 12 - len; i++) {
                code = code + 0;
            }
        } else if (code.length() > 12) {
            code = code.substring(0, 12);
        }
        for (int i = 0; i < 2; i++) {
            if (!code.endsWith("000")) break;
            code = code.substring(0, code.length() - 3);
        }
        if (code.length() <= 6) {
            for (int i = 0; i < 3; i++) {
                if (!code.endsWith("00")) break;
                code = code.substring(0, code.length() - 2);
            }
        }
        return code;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = VaildCode(a);
        System.out.println(b);
    }


}
