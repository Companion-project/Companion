package com.example.companion.controller;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.service.employeeMyPage.EmployeeInfoService;
import com.example.companion.service.employeeMyPage.EmployeeInfoUpdateService;
import com.example.companion.service.employeeMyPage.EmployeePassConfirmService;
import com.example.companion.service.employeeMyPage.EmployeePwModifyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employee")
public class EmployeeMyPageController {
    @Autowired
    EmployeeInfoService employeeInfoService;
    @Autowired
    EmployeeInfoUpdateService employeeInfoUpdateService;
    @Autowired
    EmployeePassConfirmService employeePassConfirmService;
    @Autowired
    EmployeePwModifyService employeePwModifyService;

    @RequestMapping("empMyPage")
    public String empMyPage(HttpSession session, Model model){
        employeeInfoService.execute(session, model);
        return "worker/empPage";
    }

    @RequestMapping("empInfo")
    public String empInfo(HttpSession session, Model model){
        employeeInfoService.execute(session, model);
        return "worker/myInfo";
    }

    @GetMapping("employeeUpdate")
    public String employeeUpdate(HttpSession session, Model model){
        employeeInfoService.execute(session, model);
        return "worker/myModify";
    }

    @PostMapping("employeeUpdate")
    public String employeeUpdate(@Validated EmployeeCommand employeeCommand
                               , BindingResult result, HttpSession session){
        employeeInfoUpdateService.execute(employeeCommand,session,result);
        if(result.hasErrors()){
            //수정할때 에러있으면 에러메세지
            return"/worker/myModify";
        }else{
            //수정 완료시 마이페이지
            return "redirect:empMyPage";
        }
    }

    @GetMapping("employeePwModify")
    public String employeePwModify(){

        return "/worker/myPwCon";
    }

    @PostMapping("employeePwModify")
    public String employeePwModify(@RequestParam("empPw")String empPw, Model model, HttpSession session){
        return employeePwModifyService.execute(session, model, empPw);
    }

    @PostMapping("empPwUpdate")
    public @ResponseBody boolean empPwUpdate( //boolean 타입으로 보내기 위해서 ResponsBody사용
             @RequestParam("oldPw") String oldPw, @RequestParam("newPw") String newPw, HttpSession session){
        return employeePassConfirmService.execute(newPw, oldPw, session);
    }

}
