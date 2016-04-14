package com.example.weather;

import android.os.AsyncTask;

import com.example.weather.Models.Weather;
import com.google.common.io.CharStreams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Виктор on 14.04.2016.
 */
public class WeatherTask extends AsyncTask {
    MainActivity activity;
    @Override
    protected Object doInBackground(Object[] params) {
        activity = (MainActivity)params[0];
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/city?id=524901&APPID=YOURAPPID");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();//соединяемся с json
            String response = CharStreams.toString(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(response);
            String cityObj = jsonObject.getString("city");
            JSONObject cityJson = new JSONObject(cityObj);
            String cityName = cityJson.getString("name");
            JSONArray list = jsonObject.getJSONArray("list");
            Weather.deleteAll(Weather.class);
            for (int i =0;i<list.length();i++){
                JSONObject mainObj = list.getJSONObject(i);
                String main = mainObj.getString("main");
                JSONObject tempObj = new JSONObject(main);
                double temp = tempObj.getDouble("temp")/32; //получаем температуру
                double tempMax = tempObj.getDouble("temp_max")/32; //получаем максимальную температуру
                double tempMin = tempObj.getDouble("temp_min")/32; //получаем минимальную температуру
                double pressure = tempObj.getDouble("pressure"); //получаем давление
                int humidity = tempObj.getInt("humidity"); //получаем влажность
                JSONArray weatherArr = mainObj.getJSONArray("weather");
                String weatherDesk = null;
                String weatherDeskFull=null;
                for(int j=0;j<weatherArr.length();j++){
                    JSONObject weathObj = weatherArr.getJSONObject(j);
                    weatherDesk = weathObj.getString("main"); // получаем описание погоды
                    weatherDeskFull = weathObj.getString("description"); // получаем полное описание погоды
                }
                String date = mainObj.getString("dt_txt"); //получаем дату и время погоды
                JSONObject windObj = mainObj.getJSONObject("wind");
                double windSpeed = windObj.getDouble("speed"); //получаем скорость ветра

                Weather weather = new Weather(cityName,weatherDeskFull,humidity,pressure,temp,tempMax
                        ,tempMin,weatherDesk,windSpeed,date);
                weather.save();
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        activity.onJSONParsed();
    }
}
