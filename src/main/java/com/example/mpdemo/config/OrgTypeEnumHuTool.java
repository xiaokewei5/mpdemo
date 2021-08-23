package com.example.mpdemo.config;

import cn.hutool.json.JSON;
import cn.hutool.json.serialize.JSONObjectSerializer;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.example.mpdemo.common.IBaseEnum;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author: fengyunxiaoxiao
 * @Date: 2021/8/20
 * @Description: hutool自定义序列化
 */
public class OrgTypeEnumHuTool implements JSONObjectSerializer {
    @Override
    public void serialize(JSON json, Object bean) {
        if (bean instanceof IBaseEnum) {
            if (Objects.nonNull(json)) {
                Class<IBaseEnum> clazz = IBaseEnum.class;
                String fieldName = getEnumFieldName(clazz);
                json.putByPath(fieldName, "cc");
            }
        }
    }

    private String getEnumFieldName(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if ("getValue".equals(method.getName())) {
                Class<?> returnType = method.getReturnType();
                Field[] fields = clazz.getFields();
                for (Field field : fields){
                    Class<?> type = field.getType();
                    if (Objects.equals(returnType, type)){
                        String name = type.getName();
                        return name;
                    }
                }
            }
        }
        return "aa";
    }
}
