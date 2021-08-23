package com.example.mpdemo.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.serialize.JSONObjectSerializer;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpdemo.common.BaseServiceImpl;
import com.example.mpdemo.common.TableColumns;
import com.example.mpdemo.entity.AddUserDto;
import com.example.mpdemo.entity.User;
import com.example.mpdemo.entity.UserDto;
import com.example.mpdemo.enums.DeleteEnum;
import com.example.mpdemo.enums.OrgTypeEnum;
import com.example.mpdemo.mapper.UserMapper;
import com.example.mpdemo.service.IUserService;
import com.example.mpdemo.util.BeanUt;
import com.example.mpdemo.util.DateUt;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Predicate;

@Transactional(readOnly = true,rollbackFor= Exception.class)
@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService{

//    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<User> selectListByUser(UserDto dto) {
        User user = new User();
        BeanUt.copyProperties(dto, user, false);
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        LambdaQueryWrapper wrapper1 = new LambdaQueryWrapper(user);
        String sqlSelect = wrapper.getSqlSelect();
        String sqlSelect1 = wrapper1.getSqlSelect();
        System.out.print(sqlSelect);
        System.out.print(sqlSelect1);
        Page<User> page = new Page<>();
        BeanUt.copyProperties(dto, page, false);
        log.info("使用log4j记录日志{}", JSONUtil.parse(user).toStringPretty());
        return baseMapper.selectPage(page,wrapper1).getRecords();
    }

    @Override
    public User findAdmin(String accname) {
        System.out.println("accname = " + accname);
        User user = new User();
        user.setAccname(accname);

        LambdaQueryWrapper<User> lambda = new LambdaQueryWrapper<>();
        //lambda.select(User::getId,User::getAccname);
        String acc = user.getAccname();
        if (Objects.nonNull(acc)&&!Objects.equals("", acc)) {
            lambda.likeRight(User::getAccname, acc);
        }
        lambda.orderByAsc(User::getCreatetime);

        List<Map<String, Object>> maps = mapper.selectMaps(lambda);
        Set<Map.Entry<String, Object>> entries = maps.size()>2?maps.get(0).entrySet():Collections.EMPTY_SET;
        for (Map.Entry<String, Object> entry : entries) {
            if (Objects.equals("orgtype",entry.getKey())) {
                boolean x = entry.getValue() instanceof Integer;
                System.out.println("entry.getValue() instanceof Integer = " + String.valueOf(x));
            }
            if (Objects.equals("deleted",entry.getKey())) {
                boolean x = entry.getValue() instanceof Integer;
                System.out.println("entry.getValue() instanceof Boolean = " + String.valueOf(x));
            }
            System.out.println(entry.getKey() + "******" + entry.getValue());
        }
        JSONConfig config = JSONConfig.create().setDateFormat(DateUt.YYYY_MM_DD_HH_MM_SS);
        log.info("使用log4j记录日志maps\n{}", JSONUtil.parse(maps, config));

        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        //wrapper.likeRight("accname", acc);
        wrapper.last("limit 1");
        User admin = mapper.selectOne(wrapper);
        String message = "未查询到%s相关的管理员账号";
        Assert.notNull(admin, String.format(message,accname));
        Assert.isTrue(Objects.equals(OrgTypeEnum.ADMIN, admin.getOrgtype()),"管理员账号类型不是管理员");
        log.info("使用log4j记录日志JSONUtil.parse:{}", JSONUtil.parse(admin));
        Map<String, String> tableFields = User.getTableMap(UserMapper.class);
        log.info("tableFields = {}", tableFields);
        return admin;
    }

    @Override
    @Transactional
    public List<User> add(AddUserDto addUser){
//        User user = new User();
//        BeanUt.copyProperties(addUser, user, false);
        User u1 = new User();
        //User u2 = new User();
        BeanUtils.copyProperties(addUser, u1);
        BeanUt.copyProperties(addUser, u1,false);
        //BeanUt.copyProperties(addUser, u2,false);
        //u2.setAccname(u2.getAccname().concat("-2"));
        //u2.setUphone(u2.getUphone().replace("5","6"));
        //List<User> userList = Lists.newArrayList(u1,u2);
        List<User> userList = Lists.newArrayList(u1);
        this.saveBatch(userList);
//        int row = mapper.insert(user);
//        log.info("新增了{}条数据",row);
        log.info("新增了{}条数据",userList.size());
        return userList;
    }

    @Override
    public List<TableColumns> selectColumns() {
        return mapper.selectColumns();
    }

    @Override
    public List<User> list(UserDto dto) {
        User user = new User();
        BeanUt.copyProperties(dto, user, false);
        Page<User> page = new Page<>();
        BeanUt.copyProperties(dto, page, false);
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        return baseMapper.selectPage(page,wrapper).getRecords();
        /*String[] strings = new String[25001];
        for (int i = 0; i < 25001; i++) {
            strings[i] = StrUtil.fillBefore(Integer.toString(i+1),'0',15);
        }
        ArrayList<String> idList = Lists.newArrayList(strings);
        log.info("使用log4j记录日志{}",idList.toString());
        List<User> user = baseMapper.selectBatchIds(idList);
        return user;*/
    }
    
}
