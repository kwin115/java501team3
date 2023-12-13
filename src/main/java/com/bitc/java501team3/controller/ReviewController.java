package com.bitc.java501team3.controller;

import com.bitc.java501team3.dto.ReviewDTO;
import com.bitc.java501team3.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @ResponseBody
    @RequestMapping("/review/write")
    public String writeReview(ReviewDTO review) throws Exception{
        reviewService.writeReview(review);

        return "/main/detail";//수정필요
    }

    @ResponseBody
    @GetMapping("/review/list")
    public List<ReviewDTO> selectReview(@RequestParam int idx) throws Exception {
        return reviewService.selectReviewList(idx);
    }
}
