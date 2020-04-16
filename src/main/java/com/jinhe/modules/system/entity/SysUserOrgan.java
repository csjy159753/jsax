package com.jinhe.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Data
@Accessors(chain = true)
public class SysUserOrgan implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId("ID")
    private String id;

    /**
     * 用户id
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * 机构id
     */
    @TableField("ORGAN_ID")
    private String organId;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


}
