package com.example.companion.controller;

import com.example.companion.service.goods.GoodsDetailViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("corner")
public class CornerController {
    @Autowired
    GoodsDetailViewService goodsDetailViewService;

    @GetMapping("detailView/{goodsNum}")
    public String prodInfo(@PathVariable("goodsNum") String goodsNum, Model model){
        goodsDetailViewService.execute(goodsNum, model);
        return "/corner/detailView";
    }
}
