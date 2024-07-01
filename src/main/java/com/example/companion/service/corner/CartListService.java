package com.example.companion.service.corner;

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
public class CartListService {

    @Autowired
    MemberMyMapper memberMyMapper;
    @Autowired
    CartMapper cartMapper;

    public void execute(Model model, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO memDto = memberMyMapper.memberInfo(auth.getUserId());
        // 상품정보와 장바구니 정보를 같이 가져와야 한다.
        // 상품 한개가 아니라 여러개 이므로 list로 받아와야 할 것입니다. // 여기도 사용하므로 오류 발생, null 추가
        List<CartGoodsDTO> list = cartMapper.cartList(memDto.getMemberNum(), null);
        Integer sumPrice = cartMapper.sumPrice(memDto.getMemberNum());
        model.addAttribute("list", list);
        model.addAttribute("sumPrice", sumPrice);
        System.out.println("list.size() : " + list.size());
    }
}
