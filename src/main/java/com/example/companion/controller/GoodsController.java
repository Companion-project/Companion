package com.example.companion.controller;

import com.example.companion.command.GoodsCommand;
import com.example.companion.service.goods.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    GoodsAutoNumService goodsAutoNumService;
    @Autowired
    GoodsWriteService goodsWriteService;
    @Autowired
    GoodsListService goodsListService;
    @Autowired
    ProductsDeleteService productsDeleteService;
    @Autowired
    GoodsDetailService goodsDetailService;
    @Autowired
    GoodsUpdateService goodsUpdateService;
    @Autowired
    GoodsDeleteService goodsDeleteService;

    @RequestMapping(value = "goodsList", method = RequestMethod.GET)
    public String goodsList(
                @RequestParam(value = "searchWord", required = false) String searchWord,
                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                Model model){
        goodsListService.execute(searchWord,model,page);
        return "/goods/goodsList";
    }

    @GetMapping("goodsForm")
    public String goodsForm(){
        return "/goods/goodsWrite";
    }

    @GetMapping("goodsWrite")
    public String goodsWrite(Model model){
        goodsAutoNumService.execute(model);
        return "/goods/goodsForm";
    }

    @RequestMapping(value = "goodsWrite" , method = RequestMethod.POST)
    public String goodsWrite(@Validated GoodsCommand goodsCommand, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return "/goods/goodsForm";
        }
        //메인이미지는 필수, 없을시 에러메세지
        if(goodsCommand.getGoodsMainStore().getOriginalFilename().isEmpty()){
            result.rejectValue("goodsMainStore","goodsCommand.goodsMainStore","메인이미지를 첨부해주세요.");
            return "/goods/goodsForm";
        }
        goodsWriteService.execute(goodsCommand, session);
        //오류가 없는 경우 goodsForm.html, 정상이면 goodsRedirect.html(200)을 준다
        return "/goods/goodsRedirect";
    }

    @PostMapping("productsDelete")
    public String productsDelete(
            @RequestParam(value="memDels") String memDels[]){
        productsDeleteService.execute(memDels);
        return "redirect:goodsList";
    }

    @GetMapping("goodsDetail")
    public String goodsDetail(@RequestParam("goodsNum") String goodsNum, Model model){
        goodsDetailService.execute(goodsNum,model);
        return "/goods/goodsInfo";
    }

    @GetMapping("goodsUpdate")
    public String goodsUpdate(@RequestParam("goodsNum") String goodsNum,Model model, HttpSession session){
        // 삭제파일 선택 후 다시 수정페이지로 오면  session때문에 에러의 소지가 있으니 session 제거 해준다
        session.removeAttribute("fileList");    //삭제할 파일 정보를 가지고 있는 session 삭제
        goodsDetailService.execute(goodsNum,model);
        return "/goods/goodsModify";
    }

    @PostMapping("goodsUpdate")
    public String goodsUpdate(@Validated GoodsCommand goodsCommand, BindingResult result, HttpSession session, Model model){
        goodsUpdateService.execute(goodsCommand,session, result, model);
        if(result.hasErrors()){
            return "/goods/goodsModify";
        }
        return "redirect:goodsDetail?goodsNum="+goodsCommand.getGoodsNum();
    }

    @RequestMapping("goodsDel/{goodsNum}")
    public String goodsDel(@PathVariable("goodsNum") String goodsNum){
        goodsDeleteService.execute(goodsNum);
        return "redirect:../goodsList";
    }



}
