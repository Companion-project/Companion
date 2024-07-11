package com.example.companion.controller;

import com.example.companion.service.help.FindIdService;
import com.example.companion.service.help.FindPwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("help")
public class HelpController {

    @Autowired
    FindIdService findIdService;

    @Autowired
    FindPwService findPwService;

    @RequestMapping(value = "findId", method = RequestMethod.GET)
    public String findId(){
        return "help/findId";
    }

    @RequestMapping(value = "findId", method = RequestMethod.POST)
    public String findId(
            @RequestParam("userPhone") String userPhone,
            @RequestParam("userEmail") String userEmail,
            Model model){
        findIdService.execute(userPhone, userEmail, model);
        return "help/findIdOk";
    }

    @GetMapping("findPassword")
    public String findPassword(){
        return "help/findPw";
    }

    @PostMapping("findPassword")
    public String findPassword(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "userPhone") String userPhone,
            Model model){
        findPwService.execute(userId, userPhone, model);
        model.addAttribute("userId", userId); // userId를 Model에 추가
        return "help/findPwOk";
    }

}
