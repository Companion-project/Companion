package com.example.companion.service.corner;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.CartMapper;
import com.example.companion.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsCartDelsService {
    @Autowired
    MemberMyMapper memberMyMapper;
    @Autowired
    CartMapper cartMapper;
    public String execute(String[] goodsNums ,  HttpSession session) {
        AuthInfoDTO auth  = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO memDto = memberMyMapper.memberInfo(auth.getUserId());
        List<String> cs  = new ArrayList<String>();
        for(String goodsNum : goodsNums) {
            cs.add(goodsNum);
        }
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("goodsNums", cs);
        condition.put("memberNum", memDto.getMemberNum());
        int i = cartMapper.goodsNumsDelete(condition);
        if (i >= 1) return "200";
        else return "999";
    }
}
