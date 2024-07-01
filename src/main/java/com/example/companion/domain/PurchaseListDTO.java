package com.example.companion.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("purchaseList")
public class PurchaseListDTO {
    String purchaseNum;
    String  goodsNum;
    Integer purchaseQty;
    Integer totalPrice;

    String memberNum;
    String [] goodsNums;
}
