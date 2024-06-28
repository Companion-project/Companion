package com.example.companion.controller;

import com.example.companion.command.FileCommand;
import com.example.companion.service.EmailCheckService;
import com.example.companion.service.FileDelService;
import com.example.companion.service.UserEmailCheckService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CheckRestController {
    @Autowired
    EmailCheckService emailCheckService;
    @Autowired
    UserEmailCheckService userEmailCheckService;
    @Autowired
    FileDelService fileDelService;

    @RequestMapping(value="checkRest/userEmailCheck", method = RequestMethod.POST)
    public String userEmailCheck(@RequestParam(value="userEmail") String userEmail){
        String resultEmail = emailCheckService.execute(userEmail);
         if(resultEmail == null){
            return "사용가능한 Email입니다.";
        }else {
            return "사용중인 Email입니다.";
        }
    }
    //@RestAPI사용
    @RequestMapping("userConfirm")
    public String userConfirm(@RequestParam(value="chk") String chk){
        int i = userEmailCheckService.execute(chk);
        if(i == 0)
            return "이미 인증되었습니다.";
        else    return "인증되었습니다.";
    }
    //RestAPI를 이용하여 파일 삭제할 정보를 저장, ajax에서 넘어온 삭제할 파일의 정보를 FileComman에 저장
    @PostMapping("goods/fileDel")
    public String fileDel(FileCommand fileCommand, HttpSession session){
        return fileDelService.execute(fileCommand, session);
    }
}
