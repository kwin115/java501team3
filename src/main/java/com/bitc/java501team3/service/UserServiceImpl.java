package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.UserDTO;
import com.bitc.java501team3.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void insertUser(UserDTO user) throws Exception {
        userMapper.insertUser(user);
    }
}
