package com.jinhe.testdemo;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.jinhe.common.util.ReflectionUtils;
import com.jinhe.modules.system.dto.SysRoleChDTO;
import org.junit.Test;

/**
 * 测试类,用JUnit4 进行测试
 *
 * @author syh
 */

public class ReflectionUtilsTest {

    /**
     * 测试获取父类的各个方法对象
     */

    @Test
    public void testGetDeclaredMethod() {


    }

    /**
     * 测试调用父类的方法
     *
     * @throws Exception
     */

    @Test
    public void testInvokeMethod() throws Exception {
        SysRoleChDTO obj = new SysRoleChDTO();
        obj.setId("aaa");
        List<SysRoleChDTO>  all=new ArrayList<>();
        all.add(new SysRoleChDTO());
//        obj.setChildren(all);
        //调用父类的公共方法
//        Object objkk = ReflectionUtils.invokeMethod(obj, "getId", null, null);

        //调用父类的默认方法
        Object objkk1 = ReflectionUtils.invokeMethod(obj, "setChildren", new Class[]{java.util.List.class}, new Object[]{all});

        //调用父类的被保护方法
//        Object objkk2 = ReflectionUtils.invokeMethod(obj, "setChildren", null, null);

        //调用父类的私有方法
        Object objkk3 = ReflectionUtils.invokeMethod(obj, "getChildren", null, null);
    }

    /**
     * 测试获取父类的各个属性名
     */

    @Test
    public void testGetDeclaredField() {


        Object obj = new SysRoleChDTO();

        //获取公共属性名
        Field publicField = ReflectionUtils.getDeclaredField(obj, "publicField");
        System.out.println(publicField.getName());

        //获取公共属性名
        Field defaultField = ReflectionUtils.getDeclaredField(obj, "defaultField");
        System.out.println(defaultField.getName());

        //获取公共属性名
        Field protectedField = ReflectionUtils.getDeclaredField(obj, "protectedField");
        System.out.println(protectedField.getName());

        //获取公共属性名
        Field privateField = ReflectionUtils.getDeclaredField(obj, "privateField");
        System.out.println(privateField.getName());

    }

    @Test
    public void testSetFieldValue() {

        Object obj = new SysRoleChDTO();

        System.out.println("原来的各个属性的值: ");
        System.out.println("publicField = " + ReflectionUtils.getFieldValue(obj, "publicField"));
        System.out.println("defaultField = " + ReflectionUtils.getFieldValue(obj, "defaultField"));
        System.out.println("protectedField = " + ReflectionUtils.getFieldValue(obj, "protectedField"));
        System.out.println("privateField = " + ReflectionUtils.getFieldValue(obj, "privateField"));

        ReflectionUtils.setFieldValue(obj, "publicField", "a");
        ReflectionUtils.setFieldValue(obj, "defaultField", "b");
        ReflectionUtils.setFieldValue(obj, "protectedField", "c");
        ReflectionUtils.setFieldValue(obj, "privateField", "d");

        System.out.println("***********************************************************");

        System.out.println("将属性值改变后的各个属性值: ");
        System.out.println("publicField = " + ReflectionUtils.getFieldValue(obj, "publicField"));
        System.out.println("defaultField = " + ReflectionUtils.getFieldValue(obj, "defaultField"));
        System.out.println("protectedField = " + ReflectionUtils.getFieldValue(obj, "protectedField"));
        System.out.println("privateField = " + ReflectionUtils.getFieldValue(obj, "privateField"));

    }

    @Test
    public void testGetFieldValue() {

        Object obj = new SysRoleChDTO();

        System.out.println("publicField = " + ReflectionUtils.getFieldValue(obj, "publicField"));
        System.out.println("defaultField = " + ReflectionUtils.getFieldValue(obj, "defaultField"));
        System.out.println("protectedField = " + ReflectionUtils.getFieldValue(obj, "protectedField"));
        System.out.println("privateField = " + ReflectionUtils.getFieldValue(obj, "privateField"));
    }

}