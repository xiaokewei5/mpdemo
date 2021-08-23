package com.example.mpdemo.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.mpdemo.util.DateUt;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: fengyunxiaoxiao
 * @Date: 2021/8/18
 * @Description:
 */
public class Base {

    //用来转成json的属性
    @TableField(exist = false)
    private static JSONObject json;

    //@TableField(exist = false)
    private static Map<Class, Map<String, String>> tablesMap = new HashMap<>();

    public static Map<String, String> getTableMap(Class mapperClass) {
        Map<String, String> tableMap = tablesMap.get(mapperClass);
        return Objects.isNull(tableMap) ? Collections.EMPTY_MAP : tableMap;
    }

    public static Map<Class, Map<String, String>> getTablesMap(){
        return tablesMap;
    }
}
