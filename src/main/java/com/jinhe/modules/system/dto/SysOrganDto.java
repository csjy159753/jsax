package com.jinhe.modules.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysOrganDto implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */

    private String id;

    /**
     * 父类ID
     */

    private String parentId;

    /**
     * 类型
     */

    private Integer type;

    /**
     * 编码
     */

    private String code;

    /**
     * 名称
     */

    private String name;

    /**
     * 全名
     */

    private String fullName;

    /**
     * 行政区编码
     */

    private String regionCode;

    /**
     * 行政区名称
     */

    private String regionName;

    /**
     * 描述
     */

    private String description;

    /**
     * 状态
     */

    private Integer state;

    /**
     * 排序
     */

    private Integer sort;


    private String path;

    /**
     * 深度
     */

    private Integer depth;

    /**
     * 创建人
     */

    private String createUser;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private LocalDateTime updateTime;


}
