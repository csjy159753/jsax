package com.jinhe.common.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AbstractEnum {

    private static final Map<Integer, AbstractEnum> nameEnumMap = new ConcurrentHashMap<>();


    protected Integer code;
    protected String msg;

    protected AbstractEnum() {
    }

    protected AbstractEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        if (!nameEnumMap.containsKey(code)) {
            nameEnumMap.put(code, this);
        }
    }

    public boolean equals(AbstractEnum abstractEnum) {
        return this.code == null || abstractEnum == null ? false : this.code.equals(abstractEnum.getCode());
    }


    public static AbstractEnum valueOf(String name) {
        if (name == null) {
            throw new NullPointerException("Name is null");
        }

        AbstractEnum result = nameEnumMap.get(name);
        if (result != null) {
            return result;
        }

        throw new IllegalArgumentException(
                "No enum constant exists, name is." + name);
    }

    public static void init() {
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}