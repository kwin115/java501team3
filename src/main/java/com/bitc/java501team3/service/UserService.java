package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.FreeBoardDTO;
import com.bitc.java501team3.dto.UserDTO;
import com.github.pagehelper.Page;

import java.util.List;

public interface UserService {
    void insertUser(UserDTO user) throws Exception;

    int isUserInfo(String userId, String userPw)throws Exception;

    UserDTO getUserInfo(String userId)throws Exception;

    List<FreeBoardDTO> selectWritingList(String userId) throws Exception;

    UserDTO selectUserDetail(String userId) throws Exception;


    void updateUser(UserDTO user) throws Exception;

    void deleteUser(UserDTO user) throws Exception;

    Page<FreeBoardDTO> selectBoardPageList(int pageNum,String userId);
}
