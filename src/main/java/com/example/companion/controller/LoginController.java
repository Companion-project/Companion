package com.example.companion.controller;

import com.example.companion.command.LoginCommand;
import com.example.companion.service.login.IdcheckService;
import com.example.companion.service.login.UserLoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    IdcheckService idcheckService;

    @Autowired
    UserLoginService userLoginService;

    @PostMapping("userIdCheck")
    //html문서가 아닌 텍스트를 전달하기 위해서는 @ResponseBody필요
    public @ResponseBody String userIdCheck(
           @RequestParam(value = "userId") String userId ){
        String resultId = idcheckService.execute(userId);
        if(resultId == null){
            return "사용가능한 아이디입니다.";
        }else {
            return "사용중인 아이디입니다.";
        }

    }

    @PostMapping("login")
    //아이디와 비밀번호를 command로 받아옴
    public String login(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session){
        userLoginService.execute(loginCommand, session, result);
        //오류가 있으면 index.html페이지 열리도록 구현.
        if(result.hasErrors()){
            return "index";

        }
        return "redirect:/";

    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate(); //로그아웃시 모든 세션 삭제
        return "redirect:/"; //첫페이지로 이동
    }
}
