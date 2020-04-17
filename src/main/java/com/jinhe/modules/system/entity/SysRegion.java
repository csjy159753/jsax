package com.jinhe.modules.system.entity;

import java.time.LocalDateTime;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRegion implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 行政区名称
     */
    private String name;

    private String fullName;

    /**
     * 行政区代码
     */
    private String code;

    /**
     * 上级行政区代码
     */
    private String parentCode;

    /**
     * 城市代码(固定电话，例如北京010)
     */
    private String cityCode;

    /**
     * 行政区级别 0_国家 1_省级（province） 2_地区级（prefecture） 3_县级（county） 4_乡镇级（township） 5_行政村级（village） 6_自然村（natura villagel）
     */
    private Integer level;

    /**
     * 1_直辖市，2_计划单列市，3_省会城市，4_副省级市辖区，4_地级市，5_副地级市，6_县级市 0_非城市
     */
    private Integer cityType;

    /**
     * 是否是自治行政区
     */
    private Boolean isAutonomy;

    /**
     * 是否是经济特区
     */
    private Boolean isEconomic;

    /**
     * 是否是特别行政区
     */
    private Boolean isSar;

    /**
     * 是否是市辖区
     */
    private Boolean isDistrict;

    /**
     * 是否是旗（县、市、区）
     */
    private Boolean isBanner;

    /**
     * 是否是盟（市）
     */
    private Boolean isLeague;

    /**
     * 是否市独立林区
     */
    private Boolean isForestryArea;

    /**
     * 是否是一个苏木（县）
     */
    private Boolean isSumu;

    /**
     * 是否是自然村
     */
    private Boolean isNaturalVillage;

    /**
     * 为调查设立的虚拟区域
     */
    private Boolean isVirtual;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 经度
     */
    private Double lng;

    /**
     * 维度
     */
    private Double lat;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
