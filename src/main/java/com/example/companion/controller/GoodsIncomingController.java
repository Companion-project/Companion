package com.example.companion.controller;

import com.example.companion.command.GoodsIncomingCommand;
import com.example.companion.domain.GoodsIncomingDTO;
import com.example.companion.service.goodsIncoming.*;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("goods")
public class GoodsIncomingController {
    @Autowired
    GoodsItemService goodsItemService;
    @Autowired
    GoodsIncomingAutoNumService goodsIncomingAutoNumService;
    @Autowired
    GoodsIncomingService goodsIncomingService;
    @Autowired
    GoodsIncomingListService goodsIncomingListService;
    @Autowired
    GoodsIncomingDetailService goodsIncomingDetailService;
    @Autowired
    GoodsIncomingUpdateService goodsIncomingUpdateService;
    @Autowired
    GoodsIncomingDeleteService goodsIncomingDeleteService;

    @GetMapping("goodsIncomingDelete")
    public String goodsIncomingDelete(
            @RequestParam("incomingNum") String incomingNum,
            @RequestParam("num") String goodsNum){
        goodsIncomingDeleteService.execute(incomingNum, goodsNum);
        return "redirect:goodsIncomingList";
    }

    @PostMapping("goodsIncomingModify")
    public String goodsIncomingModify(GoodsIncomingCommand goodsIncomingCommand){
        goodsIncomingUpdateService.execute(goodsIncomingCommand);
        return "redirect:goodsIncomingList";
    }

    @RequestMapping(value = "goodsIncomingUpdate", method = RequestMethod.GET)
    public String goodsIncomingUpdate(
            @RequestParam("incomingNum") String incomingNum,
            @RequestParam("num") String goodsNum,
            Model model){
        GoodsIncomingDTO dto = goodsIncomingDetailService.execute(incomingNum, goodsNum);
        model.addAttribute("dto",dto);  //dto를 전달하는 것이 아니므로 model에 저장
        return "goodsIncoming/goodsIncomingUpdate";
    }

    @PostMapping("goodsIncomingDetail")
    public @ResponseBody GoodsIncomingDTO detail(
            @RequestParam("incomingNum") String incomingNum,
            @RequestParam("goodsNum") String goodsNum){
        GoodsIncomingDTO dto = goodsIncomingDetailService.execute(incomingNum, goodsNum);
        return dto;

    }

    @PostMapping("goodsIncomingList")
    public ModelAndView goodsIncomingList(Model model){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsonView");
        goodsIncomingListService.execute(model);
        return mav;
    }

    @GetMapping("goodsIncomingList")
    public String goodsIncomingList(){
        return "/goodsIncoming/goodsIncomingList";
    }

    @RequestMapping(value = "incomingRegist", method = RequestMethod.POST)
    public String incomingRegist(GoodsIncomingCommand goodsIncomingCommand, HttpSession session){
        goodsIncomingService.execute(goodsIncomingCommand, session);
        return "redirect:goodsIncomingList";
    }

    @RequestMapping(value="goodsIncoming", method = RequestMethod.GET)
    public String goodsIncoming(Model model){
        goodsIncomingAutoNumService.execute(model);
        return "/goodsIncoming/goodsIncoming";
    }

    @GetMapping(value="goodsItem")
    public String goodsItem(){
        return "/goodsIncoming/goodsItem";
    }

    @PostMapping(value="goodsItemList")
    @ResponseBody  //Map을 이용해 goodsItem.html에 값 전송
    public Map<String, Object> goodsItem(
            @RequestParam(value = "searchWord", required = false) String searchWord,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page
    ){
        Map<String, Object> map =goodsItemService.execute(searchWord, page);
        return map;
    }
}
