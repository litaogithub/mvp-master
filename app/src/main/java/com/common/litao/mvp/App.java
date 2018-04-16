package com.common.litao.mvp;

import android.app.Application;
import android.content.Context;

/**
 * Created by litao on 6/18/17.
 */

public class App extends Application {

    private static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
