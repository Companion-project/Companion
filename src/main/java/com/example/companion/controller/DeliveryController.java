package com.example.companion.controller;

import com.example.companion.command.DeliveryCommand;
import com.example.companion.service.delivery.DeliveryDeleteService;
import com.example.companion.service.delivery.DeliveryInsertService;
import com.example.companion.service.delivery.DeliveryUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("delivery")
public class DeliveryController {
    @Autowired
    DeliveryInsertService delveryInsertService;
    @Autowired
    DeliveryDeleteService deliveryDeleteService;
    @Autowired
    DeliveryUpdateService deliveryUpdateService;

    @PostMapping("deliveryAction")
    public String deliveryAction(DeliveryCommand deliveryCommand) {
        delveryInsertService.execute(deliveryCommand);
        return "redirect:/purchase/purchaseDetail?purchaseNum=" + deliveryCommand.getPurchaseNum();
    }

    @PostMapping("deliveryDel")
    public String deliveryDel(
            @RequestParam("purchaseNum") String purchaseNum) {
        deliveryDeleteService.execute(purchaseNum);
        return "redirect:/purchase/purchaseDetail?purchaseNum=" + purchaseNum;
    }

    @GetMapping("deliveryState")
    public String deliveryState(@RequestParam("purchaseNum") String purchaseNum) {
        deliveryUpdateService.execute(purchaseNum);
        return "redirect:/purchase/purchaseList";
    }
}
