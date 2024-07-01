package com.example.companion.controller;

import com.example.companion.service.purchase.PurchaseDetailService;
import com.example.companion.service.purchase.PurchaseListService;
import com.example.companion.service.purchase.PurchaseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("purchase")
public class EmpPurchaseController {
    @Autowired
    PurchaseListService purchaseListService;
    @Autowired
    PurchaseStatusService purchaseStatusService;
    @Autowired
    PurchaseDetailService purchaseDetailService;

    @RequestMapping("purchaseStatus")
    public String purchaseStatus(
            @RequestParam("purchaseNum")String purchaseNum,Model model) {
        purchaseStatusService.execute(purchaseNum);
        model.addAttribute("newLineChar", "\n");
        return "redirect:purchaseList";
    }

    @RequestMapping("purchaseList")
    public String purchaseList(Model model) {
        purchaseListService.execute(model);
        return "purchase/purchaseList";
    }

    @RequestMapping("purchaseDetail")
    public String purchaseDetail(
            @RequestParam(value="purchaseNum") String purchaseNum
            ,Model model) {
        purchaseDetailService.execute(purchaseNum, model);
        model.addAttribute("newLineChar", "\n");
        return "purchase/purchaseDetail";
    }
}
