package app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApp extends Application {
    /*
    在app中初始化fresco
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
