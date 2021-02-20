package com.example.mpdemo.common;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.example.mpdemo.util.DateUt;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 实体公共类
 */
@Setter
@Getter
public class BaseEntity implements Serializable{
    
    private static final long serialVersionUID = 8050870209837326824L;
    
    @ApiModelProperty(value = "删除(0-否1-是)")
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
    
    @ApiModelProperty(value = "版本号")
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
    
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = DateUt.NORM_DATETIME_PATTERN)
    private Date createtime;
    
    @ApiModelProperty(value = "创建用户id")
    @TableField(fill = FieldFill.INSERT)
    private String createId;
    
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = DateUt.NORM_DATETIME_PATTERN)
    private Date updatetime;
    
    @ApiModelProperty(value = "更新用户id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateId;
}
