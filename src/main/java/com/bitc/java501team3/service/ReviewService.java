package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.ReviewDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface ReviewService {
    void writeReview(ReviewDTO review) throws Exception;

    List<ReviewDTO> selectReviewList(int idx)throws Exception;
}
