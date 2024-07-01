package com.example.companion.service.purchase;

import com.example.companion.domain.OrderListDTO;
import com.example.companion.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class PurchaseListService {

    @Autowired
    PurchaseMapper purchaseMapper;
    public void execute(Model model){
        List<OrderListDTO> list = purchaseMapper.orderList(null, null);
        model.addAttribute("list", list);
    }
}
