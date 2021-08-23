package com.example.mpdemo.common;


import com.baomidou.mybatisplus.annotation.IEnum;

import java.io.Serializable;

/**
 * @Author: fengyunxiaoxiao
 * @Date: 2021/8/17
 * @Description: 枚举接口
 */
public interface IBaseEnum<T extends Serializable> extends IEnum<T> {

    String ID = "id";

    String NAME = "name";

    String getName();

}
