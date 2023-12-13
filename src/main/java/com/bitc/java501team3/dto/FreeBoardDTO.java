package com.bitc.java501team3.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class FreeBoardDTO {
    private int boardIdx;
    private String boardTitle;
    private String boardContent;
    private String boardUserId;
    private String boardCreateDate;
    private int boardHitcnt;
    private String boardDeltedYn;
    private String commentCount;

    private List<FreeBoardFileDTO> fileList;

    public String getBoardUserId() {
        return boardUserId;
    }

    public void setBoardUserId(String boardUserId) {
        this.boardUserId = boardUserId;
    }
}
