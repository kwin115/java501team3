package com.bitc.java501team3.dto.hotPlaceDTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class GetFoodKrDTO {
    public HeaderDTO headerDTO;
    public ArrayList<ItemDTO> itemDTO;
    public int numOfRows;
    public int pageNo;
    public int totalCount;
}
