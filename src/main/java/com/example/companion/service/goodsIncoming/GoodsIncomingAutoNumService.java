package com.example.companion.service.goodsIncoming;

import com.example.companion.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GoodsIncomingAutoNumService {
    @Autowired
    GoodsMapper goodsMapper;   //goodsAutoNum으로 입고번호 받아오기

    public void execute(Model model){
        String num = goodsMapper.incomingAndGoodsAutoNum("goodsincoming", "incoming_num","hk",3);
        model.addAttribute("incomingNum", num);
    }
}
