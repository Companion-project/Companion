package com.example.companion.controller;

import com.example.companion.command.LoginCommand;
import com.example.companion.service.CookiesService;
import com.example.companion.service.review.MainGoodsListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    MainGoodsListService mainGoodsListService;
    @Autowired
    CookiesService cookiesService;
    //LoginCommand loginCommand만 사용해도 되나
    //@ModelAttribute("loginCommand")와 같이 사용하는 방법도 있다 둘중에 하나.
    //자주 사용하는 방법이 아니므로 여기서는 @ModelAttribute("loginCommand") LoginCommand loginCommand
    //사용하겠습니다.


@GetMapping("/")
public String main(@ModelAttribute("loginCommand") LoginCommand loginCommand,
                    Model model, HttpServletRequest request) {
    mainGoodsListService.execute(null, model);
    return "index";
}

@RequestMapping("/{goodsCategory}")
public String index(@ModelAttribute("loginCommand") LoginCommand loginCommand,
                    @PathVariable("goodsCategory") String goodsCategory,
                    Model model, HttpServletRequest request) {
    mainGoodsListService.execute(goodsCategory, model);
    return "index";
}
}
