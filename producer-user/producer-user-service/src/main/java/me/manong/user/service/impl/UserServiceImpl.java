package me.manong.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.manong.user.entity.User;
import me.manong.user.mapper.UserMapper;
import me.manong.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@DubboService
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    public List<User> getAllUser(){
        return this.userMapper.getAllUser();
    }

    public User getUserByUsername(String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda().eq(User::getUsername,username);
        return this.userMapper.selectOne(queryWrapper);
    }

}
