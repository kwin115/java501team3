package com.bitc.java501team3.service;

import com.bitc.java501team3.common.FileUtils;
import com.bitc.java501team3.dto.FreeBoardDTO;
import com.bitc.java501team3.dto.FreeBoardFileDTO;
import com.bitc.java501team3.mapper.BoardMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FileUtils fileUtils;


    @Override
    public List<FreeBoardDTO> selectBoardList() throws Exception {

        return boardMapper.selectBoardList();
    }

    @Override
    public Page<FreeBoardDTO> selectBoardPageList(int pageNum) throws Exception {
        PageHelper.startPage(pageNum, 5);
        return boardMapper.selectBoardPageList();
    }

    //게시글 상세보기
    @Override
    public FreeBoardDTO selectBoardDetail(int boardIdx) throws Exception {
        //mapper를 사용하여 해당 글번호의 조회수 증가
        boardMapper.updateHitCount(boardIdx);

        //mapper를 사용하여 db에서 지정한 글의 상세 내용 가져오기

        FreeBoardDTO board = boardMapper.selectBoardDetail(boardIdx);

        return  board;
    }

    //게시글 수정하기
    @Override
    public void boardEdit(FreeBoardDTO board) throws Exception {
        boardMapper.editBoard(board);
    }

    //게시글 작성하기
    @Override
    public void insertBoard(FreeBoardDTO board, MultipartHttpServletRequest multiPart) throws Exception {
        boardMapper.insertBoard(board);

        //업로드 된 파일 정보에서 데이터 베이스에 저장하기 위한 BOardFileDto 타입으로 변환
        // 첫번쨰 매개변수로 해당 글의 글번호
        // 두번쨰 매개변수로 업로드된 파일 정보(MultipartHttpServletRequest)

        List<FreeBoardFileDTO> fileList = fileUtils.parseFileInfo(board.getBoardIdx(), board.getBoardUserId(), multiPart);
        //List타입의 BoardFileDto 의 fileList를 생성

        if(CollectionUtils.isEmpty(fileList) == false) { //안비었으면
            //CollectionUtils : 원래는 java class에서 제공하는 collection 타입의 클래스이나 스프링 프레임워크에서 제공하는 컬렉션 타입의 객체를 활용할 수 있는 클래스
            //utils : 도구모음 utility
            boardMapper.insertBoardFileList(fileList);
            //만들어진 파일정보를 db에 저장
        }
    }

    @Override
    public List<FreeBoardFileDTO> selectBoardFileInfo(int boardIdx) throws Exception {
        return boardMapper.selectBoardFileInfo(boardIdx);
    }


}
