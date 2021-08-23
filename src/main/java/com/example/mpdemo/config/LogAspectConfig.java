package com.example.mpdemo.config;

import cn.hutool.json.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspectConfig {

    @Pointcut("execution(* com.example.mpdemo.web.*.*(..))")
    private void aspect() {}

    @Autowired
    private ObjectMapper objectMapper;

    @Around("aspect()")
    private Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取类的名称
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        //获取类的方法名称
        String methodName = proceedingJoinPoint.getSignature().getName();
        //获取类的入参
        Object[] args = proceedingJoinPoint.getArgs();
        //转化成jsonArray对象
        JSONArray argsArray = new JSONArray(args);
        //ObjectMapper objectMapper = new ObjectMapper();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取请求类型
        String type = request.getMethod();
        //获取请求地址
        StringBuffer requestURL = request.getRequestURL();
        //获取请求IP
        String remoteAddr = request.getRemoteAddr();

        LocalDateTime startTime = getNowTime();
        //打印日志
        log.info("调用类：{}，方法名：{}，请求类型：{}，请求IP：{}，请求地址：{}", className, methodName, type, remoteAddr, requestURL);
        log.info("[{}]==>调用类：{}，方法名：{}，入参：{}", startTime, className, methodName, objectMapper.writeValueAsString(argsArray));

        Object proceed = proceedingJoinPoint.proceed();

        LocalDateTime endTime = getNowTime();
        log.info("[{}]==>调用类：{}，方法名：{}，出参：{}", endTime, className, methodName, objectMapper.writeValueAsString(proceed));

        //计算耗时
        Duration between = Duration.between(startTime, endTime);
        long offset = between.toMillis();
        log.info("调用类：{}，方法名：{}，执行总时长：{}毫秒", className, methodName, offset);
        return proceed;
    }

    private LocalDateTime getNowTime() {
        return LocalDateTime.now();
    }

}
