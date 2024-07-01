package com.example.companion.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("purchase")
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {
    String purchaseNum;
    Date purchaseDate;
    Integer purchasePrice;
    String deliveryAddr;
    String deliveryAddrDetail;
    String deliveryPost;
    String deliveryPhone;
    String message;
    String purchaseStatus;
    String memberNum;
    String deliveryName;
}
