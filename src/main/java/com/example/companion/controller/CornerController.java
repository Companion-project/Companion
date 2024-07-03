package com.example.companion.controller;

import com.example.companion.domain.CartGoodsDTO;
import com.example.companion.service.corner.*;
import com.example.companion.service.goods.GoodsDetailService;
import com.example.companion.service.goods.GoodsDetailViewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("corner")
public class CornerController {
    @Autowired
    GoodsDetailViewService goodsDetailViewService;
    @Autowired
    GoodsWishService goodsWishService;
    @Autowired
    GoodsWishListService goodsWishListService;
    @Autowired
    WishGoodsDelsService wishGoodsDelsService;
    @Autowired
    WishDelService wishDelService;
    @Autowired
    CartInsertService cartInsertService;
    @Autowired
    CartListService cartListService;
    @Autowired
    GoodsCartDelsService goodsCartDelsService;
    @Autowired
    GoodsCartDelService goodsCartDelService;
    @Autowired
    CartQtyDownService cartQtyDownService;
    @Autowired
    GoodsDetailService goodsDetailService;

    @RequestMapping("goodsDescript")
    public String goodsDescript(
            @RequestParam(value="goodsNum") String goodsNum,
            Model model) {
        goodsDetailService.execute(goodsNum, model);
        return "corner/goodsDescript";
    }

    @GetMapping("detailView/{goodsNum}")
    public String prodInfo(@PathVariable("goodsNum") String goodsNum, Model model, HttpSession session){
        goodsDetailViewService.execute(goodsNum, model, session);
        goodsDetailService.execute(goodsNum, model);
        return "corner/detailView";
    }

    @RequestMapping(value = "goodsWishAdd", method = RequestMethod.POST)
    public @ResponseBody String goodsWishAdd(//ajax에 1또는 0을 전달하려면 RESTAPI나 @ResponseBody사용
                                             @RequestParam("goodsNum") String goodsNum,
                                             HttpSession session){
        return goodsWishService.execute(goodsNum, session);
    }

    @GetMapping("wishList")
    public String wishList(HttpSession session, Model model){
        goodsWishListService.execute(session, model);
        return "corner/wishList";
    }

    @PostMapping("goodsWishDels")
    public String goodsWishDels(
            @RequestParam("wishGoodsDel") String wishGoodsDels [],
            HttpSession session){
        wishGoodsDelsService.execute(wishGoodsDels, session);
        return "redirect:wishList";
    }

    @GetMapping("wishDel")
    public String wishDel(@RequestParam("goodsNum") String goodsNum,
                          HttpSession session){
        wishDelService.execute(goodsNum, session);
        return "redirect:wishList";
    }

    @GetMapping("cartAdd")
    @ResponseBody
    public String cartAdd(
            @RequestParam(value="goodsNum") String goodsNum,
            @RequestParam(value="qty") Integer qty,
            HttpSession session) {
        return cartInsertService.execute(goodsNum, qty, session);
    }

    @GetMapping("cartList")
    public String cartList(Model model, HttpSession session) {
        cartListService.execute(model, session);
        return "corner/cartList";
    }

    @PostMapping(value = "cartDels")
    @ResponseBody
    public String cartdel(
            @RequestParam("goodsNums[]") String goodsNums[],
            HttpSession session){
        return goodsCartDelsService.execute(goodsNums, session);
    }

    @GetMapping("cartDel")
    public String cartDel(
            @RequestParam("goodsNum") String goodsNum,
            HttpSession session) {
        goodsCartDelService.execute(goodsNum, session);
        return "redirect:cartList";
    }

    @GetMapping("cartQtyDown")
    public void cartQtyDown(
            @RequestParam(value = "goodsNum") String goodsNum,
            HttpSession session, HttpServletResponse response) {
        CartGoodsDTO dto = cartQtyDownService.execute(goodsNum, session);
        ObjectMapper mapper = new ObjectMapper();
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().print(mapper.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @GetMapping("buyItem")
    public String buyItem(
            @RequestParam(value = "goodsNum") String goodsNum,
            @RequestParam(value = "qty") Integer qty,
            HttpSession session, HttpServletResponse response){
            String result = cartInsertService.execute(goodsNum, qty, session);
            if(result == "999") {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter out;
                try {
                    out = response.getWriter();
                    String str = "<script>"
                            + "alert('관리자는 구매할 수 없습니다.');"
                            + "location.href='/corner/detailView/"+goodsNum+"';" //장바구니에 안들어 갔으면 상품페이지
                            + "</script>";
                    out.print(str);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(result == "000") {
                return "redirect:/"; //아니면 홈으로
            }
            //정상 처리 -> 결제정보 입력페이지로 이동
            return "redirect:/purchase/goodsBuy?prodCk="+goodsNum;
    }
}

