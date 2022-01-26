package com.example.app4.controller;

import android.app.Application;
import android.app.DownloadManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Acontroller extends Application {
    public static Acontroller Instance;
    public RequestQueue requestQueue;
    public static synchronized Acontroller getInstance(){
        if (Instance==null){
            Instance=new Acontroller();
        }
        return Instance;
    }
    public  <T> void AddRequest(Request<T> req){

        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        requestQueue.add(req);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Instance=this;
    }
}
