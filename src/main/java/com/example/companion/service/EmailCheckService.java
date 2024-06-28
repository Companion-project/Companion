package com.example.companion.service;

import com.example.companion.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailCheckService {
    @Autowired
    LoginMapper loginMapper;
    public String execute(String userEmail) {
        String resultEmail = loginMapper.selectEmailCheck(userEmail);
        return resultEmail;
    }
}
