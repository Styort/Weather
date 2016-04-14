package com.example.weather.Models;

import com.orm.SugarRecord;

/**
 * Created by Виктор on 15.04.2016.
 */
public class Weather extends SugarRecord {
    public double temp,tempMin,tempMax,pressure,windSpeed;
    public int humidity;
    public String city,weather,description,data;

    public Weather(String city, String description, int humidity, double pressure,
                   double temp, double tempMax, double tempMin, String weather, double windSpeed, String data) {
        this.city = city;
        this.description = description;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp = temp;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.weather = weather;
        this.windSpeed = windSpeed;
        this.data = data;
    }
    public Weather(){}
}
