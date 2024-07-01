package com.example.companion.service.delivery;

import com.example.companion.command.DeliveryCommand;
import com.example.companion.domain.DeliveryDTO;
import com.example.companion.repository.DeliveryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryInsertService{
    @Autowired
    DeliveryRepository deliveryRepository;
    public void execute(DeliveryCommand deliveryCommand) {
        DeliveryDTO deliveryDTO= new DeliveryDTO();
        BeanUtils.copyProperties(deliveryCommand, deliveryDTO);
        deliveryRepository.deliveryInsert(deliveryDTO);
    }
}
