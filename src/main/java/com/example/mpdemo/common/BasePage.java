package com.example.mpdemo.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.OrderItem;

import com.example.mpdemo.util.DateUt;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePage extends Base {
    
    /** 当前页 */
    private long current = 1;
    
    /** 每页显示条数，默认 10 */
    private long size = 10;
    /**
     * 排序字段信息
     */
    private List<OrderItem> orders = new ArrayList<>();

}
