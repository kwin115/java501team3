package com.bitc.java501team3.controller;

import com.bitc.java501team3.dto.hotPlaceDTO.RootDTO;
import com.bitc.java501team3.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    @Value("${fullstack501.hotplace.service.url}")
    private String hotPlaceServiceUrl;

    @Value("${fullstack501.hotplace.service.key}")
    private String hotPlaceServicekey;

    @RequestMapping("/")
    public String index() throws Exception {
        return "index";
    }

    @RequestMapping("/main")
    public String main() throws Exception {
        return "/main/main";
    }

    @ResponseBody
    @PostMapping("/main/hotPlaceJson")
    public List<RootDTO> getdailyBoxOfficeJson() throws Exception{

        String optkey ="?key=";
        String opt1 ="&targetDt=";

//        서비스를 통해서 데이터 가져오기
        List<RootDTO> dailyBoxOfficeList = mainService.gethotPlaceJson(hotPlaceServiceUrl + hotPlaceServicekey) ;

        return dailyBoxOfficeList;
    }

}
