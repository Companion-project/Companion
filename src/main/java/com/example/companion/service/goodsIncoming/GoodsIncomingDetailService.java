package com.example.companion.service.goodsIncoming;

import com.example.companion.domain.GoodsIncomingDTO;
import com.example.companion.mapper.GoodsIncomingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsIncomingDetailService {
    @Autowired
    GoodsIncomingMapper goodsIncomingMapper;

    public GoodsIncomingDTO execute(String incomingNum, String goodsNum){
        GoodsIncomingDTO dto = goodsIncomingMapper.selectIncomingGoods(incomingNum, goodsNum);
        return dto;
    }
}
