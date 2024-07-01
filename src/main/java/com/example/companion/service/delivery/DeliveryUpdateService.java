package com.example.companion.service.delivery;

import com.example.companion.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryUpdateService {
    @Autowired
    DeliveryRepository deliveryRepository;
    public void execute(String purchaseNum) {
        deliveryRepository.deliveryStateUpdate(purchaseNum);
    }
}
