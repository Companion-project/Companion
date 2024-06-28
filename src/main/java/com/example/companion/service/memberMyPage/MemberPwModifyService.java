package com.example.companion.service.memberMyPage;

import com.example.companion.domain.AuthInfoDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MemberPwModifyService {

    @Autowired
    PasswordEncoder passwordEncoder; //암호화된 비밀번호 비교

    public String execute(HttpSession session, Model model, String memberPw){
        //session을 통해 내 정보 가져오기
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        //session에 저장한 내 비밀번호와 입력한 비밀번호가 같은지 비교
        if(passwordEncoder.matches(memberPw,auth.getUserPw() )) {
            return "membership/myNewPw"; //일치시 비밀번호 변경 페이지로 이동
        }else {
            //오류메세지
            model.addAttribute("errPw", "잘못된 비밀번호 입니다.");
            return "membership/myPwCon"; //불일치면 현재페이지
        }
    }
}
