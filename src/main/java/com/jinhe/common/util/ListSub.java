package com.jinhe.common.util;

import lombok.Data;

import java.util.List;

@Data
public class ListSub<T>{
    private long total = 1;
    private List<T> list;
    private boolean tree = true;
}