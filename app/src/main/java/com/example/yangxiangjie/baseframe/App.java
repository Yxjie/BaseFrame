package com.example.yangxiangjie.baseframe;

import android.app.Application;
import android.content.Context;

/**
 * Created by yangxiangjie on 2017/11/30.
 *
 */

public class App extends Application {

    private static App sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;

    }

    public static Context getAppContext() {
        return sApp;
    }


}
