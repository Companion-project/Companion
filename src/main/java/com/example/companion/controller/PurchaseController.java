package com.example.companion.controller;

import com.example.companion.command.PurchaseCommand;
import com.example.companion.service.IniPayReqService;
import com.example.companion.service.purchase.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
    @Autowired
    GoodsBuyService goodsBuyService;
    @Autowired
    GoodsOrderService goodsOrderService;
    @Autowired
    IniPayReqService iniPayReqService;
    @Autowired
    IniPayReturnService iniPayReturnService;
    @Autowired
    OrderProcessListService orderProcessListService;
    @Autowired
    PaymentDeleteService paymentDeleteService;

    @GetMapping("paymentOk")
    public String paymentOk(
            @RequestParam(value = "purchaseNum") String purchaseNum,
            HttpSession session, Model model){
        try {
            iniPayReqService.execute(purchaseNum,model,session);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 이제 결제 페이지로 이동하겠습니다.
        return "purchase/payment";
    }

    @RequestMapping(value="goodsBuy")
    public String goodsBuy(
            @RequestParam(value = "prodCk") String [] prodCk, //체크박스가 배열로 전송
            HttpSession session, Model model){
        goodsBuyService.execute(prodCk, session, model);
        return "purchase/goodsOrder";
    }

    @PostMapping("goodsOrder")
    public String goodsOrder(PurchaseCommand purchaseCommand, HttpSession session, Model model,
                             HttpServletResponse response){
        String purchaseNum = goodsOrderService.execute(purchaseCommand, session, model);
        return "redirect:paymentOk?purchaseNum="+purchaseNum;
    }
    @PostMapping("INIstdpay_pc_return")
    public String payReturn(HttpServletRequest request, HttpSession session, Model model) {
        iniPayReturnService.execute(request, session, model);
        return "purchase/buyfinished";
    }
    @RequestMapping("orderList")
    public String orderList(HttpSession session, Model model){
        orderProcessListService.execute(session, model);
        return "purchase/orderList";
    }
    @RequestMapping("paymentDel")
    public String paymentDel(
            @RequestParam("purchaseNum") String purchaseNum) {
        paymentDeleteService.execute(purchaseNum);
        return "redirect:orderList";
    }

}
