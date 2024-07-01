package com.example.companion.service.review;

import com.example.companion.domain.ReviewDTO;
import com.example.companion.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewUpdateService {
    @Autowired
    ReviewRepository reviewRepository;
    public void execute(Integer reviewNum, String reviewContent){
        ReviewDTO dto = new ReviewDTO();
        dto.setReviewNum(reviewNum);
        dto.setReviewContent(reviewContent);
        reviewRepository.reviewUpdate(dto);
    }
}
