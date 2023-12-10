package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.FavListDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.ItemDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.RootDTO;
import com.bitc.java501team3.mapper.MainMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private MainMapper mainMapper;
    @Override
    public List<ItemDTO> gethotPlaceJson(String serviceUrl)throws Exception {
        List<ItemDTO> itemList = null;

        URL url = null;
        HttpURLConnection urlConn = null;
        BufferedReader reader = null;

        try {
            url = new URL(serviceUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            Gson gson = new Gson();

            RootDTO fulldata = gson.fromJson(sb.toString(), RootDTO.class);
            itemList = fulldata.getGetFoodKr().getItem();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                reader.close();
            }
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
        return itemList;
    }

    @Override
    public void insertFav(int favListStoreIdx) throws Exception {
        mainMapper.insertFav(favListStoreIdx);
    }

    @Override
    public void deltetFav(int favListStoreIdx) {
        mainMapper.deleteFav(favListStoreIdx);
    }

    @Override
    public List<FavListDTO> selectFavList() {
        return mainMapper.selectFavList();
    }
}
