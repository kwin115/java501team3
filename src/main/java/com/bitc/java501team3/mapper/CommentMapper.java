package com.bitc.java501team3.mapper;

import com.bitc.java501team3.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    public List<CommentDTO> commentList(int cmBoardIdx) throws Exception;
}
