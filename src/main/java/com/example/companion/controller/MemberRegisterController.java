package com.example.companion.controller;

import com.example.companion.command.MemberCommand;
import com.example.companion.service.memberRegist.UserWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("register")
public class MemberRegisterController {

    @Autowired
    UserWriteService userWriteService;
/*모든 주소에서 memberCommand 사용할 수 있도록 정의*/
    @ModelAttribute
    public MemberCommand memberCommand(){
        return new MemberCommand();
    }

    @RequestMapping(value = "userAgree", method = RequestMethod.GET)
    public String agree(){
        return "memberRegist/userAgree";
    }

    @RequestMapping(value = "userWrite", method = RequestMethod.POST)
    public String userWrite(
    /*userForm.html 페이지 열 때 memberCommand가 필요함.
    userForm.html 열리는 모든 곳에서 memberCommand가 전송되어야 함.
    여기는 없음
    */
            @RequestParam(value = "agree", defaultValue = "false")boolean agree){
        //동의하지 않아도 넘어가는 부분 수정(차단)
        if(agree == false){
            return "memberRegist/userAgree";
        }
        return "memberRegist/userForm";
    }

    @PostMapping("userRegist")
    public String userRegist(@Validated MemberCommand memberCommand, BindingResult result,
                             Model model){
        //오류 발생 시 오류메세지 출력
        if(result.hasErrors()){
            return "memberRegist/userForm";

        }
        userWriteService.execute(memberCommand, model);
        return "memberRegist/welcome";
    }
}
