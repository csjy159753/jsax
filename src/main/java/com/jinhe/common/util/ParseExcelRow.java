package com.jinhe.common.util;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;

public interface ParseExcelRow<T> {
      T execute(Row row);
}
