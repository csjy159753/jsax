package com.jinhe.modules.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="DStudent对象", description="")
public class DStudentDto implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "学科")
    private String subject;

    @ApiModelProperty(value = "分数")
    private Integer score;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "分数项")
    private String item;

}
