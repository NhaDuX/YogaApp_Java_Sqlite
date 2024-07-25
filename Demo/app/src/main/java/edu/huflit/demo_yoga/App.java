package edu.huflit.demo_yoga;

import android.app.Application;

public class App extends Application {
    VideoDBHelper videoDBHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        videoDBHelper = new VideoDBHelper(this);
        videoDBHelper.copyDatabase();
    }
}
