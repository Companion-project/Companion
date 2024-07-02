package com.example.companion.service.inquire;

import com.example.companion.command.InquireCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.EmployeeDTO;
import com.example.companion.domain.InquireDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.EmployeeMyMapper;
import com.example.companion.mapper.MemberMapper;
import com.example.companion.repository.InquireRepository;
import com.example.companion.service.EmailSendService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquireAnswerWriteService {
    @Autowired
    EmployeeMyMapper employeeMyMapper;
    @Autowired
    InquireRepository inquireRepository;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    EmailSendService emailSendService; // 답변을 메일링합니다.

    public void execute(InquireCommand inquireCommand, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        EmployeeDTO emp = employeeMyMapper.employeeInfo(auth.getUserId());
        InquireDTO dto = new InquireDTO();
        dto.setInquireNum(inquireCommand.getInquireNum());
        dto.setInquireAnswer(inquireCommand.getInquireAnswer());
        dto.setEmpNum(emp.getEmpNum());
        int i = inquireRepository.inquireAnswerUpdate(dto);
        try {
            if(i > 0 ) { // 답변 메일링
                MemberDTO memDto = memberMapper.memberSelectOne(inquireCommand.getMemberNum());
                String subject = inquireCommand.getInquireSubject() +"의 답변";
                String content = inquireCommand.getInquireSubject() +"의 답변 <br />"
                        + inquireCommand.getInquireAnswer().replace("\n", "<br />");
                String from = "newe9150@gmail.com";
                String to = memDto.getMemberEmail();
                emailSendService.mailsend(content, subject, from, to);
            }
        }catch(Exception e) {	} //메일링이 안되면 오류 발생할 수 있습니다.
    }
}
