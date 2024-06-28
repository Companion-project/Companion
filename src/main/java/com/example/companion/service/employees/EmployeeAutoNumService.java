package com.example.companion.service.employees;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class EmployeeAutoNumService {

    @Autowired
    EmployeeMapper employeeMapper;

    //validated할 때 command를 사용하므로 미리 command에 저장
    public void execute(Model model){
        String empNum = employeeMapper.autoNum();
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setEmpNum(empNum);
        model.addAttribute("employeeCommand", employeeCommand);
    }
}
