package com.example.mpdemo.common;


import cn.hutool.core.util.EnumUtil;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.example.mpdemo.enums.DeleteEnum;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: fengyunxiaoxiao
 * @Date: 2021/8/17
 * @Description: 枚举接口
 */
public interface IBaseEnum<T extends Serializable> extends IEnum<T> {

    String ID = "id";

    String NAME = "name";

    String getName();

    static Map<?,String> getEnumMap(final Class clazz) {
        LinkedHashMap<String, IBaseEnum> linkedHashMap = EnumUtil.getEnumMap(clazz);
        Map<?, String> enumMap = linkedHashMap.entrySet().stream()
                .collect(Collectors.toMap(a -> a.getValue().getValue(), a -> a.getValue().getName(),(a, b) -> b));
        return enumMap;
    }

}
