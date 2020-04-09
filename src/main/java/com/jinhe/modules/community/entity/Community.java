package com.jinhe.modules.community.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 小区表
 * </p>
 *
 * @author rls
 * @since 2020-04-07
 */
@Data
@Accessors(chain = true)
public class Community extends Model<Community> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private String id;
    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date CreateTime;

    /**
     * 更新时间
     */
    private Date UpdateTime;

    /**
     * 行政区编码
     */
    private String regionCode;

    /**
     * 行政区名称
     */
    private String regionName;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
