package com.example.companion.service.review;

import com.example.companion.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewDeleteService {
    @Autowired
    ReviewRepository reviewRepository;

    public void execute(String reviewNum) {
        reviewRepository.reviewDelete(Integer.parseInt(reviewNum));
    }
}
