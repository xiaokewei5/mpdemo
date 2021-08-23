package com.example.mpdemo.basesql;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.example.mpdemo.common.Base;
import com.example.mpdemo.common.TableColumns;
import org.apache.ibatis.mapping.MappedStatement;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: fengyunxiaoxiao
 * @Date: 2021/8/20
 * @Description: 数据库字段列表
 */
public class SelectListColumns extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String tableName = tableInfo.getTableName();
        List<TableFieldInfo> fieldList = tableInfo.getFieldList();
        Map<String, String> tableFieldMap = fieldList.stream().collect(
                Collectors.toMap(TableFieldInfo::getColumn, TableFieldInfo::getProperty, (a, b) -> b));
        Map<Class, Map<String, String>> tablesMap = Base.getTablesMap();
        tablesMap.put(mapperClass, tableFieldMap);
        System.out.println("tableFieldMap = " + tableFieldMap);
        return null;
    }
}
