package com.example.mpdemo.handler;

import java.util.List;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.example.mpdemo.basesql.SelectColumns;
import com.example.mpdemo.basesql.SelectListColumns;

/**
 * 自定义sql注入
 */
public class MyBaseSqlInjector extends DefaultSqlInjector{

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new SelectColumns());
        methodList.add(new SelectListColumns());
        return methodList;
    }
    
}
