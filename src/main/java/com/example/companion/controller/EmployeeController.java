package com.example.companion.controller;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.service.employees.EmployeeAutoNumService;
import com.example.companion.service.employees.EmployeeInsertService;
import com.example.companion.service.employees.EmployeeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeAutoNumService employeeAutoNumService;

    @Autowired
    EmployeeInsertService employeeInsertService;

    @Autowired
    EmployeeListService employeeListService;

    @RequestMapping(value = "employeeList", method = RequestMethod.GET)
    public String empList(Model model){
        employeeListService.execute(model);
        return "employee/employeeList";
    }

    @GetMapping("empRegist")
    public String form(Model model){
        employeeAutoNumService.execute(model);
        return "employee/employeeForm";
    }

    @RequestMapping(value = "empRegist", method = RequestMethod.POST)
    //html에 있는 값을 command로 받아옴
    //html에서 넘어온 값에 대해 유효성 검사 진행
    public String form(@Validated EmployeeCommand employeeCommand, BindingResult result, Model model){
        if(result.hasErrors()){
            return "employee/employeeForm";
        }
        if(!employeeCommand.isEmpPwEqualsEmpPwCon()){
            System.out.println("비밀번호 확인이 다릅니다.");
            return "employee/employeeForm";

        }
        employeeInsertService.execute(employeeCommand);
        return "redirect:employeeList";
    }

}
