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
    // 조인문 없이 조인을 하기 위해서는 두개의 테이블 검색 쿼리가 각각 존재해야 한다.
    public GoodsDTO goodsSelect(String goodsNum); // 그래서 각각 상품과 장바구니 쿼리문을 만들어주겠습니다.
    public CartDTO cartSelect(Integer cartNum); // 앞서 cartNum이 필요한데 테이블에는 cartNum없어 추가하겠습니다.

}
