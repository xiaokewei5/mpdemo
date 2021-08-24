package com.example.mpdemo.enums;

import com.example.mpdemo.common.IBaseEnum;

/**
 * @Author: fengyunxiaoxiao
 * @Date: 2021/8/20
 * @Description: 是否枚举
 */
//不使用全局配置可使用该注解
//@JsonSerialize(using = BaseEnumSerializer.class)
public enum YesNoEnum implements IBaseEnum<Boolean> {
    YES(true, "是"),
    NO(false, "否");

    //使用JsonValue注解可格式化显示值
    //@JsonValue
    private final boolean id;
    private final String name;

    YesNoEnum(boolean id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Boolean getValue() {
        return this.id;
    }

}
