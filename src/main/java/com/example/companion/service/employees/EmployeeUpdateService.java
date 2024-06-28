package com.example.companion.service.employees;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.domain.EmployeeDTO;
import com.example.companion.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeUpdateService {
@Autowired
    EmployeeMapper employeeMapper;

    public void execute(EmployeeCommand employeeCommand){
        EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpAddr(employeeCommand.getEmpAddr());
		dto.setEmpAddrDetail(employeeCommand.getEmpAddrDetail());
		dto.setEmpssn(employeeCommand.getEmpssn());
		dto.setEmpEmail(employeeCommand.getEmpEmail());
		dto.setEmpName(employeeCommand.getEmpName());
		dto.setEmpNum(employeeCommand.getEmpNum());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		dto.setEmpPost(employeeCommand.getEmpPost());
		dto.setEmpRegiDate(employeeCommand.getEmpRegiDate());
		// dto에 저장한 값을 디비에 저장합니다.
		employeeMapper.employeeUpdate(dto);
    }
}
