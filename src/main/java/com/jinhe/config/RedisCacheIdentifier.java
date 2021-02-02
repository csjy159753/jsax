package com.jinhe.config;

/**
 * @author Administrator
 */
public class RedisCacheIdentifier {
    public static final Long CACHE_SYS_CITY_LIST_EXPIRE_TIME = 10000L;
    /**
     * 所有省市区
     */
    public static final String SYS_CITY_LIST = "cache:api:city_list";
}
