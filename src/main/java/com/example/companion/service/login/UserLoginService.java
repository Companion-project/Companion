package com.example.companion.service.login;

import com.example.companion.command.LoginCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserLoginService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder; //회원가입 시 비밀번호 암호화 -> 암호비교를 위해 필요.

    public void execute(LoginCommand loginCommand, HttpSession session, BindingResult result) {
        String userId = loginCommand.getUserId();
        String userPw = loginCommand.getUserPw();

        //회원 로그인 정보를 가져오기 위한 DTO필요.
        AuthInfoDTO dto = userMapper.loginSelect(userId);
        if (userId != "" && userId != null) {
            if(dto != null) { //회원아이디가 있으면 dto가 null이 아니다.
                if(dto.getUserEmailCheck() == null){
                    System.out.println("아이디는 있지만 이메일 인증이 되지 않았을 때");
                    result.rejectValue("userId", "loginCommand.userId"
                            , "이메일 인증이 안되었습니다.");
                } else {
                    //아이디 존재 + 비밀번호 일치하는 경우
                    //본문(로그인창에서 입력한 값) 암호문(DB에서 가져온 암호화된 문자열)
                      if (passwordEncoder.matches(userPw, dto.getUserPw())) {
                          System.out.println("비밀번호가 일치");
                          //아이디, 비밀번호 일치 -> session 로그인 정보 저장
                          session.setAttribute("auth", dto);
                      } else {
                          System.out.println("비밀번호가 일치하지 않았을 때");
                          result.rejectValue("userPw", "loginCommand.userPw"
                                  , "잘못된 비밀번호 입니다.");
                          }
                      }
            } else {
              System.out.println("아이디가 존재 하지 않았을 때");
              result.rejectValue("userId", "loginCommand.userId", "아이디가 존재하지 않습니다.");
            }
        }
    }
}
