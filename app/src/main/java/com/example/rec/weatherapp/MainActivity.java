package com.example.rec.weatherapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rec.weatherapp.ApiInterface.apiClient;
import com.example.rec.weatherapp.ApiInterface.apiInterface;
import com.example.rec.weatherapp.Model.List;
import com.example.rec.weatherapp.Model.Weather;

import java.io.DataInput;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String city="london";
    private  String TAG="SAAJI";
    private int REQUEST_CODE=123;
    private  int count0=0;
    private int count1=0; private int count2=0; private int count3=0;private int count4=0;

    private TextView mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDesc=findViewById(R.id.textView);
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"inside onresume");
        getPermission();
        getWeatherResult();
    }
    public void getPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION
            },REQUEST_CODE );

            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                getWeatherResult();

            }
        }
    }
    public void getWeatherResult(){
        apiInterface api= apiClient.getClient().create(apiInterface.class);
        Call<Weather> call=api.getWeather(city);
        Log.d(TAG,"reached inside getWeatherResult()");
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Log.d(TAG,"inside onResponse");
               java.util.List<List> resp = response.body().getList();
                Calendar c=Calendar.getInstance();

                SimpleDateFormat TimeStamp=new SimpleDateFormat("YYYY-MM-dd");
                String today=TimeStamp.format(c.getTime());

                c.add(Calendar.DATE,1);
                String future1=TimeStamp.format(c.getTime());
                c.add(Calendar.DATE,1);
                String future2=TimeStamp.format(c.getTime());
                c.add(Calendar.DATE,1);
                String future3=TimeStamp.format(c.getTime());
                c.add(Calendar.DATE,1);
                String future4=TimeStamp.format(c.getTime());






               for(int i=0;i<40;i++) {



                   String date0=resp.get(i).getDtTxt().toString();
                   if(date0.substring(0,10).equals(today)&&count0<1) {
                      String temp= resp.get(i).getMain().getTemp().toString();

                       Log.d(TAG,"day1"+temp);
                       count0++;

                   }
                  else if(date0.substring(0,10).equals(future1)&&count1<1){
                       String temp=resp.get(i).getMain().getTemp().toString();
                       Log.d(TAG,"day2"+temp);
                       count1++;
                   }
                   else if(date0.substring(0,10).equals(future2)&&count2<1){
                       String temp=resp.get(i).getMain().getTemp().toString();
                       Log.d(TAG,"day3"+temp);
                       count2++;
                   }
                  else if(date0.substring(0,10).equals(future3)&&count3<1){
                       String temp=resp.get(i).getMain().getTemp().toString();
                       Log.d(TAG,"day4"+temp);
                       count3++;
                   }
                  else if(date0.substring(0,10).equals(future4)&&count4<1){
                       String temp=resp.get(i).getMain().getTemp().toString();
                       Log.d(TAG,"day5"+temp);
                       count4++;
                   }

               }




            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

                if(t instanceof IOException){
                    Log.d(TAG,"inside onFailure DUE TO CONNECTION ISSUES");}
                else{

                    Log.e(TAG,t.toString());
                    Log.d(TAG,"conversio problem");
                }
            }
        });
    }

}
