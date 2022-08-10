package com.example.lifechoices;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private  static  Context myContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myContext = getApplicationContext();
    }

    public static Context getContext(){

        return myContext;
    }
}
