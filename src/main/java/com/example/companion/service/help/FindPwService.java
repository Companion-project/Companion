package com.example.companion.service.help;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.mapper.FindMapper;
import com.example.companion.service.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.UUID;

@Service
public class FindPwService {

    @Autowired
    FindMapper findMapper;

    @Autowired
    PasswordEncoder passwordEncoder; //암호화시키고

    @Autowired
    EmailSendService emailSendService; //메일을 통해 임시비밀번호 전송

    public void execute(String userId, String userPhone, Model model){
        AuthInfoDTO au = new AuthInfoDTO();
        AuthInfoDTO dto = findMapper.userEmail(userId, userPhone);
        if ( dto != null){
            String newPw = UUID.randomUUID().toString().substring(0, 8);
            dto.setUserId(userId);
            //새 비밀번호 암호화
            dto.setUserPw(passwordEncoder.encode(newPw));
            if(dto.getGrade().equals("mem")){ //회원
                dto.setTableName("members");
                dto.setPwColumName("member_pw");
                dto.setUserIdColumName("member_id");
            }else{ //직원
                dto.setTableName("employees");
                dto.setPwColumName("emp_pw");
                dto.setUserIdColumName("emp_id");
            }
            findMapper.pwUpdate(dto);
            model.addAttribute("dto", dto);
            String html = "<html><body>"
                    + dto.getUserEmail() +  "님의 임시 비밀번호는 " + newPw
                    + "입니다. </body></html>";
            String subject = dto.getUserEmail()+"의 임시 비밀번호";
            String fromEmail = "newe9150@gmail.com";
            String toEmail = dto.getUserEmail();
            emailSendService.mailsend(html, subject, fromEmail, toEmail);
        }
    }
}
