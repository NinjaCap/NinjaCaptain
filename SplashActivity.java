package com.bestpickindia.android.bestpick;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;


/**
 * Created by HP on 6/20/2016.
 */
public class SplashActivity extends Activity {
    private static int SPLASH_TIME_OUT = 2000;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                session.checkLogin();
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }







}
