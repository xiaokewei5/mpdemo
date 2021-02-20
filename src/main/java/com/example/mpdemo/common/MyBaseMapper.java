package com.example.mpdemo.common;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 自定义公共sql Mapper类
 */
public interface MyBaseMapper<T> extends BaseMapper<T>{

    /**
     * 查询表数据库字段
     */
    List<TableColumns> selectColumns();
    
}
