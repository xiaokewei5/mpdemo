package com.example.mpdemo.entity;

import cn.hutool.json.JSONObject;
import com.example.mpdemo.enums.OrgTypeEnum;
import com.example.mpdemo.util.DateUt;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

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
    //@Min(value = 0,message = "组织类型不能小于0")
    //@Max(value = 2,message = "组织类型不能大于2")
    @NotNull(message = "组织类型不能为空")
    private OrgTypeEnum orgtype;

    @ApiModelProperty(value = "手机号")
    @Size(min = 11,max = 11,message = "手机号长度为11位")
    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}",message = "手机格式不正确")
    @NotBlank(message = "手机号不能为空")
    private String uphone;

    @ApiModelProperty(value = "用户类型")
    @Min(value = 0,message = "用户类型不能小于0")
    @Max(value = 2,message = "用户类型不能大于2")
    @NotNull(message = "用户类型不能为空")
    private Integer utype;

    @ApiModelProperty(value = "个性头像")
    private String uphoto;

    @ApiModelProperty(value = "创建时间")
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createtime;

}
