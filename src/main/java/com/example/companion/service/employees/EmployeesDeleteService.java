package com.example.companion.service.employees;

import com.example.companion.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeesDeleteService {
	@Autowired
	EmployeeMapper employeeMapper;

	public void execute(String empsDel []) {
		employeeMapper.employeesDelete(empsDel);
	}
}
