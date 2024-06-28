package com.example.companion.service.goods;

import com.example.companion.domain.GoodsDTO;
import com.example.companion.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GoodsDetailService {
    @Autowired
    GoodsMapper goodsMapper;

    public void execute(String goodsNum, Model model){
        GoodsDTO dto = goodsMapper.selectOne(goodsNum);
        model.addAttribute("goodsCommand", dto);
        // \n을 <br/>로 변경
        model.addAttribute("newLine","\n");
    }
}
