package com.bitc.java501team3.dto.hotPlaceDTO;

import lombok.Data;

@Data
public class RootDTO {
    private GetFoodKrDTO getFoodKr;

    public GetFoodKrDTO getGetFoodKr() {
        return getFoodKr;
    }

    public void setGetFoodKr(GetFoodKrDTO getFoodKr) {
        this.getFoodKr = getFoodKr;
    }
}
