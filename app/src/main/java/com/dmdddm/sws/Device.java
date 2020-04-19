package com.dmdddm.sws;

public class Device {
    private String City;
    private String Weather;
    private String DeviceNo;
    private Boolean WindowsStatus;

    public Device(String city,String weather,String deviceNo,Boolean windowsStatus){
        City = city;
        Weather = weather;
        DeviceNo = deviceNo;
        WindowsStatus = windowsStatus;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getWeather() {
        return Weather;
    }

    public void setWeather(String weather) {
        Weather = weather;
    }

    public String getDeviceNo() {
        return DeviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        DeviceNo = deviceNo;
    }

    public Boolean getWindowsStatus() {
        return WindowsStatus;
    }

    public void setWindowsStatus(Boolean windowsStatus) {
        WindowsStatus = windowsStatus;
    }


}
