package com.bitc.java501team3.mapper;

import com.bitc.java501team3.dto.FavListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    void insertFav(int favListStoreIdx);

    void deleteFav(int favListStoreIdx);

    List<FavListDTO> selectFavList();
}
