package com.example.companion.service.review;

import com.example.companion.domain.ReviewDTO;
import com.example.companion.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewDetailService {
    @Autowired
    ReviewRepository reviewRepository;
    public ReviewDTO execute(Integer reviewNum){
        ReviewDTO dto = reviewRepository.reviewSelect(reviewNum);
        return dto;
    }
}
