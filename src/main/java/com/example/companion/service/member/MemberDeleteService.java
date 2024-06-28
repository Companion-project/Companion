package com.example.companion.service.member;

import com.example.companion.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberDeleteService {
    @Autowired
    MemberMapper memberMapper;
    public void execute(String memberNum){
        memberMapper.memberDelete(memberNum);

    }
}
