package com.example.companion.service.purchase;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.CartGoodsDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.CartMapper;
import com.example.companion.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class GoodsBuyService {

    @Autowired
    MemberMyMapper memberMyMapper;
    @Autowired
    CartMapper cartMapper;
    public void execute(String [] prodCk, HttpSession session, Model model){
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO memDto = memberMyMapper.memberInfo(auth.getUserId());
        List<CartGoodsDTO> list = cartMapper.cartList(memDto.getMemberNum(), prodCk);
        Integer sumPrice = 0; // 상품수량 합계금액
        Integer sumTotalPrice = 0; // 결제 금액
        Integer sumDeliveryCost = 0; // 배송비합계금액
        String goodsNums = ""; // 상품번호들 저장
        for(CartGoodsDTO dto : list) {
            sumTotalPrice += dto.getTotalPrice();
            sumDeliveryCost += dto.getGoodsDTO().getDeliveryCost();
            goodsNums += dto.getGoodsDTO().getGoodsNum() + "-";
        }
        sumPrice = sumTotalPrice + sumDeliveryCost;
        model.addAttribute("list", list);
        model.addAttribute("sumPrice", sumPrice);
        model.addAttribute("sumTotalPrice", sumTotalPrice);
        model.addAttribute("sumDeliveryCost", sumDeliveryCost);
        model.addAttribute("goodsNums", goodsNums);
    }
}
