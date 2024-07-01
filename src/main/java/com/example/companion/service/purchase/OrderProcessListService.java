package com.example.companion.service.purchase;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.domain.OrderListDTO;
import com.example.companion.mapper.MemberMyMapper;
import com.example.companion.mapper.PurchaseMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class OrderProcessListService {
    @Autowired
    MemberMyMapper memberMyMapper;
    @Autowired
    PurchaseMapper purchaseMapper;
    public void execute(HttpSession session, Model model){
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO memDto = memberMyMapper.memberInfo(auth.getUserId());
        List<OrderListDTO> list = purchaseMapper.orderList(memDto.getMemberNum(), null);
        model.addAttribute("list", list);
    }
}
