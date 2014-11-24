package com.example.channing.parser;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Channing on 11/23/2014.
 */
public class MyApplication extends Application {
    public void onCreate(){
        Parse.initialize(this, "APPLICATION ID", "CLIENT KEY");
    }
}
