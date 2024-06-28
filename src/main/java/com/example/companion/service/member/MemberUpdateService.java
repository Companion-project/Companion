package com.example.companion.service.member;

import com.example.companion.command.MemberCommand;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberUpdateService {

    @Autowired
    MemberMapper memberMapper;

    public void execute(MemberCommand memberCommand){
        MemberDTO dto = new MemberDTO();
        dto.setGender(memberCommand.getMemberGender());
        dto.setMemberAddr(memberCommand.getMemberAddr());
        dto.setMemberAddrDetail(memberCommand.getMemberAddr2());
        dto.setMemberBirth(memberCommand.getMemberBirth());
        dto.setMemberEmail(memberCommand.getMemberEmail());
        dto.setMemberName(memberCommand.getMemberName());
        dto.setMemberNum(memberCommand.getMemberNum());
        dto.setMemberPhone1(memberCommand.getMemberPhone1());
        dto.setMemberPhone2(memberCommand.getMemberPhone2());
        dto.setMemberPost(memberCommand.getMemberPost());
        dto.setPoint(memberCommand.getPoint());
        memberMapper.memberUpdate(dto);
    }
}
