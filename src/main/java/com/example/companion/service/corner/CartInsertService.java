package com.example.companion.service.corner;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.CartDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.CartMapper;
import com.example.companion.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartInsertService {

    @Autowired
    MemberMyMapper memberMyMapper;
    @Autowired
    CartMapper cartMapper;

    public String execute(String goodsNum, Integer qty, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        if(auth != null) {
            if(auth.getGrade().equals("mem")) {
                MemberDTO memDto = memberMyMapper.memberInfo(auth.getUserId());
                CartDTO dto = new CartDTO();
                dto.setCartQty(qty);
                dto.setGoodsNum(goodsNum);
                dto.setMemberNum(memDto.getMemberNum());
                cartMapper.cartInsert(dto);
                return "200"; //정상처리됨
            }else {
                System.out.println("직원은 직원전용페이지를 사용하세요..");
                return "999";
            }
        }else {
            System.out.println("로그인을 해야합니다.");
            return "000";
        }
    }
}