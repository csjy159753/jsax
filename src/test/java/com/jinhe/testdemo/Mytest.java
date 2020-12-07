package com.jinhe.testdemo;

import com.jinhe.modules.system.dto.FileStoreDTO;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class Mytest {

    @Test
    public void test1() {
        FileStoreDTO fileStoreDTO = new FileStoreDTO();
        String dd = fileStoreDTO.getClass().getName();
        System.out.println(dd);
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
