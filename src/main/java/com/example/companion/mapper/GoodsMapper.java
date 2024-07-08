package com.example.companion.mapper;

import com.example.companion.domain.CartDTO;
import com.example.companion.domain.GoodsDTO;
import com.example.companion.domain.GoodsDetailStockDTO;
import com.example.companion.domain.StartEndPageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {
    public String incomingAndGoodsAutoNum(@Param("tableName") String tableName,
                                          @Param("columnName") String columnName,
                                          @Param("sep") String sep,
                                          @Param("seq") Integer seq);
    public int goodsInsert(GoodsDTO dto);
    public List<GoodsDTO> allSelect(StartEndPageDTO sepDTO);
    public int goodsCount(String searchWord);
    public int productsDelete(@Param("products") String products[]);
    public GoodsDTO selectOne(String goodsNum);
    public int goodsUpdate(GoodsDTO dto);
    public int goodsDelete(String goodsNum);
    public GoodsDetailStockDTO goodsDetailStock(String goodsNum);
    public  int visitCount(String goodsNum);
    public List<GoodsDTO> wishGoodsList(String memberNum);
}
