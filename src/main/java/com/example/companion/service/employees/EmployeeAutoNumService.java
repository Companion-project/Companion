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
    public void execute(Model model ){
        String empNum =employeeMapper.autoNum();
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setEmpNum(empNum);
        model.addAttribute("employeeCommand",employeeCommand);
    }
}
