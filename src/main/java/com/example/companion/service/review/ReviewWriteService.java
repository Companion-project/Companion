package com.example.companion.service.review;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.ReviewDTO;
import com.example.companion.repository.ReviewRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewWriteService {
    @Autowired
    ReviewRepository reviewRepository;
    public void execute (String goodsNum, String reviewContent, String purchaseNum, HttpSession session){
        AuthInfoDTO auth= (AuthInfoDTO)session.getAttribute("auth");
        ReviewDTO dto = new ReviewDTO();
        dto.setGoodsNum(goodsNum);
        dto.setPurchaseNum(purchaseNum);
        dto.setReviewContent(reviewContent);
        dto.setMemberId(auth.getUserId());
        reviewRepository.reviewWrite(dto);
    }
}
