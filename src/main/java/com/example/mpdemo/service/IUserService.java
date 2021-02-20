package com.example.mpdemo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mpdemo.common.TableColumns;
import com.example.mpdemo.entity.AddUserDto;
import com.example.mpdemo.entity.User;
import com.example.mpdemo.entity.UserDto;

public interface IUserService extends IService<User>{

    List<User> selectListByUser(UserDto user);

    User findAdmin();

    List<TableColumns> selectColumns();

    List<User> list(UserDto dto);

    List<User> add(AddUserDto user);
}
