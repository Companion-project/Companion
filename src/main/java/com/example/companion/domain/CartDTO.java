package com.example.companion.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("cart")
public class CartDTO {
    Integer cartNum;
    String memberNum;
    String goodsNum;
    Integer cartQty;
    Date cartDate;
    String [] goodsNums;
}
