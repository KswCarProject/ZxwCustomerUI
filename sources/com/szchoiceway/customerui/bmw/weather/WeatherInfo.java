package com.szchoiceway.customerui.bmw.weather;

import com.txznet.weatherquery.HourWeather;
import java.util.ArrayList;
import java.util.List;

public class WeatherInfo {
    private String curCity = "";
    private String curRain = "";
    private String curTemp = "";
    private String curTempUnit = "";
    private String curVisiblity = "";
    private String curWind = "";
    private List<HourWeather> hourWeathers = new ArrayList();
    private String maxTemp = "";
    private String minTemp = "";
    private String phrase = "";
    private String phraseID = "";

    public List<HourWeather> getHourWeathers() {
        return this.hourWeathers;
    }

    public void setHourWeathers(List<HourWeather> list) {
        this.hourWeathers = list;
    }

    public String getPhraseID() {
        return this.phraseID;
    }

    public String getPhrase() {
        return this.phrase;
    }

    public void setPhrase(String str) {
        this.phrase = str;
    }

    public void setPhraseID(String str) {
        this.phraseID = str;
    }

    public String getCurTemp() {
        return this.curTemp;
    }

    public void setCurTemp(String str) {
        this.curTemp = str;
    }

    public String getCurTempUnit() {
        return this.curTempUnit;
    }

    public void setCurTempUnit(String str) {
        this.curTempUnit = str;
    }

    public String getMinTemp() {
        return this.minTemp;
    }

    public void setMinTemp(String str) {
        this.minTemp = str;
    }

    public String getMaxTemp() {
        return this.maxTemp;
    }

    public void setMaxTemp(String str) {
        this.maxTemp = str;
    }

    public String getCurCity() {
        return this.curCity;
    }

    public void setCurCity(String str) {
        this.curCity = str;
    }

    public String getCurWind() {
        return this.curWind;
    }

    public void setCurWind(String str) {
        this.curWind = str;
    }

    public String getCurVisiblity() {
        return this.curVisiblity;
    }

    public void setCurVisiblity(String str) {
        this.curVisiblity = str;
    }

    public String getCurRain() {
        return this.curRain;
    }

    public void setCurRain(String str) {
        this.curRain = str;
    }
}
