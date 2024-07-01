package com.example.companion.service.corner;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.GoodsDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.GoodsMapper;
import com.example.companion.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class GoodsWishListService {
    @Autowired
    MemberMyMapper memberMyMapper;
    @Autowired
    GoodsMapper goodsMapper;

    public void execute(HttpSession session, Model model){
        AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("auth");
        MemberDTO memDto = memberMyMapper.memberInfo(authInfo.getUserId());
        List<GoodsDTO> list = goodsMapper.wishGoodsList(memDto.getMemberNum());
        model.addAttribute("dtos", list);

    }

}
