package com.example.companion.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("purchaseListGoods")
public class PurchaseListGoodsDTO {
    //구매한 각 상품마다 하나의 상품정보
    PurchaseListDTO purchaseList;
    GoodsDTO goods;
    // 상품당 후기가 하나이므로 여기에 후기 추가
    ReviewDTO review;

}
