package com.example.companion.command;

import lombok.Data;

import java.util.Date;

@Data
public class DeliveryCommand {
    String purchaseNum;
    String deliveryNumber;
    Date deliveryDate;
    String deliveryState;
    String deliveryCompany;
}
