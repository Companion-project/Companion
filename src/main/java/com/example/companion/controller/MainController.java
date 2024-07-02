package com.example.companion.controller;

import com.example.companion.command.LoginCommand;
import com.example.companion.service.MainGoodsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


     @RequestMapping("/")
    public String index(@ModelAttribute("loginCommand") LoginCommand loginCommand, Model model){

        return "index";
    }
}
