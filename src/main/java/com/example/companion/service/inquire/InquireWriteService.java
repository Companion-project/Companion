package com.example.companion.service.inquire;

import com.example.companion.command.InquireCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.InquireDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.MemberMyMapper;
import com.example.companion.repository.InquireRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class InquireWriteService {
    @Autowired
    MemberMyMapper memberMyMapper;

    @Autowired
    InquireRepository inquireRepository;

    public void execute(InquireCommand inquireCommand, HttpSession session
            , BindingResult result) {
        // 이번에는 command에서 오류 체크를 하는 것이 아닌 정해 놓은 오류 코드를 이용해서 오류 메시지를 전달해보자.
        if(inquireCommand.getInquireSubject()=="") {
            result.rejectValue("inquireSubject","wrong");
            return;
        }
        if(inquireCommand.getInquireContent()=="") {
            result.rejectValue("inquireContent","bad");
            return;
        }
        AuthInfoDTO auth = (AuthInfoDTO	)session.getAttribute("auth");
        MemberDTO memDto = memberMyMapper.memberInfo(auth.getUserId());
        InquireDTO dto = new InquireDTO();
        dto.setGoodsNum(inquireCommand.getGoodsNum());
        dto.setInquireContent(inquireCommand.getInquireContent());
        dto.setInquireKind(inquireCommand.getInquireKind());
        dto.setInquireSubject(inquireCommand.getInquireSubject());
        dto.setMemberNum(memDto.getMemberNum());
        inquireRepository.inquireInsert(dto);
    }
}
