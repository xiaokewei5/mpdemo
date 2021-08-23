package com.example.mpdemo.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.mpdemo.common.BaseEntity;
import com.example.mpdemo.enums.OrgTypeEnum;
import com.example.mpdemo.enums.YesNoEnum;
import com.example.mpdemo.util.DateUt;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("sys_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -8010531145628641275L;

    @ApiModelProperty(value = "用户id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "账号")
    private String accname;

    @ApiModelProperty(value = "用户名")
    private String uname;

    @ApiModelProperty(value = "密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwd;

    @ApiModelProperty(value = "组织类型(0-集团1-医院2-供应商)")
    private OrgTypeEnum orgtype;

    @ApiModelProperty(value = "组织id")
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private String orgId;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "手机号")
    private String uphone;

    @ApiModelProperty(value = "用户类型")
    private Integer utype;

    @ApiModelProperty(value = "个性头像")
    private String uphoto;

    @ApiModelProperty(value = "浏览器")
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private String browser;

    @ApiModelProperty(value = "登录ip")
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private String loginip;

    @ApiModelProperty(value = "登录时间")
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    //@JsonFormat(pattern = DateUt.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime logindate;

    @ApiModelProperty(value = "主用户id")
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private String mainId;

    @ApiModelProperty(value = "激活(0-否1-是)")
    private YesNoEnum actived;

    @ApiModelProperty(value = "审核时间")
    //@JsonFormat(pattern = DateUt.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime applydate;

    @ApiModelProperty(value = "审核用户id")
    private String applyId;

    @ApiModelProperty(value = "驳回原因")
    private String rejreasons;

    @ApiModelProperty(value = "启用(0-否1-是)")
    //@TableField(select=false)
    private YesNoEnum enabled;
    
}
