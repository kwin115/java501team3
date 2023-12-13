package com.bitc.java501team3.controller;

import com.bitc.java501team3.dto.FreeBoardDTO;
import com.bitc.java501team3.dto.UserDTO;
import com.bitc.java501team3.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    //    회원가입
    @RequestMapping("/join")
    public String join() throws Exception {
        return "/user/join";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String userInsertProcess(UserDTO user) throws Exception {
        userService.insertUser(user);
        return "redirect:/main";
    }

    //    로그인
    @RequestMapping("/login")
    public String loginForm()throws Exception{
        return "redirect:/main";
    }
    @PostMapping(value = "/loginProcess")
    public String loginProcess(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpServletRequest req) throws Exception {
        int result = userService.isUserInfo(userId, userPw);


        if (result == 1) {
            UserDTO user = userService.getUserInfo(userId);

            HttpSession session = req.getSession();
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userPw", user.getUserPw());
            session.setAttribute("userName", user.getUserName());
            session.setMaxInactiveInterval(60 * 60 * 1); // 세션 유지 시간 설정

            return "redirect:/main";
        }
        else {
            return "redirect:/user/login?errorMsg=" + URLEncoder.encode("로그인 정보가 다릅니다.", "UTF-8");
//            return "redirect:/user/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();

        session.removeAttribute("userId");
        session.removeAttribute("userName");
        session.removeAttribute("userPw");

        session.invalidate();

        return "redirect:/main";
    }

    @RequestMapping(value = "/writingList", method = RequestMethod.GET)
    public ModelAndView selectWritingList(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, @RequestParam String userId) throws Exception {

        ModelAndView mv = new ModelAndView("/user/writingList");

        List<FreeBoardDTO> writingList = userService.selectWritingList(userId);

        PageInfo<FreeBoardDTO> boardPageList = new PageInfo<>(userService.selectBoardPageList(pageNum,userId), 5);

        mv.addObject("boardPageList", boardPageList);
        mv.addObject("writingList" , writingList);

        return mv;
    }

//    @RequestMapping("/updateUser")
//    public String updateUserView() throws Exception {
//        return "/user/updateUser";
//    }

    @RequestMapping("/userDetail.do")
    public ModelAndView userDetail(@RequestParam String userId) throws Exception {
        ModelAndView mv = new ModelAndView("/user/userDetail");

        UserDTO user = userService.selectUserDetail(userId);
        mv.addObject("user" , user);

        return mv;

    }

    //    회원정보 수정하기
    @RequestMapping("/updateUser.do")
    public String updateUser(UserDTO user) throws Exception {

        userService.updateUser(user);

        return "redirect:/user/logout";
    }

    //    회원 탈퇴
    @RequestMapping("/deleteUser.do")
    public String deleteUser(UserDTO user) throws Exception {

        userService.deleteUser(user);

        return "redirect:/main";
    }

}
