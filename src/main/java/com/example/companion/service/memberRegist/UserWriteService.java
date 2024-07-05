package com.example.companion.service.memberRegist;

import com.example.companion.command.MemberCommand;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.UserMapper;
import com.example.companion.service.EmailSendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;

@Service
public class UserWriteService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    @Autowired
    EmailSendService emailSendService;

    public void execute(MemberCommand memberCommand, Model model){
        String memberId = memberCommand.getMemberId();
        String memberPw = memberCommand.getMemberPw();
        String memberName = memberCommand.getMemberName();
        String memberPhone1 = memberCommand.getMemberPhone1();
        String memberPhone2 = memberCommand.getMemberPhone2();
        String memberAddr = memberCommand.getMemberAddr();
        String memberAddrDetail = memberCommand.getMemberAddr2();
        String memberPost = memberCommand.getMemberPost();
        String memberEmail = memberCommand.getMemberEmail();
        Date memberBirth = memberCommand.getMemberBirth();
        String memberGender = memberCommand.getMemberGender();

        MemberDTO dto = new MemberDTO();
        dto.setMemberAddr(memberAddr);
        dto.setMemberAddrDetail(memberAddrDetail);
        dto.setMemberEmail(memberEmail);
        dto.setGender(memberGender);
        dto.setMemberId(memberId);
        dto.setMemberName(memberName);
        dto.setMemberPhone1(memberPhone1);
        dto.setMemberPhone2(memberPhone2);
        dto.setMemberPost(memberPost);
        dto.setMemberPw(passwordEncoder.encode(memberPw)); //비밀번호 암호화
        dto.setMemberBirth(memberBirth);
        int i = userMapper.userInsert(dto);
        model.addAttribute("userName", dto.getMemberName());
        model.addAttribute("userEmail", dto.getMemberEmail());

        //구글 smtp 사용해서 가입 시 이메일 인증처리
        if(i >= 1) {
            //메일링
            String html = "<html><body>"
                    + dto.getMemberName() + " 님 회원가입을 축하합니다. <br/>"
                    + "가입을 완료하시려면"
                    + "<a href='http://localhost:8089/userConfirm?chk=" + dto.getMemberEmail()
                    + "'>여기</a>"
                    + "를 눌러주세요";
            String subject = "환영 인사입니다.";
            String fromEmail = "newe9150@gmail.com"; //보내는사람
            String toEmail = dto.getMemberEmail(); //받는 사람

        emailSendService.mailsend(html, subject, fromEmail, toEmail);

        }
    }
}
