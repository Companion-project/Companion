package com.example.companion.service.inquire;

import com.example.companion.command.InquireCommand;
import com.example.companion.domain.InquireDTO;
import com.example.companion.repository.InquireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquireModifyService {
    @Autowired
    InquireRepository inquireRepository;
    public void execute(InquireCommand inquireCommand) {
        InquireDTO dto = new InquireDTO();
        dto.setInquireContent(inquireCommand.getInquireContent());
        dto.setInquireKind(inquireCommand.getInquireKind());
        dto.setInquireNum(inquireCommand.getInquireNum());
        dto.setInquireSubject(inquireCommand.getInquireSubject());
        inquireRepository.inquireUpdate(dto);
    }
}
