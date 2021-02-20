package com.example.mpdemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.example.mpdemo.handler.AutoFillHandler;
import com.example.mpdemo.handler.MyBaseSqlInjector;

import static com.baomidou.mybatisplus.annotation.DbType.MYSQL;

@Configuration
@MapperScan("com.example.**.mapper")
public class MybatisPlusConfig {

    //总结: 对sql进行单次改造的优先放入,不对sql进行改造的最后放入
    
    /**
     * 自动填充
     */
    @Bean
    public MetaObjectHandler AutoFill() {
        return new AutoFillHandler();
    }
    
    /**
     * 使用插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(MYSQL));
        //乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //防止全表更新
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor; 
    }
    
    /**
     * 自定义 SqlInjector
     * 里面包含自定义的全局方法
     */
    @Bean
    public MyBaseSqlInjector myBaseSqlInjector() {
        return new MyBaseSqlInjector();
    }
}
