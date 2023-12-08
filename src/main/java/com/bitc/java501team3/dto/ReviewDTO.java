package com.bitc.java501team3.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private int reviewIdx;
    private int reviewStoreIdx;
    private String reviewContent;
    private String reviewUserId;
    private String reviewCreateDate;
    private String reviewOriginalFileName;
    private String reviewStoredlFileName;
    private String reviewDeletedYn;
}
