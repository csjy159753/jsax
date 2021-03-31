package com.jinhe.common.config;

/**
 * java常量类
 *
 * @author Administrator
 */
public interface LongSwingConstants {
    /**
     * 常量规则：1是0否
     */

    /**
     * 超级管理员类型
     */
    public static final Integer USER_TYPE_ROOT_ADMIN = 99;
    /**
     * 管理员类型
     */
    public static final Integer USER_TYPE_ADMIN = 98;
    public static final String comma = ",";

    public static final String id = "id";

    public class Number {
        public static final Integer ZERO = 0;
        public static final Integer ONE = 1;
        public static final Integer TWO = 2;
        public static final Integer THREE = 3;
        public static final Integer FOUR = 4;
        public static final Integer FIVE = 5;
        public static final Integer SIX = 6;
        public static final Integer SEVEN = 7;
        public static final Integer EIGHT = 8;
        public static final Integer NINE = 9;
        public static final Integer TEN = 10;
    }

    /**
     * 资源菜单type类型
     */
    public class SysResource {
        public static final Integer TYPE_NORMAL = 1;
        public static final Integer TYPE_ROOT_ADMIN = 2;
        public static final Integer TYPE_ADMIN = 3;
    }
}
