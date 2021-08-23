package com.example.mpdemo.config;

import cn.hutool.core.util.StrUtil;
import com.example.mpdemo.common.IBaseEnum;
import com.example.mpdemo.util.DateUt;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @Author: fengyunxiaoxiao
 * @Date: 2021/8/19
 * @Description: 时间统一格式化
 */
@Configuration
public class JSONFormatConfig {

    private static final DateTimeFormatter ymdhms = DateTimeFormatter.ofPattern(DateUt.YYYY_MM_DD_HH_MM_SS);

    private static final DateTimeFormatter ymd = DateTimeFormatter.ofPattern(DateUt.YYYY_MM_DD);

    private static final DateTimeFormatter hms = DateTimeFormatter.ofPattern(DateUt.HH_MM_SS);

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //序列化设置时间格式
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(ymdhms));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(ymd));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(hms));
        //反序列化设置时间格式
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(ymdhms));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(ymd));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(hms));

        objectMapper.registerModule(javaTimeModule);
        //序列化枚举值为数据库存储值 缺少后打印枚举不能转成对应值，需自定义toString方法/或者使用@JsonValue注解
        //objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        //设置序列化枚举格式
        JsonComponentModule jsonComponentModule = new JsonComponentModule();
        jsonComponentModule.addSerializer(IBaseEnum.class,new BaseEnumSerializer());
        objectMapper.registerModule(jsonComponentModule);

        //把null值转成""
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(StrUtil.EMPTY);
            }
        });
        //使用下面的配置上述null转换将不起作用
        //或使用spring.jackson.default-property-inclusion=non_empty配置
        //过滤为null的属性
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //过滤为null和""的属性
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }
}
