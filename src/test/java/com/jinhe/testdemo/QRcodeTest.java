package com.jinhe.testdemo;

import com.google.zxing.Result;
import com.jinhe.common.config.QRCodeProperties;
import com.jinhe.common.util.QRCodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QRcodeTest {
    @Autowired
    QRCodeProperties qrCodeProperties;

    /**
     * 提供一个接口，可以根据list生成二维码，并提供打包下载
     */


    @Test
    public void testQRcode() {
        String logopath = qrCodeProperties.getLogoPath();
        String path = qrCodeProperties.getPath();
        String temppath = qrCodeProperties.getTempPath();
        String content = "testQRCode";
        System.out.println(path);
        /*
        测试给定logo和内容的二维码生成
        给定的path为目录，生成为.jpg的图片
        boolean b = QRCodeUtil.onlineCodeCreate(content, path, 250,logopath+"logo1.jpg");
        System.out.println(b);
         */

        /*
        测试没有logo只有内容的二维码生成
        给定的path为目录，生成为.jpg的图片
         boolean b = QRCodeUtil.onlineCodeCreate(content, path, 250,null);
        System.out.println(b);
         */

        /*
        测试已知二维码与logo的合成
        path为指定的文件名
        File pic = new File(temppath + "113.jpg");
        File logo = new File(logopath + "logo2.jpg");
        boolean b = QRCodeUtil.onlineCodeCreate(pic, logo, path+ UUID.randomUUID()+".jpg");
        System.out.println(b);
         */

        /*
        测试二维码解析
         Result result = QRCodeUtil.onlineCodeAnalyze("C:\\Users\\Administrator\\Desktop\\qrcode\\676.jpg");
        System.out.println(result);
         */
    }

    @Test
    public void testZip() {

    }
}
