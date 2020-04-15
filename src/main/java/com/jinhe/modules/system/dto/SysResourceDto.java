package com.jinhe.modules.system.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
/**
* author:xk
* date:2020.04.15
**/
@Data
public class SysResourceDto implements Serializable {


        private static final long serialVersionUID = 1L;

        /**
         * 主键id
         */
        private String id;

        /**
         * 父键id
         */
        private String parentId;

        /**
         * 英文名称
         */
        private String name;

        /**
         * 显示名称
         */

        private String displayName;

        /**
         * 描述
         */

        private String note;

        /**
         * 状态0正常1禁用
         */

        private Integer state;

        /**
         * 类型（预留）
         */

        private Integer type;

        /**
         * 排序
         */

        private Integer sort;

        /**
         * url路径
         */

        private String path;

        /**
         * 小图标
         */

        private String icon;

        /**
         * 创建者id
         */

        private String createUserId;

        /**
         * 创建时间
         */
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
        @JsonSerialize(using = LocalDateTimeSerializer.class)//显示
        private LocalDateTime createTime;

        /**
         * 更新
         */
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
        @JsonSerialize(using = LocalDateTimeSerializer.class)//显示
        private LocalDateTime updateTime;

        /**
         * 显示类型
         */

        private Integer displayType;


}
