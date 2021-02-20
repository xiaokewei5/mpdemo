package com.example.mpdemo.common;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.OrderItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePage {
    
    /** 当前页 */
    private long current = 1;
    
    /** 每页显示条数，默认 10 */
    private long size = 10;
    /**
     * 排序字段信息
     */
    private List<OrderItem> orders = new ArrayList<>();
}
