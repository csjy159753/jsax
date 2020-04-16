package com.jinhe.modules.system.dao;

import com.jinhe.modules.system.service.impl.SysResourceServiceImpl;

import java.lang.reflect.Method;

public class TestAnnotation {
    public static void main(String[] args) throws Exception {
       Class  cls = Class.forName("com.jinhe.modules.system.service.impl.SysResourceServiceImpl");

       Method[] methods = cls.getMethods();

       boolean flags = cls.isAnnotationPresent(SysResourceServiceImpl.class);

       System.out.println("该类是否有注释:"+flags);


       if(!flags){

           for (int i =0;i<methods.length;i++){

               System.out.println(methods[i].getName());
           }
       }

   	}

}
