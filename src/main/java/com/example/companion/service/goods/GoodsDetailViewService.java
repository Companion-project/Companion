package com.example.companion.service.goods;

import com.example.companion.domain.GoodsDetailStockDTO;
import com.example.companion.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GoodsDetailViewService {
    @Autowired
    GoodsMapper goodsMapper;

    public void execute(String goodsNum, Model model){
        //detail.view에 들어갈 데이터 가지고오기
        goodsMapper.visitCount(goodsNum); //방문자수 증가는 상품을 가져오기 전에 증가해야함
        GoodsDetailStockDTO dto = goodsMapper.goodsDetailStock(goodsNum);
        model.addAttribute("dto", dto);
    }
}
