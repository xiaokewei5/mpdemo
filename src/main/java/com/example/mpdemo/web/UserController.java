package com.example.mpdemo.web;

import com.example.mpdemo.common.TableColumns;
import com.example.mpdemo.entity.AddUserDto;
import com.example.mpdemo.entity.User;
import com.example.mpdemo.entity.UserDto;
import com.example.mpdemo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Api(tags = "系统：用户接口")
@Slf4j
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
    @ApiOperation("查询管理员信息")//@RequestParam(value = "accname",required = false,defaultValue = "")
    public ResponseEntity<User> admin(@RequestBody Map<String,String> map){
        String accname = map.get("accname");
        //Assert.notNull(accname,"accname不能为空");
        System.out.println("map.get(uphone) = " + map.get("uphone"));
        User user = userService.findAdmin(accname);
        ResponseEntity<User> ok = ResponseEntity.ok(user);
        log.info("ok = {}", ok.getBody());
        return ok;
    }

    @PostMapping("/add")
    @ApiOperation("新增用户数据")
    public ResponseEntity<Object> add(@Validated @RequestBody AddUserDto user){
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
    public ResponseEntity<List<TableColumns>> get(@RequestBody UserDto dto, @RequestParam("a2") String a2){
        List<TableColumns> tableColumns = userService.selectColumns();
        ResponseEntity<List<TableColumns>> ok = ResponseEntity.ok(tableColumns);
        log.info("ok = {}", ok.getBody());
        return ok;
    }
    
}
