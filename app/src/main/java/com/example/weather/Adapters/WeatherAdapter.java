package com.example.weather.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.Models.Weather;
import com.example.weather.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Виктор on 15.04.2016.
 */
public class WeatherAdapter extends ArrayAdapter {
    Activity context;
    List<Weather> weathers;

    public WeatherAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        this.weathers = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DecimalFormat formatter = new DecimalFormat("##,#.0");
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_item,null);
        String sign;
        if (weathers.get(position).temp > 0) {
            sign = "+";
        } else {
            sign = "-";
        }
        ((TextView) convertView.findViewById(R.id.temp_text_view)).setText(sign+formatter.format(weathers.get(position).temp)+" °C"); //добавляем температуру
        String description = null;
        int id = 0;
        switch (weathers.get(position).weather) {
            case "Rain":
                description="Дождь";
                id = context.getResources().getIdentifier("rain","drawable",context.getPackageName());
                break;
            case "Clouds":
                description="Облачно";
                id = context.getResources().getIdentifier("cloudy","drawable",context.getPackageName());
                break;
            case "Clear":
                description="Безоблачно";
                id = context.getResources().getIdentifier("partly_cloudy","drawable",context.getPackageName());
                break;
            case "Snow":
                description="Снег";
                id = context.getResources().getIdentifier("sunny_s_cloudy","drawable",context.getPackageName());
                break;
        }
        ((TextView) convertView.findViewById(R.id.deskription_text_view)).setText(description); //добавляем описание погоды
        ((TextView) convertView.findViewById(R.id.wind_text_view)).append(String.valueOf(weathers.get(position).windSpeed)+ " м/c"); //ветер
        ((TextView) convertView.findViewById(R.id.pressure_text_view)).append(String.valueOf(weathers.get(position).pressure)+ " мм рт. ст.");
        ((TextView) convertView.findViewById(R.id.humidity_text_view)).append(String.valueOf(weathers.get(position).humidity)+ " %");
        ((ImageView) convertView.findViewById(R.id.weather_image_view)).setImageResource(id);
        return convertView;
    }
}
