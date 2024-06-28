package com.example.companion.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("goodsIncoming")
@AllArgsConstructor
@NoArgsConstructor
public class GoodsIncomingDTO {
    String incomingNum;
    String goodsNum;
    Integer incomingQty;
    Date incomingDate;
    Date productionDate;
    Integer incomingPrice;
    String empNum;
}
