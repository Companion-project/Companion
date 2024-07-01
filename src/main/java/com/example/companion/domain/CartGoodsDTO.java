package com.example.companion.domain;


import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("cartGoods")
public class CartGoodsDTO {
    GoodsDTO goodsDTO;
    CartDTO cartDTO;
    Integer totalPrice;

}
