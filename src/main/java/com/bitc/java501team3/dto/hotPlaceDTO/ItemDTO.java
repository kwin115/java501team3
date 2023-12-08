package com.bitc.java501team3.dto.hotPlaceDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemDTO {
    @JsonProperty("UC_SEQ")
    public int uC_SEQ;
    @JsonProperty("MAIN_TITLE")
    public String mAIN_TITLE;
    @JsonProperty("GUGUN_NM")
    public String gUGUN_NM;
    @JsonProperty("LAT")
    public double lAT;
    @JsonProperty("LNG")
    public double lNG;
    @JsonProperty("PLACE")
    public String pLACE;
    @JsonProperty("TITLE")
    public String tITLE;
    @JsonProperty("SUBTITLE")
    public String sUBTITLE;
    @JsonProperty("ADDR1")
    public String aDDR1;
    @JsonProperty("ADDR2")
    public String aDDR2;
    @JsonProperty("CNTCT_TEL")
    public String cNTCT_TEL;
    @JsonProperty("HOMEPAGE_URL")
    public String hOMEPAGE_URL;
    @JsonProperty("USAGE_DAY_WEEK_AND_TIME")
    public String uSAGE_DAY_WEEK_AND_TIME;
    @JsonProperty("RPRSNTV_MENU")
    public String rPRSNTV_MENU;
    @JsonProperty("MAIN_IMG_NORMAL")
    public String mAIN_IMG_NORMAL;
    @JsonProperty("MAIN_IMG_THUMB")
    public String mAIN_IMG_THUMB;
    @JsonProperty("ITEMCNTNTS")
    public String iTEMCNTNTS;
}
