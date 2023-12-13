package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.ReviewDTO;
import com.bitc.java501team3.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public void writeReview(ReviewDTO review) throws Exception{
        reviewMapper.insertReview(review);
    }

    @Override
    public List<ReviewDTO> selectReviewList(int idx) throws Exception {
        return reviewMapper.selectReviewList(idx);
    }
}
