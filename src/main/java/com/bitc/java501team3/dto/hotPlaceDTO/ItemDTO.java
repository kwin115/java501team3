package com.bitc.java501team3.dto.hotPlaceDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemDTO {
    private int UC_SEQ;
    private String MAIN_TITLE;
    private String GUGUN_NM;
    private double LAT;
    private double LNG;
    private String PLACE;
    private String TITLE;
    private String SUBTITLE;
    private String ADDR1;
    private String ADDR2;
    private String CNTCT_TEL;
    private String HOMEPAGE_URL;
    private String USAGE_DAY_WEEK_AND_TIME;
    private String RPRSNTV_MENU;
    private String MAIN_IMG_NORMAL;
    private String MAIN_IMG_THUMB;
    private String ITEMCNTNTS;

    public int getUC_SEQ() {
        return UC_SEQ;
    }

    public void setUC_SEQ(int UC_SEQ) {
        this.UC_SEQ = UC_SEQ;
    }

    public String getMAIN_TITLE() {
        return MAIN_TITLE;
    }

    public void setMAIN_TITLE(String MAIN_TITLE) {
        this.MAIN_TITLE = MAIN_TITLE;
    }

    public String getGUGUN_NM() {
        return GUGUN_NM;
    }

    public void setGUGUN_NM(String GUGUN_NM) {
        this.GUGUN_NM = GUGUN_NM;
    }

    public double getLAT() {
        return LAT;
    }

    public void setLAT(double LAT) {
        this.LAT = LAT;
    }

    public double getLNG() {
        return LNG;
    }

    public void setLNG(double LNG) {
        this.LNG = LNG;
    }

    public String getPLACE() {
        return PLACE;
    }

    public void setPLACE(String PLACE) {
        this.PLACE = PLACE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getSUBTITLE() {
        return SUBTITLE;
    }

    public void setSUBTITLE(String SUBTITLE) {
        this.SUBTITLE = SUBTITLE;
    }

    public String getADDR1() {
        return ADDR1;
    }

    public void setADDR1(String ADDR1) {
        this.ADDR1 = ADDR1;
    }

    public String getADDR2() {
        return ADDR2;
    }

    public void setADDR2(String ADDR2) {
        this.ADDR2 = ADDR2;
    }

    public String getCNTCT_TEL() {
        return CNTCT_TEL;
    }

    public void setCNTCT_TEL(String CNTCT_TEL) {
        this.CNTCT_TEL = CNTCT_TEL;
    }

    public String getHOMEPAGE_URL() {
        return HOMEPAGE_URL;
    }

    public void setHOMEPAGE_URL(String HOMEPAGE_URL) {
        this.HOMEPAGE_URL = HOMEPAGE_URL;
    }

    public String getUSAGE_DAY_WEEK_AND_TIME() {
        return USAGE_DAY_WEEK_AND_TIME;
    }

    public void setUSAGE_DAY_WEEK_AND_TIME(String USAGE_DAY_WEEK_AND_TIME) {
        this.USAGE_DAY_WEEK_AND_TIME = USAGE_DAY_WEEK_AND_TIME;
    }

    public String getRPRSNTV_MENU() {
        return RPRSNTV_MENU;
    }

    public void setRPRSNTV_MENU(String RPRSNTV_MENU) {
        this.RPRSNTV_MENU = RPRSNTV_MENU;
    }

    public String getMAIN_IMG_NORMAL() {
        return MAIN_IMG_NORMAL;
    }

    public void setMAIN_IMG_NORMAL(String MAIN_IMG_NORMAL) {
        this.MAIN_IMG_NORMAL = MAIN_IMG_NORMAL;
    }

    public String getMAIN_IMG_THUMB() {
        return MAIN_IMG_THUMB;
    }

    public void setMAIN_IMG_THUMB(String MAIN_IMG_THUMB) {
        this.MAIN_IMG_THUMB = MAIN_IMG_THUMB;
    }

    public String getITEMCNTNTS() {
        return ITEMCNTNTS;
    }

    public void setITEMCNTNTS(String ITEMCNTNTS) {
        this.ITEMCNTNTS = ITEMCNTNTS;
    }
}
