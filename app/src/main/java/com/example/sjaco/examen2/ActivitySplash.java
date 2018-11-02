package com.example.sjaco.examen2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sjaco.examen2.beans.City;
import com.example.sjaco.examen2.beans.Store;
import com.example.sjaco.examen2.beans.User;
import com.example.sjaco.examen2.database.DataBaseHandler;
import com.example.sjaco.examen2.database.StoreControl;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity {

    DataBaseHandler dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dh = DataBaseHandler.getInstance(this);
        StoreControl storeControl = new StoreControl();

        if(storeControl.getStores(dh).size() == 0) {
            City city = new City();
            city.setId(2);
            Store store = new Store("BestBuy", "0123456789", 0, -20.123123, 101.12312312, city);
            Store store2 = new Store("HomeDepot", "1234567890", 0, -25.123123, 101.12312312, city);
            Store store3 = new Store("Liverpool", "3339450520", 0, -20.123123, 120.14562312, city);
            storeControl.addStore(store, dh);
            storeControl.addStore(store2, dh);
            storeControl.addStore(store3, dh);
        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                User user = loadUser();
                Intent intent;
                if(user.isLogged()){
                    intent = new Intent(ActivitySplash.this, ActivityMain.class);
                }else{
                    intent = new Intent(ActivitySplash.this, ActivityLogin.class);
                }
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);

    }

    public User loadUser(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.USER_PREFERENCES",
                        MODE_PRIVATE);
        User user = new User();
        user.setName(sharedPreferences.getString("NAME", null));
        user.setPassword(sharedPreferences.getString("PWD", null));
        user.setLogged(sharedPreferences.getBoolean("LOGGED", false));
        return user;
    }
}
