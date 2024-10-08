package com.example.companion.service.review;

import com.example.companion.domain.ReviewDTO;
import com.example.companion.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ReviewListService {
    @Autowired
    ReviewRepository reviewRepository;
    public void execute(String goodsNum, Model model) {
        List<ReviewDTO> list =  reviewRepository.goodsReviewList(goodsNum);
        model.addAttribute("list", list);
    }
}
