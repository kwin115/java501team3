package com.bitc.java501team3.mapper;

import com.bitc.java501team3.dto.FreeBoardDTO;
import com.bitc.java501team3.dto.UserDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    void insertUser(UserDTO user)throws Exception;

    int isUserInfo(@Param("userId") String userId,@Param("userPw") String userPw)throws Exception;

    UserDTO getUserInfo(@Param("userId") String userId)throws Exception;


    List<FreeBoardDTO> selectWritingList(String userId) throws Exception;

    UserDTO selectUserDetail(String userId) throws Exception;

    void updateUser(UserDTO user) throws Exception;

    void deleteUser(UserDTO user) throws Exception;

    Page<FreeBoardDTO> selectBoardPageList(String userId);
}
