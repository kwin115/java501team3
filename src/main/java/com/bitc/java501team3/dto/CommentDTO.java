package com.bitc.java501team3.dto;


import lombok.Data;

@Data
public class CommentDTO {
    private int cmIdx;
    private int cmBoardIdx;
    private String cmContent;
    private String cmUserId;
    private String cmCreateDate;
    private String cmDeleteYn;
}
