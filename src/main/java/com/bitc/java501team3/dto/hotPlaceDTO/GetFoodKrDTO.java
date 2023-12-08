package com.bitc.java501team3.dto.hotPlaceDTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetFoodKrDTO {
    private HeaderDTO header;
    private List<ItemDTO> item;

    public HeaderDTO getHeader() {
        return header;
    }

    public void setHeader(HeaderDTO header) {
        this.header = header;
    }

    public List<ItemDTO> getItem() {
        return item;
    }

    public void setItem(List<ItemDTO> item) {
        this.item = item;
    }
}