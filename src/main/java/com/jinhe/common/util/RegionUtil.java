package com.jinhe.common.util;

public class RegionUtil {
    public static String VaildCode(String regionCode) {
        if (StringUtils.isEmpty(regionCode)) {
            return null;
        }

        regionCode = org.apache.commons.lang.StringUtils.rightPad(regionCode, 12, "0");

        for (int i = 0; i < 2; i++) {
            if (!regionCode.endsWith("000")) {
                break;
            }
            regionCode = regionCode.substring(0, regionCode.length() - 3);
        }
        if (regionCode.length() <= 6) {
            for (int i = 0; i < 3; i++) {
                if (!regionCode.endsWith("00")) {
                    break;
                }
                regionCode = regionCode.substring(0, regionCode.length() - 2);
            }
        }
        return regionCode;
    }

    public static String GetRegionCode(String regionCode) {
        if (StringUtils.isEmpty(regionCode)) {
            return null;
        }

        regionCode = org.apache.commons.lang.StringUtils.rightPad(regionCode, 12, "0");

        for (int i = 0; i < 2; i++) {
            if (!regionCode.endsWith("000")) {
                break;
            }
            regionCode = regionCode.substring(0, regionCode.length() - 3);
        }
        if (regionCode.length() <= 6) {
            for (int i = 0; i < 3; i++) {
                if (!regionCode.endsWith("00")) {
                    break;
                }
                regionCode = regionCode.substring(0, regionCode.length() - 2);
            }
        }
        return regionCode;
    }

    public static void main(String[] args) {
        String str = RegionUtil.VaildCode("3200");
        System.out.println(str);
    }
}
