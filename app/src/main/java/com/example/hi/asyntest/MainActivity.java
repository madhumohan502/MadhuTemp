package com.example.hi.asyntest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DentistKartData{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(InternetStatus.isWifiOrMobileConnected(this)){
            new ServerCall("https://restcountries.eu/rest/v2/all", MainActivity.this, "response").execute();

        }else{
            Toast.makeText(MainActivity.this, "no internet", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void serverData(String data, String method) {

    }
}
