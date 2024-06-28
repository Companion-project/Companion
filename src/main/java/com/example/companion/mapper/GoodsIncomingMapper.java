package com.example.companion.mapper;

import com.example.companion.domain.GoodsIncomingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsIncomingMapper {
    public int goodsIncomingInsert(GoodsIncomingDTO dto);
    public List<GoodsIncomingDTO> goodsIncomingAllSelect();
    public GoodsIncomingDTO selectIncomingGoods(String incomingNum, String goodsNum);
    public int goodsIncomingUpdate(GoodsIncomingDTO dto);
    public int incomingGoodsNumDelete(@Param("incomingNum") String incomingNum,
                                      @Param("goodsNum") String goodsNum);
}
