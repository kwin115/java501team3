package com.bitc.java501team3.controller;

import com.bitc.java501team3.dto.FavListDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.ItemDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.RootDTO;
import com.bitc.java501team3.service.MainService;
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
        return "main/main";
    }

    @ResponseBody
    @PostMapping("/main/hotPlaceJson")
    public List<ItemDTO> gethotPlaceJson() throws Exception {
        String optkey = "?serviceKey=";
        String opt1 = "&pageNo=";
        String opt2 = "&numOfRows=";
        String opt3 = "&resultType=json";

        List<ItemDTO> hotPlaceList = mainService.gethotPlaceJson(hotPlaceServiceUrl + optkey + hotPlaceServicekey + opt1 + "1" + opt2 + "400" + opt3);

        return hotPlaceList;
    }

    @RequestMapping("/main/detail")
    public String detail(@RequestParam String idx, Model model) throws Exception {
        model.addAttribute("idx", idx);
        return "/main/detail";
    }

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

    @ResponseBody
    @RequestMapping("/mainViewFavList")
    public ResponseEntity<List<FavListDTO>> viewFavList() throws Exception {
        List<FavListDTO> favList = mainService.selectFavList();
        return new ResponseEntity<>(favList, HttpStatus.OK);
    }
}


