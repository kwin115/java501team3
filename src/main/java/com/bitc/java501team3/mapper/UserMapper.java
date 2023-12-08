package com.bitc.java501team3.mapper;

import com.bitc.java501team3.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(UserDTO user);
}
