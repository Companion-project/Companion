package com.example.companion.controller;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.service.employees.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeAutoNumService employeeAutoNumService;
    @Autowired
    EmployeeInsertService employeeInsertService;
    @Autowired
    EmployeeListService employeeListService;
	@Autowired
	EmployeesDeleteService employeesDeleteService;
	@Autowired
	EmployeeDetailService employeeDetailService;
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	@Autowired
	EmployeeDeleteService employeeDeleteService;

    @RequestMapping(value="employeeList", method= RequestMethod.GET)
	public String empList(
			@RequestParam(value="page", required = false, defaultValue = "1" ) int page,
			@RequestParam(value="searchWord" , required = false) String searchWord,
			Model model) {
		//직원 목록보기, 페이징, 검색
        employeeListService.execute(searchWord, page,model);
		return "/employee/employeeList";
	}

    @GetMapping("empRegist")
	public String form( Model model) {
		employeeAutoNumService.execute(model);
		return "/employee/employeeForm";
	}

    @RequestMapping(value="empRegist", method=RequestMethod.POST)
	public String form(@Validated EmployeeCommand employeeCommand, BindingResult result , Model model ) {
	// employeeForm에 있는 값을 command로 받아와서 유효성 검사
        if(result.hasErrors()) {
			// 오류가 있다면 employeeForm.html로
			return "/employee/employeeForm";
		}
		// 입력완료 후 EmployeeCommand 비밀번호확인 검사
		if (!employeeCommand.isEmpPwEqualsEmpPwCon()) {
			System.out.println("비밀번호 확인이 다릅니다.");
			//틀렸으면 다시 employeeForm.html로
			result.rejectValue("empPwCon", "employeeCommand.empPwCon",
					"비밀번호가 일치하지 않습니다.");
			return "/employee/employeeForm";
		}
		//완료시 DB 저장
		employeeInsertService.execute(employeeCommand);
		return "redirect:employeeList";
	}

	@PostMapping("empsDelete")
	public String membersDelete(
			@RequestParam(value="empDels") String empsDel []) {
		employeesDeleteService.execute(empsDel);
		return "redirect:employeeList";
	}

	@RequestMapping(value = "employeeDetail", method=RequestMethod.GET)
	public String employeeDetail(@RequestParam(value = "empNum") String empNum, Model model){
		employeeDetailService.execute(empNum, model);
		return "/employee/empDetail";
	}

	@RequestMapping(value="empModify", method = RequestMethod.GET)
	public String employeeUpdate(@RequestParam(value = "empNum") String empNum, Model model){
		employeeDetailService.execute(empNum, model);
		return "/employee/employeeUpdate";
	}

	@RequestMapping(value="empModify", method = RequestMethod.POST)
	public String employeeUpdate(@Validated EmployeeCommand employeeCommand, BindingResult result){
		if(result.hasErrors()){
			//에러메세지 띄워주기
			return "/employee/employeeUpdate";
		}
		employeeUpdateService.execute(employeeCommand);
		//수정 후 직원상세페이지로 이동시키기
		return "redirect:employeeDetail?empNum=" + employeeCommand.getEmpNum();
	}

	@GetMapping("empDelete")
	public String employeeDelete(@RequestParam(value = "empNum") String empNum){
		employeeDeleteService.execute(empNum);
		//삭제 후 직원목록페이지로 이동시키기
		return "redirect:employeeList";
	}
}
