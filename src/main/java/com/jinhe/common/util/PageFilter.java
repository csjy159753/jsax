package com.jinhe.common.util;

import lombok.Data;

@Data
public class PageFilter {
    private int start;
    private int length;
    private String order;
    private String sidx;
}
