//package com.example.mpdemo.config;
//
//import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.aop.support.JdkRegexpMethodPointcut;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.context.annotation.Scope;
//
//可使用spring.datasource.druid.aop-patterns=com.example.mpdemo.mapper.*,com.example.mpdemo.service.*代替
//@Configuration
//@EnableAspectJAutoProxy(proxyTargetClass = true)
//public class SpringDaoMethodAspect {
//    @Bean
//    public DruidStatInterceptor druidStatInterceptor() {
//        DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();
//        return druidStatInterceptor;
//    }
//
//    @Bean
//    @Scope("prototype")
//    public JdkRegexpMethodPointcut druidStatPointcut() {
//        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
//        pointcut.setPatterns("com.example.mpdemo.mapper.*","com.example.mpdemo.service.*");
//        return pointcut;
//    }
//
//    @Bean
//    public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
//        DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
//        defaultPointAdvisor.setPointcut(druidStatPointcut);
//        defaultPointAdvisor.setAdvice(druidStatInterceptor);
//        return defaultPointAdvisor;
//    }
//}
