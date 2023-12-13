package com.bitc.java501team3.dto;

import lombok.Data;

@Data
public class FreeBoardFileDTO {
    private int freeboardFileIdx;
    private int freeboardIdx;
    private String freeboardOrgFileName;
    private String freeboardStoredFileName;
    private long freeboardFilesize;
    private String freeboardUserId;
    private String freeboardCreatedate;
}
