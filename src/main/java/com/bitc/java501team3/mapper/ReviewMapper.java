package com.bitc.java501team3.mapper;

import com.bitc.java501team3.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    public void insertReview(ReviewDTO review) throws Exception;

    public List<ReviewDTO> selectReviewList(int idx)throws Exception;
}
