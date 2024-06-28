package com.example.companion.service.employees;

import com.example.companion.domain.EmployeeDTO;
import com.example.companion.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class EmployeeListService {

    @Autowired
    EmployeeMapper employeeMapper;

    public void execute(Model model){
        List<EmployeeDTO> list = employeeMapper.employeeAllSelect();
        model.addAttribute("list",list);
    }
}
