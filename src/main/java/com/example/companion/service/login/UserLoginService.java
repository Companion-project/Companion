package com.example.companion.service.login;

import com.example.companion.command.LoginCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.mapper.UserMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

    public void execute(LoginCommand loginCommand, HttpSession session, BindingResult result, HttpServletResponse response) {
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
                          // 로그인이 정상적으로 되면 쿠키가 만들어지도록 합니다.
                          // 먼저 아이디 저장을 하기위 해 idStore를 읽어 오겠습니다.
                          //아이디 저장에 체크가 된 경우
                          if(loginCommand.getIdStore() != null && loginCommand.getIdStore()) {
                              System.out.println("쿠키가 만들어지나요..");
                              // 쿠키를 만들어서 쿠키에 아이디를 저장하겠습니다.
                              // 쿠키 생성
                              Cookie cookie = new Cookie("idStore", loginCommand.getUserId());
                              // 저장 경로
                              cookie.setPath("/");
                              // 수명 주기
                              cookie.setMaxAge(60*60*24*30);
                              // 사용자에게 전송
                              response.addCookie(cookie); // response를 통해 사용자에게 전달합니다.
                          }else { // 아이디 저장에 체크를 해지한 경우
                              System.out.println("쿠키가 안 만들어지나요..");
                              Cookie cookie = new Cookie("idStore", loginCommand.getUserId());
                              cookie.setPath("/");
                              cookie.setMaxAge(0); // 수명주기를 0으로 주면 쿠키는 삭제됩니다.
                              response.addCookie(cookie);
                          }
                          // 자동로그인
                          if(loginCommand.getAutoLogin() != null && loginCommand.getAutoLogin()) {
                              Cookie cookie = new Cookie("autoLogin", loginCommand.getUserId());
                              cookie.setPath("/");
                              cookie.setMaxAge(60*60*24*30);
                              response.addCookie(cookie);
                          }

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
