package com.example.mpdemo.mapper;

import com.example.mpdemo.common.MyBaseMapper;
import com.example.mpdemo.entity.User;

public interface UserMapper extends MyBaseMapper<User>{

    User findAdmin();
}
