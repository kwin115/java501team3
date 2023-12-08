package com.bitc.java501team3.dto;

import lombok.Data;

@Data
public class FreeBoardDTO {
    private int boardIdx;
    private String boardTitle;
    private String boardContent;
    private String boardUserId;
    private String boardCreateDate;
    private String boardOriginalFileName;
    private String boardStoredFileName;
    private String boardDeltedYn;
}
