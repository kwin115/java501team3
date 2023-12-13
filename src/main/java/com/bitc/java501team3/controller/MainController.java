package com.bitc.java501team3.controller;

import com.bitc.java501team3.dto.FavListDTO;
import com.bitc.java501team3.dto.ReviewDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.ItemDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.RootDTO;
import com.bitc.java501team3.service.MainService;
import com.bitc.java501team3.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    @Autowired
    private ReviewService reviewService;

    @Value("${fullstack501.hotplace.service.url}")
    private String hotPlaceServiceUrl;

    @Value("${fullstack501.hotplace.service.key}")
    private String hotPlaceServicekey;

    @RequestMapping("/")
    public String index() throws Exception {
        return "index";
    }

    // 단순 뷰
    @RequestMapping("/main")
    public String main() throws Exception {
        return "main/main";
    }

    // 기본 목록 가져오기
    @ResponseBody
    @PostMapping("/main/hotPlaceJson")
    public List<ItemDTO> gethotPlaceJson() throws Exception {
        String optkey = "?serviceKey=";
        String opt1 = "&pageNo=";
        String opt2 = "&numOfRows=";
        String opt3 = "&resultType=json";

        List<ItemDTO> hotPlaceList = mainService.gethotPlaceJson(hotPlaceServiceUrl + optkey + hotPlaceServicekey + opt1 + "1" + opt2 + "300" + opt3);

        return hotPlaceList;
    }

    @RequestMapping("/main/detail")
    public ModelAndView detail(@RequestParam int idx) throws Exception {
        ModelAndView mv = new ModelAndView("/main/detail");


        mv.addObject("idx",idx);


        return mv;
    }

//    즐겨찾기 추가
    @ResponseBody
    @RequestMapping("/mainAddFav")
    public String mainAddFav(@RequestParam("favListStoreIdx") int favListStoreIdx) throws Exception {
        try {
            mainService.insertFav(favListStoreIdx);
            return "success";
        } catch (Exception e) {
            e.printStackTrace(); // 또는 로그 기록
            return "error";
        }
    }

//    즐겨찾기 삭제
    @ResponseBody
    @RequestMapping("/mainDeleteFav")
    public String mainDeleteFav(@RequestParam("favListStoreIdx") int favListStoreIdx) throws Exception {
        try {
            mainService.deltetFav(favListStoreIdx);
            return "success";
        } catch (Exception e) {
            e.printStackTrace(); // 또는 로그 기록
            return "error";
        }
    }

//    즐겨찾기 목록 불러오기
    @ResponseBody
    @RequestMapping("/mainViewFavList")
    public ResponseEntity<List<FavListDTO>> viewFavList() throws Exception {
        List<FavListDTO> favList = mainService.selectFavList();
        return new ResponseEntity<>(favList, HttpStatus.OK);
    }
}


