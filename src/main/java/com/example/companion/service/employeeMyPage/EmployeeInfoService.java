package com.example.companion.service.employeeMyPage;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.EmployeeDTO;
import com.example.companion.mapper.EmployeeMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class EmployeeInfoService {
    @Autowired
    EmployeeMyMapper employeeMyMapper;

    public void execute(HttpSession session, Model model){
        //session에서 정보 가져오기 
        AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("auth");
        String empId = authInfo.getUserId(); //로그인 아이디
        EmployeeDTO dto = employeeMyMapper.employeeInfo(empId); //아이디를 이용해서 dto에 저장

        EmployeeCommand employeeCommand = new EmployeeCommand(); //dto에 저장한 값을 command에 다시 저장

        employeeCommand.setEmpAddr(dto.getEmpAddr());
		employeeCommand.setEmpAddrDetail(dto.getEmpAddrDetail());
		employeeCommand.setEmpEmail(dto.getEmpEmail());
		employeeCommand.setEmpId(dto.getEmpId());
		employeeCommand.setEmpssn(dto.getEmpssn());
		employeeCommand.setEmpName(dto.getEmpName());
		employeeCommand.setEmpNum(dto.getEmpNum());
		employeeCommand.setEmpPhone(dto.getEmpPhone());
		employeeCommand.setEmpPost(dto.getEmpPost());
		employeeCommand.setEmpRegiDate(dto.getEmpRegiDate());
        
		//model로 뿌려주기
		model.addAttribute("employeeCommand", employeeCommand);
    }
}
