package com.example.companion.service.purchase;

import com.example.companion.domain.OrderListDTO;
import com.example.companion.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class PurchaseDetailService {
    @Autowired
    PurchaseMapper purchaseMapper;
    public void execute(String purchaseNum, Model model) {
        List<OrderListDTO> list = purchaseMapper.orderList(null, purchaseNum);
        model.addAttribute("dto", list.get(0)); //list중 한개만 가져오므로 list.get(0)
    }
}
