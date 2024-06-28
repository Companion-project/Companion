package com.example.companion.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("goodsDetailStock")
public class GoodsDetailStockDTO {
    //stock을 GoodsDTO에 추가해도 되지만 마이바티스에서 생성자를 사용하고 있으므로 위험부담이 있다 분리하기
    GoodsDTO goodsDTO;  //상품 정보를 가져오는 객체
    Integer stock;  //재고 수량
}
