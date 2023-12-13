package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.FreeBoardDTO;
import com.bitc.java501team3.dto.UserDTO;
import com.bitc.java501team3.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void insertUser(UserDTO user) throws Exception {
        userMapper.insertUser(user);
    }

    @Override
    public int isUserInfo(String userId, String userPw)throws Exception {
        return userMapper.isUserInfo(userId, userPw);
    }

    @Override
    public UserDTO getUserInfo(String userId)throws Exception {
        return userMapper.getUserInfo(userId);
    }

    @Override
    public List<FreeBoardDTO> selectWritingList(String userId) throws Exception {
        return userMapper.selectWritingList(userId);
    }

    @Override
    public UserDTO selectUserDetail(String userId) throws Exception {
        return userMapper.selectUserDetail(userId);
    }

    @Override
    public void updateUser(UserDTO user) throws Exception {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(UserDTO user) throws Exception {
        userMapper.deleteUser(user);
    }

    @Override
    public Page<FreeBoardDTO> selectBoardPageList(int pageNum,String userId) {
        PageHelper.startPage(pageNum, 3);
        return userMapper.selectBoardPageList(userId);
    }

}
