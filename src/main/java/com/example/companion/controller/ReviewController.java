package com.example.companion.controller;

import com.example.companion.domain.ReviewDTO;
import com.example.companion.service.goods.GoodsDeleteService;
import com.example.companion.service.goods.GoodsDetailService;
import com.example.companion.service.review.ReviewDeleteService;
import com.example.companion.service.review.ReviewDetailService;
import com.example.companion.service.review.ReviewUpdateService;
import com.example.companion.service.review.ReviewWriteService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("review")
public class ReviewController {
    @Autowired
    GoodsDetailService goodsDetailService;
    @Autowired
    ReviewWriteService reviewWriteService;
    @Autowired
    ReviewDetailService reviewDetailService;
    @Autowired
    ReviewUpdateService reviewUpdateService;

    @RequestMapping(value="goodsReviewModify", method =RequestMethod.POST)
    public String goodsReviewUpdate(
            @RequestParam(value="reviewNum") Integer reviewNum,
            @RequestParam(value="reviewContent") String reviewContent
    ) {
        reviewUpdateService.execute(reviewNum, reviewContent);
        return "redirect:/purchase/orderList";
    }

    @RequestMapping(value="goodsReviewUpdate", method =RequestMethod.POST)
    public void goodsReviewUpdate(
            @RequestParam(value="reviewNum") Integer reviewNum
            , HttpServletResponse response) { //데이터를 json형식으로 넘기기 위해 response가 필요..
        ReviewDTO dto = reviewDetailService.execute(reviewNum);
        //날짜를 원하는 형식으로 변경한다.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String reviewDate = sdf.format(dto.getReviewDate());
        response.setCharacterEncoding("utf-8"); // json형식으로 데이터를 적어준다.
        String reviewJson   = "{\"reviewNum\":\"" + dto.getReviewNum() + "\"";
        reviewJson  += " ,\"reviewContent\":\"" + dto.getReviewContent() + "\"";
        reviewJson  += " ,\"reviewDate\":\"" + reviewDate + "\"}";
        try {
            response.getWriter().print(reviewJson); // ajax에 json형식으로 전달
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="goodsReviewUpdate", method =RequestMethod.GET)
    public String goodsReviewUpdate(
            @ModelAttribute(value = "reviewNum") String reviewNum,
            @RequestParam(value= "goodsNum") String goodsNum,
            Model model) {
        goodsDetailService.execute(goodsNum, model);
        return "review/goodsReviewUpdate";
    }


    @RequestMapping(value = "reviewWrite", method = RequestMethod.POST)
    public String reviewWrite(
            @RequestParam(value="goodsNum") String goodsNum,
            @RequestParam(value="reviewContent") String reviewContent,
            @RequestParam(value="purchaseNum") String purchaseNum,
            HttpSession session) {
        reviewWriteService.execute(goodsNum,reviewContent,purchaseNum,session );
        return "redirect:/purchase/orderList";
    }

    @RequestMapping(value="goodsReview" , method=RequestMethod.GET)
    public String goodsReview(
            @ModelAttribute("goodsNum") String goodsNum, // requestparam으로 받아 model에 저장합니다.
            @ModelAttribute("purchaseNum")String purchaseNum
            ,Model model){
        // 리뷰를 쓰기 위해 제품 정보를 가져오겠습니다.
        goodsDetailService.execute(goodsNum, model);
        return "review/goodsReview";
    }

    @Autowired
    ReviewDeleteService reviewDeleteService ;
    @GetMapping("goodsReviewDelete")
    public String goodsReviewDelete(
            @RequestParam(value="reviewNum") String reviewNum) {
        reviewDeleteService.execute(reviewNum);
        return "redirect:/purchase/orderList";
    }
}
