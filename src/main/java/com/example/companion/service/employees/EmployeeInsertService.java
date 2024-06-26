package com.example.companion.service.employees;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.domain.EmployeeDTO;
import com.example.companion.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeInsertService {
    @Autowired
    PasswordEncoder passwordEncoder;
	@Autowired
    EmployeeMapper employeeMapper;

	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpAddr(employeeCommand.getEmpAddr());
		dto.setEmpAddrDetail(employeeCommand.getEmpAddrDetail());
		dto.setEmpEmail(employeeCommand.getEmpEmail());
		dto.setEmpId(employeeCommand.getEmpId());
		dto.setEmpName(employeeCommand.getEmpName());
		dto.setEmpNum(employeeCommand.getEmpNum());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		dto.setEmpPost(employeeCommand.getEmpPost());
		dto.setEmpPw( passwordEncoder.encode(employeeCommand.getEmpPw()));
		dto.setEmpssn(employeeCommand.getEmpssn());

		employeeMapper.employeeInsert(dto);
	}
}
