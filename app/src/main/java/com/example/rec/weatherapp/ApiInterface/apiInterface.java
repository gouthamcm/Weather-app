package com.example.rec.weatherapp.ApiInterface;

import com.example.rec.weatherapp.Model.List;
import com.example.rec.weatherapp.Model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiInterface {
    @GET("forecast?appid=267170b82441aa5239d6d7d4df6a1ea9")
    Call<Weather> getWeather(@Query("q") String city);
}
