package com.example.mpdemo.web;

import java.util.List;

import com.example.mpdemo.entity.AddUserDto;
import com.example.mpdemo.util.BeanUt;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mpdemo.common.TableColumns;
import com.example.mpdemo.entity.User;
import com.example.mpdemo.entity.UserDto;
import com.example.mpdemo.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/user")
@Api(tags = "系统：用户接口")
public class UserController {

    @Autowired
    private IUserService userService;
    
    @PostMapping("/test")
    @ApiOperation("分页查询测试1")
    public ResponseEntity<List<User>> test(@Validated @RequestBody UserDto user){
        List<User> userList = userService.selectListByUser(user);
        return ResponseEntity.ok(userList);
    }
    
    @PostMapping("/admin")
    @ApiOperation("查询管理员信息")
    public ResponseEntity<User> admin(){
        User user = userService.findAdmin();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    @ApiOperation("新增用户数据")
    public ResponseEntity<Object> add(@Validated AddUserDto user){
        List<User> addUser = userService.add(user);
        return ResponseEntity.ok(addUser);
    }
    
    @PostMapping("/list")
    @ApiOperation("分页查询测试2")
    public ResponseEntity<List<User>> list(@RequestBody UserDto dto){
        List<User> list = userService.list(dto);
        return ResponseEntity.ok(list);
    }
    
    @PostMapping("/sql")
    @ApiOperation("查询数据库字段")
    public ResponseEntity<List<TableColumns>> get(){
        List<TableColumns> tableColumns = userService.selectColumns();
        return ResponseEntity.ok(tableColumns);
    }
    
}
