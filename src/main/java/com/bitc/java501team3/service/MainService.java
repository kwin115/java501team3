package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.hotPlaceDTO.RootDTO;

import java.util.List;

public interface MainService {
    List<RootDTO> gethotPlaceJson(String s);
}
