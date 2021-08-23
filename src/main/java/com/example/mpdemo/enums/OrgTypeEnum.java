package com.example.mpdemo.enums;

import com.example.mpdemo.common.IBaseEnum;
import com.example.mpdemo.config.BaseEnumSerializer;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Author: fengyunxiaoxiao
 * @Date: 2021/8/17
 * @Description: 组织类型
 */
//不使用全局配置可使用该注解
//@JsonSerialize(using = BaseEnumSerializer.class)
public enum OrgTypeEnum implements IBaseEnum<Integer> {
    ADMIN(0,"管理员"),
    HOS(1,"医院"),
    SUP(2,"供应商");

    OrgTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //@EnumValue
    //使用JsonValue注解可格式化显示值
    //@JsonValue
    private final int id;
    private final String name;

    @Override
    public Integer getValue() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    //使用JsonValue注解可注释掉toString
    /*@Override
    public String toString() {
        //根据情况给前台数据展示code或descp
        return String.valueOf(this.descp);
    }*/
}
