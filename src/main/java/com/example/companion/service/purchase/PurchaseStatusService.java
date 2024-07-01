package com.example.companion.service.purchase;

import com.example.companion.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseStatusService {
    @Autowired
    PurchaseMapper purchaseMapper;
    public void execute(String purchaseNum){
        purchaseMapper.purchaseStatusUpdate("상품준비중", purchaseNum);
    }

}
