package com.example.mpdemo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
public class AddUserDto {

    private static final long serialVersionUID = -8010531145628641275L;

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号名称不能为空")
    @Size(min = 5,max = 15,message = "长度在5到15之间（包含）")
    private String accname;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2,max = 5,message = "长度在2到5之间（包含）")
    private String uname;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6,max = 15,message = "长度在6到15之间（包含）")
    private String passwd;

    @ApiModelProperty(value = "组织类型(0-集团1-医院2-供应商)")
    @Min(value = 0,message = "组织类型不能小于0")
    @Max(value = 2,message = "组织类型不能大于2")
    @NotNull(message = "组织类型不能为空")
    private Integer orgtype;

    @ApiModelProperty(value = "手机号")
    @Size(min = 11,max = 11,message = "手机号长度为11位")
    @NotBlank(message = "手机号不能为空")
    private String uphone;

    @ApiModelProperty(value = "用户类型")
    @Min(value = 0,message = "用户类型不能小于0")
    @Max(value = 2,message = "用户类型不能大于2")
    @NotNull(message = "用户类型不能为空")
    private Integer utype;

    @ApiModelProperty(value = "个性头像")
    private String uphoto;

}
