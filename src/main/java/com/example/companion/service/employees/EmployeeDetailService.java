package com.example.companion.service.employees;

import com.example.companion.domain.EmployeeDTO;
import com.example.companion.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class EmployeeDetailService {
    @Autowired
    EmployeeMapper employeeMapper;

    public void execute(String empNum, Model model){
        EmployeeDTO vo = employeeMapper.employeeOneSelect(empNum);
        model.addAttribute("employeeCommand", vo);
    }
}
