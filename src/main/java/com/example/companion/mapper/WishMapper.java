package com.example.companion.mapper;

import com.example.companion.domain.GoodsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WishMapper {
    public Integer wishGoodsSelect(String goodsNum, String getMemberNum);
    public int wishInsert(String goodsNum,String memberNum);
    public int wishDelete(String goodsNum,String memberNum);
    public int wishGoodsDeletes(@Param("goodsNums") String [] wishGoodsDels,
                                @Param("memberNum") String memberNum);
}
