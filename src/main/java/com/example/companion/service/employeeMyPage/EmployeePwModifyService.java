package com.example.companion.service.employeeMyPage;

import com.example.companion.domain.AuthInfoDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
@Service
public class EmployeePwModifyService {

     @Autowired
     PasswordEncoder passwordEncoder; //비밀번호 암호화

    public String execute(HttpSession session, Model model, String empPw){
       //session을 통해 내정보 가져오기
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        //session 비밀번호와 입력 비밀번호가 같은지 확인
        if(passwordEncoder.matches(empPw,auth.getUserPw())){
            return "/worker/myNewPw"; //같으면 비밀번호 변경 페이지
        } else {
            //에러메세지 전송
            model.addAttribute("errPw","비밀번호가 틀렸습니다.");
            return "/worker/myPwCon"; //틀리면 현재 페이지
        }
    }
}
