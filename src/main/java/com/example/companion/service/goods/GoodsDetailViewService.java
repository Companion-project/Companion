package com.example.companion.service.goods;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.GoodsDetailStockDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.GoodsMapper;
import com.example.companion.mapper.MemberMyMapper;
import com.example.companion.mapper.WishMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GoodsDetailViewService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    MemberMyMapper memberMyMapper;
    @Autowired
    WishMapper wishMapper;

    public void execute(String goodsNum, Model model, HttpSession session) {
        // detailView.html에 들어 갈 데이터를 가지고 옵니다.
        goodsMapper.visitCount(goodsNum); //방문자수 증가는 상품을 가져오기 전에 증가해야함
        GoodsDetailStockDTO dto = goodsMapper.goodsDetailStock(goodsNum);

        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        if (auth != null) {
            try {
                MemberDTO memdto = memberMyMapper.memberInfo(auth.getUserId());
                Integer i = wishMapper.wishGoodsSelect(goodsNum, memdto.getMemberNum());
                model.addAttribute("wish", i);
            }catch(Exception e) {}
            }
        model.addAttribute("dto", dto);
        }
    }