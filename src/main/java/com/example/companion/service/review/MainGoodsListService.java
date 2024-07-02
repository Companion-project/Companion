package com.example.companion.service.review;

import com.example.companion.domain.GoodsDTO;
import com.example.companion.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MainGoodsListService {
    @Autowired
    CartMapper cartMapper;
    public void execute( Model model) {
        List<GoodsDTO> list = cartMapper.goodsSelectAll();
        model.addAttribute("list", list);
    }
}
