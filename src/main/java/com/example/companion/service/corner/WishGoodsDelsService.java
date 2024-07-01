package com.example.companion.service.corner;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.MemberMyMapper;
import com.example.companion.mapper.WishMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishGoodsDelsService {
    @Autowired
    MemberMyMapper memberMyMapper;
    @Autowired
    WishMapper wishMapper;

    public void execute(String wishGoodsDels[], HttpSession session){
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        MemberDTO dto = memberMyMapper.memberInfo(auth.getUserId());
        wishMapper.wishGoodsDeletes(wishGoodsDels, dto.getMemberNum());
    }
}
