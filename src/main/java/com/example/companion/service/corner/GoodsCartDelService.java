package com.example.companion.service.corner;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.CartMapper;
import com.example.companion.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsCartDelService {
    @Autowired
    MemberMyMapper memberMyMapper;
    @Autowired
    CartMapper cartMapper;
    public void execute(String goodsNum , HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO dto = memberMyMapper.memberInfo(auth.getUserId());
        cartMapper.goodsNumDelete(goodsNum, dto.getMemberNum());
    }

}
