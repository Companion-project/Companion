package com.example.companion.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("payment")
public class PaymentDTO {
    String purchaseNum;
    String confirmnumber;
    String cardnum;
    String tid;
    String totalprice;
    String resultmessage;
    String paymethod;
    String appldate;
    String appltime;
    String purchasename;
}
