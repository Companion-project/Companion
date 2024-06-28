package com.example.companion.controller;

import com.example.companion.command.LoginCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(
            /*
            LoginCommand loginCommand만 사용해도 되나,
            @ModelAttribute("loginCommand")와 같이 사용하는 방법도 있음.
            */
            @ModelAttribute("loginCommand") LoginCommand loginCommand){
        return "index";
    }
}
