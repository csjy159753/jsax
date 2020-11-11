package com.jinhe.common.util;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class ListSub<T> {
    private long total;
    private List<T> list;
}