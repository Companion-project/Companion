package com.example.companion.service.goodsIncoming;

import com.example.companion.command.GoodsIncomingCommand;
import com.example.companion.domain.GoodsIncomingDTO;
import com.example.companion.mapper.GoodsIncomingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class GoodsIncomingUpdateService {
    @Autowired
    GoodsIncomingMapper goodsIncomingMapper;

    public void execute(GoodsIncomingCommand goodsIncomingCommand){

        GoodsIncomingDTO dto = new GoodsIncomingDTO();
        dto.setGoodsNum(goodsIncomingCommand.getGoodsNum());
        dto.setIncomingDate(goodsIncomingCommand.getIncomingDate());
        dto.setIncomingNum(goodsIncomingCommand.getGoodsIncomingNum());
        dto.setIncomingPrice(goodsIncomingCommand.getIncomingPrice());
        dto.setIncomingQty(goodsIncomingCommand.getIncomingQty());
        dto.setProductionDate(Timestamp.valueOf(goodsIncomingCommand.getProductionDate()));

        goodsIncomingMapper.goodsIncomingUpdate(dto);
    }
}
