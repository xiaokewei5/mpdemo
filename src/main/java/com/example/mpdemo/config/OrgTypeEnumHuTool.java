package com.example.mpdemo.config;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.serialize.GlobalSerializeMapping;
import cn.hutool.json.serialize.JSONObjectSerializer;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.example.mpdemo.common.IBaseEnum;
import com.example.mpdemo.entity.User;
import com.example.mpdemo.enums.DeleteEnum;
import com.example.mpdemo.enums.YesNoEnum;
import com.example.mpdemo.util.BeanUt;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: fengyunxiaoxiao
 * @Date: 2021/8/20
 * @Description: hutool自定义序列化
 */
public class OrgTypeEnumHuTool implements JSONObjectSerializer {
    @SneakyThrows
    @Override
    public void serialize(JSON json, Object bean) {
        User user = (User) bean;
        Map<String, Object> objectMap = BeanUt.beanToMap(user);
        JSONObject jsonObject = new JSONObject(objectMap);
        BeanUt.copyProperties(jsonObject,json);
        //Field[] fields = user.getClass().getDeclaredFields();
        Field[] fields = ReflectUtil.getFields(User.class);
        for (Field field : fields) {
            AnnotatedType[] annotatedInterfaces = field.getType().getAnnotatedInterfaces();
            for (AnnotatedType nnnotatedType : annotatedInterfaces) {
                Type type = nnnotatedType.getType();
                String typeName = type.getTypeName();
                if (typeName.startsWith(IBaseEnum.class.getName())) {
                    IBaseEnum baseEnum = (IBaseEnum) ReflectUtil.getFieldValue(user,field);
                    //枚举格式成值
                    json.putByPath(field.getName(),baseEnum.getValue());
                    //configEnum(json, field, baseEnum);
                }
            }
        }
    }

    /**
     * 枚举分开格式化
     * eg:"orgtype":{"name":"管理员","id":0}
     */
    private void configEnum(JSON json, Field field, IBaseEnum baseEnum) {
        JSONObject baseEnumJson = new JSONObject();
        baseEnumJson.putOpt(IBaseEnum.ID,baseEnum.getValue());
        baseEnumJson.putOpt(IBaseEnum.NAME,baseEnum.getName());
        json.putByPath(field.getName(),baseEnumJson);
    }
}
