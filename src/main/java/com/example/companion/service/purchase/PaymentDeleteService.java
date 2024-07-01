package com.example.companion.service.purchase;

import com.example.companion.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDeleteService {
    @Autowired
    PurchaseMapper purchaseMapper;
    public void execute(String purchaseNum) {
        int i = purchaseMapper.paymentDelete(purchaseNum);
        if(i >= 1) {
            purchaseMapper.purchaseStatusUpdate("입금대기중" ,purchaseNum);
        }
    }
}
