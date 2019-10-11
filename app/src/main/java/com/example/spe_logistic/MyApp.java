package com.example.spe_logistic;

import android.app.Application;
import android.content.Context;


public class MyApp extends Application{

    private static MyApp INSTANCE;
    public static Context getContext() {return INSTANCE;}

}
