package com.ayakut.Bayes.dto;

public class PlayGolfDto {

    private String outlook;
    private String temp;
    private String humidity;
    private String windly;
    private String isPlay;

    public PlayGolfDto(String outlook, String temp, String humidity, String windly, String isPlay) {
        this.outlook = outlook;
        this.temp = temp;
        this.humidity = humidity;
        this.windly = windly;
        this.isPlay = isPlay;
    }

    public PlayGolfDto(String outlook, String temp, String humidity, String windly) {
        this.outlook = outlook;
        this.temp = temp;
        this.humidity = humidity;
        this.windly = windly;
    }

    public PlayGolfDto() {

    }


    public String getOutlook() {
        return outlook;
    }

    public void setOutlook(String outlook) {
        this.outlook = outlook;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindly() {
        return windly;
    }

    public void setWindly(String windly) {
        this.windly = windly;
    }

    public String getIsPlay() {
        return isPlay;
    }

    public void setIsPlay(String isPlay) {
        this.isPlay = isPlay;
    }
}
