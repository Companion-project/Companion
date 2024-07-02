package com.example.companion.controller;

import com.example.companion.command.MemberCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.service.memberMyPage.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("mypage")
public class MemberMyPageController {

    @Autowired
    MemberInfoService memberInfoService;

    @Autowired
    MemberPwModifyService memberPwModifyService;

    @Autowired
    MyPassConfirmService myPassConfirmService;

    @Autowired
    MemberDropService memberDropService;

    @GetMapping("myPage")
    public String myPage(HttpSession session, Model model){
        memberInfoService.execute(session, model);
        return "memberShip/myPage";
    }

    @GetMapping("myDetail")
    //로그인 저장한 세션값 -> 내정보를 DB에서 가져오기
    //DB에서 가져온 정보값을 model을 이용해 myInfo.html로 전송
    public String myDetail(HttpSession session, Model model){
        memberInfoService.execute(session, model);
        return "memberShip/myInfo";
    }

    @RequestMapping(value = "userPwModify", method = RequestMethod.GET)
    public String userPwModify(){
        return "membership/myPwCon";
    }

    @RequestMapping(value = "memberPwModify", method = RequestMethod.POST)
    public String userPwModify(@RequestParam("memberPw") String memberPw,
            Model model, HttpSession session){
        return memberPwModifyService.execute(session, model, memberPw);
    }

    @PostMapping("myPwUpdate")
    @ResponseBody
    public String myPwUpdate(@RequestParam("oldPw") String oldPw,
                             @RequestParam("newPw") String newPw,
                             HttpSession session) {
        boolean result = myPassConfirmService.execute(newPw, oldPw, session);
        if (result) {
            session.invalidate(); // 세션 무효화
            return "success";
        } else {
            return "failure";
        }
    }

    @GetMapping("memberDrop")
    public String memberDrop(){
        return "memberShip/memberDrop";
    }

    @PostMapping("memberDropOk")
    public String memberDrop(
            @RequestParam("memberPw") String memberPw, Model model,
            HttpSession session) {
        int i = memberDropService.execute(memberPw, session, model);
        if(i == 1)
            return "redirect:/login/logout"; //탈퇴 후 로그아웃 처리
        else return "memberShip/memberDrop"; //현재 비밀번호 입력 -> 불일치면 현재 페이지로
    }

    @RequestMapping(value = "memberUpdate", method = RequestMethod.GET)
    public String memberUpdate(HttpSession session, Model model){
        memberInfoService.execute(session, model); //myModify에 내 정보를 가지고 오기 위해 myDetail에서 사용한 service사용.
        return "memberShip/myModify";
    }

    @Autowired
    MemberInfoUpdateService memberInfoUpdateService;
    @RequestMapping(value = "memberUpdate", method = RequestMethod.POST)
    public String memberUpdate(@Validated MemberCommand memberCommand, BindingResult result,
                               HttpSession session){
        memberInfoUpdateService.execute(memberCommand, session, result);
        if(result.hasErrors()){
            return "memberShip/myModify";
        }
        return "redirect:myDetail";
    }
}
