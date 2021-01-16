package com.jinhe.testdemo;

import com.jinhe.modules.system.dto.FileStoreDTO;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class Mytest {
    public Map<String,String> getTimeInterval(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        // System.out.println("所在周星期一的日期：" + imptimeBegin);
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        // System.out.println("所在周星期日的日期：" + imptimeEnd);
        String weekBegin=imptimeBegin+" 00:00:00";
        String weekEnd=imptimeEnd+" 23:59:59";
        Map<String,String> map=new HashMap<String,String>();
        map.put("weekBegin", weekBegin);
        map.put("weekEnd", weekEnd);
        return map;
    }
    @Test
    public void test1() {

        Map<String,String> weekMap=getTimeInterval(new Date());
        String weekBegin=weekMap.get("weekBegin");
        String weekEnd=weekMap.get("weekEnd");
        System.out.println(weekBegin);
        System.out.println(weekEnd);


//        FileStoreDTO fileStoreDTO = new FileStoreDTO();
//        String dd = fileStoreDTO.getClass().getName();
//        System.out.println(dd);
//        Integer weeknum = 4;
//        Date today = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(today);
//        int i = calendar.get(Calendar.DAY_OF_WEEK);
//        i=(i-1+7)%7;
//        //根据周期名字判断当天日期
//        int num = (weeknum - i+7) % 7;
//        calendar.add(Calendar.DATE,num);
//        Date time = calendar.getTime();
//        System.out.println(time);
    }

    @Test
    public void test2() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\from.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\to.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        byte[] bytes = new byte[1024];
        int len = -1;
        while ((len = bufferedInputStream.read(bytes)) != -1) {
            int length = bytes.length;
            fileOutputStream.write(bytes);
        }
        bufferedInputStream.close();
        fileOutputStream.close();

    }

    @Test
    public void integer() {
        Integer a = null;
        int b = a - 3;
        System.out.println(b);
    }

    @Test
    public void dateTest() {
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        System.out.println(time);
    }
}
