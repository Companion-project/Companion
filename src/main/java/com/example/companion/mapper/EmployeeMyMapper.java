package com.example.companion.mapper;

import com.example.companion.domain.EmployeeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMyMapper {
    public EmployeeDTO employeeInfo(String empId);
    public int employeeInfoUpdate(EmployeeDTO dto);
    public int employeePwUpdate(String userPw, String empId);
}
