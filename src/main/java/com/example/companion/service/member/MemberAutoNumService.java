package com.example.companion.service.member;

import com.example.companion.command.MemberCommand;
import com.example.companion.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MemberAutoNumService {
    @Autowired
    MemberMapper memberMapper;
    public void execute(Model model){
        String memberNum = memberMapper.memberAutoNum();
        MemberCommand dto = new MemberCommand();
        dto.setMemberNum(memberNum);
        model.addAttribute("memberCommand", dto);

    }
}
