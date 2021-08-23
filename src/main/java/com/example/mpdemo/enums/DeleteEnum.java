package com.example.mpdemo.enums;

import com.example.mpdemo.common.IBaseEnum;
import com.example.mpdemo.config.BaseEnumSerializer;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Author: fengyunxiaoxiao
 * @Date: 2021/8/17
 * @Description: 是否删除
 */
//不使用全局配置可使用该注解
//@JsonSerialize(using = BaseEnumSerializer.class)
public enum DeleteEnum implements IBaseEnum<Boolean> {

    DELETE_YES(true,"已删除"),
    DELETE_NO(false,"未删除");

    //使用JsonValue注解可格式化显示值
    //@JsonValue
    private final boolean id;
    private final String name;

    DeleteEnum(boolean id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Boolean getValue() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    //使用JsonValue注解可注释掉toString
    /*@Override
    public String toString() {
        //根据情况给前台数据展示deleted或descp
        return String.valueOf(this.deleted);
    }*/
}
