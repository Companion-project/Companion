package com.example.companion.service.goodsIncoming;

import com.example.companion.mapper.GoodsIncomingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsIncomingDeleteService {
    @Autowired
    GoodsIncomingMapper goodsIncomingMapper;

    public void execute(String incomingNum,String goodsNum){
        goodsIncomingMapper.incomingGoodsNumDelete(incomingNum,goodsNum);
    }
}
