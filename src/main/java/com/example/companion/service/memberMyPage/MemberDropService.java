package com.example.companion.service.memberMyPage;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MemberDropService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberMyMapper memberMyMapper;

    public int execute(String memberPw, HttpSession session, Model model){
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        if(passwordEncoder.matches(memberPw, auth.getUserPw())){
            int i = memberMyMapper.memberDrop(auth.getUserId());
            return 1;
        }else{
            model.addAttribute("errPw", "잘못된 비밀번호 입니다.");
            return 0;
        }
    }
}
