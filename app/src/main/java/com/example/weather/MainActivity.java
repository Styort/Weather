package com.example.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weather.Adapters.WeatherAdapter;
import com.example.weather.Models.Weather;
import com.orm.SugarContext;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    WeatherTask weatherTask;
    List<Weather> weather;
    ListView weatherList;
    TextView data, city, temp, description, wind, humidity, pressure, fulldesk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);
        weatherTask = new WeatherTask();
        weatherTask.execute(this);
        initElement();
        initAdapter();
    }

    private void initElement() {
        data = (TextView) findViewById(R.id.data_tv);
        city = (TextView) findViewById(R.id.city_tv);
        temp = (TextView) findViewById(R.id.temp_tv);
        description = (TextView) findViewById(R.id.desk_tv);
        wind = (TextView) findViewById(R.id.wind_tv);
        humidity = (TextView) findViewById(R.id.humidity_tv);
        pressure = (TextView) findViewById(R.id.pressure_tv);
        fulldesk = (TextView) findViewById(R.id.fulldesk_tv);
        weatherList = (ListView)findViewById(R.id.weather_lv);
    }

    public void onJSONParsed() {
        initAdapter();
    }

    @Override
    protected void onStop() {
        super.onStop();
        weatherTask.cancel(true);
    }

    private void initAdapter() {
        WeatherAdapter weatherAdapter = new WeatherAdapter(this,0, Weather.listAll(Weather.class));
        weatherList.setAdapter(weatherAdapter);
        weatherAdapter.notifyDataSetChanged();
    }



    /*
        switch (weather.get(0).description) {
            case "light rain":
                fulldesk.append("Небольшой дождь");
                break;
            case "few clouds":
                fulldesk.append("Незначительная облачность");
                break;
            case "scattered clouds":
                fulldesk.append("Переменная облачность");
                break;
            case "overcast clouds":
                fulldesk.append("Пасмурно");
                break;
            case "broken clouds":
                fulldesk.append("Значительная облачность");
                break;
            case "clear sky":
                fulldesk.append("Безоблачное небо");
                break;
            case "moderate rain":
                fulldesk.append("Умеренный дождь");
                break;
            case "snow":
                fulldesk.append("Снег");
                break;
            case "light snow":
                fulldesk.append("Небольшой снег");
                break;*/

}
