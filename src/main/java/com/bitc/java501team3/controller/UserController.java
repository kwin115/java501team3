package com.bitc.java501team3.controller;

import com.bitc.java501team3.dto.UserDTO;
import com.bitc.java501team3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/join")
    public String join() throws Exception {
        return "/user/join";
    }

    @PostMapping("/insert")
    public String userInsertProcess(UserDTO user) throws Exception {
        userService.insertUser(user);
        return "/user/join";
    }
}
