package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.FavListDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.ItemDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.RootDTO;

import java.util.List;

public interface MainService {
    List<ItemDTO> gethotPlaceJson(String serviceUrl) throws Exception;

    void insertFav(int buttonIndex)throws Exception;

    void deltetFav(int favListStoreIdx);

    List<FavListDTO> selectFavList();
}
