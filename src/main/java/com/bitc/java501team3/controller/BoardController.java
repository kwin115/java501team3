package com.bitc.java501team3.controller;

import com.bitc.java501team3.dto.CommentDTO;
import com.bitc.java501team3.dto.FreeBoardDTO;
import com.bitc.java501team3.dto.FreeBoardFileDTO;
import com.bitc.java501team3.service.BoardService;
import com.bitc.java501team3.service.CommentService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    //게시판 글 목록
    @RequestMapping("/board/boardList.do")
    public ModelAndView selectBoardList(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum) throws Exception{

        ModelAndView mv = new ModelAndView("board/boardList");

        List<FreeBoardDTO> boardList = boardService.selectBoardList();

        PageInfo<FreeBoardDTO> boardPageList = new PageInfo<>(boardService.selectBoardPageList(pageNum), 3);

        mv.addObject("boardPageList", boardPageList);

        mv.addObject("boardList" , boardList);

        return mv;
    }

    // 글쓰기 view 페이지
    @RequestMapping("/board/boardWrite.do")
    public String boardWrite() throws Exception{
        return "board/boardWrite";
    }

    // 사용자가 입력한 데이터로 글쓰기 페이지로 이동하는 process작성
    // 매개변수를 BoardDto 클래스 타입으로 지정했기 때문에 html의 input 태그 중 name 속성 값을 BoardDto 클래스의 필드명과 동일하게 사용해야 함
    //MultipartHttpServletRequest : 클라이언트에서 전달한 파일 정보를 받아오는 클래스

    @RequestMapping("/board/insertBoard.do")
    public String insertBoard(FreeBoardDTO board, MultipartHttpServletRequest multiPart, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();

        // 세션에서 userId 값을 받아옵니다.
        String userId = (String) session.getAttribute("userId");

        // userId 값을 board 객체에 설정하거나 다른 처리를 수행합니다.
        board.setBoardUserId(userId);

        boardService.insertBoard(board, multiPart);

        return "redirect:/board/boardList.do";
    }

    //게시판 디테일 페이지
    @RequestMapping("/board/boardDetail.do")
    public ModelAndView boardDetail(@RequestParam int boardIdx) throws Exception{
        ModelAndView mv = new ModelAndView("board/boardDetail");

//        FreeBoardFileDTO boardFile =

        FreeBoardDTO board = boardService.selectBoardDetail(boardIdx);
        //        현재 글번호를 기준으로 첨부 파일 목록을 가져옴

        List<FreeBoardFileDTO> boardFileList = boardService.selectBoardFileInfo(boardIdx);

        List<CommentDTO> comment = commentService.commentList(boardIdx);

        mv.addObject("board" , board);
        mv.addObject("comment", comment);
        mv.addObject("boardFileList",boardFileList);

        return mv;
    }


    //게시글 수정하기화면
    @RequestMapping("/board/boardEdit.do")
    public ModelAndView boardEditView(@RequestParam int boardIdx) throws Exception {

        ModelAndView mv = new ModelAndView("board/boardEdit");

        FreeBoardDTO board = boardService.selectBoardDetail(boardIdx);

        mv.addObject("board", board);

        return mv;
    }

    @ResponseBody
    @PutMapping("/board/{boardIdx}")
    public String boardEdit(FreeBoardDTO board) throws Exception {
        boardService.boardEdit(board);

        return "redirect:/board/boardList.do";
    }

}
