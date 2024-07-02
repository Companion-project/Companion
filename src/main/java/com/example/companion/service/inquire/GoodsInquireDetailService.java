package com.example.companion.service.inquire;

import com.example.companion.domain.InquireDTO;
import com.example.companion.repository.InquireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class GoodsInquireDetailService {
    @Autowired
    InquireRepository inquireRepository;
    public void execute(Integer inquireNum, Model model) {
        List<InquireDTO> list = inquireRepository.inquireList(null,inquireNum);
        model.addAttribute("list", list);
    }
}
