package com.example.companion.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("delivery")
public class DeliveryDTO {
    String purchaseNum;
    String deliveryNumber;
    Date deliveryDate;
    String deliveryState;
    String deliveryCompany;
}
