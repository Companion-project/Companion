package com.example.companion.controller;

import com.example.companion.service.MainGoodsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class categoryController {

 @Autowired
 MainGoodsListService mainGoodsListService;

    @RequestMapping("/{goodsCategory}")
    public String category(@PathVariable("goodsCategory") String goodsCategory,Model model) {
        mainGoodsListService.execute(goodsCategory,model);
        return "category";
    }
}
