package com.jinhe.modules.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 'mashz.act_id_user' is not BASE TABLE
 * </p>
 *
 * @author rls
 * @since 2020-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLoginCount implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private String id;

    /**
     * 行政区编码
     */
    private String regionCode;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 类型 0未知 1手机登录 2pc登录
     */
    private Integer type;

    /**
     * 累计登录次数
     */
    private Integer count;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新
     */
    private LocalDateTime updateTime;


}
