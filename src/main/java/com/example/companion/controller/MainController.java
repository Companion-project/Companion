package com.example.companion.controller;

import com.example.companion.command.LoginCommand;
import com.example.companion.service.CookiesService;
import com.example.companion.service.review.MainGoodsListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

     @RequestMapping("/")
    public String index(@ModelAttribute("loginCommand") LoginCommand loginCommand, Model model, HttpServletRequest request){
         //index.html페이지가 열릴 때 상품정보를 가지고 와야 합니다.

         //index.html페이지가 열릴 때 쿠키가 있는지 확인해야겠죠..
         cookiesService.execute(request, model); //로그인꺼임
         mainGoodsListService.execute(model);
        return "index";
    }
}
