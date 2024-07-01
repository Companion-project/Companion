package com.example.companion.service.corner;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.MemberMyMapper;
import com.example.companion.mapper.WishMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsWishService {
    @Autowired
    MemberMyMapper memberMyMapper;
    @Autowired
    WishMapper wishMapper;
    
    public String execute(String goodsNum, HttpSession session){
        //로그인 정보 가져오기
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO memDto = memberMyMapper.memberInfo(auth.getUserId());

        Integer i = wishMapper.wishGoodsSelect(goodsNum, memDto.getMemberNum());
        System.out.println("Wish Select Result: " + i);

        if(auth.getGrade().equals("mem")) { //mem은 회원, emp는 직원이다.
            if (i == null) {
                // null 이라는 것은 등록된 상품이 없다는 것이므로 등록을 해야 합니다.
                wishMapper.wishInsert(goodsNum, memDto.getMemberNum());
                return "1";
            }else if(i == 1) {
                // 1이 왔다는 것은 등록된것이 있으므로 취소를 해야 합니다.
                wishMapper.wishDelete(goodsNum, memDto.getMemberNum());
                return "0";
            }
        }else {
            System.out.println("User is not a member, grade: " + auth.getGrade());
            return "999";
        }
        return null;
    }
}


