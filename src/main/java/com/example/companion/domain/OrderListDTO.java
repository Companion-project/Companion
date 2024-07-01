package com.example.companion.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@Alias("orderList")
public class OrderListDTO {
    // 구매정보 마다 결제정보 하나를 가지고 온다.
    PurchaseDTO purchaseDTO; // 1
    PaymentDTO paymentDTO; //1
    DeliveryDTO deliveryDTO;
    List<PurchaseListGoodsDTO> purchaseListGoodsDTOs; // n
    // 즉, 1:N
}
