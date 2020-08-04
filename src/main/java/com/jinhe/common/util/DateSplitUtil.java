package com.jinhe.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSplitUtil {

    public static String[] dateSplit(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        String Y = simpleDateFormat.format(date).substring(0, 4);
        String M = "0".equals(simpleDateFormat.format(date).substring(5, 6))
                ? simpleDateFormat.format(date).substring(6, 7) : simpleDateFormat.format(date).substring(5, 7);
        String D = "0".equals(simpleDateFormat.format(date).substring(8, 9))
                ? simpleDateFormat.format(date).substring(9, 10) : simpleDateFormat.format(date).substring(8, 10);
        String[] s = {Y, M, D};
        return s;
    }

}
