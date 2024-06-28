package com.example.companion.service;

import com.example.companion.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEmailCheckService {

    @Autowired
    UserMapper userMapper;
    public int execute(String email){
        int i = userMapper.userCheckUpdate(email);
        return i;
    }
}
