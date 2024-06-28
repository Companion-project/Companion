package com.example.companion.service.member;

import com.example.companion.command.MemberCommand;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MemberDetailService {
    @Autowired
    MemberMapper memberMapper;

    //DTO는 DB와 주로 상호작용하는 객체(Mapper, Repository에서 사용)

    //Command객체는 컨트롤러, 서비스 계층 사이에서 데이터 전달 목적으로 사용
    //사용자의 입력을 바탕으로 비즈니스 로직 처리

    //즉 계층 분리 -> 책임 명확히, 코드 유지보수
    //DTO : DB의 모든 필드 포함
    //Command : 필요한 데이터만(예: 데이터베이스에 저장된
    // 비밀번호 해시값이나 내부 시스템에서만 사용하는 필드는
    // Command 객체로 변환하는 과정에서 제외)

    public void execute(String memberNum, Model model){
        // 로그 추가
        System.out.println("Fetching member with memberNum: " + memberNum);

       //DB에서 MemberDTO객체 가져옴
        MemberDTO dto = memberMapper.memberSelectOne(memberNum);

        //MemberCommand객체 생성하고 DTO값을 설정
        MemberCommand memberCommand = new MemberCommand();
        memberCommand.setMemberAddr(dto.getMemberAddr());
        memberCommand.setMemberAddr2(dto.getMemberAddrDetail());
        memberCommand.setMemberBirth(dto.getMemberBirth());
        memberCommand.setMemberEmail(dto.getMemberEmail());
        memberCommand.setMemberGender(dto.getGender());
        memberCommand.setMemberId(dto.getMemberId());
        memberCommand.setMemberName(dto.getMemberName());
        memberCommand.setMemberNum(dto.getMemberNum());
        memberCommand.setMemberPhone1(dto.getMemberPhone1());
        memberCommand.setMemberPhone2(dto.getMemberPhone2());
        memberCommand.setMemberPost(dto.getMemberPost());
        memberCommand.setMemberRegiDate(dto.getMemberRegistDate());
        memberCommand.setPoint(dto.getPoint());

        //Model에 memberCommand 객체를 추가
        model.addAttribute("memberCommand", memberCommand);

    }
}
