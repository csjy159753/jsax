package com.jinhe.common.util;

public class RegionUtil {
    public static String GetShortRegionCode(String regionCode) {
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


    /**
     * 获取最大等级
     *
     * @param regionCode
     * @return
     */
    public static int maxLevel(String regionCode) {
        if (org.apache.commons.lang.StringUtils.isBlank(regionCode)) {
            return 0;
        }
        regionCode = GetShortRegionCode(regionCode);
        int length = regionCode.length();

        if (length <= 6 && length % 2 == 0) {
            return length / 2;
        } else {
            return 3 + (length - 6) / 3;
        }
    }

    /**
     *  * @功能 String左对齐
     *  
     */
    public static String padLeft(String src, int len, char ch) {
        int diff = len - src.length();
        if (diff <= 0) {
            return src;
        }

        char[] charr = new char[len];
        System.arraycopy(src.toCharArray(), 0, charr, 0, src.length());
        for (int i = src.length(); i < len; i++) {
            charr[i] = ch;
        }
        return new String(charr);
    }

    /**
     *  * @功能 String右对齐
     *  
     */
    public static String padRight(String src, int len, char ch) {
        int diff = len - src.length();
        if (diff <= 0) {
            return src;
        }

        char[] charr = new char[len];
        System.arraycopy(src.toCharArray(), 0, charr, diff, src.length());
        for (int i = 0; i < diff; i++) {
            charr[i] = ch;
        }
        return new String(charr);
    }

    public static String Format(String code, int length) {
        if (code == null) {
            return null;
        }
        return padRight(code, length, '0');
    }

    public static void main(String[] args) {
        String str = RegionUtil.GetShortRegionCode("3200");
        System.out.println(str);
    }
}
