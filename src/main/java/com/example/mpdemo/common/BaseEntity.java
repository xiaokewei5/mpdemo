package com.example.mpdemo.common;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.example.mpdemo.enums.DeleteEnum;
import com.example.mpdemo.util.DateUt;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 * 实体公共类
 */
@Setter
@Getter
public class BaseEntity extends Base implements Serializable{
    
    private static final long serialVersionUID = 8050870209837326824L;
    
    @ApiModelProperty(value = "删除(0-否1-是)")
    //@TableLogic(value = "0",delval = "1") //配置二选一
    @TableField(fill = FieldFill.INSERT)
    private DeleteEnum deleted;
    
    @ApiModelProperty(value = "版本号")
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
    
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    //@JsonFormat(pattern = DateUt.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime createtime;
    
    @ApiModelProperty(value = "创建用户id")
    @TableField(fill = FieldFill.INSERT)
    private String createId;
    
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    //@JsonFormat(pattern = DateUt.YYYY_MM_DD)
    private LocalDateTime updatetime;
    
    @ApiModelProperty(value = "更新用户id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateId;
}
