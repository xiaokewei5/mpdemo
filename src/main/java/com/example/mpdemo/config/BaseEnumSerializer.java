package com.example.mpdemo.config;

import com.example.mpdemo.common.IBaseEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @Author: fengyunxiaoxiao
 * @Date: 2021/8/19
 * @Description: 枚举序列化
 */

//可以使用JsonSerializer
public class BaseEnumSerializer extends JsonSerializer<IBaseEnum> {
//public class BaseEnumSerializer extends StdSerializer<IBaseEnum> {

    //protected BaseEnumSerializer(){
    //    super(IBaseEnum.class);
    //}

    @Override
    public void serialize(IBaseEnum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        //返回枚举对象值字符串
        //gen.writeString(value.getValue().toString());
        //返回枚举对象值(相同于添加注解@JsonValue)
        gen.writeObject(value.getValue());
        //enum2Object(value, gen);
    }

    /**
     * 把枚举转成对象
     */
    private void enum2Object(IBaseEnum value, JsonGenerator gen) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName(IBaseEnum.ID);
        gen.writeObject(value.getValue());
        gen.writeFieldName(IBaseEnum.NAME);
        gen.writeString(value.getName());
        gen.writeEndObject();
    }
}
