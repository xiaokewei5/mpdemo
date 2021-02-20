package com.example.mpdemo.entity;

import com.example.mpdemo.common.BaseDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class UserDto extends BaseDto{

    @NotBlank(message = "账号名称不能为空")
    @Size(min = 5,max = 15,message = "长度在5到15之间（包含）")
    private String accname;
    @NotBlank(message = "姓名不能为空")
    @Size(min = 2,max = 5,message = "长度在2到5之间（包含）")
    private String uname;
    @NotBlank(message = "密码不能为空")
    @Size(min = 6,max = 15,message = "长度在6到15之间（包含）")
    private String passwd;
}
