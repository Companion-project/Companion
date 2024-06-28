package com.example.companion.service.memberMyPage;

import com.example.companion.command.MemberCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MemberInfoService {

    @Autowired
    MemberMyMapper memberMyMapper;

    public void execute(HttpSession session, Model model){
        //로그인 시 만든 session
        AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("auth");
        String memberId = authInfo.getUserId();

        //아이디를 이용해 회원정보를 가지고 옴
        MemberDTO dto = memberMyMapper.memberInfo(memberId);

        //dto -> html 을 위해 model에 저장
        //command로 사용할 경우도 생각해 command에 dto를 옮김
        //dto, command의 멤버필드가 같다면 dto를 model에 저장해도 됨
        //여기서는 멤버필드가 모두 일치는 아니므로 command에 저장
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
        model.addAttribute("memberCommand", memberCommand);



    }
}
