package com.example.companion.service.employees;

import com.example.companion.mapper.EmployeeMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDeleteService {
    @Autowired
    EmployeeMapper employeeMapper;

    public void execute(String empNum){
        employeeMapper.employeeDelete(empNum);
    }
}
