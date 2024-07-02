package com.example.companion.service.inquire;

import com.example.companion.domain.InquireDTO;
import com.example.companion.repository.InquireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class InquireListService {
    @Autowired
    InquireRepository inquireRepository;

    public void execute(String goodsNum, Model model) {
        List<InquireDTO> list = inquireRepository.inquireList(goodsNum, null);
        model.addAttribute("list", list);
    }
}
