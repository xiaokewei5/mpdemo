package com.example.mpdemo.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T>{

    @Autowired
    protected M mapper;
    
}
