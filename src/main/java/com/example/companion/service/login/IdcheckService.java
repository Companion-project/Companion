package com.example.companion.service.login;

import com.example.companion.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdcheckService {
    @Autowired
    LoginMapper loginMapper;
    public String execute(String userId) {
        String resultId = loginMapper.selectIdCheck(userId);
        return resultId;
    }
}
