package com.htfc786.wydb.service;

import com.htfc786.wydb.mapper.UserMapper;
import com.htfc786.wydb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryUser(int id){
        return userMapper.getUserById(id);
    }

    public List<User> queryUser(){
        return userMapper.getUser();
    }
}
