package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.FreeBoardDTO;
import com.bitc.java501team3.dto.FreeBoardFileDTO;
import com.github.pagehelper.Page;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {
    public List<FreeBoardDTO> selectBoardList() throws Exception;

    public Page<FreeBoardDTO> selectBoardPageList(int pageNum) throws Exception;

//    게시글 상세
    public FreeBoardDTO selectBoardDetail(int boardIdx) throws Exception;
// 게시글 상세와 함께 파일 정보 가져오기
    List<FreeBoardFileDTO> selectBoardFileInfo(int boardIdx) throws Exception;

    public void boardEdit(FreeBoardDTO board) throws Exception;

    public void insertBoard(FreeBoardDTO board, MultipartHttpServletRequest multiPart) throws Exception;


}
