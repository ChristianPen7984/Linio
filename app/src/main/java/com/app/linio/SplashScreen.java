package com.app.linio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        launchSplashScreen();
    }

    private void launchSplashScreen() {
        Thread timer = new Thread() {
            @Override
            public  void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (Exception e) {
                    Toast.makeText(SplashScreen.this,"Unable to load splash screen",Toast.LENGTH_LONG).show();
                }
                Intent main = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(main);
            }
        };
        timer.start();
    }
}
