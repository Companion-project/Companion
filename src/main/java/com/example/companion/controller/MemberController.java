package com.example.companion.controller;

import com.example.companion.command.MemberCommand;
import com.example.companion.service.member.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    MemberInsertService memberInsertService;

    @Autowired
    MemberAutoNumService memberAutoNumService;

    @Autowired
    MemberListService memberListService;

    @Autowired
    MembersDeleteService membersDeleteService;

    @Autowired
    MemberDetailService memberDetailService;

    @Autowired
    MemberUpdateService memberUpdateService;

    @Autowired
    MemberDeleteService memberDeleteService;

    @GetMapping("memberdelete/{memberNum}")
    public String memberDelete(
            @PathVariable(value = "memberNum") String memberNum){
        memberDeleteService.execute(memberNum);
        return "redirect:../memberList";
    }

    @PostMapping("memberModify")
    //BindingResult는 스프링이 제공하는 객체 중 하나,
    // -> 객체에서 오류가 발생 시 코드를 담아주는 역할을 하는 클래스
    public String memberModify(@Validated MemberCommand memberCommand, BindingResult result){
        //html에서 넘어온 값은MemberCommand가 받음.
        //이때 MemberCommand에 넘어오지 않은 경우 오류 검사
        if(result.hasErrors()){
            //오류가 있다면 다시 memberModify페이지로
            //memberModify페이지에 MemberCommand가 가진 값을
            //MemberCommand는 값+오류메세지 전달
            return "member/memberModify";
        }
        memberUpdateService.execute(memberCommand);
        return "redirect:memberDetail?memberNum="+memberCommand.getMemberNum();
    }

//    @GetMapping("memberUpdate")
    @RequestMapping("memberUpdate")
    public String memberUpdate(
            @RequestParam(value = "memberNum") String memberNum, Model model){
        memberDetailService.execute(memberNum, model);
        return "member/memberModify";
    }

    @GetMapping("memberDetail")
    public String memberDetail(@RequestParam(value = "memberNum") String memberNum,
                               Model model){
        memberDetailService.execute(memberNum, model);
        return "member/memberInfo";
    }

    @PostMapping("membersDelete")
    public String dels(@RequestParam(value = "memDels") String memDels[]){
        membersDeleteService.execute(memDels);
        return "redirect:memberList";
    }

    @RequestMapping(value = "memberList")
    public String list(
            //처음 페이지 열릴 때는 searchWord가 없으므로 페이지 오류 발생
            //오류 방지를 위해 필수가 아니라고 전달
            @RequestParam(value = "searchWord", required = false) String searchWord,
            //처음 페이지가 열릴 때는 페이지가 없으므로 기본값 1 설정
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            Model model){
        //회원들의 정보를 담아 memberList.html에 보낼 수 있게 Model이 필요
        memberListService.execute(model, searchWord, page);

        return "member/memberList";
    }

    @RequestMapping(value = "memberRegist", method = RequestMethod.GET)
    public String form(Model model){
        //회원번호 불러오기
        memberAutoNumService.execute(model);
        return "member/memberForm";
    }

    @RequestMapping(value = "memberRegist", method = RequestMethod.POST)
    public String form(@Validated MemberCommand memberCommand, BindingResult result){
        //오류 발생 시 오류 메세지를 html에 전달
        if(result.hasErrors()){
            return "member/memberForm";
        }
        if(!memberCommand.isMemberPwEqualsMemberPwCon()){
            //비밀번호와 비밀번호확인이 다른 경우에도 메세지 보내기
            //result.rejectValue(필드명, 에러코드, 메세지)
            result.rejectValue("memberPwCon", "memberCommand.memberPwCon",
                                "잘못된 비밀번호 입니다.");
            return "member/memberForm";
        }else{
            memberInsertService.execute(memberCommand);
            return "redirect:memberList";
        }
    }

}
